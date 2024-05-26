package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public int checkDatabase() throws SQLException {
		String url="jdbc:mysql://localhost:3306/my_manager";
		Connection con=DriverManager.getConnection(url, "root", "password");
		Statement st = con.createStatement();
	 	int count = 0;
		//Check Empty
		String checkDB = "Select * from mycost";
		ResultSet rs=st.executeQuery(checkDB);
		while(rs.next()) {
			count++;
		}
		return count;
	}

	public MainPage() throws SQLException {
		
		String url="jdbc:mysql://localhost:3306/my_manager";
		Connection con=DriverManager.getConnection(url, "root", "password");
		Statement st = con.createStatement();
		
		setTitle("My Budget");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 410);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(254, 255, 255));
		contentPane.setBackground(new Color(119, 176, 247));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Total Budget for ");
		lblNewLabel.setFont(new Font("Sathu", Font.BOLD, 16));
		lblNewLabel.setBounds(72, 36, 126, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lbMonth = new JLabel("(month)");
		lbMonth.setFont(new Font("Sathu", Font.BOLD | Font.ITALIC, 16));
		lbMonth.setBounds(200, 36, 80, 31);
		contentPane.add(lbMonth);
		
		JLabel lbMonth_1 = new JLabel(" : ");
		lbMonth_1.setFont(new Font("Sathu", Font.BOLD, 16));
		lbMonth_1.setBounds(290, 36, 21, 31);
		contentPane.add(lbMonth_1);
		
		JLabel lbBudget = new JLabel("(Budget)");
		lbBudget.setFont(new Font("Sathu", Font.BOLD, 16));
		lbBudget.setBounds(318, 36, 80, 31);
		contentPane.add(lbBudget);
		
		int total = 0;
		String bSelectQuery = "select * from mybudget";
		ResultSet brs = st.executeQuery(bSelectQuery);
		while(brs.next()) {
			lbMonth.setText(brs.getString(2));
			lbBudget.setText(Integer.toString(brs.getInt(3)));
			total = brs.getInt(3);
		}
		int cost = 0;
		try {
			String selectQuery = "select * from mycost";
			ResultSet rs = st.executeQuery(selectQuery);
			while(rs.next()) {
				cost += rs.getInt(4);
			}
			total -= cost;
			lbBudget.setText(String.valueOf(total));					

			//con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		JButton btnBudgetEdit = new JButton("Edit");
		btnBudgetEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				String bDeleteQuery = "delete from mybudget";
				try {
					st.executeUpdate(bDeleteQuery);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JTextField monthField = new JTextField(20);
			    JTextField budgetField = new JTextField(20);
			    JPanel myPanel = new JPanel();
			    myPanel.add(new JLabel("Month: "));
			    myPanel.add(monthField);
			    //myPanel.add(Box.createVerticalStrut(50)); // a spacer
			    myPanel.add(new JLabel("Budget: "));
			    myPanel.add(budgetField);
			    int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Month & Budget",JOptionPane.OK_CANCEL_OPTION);
			    if(result == JOptionPane.OK_OPTION) {
			    	if(monthField.getText().isEmpty() || budgetField.getText().isEmpty()) {
			    		JOptionPane.showMessageDialog(myPanel, "Please Fill Completely!");
			    	}
			    	else {
			    		lbMonth.setText(monthField.getText());
					    lbBudget.setText(budgetField.getText());
					    
					    try {
							Statement st = con.createStatement();
							String insertQuery= "insert into mybudget(bmonth, bbudget) values ('"+monthField.getText()+"', '"+budgetField.getText()+"')";
							//Execute Query
							st.executeUpdate(insertQuery);
							//con.close();
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	}
			    	
			    }  
			}
		});
		btnBudgetEdit.setFont(new Font("Sathu", Font.BOLD, 13));
		btnBudgetEdit.setBackground(new Color(64, 191, 241));
		btnBudgetEdit.setBounds(434, 31, 80, 36);
		contentPane.add(btnBudgetEdit);
		
		
		JButton btnAddCosts = new JButton("Add Costs");
		btnAddCosts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCostPage addCostPage = new AddCostPage();
				addCostPage.setVisible(true);
			}
		});
		btnAddCosts.setFont(new Font("Sathu", Font.BOLD, 13));
		btnAddCosts.setBackground(new Color(64, 191, 241));
		btnAddCosts.setBounds(400, 316, 114, 36);
		contentPane.add(btnAddCosts);
		
		JButton btnShowDetails = new JButton("Show Details");
		btnShowDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetailsPage detailsPage;
				try {
					detailsPage = new DetailsPage();
					detailsPage.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowDetails.setFont(new Font("Sathu", Font.BOLD, 13));
		btnShowDetails.setBackground(new Color(64, 191, 241));
		btnShowDetails.setBounds(400, 119, 114, 36);
		contentPane.add(btnShowDetails);
		
		JTextArea tAreaCost = new JTextArea();
		tAreaCost.setEditable(false);
		tAreaCost.setFont(new Font("Sathu", Font.PLAIN, 15));
		tAreaCost.setBackground(new Color(153, 196, 249));
		tAreaCost.setBounds(82, 90, 300, 196);
		//contentPane.add(tAreaCost);
		tAreaCost.setVisible(false);
		//tAreaCost.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(tAreaCost);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(82, 90, 300, 196);
		contentPane.add(scrollPane);
		
		String tableData = "";
		String selectQuery = "select * from mycost";
		ResultSet rs = st.executeQuery(selectQuery);
		while(rs.next()) {
			tableData = tableData + "                    "+rs.getString(3)+ "          "+rs.getInt(4)+"\n";
		}
		tAreaCost.setText(tableData);
		//con.close();
				
		JLabel lbAddCostWarning = new JLabel("You haven't added any cost yet! Please Add...");
		lbAddCostWarning.setForeground(new Color(0, 13, 255));
		lbAddCostWarning.setFont(new Font("Sathu", Font.ITALIC, 17));
		lbAddCostWarning.setBounds(106, 143, 369, 64);
		contentPane.add(lbAddCostWarning);
		
		
		
		JButton btnReset = new JButton("Reset Data");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Are you sure want to DELETE all records?"));
			    int result = JOptionPane.showConfirmDialog(null, myPanel,"Confirmation Dialog",JOptionPane.YES_NO_OPTION);
			    if(result == 0) {
			    	String deleteQuery = "delete from mycost";
			    	String deleteQuery1 = "delete from mybudget";
			    	try {
						st.executeUpdate(deleteQuery);
						st.executeUpdate(deleteQuery1);
						
						lbMonth.setText("(month)");
						lbBudget.setText("0");
						
						if(checkDatabase()>0) {
							tAreaCost.setVisible(true);
							scrollPane.setVisible(true);
							btnShowDetails.setVisible(true);
							btnReset.setVisible(true);
							lbAddCostWarning.setVisible(false);

						}
						else {
							lbAddCostWarning.setVisible(true);
							tAreaCost.setVisible(false);
							scrollPane.setVisible(false);
							btnShowDetails.setVisible(false);
							btnReset.setVisible(false);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	
			    }
			}
		});
		btnReset.setFont(new Font("Sathu", Font.BOLD, 13));
		btnReset.setBackground(new Color(64, 191, 241));
		btnReset.setBounds(400, 228, 114, 36);
		contentPane.add(btnReset);
		System.out.println(checkDatabase());
		
		JButton btnReload = new JButton("Refresh");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int total = 0;
				String bSelectQuery = "select * from mybudget";
				
				
				String tableData = "";
				int cost = 0;
				try {
					ResultSet brs = st.executeQuery(bSelectQuery);
					while(brs.next()) {
						total = brs.getInt(3);
					}
					
					String selectQuery = "select * from mycost";
					ResultSet rs = st.executeQuery(selectQuery);
					while(rs.next()) {
						tableData = tableData + "                    "+rs.getString(3)+ "          "+rs.getInt(4)+"\n";
						cost += rs.getInt(4);
					}
					tAreaCost.setText(tableData);
					total -= cost;
					lbBudget.setText(String.valueOf(total));					
					
					
					if(checkDatabase()>0) {
						tAreaCost.setVisible(true);
						scrollPane.setVisible(true);
						btnShowDetails.setVisible(true);
						btnReset.setVisible(true);
						lbAddCostWarning.setVisible(false);

					}
					else {
						lbAddCostWarning.setVisible(true);
						tAreaCost.setVisible(false);
						scrollPane.setVisible(false);
						btnShowDetails.setVisible(false);
						btnReset.setVisible(false);
					}

					//con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnReload.setFont(new Font("Sathu", Font.BOLD, 13));
		btnReload.setBackground(new Color(64, 191, 241));
		btnReload.setBounds(302, 316, 80, 36);
		contentPane.add(btnReload);
		
		if(checkDatabase()>0) {
			tAreaCost.setVisible(true);
			scrollPane.setVisible(true);
			btnShowDetails.setVisible(true);
			btnReset.setVisible(true);
			lbAddCostWarning.setVisible(false);

		}
		else {
			lbAddCostWarning.setVisible(true);
			tAreaCost.setVisible(false);
			scrollPane.setVisible(false);
			btnShowDetails.setVisible(false);
			btnReset.setVisible(false);
		}
	}
}
