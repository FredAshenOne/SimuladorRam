package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Manager extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel contentPane,mainPanel,processPane;
	private JTextField txtName;
	Font f = new Font("Yu Gothic UI Light", Font.PLAIN, 18);
	public List<Segmentos> lista = new ArrayList<Segmentos>();
	private JTextField textField;
	int choice;
	public Manager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 920, 450);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JPanel processMenu = new JPanel();
		processMenu.setBounds(10, 11, 143, 428);
		mainPanel.add(processMenu);
		processMenu.setLayout(null);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtName.setBounds(10, 108, 119, 32);
		processMenu.add(txtName);
		txtName.setColumns(10);
		TextPrompt phName = new TextPrompt("---",txtName,f,Color.GRAY);
		phName.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblHeader = new JLabel("Gestor de Procesos");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		lblHeader.setBounds(10, 11, 113, 20);
		processMenu.add(lblHeader);
		
		JButton btnContinue = new JButton("Continuar");
		btnContinue.setBounds(10, 361, 119, 23);
		processMenu.add(btnContinue);
		
		JLabel lblNombreDeProceso = new JLabel("<html><style>body{text-align:center:}</style><body>Nombre de proceso <br>(3 caracteres maximo)</body><html>");
		lblNombreDeProceso.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeProceso.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		lblNombreDeProceso.setBounds(10, 58, 123, 47);
		processMenu.add(lblNombreDeProceso);
		
		textField = new JTextField();
		textField.setBounds(10, 183, 119, 32);
		processMenu.add(textField);
		textField.setColumns(10);
		
		JLabel lblMemory = new JLabel("Espacio de memoria");
		lblMemory.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemory.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		lblMemory.setBounds(10, 151, 123, 32);
		processMenu.add(lblMemory);
		
		JLabel lblMemoryAlert = new JLabel("<html><style>body{text-align:center;}</style><body>Si no es multiplo  de 256 <br>se asignara al siguiente multiplo mayor</body></html>");
		lblMemoryAlert.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 10));
		lblMemoryAlert.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemoryAlert.setBounds(6, 215, 123, 47);
		processMenu.add(lblMemoryAlert);
		
		processPane = new JPanel();
		processPane.setBounds(163, 11, 900, 428);
		mainPanel.add(processPane);
		processPane.setLayout(null);
		
	
	}
	
	public void addProcess(int segmentoId,int procesoid,String a) {
		Iterator<Segmentos> iter  = lista.iterator();
		while(iter.hasNext()) {
			Segmentos s = iter.next();
			if(s.getId()==segmentoId) {
					
			}
		}
	}
}
