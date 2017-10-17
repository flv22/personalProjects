package Units;

import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class SimpleMap implements TileBasedMap{
	int HEIGHT,WIDTH;
	char[][] MAP;
	int[][] costMatrix;
	
	public SimpleMap(){
		
	}
	
	public void setMap(char[][] map){
		this.MAP=new char[HEIGHT][WIDTH];
		this.MAP=map;
		
	}
	
	 @Override
	    public boolean blocked(PathFindingContext ctx, int x, int y) {
	        return MAP[y][x] != 0;
	    }

	    @Override
	    public float getCost(PathFindingContext ctx, int x, int y) {
	        return (float)costMatrix[x][y];
	    }

	    @Override
	    public int getHeightInTiles() {
	        return HEIGHT;
	    }

	    @Override
	    public int getWidthInTiles() {
	        return WIDTH;
	    }

	    @Override
	    public void pathFinderVisited(int x, int y) {}

		public int getHEIGHT() {
			return HEIGHT;
		}

		public void setHEIGHT(int hEIGHT) {
			HEIGHT = hEIGHT;
		}

		public int getWIDTH() {
			return WIDTH;
		}

		public void setWIDTH(int wIDTH) {
			WIDTH = wIDTH;
		}

		public int[][] getCostMatrix() {
			return costMatrix;
		}

		public void setCostMatrix(int[][] costMatrix) {
			this.costMatrix=new int[HEIGHT][WIDTH];
			this.costMatrix = costMatrix;
		}
	
}
