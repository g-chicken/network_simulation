package data;

import data.dto.NetworkDto;
import utils.exceptions.DoNotExecution;

/**
 * CreateNetworkInterface define to create a network.
 */
public interface CreateNetworkInterface {
  /**
   * createNetwork create the network.
   *
   * @return NetworkDto
   * @throws DoNotExecution do not execution exception
   */
  NetworkDto createNetwork() throws DoNotExecution;
}
