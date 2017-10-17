package Resources;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Resource {
	Image sprite;
	int quantity;
	String resourceType;
	float x,y;
	boolean boundingBoxVisible;
	
	public Resource(int type){
		switch(type){
		case ResourceTypes.GoldMine:
			try {
				this.sprite=new Image("goldMine.png");
				resourceType="Gold";
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		
		case ResourceTypes.Forest:
			try {
				this.sprite=new Image("tree.png");
				resourceType="Wood";
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
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
	
	public boolean isFocused(float x,float y){
		if(x>this.x && x<this.x+this.sprite.getWidth() && y>this.y && y<this.y+this.sprite.getHeight())
			return true;
		return false;
	}
	
	public void render(Graphics g){
		g.drawImage(this.sprite, this.x, this.y);
		
		if(boundingBoxVisible==true){
			g.drawLine(this.x, this.y, this.x+this.sprite.getWidth(), this.y);
			g.drawLine(this.x+this.sprite.getWidth(), this.y, this.x+this.sprite.getWidth(), this.y+this.sprite.getHeight());
			g.drawLine(this.x+this.sprite.getWidth(),this.y+this.sprite.getHeight(),this.x,this.y+this.sprite.getHeight());
			g.drawLine(this.x, this.y+this.sprite.getHeight(), this.x, this.y);
		}
			
	}
	

	public boolean isBoundingBoxVisible() {
		return boundingBoxVisible;
	}
	

	public void setBoundingBoxVisible(boolean boundingBox) {
		this.boundingBoxVisible = boundingBox;
	}
}
