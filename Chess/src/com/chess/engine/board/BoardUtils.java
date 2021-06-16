package com.chess.engine.board;

public class BoardUtils {
	 
	public static boolean isValidCoordinate(int candidateDestinationCoordinate) {
		 return candidateDestinationCoordinate>=0 && candidateDestinationCoordinate<64;
	}
}
