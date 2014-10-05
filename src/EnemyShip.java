import java.util.Random;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class EnemyShip {
	
	private int shipX;
	private int shipY;
	private Image shipImage;
	private int vx = 5;
	Random random = new Random();
	public float getX() { return shipX; }
	public float getY() { return shipY; }
	
	public EnemyShip(int x, int y) throws SlickException {
		shipImage = new Image("res/Enemyship.png");
		this.shipX = x;
		this.shipY = y;
	}

	public void randomy(int MaxY) {
		shipY = random.nextInt(MaxY-80);
	}

	public void draw() {
		shipImage.draw(shipX,shipY);
	}

	public void Update() {
		shipX -= vx;
	}
	public void Death(int MaxX , int MaxY) {
		shipX = MaxX;
		this.randomy(MaxY);
	}
	
}
