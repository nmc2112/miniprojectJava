package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

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
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

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
		
		setBounds(100, 100, 1158, 736);
		setLocationRelativeTo(null) ;
		contentPane = new JPanel() {
			 @Override
		        protected void paintComponent(Graphics grphcs) {
		            super.paintComponent(grphcs);
		            Graphics2D g2d = (Graphics2D) grphcs;
		            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                    RenderingHints.VALUE_ANTIALIAS_ON);
		            Color color1 = new Color(153,0,153);
		            Color color2 = new Color(0,204,204);
		            GradientPaint gp = new GradientPaint(0,0,color1,180,getHeight(),color2);
		            g2d.setPaint(gp);
		            g2d.fillRect(0, 0, getWidth(), getHeight()); 

		        }

	    };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("T\u00EAn \u0110\u0103ng Nh\u1EADp");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel.setBounds(622, 212, 253, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("M\u1EADt Kh\u1EA9u");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setBounds(622, 305, 121, 70);
		contentPane.add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setBounds(622, 260, 303, 35);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		
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
		
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnNewButton.setBounds(622, 433, 303, 35);
		contentPane.add(btnNewButton);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtPassword.setBounds(622, 356, 303, 35);
		contentPane.add(txtPassword);
		txtPassword.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		
		JLabel lblNewLabel_3 = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(loginFrame.class.getResource("/assets/teamwork.jpg"));
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(465, 608, Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		lblNewLabel_3.setIcon(imageIcon);
		
		lblNewLabel_3.setBounds(159, 35, 410, 608);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("\u0110\u0102NG NH\u1EACP");
		lblNewLabel_2.setBounds(686, 92, 159, 34);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 25));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(568, 35, 410, 608);
		contentPane.add(panel);
		
		
	}
}
