package controllers;

import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JComboBox;
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
	String querySearch = "";
	int rowsLimit = 10;
	int startRow = 0;
	int rowCount = 0;
	int currentPage = 1;
	int maxPagination = 1;
	int maxPage = 1;
	int currentPagination = 1;
	JPanel paginationPanel;
	JButton btnPrev;
	JButton btnNext;
	JButton page1;
	JButton page2;
	JButton page3;
	JButton page4;	
	JButton page5;
	JLabel lbltotalPage;
	JLabel lblFromPage;
	JLabel lblToPage;
	int initialRowCount;
	
	
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
        refreshTableStaff(model, " INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id ORDER BY s.staff_id DESC");
		
		
		detailForm.setCellsAlignment(table,SwingConstants.CENTER);
	
		scrollPane.setViewportView(table);
		

        JButton btnAdd = new JButton(" THÊM");
        btnAdd.setBounds(47, 111, 113, 33);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(50, 205, 50));
		btnAdd.setIcon(new ImageIcon(detailForm.class.getResource("/assets/plus.png")));
		
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelTable.add(btnAdd);
		if(role_id == 1 || role_id == 2) {
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addFrame newframe = new addFrame();
					newframe.setVisible(true);
				}
			});
		}
		else {
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(contentPane, "Bạn không có quyền này!");
				}
			});
		}
		
		
		
		JButton btnEdit = new JButton();
		btnEdit.setBounds(170, 111, 113, 33);
		btnEdit.setBackground(new Color(255, 255, 0));
	    btnEdit.setIcon(new ImageIcon(detailForm.class.getResource("/assets/edit.png")));
	    btnEdit.setText(" SỬA");
	    if(role_id == 1 || role_id == 2) {
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
		}
		else {
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(contentPane, "Bạn không có quyền này!");
				}
			});
		}
	    
		btnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelTable.add(btnEdit);
		
		JButton btnDelete = new JButton();
		btnDelete.setBounds(295, 111, 113, 33);
		btnDelete.setBackground(new Color(255, 69, 0));
		btnDelete.setIcon(new ImageIcon(detailForm.class.getResource("/assets/delete.png")));
		btnDelete.setText("XÓA");
		if(role_id == 1 || role_id == 2) {
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
				        	   		sql  = "DELETE FROM tblstaffs WHERE staff_id =" + id;
					            	try {
						    			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
						    			preparedStatement.execute();
						    		} catch (SQLException e) {
						    			// TODO Auto-generated catch block
						    			e.printStackTrace();
						    		}
		
					           }
				        	   refreshTableStaff(model, " INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id ORDER BY s.staff_id DESC");
				           }
			           
			            	
			            }
			            else JOptionPane.showMessageDialog(contentPane, "Bạn chưa chọn ai để xóa");
			        }
								
				}
			});
		}
		else {
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(contentPane, "Bạn không có quyền này!");
				}
			});
		}
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelTable.add(btnDelete);
		
		JButton btnReload = new JButton();
		btnReload.setBackground(Color.orange);
		btnReload.setIcon(new ImageIcon(detailForm.class.getResource("/assets/reload.png")));
		btnReload.setText("RELOAD");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 refreshTableStaff(model,10,0);
				 setTextForButton(page1, page2, page3, page4, page5, currentPagination);
			}
		});
		btnReload.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnReload.setBounds(420, 111, 123, 33);
		panelTable.add(btnReload);
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH NHÂN VIÊN");
		lblNewLabel.setBounds(477, 31, 302, 31);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
		panelTable.add(lblNewLabel);
		
		paginationPanel = new JPanel();
		paginationPanel.setBackground(Color.WHITE);
		paginationPanel.setBounds(667, 676, 581, 58);
		
		btnPrev = new JButton("Trước");
		btnPrev.setBackground(Color.WHITE);
		btnPrev.setBorder(null);
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage > 1) {
					currentPage--;
				}
				if(currentPage % 5 == 0) {
					currentPagination --;
					setTextForButton(page1, page2, page3, page4, page5, currentPagination);
				}
				int surplus = currentPage % 5;
				if(surplus == 0) surplus = 5;
				refreshTableStaff(model, querySearch, rowsLimit, (currentPagination - 1) * rowsLimit * 5 + rowsLimit * (surplus - 1));
				setTextForLabels();
				
				
			}
		});
		btnPrev.setIcon(new ImageIcon(layout.class.getResource("/assets/prev.png")));
		btnPrev.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		paginationPanel.add(btnPrev);
				
		
		JLabel lblHienThi = new JLabel("Hiển Thị");
		lblHienThi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHienThi.setBounds(646, 111, 64, 33);
		panelTable.add(lblHienThi);
		
		JComboBox rowscomboBox = new JComboBox();
		rowscomboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rowscomboBox.setBounds(711, 111, 64, 33);
		rowscomboBox.addItem("10");
		rowscomboBox.addItem("25");
		rowscomboBox.addItem("50");
		rowscomboBox.addItem("100");
		panelTable.add(rowscomboBox);
		rowscomboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
				reset();
            	rowsLimit = Integer.parseInt(rowscomboBox.getSelectedItem().toString());
				refreshTableStaff(model, querySearch, rowsLimit , startRow);
				rowCount = getRowCountFromTableStaff(querySearch);
				maxPage = Math.round(rowCount/rowsLimit + 1);
				maxPagination = getPaginationNumbers(rowCount, rowsLimit);
				setTextForButton(page1, page2, page3, page4, page5, currentPagination);
				
            }
		});
		
		
		JLabel lblDng = new JLabel("Dòng");
		lblDng.setHorizontalAlignment(SwingConstants.CENTER);
		lblDng.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDng.setBounds(773, 111, 64, 33);
		panelTable.add(lblDng);
		
		JLabel lblDangHienThi = new JLabel("Hiển thị từ dòng");
		lblDangHienThi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDangHienThi.setBounds(43, 673, 105, 33);
		panelTable.add(lblDangHienThi);
		
		lblFromPage = new JLabel("100");
		lblFromPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblFromPage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblFromPage.setBounds(148, 673, 30, 33);
		panelTable.add(lblFromPage);
		
		JLabel lblDenDong = new JLabel("đến dòng");
		lblDenDong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDenDong.setBounds(181, 673, 64, 33);
		panelTable.add(lblDenDong);
		
		lblToPage = new JLabel("100");
		lblToPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblToPage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblToPage.setBounds(245, 673, 30, 33);
		panelTable.add(lblToPage);
		
		
		JLabel lblNewLabel_1 = new JLabel("TÌM KIẾM");
		lblNewLabel_1.setIcon(new ImageIcon(layout.class.getResource("/assets/search.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(865, 107, 95, 33);
		

		panelTable.add(paginationPanel);
		paginationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		page1 = new JButton("1");
		page1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage = Integer.parseInt(page1.getText());
				startRow = (currentPagination - 1) * rowsLimit * 5;
				refreshTableStaff(model, querySearch, rowsLimit, startRow);
				setTextForLabels();
			}
		});
		page1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		paginationPanel.add(page1);
		
		page2 = new JButton("2");
		page2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage = Integer.parseInt(page2.getText());
				startRow = (currentPagination - 1) * rowsLimit * 5 + rowsLimit;
				refreshTableStaff(model, querySearch, rowsLimit, startRow);
				setTextForLabels();
			}
		});
		page2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		paginationPanel.add(page2);
		
		page3 = new JButton("3");
		page3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		page3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage = Integer.parseInt(page3.getText());
				startRow = (currentPagination - 1) * rowsLimit * 5 + rowsLimit * 2;
				refreshTableStaff(model, querySearch, rowsLimit, startRow);
				setTextForLabels();
			}
		});
		paginationPanel.add(page3);
		
		page4 = new JButton("4");
		page4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage = Integer.parseInt(page4.getText());
				startRow = (currentPagination - 1) * rowsLimit * 5 + rowsLimit * 3;
				refreshTableStaff(model, querySearch, rowsLimit, startRow);
				setTextForLabels();
			}
		});
		page4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		paginationPanel.add(page4);
		
		page5 = new JButton("5");
		page5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage = Integer.parseInt(page5.getText());
				startRow = (currentPagination - 1) * rowsLimit * 5 + rowsLimit * 4;
				refreshTableStaff(model, querySearch, rowsLimit, startRow);
				setTextForLabels();
			}
		});
		page5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		paginationPanel.add(page5);
		

		btnNext = new JButton("Sau");
		btnNext.setBackground(Color.WHITE);
		btnNext.setBorder(null);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPage < maxPage) {
					currentPage++;
				}
				if(currentPage % 5 == 1 && currentPage != 1) {
					currentPagination ++;
					setTextForButton(page1, page2, page3, page4, page5, currentPagination);
				}
				int surplus = currentPage % 5;
				if(surplus == 0) surplus = 5;
				refreshTableStaff(model, querySearch, rowsLimit, (currentPagination - 1) * rowsLimit * 5 + rowsLimit * (surplus - 1));
				setTextForLabels();
				
			}
		});
		btnNext.setIcon(new ImageIcon(layout.class.getResource("/assets/next.png")));
		btnNext.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		paginationPanel.add(btnNext);
		
		txtSearch = new JTextField();
		txtSearch.setHorizontalAlignment(SwingConstants.LEFT);
		txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtSearch.setBorder(new MatteBorder(0,0,2,0,Color.BLUE));
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				querySearch = txtSearch.getText().toLowerCase();
				if(querySearch != "") {
					reset();
					rowCount = getRowCountFromTableStaff(querySearch);
	            	rowsLimit = Integer.parseInt(rowscomboBox.getSelectedItem().toString());
					refreshTableStaff(model, querySearch, rowsLimit , startRow);
					maxPagination = getPaginationNumbers(rowCount, rowsLimit);
					maxPage = Math.round(rowCount/rowsLimit + 1);
					setTextForButton(page1, page2, page3, page4, page5, currentPagination);
					
					System.out.println("rowsLimit = " + rowsLimit);
					System.out.println("maxPagination = " + maxPagination);
					System.out.println("maxPage = " + maxPage);
				}
				else {
					setTextForButton(page1, page2, page3, page4, page5, currentPagination);
					refreshTableStaff(model, " INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id ORDER BY s.staff_id DESC");
				}
			}
		});
		txtSearch.setBounds(970, 107, 278, 33);
		panelTable.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("TÌM KIẾM");
		lblNewLabel_11.setIcon(new ImageIcon(layout.class.getResource("/assets/search.png")));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_11.setBounds(865, 107, 95, 33);
		panelTable.add(lblNewLabel_11);
		
		JLabel lblLcTTng = new JLabel("lọc từ tổng cộng");
		lblLcTTng.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblLcTTng.setBounds(278, 673, 113, 33);
		panelTable.add(lblLcTTng);
		
		lbltotalPage = new JLabel("100");
		lbltotalPage.setHorizontalAlignment(SwingConstants.CENTER);
		lbltotalPage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbltotalPage.setBounds(387, 673, 30, 33);
		panelTable.add(lbltotalPage);
		
		JLabel lblKtQu = new JLabel("kết quả");
		lblKtQu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblKtQu.setBounds(421, 673, 64, 33);
		panelTable.add(lblKtQu);
		

		refreshTableStaff(model, 10, 0);
		setTextForButton(page1, page2, page3, page4, page5, currentPagination);
	}
	
	public void refreshTableStaff(DefaultTableModel model) {
		model.setRowCount(0);
		ArrayList<Engineer> enginnerList = new ListEngineer().list("*"," INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id ORDER BY s.staff_id DESC");
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
				 		 + " ORDER BY s.staff_id DESC"
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
		 		 		  + " ORDER BY s.staff_id DESC" + " LIMIT " + startEntry + ", " + maxEntries;
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
		String condition = " INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id ORDER BY s.staff_id DESC";
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

			paginationPanel.revalidate();
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
