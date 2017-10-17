package Map;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Resources.Resource;
import Resources.ResourceTypes;
import Buildings.Building;
import FogOfWar.FogOfWar;
import Units.Unit;
import Units.UnitsContainer;

public class Map {
	Tile[][] map;
	int sizeW, sizeH;
	
	ArrayList<Unit> units;
	ArrayList<Resource> resources;
	ArrayList<Building> buildings;
	
	UnitsContainer containerUnits=new UnitsContainer();
	
	Building selectedBuilding;
	Unit selectedUnit;
	
	FogOfWar fow;
	
	Graph mapGraph;
	
	public void setTile(Image spr,int i,int j){
		map[i][j].setSprite(spr);
	}
	
	public void setUnitTarget(float x,float y){
		for(int i=0;i<sizeH;i++)
			for(int j=0;j<sizeW;j++)
				if(x>map[i][j].getX() && x<map[i][j].getX()+map[i][j].getSprite().getWidth() && y>map[i][j].getY() && y<map[i][j].getY()+map[i][j].getSprite().getHeight()){
					selectedUnit.setTargetPos(i,j);
					//selectedUnit.computePath();
					selectedUnit.setSimpleMap(mapGraph,this);
					return;
				}
	}
	
	public void addUnit(Unit u){
		units.add(u);
	}
	
	public void debugMap() {
		for(int i=0;i<sizeH;i++)
			for(int j=0;j<sizeW;j++)
				fow.getTile(i, j).setBits(0, 0, 0, 0);
		
		fow.interpretBits();
	}

	public void addBuildingOnMatrix(Building b, int i, int j) {
		b.setSpawnPoint(map[i][j]);
		b.setX(map[i][j].getX());
		b.setY(map[i][j].getY());
		b.setiPos(i);
		b.setjPos(j);
		//System.out.println(b.getName());
		int distX = (b.getSprite().getWidth() / 64) + j;
		//System.out.println("distx= " + distX + ",j= " + j);
		int distY = (b.getSprite().getHeight() / 64) + i;
		//System.out.println("disty= " + distY + ",i= " + i);

		for (int i2 = i; i2 < distY; i2++) {
			for (int j2 = j; j2 < distX; j2++) {
				map[i2][j2].setOccupiedBy("building");
				fow.getTile(i2, j2).setBits(0, 0, 0, 0);
				
				fow.getTile(i-1, j2).setBottomRightBit(0);
				fow.getTile(i-1, j2).setBottomLeftBit(0);
				fow.getTile(distY, j2).setTopRightBit(0);
				fow.getTile(distY, j2).setTopLeftBit(0);
			}
			
			fow.getTile(i2, j - 1).setTopRightBit(0);
			fow.getTile(i2, j - 1).setBottomRightBit(0);
			fow.getTile(i2, distX).setTopLeftBit(0);
			fow.getTile(i2, distX).setBottomLeftBit(0);
		}

		for (int j2 = j; j2 < distX; j2++) {
			
		}
		
		fow.getTile(i - 1, j - 1).setBottomRightBit(0);
		fow.getTile(i - 1, distX).setBottomLeftBit(0);
		fow.getTile(distY, j - 1).setTopRightBit(0);
		fow.getTile(distY, distX).setTopLeftBit(0);
		// fow.getTile(distY, j-1).setBits(1,0,1,1);
		// fow.getTile(distY+1, distX+1).setBits(0, 1, 1, 1);
		fow.interpretBits();
		buildings.add(b);
	}
	
	
	
	public void translateMap(){//se va apela de fiecare data cand va aparea o cladire noua
		mapGraph=new Graph();
		mapGraph.setMap(this);
	}
	
	public void showMap(){
		mapGraph.showMap();
	}
	
	public void showCosts(){
		mapGraph.showCosts();
	}
	
	public void renderTileMap() {
		for (int i = 0; i < sizeH; i++) {
			for (int j = 0; j < sizeW; j++)
				if (map[i][j].getOccupiedBy().equals(""))
					System.out.printf("f");
				else
					System.out.printf("b");
			System.out.println();
		}
	}
	public Tile getTile(int i, int j) {
		return map[i][j];
	}

	public void addBuilding(Building b) {
		buildings.add(b);
	}
	
	public void updateTasks(){
		
		
		for(Building item: buildings){
			if(item.updateTasks()==true){
				Unit u=containerUnits.getUnit(item.getTypeOfUnit());
				u.setXY(item.getX()+item.getSprite().getWidth(), item.getY());
				u.setIJPos(item.getiPos(), item.getjPos()+2);
				//u.setPositionByTile(item.getSpawnPoint());
				System.out.println(u.getiPos()+" "+u.getjPos());
				u.setSimpleMap(mapGraph,this);
				units.add(u);
			}
		}
	}
	
	public Map() {
		resources = new ArrayList<Resource>();
		buildings = new ArrayList<Building>();
		units=new ArrayList<Unit>();
		containerUnits.loadUnitsFromDatabase("unitsDatabase.xml");
	}

