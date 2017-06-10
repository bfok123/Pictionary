package pictionary3;

public class Player {
	private int points;
	private String answer;
	private String answerModified; //modified to take out all chars except letters and change to lowercase
	private String guess;
	private String name;
	private boolean isTurn;
	
	public Player(String defaultName) {
		points = 0;
		answer = "";
		guess = "";
		isTurn = false;
		name = defaultName;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getIsTurn() {
		return isTurn;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public String getAnswerModified() {
		return answerModified;
	}
	
	public String getGuess() {
		return guess;
	}
	
	public void addPoint() {
		points++;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setIsTurn(boolean turn) {
		isTurn = turn;
	}

	public void setPoints(int newPoints) {
		points = newPoints;
	}
	
	//sets answer and modified answer
	public void setAnswer(String newDrawingName) {
		//remove everything except letters from the drawingName and change to lower case
		answer = newDrawingName;
		newDrawingName = newDrawingName.replaceAll("[^a-zA-Z]", "").toLowerCase();
		answerModified = newDrawingName;
	}

	public void setGuess(String newGuess) {
		//remove everything except letters from the guess and change to lower case
		newGuess = newGuess.replaceAll("[^a-zA-Z]", "").toLowerCase();
		guess = newGuess;
	}
}


