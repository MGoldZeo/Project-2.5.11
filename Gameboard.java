import java.util.Scanner;

public class Gameboard
{
    private static int totalPieces = 0;
    private int piecesLeft;

    public Gameboard()
    {
        totalPieces = (int) (41 * Math.random());
        totalPieces += 10;
        piecesLeft = totalPieces;
    }

    public int getPiecesLeft()
    {
        return piecesLeft;
    }

    public void setPiecesLeft(int p)
    {
        this.piecesLeft = piecesLeft - p;
    }


    public boolean ynCheck(String ans)
    {
        if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes"))
        {
            return true;
        }
        if (ans.equalsIgnoreCase("n") || ans.equalsIgnoreCase("no"))
        {
            return false;
        }
        else
        {
            while (!ans.equalsIgnoreCase("y") && !ans.equalsIgnoreCase("n") && !ans.equalsIgnoreCase("yes") && !ans.equalsIgnoreCase("no"))
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("Please indicate yes or no.");
                ans = sc.nextLine();
            }
        }
        return ynCheck(ans);
    }

    public void replay()
    {
        totalPieces = (int) (40 * Math.random());
        totalPieces += 10;
        piecesLeft = totalPieces;
    }

}
