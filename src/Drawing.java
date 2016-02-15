import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class Drawing {
	private static Point sPoint;
	Display display = new Display();
	Shell shell = new Shell(display);
	Canvas canvas = new Canvas(shell, SWT.NONE);

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


		//		String response = JOptionPane.showInputDialog
		//			      ( "What is the length for the rectangle?" );


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
					GC gc = new GC(canvas);
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
					gc.dispose();						
				}
			};
			canvas.addListener(SWT.MouseDown, selectXY);
		}
	}

	private void setToSquare(){
		GC gc = new GC(canvas);
		gc.drawRectangle(60, 60, 60, 60);
		gc.dispose();
	}

	private void setToTriangle(){
		GC gc = new GC(canvas);
		gc.drawPolygon(new int[] {125,105,145,145,105,145});
		gc.dispose();
	}
}
