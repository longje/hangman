import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class BlankWordPanel extends JPanel {
	private final Game game;
	
	public BlankWordPanel(Game g) {
		this.game = g;
		this.setPreferredSize(new Dimension(500,150));
	}
	
	public void paint(Graphics g) {
		Graphics2D ga = (Graphics2D)g;
		ga.setFont(new Font("Buxton Sketch", Font.BOLD, 24));
		
		ga.setStroke(new BasicStroke(3));
		ga.setColor(Color.WHITE);
		ga.clearRect(0, 0, this.getWidth(), this.getHeight());
		ga.fillRect(1, 1, this.getWidth(), this.getHeight());
		
		Boolean isGameOver = game.isGameOver();
		String word = (isGameOver)
				? game.wordToGuess.toUpperCase()
				: game.getGuessThusFar().toUpperCase();
		
		ga.setColor(Color.BLACK);
		
		if (isGameOver) {
			ga.setColor(Color.RED);
			ga.drawString(Config.GAMEOVER_TEXT, this.getWidth()/2 - 30, 20);
		}
		
		if (game.isGameWon()) {
			ga.setColor(Color.GREEN);
			ga.drawString(Config.GAMEWON_TEXT, this.getWidth()/2 - 30, 20);
		}
		
		char[] wordA = word.toCharArray();

		int length = word.length();
		int charL = 20;
		for (int i = 1; i <= length; i++) {
			int temp = i*35;
			ga.drawString(wordA[i-1] + "", temp + 2, 100-1);
			ga.drawLine(temp, 100, temp+charL, 100);
		}
	}
}