import java.util.ArrayList;
import java.util.Random;

/**
 * GameSimulator is a class that takes in an NTTTGameState object and simulates 5000 games from that position
 * ---It simulates each game one at a time, resulting in the moves being made fairly slowly
 * ---These simulated games are "played" by two RandomPlayer objects that make all random moves, unless a winning move is possible
 * ---It keeps track of the number of oddWins and evenWins, along with the number of draws
 * 
 * @author Blake
 *
 */
public class GameSimulator {
    
    /**
     * gameState is the current state of the game. That is, it keeps track of what the board looks like, what moves and values have already been played, and what moves and values can still be played
     * It also keeps track of whose turn it is
     */
    private NTTTGameState gameState;
    /**
     * This is one of the RandomPlayer objects that plays out the games. This is the Odd Player
     */
    private RandomPlayer playerOdd;
    /**
     * This is the other RandomPlayer object that plays out the games. This is the Even Player
     */
    private RandomPlayer playerEven;
    /**
     * This represents whose turn it currently it
     */
    private PlayerSide currentTurn; 
    /**
     * This keeps a tally of the number of games (out of 5000) the Odd Player has won
     */
    private int oddWins;
    /**
     * This keeps a tally of the number of games (out of 5000) the Even Player has won
     */
    private int evenWins;
    /**
     * This keeps a tally of the number of games (out of 5000) that have drawn
     */
    private int draws;
      
    /**
     * This is the constructor for the GameSimulator class
     * It initializes most of the instance variables
     * 
     * @param gameState is the NTTTGameState that will be simulated 5000 times
     */
    public GameSimulator(NTTTGameState gameState) {
        this.gameState = gameState;
        playerOdd = new RandomPlayer(PlayerSide.ODD);
        playerEven = new RandomPlayer(PlayerSide.EVEN);
        oddWins = 0;
        evenWins = 0;
        draws = 0;
    }
        
    /**
     * This method simulates the gameState object's game 5000 times, and it uses the two RandomPlayer objects to make the moves
     * It simply runs a for loop 5000 times, and within that, it runs a while loop that has each player making moves, alternating turns
     * After an individual game is finished, it checks to see who won, and adds 1 to the count of whichever side won, or to the draw count, if neither player won
     */
    public void compute() {
   
        for (int i = 0; i < 5000; i++) {
            
            NTTTGameState copyGameState = new NTTTGameState(gameState);              
            currentTurn = gameState.whoseTurn();
        
            while (!copyGameState.gameOver()) {
                currentTurn = copyGameState.whoseTurn();    
                if (currentTurn == PlayerSide.ODD) {                 
                    playerOdd.getNextMove(copyGameState);
                } else {
                    playerEven.getNextMove(copyGameState);
                }
            }            
            
            
            
            if (copyGameState.checkForWin()) {
                if (copyGameState.getWinner().equals(PlayerSide.ODD)) {
                   // System.out.println("Odd wins game " + i);
                    oddWins = oddWins + 1;
                } else {
                   // System.out.println("Even wins game " + i);
                    evenWins = evenWins + 1;
                }
            } else {
               // System.out.println("Game " + i + " is a draw");
                draws = draws + 1;
            }
        }
    }
    
    /**
     * This is a getter method for the number of oddWins 
     * @return an integer representing the number of games the oddPlayer won, out of 5000, for this individual game state
     */
    public int getOddWins() {
        return oddWins;
    }
    
    /**
     * This is a getter method for the number of evenWins
     * @return an integer representing the number of games the evenPlayer won, out of 5000, for this individual game state
     */
    public int getEvenWins() {
        return evenWins;
    }
    
    
    
    
    
    
    

}
