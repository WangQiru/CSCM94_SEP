import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import tree.TreeNode;

public class Drawing {
	Display display = new Display();
	Shell shell = new Shell(display);
	Canvas canvas = new Canvas(shell, SWT.NONE);

	GC gc = new GC(canvas);

	public static void main(String[] args) {
		new Drawing();
	}

	private Drawing(){		
		shell.setText("CSG Drawing Pad");
		shell.setLayout(null);

		createMenuBar();
		createCanvas();

		shell.open();
		shell.setSize(400, 450);

		Button btnClear = new Button(shell, SWT.NONE);
		btnClear.setBounds(276, 20, 80, 27);
		btnClear.setText("Clear");
		btnClear.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent event) {
				canvas.redraw();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});


		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private void createMenuBar(){
		Menu menuBar = new Menu(shell, SWT.BAR);

		/**
		 * Menu bar: File
		 */
		MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeFileMenu.setText("File");

		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		cascadeFileMenu.setMenu(fileMenu);

		MenuItem miExit = new MenuItem(fileMenu, SWT.PUSH);
		miExit.setText("Exit");
		miExit.addListener(SWT.Selection, event-> {
			shell.getDisplay().dispose();
			System.exit(0);
		});



		/**
		 * Menu bar: Shape
		 */
		MenuItem cascadeShapeMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeShapeMenu.setText("Shape");        

		Menu shapeMenu = new Menu(shell, SWT.DROP_DOWN);
		cascadeShapeMenu.setMenu(shapeMenu);        

		MenuItem miRectangle = new MenuItem(shapeMenu, SWT.PUSH);
		miRectangle.setText("Rectangle");
		miRectangle.addListener(SWT.Selection, event-> {
			setToRectangle();
		});        

		MenuItem miTriangle = new MenuItem(shapeMenu, SWT.PUSH);
		miTriangle.setText("Triangle");
		miTriangle.addListener(SWT.Selection, event-> {
			setToTriangle();
		});  

		MenuItem miSquare = new MenuItem(shapeMenu, SWT.PUSH);
		miSquare.setText("Square");
		miSquare.addListener(SWT.Selection, event-> {
			setToSquare();
		});


		/**
		 * Menu bar: Operation
		 */

		MenuItem cascadeOperationMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeOperationMenu.setText("Operation");

		Menu operationMenu = new Menu(shell, SWT.DROP_DOWN);
		cascadeOperationMenu.setMenu(operationMenu);  

		MenuItem miClear = new MenuItem(operationMenu, SWT.PUSH);
		miClear.setText("Clear");
		miClear.addListener(SWT.Selection, event-> {
			canvas.redraw();
		});  

		shell.setMenuBar(menuBar);
	}

	private void createCanvas(){
		canvas.setBounds(20, 20, 250, 250);

		shell.open();
		shell.setSize(400, 450);

	}

	private void setToRectangle(){

		JTextField heightField = new JTextField(5);
		JTextField widthField = new JTextField(5);

		KeyListener keyListener = new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar() ;
				if ( !( Character.isDigit (c))) {
					e.consume ();
					JOptionPane. showMessageDialog( null, "Integer Only!",
							"Alert", JOptionPane.ERROR_MESSAGE );
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		};

		heightField.addKeyListener(keyListener);
		widthField.addKeyListener(keyListener);



		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Height:"));
		myPanel.add(heightField);
		myPanel.add(Box.createHorizontalStrut(15));
		myPanel.add(new JLabel("Width:"));
		myPanel.add(widthField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				"Please Enter Height and Width Values", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			int width=Integer.parseInt(widthField.getText());
			int height=Integer.parseInt(heightField.getText());

			Listener selectXY = new Listener() {
				@Override
				public void handleEvent(Event e) {
					
					int actualWidth = width;
					int actualHeight = height;

					if((e.x+width)>canvas.getSize().x){
						actualWidth=canvas.getSize().x-e.x-1;
						System.out.println(actualWidth);
					}

					if((e.y+height)>canvas.getSize().y){
						actualHeight=canvas.getSize().y-e.y-1;
						System.out.println(actualHeight);
					}
					

			        gc.drawRectangle(e.x, e.y, actualWidth, actualHeight);
										
				}
			};
			canvas.addListener(SWT.MouseDown, selectXY);
		}
	}

	private void setToSquare(){
		canvas.addPaintListener(new PaintListener(){
	        public void paintControl(PaintEvent e){
	            Rectangle clientArea = shell.getClientArea();
	         e.gc.drawPolygon(new int[] {125,105,145,145,105,145});
	        }
	    });
		canvas.redraw();
	}

	private void setToTriangle(){
		gc.drawPolygon(new int[] {125,105,145,145,105,145});
		
		ArrayList<String> root=new ArrayList<String>();
		TreeNode<List<String>> tree = new TreeNode<List<String>>(root);
		ArrayList<String> left=new ArrayList<String>();
		ArrayList<String> right=new ArrayList<String>();
		tree.addChild(left);
	}

}
