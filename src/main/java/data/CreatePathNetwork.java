package data;

import data.dto.LinkDto;
import data.dto.NetworkDto;
import data.dto.VertexDto;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.StdOutHandler;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;
import utils.mathematics.Coordination;

/**
 * CreatePathNetwork implements  CreateNetworkInterface.
 * this class create path.
 */
public class CreatePathNetwork implements CreateNetworkInterface {
  private static final Logger logger = Logger.getLogger(CreatePathNetwork.class.getName());
  private final int vertexNum;
  private final Random random;

  /**
   * constructor.
   *
   * @param vertexNum int
   * @throws InvalidArguments invalid argument exception
   */
  public CreatePathNetwork(final int vertexNum) throws InvalidArguments {
    if (vertexNum < 0) {
      throw new InvalidArguments("size of #vertexes less than 0");
    }

    this.vertexNum = vertexNum;
    random = new Random(System.currentTimeMillis());
    logger.addHandler(new StdOutHandler());
    logger.setUseParentHandlers(false);
    logger.setLevel(Level.INFO);
  }

  @Override
  public NetworkDto createNetwork() throws DoNotExecution {
    VertexDto[] vertexDtoes = new VertexDto[vertexNum];
    for (int i = 0; i < vertexNum; i++) {
      vertexDtoes[i] = new VertexDto(new Coordination(random.nextDouble(), random.nextDouble()),
          String.valueOf(i));
    }

    if (vertexNum < 2) {
      return new NetworkDto(vertexDtoes, new LinkDto[] {}, String.format("path_%d", vertexNum));
    }

    LinkDto[] linkDtoes = new LinkDto[(vertexNum - 1) * 2];
    int linkIndex = 0;
    for (int i = 0; i < vertexNum - 1; i++) {
      try {
        linkDtoes[linkIndex] =
            new LinkDto(vertexDtoes[i].distance(vertexDtoes[i + 1]), -1.0, i + 1, i,
                String.valueOf(linkIndex++));
        linkDtoes[linkIndex] =
            new LinkDto(vertexDtoes[i].distance(vertexDtoes[i + 1]), -1.0, i, i + 1,
                String.valueOf(linkIndex++));
      } catch (InvalidArguments e) {
        throw new DoNotExecution(e.toString());
      }
    }

    logger.info(String.format("created network (#vertexes = %d, #links = %s)",
        vertexNum, linkDtoes.length));
    return new NetworkDto(vertexDtoes, linkDtoes, String.format("path_%d", vertexNum));
  }

  int getVertexNum() {
    return vertexNum;
  }
}
