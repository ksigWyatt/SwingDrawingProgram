import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaintPanel extends JPanel {
	private LinkedList<MyShape> myShapes;
	private LinkedList<MyShape> clearedShapes;

	private int shapeType;
	private MyShape shapeObject;
	private Color shapeColor;
	private boolean shapeFilled;
	private Point clickedPoint;
	private Shape beingMoved;
	JLabel mouseLocation;

	public PaintPanel(JLabel statusLabel) {

		myShapes = new LinkedList<MyShape>();
		clearedShapes = new LinkedList<MyShape>();

		shapeType = 0;
		shapeObject = null;
		shapeColor = Color.BLACK;
		shapeFilled = false;

		this.mouseLocation = statusLabel;

		setLayout(new BorderLayout());
		add(statusLabel, BorderLayout.SOUTH);

		MouseHandler handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// draws the shapes
		ArrayList<MyShape> shapeArray = myShapes.getArray();
		for (int counter = shapeArray.size() - 1; counter >= 0; counter--)
			shapeArray.get(counter).draw(g);

		
		if (shapeObject != null)
			shapeObject.draw(g);
		//Begin drawing the object
	}

	//Set the current shape type
	public void setCurrentShapeType(int type) {
		shapeType = type;
	}

	//Set shape color
	public void setCurrentShapeColor(Color color) {
		shapeColor = color;
	}

	//Set filled
	public void setCurrentShapeFilled(boolean filled) {
		shapeFilled = filled;
	}

	//Clear last shape if user pressed button
	public void clearLastShape() {
		if (!myShapes.isEmpty()) {
			clearedShapes.addFront(myShapes.removeFront());
			repaint();
		}
	}

	public void redoLastShape() {
		if (!clearedShapes.isEmpty()) {
			myShapes.addFront(clearedShapes.removeFront());
			repaint();
		}
	}

	public void clearDrawing() {
		myShapes.makeEmpty();
		clearedShapes.makeEmpty();
		repaint();
	}

	private class MouseHandler extends MouseAdapter implements MouseListener {

		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				switch (shapeType)
				{
				case 0: // LINE
					shapeObject = new MyLine(e.getX(), e.getY(), e.getX(), e.getY(),
							shapeColor);
					break;
				case 1: // RECTANGLE
					shapeObject = new MyRectangle(e.getX(), e.getY(), e.getX(), e.getY(),
							shapeColor, shapeFilled);
					break;
				case 2: // Oval
					shapeObject = new MyOval(e.getX(), e.getY(), e.getX(), e.getY(),
							shapeColor, shapeFilled);
					break;
				}
				System.out.println("Pressed");
			} 
			else if (e.getButton() == MouseEvent.BUTTON3) {
				System.out.println(shapeObject.getX1());
			}
			
			//Here is where the implementation for the moving of shapes is supposed to go but I can't get the 
			//functionality to work. :(
			MyRectangle shape = new MyRectangle(new Point(shapeObject.getX1(),shapeObject.getY1()),new Point(shapeObject.getX2(),shapeObject.getY2()));
	        boolean result = shape.contains(new Point(e.getX(),e.getY()));
	        
	        if (result == true) {
	        	shapeObject.setX1(e.getX());
	        	shapeObject.setY1(e.getX());
	        }
	        
//			System.out.println(beingMoved.contains(e.getX(), e.getY()));
		}

		//If the mouse was released
		public void mouseReleased(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				// sets currentShapeObject x2 and Y2
				shapeObject.setX2(e.getX());
				shapeObject.setY2(e.getY());
				myShapes.addFront(shapeObject);
				shapeObject = null;
				clearedShapes.makeEmpty();
				repaint();
			}
		}

		//Where's the mouse?
		public void mouseMoved(MouseEvent e) {
			mouseLocation.setText(String.format("X: %d Y: %d", e.getX(), e.getY()));
			//Don't worry I found it
		}

		public void mouseDragged(MouseEvent e) {
			// sets currentShapeObject x2 and Y2
			shapeObject.setX2(e.getX());
			shapeObject.setY2(e.getY());

			// sets statusLabel to current mouse position
			mouseLocation.setText(String.format("X: %d Y: %d", e.getX(), e.getY()));
			repaint();
		}

		public void mouseExited(MouseEvent e) {
			mouseLocation.setText("Exited");
		}
	}
}