public class Player
{
	private String name;
	private Piece piece;
	
	public Player(String Name, Piece piece)
	{
		this.name = name;
		this.piece = piece;
	}
	
	public Piece getPiece()
	{
		return piece;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setPiece(Piece set)
	{
		piece = set;
	}
}