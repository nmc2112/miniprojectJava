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

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Icon;
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
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;

public class detailForm extends JFrame {
	private JPanel frame;
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
		frame = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(269, 158, 1243, 547);
		getContentPane().add(scrollPane);

		table = new JTable(model);
		model = (DefaultTableModel) table.getModel();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		model.addColumn("ID"); 
        model.addColumn("TÃªn");
        model.addColumn("NgÃ y Sinh");
        model.addColumn("Giá»›i TÃ­nh");
        model.addColumn("Ä�á»‹a Chá»‰");
        model.addColumn("LÆ°Æ¡ng");
        model.addColumn("Vá»‹ TrÃ­"); 
        model.addColumn("NÄƒm Báº¯t Ä�áº§u");
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        refreshTable(model);
		
		
		detailForm.setCellsAlignment(table,SwingConstants.CENTER);
	
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton();
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setBackground(new Color(50, 205, 50));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFrame newframe = new addFrame();
				newframe.setVisible(true);
			}
		});
		btnAdd.setIcon(new ImageIcon(detailForm.class.getResource("/assets/plus.png")));
		btnAdd.setText(" THÃŠM");
		
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(270, 115, 113, 33);
		getContentPane().add(btnAdd);
		
		
		JButton btnEdit = new JButton();
		btnEdit.setBackground(new Color(255, 255, 0));
	    btnEdit.setIcon(new ImageIcon(detailForm.class.getResource("/assets/edit.png")));
	    btnEdit.setText(" Sá»¬A");
	    btnEdit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit.setBounds(541, 115, 113, 33);
		getContentPane().add(btnEdit);
		
		JButton btnDelete = new JButton();
		btnDelete.setBackground(new Color(255, 69, 0));
		btnDelete.setIcon(new ImageIcon(detailForm.class.getResource("/assets/delete.png")));
		btnDelete.setText("XÃ“A");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRows() != null) {
//					
		           int[] selectedrows = table.getSelectedRows();
		           String id = null;
		           String sql = null;
		           Connection connection = (Connection) DBConnection.getConnection();
		           
		           if(selectedrows.length > 0) {
		        	   int response = JOptionPane.showConfirmDialog(null,
                        "Báº¡n cÃ³ cháº¯c muá»‘n xÃ³a?",
                        "Báº¡n Ä‘ang xÃ³a " + selectedrows.length + " ngÆ°á»�i nÃ y", 
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
			    			refreshTable(model);
			           }
		           
		            	
		            }
		            else JOptionPane.showMessageDialog(frame, "Báº¡n chÆ°a chá»�n ai Ä‘á»ƒ xÃ³a");
		        }
							
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(404, 115, 113, 33);
		getContentPane().add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("DANH SÃ�CH NHÃ‚N VIÃŠN");
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
		txtSearch.setBounds(1243, 115, 269, 33);
		getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("TÃ¬m Kiáº¿m");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(1126, 115, 107, 33);
		getContentPane().add(lblNewLabel_1);
		
	    
		
			
		
	}
	
	public void refreshTable(DefaultTableModel model) {
		model.setRowCount(0);
		ArrayList<Engineer> enginnerList = new ListEngineer().list("*"," INNER JOIN tblpositions p ON p.position_id = s.position_id ORDER BY staff_id");
		for (Engineer staff : enginnerList) {
			String data[] = { Integer.toString(staff.getStaff_id()),staff.getStaff_name(),staff.getStaff_DOB(),staff.getStaff_gender(),staff.getStaff_address(),Integer.toString(staff.getStaff_salary()) + " triá»‡u",staff.getPosition_name(),Integer.toString(staff.getStaff_startYearofwork())};
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
