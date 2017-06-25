import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;

//Much of this class as well is created by the IDE so there's not much really you had to do here
public class MyRectangle extends MyBoundedShape {
	private Point firstPoint;
    private Point secondPoint;

	public MyRectangle() {
		super();
	}

	public MyRectangle(int x1, int y1, int x2, int y2, Color color, boolean fill) {
		super(x1, y1, x2, y2, color, fill);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor()); // Sets color
	
		if (getFill()) // Set fill to filled
			g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
		else
			g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
		//else draw it unfilled
	}

	public MyRectangle(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    boolean contains(Point point) {
       return (distance(firstPoint, point) + distance(secondPoint, point) == distance(firstPoint, secondPoint));
    }
}