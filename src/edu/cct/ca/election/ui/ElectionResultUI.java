package edu.cct.ca.election.ui;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ElectionResultUI {
    private JPanel rootPanel;
    private JTable showTable;
    private JButton uploadFile;
    private JComboBox ConstituencyName;
    private JComboBox CandidateFirstName;
    private JComboBox Candidatesurname;
    public ElectionResultUI() {
//        createTable();
//        createConstituencyCombo();
        createCandidatesurname();
        uploadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String line = "";
                String[][] tableData = new String[10000][10000];
                if(e.getSource() == uploadFile) {
                    JFileChooser upload  = new JFileChooser();
                    int saveFile = upload.showSaveDialog(null);
                    if(saveFile == JFileChooser.APPROVE_OPTION) {
                        File filePath = new File(upload.getSelectedFile().getAbsolutePath());
//                        try {
//                            CSVReader reader = new CSVReader(new FileReader(filePath))
//                        } catch (FileNotFoundException fileNotFoundException) {
//                            fileNotFoundException.printStackTrace();
//                        }
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(filePath));
                            int i = 0;
                            while((line = reader.readLine()) != null){
//                                System.out.println(line);
                                String[]values = line.split(",");
//                                System.out.println(values);
//                                System.out.println("value");
                                tableData[i] = values;
                                i++;

                            }
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        createTable(tableData);
                        System.out.println(tableData);
                    }
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createTable(String[][] data) {
        System.out.println(data);
        showTable.setShowGrid(true);
        showTable.setShowHorizontalLines(true);
        showTable.setShowVerticalLines(true);
//        Object[][] data = {
//                {"Incep", 2008, 9.0, 1292994488}, {"startwars", 1987, 7.8,9768543},
//                {"The dark night", 2010, 0.8, 1223333}};
//        showTable.setModel(new DefaultTableModel(
//                data,
//                new String[]{"Title", "Year", "Ratihng", "Num Votes"}
//        ));
        showTable.setModel(new DefaultTableModel(
                Arrays.copyOfRange(data, 1, data.length),
                data[0]
        ));
//        Column model associated with this table
        TableColumnModel columns = showTable.getColumnModel();
        columns.getColumn(0).setMinWidth(200);

//        adjust centering
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(1).setCellRenderer(centerRenderer);
        columns.getColumn(2).setCellRenderer(centerRenderer);

    }

//    private String[] getConstituencyList(String[][] data) {
//        String[] newData = new String[1000];
//        for(int i=0; i<data.length; i++) {
//            if ( ArrayUtils.contains( fieldsToInclude, "id" ) ) {
//                // Do some stuff.
//            }
//        }
//        return data[0];
//    }

    private void createConstituencyCombo(String[] constituencyData) {
//        ConstituencyName.setModel(new DefaultComboBoxModel( new String[]{"Dublin", "Carlow"}));
        ConstituencyName.setModel(new DefaultComboBoxModel( constituencyData));

    }

    private void createCandidatesurname() {
        Candidatesurname.setModel(new DefaultComboBoxModel(new String[]{"Eve", "Eva"}));
    }

}
