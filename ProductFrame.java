package asgm;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public final class ProductFrame extends JFrame {

	// table
	private JScrollPane scrollPane = new JScrollPane();
	private JTable table = new JTable();
	private DefaultTableModel dtm = new DefaultTableModel(new Object[]{ "ID", "Name", "Type", "Price", "Stock" }, 0);

	// label
	private JLabel titleLabel = new JLabel("Product", JLabel.CENTER);

	// connection
	private Connect conn = new Connect();
	private ResultSet rs = null;
	private ResultSetMetaData rsm = null;
	private Vector<String> headerTable = new Vector<String>();
	private Vector<Vector<String>> dataTable = new Vector<Vector<String>>();

	public ProductFrame() {
		createForm();
		table.setModel(dtm);
		scrollPane.setViewportView(table);
		this.add(titleLabel, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);

		titleLabel.setFont(new Font("Tahoma", 0, 18));

		refreshData();

		setVisible(true);
	}

	public void refreshData() {
		// lengkapi code disini
		int x = dtm.getRowCount();
		for (int i = 0; i < x; i++) {
			dtm.removeRow(0);
		}
		try {
			for (int i = 1; i <= rsm.getColumnCount(); i++) {
				headerTable.add(rsm.getColumnName(i));
				dtm.addRow(headerTable);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		rs = conn.executeQuery("SELECT * FROM Product");
		Vector<String> vector = new Vector<String>();
		try {
			while (rs.next()) {
				vector = new Vector<String>();
				vector.add(rs.getString(1));
				vector.add(rs.getString(2));
				vector.add(rs.getString(3));
				vector.add(rs.getString(4));
				vector.add(rs.getString(5));
				dtm.addRow(vector);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void createForm() {
		this.setTitle("Product Form");
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new ProductFrame();
	}
}
