import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.*;
import com.github.lgooddatepicker.components.*;
/*
 * Created by JFormDesigner on Fri Jan 13 21:54:07 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class EditReservationFrame extends JDialog {
    
    Reservation r;
    String selectedRoom;
    // dupRL will hold all the items the standard ReservationList holds,
    // except the currently selected one
    ArrayList<Reservation> dupRL;
    
    public EditReservationFrame(Frame owner, Reservation r) {
        super(owner);
        this.r = r;
        initComponents();
        
        dupRL = new ArrayList<Reservation>();
        for (Reservation res: ReservationList.RL.getRL()) {
            if (!res.equals(r)) {
                dupRL.add(res);
            }
        }        
        
        Client c = ClientList.CL.getClient(r.getcID());
        textFieldClient.setText(c.getName());
        selectedRoom = r.getrID();
        LocalDate from = DateConverter.getLocalDate(r.getStart());
        LocalDate to = DateConverter.getLocalDate(r.getEnd());
        datePickerFrom.setDate(from);
        datePickerTo.setDate(to);
        comboBoxRoom.setSelectedItem(selectedRoom);
    }
    
    private void datePickerFromPropertyChange(PropertyChangeEvent e) {
        updateAvailableRooms();
    }

    private void datePickerToPropertyChange(PropertyChangeEvent e) {
        updateAvailableRooms();
    }
    
    private void updateAvailableRooms() {
        try {
            LocalDate from = datePickerFrom.getDate();
            LocalDate to = datePickerTo.getDate();
            // the Departure date must be at least one day
            // after the Arrival
            if (!to.isAfter(from)) {
                datePickerTo.setDate(from.plusDays(1));
            }
            Date fromDate = DateConverter.getDate(from);
            Date toDate = DateConverter.getDate(to);
            // get a list of all rooms
            ArrayList<Room> availableRooms = new ArrayList<Room>();
            for (Room room: RoomList.RL.getRL()) {
                availableRooms.add(room);
            }
            Reservation newRes = new Reservation(fromDate, toDate, this.r.getcID(), this.r.getrID());
            // keep only available rooms in the list
            for (Reservation res: ReservationList.RL.getRL()) {
                Date resFromDate = res.getStart();
                Date resToDate = res.getEnd();
                Room resRoom = RoomList.RL.getRoom(res.getrID());
                if ((res.getId() != this.r.getId()) && (res.dateConflicts(newRes)) && availableRooms.contains(resRoom)) {
                    availableRooms.remove(resRoom);
                }
            }
            // Populate the room list
            comboBoxRoom.removeAllItems();
            for (Room room: availableRooms) {
                comboBoxRoom.addItem(room.getId());
            }
        } catch (NullPointerException e) {
            // this exception only pops up when creating a window for the first
            // time. It's because the widgets on the window have not been initialized yet.
            // Nothing to worry about.
            System.out.println("Caught exception while creating window: " + e);
        }
    }

    private void okButtonActionPerformed(ActionEvent e) {
        // TODO: write the changes to the DB
        this.dispose();
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
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        textFieldClient = new JTextField();
        separator1 = new JSeparator();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        datePickerFrom = new DatePicker();
        datePickerTo = new DatePicker();
        separator2 = new JSeparator();
        label5 = new JLabel();
        label6 = new JLabel();
        comboBoxRoom = new JComboBox();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Edit Reservation");
        setModal(true);
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
                label1.setText("Client:");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(0, 0), label1.getPreferredSize()));

                //---- textFieldClient ----
                textFieldClient.setEditable(false);
                contentPanel.add(textFieldClient);
                textFieldClient.setBounds(55, 0, 310, textFieldClient.getPreferredSize().height);
                contentPanel.add(separator1);
                separator1.setBounds(0, 35, 365, 7);

                //---- label2 ----
                label2.setText("Reservation Dates:");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD));
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(0, 45), label2.getPreferredSize()));

                //---- label3 ----
                label3.setText("From:");
                label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD));
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(0, 70), label3.getPreferredSize()));

                //---- label4 ----
                label4.setText("To:");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD));
                contentPanel.add(label4);
                label4.setBounds(new Rectangle(new Point(0, 105), label4.getPreferredSize()));

                //---- datePickerFrom ----
                datePickerFrom.addPropertyChangeListener("date", e -> datePickerFromPropertyChange(e));
                contentPanel.add(datePickerFrom);
                datePickerFrom.setBounds(55, 70, 310, datePickerFrom.getPreferredSize().height);

                //---- datePickerTo ----
                datePickerTo.addPropertyChangeListener("date", e -> datePickerToPropertyChange(e));
                contentPanel.add(datePickerTo);
                datePickerTo.setBounds(55, 105, 310, datePickerTo.getPreferredSize().height);
                contentPanel.add(separator2);
                separator2.setBounds(0, 140, 365, 2);

                //---- label5 ----
                label5.setText("Select reservation dates to get a list of available rooms");
                label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.ITALIC));
                contentPanel.add(label5);
                label5.setBounds(new Rectangle(new Point(0, 145), label5.getPreferredSize()));

                //---- label6 ----
                label6.setText("Room:");
                label6.setFont(label6.getFont().deriveFont(label6.getFont().getStyle() | Font.BOLD));
                contentPanel.add(label6);
                label6.setBounds(new Rectangle(new Point(0, 170), label6.getPreferredSize()));
                contentPanel.add(comboBoxRoom);
                comboBoxRoom.setBounds(55, 170, 310, comboBoxRoom.getPreferredSize().height);

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
    private JTextField textFieldClient;
    private JSeparator separator1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private DatePicker datePickerFrom;
    private DatePicker datePickerTo;
    private JSeparator separator2;
    private JLabel label5;
    private JLabel label6;
    private JComboBox comboBoxRoom;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
