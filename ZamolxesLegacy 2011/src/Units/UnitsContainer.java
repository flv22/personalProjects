package Units;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class UnitsContainer {
	Unit swordman,worker,priest,archer;
	
	public UnitsContainer(){
		
	}
	
	public Unit getUnit(int type){
		Unit newUnit=new Unit();
		
		switch(type){
		case UnitTypes.Swordman:
			newUnit=getCopyOf(swordman);
			break;
		case UnitTypes.Worker:
			newUnit=this.getCopyOf(worker);
			break;
		case UnitTypes.Priest:
			newUnit=this.getCopyOf(priest);
			break;
		case UnitTypes.Archer:
			newUnit=this.getCopyOf(archer);
			break;
		}
		
		return newUnit;
	}
	
	public Unit getCopyOf(Unit unit){
		Unit newUnit=new Unit();
		unit.setArmor(unit.getArmor());
		newUnit.setHealthPoints(unit.getHealthPoints());
		newUnit.setName(unit.getName());
		newUnit.setSprite(unit.getSprite());
		newUnit.setType(unit.getType());
		return newUnit;
	}
	
	public void loadUnitsFromDatabase(String database){
		try {
			File fXmlFile = new File(database);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			
			NodeList hList = doc.getElementsByTagName("units");
			Node hNode = hList.item(0);
			@SuppressWarnings("unused")
			Element hElement = (Element) hNode;
			
			NodeList nList = doc.getElementsByTagName("unit");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					int healthPoints=Integer.parseInt(eElement.getAttribute("healthPoints"));
					int armor=Integer.parseInt(eElement.getAttribute("armor"));
					String name=eElement.getAttribute("name");
					String type=eElement.getAttribute("type");
					String sprite=eElement.getAttribute("sprite");
					
					
					switch(type){
					case "swordman":
						swordman=new Unit();
						swordman.setHealthPoints(healthPoints);
						swordman.setName(name);
						swordman.setSprite(new Image(sprite));
						swordman.setType(UnitTypes.Swordman);
						swordman.setArmor(armor);
						System.out.println(swordman.getSprite().getHeight());
					break;
					
					case "archer":
						archer=new Unit();
						archer.setHealthPoints(healthPoints);
						archer.setName(name);
						archer.setSprite(new Image(sprite));
						archer.setType(UnitTypes.Archer);
						archer.setArmor(armor);
						System.out.println(archer.getSprite().getName());
					break;
					
					case "worker":
						worker=new Unit();
						worker.setHealthPoints(healthPoints);
						worker.setName(name);
						worker.setSprite(new Image(sprite));
						worker.setType(UnitTypes.Worker);
						worker.setArmor(armor);
						System.out.println(worker.getSprite().getName());
					break;
					
					case "priest":
						priest=new Unit();
						priest.setHealthPoints(healthPoints);
						priest.setName(name);
						priest.setSprite(new Image(sprite));
						priest.setType(UnitTypes.Priest);
						priest.setArmor(armor);
						System.out.println(priest.getSprite().getHeight());
					break;
					
					
					}
				}
			}

		} catch (Exception e) {

		}
	}
}
