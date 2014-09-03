package states;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Game extends BasicGameState {

	private Image ground = null;
	private Image pc = null;
	private Rectangle pcsqr;
	private int pcY = 261;
	private int pcX = 10;
	private int boxH = 60;
	private ArrayList<Circle> balls;
	private Image rock;
	private int timePassed;
	private Random rand;
	public static int[] rocksX;
	public static int[] rocksY;
	private int speed = 900;
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		ground = new Image("res/grass.jpg");
		pc = new Image("res/pc/pc1.png"); 
		pcsqr = new Rectangle(boxX(), boxY(), boxW, boxH);
		balls = new ArrayList<Circle>();
		rock = new Image("res/foreground/rock.png");
		rand = new Random();
		rocksX =new int[10];
		for(int i=0; i<10; i++){
			rocksX[i] = rand.nextInt(560);
		}
		rocksY = new int[10];
		for(int i=0; i<10; i++){
			rocksY[i] = 30 + rand.nextInt(360);
		}	
	} // end init

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// Declarations
		Input input = gc.getInput();

		movePC(input);
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) { // change state
			sbg.enterState(0);
		} // end if
		timePassed += delta;
		if (timePassed > speed) {
			timePassed = 0;
			balls.add(new Circle(640, 50 + rand.nextInt(310), 10));
		} // makes a circle every 900mil on Y from 50-640
		for (Circle c : balls) {
			c.setCenterX(c.getCenterX() - (delta / 10f));
		} // makes circle move
		for (int i = balls.size() - 1; i >= 0; i--) {
			Circle c = balls.get(i);
			if (c.getCenterX() < 0) { // remove ball subtract from lives
				balls.remove(i);
				Main.lives --;
			}else if (c.intersects(pcsqr)){ // remove ball and add to score
				balls.remove(i);
				Main.score ++;
//				speedChange();
			}// end if
		} // end for
		if(Main.lives <= 0){ // move to game over at 0 lives
			for(int i = balls.size() - 1; i >= 0; i--){
				balls.remove(i);
			} // end for
			sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
		} // end if
	} // end update

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		gc.setShowFPS(false);
		
		for(int i = 0; i<= 19; i++){
			ground.draw(33*i, 0);
			for(int j = 0; j <= 10; j++){
				ground.draw(33*i, 33*j);
			}
		} // end for render ground
		for(int i = 0; i< 10; i++){
			g.drawImage(rock, rocksX[i], rocksY[i]);
		} // end for render rocks
		g.setColor(Color.yellow);
		for (Circle c : balls) {
			g.draw(c);
		} // end for rendering balls
		
		g.setColor(Color.white);
		g.drawString("Score: " + Main.score, 500, 20);
		g.drawString("Lives: " + Main.lives, 50, 20);
		pc.draw(pcX, pcY); 
	} // end render

	public int getID() {
		return 1;
	} // end getID
	
	public int boundX(int pcX) {
		if (pcX <= 10) {
			pcX ++;
			pcsqr.setX(pcX);
		}
		if (pcX >= 601) {
			pcX --;
			pcsqr.setX(pcX);
		}
		return pcX;
	} // end boundX
	
	private int boundY(int pcY) {
		if (pcY <= 39) {
			pcY ++;
			pcsqr.setY(pcY);
		}
		if (pcY >= 292) {
			pcY --;
			pcsqr.setY(pcY);
		}
		return pcY;
	} // end boundX

	private void movePC(Input input) throws SlickException {
		if (input.isKeyDown(Input.KEY_UP)) {
			pcY -= 1;
			pc = new Image("res/pc/pcD.png");
			pcsqr = new Rectangle(boxX(), boxY(), boxW, boxH);
			pcY = boundY(pcY);
		} // end KEY_UP if
		if (input.isKeyDown(Input.KEY_DOWN)) {
			pcY += 1;
			pc = new Image("res/pc/pcU.png");
			pcsqr = new Rectangle(boxX(), boxY(), boxW, boxH);
			pcY = boundY(pcY);
		} // end KEY_DOWN if
		if (input.isKeyDown(Input.KEY_LEFT)) {
			pcX -= 1;
			pc = new Image("res/pc/pcL.png");
			pcsqr = new Rectangle(boxX(), boxY(), boxW, boxH);
			pcX = boundX(pcX);
		} // end KEY_LEFT if
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			pcX += 1;
			pc = new Image("res/pc/pcR.png");
			pcsqr = new Rectangle(boxX(), boxY(), boxW, boxH);
			pcX = boundX(pcX);
		} // end KEY_RIGHT if
	} // end movePC

	private int boxX() {
		return pcX + 5;
	}

	private int boxY() {
		return pcY + 4;
	}

	public int speedChange(){
		switch(Main.score){
			case 6:
				speed = 800;
				break;
			case 21:
				speed = 750;
				break;
			case 41:
				speed = 700;
				break;
			case 61:
				speed = 650;
				break;
			case 81:
				speed = 600;
				break;
			case 101:
				speed = 550;
				break;
			case 121:
				speed = 500;
				break;
			case 141:
				speed = 450;
				break;
			case 161:
				speed = 400;
				break;
			case 181:
				speed = 350;
				break;
			case 201:
				speed = 300;
				break;
			default:
				speed = 250;
				break;
		} // end switch
		return speed;
	} // end speedChange()
}






