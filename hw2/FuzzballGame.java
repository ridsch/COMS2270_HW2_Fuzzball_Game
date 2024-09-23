
package hw2;

/**
 * Models a simplified baseball-like game called Fuzzball.
 * 
 * @author Schuyler Ridenour
 */
public class FuzzballGame {
  
	/**
	 * Number of strikes causing a player to be out.
	 */
	public static final int MAX_STRIKES = 2;

	/**
	 * Number of balls causing a player to walk.
	 */
	public static final int MAX_BALLS = 5;

	/**
	 * Number of outs before the teams switch.
	 */
	public static final int MAX_OUTS = 3;


	
	// TODO: EVERTHING ELSE
	// Note that this code will not compile until you have put in stubs for all
	// the required methods.
	
	/**
	 * This variable keeps track of the current inning the game is on.
	 */
	private static int currentInning;
	
	/**
	 * This variable keeps track of the maximum innings which can be played.
	 */
	private static int maxInnings;
	
	/**
	 * This variable is true for if the game is at the top of the inning, and
	 * false otherwise.
	 */
	private static boolean isTopOfInning;
	
	/**
	 * This variable keeps track of the number of balls.
	 */
	private static int numBalls;
	
	/**
	 * This variable keeps track of the number of strikes.
	 */
	private static int numStrikes;
	
	/**
	 * This variable keeps track of the number of outs.
	 */
	private static int numOuts;
	
	/**
	 * This array holds the values of players on the bases, with an 'o' being
	 * an empty base, and an 'X' being a player on base. 
	 */
	private static char[] bases = new char[3];
	
	/**
	 * This variable keeps track of team0's score.
	 */
	private static int team0Score;
	
	/**
	 * This variable keeps track of team1's score.
	 */
	private static int team1Score;
	
	
	/**
	 * Constructs a game that has the given number of innings and starts at the
	 * top of inning 1.
	 * @param givenNumInnings - number of innings for the game
	 */
	public FuzzballGame(int givenNumInnings) {
		currentInning = 1;
		maxInnings = givenNumInnings;
		isTopOfInning = true;
		bases[0] = 'o';
		bases[1] = 'o';
		bases[2] = 'o';
		numStrikes = 0;
		numBalls = 0;
		numOuts = 0;
		team0Score = 0;
		team1Score = 0;
	}
	
	
						// Mutator Methods
	
	/**
	 * Method called to indicate a bad pitch at which the batter did not swing.
	 */
	public void ball() {
		if (!(gameEnded())) {
			numBalls++;
			
			if (numBalls == MAX_BALLS) {
				if (bases[0] == 'o') {				// first base was empty
					bases[0] = 'X';
				} else {
					if (bases[1] == 'X') {
						if (bases[2] == 'X' ) {		// bases loaded -> run scored
							score();
						} else {					// first and second had a player
							bases[2] = 'X';
							bases[1] = 'X';
							bases[0] = 'X';
						}
					} else {						// first base had a player
						bases[1] = 'X';
						bases[0] = 'X';
					}
				}
				numBalls = 0;
				numStrikes = 0;
			}
			checkLimits();
		}
	}
	
	/**
	 * Method called to indicate that the batter is out due to a caught fly.
	 */
	public void caughtFly() {
		if(!(gameEnded())) {
			out();		
			checkLimits();
		}
	}
	
	/**
	 * Method called to indicate that the batter hit the ball.
	 * @param distance - how far the ball is hit.
	 */
	public void hit(int distance) {
		if (!(gameEnded())) {		// This makes sure the game is still active.
			if (distance >= 0) {
				if (distance < 15) {
					out();
				} else if (distance < 150) {
					shiftRunners(1);		// Single
				} else if (distance < 200) {
					shiftRunners(2);		// Double
				} else if (distance < 250) {
					shiftRunners(3);		// Triple
				} else if (distance >= 250 ){
					shiftRunners(4);		// Home run
				}
				numStrikes = 0;
				numBalls = 0;
			} else {
				out();
			}
		}
		
	}
	
	/**
	 * Method called to indicate a strike for the current batter.
	 * @param swung - true if batter swings and misses, false otherwise.
	 */
	public void strike(boolean swung) {
		if (!(gameEnded())) {
			if (swung) 
				out();
			else 
				numStrikes++;
		}
		checkLimits();
	}
	
	
					// Helper (Mutator) Methods
	
