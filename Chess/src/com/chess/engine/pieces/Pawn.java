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

public class Pawn extends Piece{

	private final static int[] CANDIDATE_MOVE_COORDINATES= {8,16,7,9};
	public Pawn(final int piecePosition,final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		final List<Move> legalmoves=new ArrayList<>();
		
		for(final int currentCandidateoffset : CANDIDATE_MOVE_COORDINATES)
		{
			final int candidateDestinationCoordinate = this.piecePosition+ (currentCandidateoffset)*this.pieceAlliance.getDirection();
			
			if(!BoardUtils.isValidCoordinate(candidateDestinationCoordinate))
			{
			continue;	
			}
			if(currentCandidateoffset==8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied())
			{
//				here we are supposed to add more here we need to deal with promotions
				legalmoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
			}
			else if((currentCandidateoffset==16 && this.isFirstMove()) && ((BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) || (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack())))
			{
				final int behindCandidateDestinationCoordinate = this.piecePosition+(this.pieceAlliance.getDirection())*8;
				if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() && !board.getTile(candidateDestinationCoordinate).isTileOccupied())
				{
					legalmoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
				}
			}
			else if(currentCandidateoffset==7 && ((BoardUtils.FIRST_COLOUMN[candidateDestinationCoordinate] && this.pieceAlliance.isBlack()) || (BoardUtils.EIGHTH_COLUMN[candidateDestinationCoordinate]) && this.pieceAlliance.isWhite()))
			{
				final Piece pieceAtDestination = board.getTile(candidateDestinationCoordinate).getPiece();
				if(this.pieceAlliance==pieceAlliance)
				{
//					we are supposed to work on this
					legalmoves.add(new Move.AttackMove(board,this, candidateDestinationCoordinate, pieceAtDestination));
				}
			}
			else if(currentCandidateoffset==9 && ((BoardUtils.FIRST_COLOUMN[candidateDestinationCoordinate] && this.pieceAlliance.isWhite()) || (BoardUtils.EIGHTH_COLUMN[candidateDestinationCoordinate] && this.pieceAlliance.isBlack())))
			{
				final Piece pieceAtDestination = board.getTile(candidateDestinationCoordinate).getPiece();
				if(this.pieceAlliance==pieceAlliance)
				{
//					we should do more work on this
					legalmoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination)); 
				}
				
			}
			
		}
		return legalmoves;
	}
	
	
	@Override 
	public String toString()
	{
		return PieceType.PAWN.toString();
	}

}
