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
import database.ListEngineer;
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

public class layout extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtGender;
	JDialog dialog = new JDialog();
	private JTextField txtSearch;
	DefaultTableModel model = new DefaultTableModel(); 
	JLayeredPane layeredPane = new JLayeredPane();
	
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
		
		layeredPane.setBounds(259, 10, 1258, 836);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panelTable = new JPanel();
		panelTable.setBackground(new Color(255, 255, 255));
		layeredPane.add(panelTable, "detailForm");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 154, 1202, 547);
		panelTable.add(scrollPane);

		table = new JTable(model);
		model = (DefaultTableModel) table.getModel();
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(32, 136, 203));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.setRowHeight(25);
		table.setSelectionBackground(Color.pink);
		model.addColumn("ID"); 
        model.addColumn("Tên");
        model.addColumn("Ngày Sinh");
        model.addColumn("Giới Tính");
        model.addColumn("Địa Chỉ");
        model.addColumn("Lương");
        model.addColumn("Vị Trí"); 
        model.addColumn("Năm Bắt Đầu");
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        refreshTable(model);
		
		
		detailForm.setCellsAlignment(table,SwingConstants.CENTER);
	
		scrollPane.setViewportView(table);
		

        JButton btnAdd = new JButton(" THÊM");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(50, 205, 50));
		if(role_id == 1) {
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addFrame newframe = new addFrame();
					newframe.setVisible(true);
				}
			});
		}
		else{
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(contentPane, "Bạn không có quyền này!");
				}
			});
//			setVisible(false);
		}
		panelTable.setLayout(null);
		btnAdd.setIcon(new ImageIcon(detailForm.class.getResource("/assets/plus.png")));
		
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAdd.setBounds(47, 111, 113, 33);
		panelTable.add(btnAdd);
		
		
		JButton btnEdit = new JButton();
		btnEdit.setBackground(new Color(255, 255, 0));
	    btnEdit.setIcon(new ImageIcon(detailForm.class.getResource("/assets/edit.png")));
	    btnEdit.setText(" SỬA");
	    btnEdit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		StaffSession.cleanStaffSession();
	    		if (table.getSelectedRows() != null) {
		           int[] selectedrows = table.getSelectedRows();
		           String staff_id = null;
		           int position_id = 0;
		           String sql = null;
		           Connection connection = (Connection) DBConnection.getConnection();
		           
		           if(selectedrows.length == 1) {
		        	    staff_id = table.getValueAt(selectedrows[0], 0).toString();
	        	   		ArrayList<Engineer> enginnerList = new ListEngineer().list("position_id","WHERE staff_id = " + staff_id);
	        			for (Engineer staff : enginnerList) {
	        				position_id = staff.getPosition_id();
	        			}
	        	   		System.out.println(staff_id);
	        	   		StaffSession.getInstance(Integer.parseInt(staff_id), position_id);
	        	   		if(position_id == 1) {
	        	   			editWorkerFrame workerFrame = new editWorkerFrame();
	        	   			workerFrame.setVisible(true);
	        	   		}
	        	   		else {
	        	   			editEngineerFrame engineerFrame = new editEngineerFrame(); 
	        	   			engineerFrame.setVisible(true);
	        	   		}
		            }
		            else JOptionPane.showMessageDialog(contentPane, "Hãy chọn 1 người để sửa!");
			        }
								
			}
	    });
		btnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEdit.setBounds(170, 111, 113, 33);
		panelTable.add(btnEdit);
		
		JButton btnDelete = new JButton();
		btnDelete.setBackground(new Color(255, 69, 0));
		btnDelete.setIcon(new ImageIcon(detailForm.class.getResource("/assets/delete.png")));
		btnDelete.setText("XÓA");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRows() != null) {
		           int[] selectedrows = table.getSelectedRows();
		           String id = null;
		           String sql = null;
		           Connection connection = (Connection) DBConnection.getConnection();
		           
		           if(selectedrows.length > 0) {
		        	   int response = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc muốn xóa?",
                        "Bạn đang xóa " + selectedrows.length + " người này", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE); 
		           
			           if(response == 0) {
			        	   for (int i = 0; i < selectedrows.length; i++) {
			        	   		id = table.getValueAt(selectedrows[i], 0).toString();
			        	   		System.out.println(id);
			        	   		sql  = "DELETE FROM tblstaffs WHERE staff_id =" + id;
				            	try {
					    			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
					    			preparedStatement.execute();
					    		} catch (SQLException e) {
					    			// TODO Auto-generated catch block
					    			e.printStackTrace();
					    		}
	
				           }
			    			refreshTable(model);
			           }
		           
		            	
		            }
		            else JOptionPane.showMessageDialog(contentPane, "Bạn chưa chọn ai để xóa");
		        }
							
			}
		});
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnDelete.setBounds(295, 111, 113, 33);
		panelTable.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH NHÂN VIÊN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNewLabel.setBounds(477, 31, 302, 31);
		panelTable.add(lblNewLabel);
		
		txtSearch = new JTextField();
		txtSearch.setHorizontalAlignment(SwingConstants.LEFT);
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtSearch.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = txtSearch.getText().toLowerCase();
				System.out.println(query);
				filter(query);
			}
		});
		txtSearch.setBounds(970, 107, 278, 33);
		panelTable.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("TÌM KIẾM");
		lblNewLabel_1.setIcon(new ImageIcon(layout.class.getResource("/assets/search.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(865, 107, 95, 33);
		panelTable.add(lblNewLabel_1);
		
		JPanel panelAccount = new JPanel();
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
	            Color color1 = new Color(153,0,153);
	            Color color2 = new Color(0,204,204);
	            GradientPaint gp = new GradientPaint(0,0,color1,180,getHeight(),color2);
	            g2d.setPaint(gp);
	            g2d.fillRect(0, 0, getWidth(), getHeight()); 

	        }

	    };
	    panel.setBorder(new MatteBorder(0,0,0,2,Color.BLACK));
	    panel.setBounds(0, 0, 259, 811);
		contentPane.add(panel);
		panel.setLayout(null);
	}
	public void refreshTable(DefaultTableModel model) {
		model.setRowCount(0);
		ArrayList<Engineer> enginnerList = new ListEngineer().list("*"," INNER JOIN tblpositions p ON p.position_id = s.position_id ORDER BY staff_id");
		for (Engineer staff : enginnerList) {
			String data[] = { Integer.toString(staff.getStaff_id()),staff.getStaff_name(),staff.getStaff_DOB(),staff.getStaff_gender(),staff.getStaff_address(),Integer.toString(staff.getStaff_salary()) + " triệu",staff.getPosition_name(),Integer.toString(staff.getStaff_startYearofwork())};
	        model.addRow(data);
		}
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
