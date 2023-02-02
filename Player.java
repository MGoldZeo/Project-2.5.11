import java.util.InputMismatchException;
import java.util.Scanner;

public class Player
{
    public int playernum;
    private int score = 0;
    private int pb = 0;
    private int superscore;
    private String name;
    private boolean out = false;
    private boolean auto;

    /**
     * This constructor takes in a boolean saying whether or not to get initialised as a computer
     * It also takes in a number to set as the player number
     * Finally, it takes in the player name from user input
     * @param num
     * @param ai
     */
    public Player(int num, boolean ai )
    {
        auto = ai;
        playernum = num;
        Scanner nminp = new Scanner(System.in);
        if (!auto)
        {
            System.out.println("What is your name?");
            System.out.println("They call me:");
            name = nminp.nextLine();
        }
        else
        {
            name = "CPU";
        }

    }

    public void ScoreIncrement(int tileamount)
    {
        score += tileamount;
    }

    /**
     * This discusses the case in which the player loses
     * It sets them to out and clears their score and other instance variables
     */
    public void lose()
    {
        wipe();
        out = true;
    }

    /**
     * This discusses the case in which the player wins
     * It increases the superscore and clears their score and other instance variables
     */
    public void win()
    {
        wipe();
        this.superscore++;
    }

    /**
     * This resets the instance variables and sets the personal best
     */
    public void wipe()
    {
        if (this.score >= this.pb)
        {
            this.pb = this.score;
        }
        this.playernum = 0;
        this.score = 0;
    }

    /**
     * This comprises the main functionality of the player
     * It will take pieces from the gameboard and increment the score
     * It ensures that the amount of tiles is between one and half of the total
     * @param F
     * @return
     */
    public boolean takePieces(Gameboard F)
    {
        // This is the amount of tiles that are taken, it will change based on user input
        int p = 0;
        if (!this.auto)
        {
            Scanner pointsworthy = new Scanner(System.in);
            System.out.println("How many pieces will you take, " + this.getName() + "?:");
            while (p == 0)
            {
                p = getP(p, pointsworthy);
            }
            while ((p < 1 || p > (F.getPiecesLeft() / 2)) && F.getPiecesLeft() > 1)
            {
                System.out.println("Sorry, please enter a value that is between one and half of the total pieces left: ");
                p = pointsworthy.nextInt();
            }
        }
        else
        {
            p =  (int) (Math.random()*(F.getPiecesLeft()/2) + 1);
            System.out.println("The CPU has taken " + p + " pieces.");
        }
        ScoreIncrement(p);
        F.setPiecesLeft(p);
        return F.getPiecesLeft() == 0;
    }

    private static int getP(int p, Scanner pointsworthy)
    {
        try
        {
            p = pointsworthy.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.println("Enter an integer: ");
            pointsworthy.nextLine();
        }
        return p;
    }


    public boolean isOut()
    {
        return out;
    }

    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }

    public int getPb()
    {
        return pb;
    }

    public int getSuperscore()
    {
        return superscore;
    }

    public boolean isAuto()
    {
        return auto;
    }

    /**
     * This is an overloaded version of the clear method
     * It is specifically designed for the CPU player in the instance that they are replaced
     * @param CPU
     */
    public void clear(boolean CPU)
    {
        auto = CPU;
        score = 0;
        superscore = 0;
        name = "";
        out = false;
        Scanner sc = new Scanner(System.in);
        if (!CPU)
        {
            System.out.println("What is the new player's name?");
            System.out.println("Their name is: ");
            this.name = sc.nextLine();
        }
        else
        {
            name = "CPU";
        }
    }

}
