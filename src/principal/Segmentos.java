package principal;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Segmentos {
	
	private JPanel segmento;
	private JScrollPane scrollPane;
	private JTable table;
	public JLabel lblHeader;
	private int id,memory;
	private Procesos p1,p2,p3,p4;

	
	public JPanel getSegmento() {
		
		segmento = new JPanel();
		segmento.setBounds(10, 11, 169, 83);
		segmento.setLayout(null);
		
		lblHeader = new JLabel("Segmento "+id+"");
		lblHeader.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 10));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(10, 11, 148, 19);
		segmento.add(lblHeader);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 41, 148, 27);
		segmento.add(scrollPane);
		scrollPane.setBorder(null);
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},new String[] {
				p1.getName(),p2.getName(),p3.getName(),p4.getName()
			}
		));
		scrollPane.setViewportView(table);
		
		
		return segmento;
	}

	public void setSegmento(JPanel segmento) {
		this.segmento = segmento;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setP1(Procesos p1) {
		this.p1 = p1;
	}
	public void setP2(Procesos p2) {
		this.p2 = p2;
	}
	public void setP3(Procesos p3) {
		this.p3 = p3;
	}
	public void setP4(Procesos p4) {
		this.p4 = p4;
	}
	public Procesos getP1() {
		return p1;
	}
	public Procesos getP2() {
		return p2;
	}
	public Procesos getP3() {
		return p3;
	}
	public Procesos getP4() {
		return p4;
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}

}
