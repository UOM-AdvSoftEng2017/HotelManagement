import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
/*
 * Created by JFormDesigner on Fri Jan 06 13:11:43 EET 2017
 */



/**
 * @author Manos kakogian
 */
public class MainView extends JFrame {
    public MainView() {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DBManager.close();
                System.exit(0);
                e.getWindow().dispose();
            }
        });


        initComponents();
    }

    private void buttonClientsActionPerformed(ActionEvent e) {
        ClientListView cList = ClientListView.getInstance();
        cList.show();
        label2.setVisible(false);
    }

    private void buttonReservationsActionPerformed(ActionEvent e) {
        ReservationListView resList = ReservationListView.getInstance();
        resList.show();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - George Vlahavas
        label1 = new JLabel();
        buttonReservations = new JButton();
        label2 = new JLabel();
        buttonClients = new JButton();
        buttonRooms = new JButton();

        //======== this ========
        setTitle("Hotel Management");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Hotel Management");
        label1.setFont(new Font(".SF NS Text", Font.ITALIC, 36));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(55, 0, 400, 85);

        //---- buttonReservations ----
        buttonReservations.setText("Reservations");
        buttonReservations.setFont(new Font(".SF NS Text", Font.PLAIN, 22));
        buttonReservations.addActionListener(e -> buttonReservationsActionPerformed(e));
        contentPane.add(buttonReservations);
        buttonReservations.setBounds(115, 110, 250, 50);

        //---- label2 ----
        label2.setText("Some stats:100");
        label2.setFont(new Font(".SF NS Text", Font.PLAIN, 14));
        contentPane.add(label2);
        label2.setBounds(5, 395, 495, 35);

        //---- buttonClients ----
        buttonClients.setText("Clients");
        buttonClients.setFont(new Font(".SF NS Text", Font.PLAIN, 22));
        buttonClients.addActionListener(e -> buttonClientsActionPerformed(e));
        contentPane.add(buttonClients);
        buttonClients.setBounds(115, 200, 250, 50);

        //---- buttonRooms ----
        buttonRooms.setText("Rooms");
        buttonRooms.setFont(new Font(".SF NS Text", Font.PLAIN, 22));
        contentPane.add(buttonRooms);
        buttonRooms.setBounds(115, 290, 250, 50);

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



        ArrayList<Client> cl = DBManager.getClientList();
        ArrayList<Room> rooms = DBManager.getRoomList();
        ArrayList<Reservation> rs = DBManager.getReservationList();

        label2.setText("Currently we Have "+cl.size()+" Clients "+rs.size()+" Reservations and "+rooms.size()+" Rooms");

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - George Vlahavas
    private JLabel label1;
    private JButton buttonReservations;
    private JLabel label2;
    private JButton buttonClients;
    private JButton buttonRooms;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
