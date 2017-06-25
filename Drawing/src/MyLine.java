
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;

//Created much by the IDE added implementation for the draw method
public class MyLine extends MyShape {
	private Point firstPoint;
    private Point secondPoint;

	public MyLine() {
		super();
	}

	public MyLine(int x1, int y1, int x2, int y2, Color color) {
		super(x1, y1, x2, y2, color);
	}

	//Draws the shape for line - overrides the MyShape draw method
	//Sets color and then draws the line
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor()); 
		g.drawLine(getX1(), getY1(), getX2(), getY2());
	}

	public MyLine(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    boolean contains(Point point) {
       return (distance(firstPoint, point) + distance(secondPoint, point) == distance(firstPoint, secondPoint));
    }
}