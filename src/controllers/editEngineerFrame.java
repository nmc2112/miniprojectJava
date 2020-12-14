package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.DBConnection;
import database.ListEngineer;
import model.Engineer;
import model.StaffSession;

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

public class editEngineerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtName = null;
	private JTextField txtAddress= null;
	private JTextField txtStartYearOfWork = null;
	private JTextField txtSalary=null;
	JFormattedTextField txtDOB = null;
	private JTextField txtMajor = null;
	JComboBox levelcomboBox = null;;
	JComboBox academiclevelcomboBox = null;
	JComboBox gendercomboBox = null;
	JButton btnNewButton = new JButton("Chọn Ảnh (< 5MB) ");
	JLabel lblNewLabel = new JLabel("");
	String path = "";
	int staff_id = 0;
	int position_id = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editEngineerFrame frame = new editEngineerFrame();
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
	public editEngineerFrame() {
		staff_id = StaffSession.getInstanceId();
		position_id = StaffSession.getInstancePositionId();
		System.out.println("id = " + staff_id);
		System.out.println("position_id = " + position_id);
		
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
		lblName.setBounds(622, 51, 115, 34);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(621, 77, 420, 35);
		contentPane.add(txtName);
		txtName.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		
		JLabel lblDOB = new JLabel("Ngày Sinh");
		lblDOB.setForeground(Color.GRAY);
		lblDOB.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDOB.setBounds(622, 190, 115, 34);
		contentPane.add(lblDOB);
		
		JLabel lblGender = new JLabel("Giới Tính");
		lblGender.setForeground(Color.GRAY);
		lblGender.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblGender.setBounds(622, 341, 115, 34);
		contentPane.add(lblGender);
		
		gendercomboBox = new JComboBox();
		gendercomboBox.setBounds(622, 369, 419, 41);
		contentPane.add(gendercomboBox);
		gendercomboBox.addItem("Nam");
		gendercomboBox.addItem("Nữ");
		
		JLabel lblName_2_1 = new JLabel("Địa Chỉ");
		lblName_2_1.setForeground(Color.GRAY);
		lblName_2_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblName_2_1.setBounds(622, 122, 115, 34);
		contentPane.add(lblName_2_1);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(622, 148, 419, 35);
		contentPane.add(txtAddress);
		txtAddress.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		
		JLabel lblStartYear = new JLabel("Năm Bắt Đầu ");
		lblStartYear.setForeground(Color.GRAY);
		lblStartYear.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblStartYear.setBounds(622, 265, 115, 34);
		contentPane.add(lblStartYear);
		
		txtStartYearOfWork = new JTextField();
		txtStartYearOfWork.setColumns(10);
		txtStartYearOfWork.setBounds(622, 298, 419, 35);
		txtStartYearOfWork.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		contentPane.add(txtStartYearOfWork);
		
		JLabel lblAddStaff = new JLabel("Sửa Thông Tin");
		lblAddStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddStaff.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAddStaff.setBounds(388, 10, 231, 52);
		contentPane.add(lblAddStaff);
		
		JLabel lblBc = new JLabel("Bậc ");
		lblBc.setForeground(Color.GRAY);
		lblBc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblBc.setBounds(622, 420, 115, 34);
		contentPane.add(lblBc);
		
		JComboBox levelcomboBox = new JComboBox();
		levelcomboBox.setBounds(622, 449, 419, 41);
		contentPane.add(levelcomboBox);
		levelcomboBox.addItem("1");
		levelcomboBox.addItem("2");
		levelcomboBox.addItem("3");
		
		JLabel lblMajor = new JLabel("Ngành Đào Tạo");
		lblMajor.setForeground(Color.GRAY);
		lblMajor.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblMajor.setBounds(623, 500, 143, 34);
		contentPane.add(lblMajor);
		
		JLabel lblTrnhHc = new JLabel("Trình Độ Học Vấn");
		lblTrnhHc.setForeground(Color.GRAY);
		lblTrnhHc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTrnhHc.setBounds(622, 576, 143, 34);
		contentPane.add(lblTrnhHc);
		
		academiclevelcomboBox = new JComboBox();
		academiclevelcomboBox.setBounds(622, 607, 419, 41);
		contentPane.add(academiclevelcomboBox);
		academiclevelcomboBox.addItem("Tốt Nghiệp THPT");
		academiclevelcomboBox.addItem("Cử Nhân Đại Học");
		academiclevelcomboBox.addItem("Thạc Sĩ");
		academiclevelcomboBox.addItem("Tiến Sĩ");
		academiclevelcomboBox.addItem("PGS-TS");
		academiclevelcomboBox.addItem("Giáo Sư");
		
		JButton btnAdd = new JButton("CẬP NHẬT");
		btnAdd.setBackground(Color.GREEN);
		
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnAdd.setBounds(622, 744, 418, 41);
		contentPane.add(btnAdd);
		
		JLabel lblLng = new JLabel("Lương");
		lblLng.setForeground(Color.GRAY);
		lblLng.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblLng.setBounds(622, 658, 115, 34);
		contentPane.add(lblLng);
		
		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(622, 691, 419, 35);
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
		txtDOB.setBounds(622, 221, 419, 34);
		contentPane.add(txtDOB);
		
		txtMajor = new JTextField();
		txtMajor.setColumns(10);
		txtMajor.setBounds(622, 531, 419, 35);
		txtMajor.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		contentPane.add(txtMajor);
		
		lblNewLabel.setBounds(61, 109, 418, 568);
		contentPane.add(lblNewLabel);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		
		btnNewButton.addActionListener(new ActionListener() {
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
	              lblNewLabel.setIcon(ResizeImage(path));
	          }

	          else if(result == JFileChooser.CANCEL_OPTION){
	              System.out.println("No File Select");
	          }
	          
	        }
	    });
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnNewButton.setBounds(61, 743, 418, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblnhiDin = new JLabel("Ảnh Đại Diện");
		lblnhiDin.setForeground(Color.GRAY);
		lblnhiDin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblnhiDin.setBounds(76, 56, 418, 25);
		contentPane.add(lblnhiDin);
		
		JLabel lblPositionName = new JLabel();
		lblPositionName.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPositionName.setBounds(579, 19, 143, 34);
		if(position_id == 2) lblPositionName.setText("Nhân Viên");
		else lblPositionName.setText("Kỹ Sư");
		contentPane.add(lblPositionName);
		
		

		ArrayList<Engineer> enginnerList = new ListEngineer().list("*"," INNER JOIN tblpositions p ON p.position_id = s.position_id WHERE staff_id = " + staff_id);
		for (Engineer staff : enginnerList) {
			txtName.setText(staff.getStaff_name());
			txtAddress.setText(staff.getStaff_address());
			txtStartYearOfWork.setText(Integer.toString(staff.getStaff_startYearofwork()));
			txtSalary.setText(Integer.toString(staff.getStaff_salary()));
			txtDOB.setText(staff.getStaff_DOB());
			txtMajor.setText(staff.getStaff_major());
			levelcomboBox.setSelectedItem(staff.getStaff_level());
			gendercomboBox.setSelectedItem(staff.getStaff_gender());
			academiclevelcomboBox.setSelectedItem(staff.getStaff_academiclevel());
			
		}
		
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
//				InputStream staff_img = null;
//				try {
//					staff_img = new FileInputStream(new File(path));
//				} catch (FileNotFoundException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}  
				
				if(!txtStartYearOfWork.getText().equals("")) staff_startYearofwork = Integer.parseInt(txtStartYearOfWork.getText()); 
				if(!txtSalary.getText().equals("")) staff_salary = Double.parseDouble(txtSalary.getText()); 
				
//				if(staff_name.equals("") || staff_DOB.equals("") || staff_gender.equals("") || staff_address.equals("") || staff_level.equals("") || staff_startYearofwork == 0 || staff_salary == 0 || staff_major.equals("") || staff_academiclevel.equals("") || staff_img.equals("")) {
//					JOptionPane.showMessageDialog(contentPane, "Hãy điền hết các thông tin!");
//					flag = false;
//				}
				
				if(flag == true) {
					Connection connection = (Connection) DBConnection.getConnection();
					String sql  = "UPDATE tblstaffs SET staff_name = '" + staff_name + "', staff_DOB = '"+ staff_DOB + "', staff_gender = '" + staff_gender + "', staff_address = '" + staff_address + "', staff_salary = " + staff_salary + ", staff_major = '"+ staff_major + "', staff_level = " + staff_level + ", staff_DOB = '" + staff_DOB + "', staff_startYearofwork = " +staff_startYearofwork + "', staff_academiclevel = '" + staff_academiclevel + "' WHERE staff_id = " + staff_id ;
					System.out.println(sql);
//					try {
//						PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
//						preparedStatement.execute();
//						JOptionPane.showMessageDialog(contentPane ,"Cập nhật thành công!");
//						layout nextFrame = new layout();
//						nextFrame.setVisible(true);
//						setVisible(false);
//					} catch (SQLException e1 ) {
//						// TODO Auto-generated catch block
//						e1 .printStackTrace();
//					}
				}	
			}
		});
	}
	
	public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}

