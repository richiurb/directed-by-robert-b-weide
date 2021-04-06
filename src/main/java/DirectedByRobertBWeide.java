import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class DirectedByRobertBWeide {

    private final Image img;
    private final AudioInputStream stream;

    private DirectedByRobertBWeide() throws URISyntaxException, IOException, UnsupportedAudioFileException {
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
    }

    private void showMeme(Image img, Dimension dimension) {
        JFrame frame = new JFrame("Directed by Robert B. Weide");
        frame.setPreferredSize(dimension);
        frame.add(new ImagePanel(img));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void start() throws LineUnavailableException, IOException {
        playSound(Clip.LOOP_CONTINUOUSLY);
        showMeme(img, new Dimension(520, 293));
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException,
            URISyntaxException, LineUnavailableException {

        new DirectedByRobertBWeide().start();
    }
}