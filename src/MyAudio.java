import javax.sound.sampled.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyAudio {

    private static final Map<String, Clip> clips = new HashMap<>();

    static {
        loadClip("BALL", "/audio/ball.wav");
        loadClip("GAMEOVER", "/audio/gameover.wav");
        loadClip("BACK", "/audio/back.wav");
    }

    private static void loadClip(String name, String path) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    Objects.requireNonNull(Main.class.getResourceAsStream(path))
            );
            clip.open(audioInputStream);
            clips.put(name, clip);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            System.err.println("Could not load sound: " + path);
//            e.printStackTrace();
        }
    }

    public static void play(String name) {
        Clip clip = clips.get(name);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();  // Stop the clip before starting it again
            }
            clip.setFramePosition(0);  // Rewind to the beginning
            clip.start();
        }
    }

    public static void playLoop(String name) {
        Clip clip = clips.get(name);
        if (clip != null) {
            clip.setFramePosition(0);  // Rewind to the beginning
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }

    public static void stop(String name) {
        Clip clip = clips.get(name);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public static void stopAll() {
        for (Clip clip : clips.values()) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
    }
}
