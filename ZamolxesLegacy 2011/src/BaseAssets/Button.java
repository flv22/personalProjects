package BaseAssets;

public class Button {
	float x,y;
	static int id=0;
	
	public Button(){
		id++;
	}
	
	public void setPivot(float x,float y){
		this.x=x;
		this.y=y;
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

	public int getId() {
		return id;
	}

	@SuppressWarnings("static-access")
	public void setId(int id) {
		this.id = id;
	}
	
	
}
