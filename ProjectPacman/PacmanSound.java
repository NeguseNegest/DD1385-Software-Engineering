package ProjectPacman;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PacmanSound {
    private String soundFileName;
    private Clip backgroundMusicClip;
    private Clip currentClip; // Field to keep track of the current sound clip

    public void playSound(String soundFileName) {
        try {
            // If a sound is currently playing, wait for it to finish
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.drain(); // Wait for the clip to finish playing
            }

            // Load and play the new sound
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new File(soundFileName).getAbsoluteFile());
            currentClip = AudioSystem.getClip();
            currentClip.open(audioInputStream);
            currentClip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void startBackgroundMusic() {
        try {
            if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
                backgroundMusicClip.stop(); // stanna om den spelas redan
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("ProjectPacman/assets/NewBackMusic.wav").getAbsoluteFile());
            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(audioInputStream);
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY); // loop continuously
            backgroundMusicClip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing background music.");
            ex.printStackTrace();
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicClip != null) {
            backgroundMusicClip.stop();
        }
    }
}
