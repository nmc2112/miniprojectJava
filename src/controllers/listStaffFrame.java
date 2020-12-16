package controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import database.DBConnection;
import database.ListEngineer;
import model.Engineer;
import model.StaffSession;

public class listStaffFrame {
	
	private JPanel contentPane;
	private JTable table;
	private JTextField txtGender;
	JDialog dialog = new JDialog();
	private JTextField txtSearch;
	editEngineerFrame engineerFrame;
	DefaultTableModel model = new DefaultTableModel(); 
	JLayeredPane layeredPane = new JLayeredPane();
	
	
	public void listStaffFrame(JPanel panelTable, int role_id, int ad_id) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 154, 1202, 512);
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
        model.addColumn("Phòng");
        model.addColumn("Vị Trí"); 
        model.addColumn("Năm Bắt Đầu");
        table.setRowSelectionAllowed(true);
        
        int[] columnsWidth = {
                (int) (scrollPane.getWidth() * 0.05), 
                (int) (scrollPane.getWidth() * 0.15), 
                (int) (scrollPane.getWidth() * 0.1), 
                (int) (scrollPane.getWidth() * 0.1), 
                (int) (scrollPane.getWidth() * 0.15), 
                (int) (scrollPane.getWidth() * 0.25), 
                (int) (scrollPane.getWidth() * 0.1), 
                (int) (scrollPane.getWidth() * 0.1)
        };
            // Configures table's column width.
        int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = table.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        refreshTableStaff(model);
		
		
		detailForm.setCellsAlignment(table,SwingConstants.CENTER);
	
		scrollPane.setViewportView(table);
		

        JButton btnAdd = new JButton(" THÊM");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(50, 205, 50));
		panelTable.setLayout(null);
		btnAdd.setIcon(new ImageIcon(detailForm.class.getResource("/assets/plus.png")));
		
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAdd.setBounds(47, 111, 113, 33);
		panelTable.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFrame newframe = new addFrame();
				newframe.setVisible(true);
			}
		});
		
		
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
	        	   			editWorkerFrame workerFrame = null;
							try {
								workerFrame = new editWorkerFrame();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	        	   			workerFrame.setVisible(true);
	        	   		}
	        	   		else {
							try {
								engineerFrame = new editEngineerFrame();
		        	   			engineerFrame.setVisible(true);
							} catch (SQLException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
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
			        	   refreshTableStaff(model);
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
	}
	
	public void refreshTableStaff(DefaultTableModel model) {
		model.setRowCount(0);
		ArrayList<Engineer> enginnerList = new ListEngineer().list("*"," INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id");
		for (Engineer staff : enginnerList) {
			String data[] = { Integer.toString(staff.getStaff_id()),staff.getStaff_name(),staff.getStaff_DOB(),staff.getStaff_gender(),staff.getStaff_address(), staff.getDivision_name() ,staff.getPosition_name(),Integer.toString(staff.getStaff_startYearofwork())};
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
