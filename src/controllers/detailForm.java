package controllers;

import java.util.ArrayList;

import javax.swing.*;    
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import database.ListStaff;
import model.Staff;

public class detailForm {
    JFrame f;
 
    detailForm() {
        f = new JFrame();
        DefaultTableModel model = new DefaultTableModel(); 
        JTable jt = new JTable(model);
        model.addColumn("ID"); 
        model.addColumn("NAME");
        model.addColumn("SALARY");
        ArrayList<Staff> staffList = new ListStaff().list("*"," WHERE 1=1");
		for (Staff staff : staffList) {
			String data[] = { Integer.toString(staff.getStaff_id()),staff.getStaff_name(),Integer.toString(staff.getStaff_salary())};
	        model.addRow(data);
		} 
		
        jt.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
 
        f.setSize(500, 300);
        f.setVisible(true);
    }
 
    public static void main(String[] args) {
        new detailForm();
    }
}