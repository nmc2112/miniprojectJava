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
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import database.DBConnection;
import database.ListAdmin;
import database.ListEngineer;
import model.Admin;
import model.AdminSession;
import model.Engineer;
import model.StaffSession;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import javax.swing.JComboBox;

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
		
		
		JPanel panelAccount = new JPanel();
		panelAccount.setBackground(Color.WHITE);
		layeredPane.add(panelAccount, "accountForm");
		
		JPanel panelDivision = new JPanel();
		panelDivision.setBackground(Color.WHITE);
		layeredPane.add(panelDivision, "name_288141872318900");
		
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
		panelTable.setLayout(null);

		new listStaffFrame().listStaffFrame(panelTable, role_id, ad_id);
		//staff frame
		
		//end staff frame
		
		
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
					switchPanel(panelDivision);
				}
			});
			new listManagerFrame().listManagerFrame(panelDivision, role_id, ad_id);
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
		
		JButton btnDanhSchPhng = new JButton("Danh Sách Phòng Ban");
		if(role_id == 1) {
			btnDanhSchPhng.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchPanel(panelAccount);
				}
			});
			new listDivisionFrame().listDivisionFrame(panelAccount, role_id, ad_id);
		}
		else {
			btnDanhSchPhng.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(contentPane, "Bạn không có quyền này!");
				}
			});
		}
		btnDanhSchPhng.setIcon(new ImageIcon(layout.class.getResource("/assets/office.png")));
		btnDanhSchPhng.setOpaque(false);
		btnDanhSchPhng.setForeground(Color.WHITE);
		btnDanhSchPhng.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnDanhSchPhng.setBorder(new MatteBorder(0,0,2,0,Color.WHITE));
		btnDanhSchPhng.setBackground(new Color(250, 250, 250, 20));
		btnDanhSchPhng.setBounds(10, 306, 240, 53);
		panel.add(btnDanhSchPhng);
		
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
	public void refreshTableStaff(DefaultTableModel model, String query, Integer maxEntries, Integer startEntry) {
		model.setRowCount(0);
		startRow = startEntry;
		
		String condition = " INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id"
						 + " WHERE s.staff_id LIKE '%" + query +"%'"
				 		 + " OR s.staff_name LIKE '%" + query +"%'"
						 + " OR s.staff_gender LIKE '%" + query +"%'"
				 		 + " OR s.staff_DOB LIKE '%" + query +"%'"
				 		 + " OR s.staff_gender LIKE '%" + query +"%'"
				 		 + " OR s.staff_address LIKE '%" + query +"%'"
				 		 + " OR s.staff_salary LIKE '%" + query +"%'"
				 		 + " OR s.staff_startYearofwork LIKE '%" + query +"%'"
				 		 + " OR p.position_name LIKE '%" + query +"%'"
				 		 + " OR d.division_name LIKE '%" + query +"%'"
		 		 		 + " LIMIT " + startEntry + ", " + maxEntries;
		ArrayList<Engineer> enginnerList = new ListEngineer().list("*", condition);
		
		for (Engineer staff : enginnerList) {
			String data[] = { Integer.toString(staff.getStaff_id()),staff.getStaff_name(),staff.getStaff_DOB(),staff.getStaff_gender(),staff.getStaff_address(), staff.getDivision_name() ,staff.getPosition_name(),Integer.toString(staff.getStaff_startYearofwork())};
	        model.addRow(data);
		}
	}
	
	public void refreshTableStaff(DefaultTableModel model, Integer maxEntries, Integer startEntry) {
		model.setRowCount(0);
		startRow = startEntry;
		rowCount = getRowCountFromTableStaff();
		rowsLimit = 10;
		maxPage = Math.round(rowCount/rowsLimit + 1);
		String condition = " INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id"
		 		 		 + " LIMIT " + startEntry + ", " + maxEntries;
		ArrayList<Engineer> enginnerList = new ListEngineer().list("*", condition);
		
		for (Engineer staff : enginnerList) {
			String data[] = { Integer.toString(staff.getStaff_id()),staff.getStaff_name(),staff.getStaff_DOB(),staff.getStaff_gender(),staff.getStaff_address(), staff.getDivision_name() ,staff.getPosition_name(),Integer.toString(staff.getStaff_startYearofwork())};
	        model.addRow(data);
		}
	}
	
	public int getRowCountFromTableStaff(String query) {
		String condition = " INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id"
				 + " WHERE s.staff_id LIKE '%" + query +"%'"
		 		 + " OR s.staff_name LIKE '%" + query +"%'"
				 + " OR s.staff_gender LIKE '%" + query +"%'"
		 		 + " OR s.staff_DOB LIKE '%" + query +"%'"
		 		 + " OR s.staff_gender LIKE '%" + query +"%'"
		 		 + " OR s.staff_address LIKE '%" + query +"%'"
		 		 + " OR s.staff_salary LIKE '%" + query +"%'"
		 		 + " OR s.staff_startYearofwork LIKE '%" + query +"%'"
		 		 + " OR p.position_name LIKE '%" + query +"%'"
		 		 + " OR d.division_name LIKE '%" + query +"%'";
		ArrayList<Engineer> enginnerList = new ListEngineer().list("*", condition);
		return enginnerList.size();
	}
	
	public int getRowCountFromTableStaff() {
		String condition = " INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id";
		ArrayList<Engineer> enginnerList = new ListEngineer().list("*", condition);
		return enginnerList.size();
	}
	
	public void refreshTableStaff(DefaultTableModel model, String condition) {
		model.setRowCount(0);		
		ArrayList<Engineer> enginnerList = new ListEngineer().list("*", condition);
		for (Engineer staff : enginnerList) {
			String data[] = { Integer.toString(staff.getStaff_id()),staff.getStaff_name(),staff.getStaff_DOB(),staff.getStaff_gender(),staff.getStaff_address(), staff.getDivision_name() ,staff.getPosition_name(),Integer.toString(staff.getStaff_startYearofwork())};
	        model.addRow(data);
		}
	}
	
	
	
	public int getPageNumbers(Integer totalRows, Integer rowsperPage) {
		maxPage = Math.round(totalRows/rowsperPage + 1);
		return maxPage;
	}
	
	public int getPaginationNumbers(Integer totalRows, Integer rowsperPage) {
		int page = getPageNumbers(totalRows, rowsperPage);
		maxPagination = Math.round(page/5 + 1);
		return maxPagination;
	}
	
	
	public void setTextForButton(JButton page1, JButton page2, JButton page3, JButton page4, JButton page5, Integer currentPagination) {

			paginationPanel.revalidate();
			page1.setText(Integer.toString((currentPagination - 1) * 5 + 1));
			page2.setText(Integer.toString((currentPagination - 1) * 5 + 2));
			page3.setText(Integer.toString((currentPagination - 1) * 5 + 3));
			page4.setText(Integer.toString((currentPagination - 1) * 5 + 4));
			page5.setText(Integer.toString((currentPagination - 1) * 5 + 5));

			setTextForLabels();
			
			
			if(Integer.parseInt(page1.getText()) > maxPage) {
				page1.hide();
			}
			else page1.show();
			
			if(Integer.parseInt(page2.getText()) > maxPage) {
				page2.hide();
			}
			else page2.show();
			
			if(Integer.parseInt(page3.getText()) > maxPage) {
				page3.hide();
			}
			else page3.show();
			
			if(Integer.parseInt(page4.getText()) > maxPage) {
				page4.hide();
			}
			else page4.show();
			
			if(Integer.parseInt(page5.getText()) > maxPage) {
				page5.hide();
			}
			else page5.show();
			
	}
	
	public void reset() {
		page1.setText("1");
		page2.setText("2");
		page3.setText("3");
		page4.setText("4");
		page5.setText("5");
		page1.show();
		page2.show();
		page3.show();
		page4.show();
		page5.show();
		startRow = 0;	
		rowCount = 0;
		currentPage = 1;
		maxPagination = 1;
		maxPage = 1;
		currentPagination = 1;	
		lblFromPage.setText("1");
		lblToPage.setText("1");
		lbltotalPage.setText("1");
		
	}
	
	public void setTextForLabels() {
		lblFromPage.setText(String.valueOf(startRow + 1));
		
		lblToPage.setText(String.valueOf(startRow + rowsLimit));
		if(currentPage == maxPage) lblToPage.setText(String.valueOf(rowCount));
		
		lbltotalPage.setText(String.valueOf(rowCount));
		setBackgroundForButton();
	}
	
	public void setBackgroundForButton() {
		setOriginStyleButton(page1);
		setOriginStyleButton(page2);
		setOriginStyleButton(page3);
		setOriginStyleButton(page4);
		setOriginStyleButton(page5);
		
		int surplus = currentPage % 5;
		if(surplus == 0) surplus = 5;
		switch(surplus) {
			case 1: setStyleButton(page1); break;
			case 2: setStyleButton(page2); break;
			case 3: setStyleButton(page3); break;
			case 4: setStyleButton(page4); break;
			case 5: setStyleButton(page5); break;
		}
	}
	
	public void setStyleButton(JButton button) {
		button.setBackground(Color.PINK);
		button.setForeground(Color.white);
	}
	
	public void setOriginStyleButton(JButton button) {
		button.setBackground(null);
		button.setForeground(Color.black);
	}
	
}
