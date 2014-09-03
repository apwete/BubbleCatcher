package states;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{
	public final static String NAME = "Bubble Catcher";  	
	public static int lives = 3;
	public static int score = 0;
	
	public Main(String name) {
		super(name);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main(NAME));
		app.setDisplayMode(640, 360, false);
		app.setAlwaysRender(true); // set so changes render in debug automatically after saving
		app.setTargetFrameRate(300);
		app.start();
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new Start());
		this.addState(new Game());
	}

}
