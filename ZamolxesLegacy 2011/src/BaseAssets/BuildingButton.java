package BaseAssets;

import org.newdawn.slick.Image;

public class BuildingButton extends Button{
	Image sprite;
	
	public BuildingButton(){
		
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public boolean isFocused(float mx,float my){
		if(mx>this.x && mx<this.x+this.sprite.getWidth() && my>this.y && my<this.y+this.sprite.getHeight())
			return true;
		return false;
	}
	
	public void render(){
		if(sprite!=null)
			sprite.draw(this.x, this.y);
	}
}
