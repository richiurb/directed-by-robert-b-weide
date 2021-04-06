import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private final Image image;

    public ImagePanel(Image image) {
        super();
        this.image = image;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}
