package userinterface;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * 	@class ErrorFrame
 *	This ErrorFrame class serves as the frame for displaying errors.
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */

@SuppressWarnings("serial")
public class ErrorFrame extends Frame {	
	public Label label = new Label("");
	
	public TextArea ta = new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
	Button btnClose=new Button();
	int spacingX = 20;
	int spacingY = 20;
	int buttonX = 80;
	int buttonY = 30;
	
	public ErrorFrame(){
		setSize(300,200);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setBackground(new Color(225,225,225));
		
		ta.setEditable(false);
		add(label);
		add(ta);
	
		btnClose.setLabel("Close");
		btnClose.setBounds(this.getWidth()/2 - buttonX/2,this.getHeight() - (spacingY + buttonY), buttonX, buttonY);
		add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e){
				dispose();}
		}
				);
	}
	
	public void init(String text) {
		ta.setText(text);
		ta.setBounds(spacingX, 2*spacingY, this.getWidth()-spacingX*2, this.getHeight() - (4*spacingY + buttonY));
		btnClose.setBounds(this.getWidth()/2-buttonX/2,this.getHeight()-50, buttonX, buttonY);
	}

}
