import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Bullet {
	protected float BulletX;
	protected float BulletY;
	boolean checkGunFire;
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
		if (Math.abs(BulletX - enemy.getX()) < 40) {
			if (Math.abs(BulletY - enemy.getY()) < 40){
				return true;
			}			
		}
		return false;
	}

	

}
