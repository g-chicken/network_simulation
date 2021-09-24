package drawing;

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
  private static final int PADDING = 10;
  private static final int VERTEX_RADIUS = 4;

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

      for (int i = 0; i < network.getLinkNum(); i++) {
        int tail = network.getTails()[i];
        int head = network.getHeads()[i];
        double rate = network.getLinks()[i].rate();
        int gb = (int) (127 * Math.max(0, 1 - rate * 8));
        int alpha = 30 + (int) (220 * rate);

        g.setColor(new Color(127, gb, gb, alpha));
        g.drawLine(
            (int) (
                network.getVertexes()[tail].getCoordination().getCoordinateX()
                    * (getWidth() - PADDING * 2) + PADDING),
            (int) (
                network.getVertexes()[tail].getCoordination().getCoordinateY()
                    * (getHeight() - PADDING * 2) + PADDING),
            (int) (
                network.getVertexes()[head].getCoordination().getCoordinateX()
                    * (getWidth() - PADDING * 2) + PADDING),
            (int) (
                network.getVertexes()[head].getCoordination().getCoordinateY()
                    * (getHeight() - PADDING * 2) + PADDING)
        );
      }

      for (Vertex vertex : network.getVertexes()) {
        g.setColor(Color.BLACK);
        g.drawOval(
            (int) (vertex.getCoordination().getCoordinateX()
                * (getWidth() - PADDING * 2)) - VERTEX_RADIUS + PADDING,
            (int) (vertex.getCoordination().getCoordinateY()
                * (getHeight() - PADDING * 2)) - VERTEX_RADIUS + PADDING,
            VERTEX_RADIUS * 2,
            VERTEX_RADIUS * 2
        );

        int rg = (int) (64 * Math.max(0, 1 - vertex.getRate() * 8));
        int alpha = 30 + (int) (220 * vertex.getRate());

        g.setColor(new Color(rg, rg, 172, alpha));
        g.fillOval(
            (int) (vertex.getCoordination().getCoordinateX()
                * (getWidth() - PADDING * 2)) - VERTEX_RADIUS + PADDING,
            (int) (vertex.getCoordination().getCoordinateY()
                * (getHeight() - PADDING * 2)) - VERTEX_RADIUS + PADDING,
            VERTEX_RADIUS * 2,
            VERTEX_RADIUS * 2
        );
      }
    }
  }
}
