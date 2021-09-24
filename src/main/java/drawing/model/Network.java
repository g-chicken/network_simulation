package drawing.model;

import data.dto.LinkDto;
import data.dto.NetworkDto;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;
import utils.StdOutHandler;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;
import utils.mathematics.Coordination;
import utils.network.CoreNetwork;

/**
 * Network express a network to draw.
 */
public class Network extends CoreNetwork {
  private static final Logger logger = Logger.getLogger(Network.class.getName());
  private final Vertex[] vertexes;
  private final Link[] links;
  private final String label;
  private final double constant;
  private double range;
  private final Random random;

  Network(final Vertex @NotNull [] vertices, final Link @NotNull [] links, final int[] tails,
          final int[] heads, final double[] weights, final int[] first, final int[] adjList,
          final String label) {
    super(vertices.length, links.length, tails, heads, weights, first, adjList);

    this.vertexes = vertices;
    this.links = links;
    this.label = label;

    constant = Math.sqrt(1.0 / this.vertexes.length);
    range = 0.0;
    random = new Random(System.currentTimeMillis());
    logger.addHandler(new StdOutHandler());
    logger.setUseParentHandlers(false);
    logger.setLevel(Level.INFO);
  }

  /**
   * constructor.
   *
   * @param networkDto the network DTO
   */
  public Network(final @NotNull NetworkDto networkDto) {
    super(networkDto.getVertexNum(), networkDto.getLinkNum());

    vertexes = new Vertex[this.getVertexNum()];
    for (int i = 0; i < this.getVertexNum(); i++) {
      vertexes[i] =
          new Vertex(networkDto.vertexDtoes()[i].c(), networkDto.vertexDtoes()[i].label());
    }

    links = new Link[this.getLinkNum()];
    for (int i = 0; i < this.getLinkNum(); i++) {
      LinkDto dto = networkDto.linkDtoes()[i];
      links[i] = new Link(0.0, dto.label());
      tails[i] = dto.tailVertexIndex();
      heads[i] = dto.headVertexIndex();
      weights[i] = dto.weight();
    }

    label = networkDto.label();

    setAdjacencyList();

    constant = Math.sqrt(1.0 / this.vertexes.length);
    range = 0.0;
    random = new Random(System.currentTimeMillis());
    logger.addHandler(new StdOutHandler());
    logger.setUseParentHandlers(false);
    logger.setLevel(Level.INFO);
  }

  /**
   * constructor.
   *
   * @param network    the network of analysis model
   * @param vertexRate vertex rate (0 <= vertexRate <= 1) (for example, closeness centrality for the vertex)
   * @param linkRate   link rate (0 <= linkRate <= 1) (for example, closeness centrality for the link)
   */
  public Network(final analysis.model.@NotNull Network network, final double[] vertexRate,
                 final double[] linkRate) {
    super(network.getVertexNum(), network.getLinkNum(), network.getTails(), network.getHeads(),
        network.getWeights(), network.getFirst(), network.getAdjList());

    vertexes = new Vertex[this.getVertexNum()];
    for (int i = 0; i < this.getVertexNum(); i++) {
      double rate = 0.0;
      if (vertexRate != null && vertexRate.length > i) {
        rate = vertexRate[i];
      }

      vertexes[i] = new Vertex(rate, network.getVertexes()[i].label());
    }

    links = new Link[this.getLinkNum()];
    for (int i = 0; i < this.getLinkNum(); i++) {
      double rate = 0.0;
      if (linkRate != null && linkRate.length > i) {
        rate = linkRate[i];
      }

      links[i] = new Link(rate, network.getLinks()[i].label());
    }

    this.label = network.getLabel();

    constant = Math.sqrt(1.0 / this.getVertexNum());
    range = 0.0;
    random = new Random(System.currentTimeMillis());
    logger.addHandler(new StdOutHandler());
    logger.setUseParentHandlers(false);
    logger.setLevel(Level.INFO);
  }

  /**
   * setVertexCoordination determine vertexes' coordination by FruchtermanReingold method.
   * vertexes' coordination is in 1 x 1 square.
   */
  public void setVertexCoordination() {
    initializeCoordination();

    if (vertexNum < 2) {
      return;
    }

    for (int i = 0; i < vertexNum * vertexNum + linkNum; i++) {
      try {
        fruchtermanReingold();
        range -= range / (i + 1.0);
      } catch (DoNotExecution e) {
        logger.severe(e.toString());
        System.exit(1);
      }
    }
  }

