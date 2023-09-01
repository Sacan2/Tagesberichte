import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFrame extends JFrame {
    JTextArea taskInput = new JTextArea();
    JScrollPane scrollBar = new JScrollPane(taskInput);
    JLabel textForTask = new JLabel();
    JLabel textForTime = new JLabel();
    JComboBox<String> timeStart = new JComboBox<String>();
    JComboBox<String> timeEnd = new JComboBox<String>();

    MyFrame() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        this.setTitle("Tagesberichte");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        scrollBar.setBounds(10, 40, 280, 180);
        taskInput.setLineWrap(true);

        textForTask.setText("Erledigte Tätigkeiten");
        textForTask.setBounds(35, 17, 200, 30);

        textForTime.setText("Benötigte Zeit");
        textForTime.setBounds(355, 17, 200, 30);

        timeStart.setEditable(true);
        timeStart.setBounds(350, 50, 100, 20);

        timeEnd.setEditable(true);
        timeEnd.setBounds(350,100,100,20);

        for (int hour = 6; hour <= 18; hour++) {
            for (int minute = 0; minute < 60; minute += 5) {
                if (hour == 18 && minute > 0) {
                    break; // Stop at 18:00
                }
                Date time = new Date(0, 0, 0, hour, minute);
                timeStart.addItem(sdf.format(time));
                timeEnd.addItem(sdf.format(time));
            }

    }

        this.add(timeStart);
        this.add(timeEnd);
        this.add(textForTime);
        this.add(textForTask);
        this.add(scrollBar);
        this.setSize(500, 500);
        this.setVisible(true);
        }



    }

