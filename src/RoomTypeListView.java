import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Thu Jan 19 00:24:40 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class RoomTypeListView extends JFrame {
    
    // the RoomTypeListView is a singleton, so that it's impossible to get multiple
    // windows of the same kind
    private static RoomTypeListView INSTANCE = null;
    
    private RoomTypeListView() {
        initComponents();
    }
    
    public static RoomTypeListView getInstance() {
        if (INSTANCE == null) INSTANCE = new RoomTypeListView();
        INSTANCE.updateTable();
        return INSTANCE;
    }

    public void updateTable() {
        Object columnNames[] = { "Room Type ID", "Type", "Price per night"};
        Object rowData[][] = new Object[RoomTypeList.INSTANCE.getRTL().size()][3];
        
        for (int i = 0; i < RoomTypeList.INSTANCE.getRTL().size(); i++) {
            int roomTypeID = RoomTypeList.INSTANCE.getRTL().get(i).getId();
            String roomTypeName = RoomTypeList.INSTANCE.getRTL().get(i).getName();
            double price = RoomTypeList.INSTANCE.getRTL().get(i).getPrice();
            rowData[i][0] = roomTypeID;
            rowData[i][1] = roomTypeName;
            rowData[i][2] = price;
        }
        
        tableRoomTypes = new JTable(rowData, columnNames) {
            // make table cells non-editable
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        scrollPaneRoomTypes.setViewportView(tableRoomTypes);
    }
    
    private void thisWindowClosing(WindowEvent e) {
        INSTANCE.close();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        INSTANCE.close();
    }

    private void close() {
        INSTANCE.setVisible(false);
    }
    
    private void buttonAddActionPerformed(ActionEvent e) {
        AddRoomTypeFrame artf = new AddRoomTypeFrame(this);
        artf.setModal(true);
        artf.show();
    }

    private void buttonEditActionPerformed(ActionEvent e) {
        int row = tableRoomTypes.getSelectedRow();
        if (row != -1) { // -1 means nothing is selected
            int roomTypeID = Integer.parseInt((tableRoomTypes.getValueAt(row, 0).toString()));
            RoomType selected = RoomTypeList.INSTANCE.getRoomType(roomTypeID);
            EditRoomTypeFrame ertf = new EditRoomTypeFrame(this, selected);
            ertf.setModal(true);
            ertf.show();
        }
    }

    private void buttonDeleteActionPerformed(ActionEvent e) {
        int row = tableRoomTypes.getSelectedRow();
        if (row != -1) { // -1 means nothing is selected
            int roomTypeID = Integer.parseInt((tableRoomTypes.getValueAt(row, 0).toString()));
            RoomType selected = RoomTypeList.INSTANCE.getRoomType(roomTypeID);
            RoomTypeDeleteConfirmDialog rdcd = new RoomTypeDeleteConfirmDialog(this, selected);
            rdcd.setModal(true);
            rdcd.show();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - George Vlahavas
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        scrollPaneRoomTypes = new JScrollPane();
        tableRoomTypes = new JTable();
        buttonAdd = new JButton();
        buttonEdit = new JButton();
        buttonDelete = new JButton();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        setTitle("Room Type List");
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
                label1.setText("Room Type List:");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(0, 0), label1.getPreferredSize()));

                //======== scrollPaneRoomTypes ========
                {
                    scrollPaneRoomTypes.setViewportView(tableRoomTypes);
                }
                contentPanel.add(scrollPaneRoomTypes);
                scrollPaneRoomTypes.setBounds(0, 20, 395, 300);

                //---- buttonAdd ----
                buttonAdd.setText("Add");
                buttonAdd.addActionListener(e -> buttonAddActionPerformed(e));
                contentPanel.add(buttonAdd);
                buttonAdd.setBounds(410, 20, 90, buttonAdd.getPreferredSize().height);

                //---- buttonEdit ----
                buttonEdit.setText("Edit");
                buttonEdit.addActionListener(e -> buttonEditActionPerformed(e));
                contentPanel.add(buttonEdit);
                buttonEdit.setBounds(410, 50, 90, buttonEdit.getPreferredSize().height);

                //---- buttonDelete ----
                buttonDelete.setText("Delete");
                buttonDelete.addActionListener(e -> buttonDeleteActionPerformed(e));
                contentPanel.add(buttonDelete);
                buttonDelete.setBounds(410, 80, 90, buttonDelete.getPreferredSize().height);

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
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JScrollPane scrollPaneRoomTypes;
    private JTable tableRoomTypes;
    private JButton buttonAdd;
    private JButton buttonEdit;
    private JButton buttonDelete;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
