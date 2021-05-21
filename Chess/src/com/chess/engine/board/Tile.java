package com.chess.engine.board;


import java.util.HashMap;
import java.util.Map;
import com.chess.engine.pieces.Piece;


public abstract class Tile {
	protected final int tileCoordinate;
	
	private static final Map<Integer,EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
	
	Tile(int tileCoordinate)
	{
		this.tileCoordinate=tileCoordinate;
	}
	
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
		final Map<Integer,EmptyTile> emptyTileMap = new HashMap<>();
		
		for(int i=0;i<64;i++)
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
		public Piece getPiece() {
			return null;
		}
	}
	
	public static final class OccupiedTile extends Tile {
		private final Piece placeOnTile;
		
		private OccupiedTile(int tileCoordinate,Piece pieceOnTile)
		{
			super(tileCoordinate);
			this.placeOnTile=pieceOnTile;
		}
		@Override
		public boolean isTileOccupied()
		{
			return true;
		}
		public Piece getPiece()
		{
			return this.placeOnTile;
		}
	}
}
