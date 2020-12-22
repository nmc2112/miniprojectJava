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
	
	
	//staff frame
	
	editEngineerFrame engineerFrame;
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

		//new listStaffFrame().listStaffFrame(panelTable, role_id, ad_id);
		
		//staff frame
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
        refreshTableStaff(model, " INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id");
		
		
		detailForm.setCellsAlignment(table,SwingConstants.CENTER);
	
		scrollPane.setViewportView(table);
		

        JButton btnAdd = new JButton(" THÊM");
        btnAdd.setBounds(47, 111, 113, 33);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(50, 205, 50));
		btnAdd.setIcon(new ImageIcon(detailForm.class.getResource("/assets/plus.png")));
		
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelTable.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFrame newframe = new addFrame();
				newframe.setVisible(true);
			}
		});
		
		
		JButton btnEdit = new JButton();
		btnEdit.setBounds(170, 111, 113, 33);
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
		panelTable.add(btnEdit);
		
		JButton btnDelete = new JButton();
		btnDelete.setBounds(295, 111, 113, 33);
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
			        	   refreshTableStaff(model, " INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id");
			           }
		           
		            	
		            }
		            else JOptionPane.showMessageDialog(contentPane, "Bạn chưa chọn ai để xóa");
		        }
							
			}
		});
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelTable.add(btnDelete);
		
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
				if(currentPage > 2) {
					
					currentPage--;
					if(currentPage % 5 == 0) {
						currentPagination --;
						setTextForButton(page1, page2, page3, page4, page5, currentPagination);
					}
					int surplus = currentPage % 5;
					if(surplus == 0) surplus = 5;
					refreshTableStaff(model, querySearch, rowsLimit, (currentPagination - 1) * rowsLimit * 5 + rowsLimit * (surplus - 1));
					setTextForLabels();
				}
				
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
				fromRow = (currentPagination - 1) * rowsLimit * 5;
				refreshTableStaff(model, querySearch, rowsLimit, fromRow);
				setTextForLabels();
			}
		});
		page1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		paginationPanel.add(page1);
		
		page2 = new JButton("2");
		page2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage = Integer.parseInt(page2.getText());
				fromRow = (currentPagination - 1) * rowsLimit * 5 + rowsLimit;
				refreshTableStaff(model, querySearch, rowsLimit, fromRow);
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
				fromRow = (currentPagination - 1) * rowsLimit * 5 + rowsLimit * 2;
				refreshTableStaff(model, querySearch, rowsLimit, fromRow);
				setTextForLabels();
			}
		});
		paginationPanel.add(page3);
		
		page4 = new JButton("4");
		page4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage = Integer.parseInt(page4.getText());
				fromRow = (currentPagination - 1) * rowsLimit * 5 + rowsLimit * 3;
				refreshTableStaff(model, querySearch, rowsLimit, fromRow);
				setTextForLabels();
			}
		});
		page4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		paginationPanel.add(page4);
		
		page5 = new JButton("5");
		page5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage = Integer.parseInt(page5.getText());
				fromRow = (currentPagination - 1) * rowsLimit * 5 + rowsLimit * 4;
				refreshTableStaff(model, querySearch, rowsLimit, fromRow);
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
				currentPage++;
				if(currentPage % 5 == 1) {
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
		txtSearch.setBounds(1003, 111, 245, 33);
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
				}
				else {
					setTextForButton(page1, page2, page3, page4, page5, currentPagination);
					refreshTableStaff(model, " INNER JOIN tblpositions p ON p.position_id = s.position_id INNER JOIN tbldivision d ON d.division_id = s.division_id");
				}
			}
		});
		panelTable.add(txtSearch);
		txtSearch.setColumns(10);
		
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
		
		System.out.println("currentPage " + currentPage);
		System.out.println("currentPagination " + currentPagination);
		System.out.println("startEntry " + startEntry);
		System.out.println("tableRows " + table.getRowCount());
		System.out.println("++++++++++++++++++++++++++++");
		
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
			System.out.println("page1Text = " + page1.getText());
			System.out.println("page2Text = " + page2.getText());
			System.out.println("page3Text = " + page3.getText());
			System.out.println("page4Text = " + page4.getText());
			System.out.println("page5Text = " + page5.getText());
			System.out.println("maxPage = " + maxPage);

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
		lbltotalPage.setText(String.valueOf(rowCount));
	}
}
