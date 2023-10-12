import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class MyFrame extends JFrame implements ActionListener {
    String[] daysArray = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};
    JTextArea taskInput = new JTextArea();
    JScrollPane scrollBar = new JScrollPane(taskInput);
    JLabel textForTask = new JLabel();
    JLabel textForTime = new JLabel();
    JLabel textForDay = new JLabel();
    JComboBox<String> timeStart = new JComboBox<>();
    JComboBox<String> timeEnd = new JComboBox<>();
    JComboBox<String> dayChoice = new JComboBox<>(daysArray);
    JButton excel_create = new JButton("Excel erstellen");
    JButton spalte_beschreiben = new JButton("Spalte beschreiben");
    List<List<Object>> array_whole_data = new ArrayList<>();
    public Object[] columnNames = {"Montag", "Uhrzeit",
            "Dienstag", "Uhrzeit",
            "Mittwoch", "Uhrzeit",
            "Donnerstag", "Uhrzeit",
            "Freitag", "Uhrzeit"
    };

    private JTable jTable;
    private DefaultTableModel tableModel;

    MyFrame() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        this.setTitle("Tagesberichte");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 3 - this.getSize().width, dim.height / 4 - this.getSize().height);

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
        timeEnd.setBounds(350, 100, 100, 20);

        dayChoice.setEditable(false);
        dayChoice.setBounds(350, 160, 100, 20);

        spalte_beschreiben.setBounds(300, 300, 150, 40);
        spalte_beschreiben.addActionListener(this);

        excel_create.setBounds(300, 370, 150, 40);
        excel_create.addActionListener(this);

        for (int hour = 7; hour <= 18; hour++) {
            for (int minute = 0; minute < 60; minute += 5) {
                if (hour == 18 && minute > 0) {
                    break; // Stop at 18:00
                }
                Date time = new Date(0, 0, 0, hour, minute);
                timeStart.addItem(sdf.format(time));
                timeEnd.addItem(sdf.format(time));
            }
        }
        timeStart.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (timeStart.getSelectedIndex() > timeEnd.getSelectedIndex()) {
                        timeEnd.setSelectedIndex(timeStart.getSelectedIndex() + 1);
                    }
                }
            }
        });

        // Initialize the JTable and its DefaultTableModel
        tableModel = new DefaultTableModel(columnNames, 0);
        jTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(10, 200, 480, 200);

        this.add(spalte_beschreiben);
        this.add(dayChoice);
        this.add(timeStart);
        this.add(timeEnd);
        this.add(excel_create);
        this.add(textForTime);
        this.add(textForTask);
        this.add(textForDay);
        this.add(scrollBar);
        this.add(scrollPane);

        this.setSize(500, 500);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == spalte_beschreiben) {
            ArrayList<String> array_data_input = new ArrayList<>();
            array_data_input.add(taskInput.getText());
            array_data_input.add(timeStart.getSelectedItem() + "-" + timeEnd.getSelectedItem());
            array_whole_data.add(Collections.singletonList(array_data_input));

            // Add the data to the JTable
            tableModel.addRow(array_data_input.toArray());

            for (int i = timeEnd.getSelectedIndex(); i >= 0; i--) {
                timeStart.removeItemAt(i);
                timeEnd.removeItemAt(i);
            }
        }

        if (e.getSource() == excel_create) {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("SampleSheet_Arraylist");

                // Populate the sheet with data from the JTable
                for (int row = 0; row < jTable.getRowCount(); row++) {
                    Row excelRow = sheet.createRow(row);
                    for (int col = 0; col < jTable.getColumnCount(); col++) {
                        Object cellValue = jTable.getValueAt(row, col);
                        Cell cell = excelRow.createCell(col);
                        cell.setCellValue(cellValue.toString());
                    }
                }

                try (FileOutputStream fos = new FileOutputStream("SampleExcel_With_Data.xlsx")) {
                    workbook.write(fos);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            System.exit(0);
        }
    }

}
