package GUI;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

import Map.Map;
import Units.Unit;

import Buildings.Building;

public class GUI {
	public Image mainBar;
	Image minimapBackground;
	Font font;
    TrueTypeFont ttf;
    int polyWidth,polyHeight;
    
	public GUI(){
		font = new Font("cuneiform", Font.BOLD, 14);
		ttf = new TrueTypeFont(font, true);
	}

	public Image getMainBar() {
		return mainBar;
	}

	public void setMainBar(Image mainBar) {
		this.mainBar = mainBar;
	}
	
	
	public void render(Graphics g){
		g.drawImage(minimapBackground, 6, 549);
		g.drawImage(mainBar, 0, 0);
		
		g.setColor(Color.green);
		g.drawLine(17, 555, 17+this.polyWidth, 555);
		g.drawLine(17+this.polyWidth, 555, 17+this.polyWidth, 555+this.polyHeight);
		g.drawLine(17+this.polyWidth, 555+this.polyHeight, 17, 555+this.polyHeight);
		g.drawLine(17, 555+this.polyHeight, 17, 555);
	}
	
	public void showInfos(Building selectedBuilding){
		if(selectedBuilding!=null){
			ttf.drawString(660, 640, selectedBuilding.getName(), Color.green);
			ttf.drawString(660, 658, "Health: "+selectedBuilding.getHealthPoints(), Color.green);
			selectedBuilding.showTask();
			selectedBuilding.showPortrait();
			selectedBuilding.showButtons();
		}
	}
	
	public void showInfos(Unit selectedUnit){
		if(selectedUnit!=null){
			ttf.drawString(660, 640, selectedUnit.getName(),Color.green);
			ttf.drawString(660, 658, "Health: "+selectedUnit.getHealthPoints(), Color.green);
			ttf.drawString(660, 676, "Armor: "+selectedUnit.getArmor(),Color.green);
		}
	}
	
	public void setMinimap(Map m){
		this.polyWidth=1280/149;
		this.polyHeight=720/306;
		
		
	}
	
	public void showMinimap(){
		
	}

	public Image getMinimapBackground() {
		return minimapBackground;
	}

	public void setMinimapBackground(Image minimapBackground) {
		this.minimapBackground = minimapBackground;
	}
}
