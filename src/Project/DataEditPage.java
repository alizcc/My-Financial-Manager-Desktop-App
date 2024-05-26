package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DataEditPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtDate;
	private JTextField txtCost;
	private JTextField txtDesc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataEditPage frame = new DataEditPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DataEditPage() throws SQLException {
		
		String url="jdbc:mysql://localhost:3306/my_manager";
		Connection con = DriverManager.getConnection(url, "root", "password");
		Statement st = con.createStatement();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter ID :");
		lblNewLabel.setForeground(new Color(119, 176, 247));
		lblNewLabel.setFont(new Font("Sathu", Font.BOLD, 18));
		lblNewLabel.setBounds(74, 47, 87, 21);
		contentPane.add(lblNewLabel);
		
		txtID = new JTextField();
		txtID.setBackground(new Color(204, 239, 254));
		txtID.setBounds(223, 41, 202, 35);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String deleteQuery = "delete from mycost where cid= '"+txtID.getText()+"'";
					st.executeUpdate(deleteQuery);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnDelete.setForeground(new Color(120, 176, 247));
		btnDelete.setBounds(445, 312, 87, 42);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = txtDate.getText();
				String desc = txtDesc.getText();
				int cost = Integer.valueOf(txtCost.getText());
				
				String updateQuery = "update mycost set cdate = '"+date.split("/")[0]+"',cdatefull = '"+date+"',ccost = '"+cost+"',cdesc = '"+desc+"' where cid = '"+txtID.getText()+"'";
				try {
					st.executeUpdate(updateQuery);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				
			}
		});
		btnUpdate.setForeground(new Color(120, 176, 247));
		btnUpdate.setBounds(348, 312, 87, 42);
		contentPane.add(btnUpdate);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setForeground(new Color(119, 176, 247));
		lblDate.setFont(new Font("Sathu", Font.BOLD, 18));
		lblDate.setBounds(74, 114, 87, 21);
		contentPane.add(lblDate);
		lblDate.setVisible(false);
		
		JLabel lblCost = new JLabel("Cost :");
		lblCost.setForeground(new Color(119, 176, 247));
		lblCost.setFont(new Font("Sathu", Font.BOLD, 18));
		lblCost.setBounds(74, 187, 87, 21);
		contentPane.add(lblCost);
		lblCost.setVisible(false);
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setForeground(new Color(119, 176, 247));
		lblDescription.setFont(new Font("Sathu", Font.BOLD, 18));
		lblDescription.setBounds(74, 261, 113, 21);
		contentPane.add(lblDescription);
		lblDescription.setVisible(false);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBackground(new Color(204, 239, 254));
		txtDate.setBounds(223, 108, 202, 35);
		contentPane.add(txtDate);
		txtDate.setVisible(false);

		txtCost = new JTextField();
		txtCost.setColumns(10);
		txtCost.setBackground(new Color(204, 239, 254));
		txtCost.setBounds(223, 181, 202, 35);
		contentPane.add(txtCost);
		txtCost.setVisible(false);

		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBackground(new Color(204, 239, 254));
		txtDesc.setBounds(223, 255, 202, 35);
		contentPane.add(txtDesc);
		txtDesc.setVisible(false);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtID, "Please Fill!");
				}
				else {
					lblDate.setVisible(true);
					lblCost.setVisible(true);
					lblDescription.setVisible(true);
					txtDate.setVisible(true);
					txtCost.setVisible(true);
					txtDesc.setVisible(true);
					
					try {
						String selectQuery = "select cdatefull,ccost,cdesc from mycost where cid= '"+txtID.getText()+"'";
						ResultSet rs = st.executeQuery(selectQuery);
						while(rs.next()) {
							txtDate.setText(rs.getString(1));
							txtCost.setText(Integer.toString(rs.getInt(2)));
							txtDesc.setText(rs.getString(3));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				

			}
		});
		btnFind.setForeground(new Color(120, 176, 247));
		btnFind.setBounds(445, 46, 87, 27);
		contentPane.add(btnFind);
	}
}
