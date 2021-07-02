package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.pieces.Piece.PieceType;
import com.chess.engine.board.Tile;

public class King extends Piece{

	private static final int[] CANDIDATE_MOVE_COORDINATES= {1,-1,7,8,9,-7,-8,-9};
	
	public King(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		
		final List<Move> legalmoves = new ArrayList<>();
		
		for(int currentCandidateoffset : CANDIDATE_MOVE_COORDINATES)
		{
			final int candidateDestinationCoordinate=this.piecePosition+currentCandidateoffset;
			
			if(!BoardUtils.isValidCoordinate(candidateDestinationCoordinate))
			{
				continue;
			}
			if(isFirstColumnExclusion(this.piecePosition, currentCandidateoffset) || isEighthColumnExclusion(this.piecePosition, currentCandidateoffset))
			{
				continue;
			}
			final Tile candidateDestinationTile=board.getTile(candidateDestinationCoordinate);
			if(!candidateDestinationTile.isTileOccupied())
			{
				legalmoves.add(new Move.MajorMove(board,this, candidateDestinationCoordinate));
			}
			else
			{
				final Piece pieceAtDestination=board.getTile(candidateDestinationCoordinate).getPiece();
				if(this.pieceAlliance!=pieceAlliance)
				{
					legalmoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
				}
			}
		}
		return legalmoves;
	}
	@Override 
	public String toString()
	{
		return PieceType.KING.toString();
	}
	
	private static boolean isFirstColumnExclusion(final int currentposition,final int candidateoffset)
	{
		return (BoardUtils.FIRST_COLOUMN[currentposition]) && ((candidateoffset==-9) || (candidateoffset==7) || (candidateoffset==-1));
	}
	
	private static boolean isEighthColumnExclusion(final int currentposition,final int candidateoffset)
	{
		return (BoardUtils.EIGHTH_COLUMN[currentposition]) && ((candidateoffset==9) || (candidateoffset==7) || (candidateoffset==1));
	}
	
	
	
}
