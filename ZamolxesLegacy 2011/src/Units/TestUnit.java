package Units;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class TestUnit {
	SpriteSheet sprite;
	Animation animation;
	
	public TestUnit(){
		try {
			sprite=new SpriteSheet("spritesheet.png",18,30);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		animation=new Animation(sprite,35);
	}
	
	public void update(int delta){
		animation.update(delta);
	}
	
	public void render(){
		animation.draw(100,100);
	}
}
