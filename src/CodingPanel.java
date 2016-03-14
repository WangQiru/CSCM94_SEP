import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CodingPanel extends Panel {

	public TextArea textArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_NONE);

	public CodingPanel(){
		setLayout(null);
		textArea.addKeyListener(new KeyAdapter() {
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
			}
		});
		add(textArea);
	}

	public String getText(){
		return textArea.getText();
	}

	public void setText(String text){
		textArea.setText(text);
	}
}
