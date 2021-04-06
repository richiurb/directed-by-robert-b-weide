import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class DirectedByRobertBWeide {

    private final Image img;
    private final AudioInputStream stream;
    private JFrame frame;

    public DirectedByRobertBWeide() throws URISyntaxException, IOException, UnsupportedAudioFileException {
        img = Toolkit.getDefaultToolkit().createImage(DirectedByRobertBWeide.class.getResource("out.gif").toURI().toURL());
        stream = AudioSystem.getAudioInputStream(Paths.get(DirectedByRobertBWeide.class.getResource("file.wav").toURI()).toFile());
    }

    private void playSound(int loops) throws IOException, LineUnavailableException {
        Clip audioClip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, stream.getFormat()));
        audioClip.open(stream);
        FloatControl gainControl =
                (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
        audioClip.start();
        audioClip.loop(loops);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                audioClip.stop();
            }
        });
    }

    private void showMeme(Image img, Dimension dimension) {
        frame = new JFrame("Directed by Robert B. Weide");
        frame.setPreferredSize(dimension);
        frame.add(new ImagePanel(img));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void start() throws LineUnavailableException, IOException {
        showMeme(img, new Dimension(520, 293));
        playSound(Clip.LOOP_CONTINUOUSLY);
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException,
            URISyntaxException, LineUnavailableException {

        new DirectedByRobertBWeide().start();
    }
}