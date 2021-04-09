import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterFournisseur {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void NewFournisseur() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterFournisseur window = new InterFournisseur();
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
	public InterFournisseur() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	private JTextField txtNom;
	private JTextField txtAdresse;
	private JTextField txtEmail;
	private JTextField txtPortable;
	private JTextField txtType;
	private JTextField txtQuantite;
	private JTextField txtQuotation;
	private JTextField txtTaux;
	private JTextField txtPaiement;
	private JTextField txtid;
	private JTable table;
	
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

                 pst = con.prepareStatement("select * from comptable_fournisseur");

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
		frame.setBounds(100, 100, 1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFournisseur = new JLabel("Fournisseur");
		lblFournisseur.setForeground(Color.RED);
		lblFournisseur.setHorizontalAlignment(SwingConstants.CENTER);
		lblFournisseur.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblFournisseur.setBounds(464, 10, 236, 54);
		frame.getContentPane().add(lblFournisseur);
		
		JPanel panel_Form = new JPanel();
		panel_Form.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Form.setBounds(10, 70, 379, 489);
		frame.getContentPane().add(panel_Form);
		panel_Form.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNom.setBounds(10, 25, 61, 32);
		panel_Form.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setBounds(96, 28, 154, 32);
		panel_Form.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse :");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdresse.setBounds(10, 77, 76, 32);
		panel_Form.add(lblAdresse);
		
		txtAdresse = new JTextField();
		txtAdresse.setBounds(96, 80, 184, 32);
		panel_Form.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(10, 137, 69, 32);
		panel_Form.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(96, 140, 184, 32);
		panel_Form.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPortable = new JLabel("Portable :");
		lblPortable.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPortable.setBounds(10, 186, 76, 33);
		panel_Form.add(lblPortable);
		
		txtPortable = new JTextField();
		txtPortable.setBounds(96, 189, 184, 32);
		panel_Form.add(txtPortable);
		txtPortable.setColumns(10);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblType.setBounds(10, 241, 61, 32);
		panel_Form.add(lblType);
		
		txtType = new JTextField();
		txtType.setBounds(96, 244, 184, 32);
		panel_Form.add(txtType);
		txtType.setColumns(10);
		
		JLabel lblQuantite = new JLabel("Quantite :");
		lblQuantite.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQuantite.setBounds(10, 297, 90, 27);
		panel_Form.add(lblQuantite);
		
		txtQuantite = new JTextField();
		txtQuantite.setBounds(126, 290, 154, 32);
		panel_Form.add(txtQuantite);
		txtQuantite.setColumns(10);
		
		JLabel lblQuotation = new JLabel("Quotation :");
		lblQuotation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQuotation.setForeground(new Color(0, 0, 0));
		lblQuotation.setBounds(10, 347, 90, 27);
		panel_Form.add(lblQuotation);
		
		txtQuotation = new JTextField();
		txtQuotation.setBounds(126, 340, 154, 32);
		panel_Form.add(txtQuotation);
		txtQuotation.setColumns(10);
		
		JLabel lblTauxdevente = new JLabel("Taux_De_Vente :");
		lblTauxdevente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTauxdevente.setBounds(10, 396, 131, 27);
		panel_Form.add(lblTauxdevente);
		
		txtTaux = new JTextField();
		txtTaux.setBounds(154, 389, 161, 32);
		panel_Form.add(txtTaux);
		txtTaux.setColumns(10);
		
		JLabel lblPaiement = new JLabel("Paiement :");
		lblPaiement.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPaiement.setBounds(10, 433, 105, 32);
		panel_Form.add(lblPaiement);
		
		txtPaiement = new JTextField();
		txtPaiement.setBounds(126, 436, 154, 32);
		panel_Form.add(txtPaiement);
		txtPaiement.setColumns(10);
		
		JButton btnCalculer = new JButton("Calculer");
		btnCalculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                    double val1,val2,result;
				
				try {
					
					val1 = Double.parseDouble(txtQuotation.getText());
					val2 = Double.parseDouble(txtTaux.getText());
					
					result = val1 * val2;
					
					txtPaiement.setText(Double.toString(result));					
					
				}catch (Exception error) {
					JOptionPane.showMessageDialog(null,"Entrer une valeur numerique valide");
				}
			}
		});
		btnCalculer.setFont(new Font("Arial", Font.BOLD, 20));
		btnCalculer.setBounds(10, 582, 119, 33);
		frame.getContentPane().add(btnCalculer);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom = txtNom.getText();
				String adresse = txtAdresse.getText();
				String email = txtEmail.getText();
				String portable = txtPortable.getText();
				String type = txtType.getText();
				String quantite = txtQuantite.getText();
				String quotation = txtQuotation.getText();
				String taux_de_vente = txtTaux.getText();
				String paiement = txtPaiement.getText();
				 
				try {
					pst = con.prepareStatement("insert into comptable_fournisseur(nom,adresse,email,portable,type,Quantite,Quotation,Taux_De_Vente,Paiement)values(?,?,?,?,?,?,?,?,?)");
					pst.setString(1, nom);
					pst.setString(2, adresse);
					pst.setString(3, email);
					pst.setString(4, portable);
					pst.setString(5, type);
					pst.setString(6, quantite);
					pst.setString(7, quotation);
					pst.setString(8, taux_de_vente);
					pst.setString(9, paiement);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
						           
					txtNom.setText("");
					txtAdresse.setText("");
					txtEmail.setText("");
					txtPortable.setText("");
					txtType.setText("");
					txtQuantite.setText("");
					txtQuotation.setText("");
					txtTaux.setText("");
					txtPaiement.setText("");
					txtNom.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Arial", Font.BOLD, 20));
		btnSave.setBounds(168, 579, 101, 33);
		frame.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom,adresse,email,portable,type,quantite,quotation,taux_de_vente,paiement,id;
				
				 nom = txtNom.getText();
				 adresse = txtAdresse.getText();
				 email = txtEmail.getText();
				 portable = txtPortable.getText();
				 type = txtType.getText();
				 quantite = txtQuantite.getText();
				 quotation = txtQuotation.getText();
				 taux_de_vente = txtTaux.getText();
				 paiement = txtPaiement.getText();
				 id = txtid.getText();
				
				try {
					
				pst = con.prepareStatement("update comptable_fournisseur set nom = ?,adresse = ?,email = ?,portable = ?,type = ?,Quantite = ?,Quotation = ?,Taux_De_Vente = ?,Paiement = ? where id = ?");
				
				pst.setString(1, nom);
				pst.setString(2, adresse);
				pst.setString(3, email);
				pst.setString(4, portable);
				pst.setString(5, type);
				pst.setString(6, quantite);
				pst.setString(7, quotation);
				pst.setString(8, taux_de_vente);
				pst.setString(9, paiement);
				pst.setString(10, id);
				pst.executeUpdate();
				
				  JOptionPane.showMessageDialog(null, "Record Updated!");

                  table_load();

                
                  txtNom.setText("");
                  txtAdresse.setText("");
                  txtEmail.setText("");
                  txtPortable.setText("");
                  txtType.setText("");
                  txtQuantite.setText("");
                  txtQuotation.setText("");
                  txtTaux.setText("");
                  txtPaiement.setText("");
                  txtNom.requestFocus();
					
				}catch (SQLException e1) {

                    e1.printStackTrace();

      }
			}
		});
		btnEdit.setFont(new Font("Arial", Font.BOLD, 20));
		btnEdit.setBounds(303, 582, 85, 31);
		frame.getContentPane().add(btnEdit);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 647, 379, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Arial", Font.BOLD, 20));
		lblId.setBounds(10, 29, 50, 31);
		panel.add(lblId);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					 String id = txtid.getText();
					
					pst = con.prepareStatement("select nom,adresse,email,portable,type,Quantite,Quotation,Taux_De_Vente,Paiement from comptable_fournisseur where id=?");
					pst.setString(1, id);
                    ResultSet rs = pst.executeQuery();
					
					//////////table.setModel(DbUtils.resultSetToTableModel(rs));//////////////
					
					if(rs.next()==true)
					{
						String nom = rs.getString(1);
						String adresse = rs.getString(2);
						String email = rs.getString(3);
						String portable = rs.getString(4);
						String type = rs.getString(5);
						String quantite = rs.getString(6);
						String quotation = rs.getString(7);
						String taux_de_vente = rs.getString(8);
						String paiement = rs.getString(9);
						
						     txtNom.setText(nom);
		                     txtAdresse.setText(adresse);
		                     txtEmail.setText(email);
		                     txtPortable.setText(portable);
		                     txtType.setText(type);
		                     txtQuantite.setText(quantite);
		                     txtQuotation.setText(quotation);
		                     txtTaux.setText(taux_de_vente);
		                     txtPaiement.setText(paiement);
					}
					
					else {
						
						 txtNom.setText("");
	                     txtAdresse.setText("");
	                     txtEmail.setText("");
	                     txtPortable.setText("");
	                     txtType.setText("");
	                     txtQuantite.setText("");
	                     txtQuotation.setText("");
	                     txtTaux.setText("");
	                     txtPaiement.setText("");
						
					}
					
					
				} catch (SQLException ex) {
					
					
				}
			}
		});
		
		txtid.setBounds(80, 26, 105, 31);
		panel.add(txtid);
		txtid.setColumns(10);
		
		JButton lblDelete = new JButton("Delete");
		lblDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = txtid.getText();
				String nom = txtNom.getText();
				String adresse = txtAdresse.getText();
				String email = txtEmail.getText();
				String portable = txtPortable.getText();
				String type = txtType.getText();
				String quantite = txtQuantite.getText();
				String quotation = txtQuotation.getText();
				String taux_de_vente = txtTaux.getText();
				String paiement = txtPaiement.getText();
				
				try {
					
					pst = con.prepareStatement("delete from comptable_fournisseur where id =?");
					
					 pst.setString(1, id);

                     pst.executeUpdate();

                     JOptionPane.showMessageDialog(null, "Record Deleted!");

                     table_load();
                     
                     txtNom.setText("");
                     txtAdresse.setText("");
                     txtEmail.setText("");
                     txtPortable.setText("");
                     txtType.setText("");
                     txtQuantite.setText("");
                     txtQuotation.setText("");
                     txtTaux.setText("");
                     txtPaiement.setText("");
                     txtNom.requestFocus();
				}
				catch (SQLException e1) {                   

                    e1.printStackTrace();

       }
				
			}
		});
		lblDelete.setFont(new Font("Arial", Font.BOLD, 20));
		lblDelete.setBounds(530, 647, 101, 33);
		frame.getContentPane().add(lblDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 txtNom.setText("");
                 txtAdresse.setText("");
                 txtEmail.setText("");
                 txtPortable.setText("");
                 txtType.setText("");
                 txtQuantite.setText("");
                 txtQuotation.setText("");
                 txtTaux.setText("");
                 txtPaiement.setText("");
                 txtNom.requestFocus();
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear.setBounds(704, 647, 101, 33);
		frame.getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Arial", Font.BOLD, 20));
		btnExit.setBounds(876, 647, 101, 33);
		frame.getContentPane().add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(435, 72, 729, 540);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
	}
}
