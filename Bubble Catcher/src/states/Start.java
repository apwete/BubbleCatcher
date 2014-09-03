package states;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Start extends BasicGameState {

	private Rectangle rect = null;

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		rect = new Rectangle(280, 125, 80, 30);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();

		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(1);
			if(Main.lives <= 0){
				Main.lives = 3;
				Main.score = 0;
			}
		} // end state change on enter
		if ((mouseX > 280 && mouseX < 360) && (mouseY > 205 && mouseY < 235)) {
			// this run when mouse is in the button
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(1);
				if(Main.lives <= 0){
					Main.lives = 3;
					Main.score = 0;
				}
			}
		} // end state change on play button
		if ((mouseX > 280 && mouseX < 360) && (mouseY > 150 && mouseY < 180)) {
			// this run when mouse is in the button
			if (input.isMouseButtonDown(0)) {
				System.exit(0);
			}
		} // end state change on close button
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			System.exit(0);
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		gc.setShowFPS(false);
		g.drawString("Current Score: " + Main.score, 250, 90);
		g.draw(rect);
		g.drawString("Play", 300, 130);
		g.drawRect(280, 175, 80, 30);
		g.drawString("Close", 297, 180);
	}

	public int getID() {
		return 0;
	}

}
