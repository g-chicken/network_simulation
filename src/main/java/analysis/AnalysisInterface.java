package analysis;

/** AnalysisInterface define to analyse the network. */
public interface AnalysisInterface {
  /** calcDegreeCentrality calc the degree centrality for the network. */
  void calcDegreeCentrality();

  /** calcVertexClosenessCentrality calc the closeness centrality for the vertexes. */
  void calcVertexClosenessCentrality();

  /** calcLinkClosenessCentrality calc the closeness centrality for the links. */
  void calcLinkClosenessCentrality();

  /**
   * getAnalysisResult get the result of analysis of network.
   *
   * @return result
   */
  String getAnalysisResult();
}
