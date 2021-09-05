package data;

import data.dto.LinkDto;
import data.dto.NetworkDto;
import data.dto.VertexDto;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.StdOutHandler;
import utils.exceptions.InvalidArguments;

/** CreateNetwork implements CreateNetworkInterface. */
public class CreateNetwork implements CreateNetworkInterface {
  private static final Logger logger = Logger.getLogger(CreateNetwork.class.getName());
  private final Random random;

  /** CreateNetwork is constructor. */
  public CreateNetwork() {
    this.random = new Random(System.currentTimeMillis());

    logger.addHandler(new StdOutHandler());
    logger.setUseParentHandlers(false);
    logger.setLevel(Level.INFO);
  }

  @Override
  public NetworkDto undirectedRandom(final int vertexNum, final int linkNum)
      throws InvalidArguments {
    if (vertexNum <= 0 || linkNum <= 0) {
      throw new InvalidArguments("size of vertex or link is less than or equal to 0");
    }

    if (linkNum % 2 != 0) {
      throw new InvalidArguments("link size must be even number.");
    }

    VertexDto[] vertexDtoes = new VertexDto[vertexNum];

    for (int i = 0; i < vertexNum; i++) {
      double x = random.nextDouble();
      double y = random.nextDouble();

      vertexDtoes[i] = new VertexDto(x, y, String.valueOf(i));
    }

    LinkDto[] linkDtoes = new LinkDto[linkNum];

    for (int i = 0; i < linkNum / 2; i++) {
      int t = random.nextInt(vertexNum);
      int h = t;
      while (h == t) {
        h = random.nextInt(vertexNum);
      }

      linkDtoes[i * 2] =
          new LinkDto(vertexDtoes[t].distance(vertexDtoes[h]), -1.0, t, h, String.valueOf(i * 2));
      linkDtoes[i * 2 + 1] =
          new LinkDto(
              vertexDtoes[t].distance(vertexDtoes[h]), -1.0, h, t, String.valueOf(i * 2 + 1));
    }

    logger.info(String.format("created network (#vertexes = %d, #links = %s)", vertexNum, linkNum));

    return new NetworkDto(vertexDtoes, linkDtoes, "");
  }
}
