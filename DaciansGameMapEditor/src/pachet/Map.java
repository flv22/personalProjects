package pachet;
import java.io.File;
import java.security.InvalidKeyException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Buildings.Building;
import Buildings.BuildingsContainer;


public class Map {
	Tile[][] tile;
	int Width,Height;
	private ArrayList<Resource> res=new ArrayList<Resource>();
	private ArrayList<Building> buildings=new ArrayList<Building>();
	
	private int GRASS=1;
	private int GROUND=0;
	ArrayList<Image> sprites=new ArrayList<Image>(); 
	
	public void addBuilding(Building b){
		buildings.add(b);
	}
	
	public void Show(int i,int j){
	
				System.out.printf("%d %d %d %d", tile[i][j].bit[0][0],tile[i][j].bit[0][1],tile[i][j].bit[1][0],tile[i][j].bit[1][1]);
				System.out.printf("\n");
				System.out.printf("%d %d %d %d", tile[i][j+1].bit[0][0],tile[i][j+1].bit[0][1],tile[i][j+1].bit[1][0],tile[i][j+1].bit[1][1]);
				System.out.printf("\n");
				System.out.printf("%d %d %d %d", tile[i+1][j].bit[0][0],tile[i+1][j].bit[0][1],tile[i+1][j].bit[1][0],tile[i+1][j].bit[1][1]);
				System.out.printf("\n");
				System.out.printf("%d %d %d %d", tile[i+1][j+1].bit[0][0],tile[i+1][j+1].bit[0][1],tile[i+1][j+1].bit[1][0],tile[i+1][j+1].bit[1][1]);
				System.out.printf("\n");
			
		
	}
	
	/*public void convert() throws SlickException{
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				switch(tile[i][j].letter){
				case 'A': tile[i][j].tile=new Image("CornerOUL.png");tile[i][j].path="CornerOUL.png"; break;
				case 'B': tile[i][j].tile=new Image("CornerOUR.png");tile[i][j].path="CornerOUR.png"; break;
				case 'C': tile[i][j].tile=new Image("CornerODL.png");tile[i][j].path="CornerODL.png"; break;
				case 'D': tile[i][j].tile=new Image("CornerODR.png");tile[i][j].path="CornerODR.png"; break;
				case 'E': tile[i][j].tile=new Image("SideU.png");tile[i][j].path="SideU.png"; break;
				case 'F': tile[i][j].tile=new Image("SideD.png");tile[i][j].path="SideD.png"; break;
				case 'G': tile[i][j].tile=new Image("SideL.png");tile[i][j].path="SideL.png"; break;
				case 'H': tile[i][j].tile=new Image("SideR.png");tile[i][j].path="SideR.png"; break;
				case 'I': tile[i][j].tile=new Image("CornerIUL.png");tile[i][j].path="CornerIUL.png"; break;
				case 'J': tile[i][j].tile=new Image("CornerIUR.png");tile[i][j].path="CornerIUR.png"; break;
				case 'K': tile[i][j].tile=new Image("CornerIDL.png");tile[i][j].path="CornerIDL.png"; break;
				case 'L': tile[i][j].tile=new Image("CornerIDR.png");tile[i][j].path="CornerIDR.png"; break;
				case 'M': tile[i][j].tile=new Image("Center1.png");tile[i][j].path="Center1.png"; break;
				default: break;
				}
			}
		}
	}*/
	
