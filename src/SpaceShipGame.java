import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class SpaceShipGame extends BasicGame {
	public static final int Game_High = 600;
	public static final int	Game_Width = 800;
	public static final int	enemy_count = 2;
	//public static final int	bullet_count = 2;
	//private int numbullet = 0;
	public static int Hp = 1;
	public boolean check;
	private boolean iscatch = false;
	private boolean isBulletCatch = false;
	private boolean isfire = false;
	private MyShip ship;
	//private Bullet[] bullet;
	private Bullet bullet;
	private EnemyShip[] enemyShip;
	int score = 0;
	
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
		if (isfire == true) {	
			bullet.draw();
			/*if (numbullet < bullet_count-1) {
				bullet[numbullet].draw();
				numbullet++;
			}*/	
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		ship = new MyShip(Game_Width/4 , Game_High/3);
		initEnemyShip();
		initBullet();
	}

	private void initBullet() throws SlickException {
		/*bullet = new Bullet[bullet_count];
		for (int i = 0 ; i < bullet_count ; i++) {
			bullet[i] = new Bullet(ship.getX() + ship.getShipWidth()/2, ship.getY() + ship.getShipHigh()/2);
		}*/
		bullet = new Bullet(ship.getX() + ship.getShipWidth()/2, ship.getY() + ship.getShipHigh()/2);
	}

	private void initEnemyShip() throws SlickException {
		enemyShip = new EnemyShip[enemy_count];
		for(int i = 0 ; i < enemy_count ; i++) {
			enemyShip[i] = new EnemyShip(Game_Width,Game_High);
			enemyShip[i].randomy(Game_High);
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		Input input = container.getInput();
		if (iscatch == false) {
			UpdateShipMovement(input , delta);
			UpdateBullet(input , delta);
			for (EnemyShip enemy : enemyShip) {
				enemy.Update();
				isBulletCatch = bullet.updateBulletCatch(enemy);
				if  (isBulletCatch == true) {
					score+=1;
					enemy.Death(Game_Width);
				}
				check = ship.updateShipCatch(enemy);
				if (check == true)
				{
					Hp-=1;
					check = false;
					iscatch = true;
				}
			}		
			
		}
	}
	
	private void UpdateBullet(Input input, int delta) throws SlickException {
		
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			isfire = true;	
			System.out.println("fire");
			bullet.setXY(ship.getX()+ ship.getShipWidth()/2, ship.getY()+ ship.getShipHigh()/2);
		} 
		bullet.update();
		/*for (int i = 0 ; i < bullet_count ; i++) {
			bullet[i].update();
		}*/
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
