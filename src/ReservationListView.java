import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
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
            int resID = Integer.parseInt(tableReservations.getValueAt(row, 0).toString());
            ReservationDeleteConfirmDialog rdcd = new ReservationDeleteConfirmDialog(this, resID);
            rdcd.setModal(true);
            rdcd.show();
        }
    }

    private void buttonEditActionPerformed(ActionEvent e) {
        int row = tableReservations.getSelectedRow();
        if (row != -1) { // -1 means nothing is selected
            int resID = Integer.parseInt(tableReservations.getValueAt(row, 0).toString());
            Reservation r = ReservationList.RL.getReservation(resID);
            EditReservationFrame erf = new EditReservationFrame(this, r);
            erf.setModal(true);
            erf.show();
        }
    }

    public void updateTable() {
        ReservationList rl = ReservationList.RL;
        ClientList cl = ClientList.CL;
        RoomList rooms = RoomList.RL;
        // define table structure
        Object columnNames[] = {"Reservation ID", "Client", "Room", "Arrival", "Departure", "Total Price", "Paid"};
        Object rowData[][] = new Object[rl.getRL().size()][7];
        // add data to table
        for (int i = 0; i < rl.getRL().size(); i++) {
            rowData[i][0] = rl.getRL().get(i).getId();
            String clientID = rl.getRL().get(i).getcID();
            Client c = cl.getClient(clientID);
            rowData[i][1] = c.getName();
            
            String roomID = rl.getRL().get(i).getrID();
            Room room = rooms.getRoom(roomID);
            rowData[i][2] = room.getId();
            
            rowData[i][3] = rl.getRL().get(i).getStartDateString();
            rowData[i][4] = rl.getRL().get(i).getEndDateString();
            rowData[i][5] = rl.getRL().get(i).getPrice();
            String paid = "No";
            if (rl.getRL().get(i).isPaid()) paid = "Yes";
            rowData[i][6] = paid;
        }
        tableReservations = new JTable(rowData, columnNames) {
            // make table cells non-editable
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
       
        scrollPaneReservations.setViewportView(tableReservations);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Manos kakogian
        dialogPane = new JPanel();
        buttonBar = new JPanel();
        buttonOK = new JButton();
        scrollPaneReservations = new JScrollPane();
        tableReservations = new JTable();
        label1 = new JLabel();
        buttonAdd = new JButton();
        buttonDelete = new JButton();
        buttonEdit = new JButton();

        //======== this ========
        setTitle("Reservation List");
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

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};
            }
            dialogPane.add(buttonBar);
            buttonBar.setBounds(12, 228, 364, buttonBar.getPreferredSize().height);

            //---- buttonOK ----
            buttonOK.setText("OK");
            buttonOK.addActionListener(e -> buttonOKActionPerformed(e));
            dialogPane.add(buttonOK);
            buttonOK.setBounds(505, 430, 90, buttonOK.getPreferredSize().height);

            //======== scrollPaneReservations ========
            {
                scrollPaneReservations.setViewportView(tableReservations);
            }
            dialogPane.add(scrollPaneReservations);
            scrollPaneReservations.setBounds(15, 35, 480, 410);

            //---- label1 ----
            label1.setText("Reservation List");
            label1.setFont(new Font("Dialog", Font.BOLD, 14));
            dialogPane.add(label1);
            label1.setBounds(new Rectangle(new Point(15, 15), label1.getPreferredSize()));

            //---- buttonAdd ----
            buttonAdd.setText("Add");
            buttonAdd.addActionListener(e -> buttonAddActionPerformed(e));
            dialogPane.add(buttonAdd);
            buttonAdd.setBounds(505, 35, 90, buttonAdd.getPreferredSize().height);

            //---- buttonDelete ----
            buttonDelete.setText("Delete");
            buttonDelete.addActionListener(e -> buttonDeleteActionPerformed(e));
            dialogPane.add(buttonDelete);
            buttonDelete.setBounds(505, 105, 90, buttonDelete.getPreferredSize().height);

            //---- buttonEdit ----
            buttonEdit.setText("Edit");
            buttonEdit.addActionListener(e -> buttonEditActionPerformed(e));
            dialogPane.add(buttonEdit);
            buttonEdit.setBounds(505, 70, 90, buttonEdit.getPreferredSize().height);

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
    // Generated using JFormDesigner Evaluation license - Manos kakogian
    private JPanel dialogPane;
    private JPanel buttonBar;
    private JButton buttonOK;
    private JScrollPane scrollPaneReservations;
    private JTable tableReservations;
    private JLabel label1;
    private JButton buttonAdd;
    private JButton buttonDelete;
    private JButton buttonEdit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
