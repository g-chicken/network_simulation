import analysis.Analysis;
import analysis.AnalysisInterface;
import data.CreateNetwork;
import data.CreateNetworkInterface;
import data.dto.NetworkDto;
import utils.exceptions.InvalidArguments;

public class Sample {
  public static void main(String[] args) {
    new Sample();
  }

  private Sample() {
    CreateNetworkInterface createNetworkInterface = new CreateNetwork();

    NetworkDto dto;
    try {
      dto = createNetworkInterface.undirectedRandom(10, 16);
    } catch (InvalidArguments e) {
      e.printStackTrace();

      return;
    }

    System.out.println("dto");
    System.out.println(dto);

    System.out.println("\nanalysis");
    AnalysisInterface analysis = new Analysis(dto);
    analysis.calcDegreeCentrality();
    analysis.calcVertexClosenessCentrality();
    analysis.calcLinkClosenessCentrality();
    System.out.println(analysis.getAnalysisResult());
  }
}
