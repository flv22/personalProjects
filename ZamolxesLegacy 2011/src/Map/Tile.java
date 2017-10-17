package Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tile {
	Image sprite;
	float x,y;
	int iPos,jPos;
	String occupiedBy="";
	
	public Tile(){
		
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
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
	
	public int getWidth(){
		return sprite.getWidth();
	}
	
	public int getHeight(){
		return sprite.getHeight();
	}

	public int getiPos() {
		return iPos;
	}

	public void setiPos(int iPos) {
		this.iPos = iPos;
	}

	public int getjPos() {
		return jPos;
	}

	public void setjPos(int jPos) {
		this.jPos = jPos;
	}
	
	public void render(Graphics g){
		g.drawImage(this.sprite, this.x, this.y);
	}

	public String getOccupiedBy() {
		return occupiedBy;
	}

	public void setOccupiedBy(String occupiedBy) {
		this.occupiedBy = occupiedBy;
	}
}
