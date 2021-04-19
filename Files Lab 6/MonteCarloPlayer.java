import java.util.ArrayList;

/**
 * MonteCarloPlayer is a type of player for Numerical Tic-Tac-Toe that makes the best move. That is, whatever move that gives it the highest likelihood of winning
 * MonteCarloPlayer makes each possible move in copy NTTTGameState object and sends each copy GameState to GameSimulator, which simulates that GameState 5000 times
 * ---Taking feedback from GameSimulator, it calculates the win percentage for each move, and determines which move to make by choosing the move with the highest win percentage
 * ---If playing as Odd, MonteCarloPlayer will win every game, even if the Even player plays perfectly
 * 
 * @author Blake
 *
 */
public class MonteCarloPlayer implements Player {
    
    /**
     * This is the side that the MonteCarloPlayer is. It'll be either PlayerSide.ODD or PlayerSide.EVEN 
     */
    private PlayerSide side;
    
    /**
     * This is the constructor for the MonteCarloPlayer class. It just assigns the PlayerSide of the MonteCarloPlayer
     * @param side is Enum that determines what side the MonteCarloPlayer is
     */
    public MonteCarloPlayer(PlayerSide side) {        
        this.side = side;
    }
      
    /** 
     * This is the method that makes a move for MonteCarloPlayer
     * This method takes each possible move, creates a copy NTTTGameState object, and plays that move in the copyGameState
     * ---If this move is a winning move, it makes that move immediately and returns 
     * ---Otherwise, it sends that copyGameState with the move made to a GameSimulator object, which will simulate 5000 games of that game state, using RandomPlayers to make random moves for both sides
     * ---After GameSimulator completes all the simulations, MonteCarloPlayer retrieves the wins and losses of the 5000 simulated games. 
     * ---It then creates that moves winPercentage, which is its (wins - losses) / 5000
     * ---After sending all possible moves to a GameSimulator object, it makes the move  with the highest winPercentage.
     * -----This is done by storing the movePosition and moveValue when the biggest winPercentage was generated
     * 
     */
    @Override
    public void getNextMove(NTTTGameState gameState) {
        GameSimulator gameSim;
        NTTTGameState copyGameState;
        int oddWins = 0;
        int evenWins = 0;
        double winPercentage = -100;
        int bestMovePosition = 0;
        int bestMoveValue = 0;
        
        
        ArrayList<Integer> availablePositions = gameState.getAvailablePositions();
        ArrayList<Integer> availableValues = gameState.getAvailableValues(side);
        
        System.out.println("MonteCarlo is thinking...");
        
        for (int i = 0; i < availablePositions.size(); i++) {
            for (int j = 0; j < availableValues.size(); j++) {
                copyGameState = new NTTTGameState(gameState);
   
                copyGameState.makeMove(availablePositions.get(i), availableValues.get(j));
                
                // Checks to see if this move is a win
                if (copyGameState.checkForWin()) {
                    gameState.makeMove(availablePositions.get(i), availableValues.get(j));
                    return;
                }
                
                gameSim = new GameSimulator(copyGameState);
                gameSim.compute();
                
                oddWins = gameSim.getOddWins();
                evenWins = gameSim.getEvenWins();
                                       
                if (side == PlayerSide.ODD) {
                    if (winPercentage < ((oddWins - evenWins) / 5000.0)) {
                        winPercentage = (oddWins - evenWins) / 5000.0;
                        bestMovePosition = availablePositions.get(i);
                        bestMoveValue = availableValues.get(j);
                    } 
                } else {
                    if (winPercentage < ((evenWins - oddWins) / 5000.0)) {
                        winPercentage = (evenWins - oddWins) / 5000.0;
                        bestMovePosition = availablePositions.get(i);
                        bestMoveValue = availableValues.get(j);
                    }
                }
                
                
                
                
            }
        }
        
        gameState.makeMove(bestMovePosition, bestMoveValue);
        

    }

}
