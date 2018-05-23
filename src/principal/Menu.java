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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
	JComboBox<Object> cbGigas,cbOrder;
	String def = "___";
	int Width = 192;
	List<Procesos> listP= new ArrayList<Procesos>();
	
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
		m.btnBack.addActionListener(this);
		lblWarning.setForeground(Color.red);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnContinue) {
			if(cbOrder.getSelectedItem().toString().equals("")	|| cbGigas.getSelectedItem().toString().equals("")) {
				lblWarning.setText("Faltan Datos");
			}else {
				m.choice = cbOrder.getSelectedIndex();
				m.spTotal=Integer.parseInt(cbGigas.getSelectedItem().toString());
				gigas = m.spTotal / 1024;
				m.freeSpace = m.spTotal;
				
				switch(m.choice){			
				case 3:
					createSegmentosFirst(gigas,m.processPane);
					break;
				case 2:case 1:
					createSegmentosBW(gigas,m.processPane);
					break;
					
				}
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
		}else if(e.getSource() == m.btnBack) {
			m.setVisible(false);
			this.setVisible(true);
		}
	}
	
	
	public void createSegmentosFirst(int gigas,JPanel pane) {
		int x = 10,y = 11,pros = gigas*4,index = 0;
		listP.clear();
		for(int k = 0;k<=pros;k++) {
			Procesos p = new Procesos(k,def,"libre",256);
			listP.add(p);			
		}
		m.lista.clear();
		for(int i = 0;i<gigas;i++) {
			Segmentos seg = new Segmentos();
			seg.setId(i+1);
			seg.setMemory(0);
			seg.setP1(listP.get(index));
			index++;
			seg.setP2(listP.get(index));
			index++;	
			seg.setP3(listP.get(index));
			index++;
			seg.setP4(listP.get(index));
			index++;
			seg.setMemory(1024);
			JPanel memo = seg.getSegmento();
			pane.add(memo);
			s.mdPanel(memo, Color.white);
			memo.setBounds(x, y, 169, 83);
			m.lista.add(seg);
			y+=83;
			if(i==4 || i == 9 || i == 14) {
				x+=169;
				y=11;
			}
			m.listaPtot = listP;			
		}		
	}
	
	public void createSegmentosBW(int gigas,JPanel pane) {
		int x = 10,y = 11;
		for(int i = 1;i<=gigas;i++) {
			Segmentos seg = new Segmentos();
			seg.getListaP().clear();
			seg.setId(i);
			seg.setMemory(1024);
			for(int k = 0;k<4;k++) {
				Procesos p = new Procesos(k+1, def,"libre", 256);
				if(k==0) {
					seg.setP1(p);
				}else if(k==1){
					seg.setP2(p);
				}else if(k==2) {
					seg.setP3(p);
				}else {
					seg.setP4(p);
				}
			}
			
			JPanel memo = seg.getSegmento();
			s.mdPanel(memo, Color.white);
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