	/**
	 * This method is called in various other methods which will check the number
	 * of strikes and outs and update the score board accordingly when maximums 
	 * are reached.
	 */
	private void checkLimits() {		
		// This translates the max strikes to be an out, and resets accordingly.
		if (numStrikes == MAX_STRIKES) {
			numOuts++;
			numStrikes = 0;
			numBalls = 0;
		}
		
		// This resets everything when the top or bottom of the inning has ended
		if (numOuts == MAX_OUTS) {
			isTopOfInning = !(isTopOfInning);
			
			bases[0] = 'o';
			bases[1] = 'o';
			bases[2] = 'o';
			
			if (isTopOfInning) { 
				currentInning++;
			}
						
			numStrikes = 0;
			numBalls = 0;
			numOuts = 0;
		}
	}
	
	/**
	 * This method resets the number of balls and strikes when a runner gets out.
	 */
	private void out() {
		numStrikes = 0;
		numBalls = 0;
		numOuts++;
		
		checkLimits();
	}
	
	/**
	 * This method shifts the runners on the bases according to the previous hit.
	 * @param times - this is how many times the bases will be shifted.
	 */
	private void shiftRunners(int times) {
		// This will always run whenever the method is called.
		if (bases[2] == 'X')
			score();
		for (int j = 2; j > 0; j--) {
			bases[j] = bases[j- 1];
		}
		bases[0] = 'X';
			
		// This for loop will run if the batter hits more than a single.
		for (int i = 0; i < times - 1; i++) {
			if (bases[2] == 'X')
				score();
			for (int j = 2; j > 0; j--) {
				bases[j] = bases[j- 1];
			}
			bases[0] = 'o';
		}
	}
	
	/**
	 * This method adds runs to the team which is currently batting
	 */
	private void score() {
		if (isTopOfInning)
			team0Score++;
		else
			team1Score++;
	}
	
	
	
					// Accessor Methods
	
	/**
	 * Returns true if the game is over, false otherwise.
	 * @return 
	 */
	public boolean gameEnded() {
		return (currentInning > maxInnings);
	}
	
	/**
	 * Returns the count of balls for the current batter.
	 * @return numBalls
	 */
	public int getBallCount() {
		return numBalls;
	}
	
	/**
	 * Returns the number of called strikes for the current batter.
	 * @return numStrikes
	 */
	public int getCalledStrikes() {
		return numStrikes;
	}
	
	/**
	 * Returns the number of outs for the team currently at bat.
	 * @return numOuts
	 */
	public int getCurrentOuts() {
		return numOuts;
	}
	
	/**
	 * Returns the score for team 0.
	 * @return team0Score
	 */
	public int getTeam0Score() {
		return team0Score;
	}
	
	/**
	 * Returns the score for team 1.
	 * @return team1Score
	 */
	public int getTeam1Score() {
		return team1Score;
	}
	
	/**
	 * Returns true if it's the first half of the inning (team 0 is at bat).
	 * @return isTopOfInning
	 */
	public boolean isTopOfInning() {
		return isTopOfInning;
	}
	
	/**
	 * Returns true if there is a runner on the indicated base, false otherwise.
	 * @param which - base number to check
	 * @return true if the desired base has a value of 'X', false otherwise.
	 */
	public boolean runnerOnBase(int which) {
		return (bases[which - 1] == 'X');
	}
	
	/**
	 * Returns the current inning.
	 * @return currentInning
	 */
	public int whichInning() {
		return currentInning;
	}
	
  
	// The methods below are provided for you and you should not modify them.
	// The compile errors will go away after you have written stubs for the
	// rest of the API methods.
	/**
	 * Returns a three-character string representing the players on base, in the
	 * order first, second, and third, where 'X' indicates a player is present and
	 * 'o' indicates no player. For example, the string "oXX" means that there are
	 * players on second and third but not on first.
	 * 
	 * @return three-character string showing players on base
	 */
	public String getBases()
	{
		return (runnerOnBase(1) ? "X" : "o") + (runnerOnBase(2) ? "X" : "o")
			+ (runnerOnBase(3) ? "X" : "o");
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <pre>
	 *      ooo Inning:1 [T] Score:0-0 Balls:0 Strikes:0 Outs:0
	 * </pre>
	 * The first three characters represent the players on base as returned by the
	 * <code>getBases()</code> method. The 'T' after the inning number indicates
	 * it's the top of the inning, and a 'B' would indicate the bottom. The score always
	 * shows team 0 first.
	 * 
	 * @return a single line string representation of the state of the game
	 */
	public String toString()
	{
		String bases = getBases();
		String topOrBottom = (isTopOfInning() ? "T" : "B");
		String fmt = "%s Inning:%d [%s] Score:%d-%d Balls:%d Strikes:%d Outs:%d";
		return String.format(fmt, bases, whichInning(), topOrBottom, getTeam0Score(),
				getTeam1Score(), getBallCount(), getCalledStrikes(), getCurrentOuts());
	}
}