import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Fri Jan 20 00:00:44 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class ClientDeleteConfirmDialog extends JDialog {
    
    Client client;
    
    public ClientDeleteConfirmDialog(Frame owner, Client client) {
        super(owner);
        initComponents();
        
        this.client = client;
        this.textFieldName.setText(client.getName());
        this.textFieldID.setText(client.getId());
        this.textFieldPhone.setText(String.valueOf(client.getPhone()));
    }

    public ClientDeleteConfirmDialog(Dialog owner) {
        super(owner);
        initComponents();
    }

    private void thisWindowClosing(WindowEvent e) {
        this.dispose();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        boolean conflict = false; // are there any reservations for this user?
        for (Client c : ClientList.INSTANCE.getCL()) {
            if (c.getId().equals(this.client.getId())) {
                conflict = true;
                break;
            }
        }
        if (!conflict) {
            int rv = DBManager.deleteClient(this.client.getId());
            if (rv != 0) {
                JOptionPane.showMessageDialog(null, "Error deleting client from DB", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                ClientList.INSTANCE.update();
                ClientListView.getInstance().updateTable();
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cannot delete client. He has one or more reservations.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - George Vlahavas
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        textFieldName = new JTextField();
        label3 = new JLabel();
        textFieldID = new JTextField();
        label4 = new JLabel();
        textFieldPhone = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        setResizable(false);
        setTitle("Confirm Client Deletion");
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

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- label1 ----
                label1.setText("Are you sure you want to delete this client?");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(0, 5), label1.getPreferredSize()));

                //---- label2 ----
                label2.setText("Name:");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() & ~Font.BOLD));
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(0, 30), label2.getPreferredSize()));

                //---- textFieldName ----
                textFieldName.setEditable(false);
                contentPanel.add(textFieldName);
                textFieldName.setBounds(60, 30, 295, textFieldName.getPreferredSize().height);

                //---- label3 ----
                label3.setText("ID:");
                label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() & ~Font.BOLD));
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(0, 65), label3.getPreferredSize()));

                //---- textFieldID ----
                textFieldID.setEditable(false);
                contentPanel.add(textFieldID);
                textFieldID.setBounds(60, 65, 295, textFieldID.getPreferredSize().height);

                //---- label4 ----
                label4.setText("Phone Number:");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() & ~Font.BOLD));
                contentPanel.add(label4);
                label4.setBounds(new Rectangle(new Point(0, 105), label4.getPreferredSize()));

                //---- textFieldPhone ----
                textFieldPhone.setEditable(false);
                contentPanel.add(textFieldPhone);
                textFieldPhone.setBounds(110, 105, 245, textFieldPhone.getPreferredSize().height);

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
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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
    // Generated using JFormDesigner Evaluation license - George Vlahavas
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JTextField textFieldName;
    private JLabel label3;
    private JTextField textFieldID;
    private JLabel label4;
    private JTextField textFieldPhone;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
