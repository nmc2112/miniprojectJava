package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.DBConnection;
import database.ListDivision;
import model.Division;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class addEngineerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtStartYearOfWork;
	private JTextField txtSalary;
	JFormattedTextField txtDOB;
	private JTextField txtMajor;
	JButton btnBrowse = new JButton("Chọn Ảnh");
	JLabel lblstaff_img = new JLabel("");
	String path = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addEngineerFrame frame = new addEngineerFrame();
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
	public addEngineerFrame() {
		frameComponent();
	}
	
	public void frameComponent() {
		setBounds(100, 100, 1154, 848);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Tên");
		lblName.setForeground(Color.GRAY);
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblName.setBounds(62, 327, 115, 34);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(61, 353, 420, 35);
		contentPane.add(txtName);
		txtName.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		
		JLabel lblDOB = new JLabel("Ngày Sinh");
		lblDOB.setForeground(Color.GRAY);
		lblDOB.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDOB.setBounds(62, 466, 115, 34);
		contentPane.add(lblDOB);
		
		JLabel lblGender = new JLabel("Giới Tính");
		lblGender.setForeground(Color.GRAY);
		lblGender.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblGender.setBounds(622, 319, 115, 34);
		contentPane.add(lblGender);
		
		JComboBox gendercomboBox = new JComboBox();
		gendercomboBox.setBounds(622, 347, 419, 41);
		contentPane.add(gendercomboBox);
		gendercomboBox.addItem("Nam");
		gendercomboBox.addItem("Nữ");
		
		JLabel lblName_2_1 = new JLabel("Địa Chỉ");
		lblName_2_1.setForeground(Color.GRAY);
		lblName_2_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblName_2_1.setBounds(62, 398, 115, 34);
		contentPane.add(lblName_2_1);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(62, 424, 419, 35);
		contentPane.add(txtAddress);
		txtAddress.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		
		JLabel lblStartYear = new JLabel("Năm Bắt Đầu ");
		lblStartYear.setForeground(Color.GRAY);
		lblStartYear.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblStartYear.setBounds(62, 541, 115, 34);
		contentPane.add(lblStartYear);
		
		txtStartYearOfWork = new JTextField();
		txtStartYearOfWork.setColumns(10);
		txtStartYearOfWork.setBounds(62, 574, 419, 35);
		txtStartYearOfWork.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		contentPane.add(txtStartYearOfWork);
		
		JLabel lblAddStaff = new JLabel("Thêm Kỹ Sư");
		lblAddStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddStaff.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAddStaff.setBounds(388, 10, 331, 52);
		contentPane.add(lblAddStaff);
		
		JLabel lblBc = new JLabel("Bậc ");
		lblBc.setForeground(Color.GRAY);
		lblBc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblBc.setBounds(622, 389, 115, 34);
		contentPane.add(lblBc);
		
		JComboBox levelcomboBox = new JComboBox();
		levelcomboBox.setBounds(622, 418, 419, 41);
		contentPane.add(levelcomboBox);
		levelcomboBox.addItem("1");
		levelcomboBox.addItem("2");
		levelcomboBox.addItem("3");
		
		JLabel lblMajor = new JLabel("Ngành Đào Tạo");
		lblMajor.setForeground(Color.GRAY);
		lblMajor.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblMajor.setBounds(623, 466, 143, 34);
		contentPane.add(lblMajor);
		
		JLabel lblTrnhHc = new JLabel("Trình Độ Học Vấn");
		lblTrnhHc.setForeground(Color.GRAY);
		lblTrnhHc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTrnhHc.setBounds(622, 537, 143, 34);
		contentPane.add(lblTrnhHc);
		
		JComboBox academiclevelcomboBox = new JComboBox();
		academiclevelcomboBox.setBounds(622, 568, 419, 41);
		contentPane.add(academiclevelcomboBox);
		academiclevelcomboBox.addItem("Tốt Nghiệp THPT");
		academiclevelcomboBox.addItem("Cử Nhân Đại Học");
		academiclevelcomboBox.addItem("Thạc Sĩ");
		academiclevelcomboBox.addItem("Tiến Sĩ");
		academiclevelcomboBox.addItem("PGS-TS");
		academiclevelcomboBox.addItem("Giáo Sư");
		
		JButton btnAdd = new JButton("THÊM");
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnAdd.setBounds(402, 745, 277, 41);
		contentPane.add(btnAdd);
		
		JLabel lblLng = new JLabel("Lương");
		lblLng.setForeground(Color.GRAY);
		lblLng.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblLng.setBounds(622, 644, 115, 34);
		contentPane.add(lblLng);
		
		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(622, 677, 419, 35);
		txtSalary.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		contentPane.add(txtSalary);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		txtDOB = new JFormattedTextField(df);
		txtDOB.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
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
		txtDOB.setBounds(62, 497, 419, 34);
		contentPane.add(txtDOB);
		
		txtMajor = new JTextField();
		txtMajor.setColumns(10);
		txtMajor.setBounds(622, 497, 419, 35);
		txtMajor.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		contentPane.add(txtMajor);
		
		lblstaff_img.setBounds(61, 109, 166, 169);
		contentPane.add(lblstaff_img);
		btnBrowse.setForeground(Color.WHITE);
		btnBrowse.setBackground(Color.BLUE);
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  JFileChooser file = new JFileChooser();
	          file.setCurrentDirectory(new File(System.getProperty("user.home")));
	          //filter the files
	          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
	          file.addChoosableFileFilter(filter);
	          int result = file.showSaveDialog(null);
	           //if the user click on save in Jfilechooser
	          if(result == JFileChooser.APPROVE_OPTION){
	              File selectedFile = file.getSelectedFile();
	              path = selectedFile.getAbsolutePath();
	              lblstaff_img.setIcon(ResizeImage(path));
	          }

	          else if(result == JFileChooser.CANCEL_OPTION){
	              System.out.println("No File Select");
	          }
	          
	        }
	    });
		
		
		btnBrowse.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnBrowse.setBounds(272, 243, 109, 35);
		contentPane.add(btnBrowse);
		
		JLabel lblnhiDin = new JLabel("Ảnh Đại Diện");
		lblnhiDin.setForeground(Color.GRAY);
		lblnhiDin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblnhiDin.setBounds(62, 56, 418, 25);
		contentPane.add(lblnhiDin);
		
		JLabel lblDivision = new JLabel("Phòng Ban");
		lblDivision.setForeground(Color.GRAY);
		lblDivision.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDivision.setBounds(62, 643, 115, 34);
		contentPane.add(lblDivision);
		
		JComboBox divisionCombobox = new JComboBox();
		divisionCombobox.setBounds(62, 671, 419, 41);
		ArrayList<Division> divisionList = new ListDivision().list("*"," WHERE 1=1");
		for (Division division : divisionList) {
			divisionCombobox.addItem(division.getDivision_name());
		}
		contentPane.add(divisionCombobox);
		
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
				int staff_startYearofwork = 0;int division_id = 0;
				ArrayList<Division> divisionList = new ListDivision().list("*"," WHERE division_name = '" + divisionCombobox.getSelectedItem().toString() + "'");
				for (Division division : divisionList) {
					division_id = division.getDivision_id();
				}
				InputStream staff_img = null;
				if(path != "") {
					try {
						staff_img = new FileInputStream(new File(path));
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} 
				} 
				
				if(!txtStartYearOfWork.getText().equals("")) staff_startYearofwork = Integer.parseInt(txtStartYearOfWork.getText()); 
				if(!txtSalary.getText().equals("")) staff_salary = Double.parseDouble(txtSalary.getText()); 
				
				if(staff_name.equals("") || staff_DOB.equals("") || staff_gender.equals("") || staff_address.equals("") || staff_level.equals("") || staff_startYearofwork == 0 || staff_salary == 0 || staff_major.equals("") || staff_academiclevel.equals("") || staff_img == null || division_id == 0) {
					JOptionPane.showMessageDialog(contentPane, "Hãy điền hết các thông tin!");
					flag = false;
				}
				
				if(flag == true) {
					Connection connection = (Connection) DBConnection.getConnection();
					String sql  = "INSERT INTO tblstaffs(staff_name, staff_gender, staff_address, staff_salary, position_id, staff_startYearofwork, staff_DOB, staff_level, staff_major, staff_academiclevel, staff_img, division_id)"
							+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
					try {
						PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
						preparedStatement.setString(1, staff_name);
						preparedStatement.setString(2, staff_gender);
						preparedStatement.setString(3, staff_address);
						preparedStatement.setDouble(4, staff_salary);
						preparedStatement.setInt(5, 3);
						preparedStatement.setInt(6, staff_startYearofwork);
						preparedStatement.setString(7, staff_DOB);
						preparedStatement.setString(8, staff_level);
						preparedStatement.setString(9, staff_major);
						preparedStatement.setString(10, staff_academiclevel);
						preparedStatement.setBlob(11, staff_img);
						preparedStatement.setInt(12, division_id);
						preparedStatement.execute();
						JOptionPane.showMessageDialog(contentPane ,"Người này đã được thêm thành công!");
						setVisible(false);
					} catch (SQLException e1 ) {
						// TODO Auto-generated catch block
						e1 .printStackTrace();
					}
				}			
			}
		});
	}
	
	public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblstaff_img.getWidth(), lblstaff_img.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}

