import java.awt.Panel;
import java.awt.TextArea;

public class CodingPanel extends Panel {

	public TextArea textArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_NONE);
	
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
