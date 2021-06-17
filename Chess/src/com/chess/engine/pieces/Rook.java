package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tile;

public class Rook extends Piece{

	private static int[] CANDIDATE_MOVE_COORDINATES= {-1,1,-8,8};
	
	Rook(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		
		final List<Move> legalmoves= new ArrayList<>();
		
		for(final int currentCoordinateoffset : CANDIDATE_MOVE_COORDINATES)
		{
			int candidateDestinationCoordinate=this.piecePosition;
			
			if(isFirstColumnExclusion(candidateDestinationCoordinate, currentCoordinateoffset) || isEighthColumnExclusion(candidateDestinationCoordinate,currentCoordinateoffset))
			{
				//here it is difference break;
				continue;
			}
			while(BoardUtils.isValidCoordinate(candidateDestinationCoordinate))
			{
				candidateDestinationCoordinate+=currentCoordinateoffset;
				if(BoardUtils.isValidCoordinate(candidateDestinationCoordinate))
				{
					final Tile candidateDestinationaTile = board.getTile(candidateDestinationCoordinate);
					if(!candidateDestinationaTile.isTileOccupied())
					{
						legalmoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
					}
					else
					{
						final Piece pieceAtDestination = candidateDestinationaTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
						if(this.pieceAlliance==pieceAlliance)
						{
							legalmoves.add(new Move.AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
						}
					}
				}
			}
			
		}
		return legalmoves;
	}
	private static boolean isFirstColumnExclusion(final int currentCoordinate,final int currentoffset) {
		return BoardUtils.FIRST_COLOUMN[currentCoordinate] && ((currentoffset==-1));
	}
	private static boolean isEighthColumnExclusion(final int currentCoordinate,final int currentoffset)
	{
		return BoardUtils.EIGHTH_COLUMN[currentCoordinate] && ((currentoffset==1));
	}
	
}
