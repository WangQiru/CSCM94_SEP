package userinterface;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class CodingPanel extends Panel {

	public TextArea textArea = new TextArea("Triangle(100)", 0, 0, TextArea.SCROLLBARS_NONE);

	public CodingPanel(){
		setLayout(null);
		add(textArea);
	}

	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar()=='(') {
			e.consume();
			textArea.insert("()", textArea.getCaretPosition());
			textArea.setCaretPosition(textArea.getCaretPosition()-1);
		}
		if (e.getKeyChar()=='{') {
			e.consume();
			textArea.insert("{}", textArea.getCaretPosition());
			textArea.setCaretPosition(textArea.getCaretPosition()-1);
		}
		if (e.getKeyChar()=='[') {
			e.consume();
			textArea.insert("[]", textArea.getCaretPosition());
			textArea.setCaretPosition(textArea.getCaretPosition()-1);
		}

		textArea.addKeyListener((KeyListener) this);
	}

	public String getText(){
		return textArea.getText();
	}

	public void setText(String text){
		textArea.setText(text);
	}
}
