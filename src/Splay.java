import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Splay {
    private static final int EXTERNAL_BUFFER_SIZE = 1048576;// 128Kb
    private static AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    public static void main(String[] args) {

        boolean playing = true;
        System.out.println("Start");
        String fileN = "G:\\test\\mix 15" + "." + fileType;
        File f = new File(fileN);

        AudioInputStream source = null;
        try {
            source = AudioSystem.getAudioInputStream(f);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioFormat format = source.getFormat();
        System.out.println(format);
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        auline.start();

        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

        try {

            while(playing){
                while (nBytesRead != -1) {
                    nBytesRead = source.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        auline.write(abData, 0, nBytesRead);
                    System.out.println("Фрейм " + auline.getFramePosition());
                    System.out.println("Громкость " + auline.getLevel());
                    System.out.println("Микро " + auline.getMicrosecondPosition());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }

    }
}