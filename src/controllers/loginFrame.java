package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.ListAdmin;
import database.ListEngineer;
import model.Admin;
import model.AdminSession;
import model.Engineer;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class loginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame frame = new loginFrame();
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
	public loginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 1028, 736);
		setLocationRelativeTo(null) ;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("T\u00EAn \u0110\u0103ng Nh\u1EADp");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel.setBounds(556, 213, 253, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("M\u1EADt Kh\u1EA9u");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setBounds(556, 306, 121, 70);
		contentPane.add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setBounds(556, 261, 371, 35);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnNewButton = new JButton("\u0110\u0102NG NH\u1EACP");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminSession.cleanadSession();
				String ad_username = txtUsername.getText();
				String ad_password = String.valueOf(txtPassword.getPassword());
				boolean flag = false;				
				
				ArrayList<Admin> AdminList = new ListAdmin().list("*"," INNER JOIN tblroles r ON r.role_id = a.role_id");
				
				for (Admin admin : AdminList) {
					if(admin.getAd_username().equals(ad_username) && admin.getAd_password().equals(ad_password)) {
						
						flag = true;
						AdminSession.getInstance(admin.getAd_id(), admin.getRole_id());
						layout nextframe = new layout();
						nextframe.setVisible(true);
					}
				}
				if(flag == false) JOptionPane.showMessageDialog(contentPane, "Tên đăng nhập hoặc mật khẩu không đúng!");
			}
		});
		
		//int[] intArr = new int[10]
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(556, 434, 371, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("\u0110\u0102NG NH\u1EACP");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNewLabel_2.setBounds(632, 104, 202, 70);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(38, 36, 465, 608);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(556, 357, 371, 35);
		contentPane.add(txtPassword);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(502, 36, 465, 608);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
	}
}
