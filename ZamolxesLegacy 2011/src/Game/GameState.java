package Game;

public class GameState {
	private
	int activeState=-1;
	
	public GameState(){
		
	}
	
	public int getActiveState(){
		return activeState;
	}
	
	public void setActiveState(int activeState){
		this.activeState=activeState;
	}
}
