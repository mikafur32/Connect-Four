import java.util.*;

public class Main
{
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		  
		while(true)
		{

			int players = getValidBoardSize("Enter the number of players: ", "number of players", 4, 2, 0);
			int columnSize = 0;
			int rowSize = 0;
			do
			{
				columnSize = getValidBoardSize("Enter the number of columns: ", "number of columns", 20, 2, players);
				rowSize = getValidBoardSize("Enter the number of rows: ", "number of rows", 20, 2, players);

				if(!Board.isValid(columnSize, rowSize))
				{
					System.out.println("Invalid board size. Make winnable.");
				}
			}
			while(!Board.isValid(columnSize, rowSize));

			Player[] playerArr = new Player[players];
			
			for(int i = 0; i < players; i++)
			{
				String name = getValidPlayer("P" + (i + 1) + " enter your name: ");
				Piece piece = Piece.getColor(i + 1);
				Player player = new Player(name, piece);
				playerArr[i] = player;
			}
			
			
			int column = 0;
			int row = 0;
			
			Board boardState = new Board(columnSize, rowSize, players);
			while(true)
			{
				System.out.print("\u001B[2J");
				System.out.println("     _____                            _     ______               _             ");
				System.out.println("    / ____|                          | |   |  ____|             | |            ");
				System.out.println("   | |     ___  _ __  _ __   ___  ___| |_  | |__ ___  _   _ _ __| |            ");
				System.out.println("   | |    / _ \\| '_ \\| '_ \\ / _ \\/ __| __| |  __/ _ \\| | | | '__| |       ");
				System.out.println("   | |___| (_) | | | | | | |  __/ (__| |_  | | | (_) | |_| | |  |_|            ");
				System.out.println("    \\_____\\___/|_| |_|_| |_|\\___|\\___|\\__| |_|  \\___/ \\__,_|_|  (_)      ");
				System.out.println("                                                                               ");
		
				spacesToString(boardState);
				String currentPlayer = playerArr[boardState.getCurrentPlayer()].getName();

				System.out.println(currentPlayer + " Enter the column:");
				column = getValidColumn(currentPlayer + " Enter the column:", "column", boardState.getLength(), columnSize - 1, boardState);
				
				row = boardState.drop(column);
				
				if(boardState.isFull(boardState) 
					|| boardState.winConditions(column, row))
				{
					break;
				}
			}
			
			if(boardState.winConditions(column, row))
			{
				String winner = playerArr[boardState.getCurrentPlayer()].getName();
				System.out.println(winner + " won!");
			}
			else
			{
				System.out.println("It's a Tie!");
			}
			spacesToString(boardState);
			
			System.out.println("Would you like to play again? Y/N");
		
			String index = "";
			while(true)
			{
				try
				{
					index = scanner.nextLine();
					
					while(!(index.equalsIgnoreCase("N")) || !(index.equalsIgnoreCase("Y")))
					{
						System.out.println("That answer is invalid. Choose again! \n");
						System.out.println("Would you like to play again? Y/N");
						index = scanner.nextLine();
					}
					break; 
				}
				catch(InputMismatchException ex)
				{			
					System.out.println("That answer is invalid. Choose again! \n");				 
				}
			}
			
			if(index.equalsIgnoreCase("N"))
			{
				break;
			}
		}
	}

	public static void spacesToString(Board board)
	{
		for(int i = 0; i < board.getHeight(); i++)
		{
			System.out.print("|  " + i);
			for(int j = 0; j < board.getLength(); j++)
			{
				if(board.playerPiece(j, i) == 1)
				{
					System.out.print("R  ");
				}
				if(board.playerPiece(j, i) == 2)
				{
					System.out.print("G  ");
				}
				if(board.playerPiece(j, i) == 3)
				{
					System.out.print("B  ");
				}
				if(board.playerPiece(j, i) == 4)
				{
					System.out.print("P  ");
				}
				else
				{
					System.out.print("   ");
				}
				
			}
			System.out.println();
		}
		System.out.print("|-");
		for(int i = 0; i < board.getLength(); i++)
		{
			String dash = i >= 10 ? "----" : "----";
			System.out.print(dash);
		}
		System.out.println();
		for(int i = 0; i < board.getLength(); i++)
		{
			String space = i >= 10 ? "  " : "   ";
			System.out.print(space + i);
		}
		System.out.println();		
	} 
	private static int getValidColumn(String prompt, String errorType, int min, int max , Board boardState)
    {
		int index = 0;
		while(true)
		{
			try
			{
				index = Integer.parseInt(scanner.nextLine());
				
				while(index > min || index < 0 || boardState.isColumnFull(index) || index > max)
				{
					System.out.println("That " + errorType + " doesn't exist or is full! Choose again! \n");
					System.out.print(prompt);
					index = Integer.parseInt(scanner.nextLine());
				}
				
				break; 
			}
			catch(NumberFormatException ex)
			{			
				System.out.println("That's not an Integer!");				 
			}
		}
		return index;
	}

	private static int getValidBoardSize(String prompt, String errorType, int max, int min, int MIN)
    {
		int index = 0;
		while(true)
		{
			try
			{
				System.out.println(prompt);
				index = Integer.parseInt(scanner.nextLine());
				
				while(index > max || index < min || index < MIN)
				{
					System.out.println("That " + errorType + " is invalid. Choose again! \n");
					System.out.print(prompt);
					index = Integer.parseInt(scanner.nextLine());
				}
				
				break; 
			}
			catch(NumberFormatException ex)
			{			
				System.out.println("That's not an Integer!");				 
			}
		}
		return index;
	}

	private static String getValidPlayer(String prompt)
	{
		String result = "";
		System.out.println(prompt);
		
		result = scanner.nextLine();
		return result;
	}
	
	
	
	
	
	


}

