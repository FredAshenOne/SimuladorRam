package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Manager extends JFrame implements ActionListener,KeyListener{

	private static final long serialVersionUID = 1L;
	JPanel contentPane,mainPanel,processPane;
	private JTextField txtName,txtMemo;
	Font f = new Font("Yu Gothic UI Light", Font.PLAIN, 18);
	public List<Segmentos> lista = new ArrayList<Segmentos>();
	String def = "__";
	Style st = new Style();
	int memo,choice,spTotal,freeSpace,tamaño,idSegmento;
	JLabel lblWarning,lblMemory = new JLabel("Espacio de memoria"),labelMemoW;
	Segmentos s = new Segmentos();
	JButton btnContinue,btnBack,btnConsole;
	ButtonGroup g1 = new ButtonGroup();
	JRadioButton rdbtnKill,rdbtnAdd;
	Console c = new Console();
	int[] array,ids;
	String[] instruction;
	
	public List<Procesos> listaPtot = new ArrayList<Procesos>();
	
	
	public Manager() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 920, 457);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JPanel processMenu = new JPanel();
		processMenu.setBounds(25, 11, 141, 428);
		mainPanel.add(processMenu);
		processMenu.setLayout(null);
		
		JLabel lblHeader = new JLabel("Gestor de Procesos");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		lblHeader.setBounds(10, 27, 113, 20);
		processMenu.add(lblHeader);
		
		btnContinue = new JButton("Continuar");
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				st.btnPointer(btnContinue);
				st.btnHover(btnContinue, Color.WHITE, Color.decode("#00796B"), Color.decode("#00796B"));
			}
	
			@Override
			public void mouseExited(MouseEvent e) {
			st.mdBtn(btnContinue, Color.decode("#00796B"), Color.WHITE);
			}
		});
		btnContinue.setBounds(10, 361, 119, 23);
		processMenu.add(btnContinue);
		btnContinue.addActionListener(this);
		
		lblWarning = new JLabel("");
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setFont(new Font("Yu Gothic UI Light", Font.ITALIC, 12));
		lblWarning.setBounds(10, 336, 119, 14);
		processMenu.add(lblWarning);
		lblWarning.setForeground(Color.RED);
		
		JPanel statsPane = new JPanel();
		statsPane.setBounds(0, 102, 141, 223);
		processMenu.add(statsPane);
		statsPane.setLayout(null);
		
		JLabel lblNombreDeProceso = new JLabel("<html><style>body{text-align:center:}</style><body>Nombre de proceso <br>(3 caracteres maximo)</body><html>");
		lblNombreDeProceso.setBounds(0, 11, 141, 47);
		statsPane.add(lblNombreDeProceso);
		lblNombreDeProceso.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeProceso.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		
		txtName = new JTextField();
		txtName.setBounds(10, 55, 119, 32);
		statsPane.add(txtName);
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtName.setColumns(10);
		TextPrompt phName = new TextPrompt("---",txtName,f,Color.GRAY);
		
		txtMemo = new JTextField();
		txtMemo.setBounds(10, 126, 119, 32);
		statsPane.add(txtMemo);
		txtMemo.setHorizontalAlignment(SwingConstants.CENTER);
		txtMemo.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		txtMemo.setColumns(10);
		
		
		lblMemory.setBounds(6, 98, 123, 32);
		statsPane.add(lblMemory);
		lblMemory.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemory.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		
		labelMemoW = new JLabel("<html><style>body{text-align:center;}</style><body>Si no es multiplo  de 256 <br>se tomara el multiplo proximo hacia abajo</body></html>");
		labelMemoW.setHorizontalAlignment(SwingConstants.CENTER);
		labelMemoW.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 10));
		labelMemoW.setBounds(0, 165, 141, 47);
		statsPane.add(labelMemoW);
		
		rdbtnAdd = new JRadioButton("Add");
		rdbtnAdd.setFont(new Font("Yu Gothic Light", Font.PLAIN, 12));
		rdbtnAdd.setBounds(14, 54, 109, 23);
		processMenu.add(rdbtnAdd);
		
		rdbtnKill = new JRadioButton("Kill");
		rdbtnKill.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		rdbtnKill.setBounds(14, 80, 109, 23);
		processMenu.add(rdbtnKill);
		phName.setHorizontalAlignment(SwingConstants.CENTER);
		
		processPane = new JPanel();
		processPane.setBounds(163, 11, 900, 428);
		mainPanel.add(processPane);
		processPane.setLayout(null);
		
		btnBack = new JButton();
		btnBack.setBounds(0, 11, 27, 23);
		mainPanel.add(btnBack);
		btnBack.addActionListener(this);
		st.imgBtn(btnBack, "views/backgreen.png");
		st.btnPointer(btnBack);
		btnBack.addActionListener(this);
		
		rdbtnAdd.setActionCommand("add");
		rdbtnKill.setActionCommand("kill");
		rdbtnAdd.addActionListener(this);
		rdbtnKill.addActionListener(this);
		g1.add(rdbtnKill);
		g1.add(rdbtnAdd);
		rdbtnAdd.setSelected(true);
		
		btnConsole = new JButton("");
		btnConsole.setBounds(10, 394, 27, 23);
		processMenu.add(btnConsole);
		btnConsole.addActionListener(this);
		st.imgBtn(btnConsole, "views/console.png");
		
		c.txtConsole.addKeyListener(this);
		st.mdPanel(mainPanel, Color.WHITE);
		st.mdPanel(processMenu, Color.WHITE);
		st.mdPanel(processPane, Color.white);
		st.mdPanel(statsPane, Color.white);
		rdbtnAdd.setOpaque(true);
		rdbtnKill.setOpaque(true);
		rdbtnAdd.setBackground(null);
		rdbtnKill.setBackground(null);
		st.mdBtn(btnContinue, Color.decode("#00796B"), Color.WHITE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnContinue) {
			if(txtName.getText().length() > 0 && txtMemo.getText().length() > 0) {
				if(g1.getSelection().getActionCommand().equals("add")) {
					memo = bitMultiplier(Integer.parseInt(txtMemo.getText()));
						System.out.println("memo: "+memo);
						switch(choice) {
							case 1:
								addProcessBest(memo,txtName.getText());
								break;
							case 2:
									addProcessWorst(memo,txtName.getText());
								break;
							case 3: 
								if(totalSpaceCheckerFirst(memo,lista)) {
									if(consecutiveSpace(listaPtot,memo)) {
										consecutiveRoom(listaPtot,memo/256);
										addProcessFirst(txtName.getText());
									}else {
										lblWarning.setText("No hay espacio suficiente");
									}
								}else {
									lblWarning.setText("No hay espacio suficiente");
								}
								break;							
							}
				}else {
					switch(choice) {
					case 1:case 2:
						killProcessBW(lista,txtName.getText());
						break;
					case 3:
						killProcessFirst(txtName.getText());
						break;
					}
				}
			}
						
		}else if(e.getSource() == btnBack) {
			lista.clear();
			lblWarning.setText("");
			deleteTables();
		}else if(e.getSource() == rdbtnKill) {
			txtMemo.setVisible(false);
			labelMemoW.setVisible(false);
			lblMemory.setVisible(false);
		}else if(e.getSource() == rdbtnAdd) {
			txtMemo.setVisible(true);
			lblMemory.setVisible(true);
			labelMemoW.setVisible(true);
		}else if(e.getSource()==btnConsole) {
			c.setVisible(true);		
		}		
	}
	
	public boolean totalSpaceCheckerFirst(int spaceNeeded,List<Segmentos> lista) {
		
		if(spaceNeeded>0) {
			if( freeSpace >= spaceNeeded) {
				return true;
			}else{
				lblWarning.setText("No hay espacio suficiente");
				return false;
			}
		}else {
			lblWarning.setText("Asigne un proceso mayor");
		}
		return false;
	}
	
	public boolean totalSpaceCheckerBW(int needed) {
		if(freeSpace>=needed) {
			return true;
		}else {
			lblWarning.setText("Tamaño maximo alcanzado");
		}
		return false;
	}
	
	public void repaintTablesFirst() {
		Iterator<Segmentos> itrs = lista.iterator();
		int index = 0;
		while(itrs.hasNext()) {
			Segmentos s = itrs.next();
			s.setP1(listaPtot.get(index));
			index++;
			s.setP2(listaPtot.get(index));
			index++;	
			s.setP3(listaPtot.get(index));
			index++;
			s.setP4(listaPtot.get(index));
			index++;
			s.getTable().setModel(new DefaultTableModel(
			new Object[][] {
			},new String[] {
				s.getP1().getName(),s.getP2().getName(),s.getP3().getName(),s.getP4().getName()
				}
			));
		}
		
	}
	
	public void repaintTablesBest() {
		Iterator<Segmentos> iterS = lista.iterator();
		while(iterS.hasNext()) {
			Segmentos s = iterS.next();
			s.getTable().setModel(new DefaultTableModel(
					new Object[][] {
					},new String[] {
						s.getP1().getName(),s.getP2().getName(),s.getP3().getName(),s.getP4().getName()
						}
					));
		}
	}
	
	public void deleteTables() {
		txtName.setText("");
		txtMemo.setText("");
		processPane.removeAll();
		processPane.revalidate();
		processPane.repaint();
	}
	
	public void killProcessFirst(String name) {

		Iterator<Procesos> iterp = listaPtot.iterator();
		while(iterp.hasNext()) {
			Procesos p = iterp.next();
			if(p.getName().equals(name)) {
				p.setName(def);
				p.setStatus("libre");
				p.setSpace(256);
				freeSpace +=256;
				
			}
		}
		repaintTablesFirst();
	}

	public void killProcessBW(List<Segmentos> lista,String name) {
		System.out.println("Si entra");
		Iterator<Segmentos> iters = lista.iterator();
		while(iters.hasNext()) {
			Segmentos s = iters.next();
			Iterator<Procesos> iterp = s.getListaP().iterator();
			while(iterp.hasNext()) {
				Procesos p = iterp.next();
				if(p.getName().equals(name)) {
					p.setName(def);
					p.setSpace(256);
					p.setStatus("libre");
					freeSpace+=256;
					s.setMemory(s.getMemory()+256);
				}
			}
		}
		repaintTablesBest();
	}
	
	public boolean nameChecker(String name) {
		Iterator<Procesos> iterp = listaPtot.iterator();
		while(iterp.hasNext()) {
			Procesos p = iterp.next();
			if(p.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean nameCheckerBest(String name) {
		Iterator<Segmentos> iters = lista.iterator();
		while(iters.hasNext()) {
			Segmentos seg = iters.next();
			Iterator<Procesos> iterp = seg.getListaP().iterator();
			while(iterp.hasNext()) {
				Procesos p = iterp.next();
				if(p.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean consecutiveSpace(List<Procesos> lista,int needed) {
		int cont = 0;
		Iterator<Procesos> iterp = lista.iterator();
		while(iterp.hasNext()) {
			Procesos p = iterp.next();
			if(p.getSpace()==256 && p.getStatus().equals("libre")) {
				cont+=256;
				if(cont >= needed) {
					return true;
				}
			}else {
				cont=0;
			}
			
		}
		return false;
	}
	
	public boolean consecutiveWorstbySegment(Segmentos s,int pSize) {
		int cont =0,i=0;
		int[] array = new int[pSize/256];
		Iterator<Procesos> iterp = s.getListaP().iterator();
		while(iterp.hasNext()) {
			Procesos p = iterp.next();
			if(p.getStatus().equals("libre") && p.getSpace()==256) {
				cont +=256;
				array[i] = p.getId();
				i++;
				if(cont==pSize) {
					ids=array;
					return true;
				}
			}else {
				cont = 0;
				i=0;
			}
		}
		
		return false;
	}
	
	public void idsWorstbyId(int id,int pSize) {
		int[] array = new int[pSize/256];
		for(int i = 0;i<array.length;i++) {
			array[i]=i+1;
		}
		ids=array;
	}
	
	public void addProcessWorst(int pSize,String name) {
		Iterator<Segmentos> iters = lista.iterator();
		int id = findingMajor(pSize);
		if(!nameCheckerBest(name)) {
			if(totalSpaceCheckerBW(pSize)) {
				while(iters.hasNext()) {
					Segmentos s = iters.next();
					if(s.getId()==id) {
						Iterator<Procesos> iterp = s.getListaP().iterator();
						while(iterp.hasNext()) {
							Procesos p = iterp.next();
							for(int i=0;i<ids.length;i++) {
								if(p.getId()==ids[i]) {
									p.setName(name);
									p.setSpace(0);
									p.setStatus("ocupado");
									freeSpace-=256;
									s.setMemory(s.getMemory()-256);
								}
							}
							repaintTablesBest();
							
						}
					}
						
				}
			}
		}
	}
	
	public void addProcessBest(int processSize,String name) {
		if(!nameCheckerBest(name)) {	
			if(totalSpaceCheckerBW(processSize)) {
				idsConsecutiveBest(processSize);
				Iterator<Segmentos> iters = lista.iterator();
				while(iters.hasNext()) {		
					Segmentos seg = iters.next();
					if(seg.getId() == idSegmento) {
						System.out.println(seg.getId() +" "+ idSegmento);
						Iterator<Procesos> iterp = seg.getListaP().iterator();
						while(iterp.hasNext()) {
							Procesos p = iterp.next();
							for(int i = 0; i<ids.length;i++) {
								if(p.getId()==ids[i]) {
									p.setName(name);
									p.setSpace(0);
									p.setStatus("ocupado");
									freeSpace-=256;
									seg.setMemory(seg.getMemory()-256);
								}
							}
							repaintTablesBest();
						}
					}
					
				}
			}
		}else {
			lblWarning.setText("ya existe ese proceso");
		}
	}
	
	public void addProcessFirst(String name) {
		if(!nameChecker(name)) {
			for(int i = 0;i<tamaño;i++) {
				Iterator<Procesos> iterp = listaPtot.iterator();
				while(iterp.hasNext()) {
					Procesos p = iterp.next();
					if(p.getId()==array[i]) {
						p.setName(name);
						p.setSpace(0);
						p.setStatus("ocupado");
						freeSpace-=256;
						System.out.println(freeSpace);
						lblWarning.setText("");
					}	
					repaintTablesFirst();
				}
			}		
		}else {
			lblWarning.setText("Ya existe ese proceso");
		}
		
	}
	
	
	public int bitMultiplier(int memo) {
		if(memo>256) {
		int x = memo / 256;
		
			if( x % 2 == 0) {
				return x*256;
			}else {			
				x = (int)x;
				return x*256;
			}
		}else {
			return 256;
		}
	}
		
	public void idsConsecutiveBest(int pSize) {
		ids = new int[pSize/256];
		int cont = 0,i=0;	
		Iterator<Segmentos> iters = lista.iterator();
		outerloop:
		while (iters.hasNext()){
			Segmentos s = iters.next();
			Iterator<Procesos> iterp = s.getListaP().iterator();
			if(s.getMemory()>=pSize) {
				while(iterp.hasNext()) {
					Procesos p =iterp.next();
					if(p.getStatus().equals("libre") && p.getSpace()==256) {
						ids[cont] = p.getId();
						cont++;
						if(cont == ids.length) {
							
							idSegmento = s.getId();
							break outerloop;
						}if(p.getId() == 4 && cont<pSize){
							cont =0;
						}
						
					}else {
						cont=0;
					}	
				}
			}
		}
	}
	
	public int[] idsConsecutiveFirst(List<Procesos> lista,int size) {
		int[] ids = new int[size/256];
		int cont = 0;
		Iterator<Procesos> iterp = lista.iterator();
		while(iterp.hasNext()) {
			Procesos p = iterp.next();
			if(p.getSpace()==256 && p.getStatus().equals("libre")) {
				ids[cont] = p.getId();
				cont++;
				if(ids.length==cont) {
					return ids;
				}
			}else {
				cont = 0;
			}
		}		
		return ids;
		
		
	}
	
	public void consecutiveRoom(List<Procesos>lista,int size) { 
		int cont = 0;
		array = new int[size];
		Iterator<Procesos> iterp = lista.iterator();
		outerloop:
		while(iterp.hasNext()) {
			Procesos p = iterp.next();
			if(p.getSpace() == 256 && p.getStatus().equals("libre")) {
				array[cont] = p.getId();
				cont++;
				if(cont == size) {
					tamaño = array.length;
					
					break outerloop;
				}
				
			}else {
				cont=0;
			}
		}
	}
	
	
	public void arrayLoop(int[] a) {

		for(int i=0;i < a.length;i++) {
			System.out.println(a[i]);
		}
	}



	
	public int findingMajor(int pSize) {
		int id = 0;
		Iterator<Segmentos> iters = lista.iterator();
		while(iters.hasNext()) {
			Segmentos s1 = iters.next();
			Iterator<Segmentos> iters1 = lista.iterator();
			if(s1.getMemory()==1024) {
				idsWorstbyId(s1.getId(),pSize);
				return s1.getId();
			}else{
				while(iters1.hasNext()) {
				Segmentos s2 = iters1.next();
					if(s1.getId()!=s2.getId()) {
						if(s1.getMemory() > s2.getMemory() && consecutiveWorstbySegment(s1, pSize) && s1.getMemory()>=pSize) {
							return s1.getId();					
						}else if(s2.getMemory() > s1.getMemory() && consecutiveWorstbySegment(s2,pSize)&& s2.getMemory()>=pSize){
							return s2.getId();
							
							
						}else if (s1.getMemory() == s2.getMemory() && consecutiveWorstbySegment(s1, pSize) && s1.getMemory()>=pSize || s2.getMemory() == s1.getMemory() && consecutiveWorstbySegment(s2,pSize)&& s2.getMemory()>=pSize) {
							if(s1.getId()<s2.getId()) {
								return s1.getId();
							}else {
								return s2.getId();
							}
						}
					}
				}
			}
		}
		return id;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== e.VK_ENTER) {
			eventsByConsole();
		}
		
	}
	
	
	public void  eventsByConsole() {
		instruction = c.getLastLine(c);
		if(instruction != null) {
			if(instruction[0].equals("add")){
				System.out.println(instruction[2]+" numero");
				memo = bitMultiplier(Integer.parseInt(instruction[2]));
					System.out.println("memo: "+memo);
					switch(choice) {
						case 1:
							addProcessBest(memo,instruction[1]);
							break;
						case 2:
							addProcessWorst(memo,instruction[1]);
							break;
						case 3: 
							if(totalSpaceCheckerFirst(memo,lista)) {
								if(consecutiveSpace(listaPtot,memo)) {
									consecutiveRoom(listaPtot,memo/256);
									addProcessFirst(instruction[1]);
								}else {
									lblWarning.setText("No hay espacio suficiente");
								}
							}else {
								lblWarning.setText("No hay espacio suficiente");
							}
							break;							
						}
			}else {
				switch(choice) {
				case 1:case 2:
					killProcessBW(lista,instruction[1]);
					break;
				case 3:
					killProcessFirst(instruction[1]);
					break;
				}
			}
		}
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	
	



	@Override
	public void keyTyped(KeyEvent e) {
	}
}