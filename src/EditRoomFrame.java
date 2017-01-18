import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Thu Jan 19 00:00:34 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class EditRoomFrame extends JDialog {
    
    Room r;
    
    public EditRoomFrame(Frame owner, Room r) {
        super(owner);
        initComponents();
        
        this.r = r;
        
        this.textFieldRoom.setText(r.getId());
        // populate room type combobox
        for (RoomType rt : RoomTypeList.INSTANCE.getRTL()) {
            this.comboBoxRoomType.addItem(rt);
        }
        RoomType rt = RoomTypeList.INSTANCE.getRoomType(r.getType());
        this.comboBoxRoomType.setSelectedItem(rt);
    }

    private void thisWindowClosing(WindowEvent e) {
        this.dispose();
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        Room r = RoomList.INSTANCE.getRoom(this.r.getId());
        RoomType rt = (RoomType) comboBoxRoomType.getSelectedItem();
        r.setType(rt.getId());
        int rv = DBManager.updateRoom(r);
        if (rv != 0) {
            JOptionPane.showMessageDialog(null, "Could not update reservation in database.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            RoomList.INSTANCE.update();
            RoomListView.getInstance().updateTable();
            this.dispose();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - George Vlahavas
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        textFieldRoom = new JTextField();
        label2 = new JLabel();
        comboBoxRoomType = new JComboBox();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Edit Room Details");
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
                label1.setText("Room:");
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(0, 5), label1.getPreferredSize()));

                //---- textFieldRoom ----
                textFieldRoom.setEditable(false);
                contentPanel.add(textFieldRoom);
                textFieldRoom.setBounds(85, 0, 270, textFieldRoom.getPreferredSize().height);

                //---- label2 ----
                label2.setText("Room Type:");
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(0, 40), label2.getPreferredSize()));
                contentPanel.add(comboBoxRoomType);
                comboBoxRoomType.setBounds(85, 40, 270, comboBoxRoomType.getPreferredSize().height);

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
    private JTextField textFieldRoom;
    private JLabel label2;
    private JComboBox comboBoxRoomType;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
