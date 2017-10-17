package Buildings;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Building{
	
	private Image portrait;
	private int sprite;
	private float x,y;
	private boolean boundingBoxVisible,selected;
	private String name;
	private int healthPoints,type,armor,typeOfUnit;
	private int iPos,jPos;
	private ArrayList<String> tasks=new ArrayList<String>();
	private ArrayList<Integer> taskSprites = new ArrayList<Integer>();
	
	public int getTypeOfUnit(){
		return typeOfUnit;
	}
	
	public Building(){

	}
	
	public void showPortrait(){
		portrait.draw(BuildingConstants.portraitPositionX, BuildingConstants.portraitPositionY, BuildingConstants.portraitWidth, BuildingConstants.portraitHeight);
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

	public boolean isBoundingBoxVisible() {
		return boundingBoxVisible;
	}

	public void setBoundingBoxVisible(boolean boundingBoxVisible) {
		this.boundingBoxVisible = boundingBoxVisible;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
		switch(name){
		case "Barracks":
			try {
				this.portrait=new Image("resources\\images\\buildings\\barracks.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			break;
			
		case "Archery Range":
			try {
				this.portrait=new Image("resources\\images\\buildings\\archeryRange.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "Farm":
			try {
				this.portrait=new Image("resources\\images\\buildings\\farm.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "Temple":
			try {
				this.portrait=new Image("resources\\images\\buildings\\temple.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "Town Hall":
			try {
				this.portrait=new Image("resources\\images\\buildings\\townHall.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		
		if(selected==true)
			this.setBoundingBoxVisible(true);
		else
			this.setBoundingBoxVisible(false);
	}

	public Image getPortrait() {
		return portrait;
	}

	public void setPortrait(Image portrait) {
		this.portrait = portrait;
	}

	public void setTypeOfUnit(int typeOfUnit) {
		this.typeOfUnit = typeOfUnit;
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
	
	public int getSprite() {
		return sprite;
	}
	
	public void setSprite(int sprite) {
		this.sprite = sprite;
	}

	public boolean isFocused(float mx,float my,float spriteW,float spriteH){
		
		if(mx>this.x && mx<this.x+spriteW && my>this.y && my<this.y+spriteH)
			return true;
		
		return false;
	}
	
	public void showBoundingBox(Graphics g,float x,float y,float x2,float y2){
		g.drawLine(x, y, x2, y);
		g.drawLine(x2, y, x2, y2);
		g.drawLine(x2, y2, x, y2);
		g.drawLine(x, y2, x, y);
	}
	
	public void addTask(String task){
		
		if (tasks.size() < 3) {
			tasks.add(task);

			switch (task) {
			case "worker":
				this.taskSprites.add(0);//worker e retinut pe pozitia 0 in lista de sprites
				break;
			case "swordman":
				this.taskSprites.add(1);//swordman e retinut pe pozitia 1 in lista de sprites
				break;
			case "archer":
				this.taskSprites.add(2);//archer pe pozitia 2
				break;
			case "priest":
				this.taskSprites.add(3);//priest e pe poz 3
				break;
			}
		}
	}

	public ArrayList<Integer> getTasks(){
		return taskSprites;
	}
	
}
