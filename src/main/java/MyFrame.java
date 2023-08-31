import javax.swing.*;

public class MyFrame extends JFrame {
    JTextArea taskInput = new JTextArea();
    JScrollPane scrollBar = new JScrollPane(taskInput);
    JLabel textForTask = new JLabel();
    JLabel textForTime = new JLabel();


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



        this.add(textForTime);
        this.add(textForTask);
        this.add(scrollBar);
        this.setSize(500, 500);
        this.setVisible(true);
    }
}
