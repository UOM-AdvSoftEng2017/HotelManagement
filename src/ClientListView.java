/*
 * Created by JFormDesigner on Sat Jan 07 20:14:24 EET 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.StringJoiner;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

/**
 * @author Manos kakogian
 */
public final class ClientListView extends JFrame {



    // the ClientListView is a singleton, so that it's impossible to get multiple
    // windows of the same kind
    private static ClientListView INSTANCE = null;
    private int currentRow = -1;
    ArrayList<String> clintsDbIds ;

    private ClientListView() {

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closed");

                if (currentRow!=-1)
                saveThePreviewsCollumn();
                e.getWindow().dispose();

            }
        });


        initComponents();
    }

    public static ClientListView getInstance() {
        if (INSTANCE == null) INSTANCE = new ClientListView();
        return INSTANCE;
    }

    private void button1ActionPerformed(ActionEvent e) {
        Long tsLong = System.currentTimeMillis() / 1000;
        Client c = new Client(""+tsLong,"",0);
        int rv = DBManager.addClient(c);
        if (rv != 0)
            JOptionPane.showMessageDialog(null, "Unable to create new Client", "Error",
                    JOptionPane.ERROR_MESSAGE);
        loadTable();
    }

    private void button2ActionPerformed(ActionEvent e) {

        int selected = table1.getSelectedRow();

        if (selected == -1){
            JOptionPane.showMessageDialog(null, "Please first select one Client from table!", "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        else {
            String selctedClintId = clintsDbIds.get(selected);
            int rv = DBManager.deleteClient(selctedClintId);
            if (rv != 0)
                JOptionPane.showMessageDialog(null, "Unable to delete selected Client", "Error",
                        JOptionPane.ERROR_MESSAGE);
            else
                currentRow =-1;

        }
        loadTable();
    }

    private void button3ActionPerformed(ActionEvent e) {

        int selected = table1.getSelectedRow();
        if (selected!=-1) {
            String selctedClintId = clintsDbIds.get(selected);
            String selctedClintName = table1.getValueAt(selected, 1).toString();

            try {
                int selctedClintPhone = (table1.getValueAt(selected, 2).toString().isEmpty() ? 0 : Integer.parseInt(table1.getValueAt(selected, 2).toString()));
                Client c = new Client(selctedClintId, selctedClintName, selctedClintPhone);
                int rv = DBManager.updateClient(c);
                if (rv != 0)
                    JOptionPane.showMessageDialog(null, "Unable to update selected Client", "Error",
                            JOptionPane.ERROR_MESSAGE);

                dispose();
            }
            catch ( Exception e1){
                JOptionPane.showMessageDialog(null, "Phone number can be only integer!" , "Error",
                        JOptionPane.WARNING_MESSAGE);
                // table1.setValueAt("0",currentRow,2);
            }


        }
        else
            dispose();




    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Manos kakogian
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label1 = new JLabel();

        //======== this ========
        setTitle("Client List");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(10, 40, 490, 365);

        //---- button1 ----
        button1.setText("New");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(525, 40, 90, button1.getPreferredSize().height);

        //---- button2 ----
        button2.setText("Delete");
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(525, 75, 90, button2.getPreferredSize().height);

        //---- button3 ----
        button3.setText("Ok");
        button3.addActionListener(e -> button3ActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(525, 385, 90, button3.getPreferredSize().height);

        //---- label1 ----
        label1.setText("Client List");
        label1.setFont(new Font("Dialog", Font.BOLD, 14));
        contentPane.add(label1);
        label1.setBounds(10, 20, 117, 17);

        contentPane.setPreferredSize(new Dimension(635, 440));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        loadTable();

    }

    private void loadTable() {
        clintsDbIds = new ArrayList<>();
        ArrayList<Client> cl;
        cl = DBManager.getClientList();
        Object rowData[][] = new Object[cl.size()][3];
        Object columnNames[] = { "Client Id", "Name", "Phone"};
        int i=0;
        for (Client c: cl) {
            clintsDbIds .add(c.getId());
            rowData[i][0]=c.getId();
            rowData[i][1]=c.getName();
            rowData[i][2]=c.getPhone();
            i++;
        }
        table1 = new JTable(
        new javax.swing.table.DefaultTableModel(
                rowData,
                columnNames) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {

                if (currentRow!=-1){

                    if (currentRow!=rowIndex){
                        saveThePreviewsCollumn();
                        currentRow = rowIndex;
                    }

                }
                else
                    currentRow = rowIndex;

                if (columnIndex==0)
                    return false;
                else
                    return  true;
            }
        });
        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
    }

    private void saveThePreviewsCollumn() {
        String selctedClintId = clintsDbIds.get(currentRow);
        String selctedClintName = table1.getValueAt(currentRow, 1).toString();

        try {
            int  selctedClintPhone = (table1.getValueAt(currentRow, 2).toString().isEmpty() ? 0 : Integer.parseInt(table1.getValueAt(currentRow, 2).toString()));
            Client c = new Client( selctedClintId,selctedClintName,selctedClintPhone);
            int rv = DBManager.updateClient(c);
            if (rv != 0)
                JOptionPane.showMessageDialog(null, "Unable to update Client: "+selctedClintName , "Error",
                        JOptionPane.ERROR_MESSAGE);
        }
        catch ( Exception e){
            JOptionPane.showMessageDialog(null, "Phone number can be only integer!" , "Error",
                    JOptionPane.WARNING_MESSAGE);
           // table1.setValueAt("0",currentRow,2);
        }


    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Manos kakogian
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables




}
