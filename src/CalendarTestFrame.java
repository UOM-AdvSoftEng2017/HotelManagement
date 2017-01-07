import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalendarTestFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarTestFrame frame = new CalendarTestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalendarTestFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		/*
		 * Add calendar -- START
		 */
		UtilDateModel model = new UtilDateModel();
		
		// you can set any date like this:
		//model.setDate(2017, 01, 31);


		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);

		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		panel.add(datePicker);
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		
		/* 
		 * Add calendar -- END
		 */
		
		JButton buttonOK = new JButton("OK");
		panel.add(buttonOK);
		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// print the currently selected date when pressing the OK button
				System.out.println(datePicker.getJFormattedTextField().getText());
				// or individually for year, month, day
				System.out.println("Year: " + datePicker.getModel().getYear());
				System.out.println("Month: " + datePicker.getModel().getMonth() + 1); // *** Month is 0-based
				System.out.println("Day: " + datePicker.getModel().getDay());
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, buttonOK, 84, SpringLayout.SOUTH, datePicker.getJFormattedTextField());
		springLayout.putConstraint(SpringLayout.EAST, buttonOK, -149, SpringLayout.EAST, datePicker);
		
	}


}
