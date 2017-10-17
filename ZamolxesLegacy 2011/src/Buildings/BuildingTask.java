package Buildings;

import org.newdawn.slick.Image;

public class BuildingTask {
	Image portrait;
	String action;
	String taskName="newTask";
	float x,y;
	
	public BuildingTask(){
		
	}

	public Image getPortrait() {
		return portrait;
	}

	public void setPortrait(Image portrait) {
		this.portrait = portrait;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
	
	public void render(int i){
		this.portrait.draw(BuildingConstants.taskPositionX+i*15, BuildingConstants.taskPositionY);
	}
	
}
