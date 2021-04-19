import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.RecursiveAction;

/**
 * GameSimulatorFJ is a class that takes in an NTTTGameState object and simulates 5000 games from that position
 * ---Unlike GameSimulator, GameSimulatorFJ uses recursion and ForkJoin to speed up the process of simulating
 * ---It creates new GameSimulatorFJ objects until the games each GameSimulatorFJ objects needs to simulate is only 1 game
 * ---This is significantly faster than a regular GameSimulator
 * ---These simulated games are "played" by two RandomPlayer objects that make all random moves, unless a winning move is possible
 * ---It keeps track of the number of oddWins and evenWins, along with the number of draws
 * 
 * @author Blake
 *
 */
public class GameSimulatorFJ extends RecursiveAction {
    
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
     * This is the number of games that each GameSimulatorFJ object needs to simulate
     * The base case is reached when gamesToSim is equal to 1
     */
    private int gamesToSim;
    
    
    /**
     * This is the constructor for the GameSimulatorFJ class
     * It initializes most of the instance variables
     * 
     * @param gameState is the NTTTGameState object that will eventually be simulated 5000 times
     * @param gamesToSim is the number of games that an individual GameSimulatorFJ object will simulate
     */
    public GameSimulatorFJ(NTTTGameState gameState, int gamesToSim) {
        this.gameState = gameState;
        this.gamesToSim = gamesToSim;
        playerOdd = new RandomPlayer(PlayerSide.ODD);
        playerEven = new RandomPlayer(PlayerSide.EVEN);
        oddWins = 0;
        evenWins = 0;
        draws = 0;
    }

    /** 
     * This method simulates the gameState object's game 5000 times (in total), and it uses two RandomPlayers to make the moves
     * Unlike compute() in GameSimulator, it uses recursion and ForkJoin to speed up the process
     * ---If the base case of gamesToSim==1 is not reached, it will create 2 new GameSimulatorFJ objects until the base case is reached
     * ---As such, each GameSimulatorFJ object will technically only simulate 1 game and compile the results of its previously created GameSimulatorFJ simmed games
     * ---After an individual game is simulated, it adds a tally to the win count of the winner, either oddWins or evenWins
     * ---If neither player wins, a tally is added to the draw count
     * 
     */
    @Override
    protected void compute() {
        NTTTGameState copyGameState = new NTTTGameState(gameState);
        // Base case, just simulate the one game
        if (gamesToSim == 1) {
            // base case sim the game
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
            
        } else {
            // change constructor and add up the results into a new variable
            
            GameSimulatorFJ left = new GameSimulatorFJ(gameState, gamesToSim / 2);
            GameSimulatorFJ right = new GameSimulatorFJ(gameState, gamesToSim / 2);
            left.fork();
            right.compute();
            left.join();    
            oddWins = oddWins + left.oddWins + right.oddWins;
            evenWins = evenWins + left.evenWins + right.evenWins;
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
     * @return an integer representing the number of games the evenWins won, out of 5000, for this individual game state
     */
    public int getEvenWins() {
        return evenWins;
    }
    
    
    
}
