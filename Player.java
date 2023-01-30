import java.util.Scanner;

public class Player {
    public int playernum = 0;
    private int score = 0;
    private int pb = 0;
    private int superscore;
    private String name = "";
    private boolean out = false;
    public Player(int num)
    {
        playernum = num;
        Scanner nminp = new Scanner(System.in);
        System.out.println("What is your name?");
        System.out.println("They call me:");
        name = nminp.nextLine();
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
        this.superscore ++;
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
        Scanner pointsworthy = new Scanner(System.in);
        System.out.println("How many pieces will you take, " + this.getName() + "?:");
        int p = pointsworthy.nextInt();
        while ((p < 1 || p > (F.getPiecesLeft()/2)) && F.getPiecesLeft() > 1)
        {
            System.out.println("Sorry, please enter a value that is between one and half of the total pieces left: ");
            p = pointsworthy.nextInt();
        }
        ScoreIncrement(p);
        F.setPiecesLeft(p);
        if (F.getPiecesLeft() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean isOut() {
        return out;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    public int getPb() {return pb;}
    public int getSuperscore() {return superscore;}
    public void clear()
    {
        score = 0;
        superscore = 0;
        name = "";
        out = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the new player's name?");
        System.out.println("Their name is: ");
        this.name = sc.nextLine();
    }

}
