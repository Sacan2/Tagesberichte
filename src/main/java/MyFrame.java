import javax.swing.*;
import java.util.Objects;

public class MyFrame extends JFrame {
    JTextArea taskInput = new JTextArea();
    JScrollPane scrollBar = new JScrollPane(taskInput);
    JLabel textForTask = new JLabel();
    JLabel textForTime = new JLabel();
    JComboBox<String> timeDropDown = new JComboBox<String>();

    MyFrame() {
        this.setTitle("Tagesberichte");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        scrollBar.setBounds(10, 40, 280, 180);
        taskInput.setLineWrap(true);

        textForTask.setText("Erledigte Tätigkeiten");
        textForTask.setBounds(35, 17, 200, 30);

        textForTime.setText("Benötigte Zeit");
        textForTime.setBounds(355, 17, 200, 30);

        timeDropDown.setEditable(true);
        timeDropDown.setBounds(350,50,100,20);
        for (int i = 6,j=0; i < 18 && j < 60; i++,j +=5) {
            String iFormatierung = String.format("%02d", i);
            String jFormatierung = String.format("%02d", j);
            int counter = 6;
            String zero = "0";


                if (Objects.equals(iFormatierung, "06")){
                    timeDropDown.addItem(iFormatierung + ":"+jFormatierung);
                    i--;
                }

        }





        this.add(timeDropDown);
        this.add(textForTime);
        this.add(textForTask);
        this.add(scrollBar);
        this.setSize(500, 500);
        this.setVisible(true);
        }
    }

