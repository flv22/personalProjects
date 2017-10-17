package Buildings;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import BaseAssets.BuildingButton;

public class BuildingsContainer{
	
	Building townHall,farm,barracks,archeryRange,temple;
	
	public BuildingsContainer(String database){
		loadBuildingsFromDatabase(database);
	}
	
	public Building getBuilding(int type){
		switch(type){
		case BuildingTypes.TownHall:
			Building newTownHall=new Building();
			newTownHall=this.getCopyOf(townHall);
			return newTownHall;
		case BuildingTypes.Barracks:
			Building newBarracks=new Building();
			newBarracks=this.getCopyOf(barracks);
			return newBarracks;
		case BuildingTypes.ArcheryRange:
			Building newArcheryRange=new Building();
			newArcheryRange=this.getCopyOf(archeryRange);
			return newArcheryRange;
		case BuildingTypes.Farm:
			Building newFarm=new Building();
			newFarm=this.getCopyOf(farm);
			return newFarm;
		case BuildingTypes.Temple:
			Building newTemple=new Building();
			newTemple=this.getCopyOf(temple);
			return newTemple;
			
		default:
			return null;
		}
	}
	
	public Building getCopyOf(Building building){
		Building newBuilding=new Building();
		newBuilding.setArmor(building.getArmor());
		newBuilding.setHealthPoints(building.getHealthPoints());
		newBuilding.setName(building.getName());
		newBuilding.setPortrait(building.getPortrait());
		newBuilding.setSprite(building.getSprite());
		newBuilding.setButtons(building.getButtons());
		newBuilding.setType(building.getType());
		newBuilding.setTask(building.getTask());
		newBuilding.setTypeOfUnit(building.getTypeOfUnit());
		return newBuilding;
		
	}
	
	//debug function
	/*public void ShowBuildings(){
		System.out.println(barracks.getName());
		System.out.println(barracks.getHealthPoints());
		System.out.println(barracks.getArmor());
		System.out.println("---------------");
		System.out.println(townHall.getName());
		System.out.println(townHall.getHealthPoints());
		System.out.println(townHall.getArmor());
		System.out.println("---------------");
		System.out.println(farm.getName());
		System.out.println(farm.getHealthPoints());
		System.out.println(farm.getArmor());
		System.out.println("---------------");
		System.out.println(archeryRange.getName());
		System.out.println(archeryRange.getHealthPoints());
		System.out.println(archeryRange.getArmor());
		System.out.println("---------------");
		System.out.println(temple.getName());
		System.out.println(temple.getHealthPoints());
		System.out.println(temple.getArmor());
		System.out.println("---------------");
	}*/
	
	public void loadBuildingsFromDatabase(String database){
		try {
			File fXmlFile = new File(database);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			
			NodeList hList = doc.getElementsByTagName("buildings");
			Node hNode = hList.item(0);
			@SuppressWarnings("unused")
			Element hElement = (Element) hNode;
			BuildingButton cancelButton=new BuildingButton();
			cancelButton.setSprite(new Image("cancelPortrait.png"));
			cancelButton.setX(1172);
			cancelButton.setY(565);
			NodeList nList = doc.getElementsByTagName("building");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					int healthPoints=Integer.parseInt(eElement.getAttribute("healthPoints"));
					int armor=Integer.parseInt(eElement.getAttribute("armor"));
					String name=eElement.getAttribute("name");
					String type=eElement.getAttribute("type");
					String sprite=eElement.getAttribute("sprite");
					BuildingButton button1=new BuildingButton();
					
					switch(type){
					case "barracks":
						barracks=new Building();
						barracks.setHealthPoints(healthPoints);
						barracks.setName(name);
						barracks.setSprite(new Image(sprite));
						barracks.setType(BuildingTypes.Barracks);
						barracks.setArmor(armor);
							button1=new BuildingButton();
							button1.setSprite(new Image(eElement.getAttribute("button1")));
							button1.setPivot(1120, 565);
						barracks.addButton(button1);
						barracks.addButton(cancelButton);
						BuildingTask task=new BuildingTask();
						task.setAction(eElement.getAttribute("action1"));
						task.setPortrait(new Image(eElement.getAttribute("button1")));
						barracks.addTask(task);
						barracks.setTypeOfUnit(Integer.parseInt(eElement.getAttribute("typeOfUnit")));
					break;
					
					case "townHall":
						townHall=new Building();
						townHall.setHealthPoints(healthPoints);
						townHall.setName(name);
						townHall.setSprite(new Image(sprite));
						townHall.setType(BuildingTypes.TownHall);
						townHall.setArmor(armor);
							button1=new BuildingButton();
							button1.setSprite(new Image(eElement.getAttribute("button1")));
							button1.setPivot(1120, 565);
						townHall.addButton(button1);
						townHall.addButton(cancelButton);
						BuildingTask taskTownHall=new BuildingTask();
						taskTownHall.setAction(eElement.getAttribute("action1"));
						taskTownHall.setPortrait(new Image(eElement.getAttribute("button1")));
						townHall.addTask(taskTownHall);
						townHall.setTypeOfUnit(Integer.parseInt(eElement.getAttribute("typeOfUnit")));
					break;
					
					case "farm":
						farm=new Building();
						farm.setHealthPoints(healthPoints);
						farm.setName(name);
						farm.setSprite(new Image(sprite));
						farm.setType(BuildingTypes.Farm);
						farm.setArmor(armor);
					break;
					
					case "archeryRange":
						archeryRange=new Building();
						archeryRange.setHealthPoints(healthPoints);
						archeryRange.setName(name);
						archeryRange.setSprite(new Image(sprite));
						archeryRange.setType(BuildingTypes.ArcheryRange);
						archeryRange.setArmor(armor);
							button1=new BuildingButton();
							button1.setSprite(new Image(eElement.getAttribute("button1")));
							button1.setPivot(1120, 565);
						archeryRange.addButton(button1);
						archeryRange.addButton(cancelButton);
						BuildingTask taskArcheryRange=new BuildingTask();
						taskArcheryRange.setAction(eElement.getAttribute("action1"));
						taskArcheryRange.setPortrait(new Image(eElement.getAttribute("button1")));
						archeryRange.addTask(taskArcheryRange);
						archeryRange.setTypeOfUnit(Integer.parseInt(eElement.getAttribute("typeOfUnit")));
					break;
					
					case "temple":
						temple=new Building();
						temple.setHealthPoints(healthPoints);
						temple.setName(name);
						temple.setSprite(new Image(sprite));
						temple.setType(BuildingTypes.Temple);
						temple.setArmor(armor);
							button1=new BuildingButton();
							button1.setSprite(new Image(eElement.getAttribute("button1")));
							button1.setPivot(1120, 565);
						temple.addButton(button1);
						temple.addButton(cancelButton);
						BuildingTask taskTemple=new BuildingTask();
						taskTemple.setAction(eElement.getAttribute("action1"));
						taskTemple.setPortrait(new Image(eElement.getAttribute("button1")));
						temple.addTask(taskTemple);
						temple.setTypeOfUnit(Integer.parseInt(eElement.getAttribute("typeOfUnit")));
					break;
					}
				}
			}

		} catch (Exception e) {

		}
	}
}
