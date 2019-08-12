package asgm;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.*;

import javax.swing.*;

public class insertForm extends JFrame implements ActionListener {
	private Connect connect = new Connect();
	private ResultSet rs;
	
	//button
	private JButton btnInsertButton = new JButton("Insert");
	
	//label
	private JLabel juduLabel = new JLabel("Product", JLabel.CENTER);
	private JLabel lblName = new JLabel("Name");
	private JLabel lblType = new JLabel("Type");
	private JLabel lblPrice = new JLabel("Price");
	private JLabel lblStock = new JLabel("Stock");
	
	//textfield
	private JTextField txtName = new JTextField();
	private JTextField txtType = new JTextField();
	private JTextField txtPrice = new JTextField();
	private JTextField txtStock = new JTextField();
	
	//spinner
	SpinnerModel modPrice = new SpinnerNumberModel(0, 0, 200000, 1);
	SpinnerModel modStock = new SpinnerNumberModel(0, 0, 100, 1);
	JSpinner spinPrice = new JSpinner(modPrice);
	JSpinner spinStock = new JSpinner(modStock);
	
	//combo
	JComboBox comboType = new JComboBox();
	
	//panel
	private JPanel jp = new JPanel(new BorderLayout());
	private JPanel isi = new JPanel(new GridLayout(4,2,5,5));
	private JPanel south = new JPanel(new FlowLayout());
	
	public insertForm() {
		setTitle("Insert");
		juduLabel.setFont(new Font("Tahoma", 0, 18));
		
		comboType.addItem("Drink");
		comboType.addItem("Food");
		
		isi.add(lblName);isi.add(txtName);
		isi.add(lblType);isi.add(comboType);
		isi.add(lblPrice);isi.add(spinPrice);
		isi.add(lblStock);isi.add(spinStock);
		
		south.add(btnInsertButton);
		jp.add(isi, BorderLayout.CENTER);
		jp.add(juduLabel, BorderLayout.NORTH);
		jp.add(south, BorderLayout.SOUTH);
		
		add(jp);
		setSize(300,250);
		//pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		btnInsertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actionPerformedInsert(e);
				
			}
		});
	}
	
	public void actionPerformedInsert(ActionEvent e) {
		int price = Integer.parseInt(spinPrice.getValue().toString());
		int stock = Integer.parseInt(spinStock.getValue().toString());
		if(txtName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please Fill Name!");
		}
		else if (price < 1 || price > 200000) {
			JOptionPane.showMessageDialog(null, "Price must be 1-200000!");
		}
		else if (stock < 1 || stock > 100) {
			JOptionPane.showMessageDialog(null, "Stock must be 1-100");
		}
		else {
			int pilih = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
			if(pilih == JOptionPane.YES_OPTION) {
				String name = txtName.getText();
				String type = comboType.getSelectedItem().toString();
				connect.executeUpdate("Update Product set Name='"+name+"',Type='"+type+"',Price='"+price+"',Stock='"+stock+"'");
			}
		}
	}
	
	public static void main(String[] args) {
		new insertForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
