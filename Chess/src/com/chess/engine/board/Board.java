package com.chess.engine.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.Bishop;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Queen;
import com.chess.engine.pieces.Rook;

public class Board {

	private final List<Tile> gameBoard;
	private final Collection<Piece> whitePieces;
	private final Collection<Piece> blackPieces;
	
	private Board(Builder builder)
	{	
		this.gameBoard=createGameBoard(builder);
		this.whitePieces=calculateActivePieces(this.gameBoard,Alliance.WHITE);
		this.blackPieces=calculateActivePieces(this.gameBoard,Alliance.BLACK);
		
		final Collection<Move> whiteStandardLegalMoves = caluculateLegalmoves(this.whitePieces);
		final Collection<Move> balckStandardLegalMoves = caluculateLegalmoves(this.blackPieces);
	}
	private static List<Tile> createGameBoard(final Builder builder) {
		final List<Tile> tiles= new ArrayList<>();
		for(int i=0;i<64;i++)
		{
			tiles.add(Tile.createTile(i,builder.boardConfig.get(i)));
//			tiles[i]=Tile.createTile(i,builder.boardConfig.get(i));
		}
		return tiles;
	}
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		for(int i=0;i<8;i++)
		{ 
			final String tiletext = this.gameBoard.get(i).toString();
			builder.append(String.format("%3s",tiletext));
			if((i+1)%8==0)
			{
				builder.append("\n");
			}
		}
		return builder.toString(); 
	}
	
	private Collection<Move> caluculateLegalmoves(Collection<Piece>Pieces) {
		final List<Move> legalMoves= new ArrayList<>();
		for(final Piece piece : Pieces)
		{
			legalMoves.addAll(piece.calculateLegalMoves(this));
		}
		return legalMoves;
	}

	private static Collection<Piece> calculateActivePieces(final List<Tile> gameBoard,final  Alliance alliance) {
		final List<Piece> activePieces = new ArrayList<>();
		for(final Tile tile : gameBoard)
		{
			if(tile.isTileOccupied())
			{
				final Piece piece = tile.getPiece();
				if(piece.getPieceAlliance()==alliance)
				{
					activePieces.add(piece);
				}
			}
		}
	return activePieces;
	}
	public Tile getTile(final int tileCoordinate) {
		return gameBoard.get(tileCoordinate);
	}
	
	public static Board createStandardBoard()
	{
		final Builder builder = new Builder();
//		black standard pieces layout
		
		builder.setPiece(new Rook(0,Alliance.BLACK));
		builder.setPiece(new Knight(1,Alliance.BLACK));
		builder.setPiece(new Bishop(2,Alliance.BLACK));
		builder.setPiece(new Queen(3,Alliance.BLACK));
		builder.setPiece(new King(4,Alliance.BLACK));
		builder.setPiece(new Bishop(5,Alliance.BLACK));
		builder.setPiece(new Knight(6,Alliance.BLACK));
		builder.setPiece(new Rook(7,Alliance.BLACK));
		builder.setPiece(new Pawn(8,Alliance.BLACK));
		builder.setPiece(new Pawn(9,Alliance.BLACK));
		builder.setPiece(new Pawn(10,Alliance.BLACK));
		builder.setPiece(new Pawn(11,Alliance.BLACK));
		builder.setPiece(new Pawn(12,Alliance.BLACK));
		builder.setPiece(new Pawn(13,Alliance.BLACK));
		builder.setPiece(new Pawn(14,Alliance.BLACK));
		builder.setPiece(new Pawn(15,Alliance.BLACK));
		
//		white standard pieces layout
		
		builder.setPiece(new Pawn(48,Alliance.WHITE));
		builder.setPiece(new Pawn(49,Alliance.WHITE));
		builder.setPiece(new Pawn(50,Alliance.WHITE));
		builder.setPiece(new Pawn(51,Alliance.WHITE));
		builder.setPiece(new Pawn(52,Alliance.WHITE));
		builder.setPiece(new Pawn(53,Alliance.WHITE));
		builder.setPiece(new Pawn(54,Alliance.WHITE));
		builder.setPiece(new Pawn(55,Alliance.WHITE));
		builder.setPiece(new Rook(56,Alliance.WHITE));
		builder.setPiece(new Knight(57,Alliance.WHITE));
		builder.setPiece(new Bishop(58,Alliance.WHITE));
		builder.setPiece(new Queen(59,Alliance.WHITE));
		builder.setPiece(new King(60,Alliance.WHITE));
		builder.setPiece(new Bishop(61,Alliance.WHITE));
		builder.setPiece(new Knight(62,Alliance.WHITE));
		builder.setPiece(new Rook(63,Alliance.WHITE));
		
		return builder.build();
	}
	public static class Builder{
		
		Map<Integer,Piece> boardConfig;
		Alliance nextMoveMaker;
		
		public Builder()
		{
			this.boardConfig= new HashMap<>();
		}
		
		public Builder setPiece(final Piece piece)
		{
			this.boardConfig.put(piece.getPiecePosition(),piece);
			return this;
		}
		
		public Builder nextMoveMaker(final Alliance nextMoveMaker)
		{
			this.nextMoveMaker=nextMoveMaker;
			return this;
		}
		
		public Board build() {
			return new Board(this);
		}
	}

}
