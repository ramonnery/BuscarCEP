package cep;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class Cep extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JLabel lblUf;
	private JComboBox cboUf;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
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
	public Cep() {
		setTitle("Buscar CEP");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/home.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CEP");
		lblNewLabel.setBounds(41, 27, 60, 17);
		contentPane.add(lblNewLabel);
		
		txtCep = new JTextField();
		txtCep.setBounds(74, 25, 99, 21);
		contentPane.add(txtCep);
		txtCep.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setBounds(12, 98, 60, 17);
		contentPane.add(lblEndereo);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(81, 96, 297, 21);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(12, 155, 60, 17);
		contentPane.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(57, 153, 165, 21);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);
		
		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(240, 155, 60, 17);
		contentPane.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(291, 153, 137, 21);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);
		
		lblUf = new JLabel("UF");
		lblUf.setBounds(12, 203, 60, 17);
		contentPane.add(lblUf);
		
		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cboUf.setBounds(41, 198, 83, 26);
		contentPane.add(cboUf);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(298, 198, 105, 27);
		contentPane.add(btnLimpar);
		
		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txtCep.requestFocus();
				}
				
				else {
					buscarCep();
				}
			}
		});
		btnCep.setBounds(217, 22, 105, 27);
		contentPane.add(btnCep);
		
		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setToolTipText("Sobre");
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/img/about.png")));
		btnSobre.setBorder(null);
		btnSobre.setBackground(new Color(238, 238, 238));
		btnSobre.setBounds(355, 12, 48, 48);
		contentPane.add(btnSobre);
		
		// Uso da lib Atx2k para validação do campo txtCep
		RestrictedTextField validar = new RestrictedTextField(txtCep);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(179, 24, 20, 20);
		contentPane.add(lblStatus);
		validar.setOnlyNums(true);
		validar.setLimit(8);
	}
	
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep="+ cep +"&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
		        Element element = it.next();
		        
		        if(element.getQualifiedName().equals("cidade")) {
		        	txtCidade.setText(element.getText());
		        }
		        
		        if(element.getQualifiedName().equals("bairro")) {
		        	txtBairro.setText(element.getText());
		        }
		        
		        if(element.getQualifiedName().equals("uf")) {
		        	cboUf.setSelectedItem(element.getText());
		        }
		        
		        if(element.getQualifiedName().equals("tipo_logradouro")) {
		        	tipoLogradouro = element.getText();
		        }
		        
		        if(element.getQualifiedName().equals("logradouro")) {
		        	logradouro = element.getText();
		        }
		        
		        if(element.getQualifiedName().equals("resultado")) {
		        	resultado  = element.getText();
		        	
		        	if (resultado.equals("1")) {
		        		lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
		        	}
		        	else JOptionPane.showMessageDialog(null, "CEP inválido");
		        }
			}
			
			// setar o campo endereço
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		}	
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	private void limpar() {
		txtCep.setText(null);
		txtCidade.setText(null);
		txtEndereco.setText(null);
		txtBairro.setText(null);
		cboUf.setSelectedItem(null);
		txtCep.requestFocus();
		lblStatus.setIcon(null);
	}
}
