import java.util.Random;
import java.lang.Math.*;
import java.util.*;

public class ComputerMove{
	
	private String letter , opp;                 //letter=ComputerSymbol , opp=humanSymbol
	private int d;                               //d=maxDepth
	private final int MAX=Integer.MAX_VALUE;     //Integer.MAX_VALUE works like Double.POSITIVE_INFINITY
    private final int MIN=Integer.MIN_VALUE;     //Integer.MIN_VALUE works like Double.NEGATIVE_INFINITY
	
	
	public ComputerMove(String letter , int maxDepth , String Opposite){
		this.letter=letter;
		d=maxDepth;
		opp=Opposite;
	}
	
	Position MiniMax(Board board)
    {
        if(letter.equalsIgnoreCase("B"))
        {
            //If the B plays then it wants to maximize the heuristics value
            return max(new Board(board), 0 ,MIN , MAX );
        }
        else
        {
            //If the W plays then it wants to minimize the heuristics value
            return min(new Board(board), 0 , MIN, MAX );
        }
    }
	

    Position max(Board board, int depth , int alpha , int beta)
    {
        if(board.isOver(letter)|| (depth == d))
        {
            return new Position(board.getMove().getRow(), board.getMove().getCol(),board.evaluate());
        }
        ArrayList<Board> children = board.getChildren(letter);
        Position maxMove = new Position(MIN);
        for(Board child: children)
        {
            Position pos = min(child,depth + 1 , alpha ,beta);
            if(pos.getSymbol() >= maxMove.getSymbol())
            {
				//prionisma kata b
                if(pos.getSymbol()>=beta){
					return pos;
					} 
                maxMove.setRow(child.getMove().getRow());
                maxMove.setCol(child.getMove().getCol());
                maxMove.setSymbol(pos.getSymbol());
                child=null;
               
            }
            alpha=Math.max(alpha,pos.getSymbol()); //change  the limit a 
        }
        return maxMove;
       
    }

    //Min works similarly to max
    Position min(Board board, int depth , int alpha , int beta )
    {
		
        if((board.isOver(letter)||(depth==d)))
        {
            return new Position(board.getMove().getRow(),board.getMove().getCol(),board.evaluate());
          
            
        }
        ArrayList<Board> children =board.getChildren(letter);
        Position minMove=new Position(MAX);
        for(Board child : children)
        { 
            Position pos=max(child,depth+1, alpha , beta);
            if(pos.getSymbol()<=minMove.getSymbol())
            {
				//prionisma kata a
                if(pos.getSymbol()<=alpha) {
					return pos;
					} 
                minMove.setRow(child.getMove().getRow());
                minMove.setCol(child.getMove().getCol());
                minMove.setSymbol(pos.getSymbol());
                child=null;

            }
            beta=Math.min(beta,pos.getSymbol()); //change the limit b
        }
        return minMove;

    }
}