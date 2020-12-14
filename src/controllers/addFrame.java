package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

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
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddEngineer = new JButton("  K\u1EF9 S\u01B0");
		btnAddEngineer.setIcon(new ImageIcon(addFrame.class.getResource("/assets/engineer.png")));
		btnAddEngineer.setForeground(Color.WHITE);
		btnAddEngineer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEngineerFrame newframe = new addEngineerFrame();
				newframe.setVisible(true);
				setVisible(false);
			}
		});

		btnAddEngineer.setBackground(new Color(250, 250, 250, 20) );
		btnAddEngineer.setBorder(new MatteBorder(0,0,2,0,Color.WHITE));
		btnAddEngineer.setOpaque(false);
		btnAddEngineer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddEngineer.setBounds(97, 267, 279, 51);
		contentPane.add(btnAddEngineer);
		
		JButton btnAddWorker = new JButton("C\u00F4ng Nh\u00E2n");
		btnAddWorker.setIcon(new ImageIcon(addFrame.class.getResource("/assets/worker.png")));
		btnAddWorker.setForeground(Color.WHITE);
		btnAddWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addWorkerFrame newframe = new addWorkerFrame();
				newframe.setVisible(true);
				setVisible(false);
			}
		});
		btnAddWorker.setBackground(new Color(250, 250, 250, 20) );
		btnAddWorker.setBorder(new MatteBorder(0,0,2,0,Color.WHITE));
		btnAddWorker.setOpaque(false);
		btnAddWorker.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddWorker.setBounds(97, 121, 279, 51);
		contentPane.add(btnAddWorker);
		
		JButton btnAddPersonnel = new JButton("Nh\u00E2n Vi\u00EAn");
		btnAddPersonnel.setIcon(new ImageIcon(addFrame.class.getResource("/assets/personnel.png")));
		btnAddPersonnel.setForeground(Color.WHITE);
		btnAddPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPersonnelFrame newframe = new addPersonnelFrame();
				newframe.setVisible(true);
				setVisible(false);
			}
		});

		btnAddPersonnel.setBackground(new Color(250, 250, 250, 20) );
		btnAddPersonnel.setBorder(new MatteBorder(0,0,2,0,Color.WHITE));
		btnAddPersonnel.setOpaque(false);
		btnAddPersonnel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddPersonnel.setBounds(97, 195, 279, 51);
		contentPane.add(btnAddPersonnel);
		
		JLabel lblNewLabel = new JLabel("H\u00E3y Ch\u1ECDn M\u1ED9t V\u1ECB Tr\u00ED");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(97, 33, 279, 59);
		contentPane.add(lblNewLabel);
	}

}
