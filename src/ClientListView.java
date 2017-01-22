/*
 * Created by JFormDesigner on Sat Jan 07 20:14:24 EET 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

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
        initComponents();
    }

    public static ClientListView getInstance() {
        if (INSTANCE == null) INSTANCE = new ClientListView();
        INSTANCE.updateTable();
        return INSTANCE;
    }

    // add button action
    private void button1ActionPerformed(ActionEvent e) {
        AddClientFrame acf = new AddClientFrame(this);
        acf.setModal(true);
        acf.show();
    }

    // delete button action
    private void button2ActionPerformed(ActionEvent e) {
        int row = this.table1.getSelectedRow();
        if (row != -1) { // -1 means nothing is selected
            String clientID = this.table1.getValueAt(row, 0).toString();
            Client selected = ClientList.INSTANCE.getClient(clientID);
            ClientDeleteConfirmDialog cdcd = new ClientDeleteConfirmDialog(this, selected);
            cdcd.setModal(true);
            cdcd.show();
        }
    }

    // ok button action
    private void button3ActionPerformed(ActionEvent e) {
        this.dispose();
    }

    // edit button action
    private void buttonEditActionPerformed(ActionEvent e) {
        int row = this.table1.getSelectedRow();
        if (row != -1) { // -1 means nothing is selected
            String clientID = this.table1.getValueAt(row, 0).toString();
            Client selected = ClientList.INSTANCE.getClient(clientID);
            EditClientFrame ecf = new EditClientFrame(this, selected);
            ecf.setModal(true);
            ecf.show();
        }
    }

    private void thisWindowClosing(WindowEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - George Vlahavas
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        buttonAdd = new JButton();
        buttonDelete = new JButton();
        button3 = new JButton();
        label1 = new JLabel();
        buttonEdit = new JButton();

        //======== this ========
        setTitle("Client List");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(10, 40, 620, 345);

        //---- buttonAdd ----
        buttonAdd.setText("Add");
        buttonAdd.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(buttonAdd);
        buttonAdd.setBounds(640, 40, 90, buttonAdd.getPreferredSize().height);

        //---- buttonDelete ----
        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(buttonDelete);
        buttonDelete.setBounds(640, 110, 90, buttonDelete.getPreferredSize().height);

        //---- button3 ----
        button3.setText("Ok");
        button3.addActionListener(e -> button3ActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(640, 385, 90, button3.getPreferredSize().height);

        //---- label1 ----
        label1.setText("Client List");
        label1.setFont(new Font("Dialog", Font.BOLD, 14));
        contentPane.add(label1);
        label1.setBounds(10, 20, 117, 17);

        //---- buttonEdit ----
        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(e -> buttonEditActionPerformed(e));
        contentPane.add(buttonEdit);
        buttonEdit.setBounds(640, 75, 90, buttonEdit.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(750, 440));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        updateTable();

    }

    public void updateTable() {
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
                return false;
            }
        });
        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - George Vlahavas
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton buttonAdd;
    private JButton buttonDelete;
    private JButton button3;
    private JLabel label1;
    private JButton buttonEdit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables




}
