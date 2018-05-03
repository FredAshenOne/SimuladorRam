package principal;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	Style s = new Style();
	Manager m = new Manager();
	private JPanel contentPane;
	JTextField txtGigas;
	JButton btnContinue;
	JLabel lblWarning;

	
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 248, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 232, 218);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblHeader = new JLabel("Simulador RAM");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		lblHeader.setBounds(0, 0, 232, 35);
		mainPanel.add(lblHeader);
		
		btnContinue = new JButton("Continuar");
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				s.btnPointer(btnContinue);
				s.btnHover(btnContinue, Color.WHITE, Color.decode("#00796B"), Color.decode("#00796B"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				s.mdBtn(btnContinue, Color.decode("#00796B"), Color.WHITE);
			}
		});
		btnContinue.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		btnContinue.setBounds(10, 170, 212, 35);
		mainPanel.add(btnContinue);
		
		txtGigas = new JTextField();
		txtGigas.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		txtGigas.setHorizontalAlignment(SwingConstants.CENTER);
		txtGigas.setBounds(10, 71, 212, 35);
		mainPanel.add(txtGigas);
		txtGigas.setColumns(10);
		
		lblWarning = new JLabel("");
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setFont(new Font("Yu Gothic UI Light", Font.ITALIC, 12));
		lblWarning.setBounds(10, 157, 212, 14);
		mainPanel.add(lblWarning);
		
		JLabel lblGigas = new JLabel("Ingrese la cantidad de memoria disponible");
		lblGigas.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		lblGigas.setHorizontalAlignment(SwingConstants.CENTER);
		lblGigas.setBounds(10, 46, 212, 22);
		mainPanel.add(lblGigas);
		
		JComboBox cbOrder = new JComboBox();
		cbOrder.setModel(new DefaultComboBoxModel(new String[] {"", "Best Fit", "Worst Fit", "First Fit"}));
		cbOrder.setFont(new Font("Yu Gothic Light", Font.PLAIN, 12));
		cbOrder.setBounds(10, 111, 212, 35);
		mainPanel.add(cbOrder);
		cbOrder.setBorder(null);
		cbOrder.setBackground(Color.white);
		
		btnContinue.addActionListener(this);
		s.mdPanel(mainPanel, Color.WHITE);
		s.mdBtn(btnContinue,Color.decode("#00796B"), Color.WHITE);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnContinue) {
			m.setVisible(true);
			this.setVisible(false);
		}
	}
}
