package pachet;


public class Tile {
		float x=0,y=0;
		int imageType=0;
		boolean Occupied=false;
		int id;
		String type;
		char letter;
		int[][] bit;
		int GRASS=1;
		int GROUND=0;
		
		public Tile(){
			letter='X';
			bit=new int[2][2];
			
			bit[1][1]=0;
			bit[1][0]=0;
			bit[0][1]=0;
			bit[0][0]=0;
		}
		
		public float getX() {
			return x;
		}
		public void setX(float x) {
			this.x = x;
		}
		public float getY() {
			return y;
		}
		public void setY(float y) {
			this.y = y;
		}

		public boolean isOccupied() {
			return Occupied;
		}

		public void setOccupied(boolean occupied) {
			Occupied = occupied;
		}

		public void setImageIndex(int imageIndex) {
			this.imageType=imageIndex;
			switch (imageIndex){
				case 0: bit[0][0]=GRASS; bit[0][1]=GRASS; bit[1][0]=GRASS; bit[1][1]=GRASS; break;
			
				case 12: bit[0][0]=GROUND; bit[0][1]=GRASS; bit[1][0]=GROUND; bit[1][1]=GRASS; break;
				case 13: bit[0][0]=GRASS; bit[0][1]=GROUND; bit[1][0]=GRASS; bit[1][1]=GROUND; break;
				case 14: bit[0][0]=GROUND; bit[0][1]=GROUND; bit[1][0]=GRASS; bit[1][1]=GRASS; break;
				case 11: bit[0][0]=GRASS; bit[0][1]=GRASS; bit[1][0]=GROUND; bit[1][1]=GROUND; break;
			
				case 3: bit[0][0]=GRASS; bit[0][1]=GRASS; bit[1][0]=GROUND; bit[1][1]=GRASS; break;
				case 4: bit[0][0]=GRASS; bit[0][1]=GRASS; bit[1][0]=GRASS; bit[1][1]=GROUND; break;
				case 5: bit[0][0]=GROUND; bit[0][1]=GRASS; bit[1][0]=GRASS; bit[1][1]=GRASS; break;
				case 6: bit[0][0]=GRASS; bit[0][1]=GROUND; bit[1][0]=GRASS; bit[1][1]=GRASS; break;
			
				case 7: bit[0][0]=GROUND; bit[0][1]=GRASS; bit[1][0]=GROUND; bit[1][1]=GROUND; break;
				case 8: bit[0][0]=GRASS; bit[0][1]=GROUND; bit[1][0]=GROUND; bit[1][1]=GROUND; break;
				case 9: bit[0][0]=GROUND; bit[0][1]=GROUND; bit[1][0]=GROUND; bit[1][1]=GRASS; break;
				case 10: bit[0][0]=GROUND; bit[0][1]=GROUND; bit[1][0]=GRASS; bit[1][1]=GROUND; break;
			
				default: break;
			}
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getImageType() {
			return imageType;
		}

		public void setImageType(int imageType) {
			this.imageType = imageType;
		}
		
}
