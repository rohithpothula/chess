package com.chess.engine.pieces;

import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;


public abstract class Piece { 
	
	protected final int piecePosition;
	protected final Alliance pieceAllience;
	
	Piece(final int piecePosition,final Alliance pieceAlliance)  
	{
		this.piecePosition=piecePosition;
		this.pieceAllience=pieceAlliance;
		
	}
	
	public Alliance getPieceAlliance()
	{
		return this.pieceAllience;
	}
	
	 public abstract List<Move> calculateLegalMoves(final Board board);
	 
}
