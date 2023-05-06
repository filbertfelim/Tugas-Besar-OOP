import java.io.*;

import javax.sound.sampled.*;

public class MusicPlayer{

    public void plays(String namaFile, long lamaMusik, int pengulangan){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File("music/"+namaFile+".wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                    clip.loop(pengulangan);
                    Thread.sleep(lamaMusik);
                    clip.stop();
                } catch (FileNotFoundException e){
                    System.out.println("File musik tidak ditemukan");
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                } catch (LineUnavailableException e) {
                    // TODO Auto-generated catch block
                    System.out.println("File musik tidak dapat dimainkan");
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Terjadi kesalahan!");
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