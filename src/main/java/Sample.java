import analysis.Analysis;
import analysis.AnalysisInterface;
import data.CreateNetwork;
import data.CreateNetworkInterface;
import data.dto.NetworkDto;
import drawing.Drawing;
import drawing.DrawingInterface;
import utils.exceptions.InvalidArguments;

public class Sample {
  public static void main(String[] args) {
    new Sample();
  }

  private Sample() {
    CreateNetworkInterface createNetworkInterface = new CreateNetwork();

    NetworkDto dto;
    try {
      dto = createNetworkInterface.undirectedRandom(20, 100);
    } catch (InvalidArguments e) {
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
