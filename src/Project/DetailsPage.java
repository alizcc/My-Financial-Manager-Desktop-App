package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class DetailsPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailsPage frame = new DetailsPage();
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
	public DetailsPage() throws SQLException {
		setTitle("DetailsPage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 195, 249));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Sathu", Font.BOLD, 16));
		lblNewLabel.setBounds(69, 6, 54, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setFont(new Font("Sathu", Font.BOLD, 16));
		lblCost.setBounds(294, 6, 54, 46);
		contentPane.add(lblCost);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Sathu", Font.BOLD, 16));
		lblDescription.setBounds(420, 6, 118, 46);
		contentPane.add(lblDescription);
		
		JTextArea tAreaDetails = new JTextArea();
		tAreaDetails.setEditable(false);
		tAreaDetails.setBackground(new Color(153, 195, 249));
		tAreaDetails.setBounds(48, 50, 490, 219);
		contentPane.add(tAreaDetails);
		tAreaDetails.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(tAreaDetails);
		scrollPane.setBounds(48, 50, 490, 219);
		contentPane.add(scrollPane);
		
		String url="jdbc:mysql://localhost:3306/my_manager";
		Connection con=DriverManager.getConnection(url, "root", "password");
		Statement st = con.createStatement();
		String tableData = "";
		String selectQuery = "select * from mycost";
		ResultSet rs = st.executeQuery(selectQuery);
		while(rs.next()) {
			tableData = tableData +"    "+rs.getString(1)+"             "+rs.getString(3)+ "                     "+rs.getInt(4)+ "                     "+rs.getString(5)+"\n";
		}
		tAreaDetails.setText(tableData);
		
		
		
		JButton btnBackDetail = new JButton("Back");
		btnBackDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBackDetail.setFont(new Font("Sathu", Font.PLAIN, 13));
		btnBackDetail.setBounds(48, 277, 75, 34);
		contentPane.add(btnBackDetail);
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setFont(new Font("Sathu", Font.BOLD, 16));
		lblNewLabel_1.setBounds(152, 6, 54, 46);
		contentPane.add(lblNewLabel_1);
		
		JButton btnEditDetail = new JButton("Edit");
		btnEditDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DataEditPage editPage = new DataEditPage();
					editPage.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEditDetail.setFont(new Font("Sathu", Font.PLAIN, 13));
		btnEditDetail.setBounds(463, 277, 75, 34);
		contentPane.add(btnEditDetail);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tableData = "";
				String selectQuery = "select * from mycost";
				ResultSet rs;
				try {
					rs = st.executeQuery(selectQuery);
					while(rs.next()) {
						tableData = tableData +"    "+rs.getString(1)+"             "+rs.getString(3)+ "                     "+rs.getInt(4)+ "                     "+rs.getString(5)+"\n";
					}
					tAreaDetails.setText(tableData);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRefresh.setFont(new Font("Sathu", Font.PLAIN, 13));
		btnRefresh.setBounds(260, 277, 75, 34);
		contentPane.add(btnRefresh);
		
		
	}
}
