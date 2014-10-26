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
	public static final int	max_bullet = 200;
	public static int Hp = 10;
	public boolean checkCatch;
	private boolean isDeath = false;
	private boolean isBulletCatch = false;
	private boolean [] isfireArray = new boolean[max_bullet];
	private MyShip ship;
	private Bullet[] bulletArray;
	private EnemyShip[] enemyShip;
	int score = 0;
	int countBullet = 0;
	
	public SpaceShipGame(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		for (EnemyShip enemy : enemyShip) {
			enemy.draw();
		}
		g.drawString("score " + score, Game_Width - 120, 10);
		g.drawString("hp " + Hp, Game_Width/8, 10);
		for (int i = 0 ; i < max_bullet ; i++) {
			if(isfireArray[i]) {
				bulletArray[i].draw();
			}
		}
		ship.draw();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		ship = new MyShip(Game_Width/4 , Game_High/3);
		initEnemyShip();
		initBullet();
	}

	private void initBullet() throws SlickException {
		bulletArray = new Bullet[max_bullet];
		for(int i = 0 ; i < max_bullet ; i++) {
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
			for (int i = 0 ; i < countBullet ; i++)
			{	
				isBulletCatch = bulletArray[i].updateBulletCatch(enemy);
				if (isBulletCatch == true) {
					bulletArray[i].removeToOutScreen();
					isfireArray[i] = false;
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
			isfireArray[countBullet] = true;
			System.out.println("fire");
			bulletArray[countBullet].setXY(ship.getX()+ ship.getShipWidth()/2, ship.getY()+ ship.getShipHigh()/2);
			countBullet++;
		}
		for (int i = 0 ; i <= countBullet ; i++) {
			if (isfireArray[i]) {
				bulletArray[i].update();
				if (bulletArray[i].getX() > Game_Width) {
					bulletArray[i].outOfScreen();
					isfireArray[i] = false;
				}
			}
		}
	}

	private void UpdateShipMovement(Input input, int delta) {
		
		if (input.isKeyDown(Input.KEY_UP)) {
			ship.moveUp();
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			ship.moveDown(Game_High);
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
