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

    public void lose()
    {
        wipe();
        out = true;
    }

    public void win()
    {
        wipe();
        this.superscore++;
    }

    public void wipe()
    {
        if (this.score >= this.pb)
        {
            this.pb = this.score;
        }
        this.playernum = 0;
        this.score = 0;
    }

    public boolean takePieces(Gameboard F)
    {
        int p;
        if (!this.auto)
        {
            Scanner pointsworthy = new Scanner(System.in);
            System.out.println("How many pieces will you take, " + this.getName() + "?:");
            p = pointsworthy.nextInt();
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
