import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rapport {

	private AdminAccount account = new AdminAccount();
	private JFrame frame;
	private String date;
	private String client;
	private String vendeur;
	private String marque;
	private String model;
	private String no_moteur;
	private String couleur;
	private String prix;
	@SuppressWarnings({ "rawtypes", "unused" })
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rapport window = new Rapport(login);
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
	public Rapport(String login) {
		Connect();
		initialize(login);
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtDate;
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
	
	
	
	public void table_load(String type) {
		
		if (type == "jour") {
			try {
				pst = con.prepareStatement("select * from comptable_vente where (date) = (?)");
				pst.setString(1, date);
				rs = pst.executeQuery();
				JFileChooser dialog = new JFileChooser();
				dialog.setSelectedFile(new File("_Vendeur"+".pdf"));
				int dialogResult = dialog.showSaveDialog(null);
				if (dialogResult==JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getPath();
					
					try {
						
						Document myDoc = new Document();
						@SuppressWarnings("unused")
						PdfWriter  myWriter = PdfWriter.getInstance(myDoc, new FileOutputStream(filePath));
						myDoc.open();
						
						myDoc.add(new Paragraph("Vendeur", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						
						myDoc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						
						myDoc.close();
						JOptionPane.showMessageDialog(null, "PDF Valider");
						
						
					}catch (FileNotFoundException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				catch(Exception er) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			
		}
				if (!rs.isBeforeFirst()) {
					JOptionPane.showMessageDialog(null, "aucune données trouvé pour la date selectionée !");
					txtDate.setText("");
				} else {
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (type == "mois") {			
			
			try {
				
				pst = con.prepareStatement("select * from comptable_vente where month(date) = MONTH(?) and year(date) = year(?)");
				pst.setString(1, date);
				pst.setString(2, date);
				rs = pst.executeQuery();
				JFileChooser dialog = new JFileChooser();
				dialog.setSelectedFile(new File("_Vendeur"+".pdf"));
				int dialogResult = dialog.showSaveDialog(null);
				if (dialogResult==JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getPath();
					
					try {
						
						Document myDoc = new Document();
						@SuppressWarnings("unused")
						PdfWriter  myWriter = PdfWriter.getInstance(myDoc, new FileOutputStream(filePath));
						myDoc.open();
						
						myDoc.add(new Paragraph("Vendeur", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						
						myDoc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						
						myDoc.close();
						JOptionPane.showMessageDialog(null, "PDF Valider");
						
						
					}catch (FileNotFoundException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				catch(Exception er) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			
		}
				
				if (!rs.isBeforeFirst()) {
					JOptionPane.showMessageDialog(null, "aucune données trouvé pour la date selectionée !");
					txtDate.setText("");
				} else {
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	if (type == "annee") {
		try {
			pst = con.prepareStatement("select * from comptable_vente where year(date) = year(?)");
			pst.setString(1, date);
			rs = pst.executeQuery();
			JFileChooser dialog = new JFileChooser();
			dialog.setSelectedFile(new File("_Vendeur"+".pdf"));
			int dialogResult = dialog.showSaveDialog(null);
			if (dialogResult==JFileChooser.APPROVE_OPTION) {
				String filePath = dialog.getSelectedFile().getPath();
				
				try {
					
					Document myDoc = new Document();
					@SuppressWarnings("unused")
					PdfWriter  myWriter = PdfWriter.getInstance(myDoc, new FileOutputStream(filePath));
					myDoc.open();
					
					myDoc.add(new Paragraph("Vendeur", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
					
					myDoc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
					
					myDoc.close();
					JOptionPane.showMessageDialog(null, "PDF Valider");
					
					
				}catch (FileNotFoundException | DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
			catch(Exception er) {
				JOptionPane.showMessageDialog(null, "Error");
			}
		
	}
			if (!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "aucune données trouvé pour la date selectionée !");
				txtDate.setText("");
			} else {
				table.setModel(DbUtils.resultSetToTableModel(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String login) {
		account.DatabaseConnexion(login, null, null, frame);
		System.out.print(account.getId());
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRapportDeVentes = new JLabel("Rapport de Ventes Des Voitures");
		lblRapportDeVentes.setFont(new Font("Arial", Font.BOLD, 21));
		lblRapportDeVentes.setHorizontalAlignment(SwingConstants.CENTER);
		lblRapportDeVentes.setBounds(167, 10, 354, 53);
		frame.getContentPane().add(lblRapportDeVentes);
		
		JLabel lblFaitesVotreChoix = new JLabel("Faites votre choix :");
		lblFaitesVotreChoix.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFaitesVotreChoix.setBounds(10, 172, 170, 36);
		frame.getContentPane().add(lblFaitesVotreChoix);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("jour");
		comboBox.addItem("mois");
		comboBox.addItem("annee");
		comboBox.setBounds(243, 175, 170, 36);
		frame.getContentPane().add(comboBox);
		
		JLabel lblDate = new JLabel("Entrez une date(aaaa/mm/j) :");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDate.setBounds(10, 96, 239, 46);
		frame.getContentPane().add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setBounds(285, 99, 161, 46);
		frame.getContentPane().add(txtDate);
		txtDate.setColumns(10);
		
		JButton btnAfficherMensuel = new JButton("Afficher Mensuel");
		btnAfficherMensuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				date = txtDate.getText();
				table_load(comboBox.getSelectedItem().toString());	
				
			}
		});
		btnAfficherMensuel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAfficherMensuel.setBounds(211, 372, 170, 46);
		frame.getContentPane().add(btnAfficherMensuel);
		
		JButton btnAfficherHebdomadaire = new JButton("Afficher Hebdomadaire");
		btnAfficherHebdomadaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				date = txtDate.getText();
				table_load(comboBox.getSelectedItem().toString());
			}
		});
		btnAfficherHebdomadaire.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAfficherHebdomadaire.setBounds(167, 302, 246, 46);
		frame.getContentPane().add(btnAfficherHebdomadaire);
		
		JButton btnJournalier = new JButton("Journalier");
		btnJournalier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				date = txtDate.getText();
				table_load(comboBox.getSelectedItem().toString());
				
			}
		});
		btnJournalier.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnJournalier.setBounds(211, 245, 170, 36);
		frame.getContentPane().add(btnJournalier);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Rapport'?",
						"Warning", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					Rapport.this.frame.setVisible(false);
					login_connection.main(login);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBounds(243, 441, 85, 36);
		frame.getContentPane().add(btnExit);
	}
}
