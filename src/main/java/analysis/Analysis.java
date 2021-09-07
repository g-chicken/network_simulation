package analysis;

import analysis.model.Network;
import data.dto.NetworkDto;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.StdOutHandler;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;
import utils.network.Dijkstra;
import utils.network.DijkstraResult;

/** Analysis analyse the network. this class implements AnalysisInterface. */
public class Analysis implements AnalysisInterface {
  private static final Logger logger = Logger.getLogger(Analysis.class.getName());
  private final Network network;
  private final double[] degreeCentrality;
  private final double[] closenessCentrality;

  /**
   * Analysis is constructor.
   *
   * @param networkDto network model in data pkg
   */
  public Analysis(NetworkDto networkDto) {
    network = new Network(networkDto);
    network.setAdjacencyList();

    degreeCentrality = new double[network.getVertexNum()];
    closenessCentrality = new double[network.getVertexNum()];

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

    try {
      DijkstraResult result = dijkstra.dijkstra(0);
      System.out.println(result);
    } catch (InvalidArguments | DoNotExecution e) {
      logger.warning(e.toString());
    }

    // TODO

    logger.info("calculated closeness centrality");
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

    stringBuilder.append("closeness centrality\n");
    for (int i = 0; i < closenessCentrality.length; i++) {
      stringBuilder.append(String.format("   [%3d] = %11.10f\n", i, closenessCentrality[i]));
    }

    return stringBuilder.toString();
  }
}
