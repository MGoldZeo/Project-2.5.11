import java.util.Scanner;

public class Runner
{
    public static void main(String[] args)
    {
        int p = (int) (Math.random() * 2);
        Player player1 = new Player(p);
        Player player2 = new Player(1 - p);
        Gameboard game = new Gameboard();
        Play(player1, player2, game);
    }

    private static void Play(Player player1, Player player2, Gameboard game)
    {
        System.out.println(player1.getName() + " has won " + player1.getSuperscore() + " games and " + player2.getName() + " has won " + player2.getSuperscore() + " games so far");
        if (player1.playernum == 0)
        {
            while (game.getPiecesLeft() > 0)
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("There are " + game.getPiecesLeft() + " tiles left. " + player1.getName() + " has " + player1.getScore() + " points, and " + player2.getName() + " has " + player2.getScore() + " points.");
                if (player1.takePieces(game))
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

    public static void playAgain(Gameboard game, Scanner sc, Player p1, Player p2)
    {
        System.out.println("Play again? (Y/N)");
        if (game.ynCheck(sc.nextLine()))
        {
            System.out.println("Would you like to replace the lowest scoring player? (Y/N)");
            if (game.ynCheck(sc.nextLine()))
            {
                if (p1.isOut())
                {
                    p1.clear();
                }
                else
                {
                    p2.clear();
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