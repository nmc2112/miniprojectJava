package controllers;

import java.awt.Color;
import java.awt.Font;
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
import database.ListAdmin;
import database.ListEngineer;
import model.Admin;
import model.AdminSession;
import model.Engineer;
import model.StaffSession;

public class listManagerFrame {
	
	private JPanel contentPane;
	private JTable table;
	private JTextField txtGender;
	JDialog dialog = new JDialog();
	private JTextField txtSearch;
	DefaultTableModel model = new DefaultTableModel(); 
	JLayeredPane layeredPane = new JLayeredPane();
	
	
	public void listManagerFrame(JPanel panelTable, int role_id, int ad_id) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 154, 1202, 512);
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
		model.addColumn("#"); 
        model.addColumn("Tên Đăng Nhập");
        model.addColumn("Email");
        model.addColumn("Quyền");
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        refreshTableManager(model);
		
        int[] columnsWidth = {
                (int) (scrollPane.getWidth() * 0.1), 
                (int) (scrollPane.getWidth() * 0.3), 
                (int) (scrollPane.getWidth() * 0.3), 
                (int) (scrollPane.getWidth() * 0.3), 
        };
            // Configures table's column width.
        int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = table.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
		detailForm.setCellsAlignment(table,SwingConstants.CENTER);
	
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH QUẢN LÝ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNewLabel.setBounds(477, 31, 302, 31);
		panelTable.add(lblNewLabel);		

        JButton btnAdd = new JButton(" THÊM");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(50, 205, 50));
		panelTable.setLayout(null);
		btnAdd.setIcon(new ImageIcon(detailForm.class.getResource("/assets/plus.png")));
		
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAdd.setBounds(47, 111, 113, 33);
		panelTable.add(btnAdd);
		
		JButton btnDelete = new JButton();
		btnDelete.setBackground(new Color(255, 69, 0));
		btnDelete.setIcon(new ImageIcon(detailForm.class.getResource("/assets/delete.png")));
		btnDelete.setText("XÓA");
		
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnDelete.setBounds(170, 111, 113, 33);
		panelTable.add(btnDelete);
		
		JButton btnReload = new JButton();
		btnReload.setBackground(Color.orange);
		btnReload.setIcon(new ImageIcon(detailForm.class.getResource("/assets/reload.png")));
		btnReload.setText("RELOAD");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 refreshTableManager(model);
			}
		});
		btnReload.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnReload.setBounds(295, 111, 123, 33);
		panelTable.add(btnReload);
		
		txtSearch = new JTextField();
		txtSearch.setHorizontalAlignment(SwingConstants.LEFT);
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtSearch.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = txtSearch.getText().toLowerCase();
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
		
		if(role_id == 1) {
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addManagerFrame newframe = new addManagerFrame();
					newframe.setVisible(true);
				}
			});
			
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
				        	   		sql  = "DELETE FROM tbladmin WHERE ad_id =" + id;
					            	try {
						    			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
						    			preparedStatement.execute();
						    		} catch (SQLException e) {
						    			// TODO Auto-generated catch block
						    			e.printStackTrace();
						    		}
		
					           }
				        	   refreshTableManager(model);
				           }
			           
			            	
			            }
			            else JOptionPane.showMessageDialog(contentPane, "Bạn chưa chọn ai để xóa");
			        }
								
				}
			});
		}
		else{
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(contentPane, "Bạn không có quyền này!");
				}
			});
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(contentPane, "Bạn không có quyền này!");
				}
			});
		}
		
		
	}
	
	public void refreshTableManager(DefaultTableModel model) {
		model.setRowCount(0);
		ArrayList<Admin> adminList = new ListAdmin().list("*"," INNER JOIN tblroles r ON r.role_id = a.role_id");
		for (Admin admin : adminList) {
			String data[] = { Integer.toString(admin.getAd_id()),admin.getAd_username(),admin.getAd_email(),admin.getRole_name()};
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
