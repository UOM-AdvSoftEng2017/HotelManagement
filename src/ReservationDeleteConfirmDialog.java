import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Fri Jan 13 20:52:21 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class ReservationDeleteConfirmDialog extends JDialog {
    
    Reservation r;
    
    public ReservationDeleteConfirmDialog(Frame owner, int reservationID) {
        super(owner);
        initComponents();
        
        r = ReservationList.RL.getReservation(reservationID);
        Room room = RoomList.RL.getRoom(r.getrID());
        Client c = ClientList.INSTANCE.getClient(r.getcID());
        String from = r.getStartDateString();
        String to = r.getEndDateString();
        textFieldClient.setText(c.getName());
        textFieldRoom.setText(room.getId());
        textFieldArrival.setText(from);
        textFieldDeparture.setText(to);
    }

    private void okButtonActionPerformed(ActionEvent e) {
        int rv = DBManager.deleteReservation(r);
        if (rv != 0) {
            JOptionPane.showMessageDialog(null, "Error deleting reservation from DB", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            ReservationList.RL.update();
            ReservationListView.getInstance().updateTable();
            this.dispose();
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
        label4 = new JLabel();
        label5 = new JLabel();
        textFieldClient = new JTextField();
        textFieldRoom = new JTextField();
        textFieldArrival = new JTextField();
        textFieldDeparture = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setModal(true);
        setTitle("Confirm Reservation Deletion");
        setResizable(false);
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
                label1.setText("Are you sure you want to delete this reservation?");
                label1.setFont(new Font("Dialog", Font.BOLD, 12));
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(5, 0), label1.getPreferredSize()));

                //---- label2 ----
                label2.setText("Client:");
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(0, 40), label2.getPreferredSize()));

                //---- label3 ----
                label3.setText("Room ID:");
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(0, 70), label3.getPreferredSize()));

                //---- label4 ----
                label4.setText("Arrival:");
                contentPanel.add(label4);
                label4.setBounds(new Rectangle(new Point(0, 100), label4.getPreferredSize()));

                //---- label5 ----
                label5.setText("Departure:");
                contentPanel.add(label5);
                label5.setBounds(new Rectangle(new Point(0, 130), label5.getPreferredSize()));

                //---- textFieldClient ----
                textFieldClient.setEditable(false);
                contentPanel.add(textFieldClient);
                textFieldClient.setBounds(80, 40, 280, textFieldClient.getPreferredSize().height);

                //---- textFieldRoom ----
                textFieldRoom.setEditable(false);
                contentPanel.add(textFieldRoom);
                textFieldRoom.setBounds(80, 70, 280, textFieldRoom.getPreferredSize().height);

                //---- textFieldArrival ----
                textFieldArrival.setEditable(false);
                contentPanel.add(textFieldArrival);
                textFieldArrival.setBounds(80, 100, 280, textFieldArrival.getPreferredSize().height);

                //---- textFieldDeparture ----
                textFieldDeparture.setEditable(false);
                contentPanel.add(textFieldDeparture);
                textFieldDeparture.setBounds(80, 130, 280, textFieldDeparture.getPreferredSize().height);

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
    private JLabel label4;
    private JLabel label5;
    private JTextField textFieldClient;
    private JTextField textFieldRoom;
    private JTextField textFieldArrival;
    private JTextField textFieldDeparture;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
