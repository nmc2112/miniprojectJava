package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import database.ListStaff;
import model.Staff;

import java.awt.Font;
import java.util.ArrayList;

public class detail extends JFrame {
	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					detail frame = new detail();
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
	public detail() {
		frame = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1164, 805);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 119, 920, 639);
		getContentPane().add(scrollPane);
		
		
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DefaultTableModel model = new DefaultTableModel(); 
		table = new JTable(model);
		model.addColumn("ID"); 
        model.addColumn("NAME");
        model.addColumn("SALARY");
        ArrayList<Staff> staffList = new ListStaff().list("*"," WHERE 1=1");
		for (Staff staff : staffList) {
			String data[] = { Integer.toString(staff.getStaff_id()),staff.getStaff_name(),Integer.toString(staff.getStaff_salary())};
	        model.addRow(data);
		}
	
		scrollPane.setViewportView(table);
		
	}
}
