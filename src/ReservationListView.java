import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
/*
 * Created by JFormDesigner on Thu Jan 12 15:30:38 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class ReservationListView extends JFrame {
    
    // the ReservationListView is a singleton, so that it's impossible to get multiple
    // windows of the same kind
    private static ReservationListView INSTANCE = null;
    
    private ReservationListView() {
        initComponents();
    }
    
    public static ReservationListView getInstance() {
        if (INSTANCE == null) INSTANCE = new ReservationListView();
        INSTANCE.updateTable();
        return INSTANCE;
    }

    private void thisWindowClosing(WindowEvent e) {
        INSTANCE.close();
    }

    private void buttonOKActionPerformed(ActionEvent e) {
        INSTANCE.close();
    }
    
    private void close() {
        INSTANCE.setVisible(false);
    }

    private void buttonAddActionPerformed(ActionEvent e) {
        AddReservationFrame arf = new AddReservationFrame(this);
        arf.setModal(true);
        arf.show();
    }

    private void buttonDeleteActionPerformed(ActionEvent e) {
        int row = tableReservations.getSelectedRow();
        if (row != -1) { // -1 means nothing is selected
            int resID = reservationsIds.get(row);
            ReservationDeleteConfirmDialog rdcd = new ReservationDeleteConfirmDialog(this, resID);
            rdcd.setModal(true);
            rdcd.show();
        }
    }

    private void buttonEditActionPerformed(ActionEvent e) {
        int row = tableReservations.getSelectedRow();
        if (row != -1) { // -1 means nothing is selected
            int resID = reservationsIds.get(row);
            Reservation r = ReservationList.INSTANCE.getReservation(resID);
            EditReservationFrame erf = new EditReservationFrame(this, r);
            erf.setModal(true);
            erf.show();
        }
    }

    public void updateTable() {
        reservationsIds = new ArrayList<>();
        boolean showPastToo = checkBox1.isSelected();
        boolean showPaidToo = checkBox2.isSelected();

        ReservationList rl = ReservationList.INSTANCE;
        ClientList cl = ClientList.INSTANCE;
        RoomList rooms = RoomList.INSTANCE;
        // define table structure

        ArrayList<Reservation> tmpArray = new ArrayList<>();

        for (int i = 0; i < rl.getRL().size(); i++) {
            // rowData[i][0] = rl.getRL().get(i).getId();

             if (!showPastToo) {
              Date endDate = rl.getRL().get(i).getEnd();
              Date currentDate = new Date();
                 if (DateConverter.compareDates(currentDate, endDate) > 0)
                     continue;
             }

            if (!showPaidToo)
                if (rl.getRL().get(i).isPaid())
                    continue;

            tmpArray.add(rl.getRL().get(i));
        }


        Object columnNames[] = { "Client", "Room", "Arrival", "Departure", "Total Price", "Paid"};
        Object rowData[][] = new Object[tmpArray.size()][6];
        // add data to table




        for (int i = 0; i < tmpArray.size(); i++) {

            reservationsIds.add(tmpArray.get(i).getId());

            String clientID = tmpArray.get(i).getcID();
            Client c = cl.getClient(clientID);
            rowData[i][0] = c.getName();
            
            String roomID = tmpArray.get(i).getrID();
            Room room = rooms.getRoom(roomID);
            rowData[i][1] = room.getId();
            
            rowData[i][2] = tmpArray.get(i).getStartDateString();
            rowData[i][3] = tmpArray.get(i).getEndDateString();
            rowData[i][4] = tmpArray.get(i).getPrice();
            String paid = "No";
            if (tmpArray.get(i).isPaid()) paid = "Yes";
            rowData[i][5] = paid;
        }
        tableReservations = new JTable(rowData, columnNames) {
            // make table cells non-editable
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        scrollPaneReservations.setViewportView(tableReservations);




    }

    private void buttonPaidActionPerformed(ActionEvent e) {
        int row = tableReservations.getSelectedRow();
        if (row != -1) { // -1 means nothing is selected
            int resID = reservationsIds.get(row);
            Reservation r = ReservationList.INSTANCE.getReservation(resID);
            r.setPaid(!r.isPaid());
            int rv = DBManager.updateReservation(r);
            if (rv == 0) {
                updateTable();
            } else {
                JOptionPane.showMessageDialog(null, "Could not update reservation in database.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    private void checkBox1StateChanged(ChangeEvent e) {
        updateTable();
    }

    private void checkBox2StateChanged(ChangeEvent e) {
        updateTable();
    }

    private void checkBox1PropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        reservationsIds = new ArrayList<>();
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - George Vlahavas
        dialogPane = new JPanel();
        buttonOK = new JButton();
        scrollPaneReservations = new JScrollPane();
        tableReservations = new JTable();
        label1 = new JLabel();
        buttonAdd = new JButton();
        buttonDelete = new JButton();
        buttonEdit = new JButton();
        separator1 = new JSeparator();
        buttonPaid = new JButton();
        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();

        //======== this ========
        setTitle("Reservation List");
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

            dialogPane.setLayout(null);

            //---- buttonOK ----
            buttonOK.setText("OK");
            buttonOK.addActionListener(e -> buttonOKActionPerformed(e));
            dialogPane.add(buttonOK);
            buttonOK.setBounds(790, 440, 90, buttonOK.getPreferredSize().height);

            //======== scrollPaneReservations ========
            {
                scrollPaneReservations.setViewportView(tableReservations);
            }
            dialogPane.add(scrollPaneReservations);
            scrollPaneReservations.setBounds(15, 35, 745, 410);

            //---- label1 ----
            label1.setText("Reservation List");
            label1.setFont(new Font("Dialog", Font.BOLD, 14));
            dialogPane.add(label1);
            label1.setBounds(new Rectangle(new Point(15, 15), label1.getPreferredSize()));

            //---- buttonAdd ----
            buttonAdd.setText("Add");
            buttonAdd.addActionListener(e -> buttonAddActionPerformed(e));
            dialogPane.add(buttonAdd);
            buttonAdd.setBounds(790, 35, 90, buttonAdd.getPreferredSize().height);

            //---- buttonDelete ----
            buttonDelete.setText("Delete");
            buttonDelete.addActionListener(e -> buttonDeleteActionPerformed(e));
            dialogPane.add(buttonDelete);
            buttonDelete.setBounds(790, 100, 90, buttonDelete.getPreferredSize().height);

            //---- buttonEdit ----
            buttonEdit.setText("Edit");
            buttonEdit.addActionListener(e -> buttonEditActionPerformed(e));
            dialogPane.add(buttonEdit);
            buttonEdit.setBounds(790, 70, 90, buttonEdit.getPreferredSize().height);
            dialogPane.add(separator1);
            separator1.setBounds(790, 135, 90, separator1.getPreferredSize().height);

            //---- buttonPaid ----
            buttonPaid.setText("Paid");
            buttonPaid.addActionListener(e -> buttonPaidActionPerformed(e));
            dialogPane.add(buttonPaid);
            buttonPaid.setBounds(790, 150, 90, buttonPaid.getPreferredSize().height);

            //---- checkBox1 ----
            checkBox1.setText("Past reservations");
            checkBox1.setSelected(true);
            checkBox1.addPropertyChangeListener("selected", e -> checkBox1PropertyChange(e));
            checkBox1.addChangeListener(e -> checkBox1StateChanged(e));
            dialogPane.add(checkBox1);
            checkBox1.setBounds(new Rectangle(new Point(620, 10), checkBox1.getPreferredSize()));

            //---- checkBox2 ----
            checkBox2.setText("Paid reservations");
            checkBox2.setSelected(true);
            checkBox2.addChangeListener(e -> checkBox2StateChanged(e));
            dialogPane.add(checkBox2);
            checkBox2.setBounds(new Rectangle(new Point(470, 10), checkBox2.getPreferredSize()));

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < dialogPane.getComponentCount(); i++) {
                    Rectangle bounds = dialogPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = dialogPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                dialogPane.setMinimumSize(preferredSize);
                dialogPane.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents


    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - George Vlahavas
    private JPanel dialogPane;
    private JButton buttonOK;
    private JScrollPane scrollPaneReservations;
    private JTable tableReservations;
    private JLabel label1;
    private JButton buttonAdd;
    private JButton buttonDelete;
    private JButton buttonEdit;
    private JSeparator separator1;
    private JButton buttonPaid;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private ArrayList<Integer> reservationsIds;
}
