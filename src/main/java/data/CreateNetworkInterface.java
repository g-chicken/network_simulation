package data;

import data.dto.NetworkDto;
import utils.exceptions.InvalidArguments;

/**
 * CreateNetworkInterface define to create a network.
 */
public interface CreateNetworkInterface {
  /**
   * undirectedRandom create a random network.
   * the network does not have self loop.
   * do not set capacity of all links.
   *
   * @param vertexNum #vertexes
   * @param linkNum #links
   * @return a random network
   */
  NetworkDto undirectedRandom(final int vertexNum, final int linkNum) throws InvalidArguments;
}
