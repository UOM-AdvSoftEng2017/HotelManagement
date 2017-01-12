import java.awt.*;
import java.awt.event.*;
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
        // TODO add your code here
    }

    private void buttonDeleteActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void buttonEditActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void updateTable() {
        ArrayList<Reservation> rl = DBManager.getReservationList();
        Object rowData[][] = new Object[rl.size()][3];
        Object columnNames[] = { "Client", "Room", "Arrival", "Departure"};
        tableReservations = new JTable(rowData, columnNames);
        System.out.println(rl.size());
        //INSTANCE.tableReservations.setModel(dataModel);
        
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - George Vlahavas
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
    // Generated using JFormDesigner Evaluation license - George Vlahavas
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
