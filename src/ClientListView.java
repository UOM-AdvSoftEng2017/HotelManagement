/*
 * Created by JFormDesigner on Sat Jan 07 20:14:24 EET 2017
 */

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 * @author Manos kakogian
 */
public class ClientListView extends JFrame {
    public ClientListView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Manos kakogian
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        ArrayList<Client> cl;
        cl = DBManager.getClientList();

        Object rowData[][] = new Object[cl.size()][3];
        Object columnNames[] = { "Client Id", "Name", "Phone"};

        int i=0;
        for (Client c: cl) {
            rowData[i][0]=c.getId();
            rowData[i][1]=c.getName();
            rowData[i][2]=c.getPhone();
            i++;
        }


        table1 = new JTable(rowData, columnNames);

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 50, 630, 480);

        contentPane.setPreferredSize(new Dimension(630, 480));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Manos kakogian
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
