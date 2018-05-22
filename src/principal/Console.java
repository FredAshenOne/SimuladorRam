package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	String lineText;
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
		
		doc = txtConsole.getDocument();
		rootElem = doc.getDefaultRootElement();
		numLines = rootElem.getElementCount();
		lineElem = rootElem.getElement(numLines -1);
		lineStart = lineElem.getStartOffset();
		lineEnd = lineElem.getEndOffset();
		
		txtConsole.addKeyListener(this);
		
		
		
		
		
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
	
	public String[] getLastLine() {
		String[] text,instruction = null;
		try {
			rootElem = doc.getDefaultRootElement();
			numLines = rootElem.getElementCount();
			lineElem = rootElem.getElement(numLines -1);
			lineStart = lineElem.getStartOffset();
			lineEnd = lineElem.getEndOffset();
			lineText = doc.getText(lineStart,lineEnd - lineStart);
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lineText = lineText.replace(")","");
		lineText = lineText.replace("\"", "");
		
		text = lineText.split("\\(");
		if(text[0].equals("add")) {
			String[] parameters = text[1].split(",");
			instruction = new String[] {text[0],parameters[0],parameters[1]};
			System.out.println(instruction[0]+" "+instruction[1]+" "+instruction[2]);
		}else if(text[0].equals("kill")) {
			System.out.println(text[0]+" "+text[1]);
			return text;
		}else if(text[0].equals("exit")) {
			this.setVisible(false);
		}else {
			
		}
		return instruction;
	}
}