  private void initializeCoordination() {
    for (int i = 0; i < vertexNum; i++) {
      boolean isSame = true;

      while (isSame) {
        Coordination c = new Coordination(random.nextDouble(), random.nextDouble());
        isSame = false;

        for (int j = 0; j < i; j++) {
          if (vertexes[j].equalCoordination(c)) {
            isSame = true;
            break;
          }
        }

        if (!isSame) {
          vertexes[i].setCoordination(c);
        }
      }
    }

    initializeTemperature();
  }

  private void initializeTemperature() {
    double maxX = 0.0;
    double minX = Double.MAX_VALUE;
    double maxY = 0.0;
    double minY = Double.MAX_VALUE;

    for (Vertex vertex : vertexes) {
      double x = vertex.getCoordination().getCoordinateX();
      double y = vertex.getCoordination().getCoordinateY();

      if (maxX < x) {
        maxX = x;
      }
      if (minX > x) {
        minX = x;
      }

      if (maxY < y) {
        maxY = y;
      }
      if (minY > y) {
        minY = y;
      }
    }

    range = Math.max(maxX - minX, maxY - minY) * 0.1;
  }

  private void fruchtermanReingold() throws DoNotExecution {
    Coordination[] forceVectors = createForceVectorByRepulsive();
    forceVectors = updateForceVectorByAttraction(forceVectors);

    for (int i = 0; i < vertexNum; i++) {
      try {
        double forceVecNorm = forceVectors[i].distanceFromOrigin();
        double x =
            forceVectors[i].getCoordinateX() / forceVecNorm * Math.min(forceVecNorm, range);
        double y =
            forceVectors[i].getCoordinateY() / forceVecNorm * Math.min(forceVecNorm, range);

        Coordination newC = new Coordination(
            Math.min(1.0, Math.max(0, vertexes[i].getCoordination().getCoordinateX() + x)),
            Math.min(1.0, Math.max(0, vertexes[i].getCoordination().getCoordinateY() + y)));
        vertexes[i].setCoordination(newC);
      } catch (InvalidArguments e) {
        throw new DoNotExecution(e.toString());
      }
    }
  }

  private Coordination @NotNull [] createForceVectorByRepulsive() throws DoNotExecution {
    Coordination[] forceVectors = new Coordination[vertexNum];

    for (int i = 0; i < vertexNum; i++) {
      forceVectors[i] = new Coordination(0.0, 0.0);
      Coordination coordinationI = vertexes[i].getCoordination();

      for (int j = 0; j < vertexNum; j++) {
        if (i == j) {
          continue;
        }

        Coordination coordinationJ = vertexes[j].getCoordination();
        Coordination delta = new Coordination(
            coordinationI.getCoordinateX() - coordinationJ.getCoordinateX(),
            coordinationI.getCoordinateY() - coordinationJ.getCoordinateY());

        try {
          double deltaNorm = delta.distanceFromOrigin();
          forceVectors[i].add(
              new Coordination(delta.getCoordinateX() / deltaNorm * repulsion(deltaNorm),
                  delta.getCoordinateY() / deltaNorm * repulsion(deltaNorm)));
        } catch (InvalidArguments e) {
          throw new DoNotExecution(e.toString());
        }
      }
    }

    return forceVectors;
  }

  private Coordination[] updateForceVectorByAttraction(final Coordination[] forceVectors)
      throws DoNotExecution {
    if (forceVectors == null) {
      return null;
    }

    for (int e = 0; e < linkNum; e++) {
      int tailVertexIndex = tails[e];
      int headVertexIndex = heads[e];
      Coordination tailCoordination = vertexes[tailVertexIndex].getCoordination();
      Coordination headCoordination = vertexes[headVertexIndex].getCoordination();
      Coordination delta = new Coordination(
          tailCoordination.getCoordinateX() - headCoordination.getCoordinateX(),
          tailCoordination.getCoordinateY() - headCoordination.getCoordinateY());

      try {
        double deltaNorm = delta.distanceFromOrigin();
        forceVectors[tailVertexIndex].sub(new Coordination(
            delta.getCoordinateX() / deltaNorm * attraction(deltaNorm),
            delta.getCoordinateY() / deltaNorm * attraction(deltaNorm)));
        forceVectors[headVertexIndex].add(new Coordination(
            delta.getCoordinateX() / deltaNorm * attraction(deltaNorm),
            delta.getCoordinateY() / deltaNorm * attraction(deltaNorm)));
      } catch (InvalidArguments ia) {
        throw new DoNotExecution(ia.toString());
      }
    }

    return forceVectors;
  }

  private double attraction(final double distance) {
    return distance * distance / constant;
  }

  private double repulsion(final double distance) {
    return constant * constant / distance;
  }

  Vertex[] getVertexes() {
    return vertexes;
  }

  Link[] getLinks() {
    return links;
  }

  String getLabel() {
    return label;
  }
}