	public void setCenter(int k,int l,int val){
		/*if(tile[k][l].letter=='X'){System.out.println("Da!");}
		
		if((tile[k][l-1].letter=='A' || tile[k][l-1].letter=='E') && tile[k][l].letter=='B'){
            tile[k][l].letter='E';
            tile[k+1][l].letter='F';
            tile[k][l+1].letter='B';
            tile[k+1][l+1].letter='D';
       }
		else if(tile[k][l].letter=='A' && (tile[k][l+1].letter=='B' || tile[k][l+1].letter=='E')){
			tile[k][l].letter='E';
			tile[k+1][l].letter='F';
			tile[k][l-1].letter='A';
			tile[k+1][l-1].letter='C';
       }
		else if(tile[k][l].letter=='C' && tile[k][l+1].letter=='F'){
			tile[k][l].letter='G';
			tile[k][l+1].letter='L';
			tile[k+1][l].letter='C';
			tile[k+1][l+1].letter='D';
       }
		else if(tile[k][l].letter=='L' && tile[k][l+1].letter=='F'){
			tile[k][l].letter='M';
			tile[k][l+1].letter='L';
			tile[k+1][l].letter='F';
			tile[k+1][l+1].letter='D';
		}
		else if(tile[k][l].letter=='L' && tile[k][l+1].letter=='D'){
			tile[k][l].letter='M';
			tile[k][l+1].letter='H';
			tile[k+1][l].letter='F';
			tile[k+1][l+1].letter='D';
		}
		else if(tile[k][l].letter=='C' && tile[k][l+1].letter=='D'){
			tile[k][l].letter='G';
			tile[k][l+1].letter='H';
			tile[k+1][l].letter='C';
			tile[k+1][l+1].letter='D';
		}
		else
	       {
			tile[k][l].letter='A';
			tile[k][l+1].letter='B';
			tile[k+1][l].letter='C';
			tile[k+1][l+1].letter='D';
	       }
		
		/*if((tile[k][l-1].path.equals("CornerOUL.png") || tile[k][l-1].path.equals("SideU.png")) && tile[k][l].path.equals("CornerOUR.png")){
           tile[k][l].path="SideU.png"; try {
			tile[k][l].tile=new Image("SideU.png");
			tile[k+1][l].path="SideD.png"; 
				tile[k+1][l].tile=new Image("SideD.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           tile[k][l+1].path="CornerOUR.png"; try {
			tile[k][l+1].tile=new Image("CornerOUR.png");
			tile[k+1][l+1].path="CornerODR.png"; 
				tile[k+1][l+1].tile=new Image("CornerODR.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//='B';
       }
		else if(tile[k][l].path.equals("CornerOUL.png") && (tile[k][l+1].path.equals("CornerOUR.png") || tile[k][l+1].path.equals("SideU.png"))){
			try {
				tile[k][l].tile=new Image("SideU.png");tile[k][l].path="SideU.png";
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				tile[k][l-1].tile=new Image("CornerOUL.png");tile[k][l-1].path="CornerOUL.png";
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				tile[k+1][l].tile=new Image("SideD.png");tile[k+1][l].path="SideD.png";
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				tile[k+1][l-1].tile=new Image("CornerODL.png");tile[k+1][l-1].path="CornerODL.png";
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
       else
       {
           tile[k][l].path="CornerOUL.png";try {
			tile[k][l].tile=new Image("CornerOUL.png");
			tile[k+1][l].path="CornerODL.png";
				tile[k+1][l].tile=new Image("CornerODL.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//='A';
           tile[k][l+1].path="CornerOUR.png";try {
			tile[k][l+1].tile=new Image("CornerOUR.png");
			 tile[k+1][l+1].path="CornerODR.png";
					tile[k+1][l+1].tile=new Image("CornerODR.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//='B';
       }*/
			
			
		//tile[k][l].path=path;//b.item.get(selected).path;
		//System.out.println(tile[k+1][l].getX()+" "+tile[k+1][l].getY());
		
			tile[k][l].bit[1][1]=val;
			tile[k][l+1].bit[1][0]=val;
			tile[k+1][l].bit[0][1]=val;
			tile[k+1][l+1].bit[0][0]=val;
		
		//////////////////////////////////////////
	}
	
