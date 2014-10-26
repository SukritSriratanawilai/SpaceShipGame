
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class MyShip {
	
	private int shipX;
	private int shipY;
	private static final int shipWidth = 60;
	private static final int shipHIGH = 60;
	private int Vy = 5;
	private int Vx = 5;
	private Image shipImage;
	public float getX() { return shipX; }
	public float getY() { return shipY; }
	public float getShipWidth() { return shipWidth; }
	public float getShipHigh() { return shipHIGH; }
	
	public MyShip(int x, int y) throws SlickException {
		shipImage = new Image("res/ship.png");
		this.shipX = x;
		this.shipY = y;
	}
	
	public void draw() {
		shipImage.draw(shipX,shipY);
	}

	public void moveUp() {
		
		if (shipY != 0) {
			shipY -= Vy;
		}
		
	}

	public void moveDown(int maxScreen) {
		
		if (shipY != maxScreen - 80) {
			shipY += Vy;
		}
		
	}
	
	public boolean updateShipCatch(EnemyShip enemy) {
		if (Math.abs(shipX - enemy.getX()) < 45) {
			if (Math.abs(shipY - enemy.getY()) < 45){
				return true;
			}			
		}
		return false;
	}
	public void moveLeft() {
		// TODO Auto-generated method stub
		if (shipX != 0) {
			shipX -= Vx;
		}
	}
	public void moveRight(int maxScreen) {
		// TODO Auto-generated method stub
		if (shipX != maxScreen - 80) {
			shipX += Vx;
		}
	}

}
