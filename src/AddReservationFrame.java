import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.border.*;
import com.github.lgooddatepicker.components.*;
/*
 * Created by JFormDesigner on Thu Jan 12 20:25:25 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class AddReservationFrame extends JFrame {
    
    public AddReservationFrame() {
        initComponents();
    }

    private void thisWindowClosing(WindowEvent e) {
        this.close();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        this.close();
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        this.dispose();
    }

    private void buttonSelectClientActionPerformed(ActionEvent e) {
        ClientSelectionFrame csf = new ClientSelectionFrame();
        csf.show();
    }
    
    private void close() {
        Boolean close = true;
        LocalDate from = datePickerFrom.getDate();
        LocalDate to = datePickerTo.getDate();
        // check if a client has been selected
        if (textFieldClientName.getText().equals("N/A")) {
            JOptionPane.showMessageDialog(null, "You need to select a client!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            close = false;
        } else if ((from.isAfter(to)) || (from.equals(to))) {
            // check if dates are valid
            JOptionPane.showMessageDialog(null, "Invalid dates!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            close = false;
        }
        if (close) this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - George Vlahavas
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        comboBoxRoom = new JComboBox();
        label1 = new JLabel();
        datePickerFrom = new DatePicker();
        datePickerTo = new DatePicker();
        label2 = new JLabel();
        label3 = new JLabel();
        buttonSelectClient = new JButton();
        labelClientName = new JLabel();
        textFieldClientName = new JTextField();
        buttonBar = new JPanel();
        ButtonOK = new JButton();
        buttonCancel = new JButton();

        //======== this ========
        setTitle("Add Reservation");
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
                contentPanel.add(comboBoxRoom);
                comboBoxRoom.setBounds(45, 165, 200, comboBoxRoom.getPreferredSize().height);

                //---- label1 ----
                label1.setText("Room:");
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(0, 170), label1.getPreferredSize()));
                contentPanel.add(datePickerFrom);
                datePickerFrom.setBounds(45, 85, 200, datePickerFrom.getPreferredSize().height);
                contentPanel.add(datePickerTo);
                datePickerTo.setBounds(45, 120, 200, datePickerTo.getPreferredSize().height);

                //---- label2 ----
                label2.setText("From:");
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(0, 90), label2.getPreferredSize()));

                //---- label3 ----
                label3.setText("To:");
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(0, 125), label3.getPreferredSize()));

                //---- buttonSelectClient ----
                buttonSelectClient.setText("Select Client");
                buttonSelectClient.addActionListener(e -> buttonSelectClientActionPerformed(e));
                contentPanel.add(buttonSelectClient);
                buttonSelectClient.setBounds(new Rectangle(new Point(0, 0), buttonSelectClient.getPreferredSize()));

                //---- labelClientName ----
                labelClientName.setText("Client name:");
                contentPanel.add(labelClientName);
                labelClientName.setBounds(new Rectangle(new Point(0, 40), labelClientName.getPreferredSize()));

                //---- textFieldClientName ----
                textFieldClientName.setText("N/A");
                contentPanel.add(textFieldClientName);
                textFieldClientName.setBounds(95, 40, 280, textFieldClientName.getPreferredSize().height);

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

                //---- ButtonOK ----
                ButtonOK.setText("OK");
                ButtonOK.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(ButtonOK, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- buttonCancel ----
                buttonCancel.setText("Cancel");
                buttonCancel.addActionListener(e -> cancelButtonActionPerformed(e));
                buttonBar.add(buttonCancel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(415, 300);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        
        // Populate the room list
        for (Room r: RoomList.RL.getRL()) {
            comboBoxRoom.addItem(r.getId());
        }
        
        // set From and To dates to today
        datePickerFrom.setDateToToday();
        datePickerTo.setDateToToday();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - George Vlahavas
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JComboBox comboBoxRoom;
    private JLabel label1;
    private DatePicker datePickerFrom;
    private DatePicker datePickerTo;
    private JLabel label2;
    private JLabel label3;
    private JButton buttonSelectClient;
    private JLabel labelClientName;
    private JTextField textFieldClientName;
    private JPanel buttonBar;
    private JButton ButtonOK;
    private JButton buttonCancel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
