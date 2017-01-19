import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Fri Jan 20 00:13:16 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class EditClientFrame extends JDialog {
    
    Client client;
    
    public EditClientFrame(Frame owner, Client client) {
        super(owner);
        initComponents();
        
        this.client = client;
        this.textFieldName.setText(client.getName());
        this.textFieldID.setText(client.getId());
        this.textFieldPhone.setText(String.valueOf(client.getPhone()));
    }

    private void thisWindowClosing(WindowEvent e) {
        this.dispose();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        boolean proceed = true;
        int phone = -1;
        try {
            phone = Integer.parseInt(this.textFieldPhone.getText());
        } catch (java.lang.NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Phone number is not a number!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            proceed = false;
        }
        String name = this.textFieldName.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The client name cannot be empty!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            proceed = false;
        }
        if (proceed) {
            this.client.setName(name);
            this.client.setPhone(phone);
            int rv = DBManager.updateClient(client);
            if (rv != 0) {
                JOptionPane.showMessageDialog(null, "Could not update client in database.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                ClientList.INSTANCE.update();
                ClientListView.getInstance().updateTable();
                this.dispose();
            }
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
        label3 = new JLabel();
        textFieldName = new JTextField();
        textFieldID = new JTextField();
        textFieldPhone = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        setTitle("Edit Client Details");
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

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- label1 ----
                label1.setText("Name:");
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(0, 0), label1.getPreferredSize()));

                //---- label2 ----
                label2.setText("ID:");
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(0, 35), label2.getPreferredSize()));

                //---- label3 ----
                label3.setText("Phone Number:");
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(0, 65), label3.getPreferredSize()));
                contentPanel.add(textFieldName);
                textFieldName.setBounds(60, 0, 295, textFieldName.getPreferredSize().height);

                //---- textFieldID ----
                textFieldID.setEditable(false);
                textFieldID.setToolTipText("ID Number is not allowed to be edited");
                contentPanel.add(textFieldID);
                textFieldID.setBounds(60, 30, 295, textFieldID.getPreferredSize().height);
                contentPanel.add(textFieldPhone);
                textFieldPhone.setBounds(110, 65, 245, textFieldPhone.getPreferredSize().height);

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
    private JLabel label3;
    private JTextField textFieldName;
    private JTextField textFieldID;
    private JTextField textFieldPhone;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
