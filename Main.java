import java.util.Scanner;


public class Main{
	
	public static void main ( String []args){
		
	    String name; 
	    String playFirst="Null";
	    int depth , row , col;
	    String symbolPlayer , symbolComputerPlayer , currentSymbol;
	    Board board=new Board();
		Scanner s = new Scanner(System.in);
		System.out.println("....Welcome to the game....");
		System.out.println("Hello, my name is Madapo!What's your name?");
		name=s.nextLine();
		while(name.equals("Madapo")){
			System.out.println("That's my name ...Give me another one...");
			name=s.nextLine();
		}
		System.out.println("Because I am a kind person...Maybe do you want to play first?(Yes/No)");
		playFirst=s.nextLine();
	    while (!(playFirst.equals("Yes") || playFirst.equals("No"))){
			System.out.println("You gave me wrong answer...Try again(Yes or No)");
			playFirst=s.nextLine();
		}
		
		System.out.println("Now give me the maxDepth..:");
		depth=s.nextInt();
		while(depth<=0){
			System.out.println("You give me wrong number.You must give a number>=1");
			depth=s.nextInt();
		}
		
		if (playFirst.equalsIgnoreCase("Yes")){
			symbolPlayer="B";
			symbolComputerPlayer="W";
			System.out.println("You have the black pieces.Your symbol is <B>.");
		}else{
			symbolPlayer="W";
			symbolComputerPlayer="B";
			System.out.println("You have the white pieces.Your symbol is <W>.");
		}
			
		ComputerMove cm=new ComputerMove(symbolComputerPlayer, depth , symbolPlayer);
		currentSymbol="B"; //Black starts first
		
		while(true){
			
		     if(board.isOver(symbolPlayer) && board.isOver(symbolComputerPlayer)){
				 break;
			 }else if (board.isOver(symbolPlayer)){
				 currentSymbol=symbolComputerPlayer;
		    }else if(board.isOver(symbolComputerPlayer)){
				currentSymbol=symbolPlayer;
			}
			board.printBoard();
			System.out.println("\nBlack :"+ board.getSumBlack()+ "||" + "White :" + board.getSumWhite());
				 
			    
			//......Computer's turn......
			if (currentSymbol.equalsIgnoreCase(symbolComputerPlayer)){
				System.out.println("\n...Computer is playing...");
				Position pos=cm.MiniMax(board);
				board.isValidMove(pos.getRow() , pos.getCol() ,symbolComputerPlayer);
				currentSymbol=symbolPlayer; //change player
			
            //.......Human's turn.......			
			}else{
				System.out.println("\nGive me the number of row: ");
				row=s.nextInt();
			    while(row<=0 || row >=9){
			        System.out.println("You give me wrong number.You must give a number>=1 and number<9");
			        row=s.nextInt();
		        }
				System.out.println("Give me the number of column: ");
				col=s.nextInt();
				while(col<=0 || col>=9){
			        System.out.println("You give me wrong number.You must give a number>=1 and number<9");
			        col=s.nextInt();
		        }
				if(board.check( row,col ,symbolPlayer )){
					System.out.println("Your choice is valid");
					board.isValidMove(row , col , symbolPlayer);
					currentSymbol=symbolComputerPlayer;  //Valid Move , so change player
					
			    //If the move is not valid the currentSymbol won't change , so it will be human's turn again
				}else{
					System.out.println("Please try again");
				}
			}
		}
		board.printBoard();
		System.out.println("Black :"+ board.getSumBlack()+ "||" + "White :" + board.getSumWhite());
		//Print results 
		if (board.getSumBlack()>board.getSumWhite()){
			if(playFirst.equalsIgnoreCase("Yes")){
				System.out.println("Congratulations, " + name + " wins !!!");
			}else{
				
				System.out.println("HA HA HA , I win! Try again..");
			}
		}else if(board.getSumBlack()<board.getSumWhite()){
			if(playFirst.equalsIgnoreCase("No")){
				System.out.println("Congratulations, " + name + " wins !!!");
			}else{
				System.out.println("HA HA HA , I win! Try again..");
			}
        }else{			
			System.out.println("Nobody wins");	
		}
		
	}
	
	
}	
				
				
				
				
				
			