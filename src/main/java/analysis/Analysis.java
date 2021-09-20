package analysis;

import analysis.model.Network;
import data.dto.NetworkDto;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.StdOutHandler;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;
import utils.network.Dijkstra;

/** Analysis analyse the network. this class implements AnalysisInterface. */
public class Analysis implements AnalysisInterface {
  private static final Logger logger = Logger.getLogger(Analysis.class.getName());
  private final Network network;
  private final double[] degreeCentrality;
  private final double[] vertexClosenessCentrality;
  // private final double[] linkClosenessCentrality;

  /**
   * Analysis is constructor.
   *
   * @param networkDto network model in data pkg
   */
  public Analysis(NetworkDto networkDto) {
    network = new Network(networkDto);

    degreeCentrality = new double[network.getVertexNum()];
    vertexClosenessCentrality = new double[network.getVertexNum()];
    // linkClosenessCentrality = new double[network.getLinkNum()];

    logger.addHandler(new StdOutHandler());
    logger.setUseParentHandlers(false);
    logger.setLevel(Level.INFO);
  }

  @Override
  public void calcDegreeCentrality() {
    for (int i = 0; i < network.getVertexNum(); i++) {
      try {
        degreeCentrality[i] = (double) network.getDegree(i) / (network.getVertexNum() - 1);
      } catch (InvalidArguments e) {
        logger.warning(e.toString());
      }
    }

    logger.info("calculated degree centrality");
  }

  @Override
  public void calcClosenessCentrality() {
    Dijkstra dijkstra = new Dijkstra(network);
    int[] totalPaths = new int[network.getVertexNum()];

    for (int origin = 0; origin < network.getVertexNum(); origin++) {
      for (int destination = 0; destination < network.getVertexNum(); destination++) {
        if (origin == destination) {
          continue;
        }

        boolean connected;

        try {
          connected = network.isConnected(origin, destination);
        } catch (InvalidArguments e) {
          logger.warning(e.toString());

          continue;
        }

        if (connected) {
          for (int v = 0; v < network.getVertexNum(); v++) {
            if (v != origin && v != destination) {
              totalPaths[v]++;
            }
          }
        }
      }
    }

    calcVertexClosenessCentrality(dijkstra);

    for (int v = 0; v < network.getVertexNum(); v++) {
      vertexClosenessCentrality[v] /= totalPaths[v];
    }

    logger.info("calculated closeness centrality");
  }

  private void calcVertexClosenessCentrality(final Dijkstra dijkstra) {
    Arrays.fill(vertexClosenessCentrality, 0.0);

    for (int originVertexIndex = 0;
        originVertexIndex < network.getVertexNum();
        originVertexIndex++) {
      try {
        dijkstra.dijkstra(originVertexIndex);
      } catch (InvalidArguments e) {
        logger.warning(e.toString());

        continue;
      } catch (DoNotExecution e) {
        logger.severe(e.toString());

        return;
      }

      for (int destinationVertexIndex = 0;
          destinationVertexIndex < network.getVertexNum();
          destinationVertexIndex++) {
        LinkedList<LinkedList<Integer>> pathLists;

        try {
          pathLists = dijkstra.getPathList(destinationVertexIndex);
        } catch (InvalidArguments e) {
          logger.warning(e.toString());

          continue;
        }

        if (pathLists.size() == 0) {
          continue;
        }

        int[] shortestPathNumThroughV = new int[network.getVertexNum()];
        int shortestPathNum = pathLists.size();

        for (LinkedList<Integer> list : pathLists) {
          while (list.size() > 0) {
            int e = list.pollFirst();
            int v = network.getHeads()[e];

            if (v != originVertexIndex && v != destinationVertexIndex) {
              shortestPathNumThroughV[v]++;
            }
          }
        }

        for (int v = 0; v < network.getVertexNum(); v++) {
          vertexClosenessCentrality[v] += shortestPathNumThroughV[v] / (double) shortestPathNum;
        }
      }
    }
  }

  double[] getDegreeCentrality() {
    return degreeCentrality;
  }

  double[] getVertexClosenessCentrality() {
    return vertexClosenessCentrality;
  }

  @Override
  public String getAnalysisResult() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("network\n");
    stringBuilder.append(
        String.format(
            "  #vertexes = %d, #links = %s\n", network.getVertexNum(), network.getLinkNum()));

    stringBuilder.append("degree centrality\n");
    for (int i = 0; i < degreeCentrality.length; i++) {
      stringBuilder.append(String.format("   [%3d] = %11.10f\n", i, degreeCentrality[i]));
    }

    stringBuilder.append("vertex closeness centrality\n");
    for (int i = 0; i < vertexClosenessCentrality.length; i++) {
      stringBuilder.append(String.format("   [%3d] = %11.10f\n", i, vertexClosenessCentrality[i]));
    }

    // stringBuilder.append("link closeness centrality\n");
    // for (int i = 0; i < network.getLinkNum(); i++) {
    //  stringBuilder.append(
    //      String.format(
    //          "   %3d - %3d = %11.10f\n",
    //          network.getTails()[i], network.getHeads()[i], linkClosenessCentrality[i]));
    // }

    return stringBuilder.toString();
  }
}
