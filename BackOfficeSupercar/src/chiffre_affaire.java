import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class chiffre_affaire {

	private JFrame frame;
	private JTextField txtdate;
	private String date;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chiffre_affaire window = new chiffre_affaire();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;

	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root", "");
		} catch (ClassNotFoundException ex) {

		} catch (SQLException ex) {

		}
	}

	public void table_load(String type) {
		if (type == "jour") {
			try {
				pst = con.prepareStatement("select SUM(prix * quantite) AS Total from vendeur_commande where (date) = (?)");
				pst.setString(1, date);
				rs = pst.executeQuery();
				if (!rs.isBeforeFirst()) {
					JOptionPane.showMessageDialog(null, "aucune données trouvé pour la date selectionée !");
					txtdate.setText("");
				} else {
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (type == "mois") {
			try {
				pst = con.prepareStatement(
						"select SUM(prix * quantite) AS Total from vendeur_commande where month(date) = MONTH(?) and year(date) = year(?)");
				pst.setString(1, date);
				pst.setString(2, date);
				rs = pst.executeQuery();
				if (!rs.isBeforeFirst()) {
					JOptionPane.showMessageDialog(null, "aucune données trouvé pour la date selectionée !");
					txtdate.setText("");
				} else {
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (type == "annee") {
			try {
				pst = con.prepareStatement("select SUM(prix * quantite) AS Total from vendeur_commande where year(date) = year(?)");
				pst.setString(1, date);
				rs = pst.executeQuery();
				if (!rs.isBeforeFirst()) {
					JOptionPane.showMessageDialog(null, "aucune données trouvé pour la date selectionée !");
					txtdate.setText("");
				} else {
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * Create the application.
	 */
	public chiffre_affaire() {
		Connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblChiifreaffaire = new JLabel("Chiffre_affaire");
		lblChiifreaffaire.setFont(new Font("Arial", Font.BOLD, 20));
		lblChiifreaffaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiifreaffaire.setBounds(215, 10, 216, 41);
		frame.getContentPane().add(lblChiifreaffaire);
		
		JLabel lbldate = new JLabel("Entrez une date(aaaa/mm/jj)");
		lbldate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbldate.setBounds(37, 72, 240, 41);
		frame.getContentPane().add(lbldate);
		
		txtdate = new JTextField();
		txtdate.setBounds(343, 72, 170, 41);
		frame.getContentPane().add(txtdate);
		txtdate.setColumns(10);
		
		JLabel lblchoix = new JLabel("Faites votre choix");
		lblchoix.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblchoix.setBounds(25, 152, 159, 41);
		frame.getContentPane().add(lblchoix);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(254, 154, 170, 41);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("jour");
		comboBox.addItem("mois");
		comboBox.addItem("annee");
		
		JButton btnAfficher = new JButton("Afficher");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				date = txtdate.getText();
				table_load(comboBox.getSelectedItem().toString());
			}
		});
		btnAfficher.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnAfficher.setBounds(254, 264, 134, 41);
		frame.getContentPane().add(btnAfficher);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnExit.setBounds(280, 346, 85, 41);
		frame.getContentPane().add(btnExit);
	}
}
