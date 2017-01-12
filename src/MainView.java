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

    private void button2ActionPerformed(ActionEvent e) {
        ClientListView cList = ClientListView.getInstance();
        cList.show();
        label2.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - George Vlahavas
        label1 = new JLabel();
        button1 = new JButton();
        label2 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Hotel Management");
        label1.setFont(new Font(".SF NS Text", Font.ITALIC, 36));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(55, 0, 400, 85);

        //---- button1 ----
        button1.setText("Reservations");
        button1.setFont(new Font(".SF NS Text", Font.PLAIN, 22));
        contentPane.add(button1);
        button1.setBounds(115, 110, 250, 50);

        //---- label2 ----
        label2.setText("Some stats:100");
        label2.setFont(new Font(".SF NS Text", Font.PLAIN, 14));
        contentPane.add(label2);
        label2.setBounds(45, 395, 390, 35);

        //---- button2 ----
        button2.setText("Clients");
        button2.setFont(new Font(".SF NS Text", Font.PLAIN, 22));
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(115, 200, 250, 50);

        //---- button3 ----
        button3.setText("Rooms");
        button3.setFont(new Font(".SF NS Text", Font.PLAIN, 22));
        contentPane.add(button3);
        button3.setBounds(115, 290, 250, 50);

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
    private JButton button1;
    private JLabel label2;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
