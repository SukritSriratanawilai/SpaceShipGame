import javax.swing.text.Highlighter.Highlight;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class SpaceShipGame extends BasicGame {
	public static final int Game_High = 600;
	public static final int	Game_Width = 800;
	MyShip ship;
	public SpaceShipGame(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		ship.draw();
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		ship = new MyShip(Game_Width/4 , Game_High/3); 
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
	
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
