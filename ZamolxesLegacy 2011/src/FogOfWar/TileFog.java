package FogOfWar;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TileFog {
	Image sprite;
	int [][] bits=new int[2][2];
	float x,y;
	
	public TileFog(){
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public int[][] getBits() {
		return bits;
	}

	public void setBits(int[][] bits) {
		this.bits = bits;
	}

	public void setBits(int a,int b,int c,int d){
		bits[0][0]=a;
		bits[0][1]=b;
		bits[1][0]=c;
		bits[1][1]=d;	
	}
	
	public void interpretBits() {
		String filename = null;

		if (bits[0][0] == 0 && bits[0][1] == 0 && bits[1][0] == 0
				&& bits[1][1] == 0)
			this.sprite = null;
		else if (bits[0][0] == 1 && bits[0][1] == 1 && bits[1][0] == 1
				&& bits[1][1] == 1)
			filename = "centerfog.png";
		else if (bits[0][0] == 1 && bits[0][1] == 0 && bits[1][0] == 1
				&& bits[1][1] == 0)
			filename = "border_right.png";
		else if(bits[0][0] == 0 && bits[0][1] == 1 && bits[1][0] == 0
				&& bits[1][1] == 1)
			filename="border_left.png";
		else if(bits[0][0] == 0 && bits[0][1] == 0 && bits[1][0] == 1
				&& bits[1][1] == 1)
			filename="border_up.png";
		else if(bits[0][0] == 1 && bits[0][1] == 1 && bits[1][0] == 0
				&& bits[1][1] == 0)
			filename="border_down.png";
		
		else if(bits[0][0] == 0 && bits[0][1] == 0 && bits[1][0] == 0
				&& bits[1][1] == 1)
			filename="out_corner_1.png";
		else if(bits[0][0] == 0 && bits[0][1] == 0 && bits[1][0] == 1
				&& bits[1][1] == 0)
			filename="out_corner_2.png";
		else if(bits[0][0] == 0 && bits[0][1] == 1 && bits[1][0] == 0
				&& bits[1][1] == 0)
			filename="out_corner_4.png";
		else if(bits[0][0] == 1 && bits[0][1] == 0 && bits[1][0] == 0
				&& bits[1][1] == 0)
			filename="out_corner_3.png";
		
		else if (bits[0][0] == 1 && bits[0][1] == 1 && bits[1][0] == 0
				&& bits[1][1] == 1)
			filename = "in_corner_4.png";
		else if (bits[0][0] == 1 && bits[0][1] == 1 && bits[1][0] == 1
				&& bits[1][1] == 0)
			filename = "in_corner_3.png";
		else if (bits[0][0] == 1 && bits[0][1] == 0 && bits[1][0] == 1
				&& bits[1][1] == 1)
			filename = "in_corner_2.png";
		else if (bits[0][0] == 0 && bits[0][1] == 1 && bits[1][0] == 1
				&& bits[1][1] == 1)
			filename = "in_corner_1.png";
		
		if (filename != null)
			try {
				this.sprite = new Image(filename);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			this.sprite = null;
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
	
	public void setTopLeftBit(int value){
		bits[0][0]=value;
	}
	
	public void setTopRightBit(int value){
		bits[0][1]=value;
	}
	
	public void setBottomRightBit(int value){
		bits[1][1]=value;
	}
	
	public void setBottomLeftBit(int value){
		bits[1][0]=value;
	}
}
