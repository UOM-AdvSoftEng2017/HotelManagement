import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Thu Jan 12 21:55:05 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class ClientSelectionFrame extends JFrame {
    
    JTextField textFieldClientID;
    JTextField textFieldClientName;
    
    public ClientSelectionFrame(JTextField textFieldClientID, JTextField textFieldClientName) {
        this.textFieldClientID = textFieldClientID;
        this.textFieldClientName = textFieldClientName;
        initComponents();
        
        ArrayList<Client> cl = ClientList.CL.getCL();
        // create client table structure
        Object rowData[][] = new Object[cl.size()][2];
        Object columnNames[] = { "Client Id", "Name"};
        
        // add client data to table
        for (int i = 0; i < cl.size(); i++) {
            rowData[i][0]=cl.get(i).getId();
            rowData[i][1]=cl.get(i).getName();
        }
        tableClientList = new JTable(rowData, columnNames) {
            // table should not be editable
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        scrollPaneClients.setViewportView(tableClientList);
    }

    private void okButtonActionPerformed(ActionEvent e) {
        this.close();
    }

    private void thisWindowClosing(WindowEvent e) {
        this.close();
    }
    
    private void close() {
        boolean close = true;
        int row = tableClientList.getSelectedRow();
        if (row != -1) {
            String clientID = tableClientList.getValueAt(row, 0).toString();
            String clientName = tableClientList.getValueAt(row, 1).toString();
            textFieldClientID.setText(clientID);
            textFieldClientName.setText(clientName);
        }
        if (close) this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Manos kakogian
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        tableClientList = new JTable();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        setTitle("Select Client");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(tableClientList);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(5, 5, scrollPane1.getPreferredSize().width, 345);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < contentPanel.getComponentCount(); i++) {
                        Rectangle bounds = contentPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = contentPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Manos kakogian
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable tableClientList;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
