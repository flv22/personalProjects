package Menu;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import BaseAssets.MenuButton;

public class Menu {
	Image background;
	ArrayList<MenuButton> menuButton;
	float x,y;
	boolean active;
	boolean pressedFlag=false;
	
	public Menu(){
		menuButton=new ArrayList<MenuButton>();
	}
	
	public void setPivot(float x,float y){//pivot pt centrarea butoanelor in meniu
		this.x=x;
		this.y=y;
	}
	
	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}
	
	public void addButton(MenuButton menuButton){
		menuButton.setX(x+this.menuButton.size()*(menuButton.getSprite().getWidth()+40));
		menuButton.setY(y);
		/*menuButton.setX(x);
		menuButton.setY(y+this.menuButton.size()*(menuButton.getSprite().getHeight()+20));*/
		this.menuButton.add(menuButton);
	}
	
	public MenuButton getButton(int index){
		return this.menuButton.get(index);
	}
	
	public boolean isButtonFocused(Input in,int buttonIndex){
		if(this.menuButton.get(buttonIndex).isFocused(in.getAbsoluteMouseX(), in.getAbsoluteMouseY()))
			return true;
		return false;
	}
	
	public void render(Graphics g){
		if(active==true){
			g.drawImage(background, 0, 0);
		
			for(MenuButton item: menuButton)
				item.render();
		}
	}
	
	public String focusedItem(float x,float y){
		for(MenuButton item: menuButton)
			if(item.isFocused(x, y))
				return item.getAction();
		return "x";
	}
	
	public String listen(Input in){
			for(MenuButton item: menuButton)
				if(item.isFocused(in.getAbsoluteMouseX(), in.getAbsoluteMouseY())){
					 
					if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
						pressedFlag=true;
						item.changeSpriteToPressed();
					}
					if(!in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && pressedFlag){
						pressedFlag=false;
						return item.getAction();
					}			
				}
			
		return "x";
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
