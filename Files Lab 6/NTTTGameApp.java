import java.util.Scanner;

/**
 * Driver class for NTTT game.  
 * It asks the user for which types of players will be playing in an individual game of Numerical Tic Tac Toe
 * After initializing these players, it runs a loop that calls getNextMove until the game ends, alternating the turns for the players
 * The game prints the board after each turn, and after one of the players wins, it will print a statement declaring a winner
 * 
 * @author Blake Robinson + Dr. Yilek
 *
 */
public class NTTTGameApp {

    public static void main(String[] args) {
        
        
        NTTTGameState board = new NTTTGameState();
        Player[] players = new Player[2];
        int turn = 0;
        Scanner keyboard = new Scanner(System.in);
        String playerChoiceOdd;
        String playerChoiceEven;
        
        
        System.out.println("Welcome to Numerical Tic-Tac-Toe.\nPlease select an ODD Player");
        System.out.println("1) Human\n2) Random+\n3) MonteCarlo\n4) MonteCarloFJ");
        System.out.print("Your selection: ");
        playerChoiceOdd = keyboard.nextLine();
        if (playerChoiceOdd.equals("1")) {
            players[0] = new HumanPlayer(PlayerSide.ODD);
        } else if (playerChoiceOdd.equals("2")) {
            players[0] = new RandomPlayer(PlayerSide.ODD);
        } else if (playerChoiceOdd.equals("3")) {
            players[0] = new MonteCarloPlayer(PlayerSide.ODD);
        } else {
            players[0] = new MonteCarloPlayerFJ(PlayerSide.ODD);
        }
        
        
        System.out.println("Please select an EVEN player\n1) Human\n2) Random+\n3) MonteCarlo\n4) MonteCarloFJ");
        System.out.print("Your Selection: ");
        playerChoiceEven = keyboard.nextLine();
        if (playerChoiceEven.equals("1")) {
            players[1] = new HumanPlayer(PlayerSide.EVEN);
        } else if (playerChoiceEven.equals("2")) {
            players[1] = new RandomPlayer(PlayerSide.EVEN);
        } else if (playerChoiceEven.equals("3")) {
            players[1] = new MonteCarloPlayer(PlayerSide.EVEN);
        } else {
            players[1] = new MonteCarloPlayerFJ(PlayerSide.EVEN);
        }
        
        while (!board.gameOver()) {
            System.out.print(board);
            players[turn%2].getNextMove(board);
            turn = turn+1;
        }
        System.out.print(board);
        
        
        if (board.checkForWin()) {
            System.out.println("Winner "+board.getWinner()); 
        } else {
            System.out.println("DRAW");
        }
        
    }
}
