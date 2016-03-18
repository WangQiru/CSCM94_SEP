import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class CodingPanel extends Panel {

	public TextArea textArea = new TextArea("Triangle(100)", 0, 0, TextArea.SCROLLBARS_NONE);

	public CodingPanel(){
		setLayout(null);
		add(textArea);
		textArea.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar()=='(') {
					e.consume();
					textArea.insert("()", textArea.getCaretPosition() - (textArea.getText().length() - textArea.getText().replace("\r", "").length()));
					textArea.setCaretPosition(textArea.getCaretPosition()- (1 + textArea.getText().length() - textArea.getText().replace("\r", "").length()));
				}
			}
		});
	}

	public String getText(){
		return textArea.getText();
	}

	public void setText(String text){
		textArea.setText(text);
	}
}
