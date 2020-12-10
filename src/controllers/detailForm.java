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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		getContentPane().setBackground(Color.WHITE);
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 0, 1315, 1031);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(299, 127, 920, 547);
		getContentPane().add(scrollPane);

		DefaultTableModel model = new DefaultTableModel(); 
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		model.addColumn("ID"); 
        model.addColumn("Tên");
        model.addColumn("Tuổi");
        model.addColumn("Giới Tính");
        model.addColumn("Địa Chỉ");
        model.addColumn("Lương");
        model.addColumn("Vị Trí");
        model.addColumn("Năm Bắt Đầu");
        
        refreshTable(model);
		
		
		detailForm.setCellsAlignment(table,SwingConstants.CENTER);
	
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("THÊM");
		
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(10, 706, 237, 41);
		getContentPane().add(btnAdd);
		
		JLabel lblName = new JLabel("Tên");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(21, 127, 115, 34);
		getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(10, 158, 237, 35);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblAge = new JLabel("Tuổi");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAge.setBounds(21, 219, 115, 34);
		getContentPane().add(lblAge);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(10, 250, 237, 35);
		getContentPane().add(txtAge);
		
		JLabel lblGender = new JLabel("Giới Tính");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGender.setBounds(21, 313, 115, 34);
		getContentPane().add(lblGender);
		
		JComboBox gendercomboBox = new JComboBox();
		gendercomboBox.setBounds(10, 344, 237, 41);
		getContentPane().add(gendercomboBox);
		gendercomboBox.addItem("Nam");
		gendercomboBox.addItem("Nữ");
		
		
		JLabel lblName_2_1 = new JLabel("Địa Chỉ");
		lblName_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName_2_1.setBounds(21, 408, 115, 34);
		getContentPane().add(lblName_2_1);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(10, 439, 237, 35);
		getContentPane().add(txtAddress);
		
		JLabel lblPosition = new JLabel("Vị Trí");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPosition.setBounds(21, 500, 115, 34);
		getContentPane().add(lblPosition);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 535, 237, 41);
		getContentPane().add(comboBox);
		ArrayList<Position> positionList = new ListPosition().list("*"," WHERE 1=1");
		for (Position position : positionList) {
			comboBox.addItem(position.getPosition_name());
		}
		
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String staff_name = txtName.getText();
				String staff_age = txtAge.getText();
				String staff_gender = gendercomboBox.getSelectedItem().toString();
				String staff_address = txtAddress.getText();
				String position_name = comboBox.getSelectedItem().toString();
				int position_id = 0;
				double staff_salary = 0;
				int staff_startYearofwork = Integer.parseInt(txtYear.getText());
				int year = 2020 - staff_startYearofwork;
				if(position_name.equals("công nhân")){
					position_id = 1;
					staff_salary = 4 * Math.pow(1.02, year);
				}
				if(position_name.equals("nhân viên")){
					position_id = 2;
					staff_salary = 7 * Math.pow(1.02, year);
				}
				if(position_name.equals("kỹ sư")){
					position_id = 3;
					staff_salary = 10 * Math.pow(1.02, year);
				}
				System.out.println(position_id);
				Connection connection = (Connection) DBConnection.getConnection();
				String sql  = "INSERT INTO tblstaffs(staff_name, staff_age, staff_gender, staff_address, staff_salary, position_id, staff_startYearofwork)"
						+ "VALUES('" + staff_name + "'," + staff_age + ",'" + staff_gender + "','" + staff_address + "'," + staff_salary + "," + position_id + " , " + staff_startYearofwork + ")";
				System.out.println(sql);
				try {
					PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
					preparedStatement.execute();
					JOptionPane.showMessageDialog(frame, "Người này đã được thêm thành công!");
					refreshTable(model);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		JLabel lblStartYear = new JLabel("Năm Bắt Đầu ");
		lblStartYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStartYear.setBounds(21, 603, 115, 34);
		getContentPane().add(lblStartYear);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(10, 634, 237, 35);
		getContentPane().add(txtYear);
		
		JButton btnDelete = new JButton("XOÁ");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String id = model.getValueAt(table.getSelectedRow(), 0).toString();
				System.out.println(id);
				String sql = null;
	            Connection connection = (Connection) DBConnection.getConnection();
	            int response = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc muốn xóa người này?",
                        "Bạn đang xóa người này", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE); 
	            System.out.println(response);
	            if(response == 0) {
	            	sql  = "DELETE FROM tblstaffs WHERE staff_id =" + id;
	            	try {
		    			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		    			preparedStatement.execute();
		    			refreshTable(model);
		    		} catch (SQLException e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
	            }
	    		
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(299, 706, 138, 41);
		getContentPane().add(btnDelete);
		
		
	}
	
	public void refreshTable(DefaultTableModel model) {
		model.setRowCount(0);
		ArrayList<Staff> staffList = new ListStaff().list("*"," INNER JOIN tblpositions p ON p.position_id = s.position_id");
		for (Staff staff : staffList) {
			String data[] = { Integer.toString(staff.getStaff_id()),staff.getStaff_name(),Integer.toString(staff.getStaff_age()),staff.getStaff_gender(),staff.getStaff_address(),Integer.toString(staff.getStaff_salary()) + " triệu",staff.getPosition_name(),Integer.toString(staff.getStaff_startYearofwork())};
	        model.addRow(data);
		}
	}
}
