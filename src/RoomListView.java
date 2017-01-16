import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Fri Jan 13 18:13:12 EET 2017
 */



/**
 * @author babis naskra
 */
public class RoomListView extends JFrame {
	public RoomListView() {
		initComponents();
	}

	private void button1ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void button2ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void button3ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - babis naskre
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();
		label1 = new JLabel();
		textField1 = new JTextField();

		//======== this ========
		setTitle("Room List");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(table1);
		}
		contentPane.add(scrollPane1);
		scrollPane1.setBounds(10, 45, 490, 365);

		//---- button1 ----
		button1.setText("Add");
		button1.addActionListener(e -> button1ActionPerformed(e));
		contentPane.add(button1);
		button1.setBounds(525, 40, 90, button1.getPreferredSize().height);

		//---- button2 ----
		button2.setText("Edit");
		button2.addActionListener(e -> button2ActionPerformed(e));
		contentPane.add(button2);
		button2.setBounds(525, 75, 90, button2.getPreferredSize().height);

		//---- button3 ----
		button3.setText("Ok");
		button3.addActionListener(e -> button3ActionPerformed(e));
		contentPane.add(button3);
		button3.setBounds(525, 385, 90, button3.getPreferredSize().height);

		//---- button4 ----
		button4.setText("Delete");
		button4.addActionListener(e -> button2ActionPerformed(e));
		contentPane.add(button4);
		button4.setBounds(0, 415, 90, 22);

		//---- label1 ----
		label1.setText("Room List");
		label1.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(label1);
		label1.setBounds(10, 20, 117, 17);
		contentPane.add(textField1);
		textField1.setBounds(new Rectangle(new Point(20, 20), textField1.getPreferredSize()));

		contentPane.setPreferredSize(new Dimension(635, 440));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - babis naskre
	private JScrollPane scrollPane1;
	private JTable table1;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JLabel label1;
	private JTextField textField1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
