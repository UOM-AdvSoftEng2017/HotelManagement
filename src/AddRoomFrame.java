import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Wed Jan 18 23:00:35 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class AddRoomFrame extends JDialog {
    public AddRoomFrame(Frame owner) {
        super(owner);
        initComponents();
        
        // populate room type combobox
        for (RoomType rt : RoomTypeList.INSTANCE.getRTL()) {
            this.comboBoxRoomType.addItem(rt);
        }
    }

    private void okButtonActionPerformed(ActionEvent e) {
        String roomID = this.textFieldRoomName.getText();
        RoomType roomTypeName = (RoomType) this.comboBoxRoomType.getSelectedItem();
        boolean found = false;
        for (int i = 0; i < RoomList.INSTANCE.getRL().size(); i++) {
            if (RoomList.INSTANCE.getRL().get(i).getId().equals(roomID)) {
                found = true;
                JOptionPane.showMessageDialog(null, "There is already a room with the same name!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (!found) {
            Room newRoom = new Room(roomID, roomTypeName.getId());
            int rv = DBManager.addRoom(newRoom);
            if (rv != 0) {
                JOptionPane.showMessageDialog(null, "Error adding room to database.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                RoomList.INSTANCE.update();
                RoomListView.getInstance().updateTable();
                this.dispose();
            }
        }
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void thisWindowClosing(WindowEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - George Vlahavas
        contentPanel = new JPanel();
        label1 = new JLabel();
        textFieldRoomName = new JTextField();
        label2 = new JLabel();
        comboBoxRoomType = new JComboBox();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Add Room");
        setResizable(false);
        setModal(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== contentPanel ========
        {

            contentPanel.setLayout(null);

            //---- label1 ----
            label1.setText("Room Name:");
            contentPanel.add(label1);
            label1.setBounds(new Rectangle(new Point(0, 10), label1.getPreferredSize()));
            contentPanel.add(textFieldRoomName);
            textFieldRoomName.setBounds(90, 10, 270, textFieldRoomName.getPreferredSize().height);

            //---- label2 ----
            label2.setText("Room Type:");
            contentPanel.add(label2);
            label2.setBounds(new Rectangle(new Point(0, 50), label2.getPreferredSize()));
            contentPanel.add(comboBoxRoomType);
            comboBoxRoomType.setBounds(90, 50, 270, comboBoxRoomType.getPreferredSize().height);

            //---- okButton ----
            okButton.setText("OK");
            okButton.addActionListener(e -> okButtonActionPerformed(e));
            contentPanel.add(okButton);
            okButton.setBounds(175, 95, 90, 22);

            //---- cancelButton ----
            cancelButton.setText("Cancel");
            cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
            contentPanel.add(cancelButton);
            cancelButton.setBounds(270, 95, 90, 22);

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
        contentPane.add(contentPanel);
        contentPanel.setBounds(10, 10, 364, 125);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - George Vlahavas
    private JPanel contentPanel;
    private JLabel label1;
    private JTextField textFieldRoomName;
    private JLabel label2;
    private JComboBox comboBoxRoomType;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
