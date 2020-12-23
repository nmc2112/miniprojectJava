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

public class addWorkerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtStartYearOfWork;
	private JTextField txtSalary;
	JFormattedTextField txtDOB;
	JButton btnNewButton = new JButton("Chọn Ảnh");
	JLabel lblNewLabel = new JLabel("");
	String path = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addWorkerFrame frame = new addWorkerFrame();
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
	public addWorkerFrame() {
		frameComponent();
	}
	
	public void frameComponent() {
		setBounds(100, 100, 1077, 883);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Tên");
		lblName.setForeground(Color.GRAY);
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblName.setBounds(61, 363, 418, 25);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		txtName.setBounds(61, 398, 418, 35);
		contentPane.add(txtName);
		
		JLabel lblDOB = new JLabel("Ngày Sinh");
		lblDOB.setForeground(Color.GRAY);
		lblDOB.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDOB.setBounds(61, 533, 115, 34);
		contentPane.add(lblDOB);
		
		JLabel lblGender = new JLabel("Giới Tính");
		lblGender.setForeground(Color.GRAY);
		lblGender.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblGender.setBounds(541, 443, 115, 34);
		contentPane.add(lblGender);
		
		JComboBox gendercomboBox = new JComboBox();
		gendercomboBox.setBackground(Color.WHITE);
		gendercomboBox.setBounds(541, 471, 418, 41);
		contentPane.add(gendercomboBox);
		gendercomboBox.addItem("Nam");
		gendercomboBox.addItem("Nữ");
		
		JLabel lblName_2_1 = new JLabel("Địa Chỉ");
		lblName_2_1.setForeground(Color.GRAY);
		lblName_2_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblName_2_1.setBounds(61, 443, 115, 34);
		contentPane.add(lblName_2_1);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(61, 475, 418, 35);
		txtAddress.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		contentPane.add(txtAddress);
		
		JLabel lblStartYear = new JLabel("Năm Bắt Đầu ");
		lblStartYear.setForeground(Color.GRAY);
		lblStartYear.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblStartYear.setBounds(541, 630, 115, 34);
		contentPane.add(lblStartYear);
		
		txtStartYearOfWork = new JTextField();
		txtStartYearOfWork.setColumns(10);
		txtStartYearOfWork.setBounds(541, 669, 418, 35);
		txtStartYearOfWork.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		contentPane.add(txtStartYearOfWork);
		
		JLabel lblAddStaff = new JLabel("Thêm Công Nhân");
		lblAddStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddStaff.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAddStaff.setBounds(342, 10, 331, 52);
		contentPane.add(lblAddStaff);
		
		JLabel lblBc = new JLabel("Bậc ");
		lblBc.setForeground(Color.GRAY);
		lblBc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblBc.setBounds(541, 533, 115, 34);
		contentPane.add(lblBc);
		
		JComboBox levelcomboBox = new JComboBox();
		levelcomboBox.setBackground(Color.WHITE);
		levelcomboBox.setBounds(541, 559, 418, 41);
		contentPane.add(levelcomboBox);
		levelcomboBox.addItem("1");
		levelcomboBox.addItem("2");
		levelcomboBox.addItem("3");
		
		JButton btnAdd = new JButton("THÊM");
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnAdd.setBounds(299, 772, 418, 41);
		btnAdd.setBackground(Color.GREEN);
		contentPane.add(btnAdd);
		
		JLabel lblLng = new JLabel("Lương");
		lblLng.setForeground(Color.GRAY);
		lblLng.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblLng.setBounds(61, 630, 115, 34);
		contentPane.add(lblLng);
		
		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(61, 669, 418, 35);
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
		txtDOB.setBounds(61, 564, 418, 34);
		contentPane.add(txtDOB);
		
		lblNewLabel.setBounds(61, 109, 159, 163);
		contentPane.add(lblNewLabel);
		
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
		btnNewButton.setBounds(230, 231, 109, 35);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		contentPane.add(btnNewButton);
		

		
		
		JLabel lblnhiDin = new JLabel("Ảnh Đại Diện");
		lblnhiDin.setForeground(Color.GRAY);
		lblnhiDin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblnhiDin.setBounds(61, 74, 418, 25);
		contentPane.add(lblnhiDin);
		
		JLabel lblPhngBan = new JLabel("Phòng, Ban");
		lblPhngBan.setForeground(Color.GRAY);
		lblPhngBan.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPhngBan.setBounds(541, 358, 115, 34);
		contentPane.add(lblPhngBan);
		
		JComboBox divisionCombobox = new JComboBox();
		divisionCombobox.setBackground(Color.WHITE);
		divisionCombobox.setBounds(541, 394, 418, 41);
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
				int division_id = 0;
				ArrayList<Division> divisionList = new ListDivision().list("*"," WHERE division_name = '" + divisionCombobox.getSelectedItem().toString() + "'");
				for (Division division : divisionList) {
					division_id = division.getDivision_id();
				}
				double staff_salary = 0;	
				int staff_startYearofwork = 0;
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
				
				if(staff_name.equals("") || staff_DOB.equals("") || staff_gender.equals("") || staff_address.equals("") || staff_level.equals("") || staff_startYearofwork == 0 || staff_salary == 0 || staff_img == null || division_id == 0) {
					JOptionPane.showMessageDialog(contentPane, "Hãy điền hết các thông tin!");
					flag = false;
				}
				
				if(flag == true) {
					Connection connection = (Connection) DBConnection.getConnection();
					String sql  = "INSERT INTO tblstaffs(staff_name, staff_gender, staff_address, staff_salary, position_id, staff_startYearofwork, staff_DOB, staff_level, staff_img, division_id)"
							+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
					try {
						PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
						preparedStatement.setString(1, staff_name);
						preparedStatement.setString(2, staff_gender);
						preparedStatement.setString(3, staff_address);
						preparedStatement.setDouble(4, staff_salary);
						preparedStatement.setInt(5, 1);
						preparedStatement.setInt(6, staff_startYearofwork);
						preparedStatement.setString(7, staff_DOB);
						preparedStatement.setString(8, staff_level);
						preparedStatement.setBlob(9, staff_img);
						preparedStatement.setInt(10, division_id);
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
        Image newImg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}
