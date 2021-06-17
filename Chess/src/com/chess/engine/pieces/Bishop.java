package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Tile;

public class Bishop extends Piece{

	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES= {-9,-7,7,9};
	
	
	Bishop(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		
		final List <Move> legalMoves= new ArrayList<>();
		for(final int candidateCoordinateoffset : CANDIDATE_MOVE_VECTOR_COORDINATES)
		{
			int candidateDestinationCoordinate = this.piecePosition;
			
			while(BoardUtils.isValidCoordinate(candidateDestinationCoordinate))
			{
				if(isFirstColumlnExclusion(candidateDestinationCoordinate, candidateCoordinateoffset) || isEighthColumnExclusion(candidateDestinationCoordinate,candidateCoordinateoffset))
				{
					//here it is difference break;
					continue;
		
				}
				candidateDestinationCoordinate+=candidateCoordinateoffset;
				if(BoardUtils.isValidCoordinate(candidateDestinationCoordinate))
				{
					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
					if(!candidateDestinationTile.isTileOccupied())
					{
						legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
					}
					else
					{
						final Piece pieceAtDestination = candidateDestinationTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
						if(this.pieceAlliance!=pieceAlliance)
						{
							legalMoves.add(new Move.AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
						}
						break;
					}
				}
			}
		}
		return legalMoves;
		
	}
	
	private static boolean isFirstColumlnExclusion(final int currentCoordinate,final int candidateoffset)
	{
		return BoardUtils.FIRST_COLOUMN[currentCoordinate] && ((candidateoffset==-9) || (candidateoffset==7));
	}
	
	private static boolean isEighthColumnExclusion(final int currentCoordinate,final int candidateoffset)
	{
		return BoardUtils.EIGHTH_COLUMN[currentCoordinate] && ((candidateoffset==-7) || (candidateoffset)==9);
	}

}
