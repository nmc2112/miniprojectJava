package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DBConnection;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class addEnginnerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtStartYearOfWork;
	private JTextField txtSalary;
	JFormattedTextField txtDOB;
	private JTextField txtMajor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addEnginnerFrame frame = new addEnginnerFrame();
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
	public addEnginnerFrame() {
		frameComponent();
	}
	
	public void frameComponent() {
		setBounds(100, 100, 682, 759);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Tên");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(114, 112, 115, 34);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(295, 112, 237, 35);
		contentPane.add(txtName);
		
		JLabel lblDOB = new JLabel("Ngày Sinh");
		lblDOB.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDOB.setBounds(114, 232, 115, 34);
		contentPane.add(lblDOB);
		
		JLabel lblGender = new JLabel("Giới Tính");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGender.setBounds(114, 356, 115, 34);
		contentPane.add(lblGender);
		
		JComboBox gendercomboBox = new JComboBox();
		gendercomboBox.setBounds(295, 353, 237, 41);
		contentPane.add(gendercomboBox);
		gendercomboBox.addItem("Nam");
		gendercomboBox.addItem("Nữ");
		
		JLabel lblName_2_1 = new JLabel("Địa Chỉ");
		lblName_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName_2_1.setBounds(114, 176, 115, 34);
		contentPane.add(lblName_2_1);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(295, 174, 237, 35);
		contentPane.add(txtAddress);
		
		JLabel lblStartYear = new JLabel("Năm Bắt Đầu ");
		lblStartYear.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStartYear.setBounds(114, 295, 115, 34);
		contentPane.add(lblStartYear);
		
		txtStartYearOfWork = new JTextField();
		txtStartYearOfWork.setColumns(10);
		txtStartYearOfWork.setBounds(295, 293, 237, 35);
		contentPane.add(txtStartYearOfWork);
		
		JLabel lblAddStaff = new JLabel("Thêm Kỹ Sư");
		lblAddStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddStaff.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAddStaff.setBounds(159, 37, 331, 52);
		contentPane.add(lblAddStaff);
		
		JLabel lblBc = new JLabel("Bậc ");
		lblBc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBc.setBounds(114, 416, 115, 34);
		contentPane.add(lblBc);
		
		JComboBox levelcomboBox = new JComboBox();
		levelcomboBox.setBounds(295, 414, 237, 41);
		contentPane.add(levelcomboBox);
		levelcomboBox.addItem("1");
		levelcomboBox.addItem("2");
		levelcomboBox.addItem("3");
		
		JLabel lblMajor = new JLabel("Ngành Đào Tạo");
		lblMajor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMajor.setBounds(114, 483, 143, 34);
		contentPane.add(lblMajor);
		
		JLabel lblTrnhHc = new JLabel("Trình Độ Học Vấn");
		lblTrnhHc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTrnhHc.setBounds(114, 534, 143, 34);
		contentPane.add(lblTrnhHc);
		
		JComboBox academiclevelcomboBox = new JComboBox();
		academiclevelcomboBox.setBounds(295, 533, 237, 41);
		contentPane.add(academiclevelcomboBox);
		academiclevelcomboBox.addItem("Tốt Nghiệp THPT");
		academiclevelcomboBox.addItem("Cử Nhân Đại Học");
		academiclevelcomboBox.addItem("Thạc Sĩ");
		academiclevelcomboBox.addItem("Tiến Sĩ");
		academiclevelcomboBox.addItem("PGS-TS");
		academiclevelcomboBox.addItem("Giáo Sư");
		
		JButton btnAdd = new JButton("THÊM");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean flag = true;
				String staff_name = txtName.getText();
				String staff_DOB = txtDOB.getText();
				String staff_gender = gendercomboBox.getSelectedItem().toString();
				String staff_address = txtAddress.getText();
				String staff_level = levelcomboBox.getSelectedItem().toString();
				String staff_major = txtMajor.getText();
				String staff_academiclevel = academiclevelcomboBox.getSelectedItem().toString();
				double staff_salary = 0;	
				int staff_startYearofwork = 0;
				
				if(!txtStartYearOfWork.getText().equals("")) staff_startYearofwork = Integer.parseInt(txtStartYearOfWork.getText()); 
				if(!txtSalary.getText().equals("")) staff_salary = Double.parseDouble(txtSalary.getText()); 
				
				if(staff_name.equals("") || staff_DOB.equals("") || staff_gender.equals("") || staff_address.equals("") || staff_level.equals("") || staff_startYearofwork == 0 || staff_salary == 0 || staff_major.equals("") || staff_academiclevel.equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Hãy điền hết các thông tin!");
					flag = false;
				}
				
				if(flag == true) {
					Connection connection = (Connection) DBConnection.getConnection();
					String sql  = "INSERT INTO tblstaffs(staff_name, staff_gender, staff_address, staff_salary, position_id, staff_startYearofwork, staff_DOB, staff_level, staff_major, staff_academiclevel)"
							+ " VALUES ('" + staff_name + "', '" + staff_gender + "','" + staff_address + "'," + staff_salary + " , 3, " + staff_startYearofwork + ", '" + staff_DOB + "', " + staff_level + ",'" + staff_major + "', '" + staff_academiclevel + "' )";
					System.out.println(sql);
					try {
						PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
						preparedStatement.execute();
						JOptionPane.showMessageDialog(contentPane ,"Người này đã được thêm thành công!");
						layout nextFrame = new layout();
						nextFrame.setVisible(true);
						setVisible(false);
					} catch (SQLException e1 ) {
						// TODO Auto-generated catch block
						e1 .printStackTrace();
					}
				}			
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(114, 650, 418, 41);
		contentPane.add(btnAdd);
		
		JLabel lblLng = new JLabel("Lương");
		lblLng.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLng.setBounds(114, 593, 115, 34);
		contentPane.add(lblLng);
		
		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(295, 591, 237, 35);
		contentPane.add(txtSalary);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		txtDOB = new JFormattedTextField(df);
		txtDOB .addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
		      {
		        JOptionPane.showMessageDialog(null, "Hãy Nhập Đúng Định Dạng Thời Gian");
		        e.consume();
		      }
		    }
		  });
		txtDOB.setBounds(295, 235, 237, 34);
		contentPane.add(txtDOB);
		
		txtMajor = new JTextField();
		txtMajor.setColumns(10);
		txtMajor.setBounds(295, 482, 237, 35);
		contentPane.add(txtMajor);
	}
}
