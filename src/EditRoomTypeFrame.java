import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Thu Jan 19 01:08:00 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class EditRoomTypeFrame extends JDialog {
    
    RoomType rt;
    
    public EditRoomTypeFrame(Frame owner, RoomType rt) {
        super(owner);
        initComponents();
                
        this.rt = rt;
        this.textFieldName.setText(rt.getName());
        this.textFieldPrice.setText(String.valueOf(rt.getPrice()));
    }

    private void thisWindowClosing(WindowEvent e) {
        this.dispose();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        String name = this.textFieldName.getText();
        try {
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The room type name cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                double price = Double.parseDouble(this.textFieldPrice.getText());
                this.rt.setName(name);
                this.rt.setPrice(price);
                int rv = DBManager.updateRoomType(this.rt);
                if (rv != 0) {
                    JOptionPane.showMessageDialog(null, "Error editing room type in database.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    RoomTypeList.INSTANCE.update();
                    RoomTypeListView.getInstance().updateTable();
                    this.dispose();
                }
            }
        } catch (java.lang.NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "You need to enter a valid price per night!", "Error",
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
        textFieldName = new JTextField();
        label2 = new JLabel();
        textFieldPrice = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Edit Room Type");
        setModal(true);
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
                label1.setText("Room Type Name:");
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(0, 5), label1.getPreferredSize()));
                contentPanel.add(textFieldName);
                textFieldName.setBounds(130, 5, 230, textFieldName.getPreferredSize().height);

                //---- label2 ----
                label2.setText("Price per Night:");
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(0, 45), label2.getPreferredSize()));
                contentPanel.add(textFieldPrice);
                textFieldPrice.setBounds(130, 45, 230, textFieldPrice.getPreferredSize().height);

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
    private JTextField textFieldPrice;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
