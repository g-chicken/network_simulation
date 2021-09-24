package data;

import static org.junit.jupiter.api.Assertions.*;

import data.dto.LinkDto;
import data.dto.NetworkDto;
import data.dto.VertexDto;
import org.junit.jupiter.api.Test;
import utils.exceptions.DoNotExecution;
import utils.exceptions.InvalidArguments;
import utils.mathematics.Coordination;

class CreateCompleteNetworkTest {
  @Test
  void constructor() {
    {
      try {
        CreateCompleteNetwork got = new CreateCompleteNetwork(10);
        assertEquals(10, got.getVertexNum());
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
    }

    {
      try {
        CreateCompleteNetwork got = new CreateCompleteNetwork(1);
        assertEquals(1, got.getVertexNum());
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
    }

    {
      try {
        CreateCompleteNetwork got = new CreateCompleteNetwork(0);
        assertEquals(0, got.getVertexNum());
      } catch (InvalidArguments e) {
        assertEquals("", e.toString());
      }
    }

    {
      try {
        CreateCompleteNetwork got = new CreateCompleteNetwork(-1);
        assertEquals(-1, got.getVertexNum());
      } catch (InvalidArguments e) {
        assertEquals("utils.exceptions.InvalidArguments: Invalid Argument : size of #vertexes less than 0", e.toString());
      }
    }
  }

  @Test
  void createNetwork() {
    {
      try {
        VertexDto[] vertexDtoes = new VertexDto[]{};
        LinkDto[] linkDtoes = new LinkDto[]{};
        NetworkDto want = new NetworkDto(vertexDtoes, linkDtoes, "K_0");
        CreateCompleteNetwork network = new CreateCompleteNetwork(0);
        NetworkDto got = network.createNetwork();

        assertEquals(want.toString(), got.toString());
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
    }

    {
      try {
        VertexDto[] vertexDtoes = new VertexDto[]{new VertexDto(new Coordination(0.0, 0.0), "0")};
        LinkDto[] linkDtoes = new LinkDto[]{};
        NetworkDto want = new NetworkDto(vertexDtoes, linkDtoes, "K_1");
        CreateCompleteNetwork network = new CreateCompleteNetwork(1);
        NetworkDto got = network.createNetwork();

        assertEquals(want.getVertexNum(), got.getVertexNum());
        assertEquals(want.getLinkNum(), got.getLinkNum());

        for (int i = 0; i < want.getVertexNum(); i++) {
          assertEquals(want.vertexDtoes()[i].label(), got.vertexDtoes()[i].label());
        }

        for (int i = 0; i < want.getLinkNum(); i++) {
          assertEquals(want.linkDtoes()[i].tailVertexIndex(), got.linkDtoes()[i].tailVertexIndex());
          assertEquals(want.linkDtoes()[i].headVertexIndex(), got.linkDtoes()[i].headVertexIndex());
          assertEquals(want.linkDtoes()[i].weight(), got.linkDtoes()[i].weight());
          assertEquals(want.linkDtoes()[i].capacity(), got.linkDtoes()[i].capacity());
          assertEquals(want.linkDtoes()[i].label(), got.linkDtoes()[i].label());
        }
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
    }

    {
      try {
        VertexDto[] vertexDtoes = new VertexDto[]{
            new VertexDto(new Coordination(0.0, 0.0), "0"),
            new VertexDto(new Coordination(0.0, 0.0), "1")
        };
        LinkDto[] linkDtoes = new LinkDto[]{
            new LinkDto(1.0, -1.0, 1, 0, "0"),
            new LinkDto(1.0, -1.0, 0, 1, "1"),
        };
        NetworkDto want = new NetworkDto(vertexDtoes, linkDtoes, "K_2");
        CreateCompleteNetwork network = new CreateCompleteNetwork(2);
        NetworkDto got = network.createNetwork();

        assertEquals(want.getVertexNum(), got.getVertexNum());
        assertEquals(want.getLinkNum(), got.getLinkNum());

        for (int i = 0; i < want.getVertexNum(); i++) {
          assertEquals(want.vertexDtoes()[i].label(), got.vertexDtoes()[i].label());
        }

        for (int i = 0; i < want.getLinkNum(); i++) {
          assertEquals(want.linkDtoes()[i].tailVertexIndex(), got.linkDtoes()[i].tailVertexIndex());
          assertEquals(want.linkDtoes()[i].headVertexIndex(), got.linkDtoes()[i].headVertexIndex());
          assertEquals(want.linkDtoes()[i].capacity(), got.linkDtoes()[i].capacity());
          assertEquals(want.linkDtoes()[i].label(), got.linkDtoes()[i].label());
        }
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
    }

    {
      try {
        VertexDto[] vertexDtoes = new VertexDto[]{
            new VertexDto(new Coordination(0.0, 0.0), "0"),
            new VertexDto(new Coordination(0.0, 0.0), "1"),
            new VertexDto(new Coordination(0.0, 0.0), "2"),
            new VertexDto(new Coordination(0.0, 0.0), "3"),
            new VertexDto(new Coordination(0.0, 0.0), "4")
        };
        LinkDto[] linkDtoes = new LinkDto[]{
            new LinkDto(1.0, -1.0, 1, 0, "0"),
            new LinkDto(1.0, -1.0, 2, 0, "1"),
            new LinkDto(1.0, -1.0, 3, 0, "2"),
            new LinkDto(1.0, -1.0, 4, 0, "3"),
            new LinkDto(1.0, -1.0, 0, 1, "4"),
            new LinkDto(1.0, -1.0, 2, 1, "5"),
            new LinkDto(1.0, -1.0, 3, 1, "6"),
            new LinkDto(1.0, -1.0, 4, 1, "7"),
            new LinkDto(1.0, -1.0, 0, 2, "8"),
            new LinkDto(1.0, -1.0, 1, 2, "9"),
            new LinkDto(1.0, -1.0, 3, 2, "10"),
            new LinkDto(1.0, -1.0, 4, 2, "11"),
            new LinkDto(1.0, -1.0, 0, 3, "12"),
            new LinkDto(1.0, -1.0, 1, 3, "13"),
            new LinkDto(1.0, -1.0, 2, 3, "14"),
            new LinkDto(1.0, -1.0, 4, 3, "15"),
            new LinkDto(1.0, -1.0, 0, 4, "16"),
            new LinkDto(1.0, -1.0, 1, 4, "17"),
            new LinkDto(1.0, -1.0, 2, 4, "18"),
            new LinkDto(1.0, -1.0, 3, 4, "19"),
        };
        NetworkDto want = new NetworkDto(vertexDtoes, linkDtoes, "K_5");
        CreateCompleteNetwork network = new CreateCompleteNetwork(5);
        NetworkDto got = network.createNetwork();

        assertEquals(want.getVertexNum(), got.getVertexNum());
        assertEquals(want.getLinkNum(), got.getLinkNum());

        for (int i = 0; i < want.getVertexNum(); i++) {
          assertEquals(want.vertexDtoes()[i].label(), got.vertexDtoes()[i].label());
        }

        for (int i = 0; i < want.getLinkNum(); i++) {
          assertEquals(want.linkDtoes()[i].tailVertexIndex(), got.linkDtoes()[i].tailVertexIndex());
          assertEquals(want.linkDtoes()[i].headVertexIndex(), got.linkDtoes()[i].headVertexIndex());
          assertEquals(want.linkDtoes()[i].capacity(), got.linkDtoes()[i].capacity());
          assertEquals(want.linkDtoes()[i].label(), got.linkDtoes()[i].label());
        }
      } catch (InvalidArguments | DoNotExecution e) {
        assertEquals("", e.toString());
      }
    }
  }
}