	public void loadMap(String filename) {
		// resources.removeAll(resources);

		try {
			File fXmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("tile");
			NodeList hList = doc.getElementsByTagName("harta");

			Node hNode = hList.item(0);
			Element hElement = (Element) hNode;
			sizeW = Integer.parseInt(hElement.getAttribute("wSize"));
			sizeH = Integer.parseInt(hElement.getAttribute("hSize"));

			map = new Tile[sizeH][sizeW];

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					int iPos = Integer.parseInt(eElement.getAttribute("ipos"));
					int jPos = Integer.parseInt(eElement.getAttribute("jpos"));
					map[iPos][jPos] = new Tile();
					map[iPos][jPos].setiPos(iPos);
					map[iPos][jPos].setjPos(jPos);
					map[iPos][jPos].setX(jPos * 64);
					map[iPos][jPos].setY(iPos * 64);
					try {
						map[iPos][jPos].setSprite(new Image(eElement
								.getAttribute("path")));
					} catch (SlickException e) {
						// TODO Auto-generated catch block
						System.out
								.println("Eroare la incarcarea imaginii (Map.Java)");
						e.printStackTrace();
					}

				}
			}

			NodeList gList = doc.getElementsByTagName("GoldMine");
			for (int temp = 0; temp < gList.getLength(); temp++) {

				Node nNode = gList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					int quantity = Integer.parseInt(eElement
							.getAttribute("quantity"));
					int iPos = Integer.parseInt(eElement.getAttribute("ip"));
					int jPos = Integer.parseInt(eElement.getAttribute("jp"));

					Resource res = new Resource(ResourceTypes.GoldMine);
					res.setQuantity(quantity);
					res.setX(map[iPos][jPos].getX());
					res.setY(map[iPos][jPos].getY());

					resources.add(res);
				}
			}

			NodeList tList = doc.getElementsByTagName("Forest");
			for (int temp = 0; temp < tList.getLength(); temp++) {

				Node nNode = tList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					int quantity = Integer.parseInt(eElement
							.getAttribute("quantity"));
					int iPos = Integer.parseInt(eElement.getAttribute("ip"));
					int jPos = Integer.parseInt(eElement.getAttribute("jp"));

					Resource res = new Resource(ResourceTypes.Forest);
					res.setQuantity(quantity);
					res.setX(map[iPos][jPos].getX());
					res.setY(map[iPos][jPos].getY());

					resources.add(res);
				}
			}
		} catch (Exception e) {

		}

		fow = new FogOfWar(sizeH, sizeW);
	}

	public void renderMap(Graphics g) {
		for (int i = 0; i < sizeH; i++) {
			for (int j = 0; j < sizeW; j++)
				map[i][j].render(g);
		}

		for (Resource item : resources)
			item.render(g);

		for (Building item : buildings)
			item.render(g);
		
		for(Unit item: units)
			item.render(g);
		
		fow.render(g);
	}

	public int getSizeW() {
		return sizeW;
	}

	public void setSizeW(int sizeW) {
		this.sizeW = sizeW;
	}

	public int getSizeH() {
		return sizeH;
	}

	public void setSizeH(int sizeH) {
		this.sizeH = sizeH;
	}

	public void moveX(float scrollSpeed) {
		for (int i = 0; i < sizeH; i++) {
			for (int j = 0; j < sizeW; j++)
				map[i][j].setX(map[i][j].getX() + scrollSpeed);
		}

		for (Resource item : resources)
			item.setX(item.getX() + scrollSpeed);

		for (Building item : buildings){
			item.setX(item.getX() + scrollSpeed);
			item.getSpawnPoint().setX(item.getSpawnPoint().getX()+scrollSpeed);
		}
		
		for(Unit item: units)
			item.setX(item.getX()+scrollSpeed);
		
		fow.moveX(scrollSpeed);
	}

	public void moveY(float scrollSpeed) {
		for (int i = 0; i < sizeH; i++) {
			for (int j = 0; j < sizeW; j++)
				map[i][j].setY(map[i][j].getY() + scrollSpeed);
		}

		for (Resource item : resources)
			item.setY(item.getY() + scrollSpeed);

		for (Building item : buildings){
			item.setY(item.getY() + scrollSpeed);
			item.getSpawnPoint().setY(item.getSpawnPoint().getY()+scrollSpeed);
		}
		
		for(Unit item: units)
			item.setY(item.getY()+scrollSpeed);
		
		fow.moveY(scrollSpeed);
	}

	public Tile getTopLeftPoint() {
		return map[0][0];
	}

	public Tile getBottomLeftPoint() {
		return map[sizeH - 1][0];
	}

	public Tile getTopRightPoint() {
		return map[0][sizeW - 1];
	}

	public Tile getBottomRightPoint() {
		return map[sizeH - 1][sizeW - 1];
	}

	public ArrayList<Resource> getResources() {
		return resources;
	}

	public void setResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}

	public ArrayList<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(ArrayList<Building> buildings) {
		this.buildings = buildings;
	}

	public Building getSelectedBuilding() {
		return selectedBuilding;
	}

	public void setSelectedBuilding(Building selectedBuilding) {
		if (this.selectedBuilding != null)
			this.selectedBuilding.setSelected(false);
		if(this.selectedUnit!=null){
			this.selectedUnit.setSelected(false);
			this.selectedUnit=null;
		}
		this.selectedBuilding = selectedBuilding;
		this.selectedBuilding.setSelected(true);
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	public Unit getSelectedUnit() {
		return selectedUnit;
	}

	public void setSelectedUnit(Unit selectedUnit) {
		if (this.selectedBuilding != null) {
			this.selectedBuilding.setSelected(false);
			this.selectedBuilding = null;
		}
		if (this.selectedUnit != null)
			this.selectedUnit.setSelected(false);
		this.selectedUnit = selectedUnit;
		this.selectedUnit.setSelected(true);
	}
}
