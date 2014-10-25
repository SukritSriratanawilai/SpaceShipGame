import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class SpaceShipGame extends BasicGame {
	public static final int Game_High = 600;
	public static final int	Game_Width = 800;
	public static final int	enemy_count = 10;
	public static final int	bullet_count = 200;
	public static int Hp = 10;
	public boolean checkCatch;
	private boolean isDeath = false;
	private boolean isBulletCatch = false;
	private boolean isfire = false;
	private boolean [] isfireArray = new boolean[bullet_count];
	private MyShip ship;
	//private Bullet bullet;
	private Bullet[] bulletArray;
	private EnemyShip[] enemyShip;
	int score = 0;
	int countBullet = 0;
	
	public SpaceShipGame(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		ship.draw();
		for (EnemyShip enemy : enemyShip) {
			enemy.draw();
		}
		g.drawString("score " + score, Game_Width - 120, 10);
		g.drawString("hp " + Hp, Game_Width/8, 10);
		//if (isfire == true) {	
		//	bullet.draw();
			for (int i = 1 ; i < bullet_count ; i++) {
				bulletArray[i].draw();
		//	}
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		ship = new MyShip(Game_Width/4 , Game_High/3);
		initEnemyShip();
		initBullet();
	}

	private void initBullet() throws SlickException {
		//bullet = new Bullet(ship.getX() + ship.getShipWidth()/2, ship.getY() + ship.getShipHigh()/2);
		bulletArray = new Bullet[bullet_count];
		for(int i = 0 ; i < bullet_count ; i++) {
			bulletArray[i] = new Bullet(ship.getX() + ship.getShipWidth()/2, ship.getY() + ship.getShipHigh()/2);
		}
	}

	private void initEnemyShip() throws SlickException {
		enemyShip = new EnemyShip[enemy_count];
		for(int i = 0 ; i < enemy_count ; i++) {
			enemyShip[i] = new EnemyShip(Game_Width,Game_High);
			enemyShip[i].randomy(Game_High);
			enemyShip[i].randomx(Game_Width);
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		Input input = container.getInput();
		if (isDeath == false) {
			UpdateShipMovement(input , delta);
			UpdateBullet(input , delta);
			UpdateEnemyShip();		
		}
	}

	private void UpdateEnemyShip() {
		for (EnemyShip enemy : enemyShip) {
			enemy.Update();
			//isBulletCatch = bullet.updateBulletCatch(enemy);
			for (int i = 0 ; i < countBullet ; i++)
			{	
				isBulletCatch = bulletArray[i].updateBulletCatch(enemy);
				if (isBulletCatch == true) {
					break;
				}
			}	
			if  (isBulletCatch == true) {
				score+=1;
				enemy.Death(Game_Width , Game_High);
			}
			checkCatch = ship.updateShipCatch(enemy);
			if (checkCatch == true)
			{
				Hp-=1;
				System.out.println("catch");
				enemy.Death(Game_Width , Game_High);
				checkCatch = false;
				if (Hp == 0) {
					isDeath = true;		
				}
			}
			if (enemy.getX() < -50) {
				enemy.reEnemyToTheScreen(Game_Width , Game_High);
			}
		}
	}
	
	private void UpdateBullet(Input input, int delta) throws SlickException {
		
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			isfire = true;
			isfireArray[countBullet] = true;
			System.out.println("fire");
			//bullet.setXY(ship.getX()+ ship.getShipWidth()/2, ship.getY()+ ship.getShipHigh()/2);
			bulletArray[countBullet].setXY(ship.getX()+ ship.getShipWidth()/2, ship.getY()+ ship.getShipHigh()/2);
			countBullet++;
		}
		for (int i = 0 ; i < countBullet ; i++) {
			bulletArray[i].update();
		}
		/*if (isfire == true) {
			isfire = false;
			countBullet++;
		}*/
		//bullet.update();
	}

	private void UpdateShipMovement(Input input, int delta) {
		
		if (input.isKeyDown(Input.KEY_UP)) {
			ship.moveUp();
			for (Bullet bullet : bulletArray) {
				bullet.moveUp();
			}
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			ship.moveDown(Game_High);
			for (Bullet bullet : bulletArray) {
				bullet.moveDown();
			}
		}
		
	}

	public static void main(String[] args) {
		try {		    	
			SpaceShipGame game = new SpaceShipGame("SpaceShipGame");
			AppGameContainer container = new AppGameContainer(game);
			container.setDisplayMode(Game_Width , Game_High , false);
			container.setMinimumLogicUpdateInterval(1000 / 60);
			container.start();
		} 
		catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