	public void interpret() throws SlickException{
		for(int i=0;i<Height;i++)
			for(int j=0;j<Width;j++){///////////////////////////////////////////////////////////////////////////////////////////////
				if(tile[i][j].bit[0][0]==GROUND && tile[i][j].bit[0][1]==GROUND && tile[i][j].bit[1][0]==GROUND && tile[i][j].bit[1][1]==GRASS){
					tile[i][j].setImageIndex(9);
				}
				if(tile[i][j].bit[0][0]==GROUND && tile[i][j].bit[0][1]==GROUND && tile[i][j].bit[1][0]==GRASS && tile[i][j].bit[1][1]==GROUND){
					tile[i][j].setImageIndex(10);
				}
				if(tile[i][j].bit[0][0]==GROUND && tile[i][j].bit[0][1]==GRASS && tile[i][j].bit[1][0]==GROUND && tile[i][j].bit[1][1]==GROUND){
					tile[i][j].setImageIndex(7);
				}
				if(tile[i][j].bit[0][0]==GRASS && tile[i][j].bit[0][1]==GROUND && tile[i][j].bit[1][0]==GROUND && tile[i][j].bit[1][1]==GROUND){
					tile[i][j].setImageIndex(8);
				}
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(tile[i][j].bit[0][0]==GROUND && tile[i][j].bit[0][1]==GROUND && tile[i][j].bit[1][0]==GRASS && tile[i][j].bit[1][1]==GRASS){
					tile[i][j].setImageIndex(14);
				}
				if(tile[i][j].bit[0][0]==GRASS && tile[i][j].bit[0][1]==GRASS && tile[i][j].bit[1][0]==GROUND && tile[i][j].bit[1][1]==GROUND){
					tile[i][j].setImageIndex(11);
				}
				if(tile[i][j].bit[0][0]==GRASS && tile[i][j].bit[0][1]==GROUND && tile[i][j].bit[1][0]==GRASS && tile[i][j].bit[1][1]==GROUND){
					tile[i][j].setImageIndex(13);
				}
				if(tile[i][j].bit[0][0]==GROUND && tile[i][j].bit[0][1]==GRASS && tile[i][j].bit[1][0]==GROUND && tile[i][j].bit[1][1]==GRASS){
					tile[i][j].setImageIndex(12);
				}
				/////////////////////////////////////////////////////////////
				
				if(tile[i][j].bit[0][0]==GROUND && tile[i][j].bit[0][1]==GROUND && tile[i][j].bit[1][0]==GROUND && tile[i][j].bit[1][1]==GROUND){
					tile[i][j].setImageIndex(15);
				}
				if(tile[i][j].bit[0][0]==GRASS && tile[i][j].bit[0][1]==GRASS && tile[i][j].bit[1][0]==GRASS && tile[i][j].bit[1][1]==GRASS){
					tile[i][j].setImageIndex(1);
				}
				/////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				if(tile[i][j].bit[0][0]==GRASS && tile[i][j].bit[0][1]==GRASS && tile[i][j].bit[1][0]==GRASS && tile[i][j].bit[1][1]==GROUND){
					tile[i][j].setImageIndex(4);
				}
				if(tile[i][j].bit[0][0]==GRASS && tile[i][j].bit[0][1]==GRASS && tile[i][j].bit[1][0]==GROUND && tile[i][j].bit[1][1]==GRASS){
					tile[i][j].setImageIndex(3);
				}
				if(tile[i][j].bit[0][0]==GROUND && tile[i][j].bit[0][1]==GRASS && tile[i][j].bit[1][0]==GRASS && tile[i][j].bit[1][1]==GRASS){

					tile[i][j].setImageIndex(5);
				}
				if(tile[i][j].bit[0][0]==GRASS && tile[i][j].bit[0][1]==GROUND && tile[i][j].bit[1][0]==GRASS && tile[i][j].bit[1][1]==GRASS){
					tile[i][j].setImageIndex(6);
				}
			}
				
	}
	
	public void flood(int imageIndex){
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				tile[i][j].setImageType(imageIndex);
				tile[i][j].letter='X';
				
				switch(imageIndex){
				case 15: 	tile[i][j].bit[1][1]=0;
									tile[i][j].bit[1][0]=0;
									tile[i][j].bit[0][1]=0;
									tile[i][j].bit[0][0]=0; 
				break;
				
				case 0: tile[i][j].bit[1][1]=1;
									tile[i][j].bit[1][0]=1;
									tile[i][j].bit[0][1]=1;
									tile[i][j].bit[0][0]=1; 
				break;

				default: break;
				}
				
			}
		}
	}
	
	/*protected Map(String filename){
		try {
						
			File fXmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("tile");
			NodeList hList = doc.getElementsByTagName("harta");
			
			Node hNode=hList.item(0);
			Element hElement=(Element)hNode;
			Width=Integer.parseInt(hElement.getAttribute("wSize"));
			Height=Integer.parseInt(hElement.getAttribute("hSize"));
			
			/////////////////////////////////////////////////////////////////////
			tile=new Tile[Height][Width];
			res=new ArrayList<Resource>();
			System.out.println("trecut1!");
			
			int id=0;
			for(int i=0;i<Height;i++){
				for(int j=0;j<Width;j++){
					tile[i][j]=new Tile();
					tile[i][j].setX(j*64);
					tile[i][j].setY(i*64);
					tile[i][j].setId(id);
					tile[i][j].path=new String("defaultTile.png");
					id++;
					try {
						tile[i][j].setTile(new Image("defaultTile.png"));
					} catch (SlickException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}		
			}////////////////////////////////////////////////////////////////////
			
			System.out.println("trecut!");
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					//int id=Integer.parseInt(eElement.getAttribute("id"));
					//String enunt=eElement.getElementsByTagName("path").item(0).getTextContent();
					//int id=Integer.parseInt(eElement.getAttribute("id"));
					int i=Integer.parseInt(eElement.getAttribute("ipos"));
					int j=Integer.parseInt(eElement.getAttribute("jpos"));
					String path=eElement.getAttribute("path");
					tile[i][j].path=path;
					setTile(path, i, j);
					
					//System.out.println(enunt);
					//System.out.println(id);
				}
			}
			
			//
			/*NodeList gList = doc.getElementsByTagName("GoldMine");
			System.out.println(gList.getLength());
			for (int temp = 0; temp < gList.getLength(); temp++) {
		 
				Node nNode = gList.item(temp);
		 		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					//int id=Integer.parseInt(eElement.getAttribute("id"));
					//String enunt=eElement.getElementsByTagName("path").item(0).getTextContent();
					int pivot=Integer.parseInt(eElement.getAttribute("pivot"));
					int j=Integer.parseInt(eElement.getAttribute("quantity"));
					
					for(int i=0;i<Height;i++)
						for(j=0;j<Width;j++){
							if(tile[i][j].getId()==pivot)
								addResource(new Resource(new Image("goldMine.png"),"Gold",tile[i][j].getX(),tile[i][j].getY()));
						}
							
					//addResource(new Resource(new Image("goldMine.png"),"Gold",64,192));
				}
			}
			//
			
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		
		
	}*/
	
	public Map(int w,int h){
		Height=h;
		Width=w;
		tile=new Tile[h][w];
		int id=0;
		for(int i=0;i<h;i++){
			for(int j=0;j<w;j++){
				tile[i][j]=new Tile();
				tile[i][j].setImageType(0);
				tile[i][j].setX(j*64);
				tile[i][j].setY(i*64);
				tile[i][j].setId(id);
				id++;
			}		
		}
		
		try {
			sprites.add(new Image("Center1.png"));//0
			sprites.add(new Image("Center2.png"));//1
			sprites.add(new Image("Center3.png"));//2
			
			sprites.add(new Image("CornerIDL.png"));//3
			sprites.add(new Image("CornerIDR.png"));//4
			sprites.add(new Image("CornerIUL.png"));//5
			sprites.add(new Image("CornerIUR.png"));//6
			
			sprites.add(new Image("CornerODL.png"));//7
			sprites.add(new Image("CornerODR.png"));//8
			sprites.add(new Image("CornerOUL.png"));//9
			sprites.add(new Image("CornerOUR.png"));//10
			
			sprites.add(new Image("SideD.png"));//11
			sprites.add(new Image("SideL.png"));//12
			sprites.add(new Image("SideR.png"));//13
			sprites.add(new Image("SideU.png"));//14
			
			sprites.add(new Image("Base1.png"));//15
			sprites.add(new Image("CornerIDR.png"));//16
			
			sprites.add(new Image("goldMine.png"));//17
			sprites.add(new Image("tree.png"));//18
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public static Map getInstance(String filename){
		if(instance==null){
			instance=new Map(filename);
		}
		
		return instance;
	}*/
	
	public void addResource(Resource r){
		res.add(r);
		
		for(Resource item: res)
			System.out.println("Adaugat: "+item.type);
	}
	
	public void popResource(){
		if(res.size()>0)
			res.remove(res.size()-1);
	}
	
	public float getX(String whichX){
		if(whichX.equals("first"))
			return tile[0][0].getX();
		else
		if(whichX.equals("last"))
			return tile[0][Width-1].getX();
		else
			return 0;
	}
	
	public float getY(String whichX){
		if(whichX.equals("first"))
			return tile[0][0].getY();
		else
		if(whichX.equals("last"))
			return tile[Height-1][0].getY();
		else
			return 0;
	}
	
	public void moveX(float speed){
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				tile[i][j].setX(tile[i][j].getX()+speed);
			}
		}
		
		for(int i=0;i<res.size();i++)
			res.get(i).x=res.get(i).x+speed;
		
		for(Building item: buildings)
			item.setX(item.getX()+speed);
	}
	
	public void moveY(float speed){
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				tile[i][j].setY(tile[i][j].getY()+speed);
			}
		}
		
		for(int i=0;i<res.size();i++)
			res.get(i).y=res.get(i).y+speed;
		
		for(Building item: buildings)
			item.setY(item.getY()+speed);
	}
	
	public void render(Graphics g,BuildingsContainer container){
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				if( (tile[i][j].getX()>=0 && tile[i][j].getX()<=960) || (tile[i][j].getX()+64>=0 && tile[i][j].getX()+64<=960) || (tile[i][j].getY()>=0 && tile[i][j].getY()<=600) || (tile[i][j].getY()+64>=0 && tile[i][j].getY()+64<=600) )
					g.drawImage(sprites.get(tile[i][j].getImageType()), tile[i][j].getX(), tile[i][j].getY());
			}
		}
		
		for(int i=0;i<res.size();i++)
			res.get(i).img.draw(res.get(i).x,res.get(i).y);
		
		for(Building item: buildings)
			container.getSprites().get(item.getSprite()).draw(item.getX(), item.getY());
	}
	
	public void grid(Graphics g){
		g.setColor(Color.white);
		for(int i=0;i<Height;i++){
			for(int j=0;j<Width;j++){
				g.drawLine(tile[i][j].getX(),tile[i][j].getY(),tile[i][j].getX()+64,tile[i][j].getY());
				g.drawLine(tile[i][j].getX(), tile[i][j].getY(), tile[i][j].getX(), tile[i][j].getY()+64);
			}
		}
	}
	
	public void setTile(int imageIndex,int i,int j){
			tile[i][j].setImageType(imageIndex);
	}
	
	/*public void loadFromXML(String filename){
		try {
			
			File fXmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("tile");
			NodeList hList = doc.getElementsByTagName("harta");
			
			Node hNode=hList.item(0);
			Element hElement=(Element)hNode;
			int wSize=Integer.parseInt(hElement.getAttribute("wSize"));
			int hSize=Integer.parseInt(hElement.getAttribute("hSize"));
			///////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////
			Height=hSize;
			Width=wSize;
			tile=new Tile[hSize][wSize];
			int id=0;
			for(int i=0;i<hSize;i++){
				for(int j=0;j<wSize;j++){
					tile[i][j]=new Tile();
					tile[i][j].setX(j*64);
					tile[i][j].setY(i*64);
					tile[i][j].setId(id);
					tile[i][j].path=new String("defaultTile.png");
					id++;
					try {
						tile[i][j].setTile(new Image("defaultTile.png"));
					} catch (SlickException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}		
			}
			
			
			//Height=hSize;
			
			//System.out.println("N: "+wSize);
			System.out.println("Trecut!");
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					//int id=Integer.parseInt(eElement.getAttribute("id"));
					//String enunt=eElement.getElementsByTagName("path").item(0).getTextContent();
					//int id=Integer.parseInt(eElement.getAttribute("id"));
					int i=Integer.parseInt(eElement.getAttribute("ipos"));
					int j=Integer.parseInt(eElement.getAttribute("jpos"));
					String path=eElement.getAttribute("path");
					tile[i][j].path=path;
					setTile(path, i, j);
					
					//System.out.println(enunt);
					//System.out.println(id);
				}
			}
			
			//
			/*NodeList gList = doc.getElementsByTagName("GoldMine");
			System.out.println(gList.getLength());
			for (int temp = 0; temp < gList.getLength(); temp++) {
		 
				Node nNode = gList.item(temp);
		 		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					//int id=Integer.parseInt(eElement.getAttribute("id"));
					//String enunt=eElement.getElementsByTagName("path").item(0).getTextContent();
					int pivot=Integer.parseInt(eElement.getAttribute("pivot"));
					int j=Integer.parseInt(eElement.getAttribute("quantity"));
					
					for(int i=0;i<Height;i++)
						for(j=0;j<Width;j++){
							if(tile[i][j].getId()==pivot)
								addResource(new Resource(new Image("goldMine.png"),"Gold",tile[i][j].getX(),tile[i][j].getY()));
						}
							
					//addResource(new Resource(new Image("goldMine.png"),"Gold",64,192));
				}
			}
			//
			
		    } catch (Exception e) {
			e.printStackTrace();
		    }
	}*/
	
	public void saveToXML(String filename) throws InvalidKeyException, DOMException, IllegalBlockSizeException, BadPaddingException{
			  try {
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			 
					// root elements
					Document doc = docBuilder.newDocument();
					Element rootElement = doc.createElement("harta");
					Attr atr=doc.createAttribute("wSize");
					Attr atrx=doc.createAttribute("hSize");
					atr.setValue(Integer.toString(Width));
					atrx.setValue(Integer.toString(Height));
					rootElement.setAttributeNode(atrx);
					rootElement.setAttributeNode(atr);
					doc.appendChild(rootElement);
			 
					// staff elements
					for(int i=0;i<Height;i++)
						for(int j=0;j<Width;j++){
						
							Element e = doc.createElement("tile");
							rootElement.appendChild(e);
							
							// set attribute to staff element
							Attr attr = doc.createAttribute("ipos");
							attr.setValue(Integer.toString(i));
							e.setAttributeNode(attr);
							
							attr = doc.createAttribute("jpos");
							attr.setValue(Integer.toString(j));
							e.setAttributeNode(attr);							
							
							attr = doc.createAttribute("sprite");
							attr.setValue(Integer.toString(tile[i][j].getImageType()));
							e.setAttributeNode(attr);
						}
					//////////////////////////////////////////////////////////////////-------------------------------------------
					Element e;
					for(int i=0;i<res.size();i++){
						if(res.get(i).type.equals("Forest"))
							e = doc.createElement("Forest");
						else
							e = doc.createElement("GoldMine");
						
							rootElement.appendChild(e);
							
							Attr attr = doc.createAttribute("ip");
							attr.setValue(Integer.toString(res.get(i).j));
							e.setAttributeNode(attr);
							
							// set attribute to staff element
							attr = doc.createAttribute("jp");
							attr.setValue(Integer.toString(res.get(i).i));
							e.setAttributeNode(attr);
							
							attr = doc.createAttribute("length");
							attr.setValue(Integer.toString(1));
							e.setAttributeNode(attr);
							
							//System.out.println(tile[i][j].path);
							attr = doc.createAttribute("height");
							attr.setValue(Integer.toString(1));
							e.setAttributeNode(attr);							
							
							attr = doc.createAttribute("quantity");
							attr.setValue(Integer.toString(35000));
							e.setAttributeNode(attr);
							
							// write the content into xml file
							
						}
					
					for(Building item: buildings){
						Element building = doc.createElement("building");
						rootElement.appendChild(building);
						
						// set attribute to staff element
						
						
						Attr attr = doc.createAttribute("xPos");
						attr.setValue(Float.toString(item.getX()));
						building.setAttributeNode(attr);
						
						attr = doc.createAttribute("yPos");
						attr.setValue(Float.toString(item.getY()));
						building.setAttributeNode(attr);							
						
						attr = doc.createAttribute("type");
						switch(item.getName()){
						case "Archery Range":
							attr.setValue(Integer.toString(4));
							break;
						case "Barracks":
							attr.setValue(Integer.toString(3));
							break;
						case "Farm":
							attr.setValue(Integer.toString(2));
							break;
						case "Temple":
							attr.setValue(Integer.toString(5));
							break;
						case "Town Hall":
							attr.setValue(Integer.toString(1));
							break;
						}
						
						
						building.setAttributeNode(attr);
					}
					
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File(filename+".xml"));

					transformer.transform(source, result);
				  } catch (ParserConfigurationException pce) {
					pce.printStackTrace();
				  } catch (TransformerException tfe) {
					tfe.printStackTrace();
				  }
				}
	
	public int getWidth() {
		return Width;
	}
	
	public void setWidth(int width) {
		Width = width;
	}
	
	public int getHeight() {
		return Height;
	}
	
	public void setHeight(int height) {
		Height = height;
	}
	}
	