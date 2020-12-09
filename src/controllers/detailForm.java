package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import database.DBConnection;
import database.ListPosition;
import database.ListStaff;
import model.Position;
import model.Staff;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class detailForm extends JFrame {
	private JFrame frame;
	private JTable table;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtGender;
	private JTextField txtAddress;
	private JTextField txtYear;

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
	
	 public static void setCellsAlignment(JTable table, int alignment)
    {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }
	
	public detailForm() {
		frame = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1233, 845);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(269, 114, 920, 659);
		getContentPane().add(scrollPane);
		
		
//		Connection connection = (Connection) DBConnection.getConnection();
//		String sql  = "UPDATE tblstaffs SET staff_name = 'Đạt 96' WHERE staff_id = 2";
//		try {
//			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
//			preparedStatement.execute();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		DefaultTableModel model = new DefaultTableModel(); 
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		model.addColumn("ID"); 
        model.addColumn("Tên");
        model.addColumn("Tuổi");
        model.addColumn("Lương");
        model.addColumn("Vị Trí");
        model.addColumn("Năm Bắt Đầu");
        
        ArrayList<Staff> staffList = new ListStaff().list("*"," INNER JOIN tblpositions p ON p.position_id = s.position_id");
		for (Staff staff : staffList) {
			String data[] = { Integer.toString(staff.getStaff_id()),staff.getStaff_name(),Integer.toString(staff.getStaff_age()),Integer.toString(staff.getStaff_salary()) + " triệu",staff.getPosition_name(),Integer.toString(staff.getStaff_startYearofwork())};
	        model.addRow(data);
		}
		
		
		detailForm.setCellsAlignment(table,SwingConstants.CENTER);
	
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("THÊM");
		
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(10, 732, 237, 41);
		getContentPane().add(btnAdd);
		
		JLabel lblName = new JLabel("Tên");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(21, 127, 115, 34);
		getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(10, 158, 237, 41);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblAge = new JLabel("Tuổi");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAge.setBounds(21, 219, 115, 34);
		getContentPane().add(lblAge);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(10, 250, 237, 41);
		getContentPane().add(txtAge);
		
		JLabel lblGender = new JLabel("Giới Tính");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGender.setBounds(21, 313, 115, 34);
		getContentPane().add(lblGender);
		
		txtGender = new JTextField();
		txtGender.setColumns(10);
		txtGender.setBounds(10, 344, 237, 41);
		getContentPane().add(txtGender);
		
		JLabel lblName_2_1 = new JLabel("Địa Chỉ");
		lblName_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName_2_1.setBounds(21, 408, 115, 34);
		getContentPane().add(lblName_2_1);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(10, 439, 237, 41);
		getContentPane().add(txtAddress);
		
		JLabel lblPosition = new JLabel("Vị Trí");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPosition.setBounds(21, 500, 115, 34);
		getContentPane().add(lblPosition);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 535, 237, 41);
		getContentPane().add(comboBox);
		//add value to combobox
		ArrayList<Position> positionList = new ListPosition().list("*"," WHERE 1=1");
		for (Position position : positionList) {
			comboBox.addItem(position.getPosition_name());
		}
		
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String staff_name = txtName.getText();
				String staff_age = txtAge.getText();
				String staff_gender = txtGender.getText();
				String staff_address = txtAddress.getText();
				String position_id = comboBox.getSelectedItem().toString();
				System.out.println(position_id);
			}
		});
		
		
		JLabel lblStartYear = new JLabel("Năm Bắt Đầu ");
		lblStartYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStartYear.setBounds(21, 603, 115, 34);
		getContentPane().add(lblStartYear);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(10, 634, 237, 41);
		getContentPane().add(txtYear);
		
		
		
	}
}
