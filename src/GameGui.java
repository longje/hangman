import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameGui extends JApplet {
	Game game;
	HangManBoard gb;
	BlankWordPanel bwp;
	GameGui gui;
	
	public void init() {
		gui = this;
		JPanel board = new JPanel();
		board.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));

		game = new Game();
		gb = new HangManBoard(game);
		bwp = new BlankWordPanel(game);
		
		gb.setFocusable(false);
		bwp.setFocusable(false);

		JButton resetButton = new JButton(Config.GAMERESET_TEXT);
		resetButton.setFocusable(false);
		resetButton.addActionListener(new ResetGame());
		
		board.add(gb);
		board.add(bwp);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(resetButton);
		buttonPanel.setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		
		Container cp = getContentPane();
		cp.setBackground(Color.WHITE);
		cp.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		cp.add(board);
		cp.add(buttonPanel);
		
		addKeyListener(new GuessKey());
		setSize(600,500);
		setVisible(true);
		setFocusable(true);
		requestFocus();
	}
	
	class ResetGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.resetGame();
			gb.repaint();
			bwp.repaint();
		}
	}
	
	class GuessKey implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			final int keyCode = e.getKeyCode();
			char c = (char) keyCode;
			if ( ( c > 64 && c < 91) || (c > 96 && c < 123) ) {
				game.addGuessChar(c);
				gb.repaint();
				bwp.repaint();
			}
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {

			
		}
		
	}

}
