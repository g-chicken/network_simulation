package analysis;

/** AnalysisInterface define to analyse the network. */
public interface AnalysisInterface {
  /** calcDegreeCentrality calc the degree centrality for the network. */
  void calcDegreeCentrality();

  /** calcClosenessCentrality calc the closeness centrality for the network. */
  void calcClosenessCentrality();

  /**
   * getAnalysisResult get the result of analysis of network.
   *
   * @return result
   */
  String getAnalysisResult();
}
