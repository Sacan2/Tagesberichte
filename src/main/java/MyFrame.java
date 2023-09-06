import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyFrame extends JFrame implements ActionListener {
    public int rowsCount = 0;
    String[] daysArray = {"Montag","Dienstag","Mittwoch","Donnerstag","Freitag"};
    JTextArea taskInput = new JTextArea();
    JScrollPane scrollBar = new JScrollPane(taskInput);
    JLabel textForTask = new JLabel();
    JLabel textForTime = new JLabel();
    JLabel textForDay = new JLabel();
    JComboBox<String> timeStart = new JComboBox<String>();
    JComboBox<String> timeEnd = new JComboBox<String>();
    JComboBox<String> dayChoice = new JComboBox<String>(daysArray);
    JButton createButton = new JButton("Excel erstellen");
    JButton rowButton = new JButton("Spalte beschreiben");
    List<List<Object>> ls2d = new ArrayList<List<Object>>();
    List<Object> x = new ArrayList<Object>();
    MyFrame() {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        this.setTitle("Tagesberichte");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        scrollBar.setBounds(10, 40, 280, 100);
        taskInput.setLineWrap(true);

        textForTask.setText("Erledigte Tätigkeiten");
        textForTask.setBounds(35, 17, 200, 30);

        textForTime.setText("Benötigte Zeit");
        textForTime.setBounds(355, 17, 200, 30);

        textForDay.setText("Tag");
        textForDay.setBounds(355, 127, 200, 30);

        timeStart.setEditable(true);
        timeStart.setBounds(350, 50, 100, 20);

        timeEnd.setEditable(true);
        timeEnd.setBounds(350,100,100,20);

        dayChoice.setEditable(false);
        dayChoice.setBounds(350,160,100,20);


        rowButton.setBounds(300,300,150,40);
        rowButton.addActionListener(this);


        createButton.setBounds(300,370,150,40);

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
        this.add(rowButton);
        this.add(dayChoice);
        this.add(timeStart);
        this.add(timeEnd);
        this.add(createButton);
        this.add(textForTime);
        this.add(textForTask);
        this.add(textForDay);
        this.add(scrollBar);
        this.setSize(500, 500);
        this.setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==rowButton){

            rowsCount++;
            System.out.println(rowsCount);

            x.add(taskInput.getText());
            x.add(timeStart.getSelectedItem()+"-"+timeEnd.getSelectedItem());
            ls2d.add(x);

            System.out.println(Arrays.deepToString(ls2d.toArray()));



            //Object[][] taskData ={{taskInput.getText(),timeStart.getSelectedItem()+"-"+timeEnd.getSelectedItem()}};




    }
}
}

