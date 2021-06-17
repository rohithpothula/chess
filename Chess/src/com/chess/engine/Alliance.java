package com.chess.engine;

public enum Alliance {
	WHITE
	{
		@Override
		public int getDirection() 
		{
			return -1;
		}

		@Override
		public boolean isWhite() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isBlack() {
			// TODO Auto-generated method stub
			return false;
		}
	},
 BLACK{

	@Override
	public int getDirection() {
		return 1;
	}

	@Override
	public boolean isWhite() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlack() {
		// TODO Auto-generated method stub
		return true;
	}
		
	};
	public abstract int getDirection();
	public abstract boolean isWhite();
	public abstract boolean isBlack();
}
 