package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addFrame frame = new addFrame();
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
	public addFrame() {
		setBounds(100, 100, 490, 491);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddEngineer = new JButton("K\u1EF9 S\u01B0");
		btnAddEngineer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEnginnerFrame newframe = new addEnginnerFrame();
				newframe.setVisible(true);
				setVisible(false);
			}
		});
		btnAddEngineer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddEngineer.setBounds(97, 267, 279, 51);
		contentPane.add(btnAddEngineer);
		
		JButton btnAddWorker = new JButton("C\u00F4ng Nh\u00E2n");
		btnAddWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addWorkerFrame newframe = new addWorkerFrame();
				newframe.setVisible(true);
				setVisible(false);
			}
		});
		btnAddWorker.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddWorker.setBounds(97, 121, 279, 51);
		contentPane.add(btnAddWorker);
		
		JButton btnAddPersonnel = new JButton("Nh\u00E2n Vi\u00EAn");
		btnAddPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPersonnelFrame newframe = new addPersonnelFrame();
				newframe.setVisible(true);
				setVisible(false);
			}
		});
		btnAddPersonnel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddPersonnel.setBounds(97, 195, 279, 51);
		contentPane.add(btnAddPersonnel);
		
		JLabel lblNewLabel = new JLabel("H\u00E3y Ch\u1ECDn M\u1ED9t V\u1ECB Tr\u00ED");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(97, 33, 279, 59);
		contentPane.add(lblNewLabel);
	}

}
