import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Thu Jan 19 23:29:49 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class AddClientFrame extends JDialog {
    public AddClientFrame(Frame owner) {
        super(owner);
        initComponents();
    }

    public AddClientFrame(Dialog owner) {
        super(owner);
        initComponents();
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
        String id = this.textFieldID.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The client ID cannot be empty!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            proceed = false;
        }
        for (int i = 0; i < ClientList.INSTANCE.getCL().size(); i++) {
            if (ClientList.INSTANCE.getCL().get(i).getId().equals(id)) {
                proceed = false;
                JOptionPane.showMessageDialog(null, "There is already a client with the same ID!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (proceed) {
            Client c = new Client(id, name, phone);
            int rv = DBManager.addClient(c);
            if (rv != 0) {
                JOptionPane.showMessageDialog(null, "Error adding client to database.", "Error",
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
        textFieldName = new JTextField();
        label2 = new JLabel();
        textFieldID = new JTextField();
        label3 = new JLabel();
        textFieldPhone = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        setResizable(false);
        setTitle("Add Client");
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
                label1.setBounds(new Rectangle(new Point(0, 5), label1.getPreferredSize()));
                contentPanel.add(textFieldName);
                textFieldName.setBounds(60, 5, 295, textFieldName.getPreferredSize().height);

                //---- label2 ----
                label2.setText("ID:");
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(0, 45), label2.getPreferredSize()));
                contentPanel.add(textFieldID);
                textFieldID.setBounds(60, 45, 295, textFieldID.getPreferredSize().height);

                //---- label3 ----
                label3.setText("Phone Number:");
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(0, 85), label3.getPreferredSize()));
                contentPanel.add(textFieldPhone);
                textFieldPhone.setBounds(110, 85, 245, textFieldPhone.getPreferredSize().height);

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
    private JTextField textFieldName;
    private JLabel label2;
    private JTextField textFieldID;
    private JLabel label3;
    private JTextField textFieldPhone;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
