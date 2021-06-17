package com.chess.engine.board;	

public class BoardUtils {
	
	public static final boolean[] FIRST_COLOUMN = initColumn(0);
	public static final boolean[] SECOND_COLOUMN = initColumn(1);
	public static final boolean[] SEVENTH_COLOUMN = initColumn(6);
	public static final boolean[] EIGHTH_COLUMN = initColumn(7);
	
	public static int NUM_TILES=64;
	public static int NUM_TILES_PER_ROW=8;
	
	
	private BoardUtils() {
		throw new RuntimeException("you can not instantiate this class");
	}
	
	
	private static boolean[] initColumn(int ColumnNumber) {
		final boolean[] column = new boolean[NUM_TILES];
		while(ColumnNumber<NUM_TILES)
		{
			column[ColumnNumber]=true;
			ColumnNumber+=NUM_TILES_PER_ROW;
		}	
		return column;
	}


	public static boolean isValidCoordinate(final int candidateDestinationCoordinate) {
		 return candidateDestinationCoordinate>=0 && candidateDestinationCoordinate<NUM_TILES;
	}
}
