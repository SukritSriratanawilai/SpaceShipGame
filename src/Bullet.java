import org.newdawn.slick.Graphics;


public class Bullet {
	private static final float BULLET_SIZE = 5;
	protected float BulletX ;
	protected float BulletY;
	boolean checkGunFire;
	
	public Bullet(float x, float y) {
		this.setXY(x,y);    
	}
		
	public void draw(Graphics g) {
		g.fillOval(getX(), getY(), BULLET_SIZE, BULLET_SIZE);
	}
	 
	public void update(float x , float y) {	
		BulletX = x;
		BulletY = y;
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

	

}
