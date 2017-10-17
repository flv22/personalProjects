package Buildings;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import BaseAssets.BuildingButton;
import Map.Tile;

public class Building{
	Image sprite,portrait;
	float x,y; //scale x,y sunt pt portrait,ca sa il aduc la 129x133
	boolean boundingBoxVisible,selected;
	String name;
	int healthPoints,type,armor,typeOfUnit;
	int iPos,jPos;
	Tile spawnPoint=new Tile();
	
	int counterTask=0;
	BuildingTask task;
	ArrayList<BuildingTask> buildingTasks=new ArrayList<BuildingTask>();
	
	ArrayList<BuildingButton> buttons=new ArrayList<BuildingButton>();
	

	
	public boolean isButtonFocused(float x,float y){
		for(BuildingButton item: buttons)
			if(x>item.getX() && x<item.getX()+item.getSprite().getWidth() && y>item.getY() && y<item.getY()+item.getSprite().getHeight())
				return true;
		
		return false;
	}
	
	public void addTask(BuildingTask task){
		this.task=new BuildingTask();
		this.task=task;
	}
	
	public void addTaskOnStack() {
		if (buildingTasks.size() < 3)
			buildingTasks.add(task);
	}
	
	public void showTask() {
		//System.out.println(name);
		/*for (BuildingTask item : buildingTasks)
			System.out.println(item.getAction());*/
			//System.out.println(item.getTaskName() + " " + item.getAction());
		
		for(int i=buildingTasks.size()-1;i>=0;i--)
			buildingTasks.get(i).render(i);
	}
	
	public int getTypeOfUnit(){
		return typeOfUnit;
	}
	
	public boolean updateTasks() {
		if (buildingTasks.size() > 0) {
			counterTask++;
			if (counterTask > 60) {
				buildingTasks.remove(0);
				counterTask = 0;
				return true;
			}
		}
		return false;
	}
	
	public Building(){
		
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

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
		this.portrait=this.sprite;
		
		portrait.setFilter(Image.FILTER_NEAREST);
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
	
	public boolean isFocused(float x,float y){
		if(x>this.x && x<this.x+this.sprite.getWidth() && y>this.y && y<this.y+this.sprite.getHeight())
			return true;
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public void addButton(BuildingButton button){
		buttons.add(button);
	}
	
	public void showButtons(){
		if(buttons.size()>0)
			for(BuildingButton item: buttons)
				item.render();
	}

	public ArrayList<BuildingButton> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<BuildingButton> buttons) {
		this.buttons = buttons;
	}

	public BuildingTask getTask() {
		return task;
	}

	public void setTask(BuildingTask task) {
		this.task = task;
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

	public Tile getSpawnPoint() {
		int spawnI=this.spawnPoint.getiPos();
		int spawnJ=this.spawnPoint.getjPos();
		System.out.println("Building: "+this.iPos+" "+this.jPos+", Spawn: "+spawnI+","+spawnJ);
		this.spawnPoint.setiPos(++spawnI);
		this.spawnPoint.setY(spawnI*64);
		return spawnPoint;
	}

	public void setSpawnPoint(Tile spawnPoint) {
		Tile newSpawnPoint=new Tile();
		newSpawnPoint.setiPos(spawnPoint.getiPos());
		newSpawnPoint.setjPos(spawnPoint.getjPos());
		newSpawnPoint.setX(spawnPoint.getX());
		newSpawnPoint.setY(spawnPoint.getY());
		this.spawnPoint = newSpawnPoint;
	}

	
	
}
