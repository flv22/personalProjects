package pachet;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import principal.BuildingInfos;


public class GBar {
	ArrayList<Item> item;
	private final int tileY=165;
	
	public GBar(){
		item=new ArrayList<Item>();
	}
	
	public void push(Item i){
		item.add(i);
	}
	
	public void render(Graphics g){
		for(int i=0;i<item.size();i++)
			if(item.get(i).img.getWidth()==128 && item.get(i).img.getHeight()==128)
				item.get(i).img.draw(item.get(i).x, item.get(i).y,0.5f);
			else if(item.get(i).img.getWidth()==512 && item.get(i).img.getHeight()==512)
				item.get(i).img.draw(item.get(i).x, item.get(i).y,0.125f);
			else if(item.get(i).img.getWidth()==256 && item.get(i).img.getHeight()==192)
				item.get(i).img.draw(item.get(i).x, item.get(i).y, 0.25f);
			else
				g.drawImage(item.get(i).img, item.get(i).x, item.get(i).y);
	}
	
	public Item get(int index){
		return item.get(index);
	}

	public void load(String tileset){
		try {
			item.clear();
			
			File fXmlFile = new File(tileset);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("tile");
			int poz=36;
			int ypoz=tileY;
			int count=0;
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				
				Node nNode = nList.item(temp);
		 		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
					Element eElement = (Element) nNode;
					int imgIndex=Integer.parseInt(eElement.getAttribute("imageIndex"));	
					String path=eElement.getAttribute("path");
					push(new Item(new Image(path),960-288+poz,ypoz));
					item.get(item.size()-1).imageIndex=imgIndex;
					poz+=90;
					count++;
					if(count==3){
						count=0;
						poz=36;
						ypoz+=70;
					}
				}
			}

		 } catch (Exception e) {
			e.printStackTrace();
		    }
	}

	public void loadBuildings(String buildingSet){
		try {
			item.clear();
			
			File fXmlFile = new File(buildingSet);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("sprite");
			int poz=36;
			int ypoz=tileY;
			int count=0;
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					//int imgIndex=Integer.parseInt(eElement.getAttribute("imageIndex"));	
					String path=eElement.getAttribute("path");
					push(new Item(new Image(path),960-288+poz,ypoz));
					item.get(item.size()-1).imageIndex=temp+19;
					poz+=90;
					count++;
					if(count==3){
						count=0;
						poz=36;
						ypoz+=70;
					}
				}
			}

		 } catch (Exception e) {
			e.printStackTrace();
		 }
	}
}
