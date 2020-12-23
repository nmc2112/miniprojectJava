package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import database.DBConnection;

import javax.swing.JFormattedTextField;
import java.text.Format;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class addManagerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private JPasswordField passwordField;
	String salt = "kjsbdiaskdnasidka";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addManagerFrame frame = new addManagerFrame();
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
	public addManagerFrame() {
		setBounds(100, 100, 647, 677);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Tên");
		lblName.setForeground(Color.GRAY);
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblName.setBounds(101, 145, 418, 25);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		txtName.setBounds(101, 180, 418, 35);
		contentPane.add(txtName);
		
		JLabel lblName_2_1 = new JLabel("Email");
		lblName_2_1.setForeground(Color.GRAY);
		lblName_2_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblName_2_1.setBounds(101, 225, 115, 34);
		contentPane.add(lblName_2_1);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		txtEmail.setBounds(101, 257, 418, 35);
		contentPane.add(txtEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.GRAY);
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPassword.setBounds(101, 302, 115, 34);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(101, 346, 418, 35);
		passwordField.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		contentPane.add(passwordField);
		
		JLabel lblGender = new JLabel("Quyền");
		lblGender.setForeground(Color.GRAY);
		lblGender.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblGender.setBounds(101, 379, 115, 34);
		contentPane.add(lblGender);
		
		JComboBox rolecomboBox = new JComboBox();
		rolecomboBox.setBackground(Color.WHITE);
		rolecomboBox.setBounds(101, 414, 418, 41);
		rolecomboBox.addItem("Quản Lý");
		rolecomboBox.addItem("Nhân Viên");
		contentPane.add(rolecomboBox);
		
		JButton btnAdd = new JButton("THÊM");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = true;
				String ad_username = txtName.getText();
				String ad_email = txtEmail.getText();
				String ad_password = String.valueOf(passwordField.getPassword());
				ad_password = get_SHA_512_SecurePassword(ad_password, salt);
				int role_id = 0;
				switch(rolecomboBox.getSelectedItem().toString()) {
					case "Quản Lý" :{
						role_id = 2;
						break;
					}
					case "Nhân Viên" :{
						role_id = 3;
						break;
					}
				}
				
				if(ad_username.equals("") || ad_email.equals("") || role_id == 0 || ad_password.equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Hãy điền hết các thông tin!");
					flag = false;
				}
				
				if(flag == true) {
					Connection connection = (Connection) DBConnection.getConnection();
					String sql  = "INSERT INTO tbladmin(ad_username, ad_email, ad_password, role_id)"
							+ " VALUES (?,?,?,?)";
					try {
						PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
						preparedStatement.setString(1, ad_username);
						preparedStatement.setString(2, ad_email);
						preparedStatement.setString(3, ad_password);
						preparedStatement.setInt(4, role_id);
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
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setBounds(101, 489, 418, 41);
		contentPane.add(btnAdd);
		
		JLabel lblThmQunL = new JLabel("Thêm Quản Lý");
		lblThmQunL.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmQunL.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblThmQunL.setBounds(140, 39, 331, 52);
		contentPane.add(lblThmQunL);
		
	}
	
	public String get_SHA_512_SecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
