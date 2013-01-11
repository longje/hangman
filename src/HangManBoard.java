import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;

import javax.swing.JPanel;


public class HangManBoard extends JPanel {

	private final Game game;
	
	public HangManBoard(Game game) {
		this.setPreferredSize(new Dimension(500,350));
		this.game = game;
	}

	public void paint(Graphics g) {
		Graphics2D ga = (Graphics2D)g;
	/* Enable Anti Aliasing - http://developmentality.wordpress.com/2010/02/09/quick-hit-antialiasing-in-java-graphics2d/*/
		ga.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int halfMark = this.getWidth()/2;
		int topY = this.getHeight() - 250;

		ga.setColor(Color.WHITE);
		ga.clearRect(0, 0, this.getWidth(), this.getHeight());
		ga.fillRect(1, 1, this.getWidth()-1, this.getHeight()-1);

		ga.setFont(new Font("Buxton Sketch", Font.BOLD, 30));
		ga.setColor(Color.BLACK);
		ga.drawString(Config.GAMETITLE_TEXT, this.getWidth()/2 - 50, 30);
		ga.setFont(new Font("Buxton Sketch", Font.BOLD, 24));
		ga.drawString(Config.GAMELETTERUSED_TEXT, this.getWidth() - 175, topY);
		
		char[] guess = game.charsGuessed.toCharArray();
		int yPos = topY;
		
		for (int i = 0, x = i; i < guess.length; i++,x++) {
			if ( (i % 5) == 0) {
				yPos += 50;
				x = 0;
			}
			int xPos =  this.getWidth() - 175 + 25*x;
			ga.drawString(guess[i] + "", xPos, yPos);
		}
		
		
/* Draw Pole */
		ga.setColor(Color.black);
		ga.setStroke(new BasicStroke(7));
		ga.drawLine(halfMark, topY, halfMark, this.getHeight() - 15);
		ga.drawLine(100, topY, halfMark, topY);
		ga.drawLine(100, topY+15, 100, topY);

/* Draw Person */
		Shape[] human 
			= {new Arc2D.Double(100-25, topY+15, 50, 50, 90, 360, Arc2D.PIE), //head
				new Line2D.Double(100, topY+50, 100, topY+150), //body
				new Line2D.Double(100, topY+100, 50, topY+40), //left arm
				new Line2D.Double(100, topY+100, 150, topY+40), //right arm
				new Line2D.Double(100, topY+150, 50, topY+210), //left leg
				new Line2D.Double(100, topY+150, 150, topY+210), //right leg  
				new Arc2D.Double(100-10, topY+30, 5, 5, 90, 360, Arc2D.PIE), //left eye
				new Arc2D.Double(100+10, topY+30, 5, 5, 90, 360, Arc2D.PIE), //right eye
				new QuadCurve2D.Float(100-10, topY+55, 100, topY+45, 100+10, topY+55)}; //mouth 
		
		ga.setStroke(new BasicStroke(5));

		for (int i = 0; i < game.getNumIncorrectGuesses(); i++) {
			if (i == 0) {
				ga.fill(human[i]);
			}
			if ( i <= 5) {
				ga.draw(human[i]);
				continue;
			}
			
			if (i > 5 && i <= 7) {
				ga.setColor(Color.WHITE);
				ga.fill(human[i]);
			}
			
			if (i > 7) {
				ga.setStroke(new BasicStroke(3));
				ga.draw(human[i]);
			}
		}
	}
}
