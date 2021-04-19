import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

/**
 * MonteCarloPlayerFJ is a type of player for Numerical Tic-Tac-Toe that makes the best move. That is, whatever move that gives it the highest likelihood of winning
 * MonteCarloPlayerFJ behaves similarly to MonteCarloPlayer, in that it makes every possible move in a different copy NTTTGameState object and sends each copy gameState off to a GameSimulatorFJ object
 * ---MonteCarloPlayerFJ differs in that GameSimulatorFJ uses ForkJoin to speed up the process
 * ---It uses the line "ForkJoinPool.commonPool().invoke(gameSim);" instead of calling the gameSim.compute() method
 * 
 * @author Blake
 *
 */
public class MonteCarloPlayerFJ implements Player {
    
    /**
     * This is the side that the MonteCarloPlayerFJ is. It'll be either PlayerSide.ODD or PlayerSide.EVEN 
     */
    private PlayerSide side;
    
    /**
     * This is the constructor for the MonteCarloPlayerFJ class. It just assigns the PlayerSide of the MonteCarloPlayerFJ
     * @param side is Enum that determines what side the MonteCarloPlayerFJ is
     */
    public MonteCarloPlayerFJ(PlayerSide side) {
        this.side = side;      
    }
    
    /** 
     * This is the method that makes the move for MonteCarloPlayerFJ
     * This method takes each possible move, creates a copy NTTTGameState object, and plays that move in the copyGameState
     * ---If this move is a winning move, it makes that move immediately and returns 
     * ---Otherwise, it sends that copyGameState with the move made to a GameSimulatorFJ object, which will simulate 5000 games of that game state, using RandomPlayers to make random moves for both sides
     * ---After GameSimulatorFJ completes all the simulations (Using ForkJoin), MonteCarloPlayerFJ retrieves the wins and losses of the 5000 simulated games. 
     * ---It then creates that moves winPercentage, which is its (wins - losses) / 5000
     * ---After sending all possible moves to GameSimulatorFJ objects, it makes the move  with the highest winPercentage.
     * -----This is done by storing the movePosition and moveValue when the biggest winPercentage was generated
     * 
     */
    @Override
    public void getNextMove(NTTTGameState gameState) {
        GameSimulatorFJ gameSim;
        NTTTGameState copyGameState;
        int oddWins = 0;
        int evenWins = 0;
        double winPercentage = -100;
        int bestMovePosition = 0;
        int bestMoveValue = 0;
      
        ArrayList<Integer> availablePositions = gameState.getAvailablePositions();
        ArrayList<Integer> availableValues = gameState.getAvailableValues(side);
                
        System.out.println("MonteCarloFJ is thinking...");
        
        for (int i = 0; i < availablePositions.size(); i++) {
            for (int j = 0; j < availableValues.size(); j++) {
                copyGameState = new NTTTGameState(gameState);
                copyGameState.makeMove(availablePositions.get(i), availableValues.get(j));
                
                // Checks to see if this move is a win
                if (copyGameState.checkForWin()) {
                    gameState.makeMove(availablePositions.get(i), availableValues.get(j));
                    return;
                }
                                
                // Hand this game state off to game Simulator
                gameSim = new GameSimulatorFJ(copyGameState, 5000);
                
                ForkJoinPool.commonPool().invoke(gameSim);
                
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
 
                
                

    
    

