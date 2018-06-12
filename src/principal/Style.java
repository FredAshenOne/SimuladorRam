package principal;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class Style extends BasicComboBoxUI {
	public Font f12 = new Font("Yu Gothic UI Light", Font.ITALIC, 12);
	public Font f16 = new Font("Yu Gothic UI Light", Font.ITALIC, 16);
	public String color = "#2196F3";
	public Color glight =Color.decode("#B0BEC5");
	public String urlIcon = "views/backblue.png";
	public String urlbtn = "views/addblue.png";
	
	public void mdPanel(JPanel panel, Color c) {
		panel.setBorder(null);
		panel.setOpaque(true);
		panel.setBackground(c);
	}
	public void mdBtn(JButton btn, Color cb,Color cf) {
		btn.setContentAreaFilled(false);
		btn.setBorder(new LineBorder(cb,1,true));
		btn.setOpaque(true);
		btn.setBackground(cb);
		btn.setForeground(cf);
	}
	public void btnPointer(JButton btn) {
		btn.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
		new ImageIcon("views/cursor.png").getImage(),
		new Point(0,0), "custom cursor"));
	}
	
	public void mdTxt(JTextField txt,Color bg,Color fg) {
		txt.setBorder(null);
		txt.setOpaque(true);
		txt.setBackground(bg);
		txt.setForeground(fg);
	}
	
	public void imgBtn(JButton btn, String url) {
		btn.setBorder(null);
		btn.setOpaque(true);
		btn.setContentAreaFilled(false);
		btn.setIcon(new ImageIcon(url));
	}
	
	public void btnHover(JButton btn,Color cb,Color cf,Color cbord){
	
		btn.setContentAreaFilled(false);
		btn.setBorder(new LineBorder(cbord,1,true));
		btn.setOpaque(true);
		btn.setBackground(cb);
		btn.setForeground(cf);
		
	}
	
	public void clearData(JTextField t1) {
		t1.setText("");
	}
	
	public boolean maxLength(JTextField txt,JLabel wrn,int ch) {
		if(txt.getText().length() > ch) {
			wrn.setForeground(Color.red);
			wrn.setText("Excede los caracteres permitidos");			
			return true;
		}
		return false;
		
	}
	
	
	@Override protected JButton createArrowButton() {
		return new BasicArrowButton(
				BasicArrowButton.SOUTH,
				Color.WHITE, Color.BLACK,Color.CYAN,Color.BLUE
				);
	 }	


}
