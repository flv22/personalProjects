package Main;

import java.util.logging.Level;
import java.util.logging.Logger;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


import BaseAssets.MenuButton;
import Buildings.Building;
import Buildings.BuildingTypes;
import Buildings.BuildingsContainer;
import DebuggingTools.SystemInfos;
import GUI.GUI;
import Game.GameConstants;
import Game.GameState;
import Game.GameStates;
import Map.Map;
import Menu.Menu;
import ParticleEngine.Rain;
import Resources.Resource;
import SoundEngine.SoundHandler;
import Units.Unit;


public class SimpleSlickGame extends BasicGame
{
	Input in;
	Menu menu;
	GameState gamestate;
	Map map;
	GUI gui;
	SoundHandler sh;
	Rain rain;
	SystemInfos si;
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);	
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
	    menu=new Menu();
	    menu.setBackground(new Image("background2.png"));
	    menu.setPivot(340, 52);
	    menu.addButton(new MenuButton(new Image("playButton.png"),"PlayGame"));
	    menu.addButton(new MenuButton(new Image("optionsButton.png"),"Options"));
	    menu.addButton(new MenuButton(new Image("quitButton.png"),"QuitGame"));
	    menu.setActive(true);
	    
	    gamestate=new GameState();
	    gamestate.setActiveState(GameStates.MainMenu);
	    
	    map=new Map();
	    map.loadMap("map_dbg2.xml");
	    
	    gui=new GUI();
	    gui.setMainBar(new Image("gui.png"));
	    gui.setMinimapBackground(new Image("minimap.png"));
	    
	    BuildingsContainer buildingsContainer=new BuildingsContainer("buildingsDatabase.xml");
	    //buildingsContainer.ShowBuildings();
	    
	    Building newBuilding=buildingsContainer.getBuilding(BuildingTypes.Barracks);
	    map.addBuildingOnMatrix(newBuilding, 8, 10);
	    
	    newBuilding=buildingsContainer.getBuilding(BuildingTypes.ArcheryRange);  
	    map.addBuildingOnMatrix(newBuilding, 8, 13);
	    
	    newBuilding=buildingsContainer.getBuilding(BuildingTypes.Temple);  
	    map.addBuildingOnMatrix(newBuilding, 13, 13);
	    
	    newBuilding=buildingsContainer.getBuilding(BuildingTypes.TownHall);  
	    map.addBuildingOnMatrix(newBuilding, 13, 10);
	    /*newBuilding=buildingsContainer.getBuilding(BuildingTypes.Barracks);  
	    map.addBuildingOnMatrix(newBuilding, 10, 13);*/
	    /* newBuilding=buildingsContainer.getBuilding(BuildingTypes.Farm);  
	    map.addBuildingOnMatrix(newBuilding, 12, 14);
	    newBuilding=buildingsContainer.getBuilding(BuildingTypes.Farm);  
	    map.addBuildingOnMatrix(newBuilding, 11, 12);
	    map.debugMap();*/
	    
	    /*newBuilding=buildingsContainer.getBuilding(BuildingTypes.TownHall);
	    map.addBuilding(newBuilding);
	    
	    newBuilding=buildingsContainer.getBuilding(BuildingTypes.Farm);
	    map.addBuilding(newBuilding);
	    
	    newBuilding=buildingsContainer.getBuilding(BuildingTypes.Farm);
	    map.addBuilding(newBuilding);
	    
	    newBuilding=buildingsContainer.getBuilding(BuildingTypes.ArcheryRange);
	    map.addBuilding(newBuilding);*/
	    
	    /*newBuilding=buildingsContainer.getBuilding(BuildingTypes.Temple);
	    map.addBuildingOnMatrix(newBuilding, 13, 11);*/
	    //map.addBuilding(newBuilding);
	    sh=new SoundHandler();
	    sh.setMenuSound(new Sound("menuMusic.wav"));
	    sh.playMenuSound();
	    sh.setInGameSound(new Sound("rainSound.wav"));
	    
	    rain=new Rain();
	    
	    map.renderTileMap();
	    map.translateMap();
	    map.showCosts();
	    map.showMap();
	    
	    gui.setMinimap(map);
	    
	    si=new SystemInfos();
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		in=gc.getInput();
		
		switch(gamestate.getActiveState()){
		case GameStates.MainMenu:
			switch (menu.listen(in)) {
				case "PlayGame":
					gamestate.setActiveState(GameStates.Gameplay);
					
					sh.stopMenuSound();
					sh.playInGameSound();
				break;
	
				case "Options":
					gamestate.setActiveState(GameStates.OptionsMenu);
					
				break;
	
				case "QuitGame":
					gamestate.setActiveState(GameStates.OptionsMenu);
					
					System.exit(0);
				break;
			}
		break;
			
		case GameStates.Gameplay:
			if (in.isKeyDown(Input.KEY_LEFT) || in.getAbsoluteMouseX()<GameConstants.mapScrollSpeed)
				if(map.getTopLeftPoint().getX()<=-GameConstants.mapScrollSpeed){
					map.moveX(GameConstants.mapScrollSpeed);					
				}
			if (in.isKeyDown(Input.KEY_RIGHT) || in.getAbsoluteMouseX()>gc.getWidth()-GameConstants.mapScrollSpeed)
				if(map.getTopRightPoint().getX()>=gc.getWidth()-GameConstants.mapScrollSpeed){
					map.moveX(-GameConstants.mapScrollSpeed);					
				}
			if(in.isKeyDown(Input.KEY_DOWN) || in.getAbsoluteMouseY()>gc.getHeight()-GameConstants.mapScrollSpeed)
				if(map.getBottomRightPoint().getY()>=gc.getHeight()){
					map.moveY(-GameConstants.mapScrollSpeed);				
				}
			if(in.isKeyDown(Input.KEY_UP) || in.getAbsoluteMouseY()<GameConstants.mapScrollSpeed)
				if(map.getTopRightPoint().getY()<=-GameConstants.mapScrollSpeed){
					map.moveY(GameConstants.mapScrollSpeed);					
				}
			if(in.isKeyDown(Input.KEY_ESCAPE))
				gamestate.setActiveState(GameStates.MainMenu);
			
			if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				for(Resource item:map.getResources())
					if(item.isFocused(in.getAbsoluteMouseX(), in.getAbsoluteMouseY()))
						item.setBoundingBoxVisible(true);
				
				for(Building item:map.getBuildings())
					if(item.isFocused(in.getAbsoluteMouseX(), in.getAbsoluteMouseY()))
						map.setSelectedBuilding(item);
				
				if(map.getSelectedBuilding()!=null)
					if(map.getSelectedBuilding().isButtonFocused(in.getAbsoluteMouseX(), in.getAbsoluteMouseY())){
						//map.getSelectedBuilding().addTaskOnStack();
						map.getSelectedBuilding().addTaskOnStack();
						map.getSelectedBuilding().showTask();
						break;
					}
				for(Unit item: map.getUnits())
					if(item.isFocused(in.getAbsoluteMouseX(),in.getAbsoluteMouseY())){
						map.setSelectedUnit(item);
						break;
					}
			}
			if(in.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
				map.setUnitTarget(in.getAbsoluteMouseX(), in.getAbsoluteMouseY());
				//map.getSelectedUnit().showPath();
				try{
					map.getSelectedUnit().computePath();
				}catch(Exception e){
					
				}
				System.out.println(map.getSelectedUnit().getiTargetPos()+"-"+map.getSelectedUnit().getjTargetPos());
			}
			map.updateTasks();
			if(map.getSelectedUnit()!=null)
				map.getSelectedUnit();
			
			if(in.isKeyPressed(Input.KEY_D))
				map.debugMap();//System.out.println("S-a dat click pe "+item.getResourceType());
			/*if(map.getSelectedBuilding()!=null)
				map.getSelectedBuilding().updateTasks();*/
		break;
		}	
		
		rain.doRain();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		if(gamestate.getActiveState()==GameStates.MainMenu)
			menu.render(g);
		
		if(gamestate.getActiveState()==GameStates.Gameplay){
			map.renderMap(g);
			
			if (map.getSelectedUnit() != null){
				try{
					map.getSelectedUnit().showPathOnMap(map);
				}catch(Exception e){
					
				}
			}
				
			//rain.render(g);
			
			gui.render(g);
			gui.showInfos(map.getSelectedBuilding());
			gui.showInfos(map.getSelectedUnit());
			g.drawString(si.showInfos().toString(), 0, 20);
			//g.setColor(new Color(188.0f,227.0f,229.0f,0.3f));
		}
	}
	
	public static void main(String[] args)
	{	
		try
		{		
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Dacia"));
			appgc.setDisplayMode(1280, 720, false);//true pt fullscreen
			appgc.setTargetFrameRate(40);
			appgc.setShowFPS(true);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}