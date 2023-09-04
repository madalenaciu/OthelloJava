import java.util.ArrayList;

public class Board{
	
	String[][] boardGame = new String[9][9];
	String  OpponentPlayerSymbol;
	Position lastMove;
	int sumBlack =2 ;
	int sumWhite=2; //sumBlack=score of black pieces , sumWhite=score of white pieces 
	int weight[][]={{0,0,0,0,0,0,0,0,0},
			            {0,4,-3,2,2,2,2,-3,4},
						{0,-3,-4,-1,-1,-1,-1,-4,-3},
						{0,2,-1,1,0,0,1,-1,2},
						{0,2,-1,0,1,1,0,-1,2},
						{0,2,-1,0,1,1,0,-1,2},
						{0,2,-1,1,0,0,1,-1,2},
						{0,-3,-4,-1,-1,-1,-1,-4,-3},
						{0,4,-3,2,2,2,2,-3,4}};
    //wighted board 
	
    Board(){
		for (int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				boardGame[i][j]="-";
			}
		}
		for (int i=1;i<=8;i++){
			boardGame[0][i]=Integer.toString(i);
			boardGame[i][0]=Integer.toString(i);
		}
		boardGame[4][4]="W";
		boardGame[4][5]="B";
		boardGame[5][5]="W";
	    boardGame[5][4]="B";
	}
	
	//Copy contructor 
	Board (Board board){
		
		for(int i = 0; i < 9; i++){
            for(int j = 0; j <9; j++){
                this.boardGame[i][j] = board.boardGame[i][j];
            }
	    }
	}
	
	
		
	//checks if is a valid move in all directions(up , down , left , right, diagonial)and make the move if it is valid 
	public void isValidMove( int row , int col , String currentPlayer){
		boolean check = false ; //for the sum
		//eksetazei thn idia sthlh pros ta panw
		if (MoveCheck( row-1 , col , -1 ,0 , currentPlayer)){
			    boardGame[row][col]=currentPlayer;
				check = true;
				makeMove( row-1 , col , -1 ,0 , currentPlayer);
				
		}
		//eksetazei diagonia aristera kai pros ta panw(aristera= <--)
		if (MoveCheck(row-1 , col-1 , -1 ,-1 ,currentPlayer)){
			boardGame[row][col]=currentPlayer;
			check = true;
			makeMove(row-1 , col-1 , -1 ,-1,currentPlayer);
			
		}
		//eksetazei diagonia panw kai deksia(-->)
		if (MoveCheck(row-1 , col+1 , -1 ,1,currentPlayer)){
			boardGame[row][col]=currentPlayer;
			check = true;
			makeMove(row-1 , col+1 , -1 ,1,currentPlayer);
			
		}
		//eksetazei idia grammh pros ta deksia 
		if (MoveCheck(row , col+1 , 0 ,1, currentPlayer)){
			boardGame[row][col]=currentPlayer;
			check = true;
			makeMove(row , col+1 , 0 ,1,currentPlayer);
			
		} 
		//eksetazei idia grammh pros ta aristera
		if (MoveCheck(row , col-1 , 0 ,-1,currentPlayer)){
			boardGame[row][col]=currentPlayer;
			check = true;
			makeMove(row , col-1 ,0,-1,currentPlayer);
			
		}
		//eksetazei thn idia sthlh pros ta katw
		if (MoveCheck(row+1 , col , 1 ,0,currentPlayer )){
			boardGame[row][col]=currentPlayer;
			check = true;
			makeMove(row+1 , col , 1 ,0,currentPlayer);
			
		}	
		
		//eksetazei diagonia pros ta katw kai aristera 
		if (MoveCheck(row+1 , col-1 , 1 ,-1,currentPlayer)){
			boardGame[row][col]=currentPlayer;
			check= true;
			makeMove(row+1 , col-1 , 1 ,-1,currentPlayer);
			
			
		}
		//eksetazei diagonia pros ta katw kai deksia
		if (MoveCheck(row+1 , col+1 , 1 ,1 ,currentPlayer )){
			boardGame[row][col]=currentPlayer;
			check = true;
			makeMove(row+1 , col+1 , 1 ,1 ,currentPlayer);	
			
		
		}
		if (check){
			if(currentPlayer.equals("B")){
				sumBlack++;
			}else{
			    sumWhite++;
			}
		}
		lastMove=new Position(row , col , weight[row][col]);
	}
	
	
	//only checks in all directions
	public boolean check( int row , int col,String currentPlayer){
		 
		if (!boardGame[row][col].equals("-")){
			return false;
		}
		
		//eksetazei thn idia sthlh pros ta panw
		if (MoveCheck( row-1 , col , -1 ,0 ,currentPlayer )){
		    return true;
		}
        //eksetazei diagonia aristera kai pros ta panw(aristera= <--)		
		if (MoveCheck(row-1 , col-1 , -1 ,-1,currentPlayer)){
			return true;	
		}
        //eksetazei diagonia panw kai deksia(-->)		
		if (MoveCheck(row-1 , col+1 , -1 ,1,currentPlayer)){
			return true;
		}
        //eksetazei idia grammh pros ta deksia		
		if (MoveCheck(row , col+1 , 0 ,1 ,currentPlayer)){
			return true;
		}
		//eksetazei idia grammh pros ta aristera
		if (MoveCheck(row , col-1 , 0 ,-1,currentPlayer)){
			return true;
		}	
		//eksetazei thn idia sthlh pros ta katw	
		if (MoveCheck(row+1 , col , 1 ,0,currentPlayer)){
			return true;	
		}
		//eksetazei diagonia pros ta katw kai aristera 
		if (MoveCheck(row+1 , col-1 , 1 ,-1,currentPlayer)){
			return true;	
		}
		//eksetazei diagonia pros ta katw kai deksia
		if (MoveCheck(row+1 , col+1 , 1 ,1,currentPlayer )){
			return true;	
		}
		
		return false;
	}
	
    //Checks a move
	public boolean MoveCheck(int row , int col , int RowIncrease , int CollIncrease , String currentPlayer){
		if (currentPlayer.equals("B")){
			OpponentPlayerSymbol="W";
		}else{
			OpponentPlayerSymbol="B";
		}
	    if((row>0) && (row<9) && (col>0)&& (col<9)){
		    if (boardGame[row][col].equalsIgnoreCase(OpponentPlayerSymbol)){
			    while((row>0) && (row<9) && (col>0)&& (col<9)){
				    row+=RowIncrease;
				    col+=CollIncrease;
					if ( row < 9 && col < 9 && col>0 && row>0){
						if(boardGame[row][col].equalsIgnoreCase(currentPlayer)){
					       return true;
						}
						if(boardGame[row][col].equalsIgnoreCase("-")){
					       return false;
						}
					}
                }
            }
	    }
        return false;
	
	}
	
	//make the move by changing all the opponent pieces that current's move cover
	public void makeMove(int row , int col , int RowIncrease , int CollIncrease ,String  currentPlayer ){
		//if..else for the move that has be done in function isValidMove()
		
		while(boardGame[row][col].equalsIgnoreCase(OpponentPlayerSymbol)){
			boardGame[row][col]=currentPlayer;
			if(currentPlayer.equals("B")){
				sumBlack++; //increase score of black pieces 
				sumWhite--; //reduce score of white pieces that will be black
			}else{
				sumWhite++;//increase score of white pieces 
				sumBlack--;//reduce score of black pieces that will be white
			}
			row+=RowIncrease;
			col+=CollIncrease;
		}
	}
	
	//checks if symbol have more valid moves
	public boolean isOver(String symbol){
		 
		for (int i=1;i<9;i++){
		    for(int j=1;j<9; j++){
				if(check( i , j, symbol)){
					return false;
				}
			}
		}
		return true;
	}
	
	
	public ArrayList<Board> getChildren(String symbol) {

        ArrayList<Board> children = new ArrayList<Board>();
        for (int i = 1; i <9; i++) {
            for (int j =1; j<9; j++) {
                if (this.check( i , j,symbol)) {
                    Board child = new Board(this);
                    child.isValidMove(i, j, symbol);
                    children.add(child);
                }
            }
        }
        return children;
    }
	
	//heuristic of the game 
	public int evaluate(){
		 
		int pointsOfBlack=countPoints("B"); //calculates the max utility 
		int pointsOfWhite=countPoints("W"); //calculates the min utility
		return pointsOfBlack-pointsOfWhite; //return the difference
    }		
	 
    //add the weights from table weight of each position with given symbol
    public int countPoints(String symbol){
      		 
		int score=0;
		for (int i=1;i<9;i++){
			for (int j=1;j<9;j++){
			    if (boardGame[i][j].equals(symbol)){
					score+=weight[i][j];
				}
			}
		}
		return score;
	}
	
	
	public Position getMove(){
		return lastMove;
	}
	
	public int getSumBlack(){
		return sumBlack;
	}
	
	public int getSumWhite(){
		return sumWhite;
	}
	
	public void printBoard(){
		for (int i=0;i<9;i++){
			System.out.println(" ");
			for(int j=0;j<9;j++){
				System.out.print(boardGame[i][j]+ "  " );
			}
		}
	}
	 
}
	

	
	
				
				
			
				
				
				
				
				
				
				
				
				
				
				
				
				
		