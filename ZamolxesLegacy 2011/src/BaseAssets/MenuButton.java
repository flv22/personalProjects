package BaseAssets;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MenuButton extends Button{
	Image sprite,normalSprite,overSprite,pressedSprite;
	String action;
	
	public MenuButton(Image sprite,String action){
		super();
		this.sprite=sprite;
		this.normalSprite=sprite;
		try {
			this.overSprite=new Image(deriveSprites("Over",this.normalSprite.toString()));
			this.pressedSprite=new Image(deriveSprites("Pressed",this.normalSprite.toString()));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.action=action;
	}
	
	public String deriveSprites(String typeOfImage,String imageString){
		String[] result = imageString.split(" ");
		result[1]=result[1].replaceAll(".png", typeOfImage+".png");
		
		return result[1];
	}
	
	public String getAction(){
		return action;
	}
	
	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public void render(){
		this.sprite.draw(this.x, this.y);
	}
	
	public void changeSpriteToPressed(){
		this.sprite=this.pressedSprite;
	}
	
	public boolean isFocused(float mx,float my){
		if(mx>this.x && mx<this.x+this.sprite.getWidth() && my>this.y && my<this.y+this.sprite.getHeight()){
			if(this.sprite!=this.overSprite)
				this.sprite=this.overSprite;
			return true;		
		}
		if(this.sprite!=this.normalSprite)
			this.sprite=this.normalSprite;
		return false;
	}
}
