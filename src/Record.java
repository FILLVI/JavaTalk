import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
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

    public static void main(String[] args) throws IOException {

//        SimpleFrame frame = new SimpleFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);

        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                44100, 16, MONO, 2, 44100, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        int numb = 15; //number of file
        String files = "G:\\test\\mix " + numb + "." + fileType;
        File fileOut = new File(files);

        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line need support!");
        }
        System.out.println("For stop record voice write " + 'g');
        System.out.println("For start record voice write " + 's');
        TargetDataLine mike = null;

        try {
            mike = (TargetDataLine) AudioSystem.getLine(info);
            mike.open(format);
            AudioInputStream sound = new AudioInputStream(mike);
//            mike.start();

            try (Scanner sc = new Scanner(System.in)) {
                String scc = sc.next();
                if (scc.equals("s")) {
                    System.out.println("Record start");
                    mike.start();
                } else {
                    String ssc = sc.next();
                    if (ssc.equals("g"))
                    System.out.println("Record stop");
                    mike.stop();
                }
//                    System.out.println("Record voice stop");
                AudioSystem.write(sound, fileType, fileOut);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("Stop");
            }
//            System.out.println("Started");
        } catch (LineUnavailableException e) {
            System.out.println("line not available");
        } finally {
            mike.stop();

//        String scc = sc.next();
//        if (scc.equals("g")) {
//            mike.stop();
//            System.out.println("Record voice stop");
//            scc = sc.equals();
//            if (scc.equals("close")) {
//                System.out.println("Record voice stop");
        }
    }
}


//        class SimpleFrame extends Record {
//
//            public static final int DEFAULT_WIDTH = 300;
//            public static final int DEFAULT_HEIGHT = 200;
//
//            public SimpleFrame() {
//                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT); // задаємо розміри фрейму
//                JPanel panel = new JPanel();            // створюємо панель
//                this.add(panel);                        // додаємо панель у фрейм
//
//                JButton button1 = new JButton("Start");   //створюємо кнопку 1
//                panel.add(button1);             // додаємо кнопку на панель
//                EventListener button1Action = new EventListener(1); // створюємо екземпляр обробника подій
//                button1.addActionListener(button1Action);           // зв'язуємо обробника подій з кнопкою 1
//
//                JButton button2 = new JButton("Stop");   //створюємо кнопку 2
//                panel.add(button2);             // додаємо кнопку на панель
//                EventListener button2Action = new EventListener(2); // створюємо екземпляр обробника подій
//                button2.addActionListener(button2Action);           // зв'язуємо обробника подій з кнопкою 2
//            }
//        }
//        TargetDataLine finalMike = mike;
//        class EventListener implements ActionListener {
//            private int buttonNumber;
//            public EventListener(int number){
//                buttonNumber = number;
//            }
//
//            @Override
//            public void actionPerformed(ActionEvent event){
//
//                if(buttonNumber == 1) {
//                    JOptionPane.showMessageDialog(new JFrame(), "You use button 1");
//                    finalMike.start();
//                }else if(buttonNumber == 2){
//                    JOptionPane.showMessageDialog(new JFrame(), "You use button 2");
//                    finalMike.stop();
//                }
//            }
//        }
//    }
//}