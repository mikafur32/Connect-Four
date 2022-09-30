public enum Piece
{
	Red, Green, Blue, Purple;
	
	public static Piece getColor(int order)
	{
		return order == 1 ? Piece.Red
			:  order == 2 ? Piece.Green
			:  order == 3 ? Piece.Blue
			:  Piece.Purple;
	}
}