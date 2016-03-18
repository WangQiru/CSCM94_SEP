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
	}

	public String getText(){
		return textArea.getText();
	}

	public void setText(String text){
		textArea.setText(text);
	}
}
