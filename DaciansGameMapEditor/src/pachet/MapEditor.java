package pachet;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.w3c.dom.DOMException;

import Buildings.Building;
import Buildings.BuildingTypes;
import Buildings.BuildingsContainer;
import DebuggingTools.SystemInfos;

public class MapEditor extends BasicGame
{
	int GRASS=1;
	int GROUND=0;
	Image bar;
	int wSize=24,hSize=16;
	GBar itemBar=new GBar();
	Map map=null;
	Resource goldMine;
	Input in;
	int selected=-1;
	ArrayList<Image> images;
	TextField txt,txt2,txt3,txt4,txt5;
	Font font;
	Image buttonS,buttonL,buttonSa,buttonLt,buttonLb;
	String added="";
	boolean showGrid=true;
	float x,y;
	Quad quad;
	SystemInfos si;
	
	BuildingsContainer container;
	
	public MapEditor(String gamename)
	{
		super(gamename);	
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {

		quad=new Quad();
		map=new Map(192,192);
		in=gc.getInput();
		map.Show(2,2);	
		
		bar=new Image("bar.png");
		itemBar.load("grass.xml");
		
		images=new ArrayList<Image>();
		for(int i=0;i<itemBar.item.size();i++)
			images.add(itemBar.item.get(i).img);
		
		font = new UnicodeFont(new java.awt.Font(java.awt.Font.SANS_SERIF, java.awt.Font.ITALIC, 26));
		txt = new TextField(gc, gc.getDefaultFont(),960-256, 30, 50, 25);
		txt2 = new TextField(gc, gc.getDefaultFont(),960-256+60, 30, 50, 25);
		txt3 = new TextField(gc, gc.getDefaultFont(),960-256, 60, 110, 25);
		txt4 = new TextField(gc, gc.getDefaultFont(),960-256, 90, 110, 25);
		txt5 = new TextField(gc, gc.getDefaultFont(),960-256, 120, 110, 25);
		
		buttonS=new Image("buildbutton.png");
		buttonL=new Image("loadbutton.png");
		buttonSa=new Image("savebutton.png");
		buttonLt=new Image("loadTileButton.png");
		buttonLb=new Image("loadBuildings.png");
		
		si=new SystemInfos();
		
		for(Item item: itemBar.item)
			System.out.println(item.imageIndex);
		
		container = new BuildingsContainer("buildingsDatabase.xml","buildingSpritesDatabase.xml");
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		in=gc.getInput();
		
		if(in.isKeyDown(Input.KEY_DOWN) || in.getAbsoluteMouseY()>=710)
			if(map.getY("last")>=gc.getHeight()){
				map.moveY(-15);
			}
		if(in.isKeyDown(Input.KEY_UP) || in.getAbsoluteMouseY()<=5)
			if(map.getY("first")<=-5){
				map.moveY(15);	
			}
		if(in.isKeyDown(Input.KEY_RIGHT) || in.getAbsoluteMouseX()>=1270)
			if(map.getX("last")>=gc.getWidth()-320){
				map.moveX(-15);
			}
		if(in.isKeyDown(Input.KEY_LEFT) || in.getAbsoluteMouseX()<=5)
			if(map.getX("first")<=-5){
				map.moveX(15);
			}

		if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			for(int j=0;j<itemBar.item.size();j++)
				if(in.getAbsoluteMouseX()>itemBar.item.get(j).x && in.getAbsoluteMouseX()<itemBar.item.get(j).x+64 && in.getAbsoluteMouseY()>itemBar.item.get(j).y && in.getAbsoluteMouseY()<itemBar.item.get(j).y+64)
				{	
					selected=j;
				}
			
			for(int k=0;k<map.Height;k++){
				for(int l=0;l<map.Width;l++)
					if(in.getAbsoluteMouseX()>map.tile[k][l].x+32 && in.getAbsoluteMouseX()<map.tile[k][l].x+96 && in.getAbsoluteMouseY()>map.tile[k][l].y+32 && in.getAbsoluteMouseY()<map.tile[k][l].y+96 && in.getAbsoluteMouseX()<704){
						if(selected!=-1 && l<map.Width-1 && k<map.Height-1){
							if(itemBar.item.get(selected).imageIndex==1){
								map.setCenter(k,l,GRASS);
								map.interpret();
							}
							else
							if(itemBar.item.get(selected).imageIndex==15){
								map.setCenter(k, l, GROUND);
								map.interpret();
							}
							else
							if(itemBar.item.get(selected).imageIndex>=19){
								switch(itemBar.item.get(selected).imageIndex){
									case 19:
										Building archery = container.getBuilding(BuildingTypes.ArcheryRange);
											archery.setX(map.tile[k][l].x);
											archery.setY(map.tile[k][l].y);
										map.addBuilding(archery);
									break;
									
									case 20:
										Building barracks = container.getBuilding(BuildingTypes.Barracks);
											barracks.setX(map.tile[k][l].x);
											barracks.setY(map.tile[k][l].y);
										map.addBuilding(barracks);
									break;
									
									case 21:
										Building farm = container.getBuilding(BuildingTypes.Farm);
											farm.setX(map.tile[k][l].x);
											farm.setY(map.tile[k][l].y);
										map.addBuilding(farm);
									break;
									
									case 22:
										Building temple = container.getBuilding(BuildingTypes.Temple);
											temple.setX(map.tile[k][l].x);
											temple.setY(map.tile[k][l].y);
										map.addBuilding(temple);
									break;
									
									case 23:
										Building townHall = container.getBuilding(BuildingTypes.TownHall);
											townHall.setX(map.tile[k][l].x);
											townHall.setY(map.tile[k][l].y);
										map.addBuilding(townHall);
									break;
								}
							}
						}
						
					}
			}
		}
		if(in.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
			for(int j=0;j<itemBar.item.size();j++)
				if(in.getAbsoluteMouseX()>itemBar.item.get(j).x && in.getAbsoluteMouseX()<itemBar.item.get(j).x+64 && in.getAbsoluteMouseY()>itemBar.item.get(j).y && in.getAbsoluteMouseY()<itemBar.item.get(j).y+64)
				{	selected=itemBar.item.get(j).imageIndex;
					//System.out.println("S-a dat click pe "+selected+" "+b.item.get(0).img.toString());
				}
				map.flood(selected);
				selected=-1;
				map.Show(0, 0);
		}
		
		if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))	{
			for(int k=0;k<map.Height;k++)
			{	
				for(int l=0;l<map.Width;l++)
					if(in.getAbsoluteMouseX()>map.tile[k][l].x && in.getAbsoluteMouseX()<map.tile[k][l].x+64 && in.getAbsoluteMouseY()>map.tile[k][l].y && in.getAbsoluteMouseY()<map.tile[k][l].y+64 && in.getAbsoluteMouseX()<704)
						if(selected!=-1){
								if(itemBar.item.get(selected).imageIndex==17){
									map.addResource(new Resource(new Image("goldMine.png"), "GoldMine", map.tile[k][l],k,l));break;
							}
								
							else if(itemBar.item.get(selected).imageIndex==18){
								map.addResource(new Resource(new Image("tree.png"), "Forest", map.tile[k][l],k,l));
								break;
							}
						}
			}
			if(selected!=-1)
				System.out.println("indexul e: "+itemBar.item.get(selected).imageIndex);
		}
				
		
		if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			if(in.getAbsoluteMouseX()>960-256+128 && in.getAbsoluteMouseX()<960-256+128+68 && in.getAbsoluteMouseY()>30 && in.getAbsoluteMouseY()<52)
				map=new Map(Integer.parseInt(txt.getText()),Integer.parseInt(txt2.getText()));
			
			if(in.getAbsoluteMouseX()>960-256+115 && in.getAbsoluteMouseX()<960-256+115+68 && in.getAbsoluteMouseY()>60 && in.getAbsoluteMouseY()<82){
				//map.loadFromXML(txt3.getText());
			}
			if(in.getAbsoluteMouseX()>960-256+190 && in.getAbsoluteMouseX()<960-256+190+68 && in.getAbsoluteMouseY()>60 && in.getAbsoluteMouseY()<82)
				try {
					map.saveToXML(txt3.getText());
				} catch (InvalidKeyException | DOMException
						| IllegalBlockSizeException | BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(in.getAbsoluteMouseX()>960-256+115 && in.getAbsoluteMouseX()<960-256+115+68 && in.getAbsoluteMouseY()>90 && in.getAbsoluteMouseY()<112){
				
				selected=-1;
				itemBar.load(txt4.getText());
				images=new ArrayList<Image>();
				for(int k=0;k<itemBar.item.size();k++)
					images.add(itemBar.item.get(k).img);
			}
			
			if(in.getAbsoluteMouseX()>960-256+115 && in.getAbsoluteMouseX()<960-256+115+68 && in.getAbsoluteMouseY()>120 && in.getAbsoluteMouseY()<142){
				selected=-1;
				itemBar.loadBuildings(txt5.getText());
				images=new ArrayList<Image>();
				for(int k=0;k<itemBar.item.size();k++)
					images.add(itemBar.item.get(k).img);
			}
		}
		
		
		if(in.isKeyDown(Input.KEY_LCONTROL))
				if(in.isKeyPressed(Input.KEY_Z))
					map.popResource();
		
		if(in.isKeyDown(Input.KEY_LCONTROL) && in.isKeyDown(Input.KEY_F))
			if(selected!=-1)
				map.flood(selected);
		
		if(in.isKeyDown(Input.KEY_LCONTROL))
				if(in.isKeyPressed(Input.KEY_G))
					if(showGrid==true)
						showGrid=false;
					else
						showGrid=true;
		
		if(in.isKeyDown(Input.KEY_ESCAPE))
			System.exit(0);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		map.render(g,container);
		if(showGrid==true)
			map.grid(g);
		
		g.drawImage(bar, 960-256, 0);
		itemBar.render(g);
		txt.render(gc,g);
		txt2.render(gc, g);
		txt3.render(gc, g);
		txt4.render(gc,g);
		txt5.render(gc, g);
		
		buttonS.draw(960-256+128, 30, 0.35f);
		buttonL.draw(960-256+115, 60, 0.35f);
		buttonSa.draw(960-256+190, 60, 0.35f);
		buttonLt.draw(960-256+115, 90, 0.35f);
		buttonLb.draw(960-256+115, 120, 0.35f);
		
		if (selected != -1) {
			if (itemBar.item.get(selected).imageIndex == 17)
				for (int k = 0; k < map.Height; k++) {
					for (int l = 0; l < map.Width; l++)
						if (in.getAbsoluteMouseX() > map.tile[k][l].x
								&& in.getAbsoluteMouseX() < map.tile[k][l].x + 64
								&& in.getAbsoluteMouseY() > map.tile[k][l].y
								&& in.getAbsoluteMouseY() < map.tile[k][l].y + 64
								&& in.getAbsoluteMouseX() < 704)
							quad.render("goldMine", map.tile[k][l].x,
									map.tile[k][l].y);
				}
			else if (itemBar.item.get(selected).imageIndex == 18)
				for (int k = 0; k < map.Height; k++) {
					for (int l = 0; l < map.Width; l++)
						if (in.getAbsoluteMouseX() > map.tile[k][l].x
								&& in.getAbsoluteMouseX() < map.tile[k][l].x + 64
								&& in.getAbsoluteMouseY() > map.tile[k][l].y
								&& in.getAbsoluteMouseY() < map.tile[k][l].y + 64
								&& in.getAbsoluteMouseX() < 704)
							quad.render("tree", map.tile[k][l].x,
									map.tile[k][l].y);
				}
			else
				for (int k = 0; k < map.Height; k++) {
					for (int l = 0; l < map.Width; l++)
						if (in.getAbsoluteMouseX() > map.tile[k][l].x + 32
								&& in.getAbsoluteMouseX() < map.tile[k][l].x + 96
								&& in.getAbsoluteMouseY() > map.tile[k][l].y + 32
								&& in.getAbsoluteMouseY() < map.tile[k][l].y + 96
								&& in.getAbsoluteMouseX() < 704)
							quad.render("single", map.tile[k][l].x + 32,
									map.tile[k][l].y + 32);
				}
		}
		
		/*if(selected!=-1)
			switch(itemBar.item.get(selected).path){
			case "goldMine.png": g.drawString("Selected: Gold Mine", 0, 0); break;
			case "tree.png": g.drawString("Selected: Trees", 0, 0); break;
			case "center1.png": g.drawString("Selected: Grass", 0, 0); break;
			case "base1.png": g.drawString("Selected: Dirt", 0, 0); break;
			default: g.drawString("Unable to identify resource", 0, 0); break;
			}*/
		
		g.drawString(si.showInfos().toString(), 0, 0);
	}

	public static void main(String[] args)
	{
		try
		{	
			AppGameContainer appgc;
			
			appgc = new AppGameContainer(new MapEditor("Dacia"));
			appgc.setDisplayMode(960, 600, false);//true pt fullscreen
			appgc.setTargetFrameRate(30);
			appgc.setShowFPS(false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(MapEditor.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}