package data;

import data.dto.LinkDto;
import data.dto.NetworkDto;
import data.dto.VertexDto;
import java.util.Random;
import java.util.logging.Logger;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;
import utils.mathematics.Coordination;

/**
 * CreateCompleteNetwork implements  CreateNetworkInterface.
 * this class create complete graph.
 */
public class CreateCompleteNetwork implements CreateNetworkInterface {
  private static final Logger logger = Logger.getLogger(CreateCompleteNetwork.class.getName());
  private final int vertexNum;
  private final Random random;

  /**
   * constructor.
   *
   * @param vertexNum int
   * @throws InvalidArguments invalid argument exception
   */
  public CreateCompleteNetwork(final int vertexNum) throws InvalidArguments {
    if (vertexNum < 0) {
      throw new InvalidArguments("size of #vertexes less than 0");
    }

    this.vertexNum = vertexNum;
    random = new Random(System.currentTimeMillis());
  }

  @Override
  public NetworkDto createNetwork() throws DoNotExecution {
    VertexDto[] vertexDtoes = new VertexDto[vertexNum];
    for (int i = 0; i < vertexNum; i++) {
      vertexDtoes[i] = new VertexDto(new Coordination(random.nextDouble(), random.nextDouble()),
          String.valueOf(i));
    }

    if (vertexNum < 2) {
      return new NetworkDto(vertexDtoes, new LinkDto[] {}, String.format("K_%d", vertexNum));
    }

    LinkDto[] linkDtoes = new LinkDto[vertexNum * (vertexNum - 1)];
    int linkIndex = 0;
    for (int i = 0; i < vertexNum; i++) {
      for (int j = 0; j < vertexNum; j++) {
        if (i == j) {
          continue;
        }

        try {
          linkDtoes[linkIndex] =
              new LinkDto(vertexDtoes[i].distance(vertexDtoes[j]), -1.0, j, i,
                  String.valueOf(linkIndex++));
        } catch (InvalidArguments e) {
          throw new DoNotExecution(e.toString());
        }
      }
    }

    logger.info(String.format("created network (#vertexes = %d, #links = %s)",
        vertexNum, linkDtoes.length));
    return new NetworkDto(vertexDtoes, linkDtoes, String.format("K_%d", vertexNum));
  }

  int getVertexNum() {
    return vertexNum;
  }
}
