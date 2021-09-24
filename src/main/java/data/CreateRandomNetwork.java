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
 * CreateRandomNetwork implements  CreateNetworkInterface.
 * this class create random network.
 */
public class CreateRandomNetwork implements CreateNetworkInterface {
  private static final Logger logger = Logger.getLogger(CreateRandomNetwork.class.getName());
  private final int vertexNum;
  private final int linkNum;
  private final boolean undirected;
  private final Random random;

  /**
   * constructor.
   *
   * @param vertexNum  int
   * @param linkNum    int
   * @param undirected boolean
   * @throws InvalidArguments invalid argument exception
   */
  public CreateRandomNetwork(final int vertexNum, final int linkNum, final boolean undirected)
      throws InvalidArguments {
    if (vertexNum < 0) {
      throw new InvalidArguments("size of #vertexes less than 0");
    }

    if (linkNum < 0) {
      throw new InvalidArguments("size of #links less than 0");
    }

    if (undirected && linkNum % 2 != 0) {
      throw new InvalidArguments("in case of undirected, #links must be even number");
    }

    this.vertexNum = vertexNum;
    this.linkNum = linkNum;
    this.undirected = undirected;
    random = new Random(System.currentTimeMillis());
    logger.addHandler(new StdOutHandler());
    logger.setUseParentHandlers(false);
    logger.setLevel(Level.INFO);
  }

  @Override
  public NetworkDto createNetwork() throws DoNotExecution {
    VertexDto[] vertexDtoes = new VertexDto[vertexNum];

    for (int i = 0; i < vertexNum; i++) {
      Coordination c = new Coordination(random.nextDouble(), random.nextDouble());
      vertexDtoes[i] = new VertexDto(c, String.valueOf(i));
    }

    if (undirected) {
      LinkDto[] linkDtoes = createUndirectedLinks(vertexDtoes);

      logger.info(
          String.format("created network (#vertexes = %d, #links = %s)", vertexNum, linkNum));
      return new NetworkDto(vertexDtoes, linkDtoes, "random");
    }

    LinkDto[] linkDtoes = createDirectedLinks(vertexDtoes);
    logger.info(String.format("created network (#vertexes = %d, #links = %s)", vertexNum, linkNum));

    return new NetworkDto(vertexDtoes, linkDtoes, "");
  }

  private LinkDto @NotNull [] createUndirectedLinks(final VertexDto[] vertexDtoes) throws
      DoNotExecution {
    LinkDto[] linkDtoes = new LinkDto[linkNum];

    for (int i = 0; i < linkNum / 2; i++) {
      int t = 0;
      int h = 0;
      boolean alreadyExist = true;

      while (alreadyExist) {
        alreadyExist = false;
        t = random.nextInt(vertexNum);
        h = t;

        while (h == t) {
          h = random.nextInt(vertexNum);
        }

        for (int j = 0; j < i * 2; j++) {
          int tail = linkDtoes[j].tailVertexIndex();
          int head = linkDtoes[j].headVertexIndex();

          if (t == tail && h == head) {
            alreadyExist = true;
            break;
          }
        }
      }

      try {
        linkDtoes[i * 2] =
            new LinkDto(vertexDtoes[t].distance(vertexDtoes[h]), -1.0, h, t, String.valueOf(i * 2));
        linkDtoes[i * 2 + 1] =
            new LinkDto(
                vertexDtoes[t].distance(vertexDtoes[h]), -1.0, t, h, String.valueOf(i * 2 + 1));
      } catch (InvalidArguments e) {
        throw new DoNotExecution(e.toString());
      }
    }

    return linkDtoes;
  }

  private LinkDto @NotNull [] createDirectedLinks(final VertexDto[] vertexDtoes) throws
      DoNotExecution {
    LinkDto[] linkDtoes = new LinkDto[linkNum];

    for (int i = 0; i < linkNum; i++) {
      int t = 0;
      int h = 0;
      boolean alreadyExist = true;

      while (alreadyExist) {
        alreadyExist = false;
        t = random.nextInt(vertexNum);
        h = t;

        while (h == t) {
          h = random.nextInt(vertexNum);
        }

        for (int j = 0; j < i; j++) {
          int tail = linkDtoes[j].tailVertexIndex();
          int head = linkDtoes[j].headVertexIndex();

          if (t == tail && h == head) {
            alreadyExist = true;
            break;
          }
        }
      }

      try {
        linkDtoes[i] =
            new LinkDto(vertexDtoes[t].distance(vertexDtoes[h]), -1.0, h, t, String.valueOf(i));
      } catch (InvalidArguments e) {
        throw new DoNotExecution(e.toString());
      }
    }

    return linkDtoes;
  }

  int getVertexNum() {
    return vertexNum;
  }

  int getLinkNum() {
    return linkNum;
  }

  boolean getUndirected() {
    return undirected;
  }
}
