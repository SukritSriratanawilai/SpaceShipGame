import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Bullet {
	protected float BulletX;
	protected float BulletY;
	Image bulletImage;
	
	public Bullet(float x, float y) throws SlickException {
		this.setXY(x,y);
		bulletImage = new Image("res/Bullet1.png");
	}
	
	public void draw() {	
		bulletImage.draw(BulletX,BulletY);
	}
	 
	public void update() {
		BulletX += 5;
	}
	
	public float getX() {
	    return BulletX ;
	}
	 
	public float getY() {
		return BulletY;
	}
	 
	protected void setXY(float x, float y) {
		this.BulletX  = x;
		this.BulletY = y;
	}

	public boolean updateBulletCatch(EnemyShip enemy) {
		if (Math.abs(BulletX - enemy.getX()) < 30) {
			if (BulletY - enemy.getY() >= 0 && BulletY - enemy.getY() <= 60){
				return true;
			}			
		}
		return false;
	}

	public void removeToOutScreen() {
		// TODO Auto-generated method stub
		this.BulletY = -20;
	}

	public void moveDown(int maxScreen) {
		// TODO Auto-generated method stub
		if (BulletY != maxScreen - 50) {
			BulletY += 5;
		}
	}

	public void moveUp() {
		// TODO Auto-generated method stub
		if (BulletY != 30) {
			BulletY -= 5;
		}
	}

	public void outOfScreen() {
		// TODO Auto-generated method stub
		this.BulletY = -20;
	}

	

}
