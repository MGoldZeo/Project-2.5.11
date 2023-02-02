import java.util.Scanner;

public class Gameboard
{
    private static int totalPieces = 0;
    private int piecesLeft;

    /**
     * This constructor randomises the gameboard and the amount of pieces it has
     * It also initialises the pieces left to be the total amount of pieces
     */
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

    /**
     * This checker is just to ensure whether the answer is yes or no. It will return a boolean accordingly
     * @param ans
     * @return
     */
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

    /**
     * This reconstructs the gameboard, resetting all of the instance variables to the same initial values.
     */
    public void replay()
    {
        totalPieces = (int) (40 * Math.random());
        totalPieces += 10;
        piecesLeft = totalPieces;
    }

}
