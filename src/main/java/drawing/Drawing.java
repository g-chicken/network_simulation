package drawing;

import analysis.Analysis;
import analysis.AnalysisInterface;
import data.dto.NetworkDto;
import drawing.model.Network;
import drawing.model.Vertex;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import org.jetbrains.annotations.NotNull;

/**
 * Drawing implements DrawingInterface.
 */
public class Drawing extends JFrame implements DrawingInterface {
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;
  private final Network network;

  /**
   * constructor.
   *
   * @param networkDto network in dto pkg
   */
  public Drawing(final @NotNull NetworkDto networkDto) {
    super(networkDto.label());
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null);
    setResizable(true);

    this.network = new Network(networkDto);
  }

  /**
   * constructor.
   *
   * @param analysis AnalysisInterface
   */
  public Drawing(final @NotNull AnalysisInterface analysis) {
    super(analysis.getNetwork().getLabel());

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null);
    setResizable(true);

    this.network = new Network(analysis.getNetwork(), analysis.getVertexClosenessCentrality(),
        analysis.getLinkClosenessCentrality());
  }

  @Override
  public void setVertexCoordination() {
    network.setVertexCoordination();
  }

  @Override
  public void showNetwork() {
    DrawPanel dp = new DrawPanel();
    add(dp, BorderLayout.CENTER);

    setVisible(true);
  }

  private class DrawPanel extends JPanel {
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      g.setColor(Color.WHITE);
      g.fillRect(0, 0, getWidth(), getHeight());

      g.setColor(Color.GRAY);
      for (int i = 0; i < network.getLinkNum(); i++) {
        int tail = network.getTails()[i];
        int head = network.getHeads()[i];

        g.drawLine(
            (int) (
                network.getVertexes()[tail].getCoordination().getCoordinateX()
                    * (getWidth() - 40) + 20),
            (int) (
                network.getVertexes()[tail].getCoordination().getCoordinateY()
                    * (getHeight() - 40) + 20),
            (int) (
                network.getVertexes()[head].getCoordination().getCoordinateX()
                    * (getWidth() - 40) + 20),
            (int) (
                network.getVertexes()[head].getCoordination().getCoordinateY()
                    * (getHeight() - 40) + 20)
        );
      }

      for (Vertex vertex : network.getVertexes()) {
        g.setColor(Color.GRAY);
        g.fillOval(
            (int) (vertex.getCoordination().getCoordinateX() * (getWidth() - 40)) - 7 + 20,
            (int) (vertex.getCoordination().getCoordinateY() * (getHeight() - 40)) - 7 + 20,
            14,
            14
        );

        g.setColor(Color.BLACK);
        g.drawOval(
            (int) (vertex.getCoordination().getCoordinateX() * (getWidth() - 40)) - 7 + 20,
            (int) (vertex.getCoordination().getCoordinateY() * (getHeight() - 40)) - 7 + 20,
            14,
            14
        );
      }
    }
  }
}
