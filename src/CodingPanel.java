import java.awt.Panel;
import java.awt.TextArea;

public class CodingPanel extends Panel {

	public TextArea textArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_NONE);
	
	public CodingPanel(){
		setLayout(null);
		setBounds(50, 50, 380, 480);
		textArea.setBounds(30,30,350,330);
		add(textArea);
	}
	
	public String getText(){
		return textArea.getText();
	}
	
	public void setText(String text){
		textArea.setText(text);
	}
}
