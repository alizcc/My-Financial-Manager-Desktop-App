package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddCostPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDate;
	private JTextField txtCost;
	private JTextField txtDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCostPage frame = new AddCostPage();
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
	public AddCostPage() {
		setTitle("Add Costs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date (dd/mm/yy)");
		lblNewLabel.setForeground(new Color(119, 176, 247));
		lblNewLabel.setFont(new Font("Sathu", Font.PLAIN, 15));
		lblNewLabel.setBounds(109, 57, 130, 33);
		contentPane.add(lblNewLabel);
		
		txtDate = new JTextField();
		txtDate.setBackground(new Color(204, 239, 254));
		txtDate.setBounds(253, 57, 182, 33);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		JLabel lblEnterCost = new JLabel("Cost : ");
		lblEnterCost.setForeground(new Color(119, 176, 247));
		lblEnterCost.setBackground(new Color(238, 238, 238));
		lblEnterCost.setFont(new Font("Sathu", Font.PLAIN, 15));
		lblEnterCost.setBounds(109, 126, 98, 33);
		contentPane.add(lblEnterCost);
		
		txtCost = new JTextField();
		txtCost.setBackground(new Color(204, 239, 254));
		txtCost.setColumns(10);
		txtCost.setBounds(253, 129, 182, 33);
		contentPane.add(txtCost);
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setForeground(new Color(119, 176, 247));
		lblDescription.setFont(new Font("Sathu", Font.PLAIN, 15));
		lblDescription.setBackground(UIManager.getColor("Button.background"));
		lblDescription.setBounds(109, 197, 98, 33);
		contentPane.add(lblDescription);
		
		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBackground(new Color(204, 239, 254));
		txtDescription.setBounds(253, 200, 182, 33);
		contentPane.add(txtDescription);
		
		JButton btnCostAdd = new JButton("Add");
		btnCostAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtDate.getText().isEmpty() || txtCost.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtCost, "Please Fill Completely!");
				}
				else {
					String date = txtDate.getText().split("/")[0];
					String desc = "";
					if(txtDescription.getText().isEmpty()) {
						desc = "no description";
					}
					else {
						desc = txtDescription.getText();
					}
					try {
						String url="jdbc:mysql://localhost:3306/my_manager";
						Connection con=DriverManager.getConnection(url, "root", "password");
						
						Statement st = con.createStatement();
						String insertQuery= "insert into mycost(cdate,cdatefull,ccost,cdesc) values('"+date+"', '"+txtDate.getText()+"', '"+txtCost.getText()+"', '"+desc+"')";
						//Execute Query
						st.executeUpdate(insertQuery);
						con.close();
						
						txtDate.setText("");
						txtCost.setText("");
						txtDescription.setText("");
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnCostAdd.setForeground(new Color(119, 176, 247));
		btnCostAdd.setBackground(new Color(119, 176, 247));
		btnCostAdd.setBounds(338, 272, 84, 33);
		contentPane.add(btnCostAdd);
		
		JButton btnCostClear = new JButton("Clear");
		btnCostClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDate.setText("");
				txtCost.setText("");
				txtDescription.setText("");
			}
			
		});
		btnCostClear.setForeground(new Color(119, 176, 247));
		btnCostClear.setBackground(new Color(119, 176, 247));
		btnCostClear.setBounds(231, 272, 84, 33);
		contentPane.add(btnCostClear);
		
		JButton btnCostBack = new JButton("Back");
		btnCostBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					setVisible(false);
			}
		});
		btnCostBack.setForeground(new Color(119, 176, 247));
		btnCostBack.setBackground(new Color(119, 176, 247));
		btnCostBack.setBounds(123, 272, 84, 33);
		contentPane.add(btnCostBack);
	}
}
