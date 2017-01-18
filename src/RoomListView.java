import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Wed Jan 18 22:22:20 EET 2017
 */



/**
 * @author George Vlahavas
 */
public class RoomListView extends JFrame {
    
    // the RoomListView is a singleton, so that it's impossible to get multiple
    // windows of the same kind
    private static RoomListView INSTANCE = null;
    
    private RoomListView() {
        initComponents();
    }
    
    public static RoomListView getInstance() {
        if (INSTANCE == null) INSTANCE = new RoomListView();
        INSTANCE.updateTable();
        return INSTANCE;
    }

    private void okButtonActionPerformed(ActionEvent e) {
        INSTANCE.close();
    }

    public void updateTable() {
        Object columnNames[] = { "Room ID", "Type"};
        Object rowData[][] = new Object[RoomList.INSTANCE.getRL().size()][2];
        
        for (int i = 0; i < RoomList.INSTANCE.getRL().size(); i++) {
            String roomID = RoomList.INSTANCE.getRL().get(i).getId();
            int roomTypeID = RoomList.INSTANCE.getRL().get(i).getType();
            String roomType = RoomTypeList.INSTANCE.getRoomType(roomTypeID).getName();
            rowData[i][0] = roomID;
            rowData[i][1] = roomType;
        }
        
        tableRooms = new JTable(rowData, columnNames) {
            // make table cells non-editable
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        scrollPaneRoomList.setViewportView(tableRooms);
    }

    private void thisWindowClosing(WindowEvent e) {
        INSTANCE.close();
    }
    
    private void close() {
        INSTANCE.setVisible(false);
    }

    private void buttonAddActionPerformed(ActionEvent e) {
        AddRoomFrame adf = new AddRoomFrame(this);
        adf.setModal(true);
        adf.show();
    }

    private void buttonEditActionPerformed(ActionEvent e) {
        int row = tableRooms.getSelectedRow();
        if (row != -1) { // -1 means nothing is selected
            String roomID = tableRooms.getValueAt(row, 0).toString();
            Room selected = RoomList.INSTANCE.getRoom(roomID);
            EditRoomFrame erf = new EditRoomFrame(this, selected);
            erf.setModal(true);
            erf.show();
        }
    }

    private void buttonDeleteActionPerformed(ActionEvent e) {
        int row = tableRooms.getSelectedRow();
        if (row != -1) { // -1 means nothing is selected
            String roomID = tableRooms.getValueAt(row, 0).toString();
            Room selected = RoomList.INSTANCE.getRoom(roomID);
            RoomDeleteConfirmDialog rdcd = new RoomDeleteConfirmDialog(this, selected.getId());
            rdcd.setModal(true);
            rdcd.show();
        }
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - George Vlahavas
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPaneRoomList = new JScrollPane();
        tableRooms = new JTable();
        buttonBar = new JPanel();
        okButton = new JButton();
        label1 = new JLabel();
        panel1 = new JPanel();
        buttonAdd = new JButton();
        buttonEdit = new JButton();
        buttonDelete = new JButton();

        //======== this ========
        setTitle("Room List");
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

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout(0, 10));

                //======== scrollPaneRoomList ========
                {
                    scrollPaneRoomList.setViewportView(tableRooms);
                }
                contentPanel.add(scrollPaneRoomList, BorderLayout.WEST);
            }
            dialogPane.add(contentPanel);
            contentPanel.setBounds(12, 27, 468, 378);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(null);

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton);
                okButton.setBounds(475, 10, 90, okButton.getPreferredSize().height);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < buttonBar.getComponentCount(); i++) {
                        Rectangle bounds = buttonBar.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = buttonBar.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    buttonBar.setMinimumSize(preferredSize);
                    buttonBar.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(buttonBar);
            buttonBar.setBounds(10, 405, 568, buttonBar.getPreferredSize().height);

            //---- label1 ----
            label1.setText("Room List");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
            dialogPane.add(label1);
            label1.setBounds(12, 12, 549, label1.getPreferredSize().height);

            //======== panel1 ========
            {
                panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
                panel1.setLayout(null);

                //---- buttonAdd ----
                buttonAdd.setText("Add");
                buttonAdd.addActionListener(e -> buttonAddActionPerformed(e));
                panel1.add(buttonAdd);
                buttonAdd.setBounds(10, 0, 90, buttonAdd.getPreferredSize().height);

                //---- buttonEdit ----
                buttonEdit.setText("Edit");
                buttonEdit.addActionListener(e -> buttonEditActionPerformed(e));
                panel1.add(buttonEdit);
                buttonEdit.setBounds(10, 30, 90, buttonEdit.getPreferredSize().height);

                //---- buttonDelete ----
                buttonDelete.setText("Delete");
                buttonDelete.addActionListener(e -> buttonDeleteActionPerformed(e));
                panel1.add(buttonDelete);
                buttonDelete.setBounds(10, 60, 90, buttonDelete.getPreferredSize().height);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel1.getComponentCount(); i++) {
                        Rectangle bounds = panel1.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel1.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel1.setMinimumSize(preferredSize);
                    panel1.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(panel1);
            panel1.setBounds(475, 25, 105, 206);

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
    private JPanel contentPanel;
    private JScrollPane scrollPaneRoomList;
    private JTable tableRooms;
    private JPanel buttonBar;
    private JButton okButton;
    private JLabel label1;
    private JPanel panel1;
    private JButton buttonAdd;
    private JButton buttonEdit;
    private JButton buttonDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
