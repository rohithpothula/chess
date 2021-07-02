package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece.PieceType;
import com.chess.engine.board.BoardUtils;

public class Knight extends Piece {

	private final static int[] CANDIDATE_MOVE_COORDINATES = {-17,-15,-10,-6,6,10,15,17};
	
	
	public Knight(final int piecePosition, final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		
		final List<Move> legalMoves=new ArrayList<>();
		
		for(final int currentcandidateoffset : CANDIDATE_MOVE_COORDINATES)
		{
			if(isFirstColumnExclusion(this.piecePosition,currentcandidateoffset) || isSeventhColumnExclusion(this.piecePosition,currentcandidateoffset) || isSecondColumnExclusion(this.piecePosition,currentcandidateoffset) || isEighthColumnExclusion(this.piecePosition,currentcandidateoffset))
			{
				continue;
			}
			final int CandidateDestinationCoordinate=this.piecePosition+currentcandidateoffset;
			if(isValidCoordinate(CandidateDestinationCoordinate))
			{
				final Tile candidateDestinationTile=board.getTile(CandidateDestinationCoordinate);
				if(!candidateDestinationTile.isTileOccupied())
				{
					legalMoves.add(new Move.MajorMove(board,this,CandidateDestinationCoordinate));
				}
				else
				{
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					
					if(this.pieceAlliance!=pieceAlliance)
					{
						legalMoves.add(new Move.AttackMove(board,this,CandidateDestinationCoordinate,pieceAtDestination));
					}
				}
			}
		}
		return legalMoves;
	}
	
	@Override 
	public String toString()
	{
		return PieceType.KNIGHT.toString();
	}
	
	
	private static boolean isFirstColumnExclusion(final int currentposition, final int candidateoffset)
	{
		return BoardUtils.FIRST_COLOUMN[currentposition] && ((candidateoffset==-17) || (candidateoffset==-10) || (candidateoffset==6)|| (candidateoffset==15));
	}
	private static boolean isSecondColumnExclusion(final int currentposition,final int candidateoffset)
	{
		return BoardUtils.SECOND_COLOUMN[currentposition] && ((candidateoffset==-10) || candidateoffset==6);
	}
	private static boolean isSeventhColumnExclusion(final int currentposition,final int candidateoffset)
	{
		return BoardUtils.SEVENTH_COLOUMN[currentposition] && ((candidateoffset==-6) || (candidateoffset==10));
	}
	private static boolean isEighthColumnExclusion(final int currentposition,final int candidateoffset)
	{
		return BoardUtils.EIGHTH_COLUMN[currentposition] && ((candidateoffset==-15) || (candidateoffset==-6) || (candidateoffset==10) || (candidateoffset==17));
	}
	private boolean isValidCoordinate(int candidateDestinationCoordinate) {
		return candidateDestinationCoordinate>=0 && candidateDestinationCoordinate<64;
	}
}
