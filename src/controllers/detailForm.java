package controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import database.DBConnection;
import database.ListPosition;
import database.ListEngineer;
import model.Engineer;
import model.Position;
import model.Staff;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class detailForm extends JFrame {
	private JFrame frame;
	private JTable table;
	private JTextField txtGender;
	JDialog dialog = new JDialog();
	private JTextField txtSearch;

	DefaultTableModel model = new DefaultTableModel(); 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					detailForm frame = new detailForm();
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
	
	 public static void setCellsAlignment(JTable table, int alignment)
    {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }
	
	public detailForm() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		getContentPane().setBackground(Color.WHITE);
		frame = new JFrame();
		frame.setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 158, 1485, 547);
		getContentPane().add(scrollPane);

		table = new JTable(model);
		model = (DefaultTableModel) table.getModel();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
		
		JButton btnAdd = new JButton("THÊM");
		
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(31, 107, 159, 41);
		getContentPane().add(btnAdd);
		
		
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addFrame newframe = new addFrame();
				newframe.setVisible(true);	
			}
		});
		
		JButton btnDelete = new JButton("XOÁ");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
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
		            else JOptionPane.showMessageDialog(frame, "Bạn chưa chọn ai để xóa");
		        }
				
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(212, 107, 159, 41);
		getContentPane().add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH NHÂN VIÊN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(630, 24, 320, 62);
		getContentPane().add(lblNewLabel);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query = txtSearch.getText().toLowerCase();
				System.out.println(query);
				filter(query);
			}
		});
		txtSearch.setBounds(1243, 107, 269, 41);
		getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm Kiếm");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(1126, 107, 107, 41);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnEdit = new JButton("SỬA");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit.setBounds(396, 107, 159, 41);
		getContentPane().add(btnEdit);
		
		
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
}
