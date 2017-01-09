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
    private int columnValue = -1;
    private int columnNewValue = -1;
    ArrayList<String> clintsDbIds ;

    private ClientListView() {
        initComponents();
    }

    public static ClientListView getInstance() {
        if (INSTANCE == null) INSTANCE = new ClientListView();
        return INSTANCE;
    }

    private void button1ActionPerformed(ActionEvent e) {
        Long tsLong = System.currentTimeMillis() / 1000;
        Client c = new Client(""+tsLong,"",0);
        DBManager.addClient(c);
        loadTable();
    }

    private void button2ActionPerformed(ActionEvent e) {

        int selected = table1.getSelectedRow();
        String selctedClintId = clintsDbIds.get(selected);
        DBManager.deleteClient(selctedClintId);
        loadTable();
    }

    private void button3ActionPerformed(ActionEvent e) {

        int selected = table1.getSelectedRow();
        String selctedClintId = clintsDbIds.get(selected);
        String selctedClintName = table1.getValueAt(selected, 1).toString();
        int selctedClintPhone = (table1.getValueAt(selected, 2).toString().isEmpty()?0:Integer.parseInt(table1.getValueAt(selected, 2).toString()));
        Client c = new Client( selctedClintId,selctedClintName,selctedClintPhone);
        DBManager.updateClient(c);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Manos kakogian
        scrollPane1 = new JScrollPane();
        loadTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 40, 630, scrollPane1.getPreferredSize().height);

        //---- button1 ----
        button1.setText("New");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(455, 5), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("Delete");
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(535, 5), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("Save");
        button3.addActionListener(e -> button3ActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(375, 5), button3.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(630, 480));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents



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
                if (columnIndex==0)
                    return false;
                else
                    return  true;
            }
        });

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Manos kakogian
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
