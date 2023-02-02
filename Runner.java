import java.util.Scanner;

public class Runner
{
    /**
     * This is our main method, running the program
     * @param args
     */
    public static void main(String[] args)
    {
        int p = (int) (Math.random() * 2);
        Player player1 = new Player(p, false);
        Gameboard game = new Gameboard();
        Scanner sc = new Scanner(System.in);
        System.out.println("Play against the computer? (Y/N)");
        Player player2;
        if(game.ynCheck(sc.nextLine()))
        {
            player2 = new Player(1 - p, true);
        }
        else
        {
            player2 = new Player(1 - p, false);
        }
        Play(player1, player2, game);
    }

    /**
     * This method contains all of the code required for gameplay.
     * It makes use of all the methods in the other classes to provide a relevant and immersive gameplay experience
     *
     * @param player1
     * @param player2
     * @param game
     */
    private static void Play(Player player1, Player player2, Gameboard game)
    {
        System.out.println(player1.getName() + " has won " + player1.getSuperscore() + " games and " + player2.getName() + " has won " + player2.getSuperscore() + " games so far");
        if (player1.playernum == 0)
        // This loop is for if player 1's randomly selected player number is zero, and the else loop is for player2
        {
            while (game.getPiecesLeft() > 0)
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("There are " + game.getPiecesLeft() + " tiles left. " + player1.getName() + " has " + player1.getScore() + " points, and " + player2.getName() + " has " + player2.getScore() + " points.");
                if (player1.takePieces(game))
                // This handles the case in which player1 loses by taking the last piece. The other if statement handles the case in which player2 loses. Otherwise the game continues.
                {
                    System.out.println(player2.getName() + " wins with " + player2.getScore() + " points.");
                    player1.lose();
                    player2.win();
                    bigScore(game, sc, player1, player2);
                    playAgain(game, sc, player1, player2);
                }
                System.out.println("It is " + player2.getName() + "'s turn. There are " + game.getPiecesLeft() + " tiles left. ");
                if (player2.takePieces(game))
                {
                    System.out.println(player1.getName() + " wins with " + player1.getScore() + " points.");
                    player2.lose();
                    player1.win();
                    bigScore(game, sc, player1, player2);
                    playAgain(game, sc, player1, player2);
                }
            }
        }
        else
        {
            while (game.getPiecesLeft() > 0)
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("There are " + game.getPiecesLeft() + " tiles left. " + player2.getName() + " has " + player2.getScore() + " points, and " + player1.getName() + " has " + player1.getScore() + " points.");
                if (player2.takePieces(game))
                {
                    System.out.println(player1.getName() + " wins with " + player1.getScore() + " points.");
                    player2.lose();
                    player1.win();
                    bigScore(game, sc, player2, player1);
                    playAgain(game, sc, player2, player1);
                }
                System.out.println("It is " + player1.getName() + "'s turn. There are " + game.getPiecesLeft() + " tiles left. ");
                if (player1.takePieces(game))
                {
                    System.out.println(player2.getName() + " wins with " + player2.getScore() + " points.");
                    player1.lose();
                    player2.win();
                    bigScore(game, sc, player2, player1);
                    playAgain(game, sc, player2, player1);
                }
            }
        }
    }

    /**
     *
     * @param game
     * @param sc
     * @param p1
     * @param p2
     */
    public static void playAgain(Gameboard game, Scanner sc, Player p1, Player p2)
    {
        System.out.println("Play again? (Y/N)");
        if (game.ynCheck(sc.nextLine()))
        {
            System.out.println("Would you like to replace the loser? (Y/N)");
            if (game.ynCheck(sc.nextLine()))
            {
                System.out.println("Would you like to play against the CPU this time? (Y/N)");
                boolean cpunext = game.ynCheck(sc.nextLine());
                if ((p1.isAuto() || p2.isAuto()) && cpunext) {
                    System.out.println("The CPU has been reset.");
                    if (p2.isAuto() && p1.isOut() && cpunext)
                    // These two cases handle the option that the human loses to a computer and wishes to replace the loser with a CPU
                        // It tells the user that one player is already the CPU and changes the human player to the other player slot
                    {
                        Player p3 = p1;
                        p1 = p2;
                        p2 = p3;
                        p1.clear(cpunext);
                    }
                    if (p1.isAuto() && p2.isOut() && cpunext)
                    {
                        Player p3 = p2;
                        p2 = p1;
                        p1 = p3;
                        p2.clear(cpunext);
                    }
                }
                else if (p1.isOut() && !cpunext)
                {
                    p1.clear(cpunext);
                }
                else
                {
                    p2.clear(cpunext);
                }
                Play(p1, p2, game);
            }
            else
            {
                Play(p1, p2, game);
            }
            game.replay();
        }
        else
        {
            System.out.println("Farewell, respected friends " + p1.getName() + " and " + p2.getName() + ". You have enjoyed this game.");
            System.exit(0);
        }
    }

    /**
     * This method handles the high score viewer and returns who the king is.
     * @param game
     * @param sc
     * @param p1
     * @param p2
     */
    public static void bigScore(Gameboard game, Scanner sc, Player p1, Player p2)
    {
        System.out.println("Would you like to view your high scores? (Y/N)");
        if (game.ynCheck(sc.nextLine()))
        {
            System.out.println("Here are your high scores: ");
            System.out.println(p1.getName() + ": " + p1.getPb());
            System.out.println(p2.getName() + ": " + p2.getPb());
        }
        Player king = null;
        if (p1.getPb() > p2.getPb())
        {
            king = p1;
        }
        else if (p2.getPb() > p1.getPb())
        {
            king = p2;
        }
        System.out.println("The king is: " + king.getName());
    }
}