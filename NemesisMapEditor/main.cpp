#include <stdio.h>
#include <fstream>
#include "hge.h"
#include "hgesprite.h"
#include "hgegui.h"
#include "hgefont.h"

#include "Menu.h"
#include "Tile.h"
#include "Types.h"

#define SCREEN_WIDTH  1280
#define SCREEN_HEIGHT 640

#define MAX_TILES 25
#define WTILES 15
#define HTILES 10
#define MAX_TOWERS 255
#define MAX_MOBS 255
#define NO_MOBS 20

HGE *hge=0;

/////////////Tiles
hgeSprite *spr;
HTEXTURE tex;
Tile* t[MAX_TILES][MAX_TILES];
///////////////////////////////

////////////////Font
hgeFont* fnt=0;
////////////////////

////////MOUSE
hgeGUI *gui;
hgeSprite* mouse;
HTEXTURE mouseTex;
float mx,my;
hgeSprite* mouse_tile1;
HTEXTURE mouse_tileTex1;
hgeSprite* mouse_tile2;
HTEXTURE mouse_tileTex2;
//////////////////

/////////////////Backgroundul
hgeSprite* background;
HTEXTURE backgroundTex;
/////////////////////////////

///////////////////Meniul dreapta
Menu* menu;
Window* window;
Button* path_mode;
Button* block_mode;

hgeSprite* pathSpr;
HTEXTURE pathTex;
hgeSprite* blockSpr;
HTEXTURE blockTex;
hgeSprite* windowSpr;
HTEXTURE windowTex;
////////////////////////////////

//////////////////////////Modes
int PathMode;
int BlockMode;

typedef struct
{
       int x,y,w,h;
       int ID; 
}Point;
Point point[255];
int noPoints=0;


Point blocked[255];
int noBlockeds=0;

using namespace std;

void Init()
{    
     int _x=0;
     int _y=0;
     
     window=new Window(960,0);
     path_mode=new Button(960+64,64);
     block_mode=new Button(960+64,192);
     menu=new Menu(320,640);
     menu->AddWindow(window);
     menu->AddButtons(path_mode,block_mode);
     
     PathMode=0;
     BlockMode=0;
     
     for(int i=0;i<10;i++)
     {
             for(int j=0;j<15;j++)
             {
                     t[i][j]=new Tile(_x,_y,true);
                     _x+=64;
             }
             
             _x=0;
             _y+=64;
     }
}

bool FrameFunc()
{    	
     hge->Input_GetMousePos(&mx,&my);
     float dt=hge->Timer_GetDelta();
         
     
     
     if(hge->Input_GetKeyState(HGEK_ESCAPE))
     {                  
                        ofstream fout("path.txt");
                        fout<<noPoints<<endl;
                        
                        for(int i=1;i<=noPoints;i++)
                                fout<<point[i].w<<" "<<point[i].h<<endl;
                        
                        fout<<noBlockeds<<endl;
                        
                        for(int i=1;i<=noBlockeds;i++)
                                fout<<blocked[i].w<<" "<<blocked[i].h<<endl;
                                
                        fout.close();        
                        return true;
     }
          
     
     if(hge->Input_GetKeyState(HGEK_LBUTTON))
     {
                                             if(mx>path_mode->GetX() && mx<path_mode->GetX()+128 && my>path_mode->GetY() && my<path_mode->GetY()+128)
                                             {
                                                                                PathMode=1;
                                                                                BlockMode=0;
                                             }
                                             else
                                             if(mx>block_mode->GetX() && mx<block_mode->GetX()+128 && my>block_mode->GetY() && my<block_mode->GetY()+128)
                                             {
                                                                                PathMode=0;
                                                                                BlockMode=1;                         
                                             }
                                             
                                             for(int i=0;i<10;i++)
                                             {
                                                     for(int j=0;j<15;j++)
                                                     {
                                                             if(mx>t[i][j]->GetX() && mx<t[i][j]->GetX()+64 && my>t[i][j]->GetY() && my<t[i][j]->GetY()+64)
                                                             {
                                                                                   if(PathMode==1)
                                                                                   {              
                                                                                                  noPoints++;
                                                                                                  point[noPoints].x=t[i][j]->GetX();
                                                                                                  point[noPoints].y=t[i][j]->GetY();
                                                                                                  point[noPoints].w=i;
                                                                                                  point[noPoints].h=j;
                                                                                                  
                                                                                                  point[noPoints].ID=noPoints;
                                                                                                  PathMode=0;
                                                                                   }
                                                                                   if(BlockMode==1)
                                                                                   {
                                                                                                   noBlockeds++;
                                                                                                   blocked[noBlockeds].x=t[i][j]->GetX();
                                                                                                   blocked[noBlockeds].y=t[i][j]->GetY();
                                                                                                   blocked[noBlockeds].w=i;
                                                                                                   blocked[noBlockeds].h=j;
                                                                                                   blocked[noBlockeds].ID=noBlockeds*100;
                                                                                                   
                                                                                                   BlockMode=0;               
                                                                                   }
                                                                                   
                                                             }
                                                     }
                                             }
                                                                                                  
     }
                                                                       
     return false;
}

