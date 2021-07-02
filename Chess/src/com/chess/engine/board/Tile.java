package com.chess.engine.board;


import java.util.HashMap;
import java.util.Map;
import com.chess.engine.pieces.Piece;


public abstract class Tile {
	protected final int tileCoordinate;
	
	private static final Map<Integer,EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
	
	Tile(final int tileCoordinate)
	{
		this.tileCoordinate=tileCoordinate;
	}
	
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
		final Map<Integer,EmptyTile> emptyTileMap = new HashMap<>();
		
		for(int i=0;i<8;i++)
		{
			emptyTileMap.put(i,new EmptyTile(i));
		}
		return emptyTileMap;
		
	}
	
	public static Tile createTile(final int tileCoordinate, final Piece piece)
	{
		if(piece!=null)
			return new OccupiedTile(tileCoordinate,piece);
		else
			return EMPTY_TILES_CACHE.get(tileCoordinate);
			
	}
	
	public abstract boolean isTileOccupied();
	
	public abstract Piece getPiece();
	
	public static final class EmptyTile extends Tile{
		
		private EmptyTile(final int coordinate)
		{
			super(coordinate);
		}

		@Override
		public boolean isTileOccupied() {
			return false;
		}

		@Override
		public String toString()
		{
			return "-";
		}
		@Override
		public Piece getPiece() {
			return null;
		}
	}
	
	public static final class OccupiedTile extends Tile {
		private final Piece pieceOnTile;
		
		private OccupiedTile(int tileCoordinate,Piece pieceOnTile)
		{
			super(tileCoordinate);
			this.pieceOnTile=pieceOnTile;
		}
		@Override
		public String toString ()
		{
			if(this.getPiece().getPieceAlliance().isBlack())
			{
				return this.getPiece().toString().toLowerCase();
			}
			else
			{
				return this.getPiece().toString().toUpperCase(); 
			}
		}
		@Override
		public boolean isTileOccupied()
		{
			return true;
		}
		public Piece getPiece()
		{
			return this.pieceOnTile;
		}
	}
}
