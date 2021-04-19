import java.util.ArrayList;
import java.util.Random;

/**
 * RandomPlayer is a type of player  for Numerical Tic-Tac-Toe that makes random moves
 * RandomPlayer checks to see if it can win within one move
 * ---If it finds this winning move, it will make it and win
 * ---Otherwise, it will make a random move
 * 
 * 
 * @author Blake + Dr. Yilek
 *
 */
public class RandomPlayer implements Player {

    /**
     * This is the side that the RandomPlayer is. It'll be either PlayerSide.ODD or PlayerSide.EVEN
     */
    private PlayerSide side;
               
    /**
     * This is the constructor for the RandomPlayer class
     * 
     * @param side is the PlayerSide enum that determines what side the RandomPlayer is
     */
    public RandomPlayer(PlayerSide side) {
        this.side = side;
    }
           
    /** 
     * This method is what makes a move for the RandomPlayer object
     * It first checks to see if a winning move can be made by creating a copy gameState for every possible move, and seeing if that move is a win
     * If it doesn't find a winning move, it makes a random move. Finally, it sends the move to the makeMove method in NTTTGameState
     * 
     * @param gameState is the current game state 
     */
    @Override
    public void getNextMove(NTTTGameState gameState) {
                
        ArrayList<Integer> availablePositions = gameState.getAvailablePositions();
        ArrayList<Integer> availableValues = gameState.getAvailableValues(side);
        // You should put code here
        
        NTTTGameState copyGameState;
        
        for (int i = 0; i < availablePositions.size(); i++) {
            for (int j = 0; j < availableValues.size(); j++) {
                copyGameState = new NTTTGameState(gameState);
                copyGameState.makeMove(availablePositions.get(i), availableValues.get(j));
                
                if (copyGameState.checkForWin()) {
                    gameState.makeMove(availablePositions.get(i), availableValues.get(j));
                    return;
                }
            }
        }

        // Dr. Yilek provided code starts here    
        Random randomGenerator = new Random(); 

        int chosenPositionIndex = randomGenerator.nextInt(availablePositions.size());
        int chosenValueIndex = randomGenerator.nextInt(availableValues.size());
        
        int positionToPlay = availablePositions.get(chosenPositionIndex);
        int valueToPlay = availableValues.get(chosenValueIndex);
        gameState.makeMove(positionToPlay, valueToPlay);
        
            
    }

    
    
}
