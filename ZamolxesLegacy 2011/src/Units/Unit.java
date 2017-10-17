package Units;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import Map.Graph;
import Map.Map;
import Map.Tile;

public class Unit {
	float x,y;
	Image sprite;
	int armor,healthPoints;
	String name;
	int type;
	double rotation=0.785*2.0;
	boolean selected;
	boolean showBoundingBox;
	float targetX,targetY;

	int iPos,jPos,iTargetPos,jTargetPos,pathLength;
	public int index=0;
	Image pathSprite;
	Graph mapGraph;
	SimpleMap simpleMap;
	Path path;
	AStarPathFinder pathFinder;
	
	public void setPositionByTile(Tile t){
		this.iPos=t.getiPos();
		this.jPos=t.getjPos();
		this.x=t.getX();
		this.y=t.getY();
	}
	
	public void setXY(float x,float y){
		this.x=x;
		this.y=y;
	}
	
	public void setIJPos(int i,int j){
		this.iPos=i;
		this.jPos=j;
	}
	
	public void showPathOnMap(Map m) throws NullPointerException{
		//System.out.println(index+" "+path.getLength());
		
		if (index == 1 && path.getLength()>0) {
			for (int i = 0; i < path.getLength(); i++) {
				m.setTile(pathSprite, path.getX(i), path.getY(i));
			}
		}
	}
	
	public void computePath() throws NullPointerException{
		pathFinder = new AStarPathFinder(simpleMap, 100, true);
		System.out.println(iPos+" "+jPos+" "+iTargetPos+" "+jTargetPos);
		path = pathFinder.findPath(null, iPos, jPos, iTargetPos, jTargetPos);
		index=1;
		for(int i=0;i<path.getLength();i++)
			System.out.println(path.getX(i)+"-"+path.getY(i));
	}
	
	public void setSimpleMap(Graph g,Map m){
		mapGraph=new Graph();
		mapGraph=g;
		
		simpleMap=new SimpleMap();
		simpleMap.setHEIGHT(m.getSizeH());
		simpleMap.setWIDTH(m.getSizeW());
		simpleMap.setMap(mapGraph.getMap());
		simpleMap.setCostMatrix(mapGraph.getCost());
		//simpleMap.setCostMatrix(g.getCost());
	}
	
	public Unit(){
		try {
			pathSprite=new Image("pathSprite.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateMove(){
		x+=Math.cos(rotation)*5.5;
		y+=Math.sin(rotation)*5.5;
	}
	
	public boolean isFocused(float x,float y){
		if(x>this.x && x<this.x+this.sprite.getWidth() && y>this.y && y<this.y+this.sprite.getHeight())
			return true;
		return false;
	}
	
	public void render(Graphics g) {
		sprite.draw(x, y);

		if (this.showBoundingBox == true) {
			g.setColor(Color.green);
			g.drawLine(this.x, this.y, this.x + this.sprite.getWidth(), this.y);
			g.drawLine(this.x + this.sprite.getWidth(), this.y, this.x
					+ this.sprite.getWidth(), this.y + this.sprite.getHeight());
			g.drawLine(this.x + this.sprite.getWidth(),
					this.y + this.sprite.getHeight(), this.x, this.y
							+ this.sprite.getHeight());
			g.drawLine(this.x, this.y + this.sprite.getHeight(), this.x, this.y);
		}
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

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		
		if(this.selected==true)
			this.showBoundingBox=true;
		else
			this.showBoundingBox=false;
			
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

	public int getiTargetPos() {
		return iTargetPos;
	}

	public void setTargetPos(int iTargetPos,int jTargetPos) {
		this.iTargetPos = iTargetPos;
		this.jTargetPos = jTargetPos;
	}

	public int getjTargetPos() {
		return jTargetPos;
	}

}
