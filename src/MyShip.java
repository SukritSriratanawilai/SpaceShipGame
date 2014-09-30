import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class MyShip {
	
	private int shipX;
	private int shipY;
	private Image shipImage;
	
	public MyShip(int x, int y) throws SlickException {
		shipImage = new Image("res/ship.png");
		this.shipX = x;
		this.shipY = y;
	}
	
	public void draw() {
		shipImage.draw(shipX,shipY);
	}
}
