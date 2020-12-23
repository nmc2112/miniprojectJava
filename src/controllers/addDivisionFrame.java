package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import database.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class addDivisionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtDivisionName;
	private JLabel lblThmPhng;
	private JButton btnAdd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addDivisionFrame frame = new addDivisionFrame();
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
	public addDivisionFrame() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 561, 402);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtDivisionName = new JTextField();
		txtDivisionName.setColumns(10);
		txtDivisionName.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		txtDivisionName.setBounds(59, 214, 418, 35);
		contentPane.add(txtDivisionName);
		
		JLabel lblTnPhng = new JLabel("T\u00EAn Ph\u00F2ng");
		lblTnPhng.setForeground(Color.GRAY);
		lblTnPhng.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTnPhng.setBounds(59, 179, 418, 25);
		contentPane.add(lblTnPhng);
		
		lblThmPhng = new JLabel("Thêm Phòng, Ban");
		lblThmPhng.setHorizontalAlignment(SwingConstants.CENTER);
		lblThmPhng.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblThmPhng.setBounds(101, 47, 331, 52);
		contentPane.add(lblThmPhng);
		
		btnAdd = new JButton("THÊM");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean flag = true;
				String division_name = txtDivisionName.getText();
				if(division_name.equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Hãy điền hết các thông tin!");
					flag = false;
				}
				
				if(flag == true) {
					Connection connection = (Connection) DBConnection.getConnection();
					String sql  = "INSERT INTO tbldivision(division_name)"
							+ " VALUES (?)";
					try {
						PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
						preparedStatement.setString(1, division_name);
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
		btnAdd.setBounds(59, 284, 418, 41);
		contentPane.add(btnAdd);
	}
}
