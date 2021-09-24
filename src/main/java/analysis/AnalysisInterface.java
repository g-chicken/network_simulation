package analysis;

import analysis.model.Network;

/**
 * AnalysisInterface define to analyse the network.
 */
public interface AnalysisInterface {
  /**
   * calcDegreeCentrality calc the degree centrality for the network.
   */
  void calcDegreeCentrality();

  /**
   * calcVertexClosenessCentrality calc the closeness centrality for the vertexes.
   */
  void calcVertexClosenessCentrality();

  /**
   * calcLinkClosenessCentrality calc the closeness centrality for the links.
   */
  void calcLinkClosenessCentrality();

  /**
   * getVertexClosenessCentrality get closeness centrality for vertexes.
   *
   * @return array of double
   */
  double[] getVertexClosenessCentrality();

  /**
   * getLinkClosenessCentrality get closeness centrality for links.
   *
   * @return array of double
   */
  double[] getLinkClosenessCentrality();

  Network getNetwork();

  /**
   * getAnalysisResult get the result of analysis of network.
   *
   * @return result
   */
  String getAnalysisResult();
}
