package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.DBConnection;
import database.ListAdmin;
import database.ListEngineer;
import model.Admin;
import model.AdminSession;
import model.CustomButton;
import model.DropShadowBorder;
import model.Engineer;
import model.RoundedBorder;
import model.StaffSession;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JToggleButton;

public class layout extends JFrame {

	private JPanel contentPane;
	JLayeredPane layeredPane = new JLayeredPane();
	private JTable table;
	private JTextField txtGender;
	JDialog dialog = new JDialog();
	private JTextField txtSearch;
	DefaultTableModel model = new DefaultTableModel(); 
	private int ad_id = 0;
	private int role_id = 0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					layout frame = new layout();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public layout() {
		ad_id = AdminSession.getInstanceId();
		role_id = AdminSession.getInstanceroleId();
		layoutComponent();
		System.out.println(ad_id);
		
	}
	/**
	 * Create the frame.
	 */
	public void layoutComponent() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1609, 838);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane.setBounds(259, 67, 1258, 744);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panelTable = new JPanel();
		panelTable.setBackground(new Color(255, 255, 255));
		layeredPane.add(panelTable, "detailForm");
		
		//frame listStaff
		
		
		JPanel panelAccount = new JPanel();
		panelAccount.setBackground(Color.WHITE);
		layeredPane.add(panelAccount, "accountForm");
		
		JButton btnPanelTable = new JButton("Danh Sách Nhân Viên");
		btnPanelTable.setForeground(Color.WHITE);
		btnPanelTable.setIcon(new ImageIcon(layout.class.getResource("/assets/employee-1.png")));
		btnPanelTable.setOpaque(false); // background of parent will be painted first
		btnPanelTable.setBackground(new Color(250, 250, 250, 20) );
		btnPanelTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panelTable);
			}
		});

		new listStaffFrame().listStaffFrame(panelTable, role_id, ad_id);
		btnPanelTable.setBorder(new MatteBorder(0,0,2,0,Color.WHITE));
		btnPanelTable.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnPanelTable.setBounds(10, 165, 240, 53);
		contentPane.add(btnPanelTable);
		
		JPanel panel = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics grphcs) {
	            super.paintComponent(grphcs);
	            Graphics2D g2d = (Graphics2D) grphcs;
	            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                    RenderingHints.VALUE_ANTIALIAS_ON);
	            Color color1 = new Color(32, 136, 203);
	            Color color2 = Color.PINK;
	            GradientPaint gp = new GradientPaint(0,0,color1,180,getHeight(),color2);
	            g2d.setPaint(gp);
	            g2d.fillRect(0, 0, getWidth(), getHeight()); 

	        }

	    };
	    panel.setBorder(new MatteBorder(0,0,0,2,Color.BLACK));
	    panel.setBounds(0, 0, 259, 811);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnDanhSchQun = new JButton("Danh Sách Quản Lý");
		if(role_id == 1) {
			btnDanhSchQun.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchPanel(panelAccount);
				}
			});
			new listManagerFrame().listManagerFrame(panelAccount, role_id, ad_id);
		}
		else {
			btnDanhSchQun.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(contentPane, "Bạn không có quyền này!");
				}
			});
		}
		btnDanhSchQun.setIcon(new ImageIcon(layout.class.getResource("/assets/boss.png")));
		btnDanhSchQun.setOpaque(false);
		btnDanhSchQun.setForeground(Color.WHITE);
		btnDanhSchQun.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnDanhSchQun.setBorder(new MatteBorder(0,0,2,0,Color.WHITE));
		btnDanhSchQun.setBackground(new Color(250, 250, 250, 20));
		btnDanhSchQun.setBounds(10, 235, 240, 53);
		panel.add(btnDanhSchQun);
		
		JButton btnNewButton = new JButton("Đăng Xuất");
		btnNewButton.setBounds(1370, 10, 147, 40);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminSession.cleanadSession();
				loginFrame nextFrame = new loginFrame();
				nextFrame.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setIcon(new ImageIcon(layout.class.getResource("/assets/exit.png")));
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
	}
	
	public void filter(String query) {
		TableRowSorter<DefaultTableModel> tablerow = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tablerow);
		
		
		if (query.trim().length() == 0) {
			tablerow.setRowFilter(null);
        } else {
        	tablerow.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
		
	}
	
	public void switchPanel(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
}
