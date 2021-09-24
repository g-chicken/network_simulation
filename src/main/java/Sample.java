import analysis.Analysis;
import analysis.AnalysisInterface;
import data.CreateCompleteNetwork;
import data.CreateNetworkInterface;
import data.CreatePathNetwork;
import data.CreateRandomNetwork;
import data.CreateWattsStrogatzNetwork;
import data.dto.NetworkDto;
import drawing.Drawing;
import drawing.DrawingInterface;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;

/**
 * Sample class is sample.
 */
public class Sample {
  /**
   * main method.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    CreateNetworkInterface createNetworkInterface;
    NetworkDto dto;

    try {
      //createNetworkInterface = new CreateRandomNetwork(20, 100, true);
      //createNetworkInterface = new CreateCompleteNetwork(8);
      //createNetworkInterface = new CreatePathNetwork(3);
      createNetworkInterface = new CreateWattsStrogatzNetwork(12, 4, 0.2);
      dto = createNetworkInterface.createNetwork();
    } catch (InvalidArguments | DoNotExecution e) {
      e.printStackTrace();

      return;
    }

    AnalysisInterface analysis = new Analysis(dto);
    analysis.calcDegreeCentrality();
    analysis.calcVertexClosenessCentrality();
    analysis.calcLinkClosenessCentrality();

    DrawingInterface drawing = new Drawing(analysis);
    drawing.setVertexCoordination();
    drawing.showNetwork();
  }
}
