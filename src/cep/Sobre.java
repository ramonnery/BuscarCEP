package cep;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sobre extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setResizable(false);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/home.png")));
		setBounds(160, 160, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar CEP - Versão 1.0");
		lblNewLabel.setBounds(30, 23, 162, 17);
		getContentPane().add(lblNewLabel);
		
		JLabel lblauthorRamonNery = new JLabel("@Author Ramon Nery");
		lblauthorRamonNery.setBounds(30, 52, 150, 17);
		getContentPane().add(lblauthorRamonNery);
		
		JLabel lblWebService = new JLabel("Web Service:");
		lblWebService.setBounds(30, 81, 82, 17);
		getContentPane().add(lblWebService);
		
		JLabel lblLinkWebService = new JLabel("republicavirtual.com.br");
		lblLinkWebService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				link("https://www.republicavirtual.com.br/");
				
			}
		});
		lblLinkWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLinkWebService.setForeground(new Color(28, 113, 216));
		lblLinkWebService.setBounds(116, 81, 150, 17);
		getContentPane().add(lblLinkWebService);
		
		JButton btnLinkedin = new JButton("");
		btnLinkedin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://www.linkedin.com/in/ramon-nery/");
			}
		});
		btnLinkedin.setBorder(null);
		btnLinkedin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLinkedin.setIcon(new ImageIcon(Sobre.class.getResource("/img/linkedin.png")));
		btnLinkedin.setToolTipText("LinkedIn");
		btnLinkedin.setBackground(new Color(238, 238, 238));
		btnLinkedin.setBounds(144, 160, 48, 48);
		getContentPane().add(btnLinkedin);
		
		JButton btnGithub = new JButton("");
		btnGithub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://github.com/ramonnery");
			}
		});
		btnGithub.setBorder(null);
		btnGithub.setIcon(new ImageIcon(Sobre.class.getResource("/img/github.png")));
		btnGithub.setToolTipText("Github");
		btnGithub.setBackground(new Color(238, 238, 238));
		btnGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGithub.setBounds(251, 160, 48, 48);
		getContentPane().add(btnGithub);

	}
	
	private void link(String site) {
		Desktop desktop = Desktop.getDesktop();
		
		try {
			URI uri = new URI(site);
			desktop.browse(uri);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
