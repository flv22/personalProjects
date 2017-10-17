package Map;

public class Graph {
	int sizeW,sizeH;
	int[][] cost;
	char[][] map;
	
	public Graph(){
		
	}
	
	public void showMap(){
		for(int i=0;i<sizeH;i++){
			for(int j=0;j<sizeW;j++)
				if(map[i][j]==1)
					System.out.printf("1 ");
				else
					System.out.printf("0 ");
			System.out.println();
		}
	}
	
	public void showCosts(){
		for(int i=0;i<sizeH;i++){
			for(int j=0;j<sizeW;j++)
				System.out.printf("%d ",cost[i][j]);
			System.out.println();
		}
	}
	
	public void setMap(Map m){
		this.sizeH=m.getSizeH();
		this.sizeW=m.getSizeW();
		map=new char[sizeH][sizeW];
		cost=new int[sizeH][sizeW];
		
		for(int i=0;i<m.getSizeH();i++){
			for(int j=0;j<m.getSizeW();j++){
				if(m.getTile(i, j).getOccupiedBy().equals("building")){
					map[i][j]=1;
					cost[i][j]=100;
				}
			}
		}
	}

	public int[][] getCost() {
		return cost;
	}

	public void setCost(int[][] cost) {
		this.cost = cost;
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}
}
