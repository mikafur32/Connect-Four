public class Board
{
	private int currentPlayer; 
	private final Player[][] spaces; 
	private int numPlayers;
	private int columns; 
	private int rows;
	
	public Board(int columns, int rows, int players)
	{
		this.columns = columns;
		this.rows = rows;
		spaces = new Player[columns][rows];
		numPlayers = players; 
		currentPlayer = 0;			
	}
	
	public int drop(int column)
	{
		return drop(column, currentPlayer);
	}

	public static boolean isValid(int column, int row)
	{
		
		if (column + row >= 6 && (column >= 4 || row >= 4))
		{
			return true;
		}
		return false;
	}


	public int drop(int column, int player)
	{
		int present = 0; //height of the board 
		
		while(spaces[column][present] == null)
		{   
			if(spaces[column][present] != null || present == rows - 1)
			{ 
				break;
			}
			present++;
		}
		Player spot = spaces[column][present];
		if(currentPlayer == 0)
		{
			spot.setPiece(Piece.Red); 
		}
		if(currentPlayer == 1)
		{
			spot.setPiece(Piece.Green); 
		}
		if(currentPlayer == 2)
		{
			spot.setPiece(Piece.Blue); 
		}
		if(currentPlayer == 3)
		{
			spot.setPiece(Piece.Purple); 
		}
		
		currentPlayer++;
		if(currentPlayer % numPlayers == 0) 
		{
			currentPlayer = 0;
		}
		
		return present;
	}
	public int getCurrentPlayer()
	{
		return currentPlayer;
	}
	public int getLength()
	{
		return spaces.length;
	}

	public int getHeight()
	{
		return spaces[0].length;
	}
	
	public int playerPiece(int column, int row)
	{
		if(spaces[column][row] == null)
		{
			return 0;
		}
		return spaces[column][row].getPiece() == Piece.Red ? 1
			: spaces[column][row].getPiece() == Piece.Green ? 2
			: spaces[column][row].getPiece() == Piece.Blue ? 3
			: spaces[column][row].getPiece() == Piece.Purple ? 4
			: 0;
	}
	
	public boolean isColumnFull(int column)
	{
		return playerPiece(column - 1, 0) == 0 ? false : true;
	}
	
	public boolean isFull(Board board)
	{
		for(int i = 0; i < spaces.length; i++)
		{
			for(int j = 0; j < spaces[0].length; j++)
			{
				if(spaces[i][j] == null)  
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean winConditions(int x, int y) 
	{
		if(streak(x, y, 0, 1) >= 4 ||
			   streak(x, y, 1, 1) >= 4 ||
			   streak(x, y, 1, 0) >= 4 ||
			   streak(x, y, 1, -1) >= 4)
		{
			return true;
		}
		return false;
	}
	
	private int streak(int col, int row, int changeX, int changeY)
	{
		int streak = 0;
		if(spaces[col][row] == null)
		{
			return 0;
		}
		Piece piece = spaces[col][row].getPiece();

		while(spaces[col][row].getPiece() == piece && spaces[col][row].getPiece() != null) 
		{ 
			
			col = col + changeX;
			row = row + changeY;
			streak++;

			if(spaces[col][row].getPiece() == null)
			{
				return 0;
			}
			if(col == columns - 1 || row == rows - 1)
			{
				return streak;
			}

		}
		return streak;
	}
} 
     



