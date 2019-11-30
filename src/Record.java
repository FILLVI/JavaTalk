import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class Record {

    final static int MONO = 1;
    private static AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    public static void main(String[] args) {
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                44100, 16, MONO, 2, 44100, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        int numb = 1; //number of file
        String files = "G:\\test\\mix1" + numb + "." + fileType;
        File fileOut = new File(files);

        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line need support!");
        }
        System.out.println("For stop record voice write - g");
        TargetDataLine mike = null;
        try {
            mike = (TargetDataLine) AudioSystem.getLine(info);
            mike.open(format);
            AudioInputStream sound = new AudioInputStream(mike);
            mike.start();

            try {
                AudioSystem.write(sound, fileType, fileOut);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (LineUnavailableException e) {
            System.out.println("line not available");
        }

        Scanner sc = new Scanner(System.in);
        String scc;
        if (sc.hasNext()) {
            System.out.println("Запись звука остановлена");
            scc = sc.nextLine();
            if (scc.equals("g")) {
                System.out.println("Запись звука остановлена");
                mike.stop();
            }
        }


    }
}