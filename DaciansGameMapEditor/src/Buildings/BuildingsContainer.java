package Buildings;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BuildingsContainer{
	
	ArrayList<Building> buildings=new ArrayList<Building>();
	ArrayList<Image> sprites=new ArrayList<Image>();
	
	public BuildingsContainer(String database,String spritesDatabase){
		this.loadBuildingsFromDatabase(database);
		this.loadSprites(spritesDatabase);
	}
	
	public ArrayList<Image> getSprites(){
		return sprites;
	}
	
	public Building getBuilding(int type){
		switch(type){
		case BuildingTypes.TownHall:
			Building newTownHall=new Building();
			newTownHall=this.getCopyOf(buildings.get(4));
			return newTownHall;
		case BuildingTypes.Barracks:
			Building newBarracks=new Building();
			newBarracks=this.getCopyOf(buildings.get(1));
			return newBarracks;
		case BuildingTypes.ArcheryRange:
			Building newArcheryRange=new Building();
			newArcheryRange=this.getCopyOf(buildings.get(0));
			return newArcheryRange;
		case BuildingTypes.Farm:
			Building newFarm=new Building();
			newFarm=this.getCopyOf(buildings.get(2));
			return newFarm;
		case BuildingTypes.Temple:
			Building newTemple=new Building();
			newTemple=this.getCopyOf(buildings.get(3));
			return newTemple;
			
		default:
			return null;
		}
	}
	
	public void showBuildings(){
		for(Building item: buildings)
			System.out.println(item.getName());
	}
	
	private Building getCopyOf(Building building){
		Building newBuilding=new Building();
		newBuilding.setArmor(building.getArmor());
		newBuilding.setHealthPoints(building.getHealthPoints());
		newBuilding.setName(building.getName());
		newBuilding.setPortrait(building.getPortrait());
		newBuilding.setSprite(building.getSprite());
		newBuilding.setType(building.getType());
		newBuilding.setTypeOfUnit(building.getTypeOfUnit());
		
		return newBuilding;
	}
	
	private void loadBuildingsFromDatabase(String database){
		try {
			File fXmlFile = new File(database);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();
			
			NodeList hList = doc.getElementsByTagName("buildings");
			Node hNode = hList.item(0);
			
			@SuppressWarnings("unused")
			Element hElement = (Element) hNode;
			NodeList nList = doc.getElementsByTagName("building");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					int healthPoints=Integer.parseInt(eElement.getAttribute("healthPoints"));
					int armor=Integer.parseInt(eElement.getAttribute("armor"));
					String name=eElement.getAttribute("name");
					int sprite=Integer.parseInt(eElement.getAttribute("sprite"));
					Building building = new Building();
					building.setArmor(armor);
					building.setHealthPoints(healthPoints);
					building.setName(name);
					building.setSprite(sprite);
					
					switch(name){
					case "Archery Range":
						building.setType(1);
						break;
					case "Barracks":
						building.setType(2);
						break;
					case "Farm":
						building.setType(3);
						break;
					case "Temple":
						building.setType(4);
						break;
					case "Town Hall":
						building.setType(5);
						break;
					}
					
					buildings.add(building);
				}
			}

		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	private void loadSprites(String database){
		try {
			File fXmlFile = new File(database);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList hList = doc.getElementsByTagName("sprites");
			Node hNode = hList.item(0);
			
			@SuppressWarnings("unused")
			Element hElement = (Element) hNode;
			NodeList nList = doc.getElementsByTagName("sprite");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					String spritePath=eElement.getAttribute("path");
					sprites.add(new Image(spritePath));
				}
			}
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
}
