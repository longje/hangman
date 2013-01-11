
public class Game {
	
	public String wordToGuess;
	public String charsGuessed;
	
	private String guessSoFar;
	private boolean newGuess = false;
	private int numIncorrectGuesses = 0;
	
	private final RandomWord wordGetter;
	private static final int MAX_NUM_GUESSES = 9;
	
	public Game() {
		guessSoFar = Config.EMPTY_STRING;
		charsGuessed = Config.EMPTY_STRING;
		wordGetter = RandomWord.getInstance();
		wordToGuess = wordGetter.getWord().toUpperCase();
		newGuess = true;
	}
	
	public void resetGame() {
		wordToGuess = wordGetter.getWord().toUpperCase();
		charsGuessed = Config.EMPTY_STRING;
		numIncorrectGuesses = 0;
		newGuess = true;
	}

	public boolean isGameWon() {
		return wordToGuess.equals(getGuessThusFar());
	}
	
	public boolean isGameOver() {
		return !isGameWon() && isNumGuessesUp();
	}
	
	public boolean isNumGuessesUp() {
		return numIncorrectGuesses >= MAX_NUM_GUESSES;
	}
	
	public void addGuessChar(char c) {
		if (isNumGuessesUp() || isGameWon())
			return;
		
		char a = (String.valueOf(c)).toUpperCase().charAt(0);
		if (charsGuessed.contains(String.valueOf(a)))
			return;
		
		newGuess = true;
		charsGuessed += (c + "").toUpperCase();
		if (!wordContainsGuess(a))
			numIncorrectGuesses++;
	}

	public int getNumIncorrectGuesses() {
		return numIncorrectGuesses;
	}
	
	public String getGuessThusFar() {
		//Cache guess
		if (!newGuess)
			return guessSoFar;
		
		char[] returnGuess = blankArray();
		char[] wordToGuessArray = wordToGuess.toCharArray();
		
		for (char c : charsGuessed.toCharArray()) {
			for (int i = 0; i < wordToGuess.length(); i++) {
				if (c == wordToGuessArray[i]) {
					returnGuess[i] = wordToGuessArray[i];
				}
			}
		}
		
		newGuess = false;
		guessSoFar = String.copyValueOf(returnGuess);
		return guessSoFar;
	}
	
	private boolean wordContainsGuess(char c) {
		return wordToGuess.contains(String.valueOf(c));
	}
	
	private char[] blankArray() {
		char[] returnGuess = new char[wordToGuess.length()];
		for (int i = 0; i < returnGuess.length; i++) {
			returnGuess[i] = ' ';
		}
		return returnGuess;
	}
}