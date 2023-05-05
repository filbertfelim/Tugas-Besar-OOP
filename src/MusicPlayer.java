import java.io.*;

import javax.sound.sampled.*;

public class MusicPlayer{

    public void plays(String namaFile, long lamaMusik){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File("music/"+namaFile+".wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);

                    clip.start();
                    Thread.sleep(lamaMusik);
                    clip.stop();
                } catch (UnsupportedAudioFileException e) {
                } catch (LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}