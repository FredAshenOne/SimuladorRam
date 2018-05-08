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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Menu extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	Style s = new Style();
	Manager m = new Manager();
	private JPanel contentPane;
	JButton btnContinue;
	JLabel lblWarning;
	int gigas;
	Segmentos seg = new Segmentos();
	JComboBox cbGigas,cbOrder;
	String def = "___";
	int Width = 192;
	
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
		
		
		lblWarning = new JLabel("");
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setFont(new Font("Yu Gothic UI Light", Font.ITALIC, 12));
		lblWarning.setBounds(10, 146, 212, 27);
		mainPanel.add(lblWarning);
		
		JLabel lblGigas = new JLabel("<html><style>body{text-align:center;}</style><body>Ingrese la cantidad de memoria <br>disponible(MB)</body></html>");
		lblGigas.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		lblGigas.setHorizontalAlignment(SwingConstants.CENTER);
		lblGigas.setBounds(10, 33, 212, 35);
		mainPanel.add(lblGigas);
		
		cbOrder = new JComboBox<Object>();
		cbOrder.setModel(new DefaultComboBoxModel(new String[] {"", "Best Fit", "Worst Fit", "First Fit"}));
		cbOrder.setFont(new Font("Yu Gothic Light", Font.PLAIN, 12));
		cbOrder.setBounds(10, 111, 212, 35);
		mainPanel.add(cbOrder);
		cbOrder.setBorder(null);
		cbOrder.setBackground(Color.white);
		cbGigas = new JComboBox<Object>();
		cbGigas.setModel(new DefaultComboBoxModel(new String[] {"","1024","2048","3072","4096","6144","8192","16384","32768"}));
		cbGigas.setFont(new Font("Yu Gothic Light", Font.PLAIN, 12));
		cbGigas.setBounds(10, 71, 212, 35);
		mainPanel.add(cbGigas);
		cbGigas.setBorder(null);
		cbGigas.setBackground(Color.white);
		btnContinue.addActionListener(this);
		s.mdPanel(mainPanel, Color.WHITE);
		s.mdBtn(btnContinue,Color.decode("#00796B"), Color.WHITE);
		
		lblWarning.setForeground(Color.red);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnContinue) {
			if(cbOrder.getSelectedItem().toString().equals("")	|| cbGigas.getSelectedItem().toString().equals("")) {
				lblWarning.setText("Faltan Datos");
			}else {
				gigas = Integer.parseInt(cbGigas.getSelectedItem().toString()) / 1024;
				createSegmentos(gigas,m.processPane);
				int width = 397;
				if(gigas > 4 ) {
					width +=170;
					if(gigas > 8) {
						width +=170;
						if(gigas > 12) {
							width +=170;
						}
					}
							
				}
				m.setBounds(100,100,width,500);
				m.setVisible(true);
				this.setVisible(false);
			}
			m.choice = cbOrder.getSelectedIndex();
		}
	}
	
	
	public void createSegmentos(int gigas,JPanel pane) {
		int x = 10,y = 11;
		for(int i = 1;i<=gigas;i++) {
			Segmentos seg = new Segmentos();
			seg.setId(i);
			seg.setMemory(0);
			for(int k = 1;k < 5;k++) {
				Procesos p = new Procesos(k,def,"libre",256);
				switch(k) {
				case 1:
					seg.setP1(p);
					break;
				case 2:
					seg.setP2(p);
					break;
				case 3:
					seg.setP3(p);
					break;
				case 4:
					seg.setP4(p);
					break;				
				}
				seg.setMemory(seg.getMemory()+p.getSpace());
			}
			
			JPanel memo = seg.getSegmento();
			pane.add(memo);
			memo.setBounds(x, y, 169, 83);
			m.lista.add(seg);
			y+=83;
			if(i==4 || i == 8 || i == 12) {
				x+=169;
				y=11;				
			}
		}
		
	}
	
	
}
