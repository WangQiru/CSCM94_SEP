package userinterface;
import java.awt.Panel;
import java.awt.TextArea;

/**
 * 	@class CodingPanel
 *	This CodingPanel class serves as a panel of DrawingFrame.
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */


@SuppressWarnings("serial")
public class CodingPanel extends Panel {

	public TextArea textArea = new TextArea("Triangle(100)", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);

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
