import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class SpaceShipGame extends BasicGame {
	public static final int Game_High = 600;
	public static final int	Game_Width = 800;
	public static final int	enemy_count = 8;
	MyShip ship;
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
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		ship = new MyShip(Game_Width/4 , Game_High/3);
		initEnemyShip();
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
		UpdateShipMovement(input , delta);
		for (EnemyShip enemy : enemyShip) {
			enemy.Update();
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