bool RenderFunc()
{
     hge->Gfx_BeginScene();
     
     background->Render(0,0);
     
     menu->Show(windowSpr,pathSpr,blockSpr);
     
     for(int i=0;i<10;i++)
             for(int j=0;j<15;j++)
                     spr->Render(t[i][j]->GetX(),t[i][j]->GetY());
     
     for(int i=1;i<=noPoints;i++)
             fnt->printf(point[i].x+20,point[i].y+20,HGETEXT_LEFT,"%d",point[i].ID);
     for(int i=1;i<=noBlockeds;i++)
             fnt->printf(blocked[i].x+20,blocked[i].y+20,HGETEXT_LEFT,"%d",blocked[i].ID);
             
     mouse->Render(mx,my);   
     
     hge->Gfx_EndScene();
     
     return false;
}

int WINAPI WinMain(HINSTANCE, HINSTANCE, LPSTR, int)
{   
    
    Init();
    hge=hgeCreate(HGE_VERSION);
    
    
	hge->System_SetState(HGE_LOGFILE, "Log.log");
	hge->System_SetState(HGE_FRAMEFUNC, FrameFunc);
	hge->System_SetState(HGE_RENDERFUNC, RenderFunc);
	hge->System_SetState(HGE_TITLE, "Project1");
	hge->System_SetState(HGE_USESOUND, false);
	hge->System_SetState(HGE_WINDOWED, true);
	hge->System_SetState(HGE_SCREENWIDTH, SCREEN_WIDTH);
	hge->System_SetState(HGE_SCREENHEIGHT, SCREEN_HEIGHT);
	hge->System_SetState(HGE_SCREENBPP, 32);

    if(hge->System_Initiate())
    {                         
                              fnt=new hgeFont("font2.fnt");
                              
                              tex=hge->Texture_Load("tile.png");
                              spr=new hgeSprite(tex,0,0,64,64);
                              
                              mouseTex=hge->Texture_Load("cursor.png");
                              mouse=new hgeSprite(mouseTex,0,0,32,32);
                              
                              backgroundTex=hge->Texture_Load("background.png");
                              background=new hgeSprite(backgroundTex,0,0,960,640);
                              
                              pathTex=hge->Texture_Load("pathButton.png");
                              pathSpr=new hgeSprite(pathTex,0,0,128,64);
                              
                              blockTex=hge->Texture_Load("blockButton.png");
                              blockSpr=new hgeSprite(blockTex,0,0,128,64);
                              
                              windowTex=hge->Texture_Load("build_menu.png");
                              windowSpr=new hgeSprite(windowTex,0,0,320,640);
                              
                              mouse_tileTex1=hge->Texture_Load("pathTile.png");
                              mouse_tile1=new hgeSprite(mouse_tileTex1,0,0,64,64);
                              
                              mouse_tileTex2=hge->Texture_Load("blockTile.png");
                              mouse_tile2=new hgeSprite(mouse_tileTex2,0,0,64,64);
                              
                              fnt=new hgeFont("font2.fnt");
                              
                              
                              hge->System_Start();
                              
    }
    
    hge->System_Shutdown();
    hge->Release();
    
    return 0;
}
