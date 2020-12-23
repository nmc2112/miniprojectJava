package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.jdbc.Blob;

import database.DBConnection;
import database.ListDivision;
import database.ListEngineer;
import model.Division;
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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class editWorkerFrame extends JFrame {

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
	JButton btnBrowse = new JButton("Chọn Ảnh");
	JLabel lblstaff_img = new JLabel("");
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
					editWorkerFrame frame = new editWorkerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public editWorkerFrame() throws SQLException, IOException {
		staff_id = StaffSession.getInstanceId();
		position_id = StaffSession.getInstancePositionId();
		
		frameComponent();
	}
	
	public void frameComponent() throws SQLException, IOException {
		setBounds(100, 100, 1125, 779);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Tên");
		lblName.setForeground(Color.GRAY);
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblName.setBounds(62, 341, 115, 34);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(61, 367, 420, 35);
		contentPane.add(txtName);
		txtName.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		
		JLabel lblDOB = new JLabel("Ngày Sinh");
		lblDOB.setForeground(Color.GRAY);
		lblDOB.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDOB.setBounds(62, 480, 115, 34);
		contentPane.add(lblDOB);
		
		JLabel lblGender = new JLabel("Giới Tính");
		lblGender.setForeground(Color.GRAY);
		lblGender.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblGender.setBounds(622, 333, 115, 34);
		contentPane.add(lblGender);
		
		gendercomboBox = new JComboBox();
		gendercomboBox.setBounds(622, 361, 419, 41);
		contentPane.add(gendercomboBox);
		gendercomboBox.addItem("Nam");
		gendercomboBox.addItem("Nữ");
		
		JLabel lblName_2_1 = new JLabel("Địa Chỉ");
		lblName_2_1.setForeground(Color.GRAY);
		lblName_2_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblName_2_1.setBounds(62, 412, 115, 34);
		contentPane.add(lblName_2_1);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(62, 438, 419, 35);
		contentPane.add(txtAddress);
		txtAddress.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		
		JLabel lblStartYear = new JLabel("Năm Bắt Đầu ");
		lblStartYear.setForeground(Color.GRAY);
		lblStartYear.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblStartYear.setBounds(62, 570, 115, 34);
		contentPane.add(lblStartYear);
		
		txtStartYearOfWork = new JTextField();
		txtStartYearOfWork.setColumns(10);
		txtStartYearOfWork.setBounds(62, 603, 419, 35);
		txtStartYearOfWork.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		contentPane.add(txtStartYearOfWork);
		
		JLabel lblAddStaff = new JLabel("Sửa Thông Tin Công Nhân");
		lblAddStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddStaff.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAddStaff.setBounds(411, 0, 280, 52);
		contentPane.add(lblAddStaff);
		
		JLabel lblBc = new JLabel("Bậc ");
		lblBc.setForeground(Color.GRAY);
		lblBc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblBc.setBounds(622, 403, 115, 34);
		contentPane.add(lblBc);
		
		JComboBox levelcomboBox = new JComboBox();
		levelcomboBox.setBounds(622, 432, 419, 41);
		contentPane.add(levelcomboBox);
		levelcomboBox.addItem("1");
		levelcomboBox.addItem("2");
		levelcomboBox.addItem("3");
		
		JButton btnEdit = new JButton("CẬP NHẬT");
		btnEdit.setBackground(Color.GREEN);
		
		btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnEdit.setBounds(391, 680, 277, 41);
		contentPane.add(btnEdit);
		
		JLabel lblLng = new JLabel("Lương");
		lblLng.setForeground(Color.GRAY);
		lblLng.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblLng.setBounds(622, 477, 115, 34);
		contentPane.add(lblLng);
		
		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(622, 510, 419, 35);
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
		txtDOB.setBounds(62, 511, 419, 34);
		contentPane.add(txtDOB);
		
		lblstaff_img.setBounds(61, 109, 161, 162);
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
		btnBrowse.setBounds(232, 232, 109, 35);
		contentPane.add(btnBrowse);
		
		JLabel lblnhiDin = new JLabel("Ảnh Đại Diện");
		lblnhiDin.setForeground(Color.GRAY);
		lblnhiDin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblnhiDin.setBounds(62, 62, 418, 25);
		contentPane.add(lblnhiDin);
		
		JComboBox divisionCombobox = new JComboBox();
		divisionCombobox.setBounds(623, 597, 418, 41);
		ArrayList<Division> divisionList = new ListDivision().list("*"," WHERE 1=1");
		for (Division division : divisionList) {
			divisionCombobox.addItem(division.getDivision_name());
		}
		contentPane.add(divisionCombobox);
		
		JLabel lblPhngBan = new JLabel("Phòng Ban");
		lblPhngBan.setForeground(Color.GRAY);
		lblPhngBan.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPhngBan.setBounds(623, 553, 115, 34);
		contentPane.add(lblPhngBan);

		ArrayList<Engineer> engineerList = new ListEngineer().list("*"," INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id  WHERE staff_id = " + staff_id);
		for (Engineer staff : engineerList) {
			txtName.setText(staff.getStaff_name());
			txtAddress.setText(staff.getStaff_address());
			txtStartYearOfWork.setText(Integer.toString(staff.getStaff_startYearofwork()));
			txtSalary.setText(Integer.toString(staff.getStaff_salary()));
			txtDOB.setText(staff.getStaff_DOB());
			levelcomboBox.setSelectedItem(staff.getStaff_level());
			gendercomboBox.setSelectedItem(staff.getStaff_gender());
			if(staff.getStaff_img() != null) lblstaff_img.setIcon(getImageIcon(staff.getStaff_img()));
			divisionCombobox.setSelectedItem(staff.getDivision_name());
		}
		
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean flag = true;
				boolean imgStatus = true;
				String staff_name = txtName.getText();
				String staff_DOB = txtDOB.getText();
				String staff_gender = gendercomboBox.getSelectedItem().toString();
				String staff_address = txtAddress.getText();
				String staff_level = levelcomboBox.getSelectedItem().toString();
				double staff_salary = 0;	
				int staff_startYearofwork = 0;
				if(!txtStartYearOfWork.getText().equals("")) staff_startYearofwork = Integer.parseInt(txtStartYearOfWork.getText()); 
				if(!txtSalary.getText().equals("")) staff_salary = Double.parseDouble(txtSalary.getText()); 
				int division_id = 0;
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
					imgStatus = true;
				}	
				else imgStatus = false;
				
				
				if(staff_name.equals("") || staff_DOB.equals("") || staff_gender.equals("") || staff_address.equals("") || staff_level.equals("") || staff_startYearofwork == 0 || staff_salary == 0 || division_id == 0) {
					JOptionPane.showMessageDialog(contentPane, "Hãy điền hết các thông tin!");
					flag = false;
				}
				
				if(flag == true && imgStatus == true) {
					Connection connection = (Connection) DBConnection.getConnection();
					String sql  = "UPDATE tblstaffs SET staff_name = ?, staff_DOB = ?, staff_gender = ?, staff_address = ?, staff_salary = ?, staff_major = ?, staff_level = ?, staff_startYearofwork = ?, staff_academiclevel = ?,  staff_img = ?, division_id = ? WHERE staff_id = ?" ;
					try {
						PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
						preparedStatement.setString(1, staff_name);
						preparedStatement.setString(2, staff_DOB);
						preparedStatement.setString(3, staff_gender);
						preparedStatement.setString(4, staff_address);
						preparedStatement.setDouble(5, staff_salary);
						preparedStatement.setString(6, staff_level);
						preparedStatement.setInt(7, staff_startYearofwork);
						preparedStatement.setBlob(8, staff_img);
						preparedStatement.setInt(9, division_id);
						preparedStatement.setInt(10, staff_id);
						preparedStatement.execute();
						JOptionPane.showMessageDialog(contentPane ,"Cập nhật thành công!");
						setVisible(false);
					} catch (SQLException e1 ) {
						// TODO Auto-generated catch block
						e1 .printStackTrace();
					}
				}	
				if(flag == true && imgStatus == false) {
					Connection connection = (Connection) DBConnection.getConnection();
					String sql  = "UPDATE tblstaffs SET staff_name = ?, staff_DOB = ?, staff_gender = ?, staff_address = ?, staff_salary = ?, staff_level = ?, staff_startYearofwork = ?, division_id = ? WHERE staff_id = ?" ;
					
					try {
						PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
						preparedStatement.setString(1, staff_name);
						preparedStatement.setString(2, staff_DOB);
						preparedStatement.setString(3, staff_gender);
						preparedStatement.setString(4, staff_address);
						preparedStatement.setDouble(5, staff_salary);
						preparedStatement.setString(6, staff_level);
						preparedStatement.setInt(7, staff_startYearofwork);
						preparedStatement.setInt(8, division_id);
						preparedStatement.setInt(9, staff_id);
						preparedStatement.execute();
						JOptionPane.showMessageDialog(contentPane ,"Cập nhật thành công!");
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
	
	public ImageIcon ResizeImage(String ImagePath, byte[] pic) {
        ImageIcon MyImage = null;
        if(ImagePath != null)
        {
           MyImage = new ImageIcon(ImagePath);
        }else
        {
            MyImage = new ImageIcon(pic);
        }
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblstaff_img.getWidth(), lblstaff_img.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
	
	public byte[] getBytesFromImage(java.sql.Blob blob) throws SQLException, IOException{
	    int blobLength = (int) blob.length();  
	    byte[] bytes = blob.getBytes(1, blobLength);
	    blob.free();
	    return bytes;
	}
	
	public ImageIcon getImageIcon(byte[] img){
		ImageIcon image = new ImageIcon(img);
		Image im = image.getImage();
		Image myImg = im.getScaledInstance(lblstaff_img.getWidth(), lblstaff_img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(myImg);
		return newImage;
	}
	
}

