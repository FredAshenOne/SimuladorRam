package principal;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.JTextArea;

public class Console extends JFrame implements KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextArea txtConsole = new JTextArea();
	String lineText,endLine;
	int numLines,lineStart,lineEnd;
	Document doc;
	Element lineElem,rootElem;


	public Console() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		txtConsole.setBounds(0, 0, 434, 261);
		contentPane.add(txtConsole);
		txtConsole.setForeground(Color.WHITE);
		txtConsole.setOpaque(true);
		txtConsole.setBackground(Color.BLACK);
		doc = txtConsole.getDocument();
		txtConsole.addKeyListener(this);
		txtConsole.setCaretColor(Color.WHITE);
		txtConsole.getCaret().setBlinkRate(500);
		
	}
	
	


	@Override
	public void keyPressed(KeyEvent e) {
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public String[] getLastLine(JFrame c) {
		String[] text,instruction = null;
		try {
			rootElem = doc.getDefaultRootElement();
			numLines = rootElem.getElementCount();
			lineElem = rootElem.getElement(numLines -1);
			lineStart = lineElem.getStartOffset();
			lineEnd = lineElem.getEndOffset();
			lineText = doc.getText(lineStart,lineEnd - lineStart);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		txtConsole.setCaretPosition(txtConsole.getText().length());
		lineText = lineText.substring(0,lineText.length()-1);
		if(isAnExpression(lineText)) {
			lineText = lineText.replace("'",""); 
			lineText = lineText.replace("\"", "");
			lineText = lineText.substring(0,lineText.length()-1);
			text = lineText.split("\\(");
			if(text[0].equals("add")) {
				String[] parameters = text[1].split(",");
				instruction = new String[] {text[0],parameters[0],parameters[1]};
				arrayLoop(instruction);
			}else if(text[0].equals("kill")) {
				return text;
			}else if(text[0].equals("exit")) {
				c.setVisible(false);
			}else {
				txtConsole.setText("La funcio n no es valida\n");
			}
		}else{
			txtConsole.setText("La funcion no es valida\n");
		}
		return instruction;
	}	
	
	public boolean isAnExpression(String s) {
		Pattern patAdd = Pattern.compile("^[a-zA-Z]{3}[(](\"|\')[a-zA-Z]{1,3}(\"|\')[,][0-9]{3,4}[)]");
		Pattern patKill = Pattern.compile("^[a-zA-Z]{4}[(](\"|\')[a-zA-Z]{1,3}(\"|\')[)]");
		Pattern patExit = Pattern.compile("^[a-zA-Z]{4}[(][)]");
		Matcher matAdd = patAdd.matcher(s);
		Matcher matExit = patExit.matcher(s);
		Matcher matKill = patKill.matcher(s);
		if(matAdd.matches()||matKill.matches()|| matExit.matches()) {
			return true;
		}
		return false;
	}
	public void arrayLoop(String[] a) {

		for(int i=0;i < a.length;i++) {
			System.out.println(a[i]);
		}
	}
}
