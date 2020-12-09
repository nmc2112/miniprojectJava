package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class detailForm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					detailForm frame = new detailForm();
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
	public detailForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 766, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		JTable table = new JTable(data, columnNames);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Kathy", "Smith", "Snowboarding", new Integer(5), Boolean.FALSE},
				{"John", "Doe", "Rowing", new Integer(3), Boolean.TRUE},
				{"Sue", "Black", "Knitting", new Integer(2), Boolean.FALSE},
				{"Jane", "White", "Speed reading", new Integer(20), Boolean.TRUE},
				{"Joe", "Brown", "Pool", new Integer(10), Boolean.FALSE},
			},
			new String[] {
				"First Name", "Last Name", "Sport", "# of Years", "Vegetarian"
			}
		));
		table.setBounds(0, 0, 1, 1);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 480, 752, -334);
		table.setFillsViewportHeight(true);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Danh S\u00E1ch Nh\u00E2n Vi\u00EAn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(124, 10, 501, 50);
		contentPane.add(lblNewLabel);
	}
}
