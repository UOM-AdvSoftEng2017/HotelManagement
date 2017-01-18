import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Wed Jan 18 23:42:29 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class RoomDeleteConfirmDialog extends JDialog {
    
    Room r;
    
    public RoomDeleteConfirmDialog(Frame owner, String roomID) {
        super(owner);
        initComponents();
        
        r = RoomList.INSTANCE.getRoom(roomID);
        RoomType rt = RoomTypeList.INSTANCE.getRoomType(r.getType());
        this.textFieldRoomID.setText(r.getId());
        this.textFieldRoomType.setText(rt.getName());
    }

    private void thisWindowClosing(WindowEvent e) {
        this.dispose();
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        int rv = DBManager.deleteRoom(r);
        if (rv != 0) {
            JOptionPane.showMessageDialog(null, "Error deleting reservation from DB", "Error",
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
        label2 = new JLabel();
        textFieldRoomID = new JTextField();
        label3 = new JLabel();
        textFieldRoomType = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Confirm Room Deletion");
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
                label1.setText("Are you sure you want to delete this room?");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(0, 0), label1.getPreferredSize()));

                //---- label2 ----
                label2.setText("Room Name:");
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(0, 35), label2.getPreferredSize()));

                //---- textFieldRoomID ----
                textFieldRoomID.setEditable(false);
                contentPanel.add(textFieldRoomID);
                textFieldRoomID.setBounds(90, 35, 270, textFieldRoomID.getPreferredSize().height);

                //---- label3 ----
                label3.setText("Room Type:");
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(0, 80), label3.getPreferredSize()));

                //---- textFieldRoomType ----
                textFieldRoomType.setEditable(false);
                contentPanel.add(textFieldRoomType);
                textFieldRoomType.setBounds(90, 75, 270, textFieldRoomType.getPreferredSize().height);

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
    private JTextField textFieldRoomID;
    private JLabel label3;
    private JTextField textFieldRoomType;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
