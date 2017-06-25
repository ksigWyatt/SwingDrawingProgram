import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;

public class MyOval extends MyBoundedShape {
	private Point firstPoint;
    private Point secondPoint;

	public MyOval() {
		super();
	}

	public MyOval(int x1, int y1, int x2, int y2, Color color, boolean fill) {
		super(x1, y1, x2, y2, color, fill);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor()); // Get color
		
		if (getFill()) // Set fill
			g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
		else
			g.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
		//Else draw un-filled
	}

	public MyOval(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    boolean contains(Point point) {
       return (distance(firstPoint, point) + distance(secondPoint, point) == distance(firstPoint, secondPoint));
    }
}