import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

public class Vente {

	private JFrame frame;
	private JTextField txtclient;
	private JTextField txtvendeur;
	private JTextField txtmarque;
	private JTextField txtmodel;
	private JTextField txtmoteur;
	private JTextField txtcouleur;
	private JTextField txtprix;
	private JTextField txtdate;
	private JTable table;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void NewVente() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vente window = new Vente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vente() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect() {
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
        }
        catch (ClassNotFoundException ex) 
        {
          ex.printStackTrace();
        }
        catch (SQLException ex) 
        {
        	   ex.printStackTrace();
        }
		
	}
	
	public void table_load()

    {

             try

             {

                 pst = con.prepareStatement("select * from comptable_vente");

                 rs = pst.executeQuery();
                 
                 table.setModel(DbUtils.resultSetToTableModel(rs));


             }

             catch (SQLException e)

              {

                           e.printStackTrace();

               }

    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblVente = new JLabel("Vente");
		lblVente.setForeground(Color.RED);
		lblVente.setFont(new Font("Arial Black", Font.BOLD, 33));
		lblVente.setHorizontalAlignment(SwingConstants.CENTER);
		lblVente.setBounds(459, 10, 156, 50);
		frame.getContentPane().add(lblVente);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 81, 330, 443);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblClient = new JLabel("Client :");
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblClient.setBounds(10, 20, 71, 32);
		panel.add(lblClient);
		
		txtclient = new JTextField();
		txtclient.setBounds(105, 20, 142, 32);
		panel.add(txtclient);
		txtclient.setColumns(10);
		
		JLabel lblVendeur = new JLabel("Vendeur :");
		lblVendeur.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVendeur.setBounds(10, 70, 84, 32);
		panel.add(lblVendeur);
		
		txtvendeur = new JTextField();
		txtvendeur.setBounds(117, 71, 142, 32);
		panel.add(txtvendeur);
		txtvendeur.setColumns(10);
		
		JLabel lblMarque = new JLabel("Marque :");
		lblMarque.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMarque.setBounds(10, 116, 71, 32);
		panel.add(lblMarque);
		
		txtmarque = new JTextField();
		txtmarque.setBounds(105, 119, 166, 32);
		panel.add(txtmarque);
		txtmarque.setColumns(10);
		
		JLabel lblModel = new JLabel("Model :");
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModel.setBounds(10, 168, 71, 32);
		panel.add(lblModel);
		
		txtmodel = new JTextField();
		txtmodel.setBounds(105, 168, 154, 32);
		panel.add(txtmodel);
		txtmodel.setColumns(10);
		
		JLabel lblNomoteur = new JLabel("No_Moteur :");
		lblNomoteur.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNomoteur.setBounds(10, 223, 102, 32);
		panel.add(lblNomoteur);
		
		txtmoteur = new JTextField();
		txtmoteur.setBounds(136, 226, 135, 32);
		panel.add(txtmoteur);
		txtmoteur.setColumns(10);
		
		JLabel lblCouleur = new JLabel("Couleur :");
		lblCouleur.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCouleur.setBounds(10, 276, 84, 25);
		panel.add(lblCouleur);
		
		txtcouleur = new JTextField();
		txtcouleur.setBounds(105, 274, 160, 32);
		panel.add(txtcouleur);
		txtcouleur.setColumns(10);
		
		JLabel lblPrix = new JLabel("Prix :");
		lblPrix.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrix.setBounds(10, 325, 64, 25);
		panel.add(lblPrix);
		
		txtprix = new JTextField();
		txtprix.setBounds(105, 324, 154, 32);
		panel.add(txtprix);
		txtprix.setColumns(10);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDate.setBounds(10, 373, 64, 32);
		panel.add(lblDate);
		
		txtdate = new JTextField();
		txtdate.setBounds(105, 376, 127, 32);
		panel.add(txtdate);
		txtdate.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String client = txtclient.getText();
				String vendeur = txtvendeur.getText();
				String marque = txtmarque.getText();
				String model = txtmodel.getText();
				String moteur = txtmoteur.getText();
				String couleur = txtcouleur.getText();
				String prix = txtprix.getText();
				String date = txtdate.getText();
				
				try {
					pst = con.prepareStatement("insert into comptable_vente(Client,Vendeur,Marque,Model,No_Moteur,Couleur,Prix,Date)values(?,?,?,?,?,?,?,?)");
					pst.setString(1, client);
					pst.setString(2, vendeur);
					pst.setString(3, marque);
					pst.setString(4, model);
					pst.setString(5, moteur);
					pst.setString(6, couleur);
					pst.setString(7, prix);
					pst.setString(8, date);
					pst.executeUpdate();
					FileWriter Writer = new FileWriter("RapportVente.txt",true);
					Writer.write(""+client+"  "+vendeur+" "+marque+" "+model+" "+moteur+" "+couleur+" "+prix+" "+date);
					Writer.write(System.getProperty("line.separator"));
					Writer.close();
					///////////setVisible(false);///////////////
					////////////////////new InsertData().setVisible(true);//////////////////
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
					
						           
					txtclient.setText("");
					txtvendeur.setText("");
					txtmarque.setText("");
					txtmodel.setText("");
					txtmoteur.setText("");
					txtcouleur.setText("");
					txtprix.setText("");
					txtdate.setText("");
					txtclient.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSave.setFont(new Font("Arial", Font.BOLD, 20));
		btnSave.setBounds(10, 554, 92, 33);
		frame.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String client,vendeur,marque,model,moteur,couleur,prix,date,id;
				
				 client = txtclient.getText();
				 vendeur = txtvendeur.getText();
				 marque = txtmarque.getText();
				 model = txtmodel.getText();
				 moteur = txtmoteur.getText();
				 couleur = txtcouleur.getText();
				 prix = txtprix.getText();
				 date = txtdate.getText();
				 id = txtid.getText();
				
				try {
					
					pst = con.prepareStatement("update comptable_vente set Client = ?,Vendeur = ?,Marque = ?,Model = ?,No_Moteur = ?,Couleur = ?,Prix = ?,Date = ? where id_vente = ?");
					
					pst.setString(1, client);
					pst.setString(2, vendeur);
					pst.setString(3, marque);
					pst.setString(4, model);
					pst.setString(5, moteur);
					pst.setString(6, couleur);
					pst.setString(7, prix);
					pst.setString(8, date);
					pst.setString(9, id);
					pst.executeUpdate();
					
					  JOptionPane.showMessageDialog(null, "Record Updated!");

	                  table_load();

	                
	                  txtclient.setText("");
	                  txtvendeur.setText("");
	                  txtmarque.setText("");
	                  txtmodel.setText("");
	                  txtmoteur.setText("");
	                  txtcouleur.setText("");
	                  txtprix.setText("");
	                  txtdate.setText("");
	                  txtclient.requestFocus();
						
					}catch (SQLException e1) {

	                    e1.printStackTrace();

	      }
				
			}
		});
		btnEdit.setFont(new Font("Arial", Font.BOLD, 20));
		btnEdit.setBounds(137, 554, 85, 33);
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtclient.setText("");
                txtvendeur.setText("");
                txtmarque.setText("");
                txtmodel.setText("");
                txtmoteur.setText("");
                txtcouleur.setText("");
                txtprix.setText("");
                txtdate.setText("");
                txtclient.requestFocus();
				
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear.setBounds(255, 554, 85, 33);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(372, 80, 804, 503);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 620, 330, 74);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblId = new JLabel("Id :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblId.setBounds(10, 25, 58, 24);
		panel_1.add(lblId);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					  String id = txtid.getText();
						
		                pst = con.prepareStatement("select Client,Vendeur,Marque,Model,No_Moteur,Couleur,Prix,Date from comptable_vente where id_vente = ?");
		                pst.setString(1, id);
	                    ResultSet rs = pst.executeQuery();
						
						//////////table.setModel(DbUtils.resultSetToTableModel(rs));///////////////////
						
						if(rs.next()==true)
						{
							String client = rs.getString(1);
							String vendeur = rs.getString(2);
							String marque = rs.getString(3);
							String model = rs.getString(4);
							String moteur = rs.getString(5);
							String couleur = rs.getString(6);
							String prix = rs.getString(7);
							String date = rs.getString(8);
							
							     txtclient.setText(client);
			                     txtvendeur.setText(vendeur);
			                     txtmarque.setText(marque);
			                     txtmodel.setText(model);
			                     txtmoteur.setText(moteur);
			                     txtcouleur.setText(couleur);
			                     txtprix.setText(prix);
			                     txtdate.setText(date);
			                    
						}
						
						else {
							
							txtclient.setText("");
			                txtvendeur.setText("");
			                txtmarque.setText("");
			                txtmodel.setText("");
			                txtmoteur.setText("");
			                txtcouleur.setText("");
			                txtprix.setText("");
			                txtdate.setText("");
							
						}
					
				} catch (SQLException ex) {
					
				}
			}
		});
		txtid.setBounds(99, 25, 96, 25);
		panel_1.add(txtid);
		txtid.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = txtid.getText();
				String client = txtclient.getText();
				String vendeur = txtvendeur.getText();
				String marque = txtmarque.getText();
				String model = txtmodel.getText();
				String moteur = txtmoteur.getText();
				String couleur = txtcouleur.getText();
				String prix = txtprix.getText();
				String date = txtdate.getText();
				
		try {
					
					pst = con.prepareStatement("delete from comptable_vente where id =?");
					
					 pst.setString(1, id);

                     pst.executeUpdate();

                     JOptionPane.showMessageDialog(null, "Record Deleted!");

                     table_load();
                     
                     txtclient.setText("");
                     txtvendeur.setText("");
                     txtmarque.setText("");
                     txtmodel.setText("");
                     txtmoteur.setText("");
                     txtcouleur.setText("");
                     txtprix.setText("");
                     txtdate.setText("");
                     txtclient.requestFocus();
				}
				catch (SQLException e1) {                   

                    e1.printStackTrace();

       }
				
			}
		});
		btnDelete.setFont(new Font("Arial", Font.BOLD, 18));
		btnDelete.setBounds(571, 620, 92, 33);
		frame.getContentPane().add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Arial", Font.BOLD, 18));
		btnExit.setBounds(772, 620, 85, 33);
		frame.getContentPane().add(btnExit);
	}
}
