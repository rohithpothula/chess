package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.BoardUtils;

public class Knight extends Piece {

	private final static int[] CANDIDATE_MOVE_COORDINATES = {-17,-15,-10,-6,6,10,15,17};
	
	
	Knight(final int piecePosition, final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Move> calculateLegalMoves(Board board) {
		
		int CandidateDestinationCoordinate;
		final List<Move> legalMoves=new ArrayList<>();
		
		for(final int currentcandidate : CANDIDATE_MOVE_COORDINATES)
		{
			CandidateDestinationCoordinate=this.piecePosition+currentcandidate;
			
			if(isValidCoordinate(CandidateDestinationCoordinate))
			{
				final Tile candidateDestinationTile=board.getTile(CandidateDestinationCoordinate);
				if(!candidateDestinationTile.isTileOccupied())
				{
					legalMoves.add(new Move());
				}
				else
				{
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					
					if(this.pieceAllience!=pieceAlliance)
					{
						legalMoves.add(new Move());
					}
				}
			}
		}
		return legalMoves;
	}

	private boolean isValidCoordinate(int candidateDestinationCoordinate) {
		// TODO Auto-generated method stub
		return false;
	}
}
