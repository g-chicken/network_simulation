package data;

import data.dto.LinkDto;
import data.dto.NetworkDto;
import data.dto.VertexDto;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;
import utils.StdOutHandler;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;
import utils.mathematics.Coordination;

/**
 * CreateWattsStrogatzNetwork implements  CreateNetworkInterface.
 * this class create WattsStrogatz network.
 */
public class CreateWattsStrogatzNetwork implements CreateNetworkInterface {
  private static final Logger logger = Logger.getLogger(CreateWattsStrogatzNetwork.class.getName());
  private final int vertexNum;
  private final int degree;
  private final double beta;
  private final Random random;

  /**
   * constructor.
   *
   * @param vertexNum int
   * @param degree    int (degree < vertex and degree is even and degree > log_2(vertexNum))
   * @param beta      double (0 < beta < 1)
   * @throws InvalidArguments invalid argument exception
   */
  public CreateWattsStrogatzNetwork(final int vertexNum, final int degree, final double beta) throws
      InvalidArguments {
    if (vertexNum < 0) {
      throw new InvalidArguments("size of #vertexes less than 0");
    }

    if (degree >= vertexNum) {
      throw new InvalidArguments("degree is more than #vertexes");
    }

    if (degree <= Math.log(vertexNum) / Math.log(2)) {
      throw new InvalidArguments("degree is less than log_2(#vertexes)");
    }

    if (degree % 2 == 1) {
      throw new InvalidArguments("degree is odd");
    }

    if (beta < 0) {
      throw new InvalidArguments("beta is less than 0");
    }

    if (beta > 1) {
      throw new InvalidArguments("beta is more than 1");
    }

    this.vertexNum = vertexNum;
    this.degree = degree;
    this.beta = beta;
    random = new Random(System.currentTimeMillis());
    logger.addHandler(new StdOutHandler());
    logger.setUseParentHandlers(false);
    logger.setLevel(Level.INFO);
  }

  @Override
  public NetworkDto createNetwork() throws DoNotExecution {
    VertexDto[] vertexes = new VertexDto[vertexNum];
    for (int i = 0; i < vertexNum; i++) {
      vertexes[i] = new VertexDto(new Coordination(random.nextDouble(), random.nextDouble()),
          String.valueOf(i));
    }

    LinkDto[] links = initializeLinks();
    replace(links);

    return new NetworkDto(vertexes, links, "watts_strogatz");
  }

  private LinkDto @NotNull [] initializeLinks() {
    int mod = vertexNum - 1 - degree / 2;
    int linkNum = 0;
    LinkDto[] links = new LinkDto[vertexNum * degree];

    for (int i = 0; i < vertexNum; i++) {
      for (int j = 0; j < vertexNum; j++) {
        int sub = Math.abs(i - j);
        if (sub % mod > 0 && sub % mod <= degree / 2) {
          links[linkNum] = new LinkDto(random.nextDouble(), -1.0, j, i, String.valueOf(linkNum++));
        }
      }
    }

    return links;
  }

  private void replace(final LinkDto[] links) throws DoNotExecution {
    for (int i = 0; i < vertexNum; i++) {
      for (int j = i + 1; j < i + degree / 2; j++) {
        if (random.nextDouble() >= beta) {
          continue;
        }

        int modJ = j % vertexNum;

        int tailLinkIndexModJ = -1;
        int headLinkIndexModJ = -1;
        for (int e = 0; e < links.length; e++) {
          if (links[e].tailVertexIndex() == i && links[e].headVertexIndex() == modJ) {
            headLinkIndexModJ = e;
          }

          if (links[e].tailVertexIndex() == modJ && links[e].headVertexIndex() == i) {
            tailLinkIndexModJ = e;
          }
        }

        if (tailLinkIndexModJ < 0 || headLinkIndexModJ < 0) {
          throw new DoNotExecution(String.format("not found link index (%d <--> %d)", i, modJ));
        }

        int k = -1;
        boolean badLink = true;
        while (badLink) {
          k = random.nextInt(vertexNum);
          badLink = k == i || k == modJ;

          if (!badLink) {
            for (LinkDto link : links) {
              if (link.tailVertexIndex() == i && link.headVertexIndex() == k) {
                badLink = true;
                break;
              }

              if (link.tailVertexIndex() == k && link.headVertexIndex() == i) {
                badLink = true;
                break;
              }
            }
          }
        }

        links[tailLinkIndexModJ] = new LinkDto(
            links[tailLinkIndexModJ].weight(),
            links[tailLinkIndexModJ].capacity(),
            links[tailLinkIndexModJ].headVertexIndex(),
            k,
            links[tailLinkIndexModJ].label());
        links[headLinkIndexModJ] = new LinkDto(
            links[headLinkIndexModJ].weight(),
            links[headLinkIndexModJ].capacity(),
            k,
            links[headLinkIndexModJ].tailVertexIndex(),
            links[headLinkIndexModJ].label());
      }
    }
  }

  int getVertexNum() {
    return vertexNum;
  }

  int getDegree() {
    return degree;
  }

  double getBeta() {
    return beta;
  }
}
