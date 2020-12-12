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
import javax.swing.table.TableRowSorter;

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
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class detailForm extends JFrame {
	private JFrame frame;
	private JTable table;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtGender;
	private JTextField txtAddress;
	private JTextField txtYear;
	JDialog dialog = new JDialog();
	private JTextField txtSearch;

	DefaultTableModel model = new DefaultTableModel(); 
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
		setExtendedState(Frame.MAXIMIZED_BOTH);
		getContentPane().setBackground(Color.WHITE);
		frame = new JFrame();
		frame.setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 158, 1200, 547);
		getContentPane().add(scrollPane);

		table = new JTable(model);
		model = (DefaultTableModel) table.getModel();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		model.addColumn("ID"); 
        model.addColumn("Tên");
        model.addColumn("Tuổi");
        model.addColumn("Giới Tính");
        model.addColumn("Địa Chỉ");
        model.addColumn("Lương");
        model.addColumn("Vị Trí"); 
        model.addColumn("Năm Bắt Đầu");
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        refreshTable(model);
		
		
		detailForm.setCellsAlignment(table,SwingConstants.CENTER);
	
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("THÊM");
		
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(270, 107, 159, 41);
		getContentPane().add(btnAdd);
		
		JLabel lblName = new JLabel("Tên");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(21, 193, 115, 34);
		getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(10, 224, 237, 35);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblAge = new JLabel("Tuổi");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAge.setBounds(21, 285, 115, 34);
		getContentPane().add(lblAge);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(10, 316, 237, 35);
		getContentPane().add(txtAge);
		
		JLabel lblGender = new JLabel("Giới Tính");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGender.setBounds(21, 379, 115, 34);
		getContentPane().add(lblGender);
		
		JComboBox gendercomboBox = new JComboBox();
		gendercomboBox.setBounds(10, 410, 237, 41);
		getContentPane().add(gendercomboBox);
		gendercomboBox.addItem("Nam");
		gendercomboBox.addItem("Nữ");
		
		
		JLabel lblName_2_1 = new JLabel("Địa Chỉ");
		lblName_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName_2_1.setBounds(21, 461, 115, 34);
		getContentPane().add(lblName_2_1);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(10, 492, 237, 35);
		getContentPane().add(txtAddress);
		
		JLabel lblPosition = new JLabel("Vị Trí");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPosition.setBounds(21, 553, 115, 34);
		getContentPane().add(lblPosition);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 588, 237, 41);
		getContentPane().add(comboBox);
		ArrayList<Position> positionList = new ListPosition().list("*"," WHERE 1=1");
		for (Position position : positionList) {
			comboBox.addItem(position.getPosition_name());
		}
		
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				boolean flag = true;
				String staff_name = txtName.getText();
				String staff_age = txtAge.getText();
				String staff_gender = gendercomboBox.getSelectedItem().toString();
				String staff_address = txtAddress.getText();
				String position_name = comboBox.getSelectedItem().toString();
				
				int staff_startYearofwork = 0;
				if(!txtYear.getText().equals("")) staff_startYearofwork = Integer.parseInt(txtYear.getText()); 
				
				if(staff_name.equals("") || staff_age.equals("") || staff_gender.equals("") || staff_address.equals("") || position_name.equals("") || staff_startYearofwork == 0) {
					JOptionPane.showMessageDialog(frame, "Hãy điền hết các thông tin!");
					flag = false;
				}
				if(flag == true) {
					int position_id = 0;
					double staff_salary = 0;
					
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
							+ "VALUES('" + staff_name + "'," + staff_age + ",'" + staff_gender + "','" + staff_address + "'," + staff_salary + " ," + position_id + " , " + staff_startYearofwork + ")";
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
				
				
			}
		});
		
		
		JLabel lblStartYear = new JLabel("Năm Bắt Đầu ");
		lblStartYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStartYear.setBounds(21, 639, 115, 34);
		getContentPane().add(lblStartYear);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(10, 670, 237, 35);
		getContentPane().add(txtYear);
		
		JButton btnDelete = new JButton("XOÁ");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if (table.getSelectedRows() != null) {
					
		           int[] selectedrows = table.getSelectedRows();
		           String id = null;
		           String sql = null;
		           Connection connection = (Connection) DBConnection.getConnection();
		           
		           if(selectedrows.length > 0) {
		        	   int response = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc muốn xóa?",
                        "Bạn đang xóa " + selectedrows.length + " người này", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE); 
		           
			           if(response == 0) {
			        	   for (int i = 0; i < selectedrows.length; i++) {
			        	   		id = table.getValueAt(selectedrows[i], 0).toString();
			        	   		System.out.println(id);
			        	   		sql  = "DELETE FROM tblstaffs WHERE staff_id =" + id;
				            	try {
					    			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
					    			preparedStatement.execute();
					    		} catch (SQLException e) {
					    			// TODO Auto-generated catch block
					    			e.printStackTrace();
					    		}
	
				           }
			    			refreshTable(model);
			           }
		           
		            	
		            }
		            else JOptionPane.showMessageDialog(frame, "Bạn chưa chọn ai để xóa");
		        }
				
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(439, 107, 159, 41);
		getContentPane().add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH NHÂN VIÊN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(630, 24, 320, 62);
		getContentPane().add(lblNewLabel);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = txtSearch.getText().toLowerCase();
				System.out.println(query);
				filter(query);
			}
		});
		txtSearch.setBounds(1201, 107, 269, 41);
		getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm Kiếm");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(1084, 107, 107, 41);
		getContentPane().add(lblNewLabel_1);
		
		
	}
	
	public void refreshTable(DefaultTableModel model) {
		model.setRowCount(0);
		ArrayList<Staff> staffList = new ListStaff().list("*"," INNER JOIN tblpositions p ON p.position_id = s.position_id ORDER BY staff_id");
		for (Staff staff : staffList) {
			String data[] = { Integer.toString(staff.getStaff_id()),staff.getStaff_name(),Integer.toString(staff.getStaff_age()),staff.getStaff_gender(),staff.getStaff_address(),Integer.toString(staff.getStaff_salary()) + " triệu",staff.getPosition_name(),Integer.toString(staff.getStaff_startYearofwork())};
	        model.addRow(data);
		}
	}
	
	public void filter(String query) {
		TableRowSorter<DefaultTableModel> tablerow = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tablerow);
		
		tablerow.setRowFilter(RowFilter.regexFilter( "(?i)" + query));
		
	}
}
