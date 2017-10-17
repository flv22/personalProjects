#include <stdio.h>
#include <fstream>
#include <math.h>
#include <hgeresource.h>
#include "hge.h"
#include "hgesprite.h"
#include "hgegui.h"
#include "hgefont.h"

#include "Menu.h"
#include "Tile.h"
#include "Types.h"
#include "Tower.h"
#include "Mob.h"
#include "Bullet.h"
#include "Text.h"

#define SCREEN_WIDTH  1280
#define SCREEN_HEIGHT 640

#define MAX_TILES 25
#define WTILES 15
#define HTILES 10
#define MAX_TOWERS 255
#define MAX_MOBS 255
#define NO_MOBS 20

using namespace std;

HGE *hge=0;
hgeResourceManager* myRes;
HCHANNEL chan;
HSTREAM myMusic;

/////////////Tiles
hgeSprite *spr;
HTEXTURE tex;
Tile* t[MAX_TILES][MAX_TILES];
///////////////////////////////

////////////////Font
hgeFont* fnt=0;
hgeFont* smallfnt=0;
////////////////////

////////MOUSE
hgeGUI *gui;
hgeSprite* mouse;
HTEXTURE mouseTex;
float mx,my;
//////////////////

/////////////////Backgroundul
hgeSprite* background;
HTEXTURE backgroundTex;
/////////////////////////////

////////////////////Menu
Menu *menu;
Window* _window;
Button* _play;
Button* _quit;

hgeSprite* window;
HTEXTURE windowTex;
hgeSprite* play;
HTEXTURE playTex;
hgeSprite* quit;
HTEXTURE quitTex;
////////////////////////

/////////////////////Build menu
Menu *buildmenu;
Window* windowbuild;
Button* turret;
Button* turret2;

hgeSprite* windowbuildspr;
HTEXTURE windowbTex;
hgeSprite* turretspr;
HTEXTURE turretTex;
hgeSprite* turret2spr;
HTEXTURE turret2Tex;

typedef struct
{
        int cost;
        int damage;
        int radius;
}Turret_button;
Turret_button turret_button[3];
///////////////////////////////

///////////////////////////////Auxiliare: camp liber(albastru),variabile pt coordonatele mouse-ului + flag-ul buildmode
hgeSprite *test;
HTEXTURE testTex;
float tx,ty;
float lx=0,ly=0; //modificat din float
int buildMode=0;
int GlobalSpeed=1;
///////////////////////////////

///////////////////////////////Turn
int type;

hgeSprite* towerspr;
HTEXTURE towerTex;
hgeSprite* towerspr_base;
HTEXTURE towerTex_base;

hgeSprite* towerspr2;
HTEXTURE towerTex2;
hgeSprite* towerspr2_base;
HTEXTURE towerTex2_base;

Tower* tower[MAX_TOWERS];
int towerCount=0;
///////////////////////////////////


//////////////////////////////////Mob
Mob* mob[MAX_MOBS];
hgeSprite* mobspr;
HTEXTURE mobTex;
hgeSprite* health_bar_spr;
HTEXTURE health_bar_tex;
/////////////////////////////////////

/////////////////////////////////Points for path
Point point[255];
int index=1;
int noPoints;
////////////////////////////////////////////////


/////////////////Stats
int GlobalScale=1;
int Money=400;
int Points=0;
int Life=100;
float health_scale;
float counter[255];
float mob_counter_start;
int gameStarted;
///////////////////////////

///////////////////Bullet
Bullet* bullet[255];
hgeSprite* bulletSpr;
HTEXTURE bulletTex;
////////////////////////

///////////////////////Text
Text *txt[2];
hgeSprite* textSpr;
HTEXTURE textTex;
///////////////////////////

void Init()
{        
                   
     int _x=0,_y=0;
     for(int i=0;i<HTILES;i++)
     {
             for(int j=0;j<WTILES;j++)
             {
                      t[i][j]=new Tile(_x,_y,false);
                      _x+=64;
             }
             
             _x=0;
             _y+=64;
     }        
     
     _window=new Window(490,150);
     _play=new Button(_window->GetX()+28,_window->GetY()+60);
     _quit=new Button(_window->GetX()+28,_window->GetY()+217);
     menu=new Menu(0,0);
     menu->AddWindow(_window);
     menu->AddButtons(_play,_quit);
     
     windowbuild=new Window(960,0);
     turret=new Button(1000,10);
     turret2=new Button(1000,80);
     buildmenu=new Menu(960,0);
     buildmenu->AddWindow(windowbuild);
     buildmenu->AddButtons(turret,turret2);
     turret_button[1].cost=100; turret_button[1].damage=10; turret_button[1].radius=100;
     turret_button[2].cost=200; turret_button[2].damage=20; turret_button[2].radius=100;
     
     ifstream in("stats.txt");
     float _s;
     in>>_s;
     in.close();
     
     float y=-128.0;
     for(int i=1;i<=NO_MOBS;i++)
     {                    
             mob[i]=new Mob(0.0,y,_s);//0.0625);
                        y-=128;
     }
     
     //citirea path-ului din fisier (a,b,c = x,y,rotatia) in vectorul de structuri Point
     int a,b,c;
     ifstream fin("path.txt");
     fin>>noPoints;
     for(int i=1;i<=noPoints;i++)
             {
                                 fin>>a>>b>>c;
                                 t[a][b]->SetFree(false);
                                 point[i].x=t[a][b]->GetX();
                                 point[i].y=t[a][b]->GetY();
                                 point[i].dir=c;
             }
     noPoints++;
     point[noPoints].x=-64;
     point[noPoints].y=point[noPoints-1].y;
     point[noPoints].dir=c;
     
     int noBlockeds=0;
     fin>>noBlockeds;
     for(int i=1;i<=noBlockeds;i++)
             {
                                 fin>>a>>b;
                                 t[a][b]->SetFree(false);
             }
     fin.close();
     
     for(int i=1;i<=100;i++)
             counter[i]=0.0;
     
     mob_counter_start=0.0;
     gameStarted=1;
     
     txt[0]=new Text(-641.0,244.0);
     txt[1]=new Text(1280.0,244.0);
}

bool FrameFunc()
{    	
     hge->Input_GetMousePos(&mx,&my);
     float dt=hge->Timer_GetDelta();
     
     for(int i=1;i<=towerCount;i++)
             counter[i]+=dt;
     
     if(gameStarted==1)
                       if(GlobalSpeed==1)
                                         mob_counter_start+=dt;
         
     if(hge->Input_GetKeyState(HGEK_ESCAPE))
     {                                      
                                            GlobalSpeed=0;
                                            menu->SetVisible(true);   
     }   
                                         
     if(hge->Input_GetKeyState(HGEK_LBUTTON))
     {                                       
                                             
                                             if(!buildMode) //daca nu e activat buildmode
                                             {              GlobalSpeed=1;
                                                            if(mx>turret->GetX() && mx<turret->GetX()+64 && my>turret->GetY() && my<turret->GetY()+64 ) 
                                                            {                                            
                                                                                                         if(Money>=100)
                                                                                                         {
                                                                                                           type=1;
                                                                                                           buildMode=1;
                                                                                                           for(int i=0;i<HTILES;i++)
                                                                                                           {
                                                                                                                   for(int j=0;j<WTILES;j++)
                                                                                                                   t[i][j]->SetVisible(true);
                                                                                                           }
                                                                                                           
                                                                                                         }else{}
                                     
                                                            }
                                                            else{}
                                                            if(mx>turret2->GetX() && mx<turret2->GetX()+64 && my>turret2->GetY() && my<turret2->GetY()+64 ) 
                                                            {                                              
                                                                                                           if(Money>=200)
                                                                                                           {
                                                                                                            type=2;
                                                                                                            buildMode=1;
                                                                                                            for(int i=0;i<HTILES;i++)
                                                                                                            {
                                                                                                                   for(int j=0;j<WTILES;j++)
                                                                                                                   t[i][j]->SetVisible(true);
                                                                                                            }
                                                                                                           }
                                     
                                                            }
                                                            else{}
                                             }
                                             else //daca este activat buildmode
                                             {    //se verifica daca mouseul este deasupra unui tile; daca este,buildmode-ul se inchide si se creeaza un turn la coordonatele tile-ului
                                                 GlobalSpeed=0;
                                                 for(int i=0;i<HTILES;i++)
                                                 {
                                                           for(int j=0;j<WTILES;j++)
                                                                  if(mx>t[i][j]->GetX() && mx<t[i][j]->GetX()+64 && my>t[i][j]->GetY() && my<t[i][j]->GetY()+64)
                                                                   {
                                                                                         buildMode=0;
                                                                                         lx=t[i][j]->GetX();
                                                                                         ly=t[i][j]->GetY();
                                                                                         
                                                                                         if(t[i][j]->IsFree())
                                                                                                              if(towerCount<=MAX_TOWERS)
                                                                                                              {                        
                                                                                                                  t[i][j]->SetFree(false);
                                                                                                                  towerCount+=1;
                                                                                                                  tower[towerCount]=new Tower(lx,ly,type,100);
                                                                                                                  tower[towerCount]->SetRot(0.0);
                                                                                                                  bullet[towerCount]=new Bullet(lx,ly);
                                                                                                                  Money-=tower[towerCount]->GetCost();
                                                                                                                   
                                                                                                              }
                                                                                                              else
                                                                                                              {
                                                                                                                   towerCount+=0;
                                                                                                              }
                                                                                         
                                                                                         for(int i=0;i<HTILES;i++)
                                                                                         {
                                                                                                 for(int j=0;j<WTILES;j++)
                                                                                                          t[i][j]->SetVisible(false);
                                                                                         }
                                                                                                            
                                                                   }
                                                 }
                                             }
                                             
                                             
                                             if(menu->IsVisible())
                                             {                    
                                                                  
                                                                  if(mx>_play->GetX() && mx<_play->GetX()+300 && my>_play->GetY() && my<_play->GetY()+144)
                                                                  {                                           
                                                                                                          menu->SetVisible(false);
                                                                                                          GlobalSpeed=1;
                                                                  }
                                                                  
                                                                  if(mx>_quit->GetX() && mx<_quit->GetX()+300 && my>_quit->GetY() && my<_quit->GetY()+150)
                                                                                      return true;
                                             }
     }              
     
                                      
                                      

     
     for(int i=1;i<=towerCount;i++)
     {
                     if( tower[i]->GetTarget()==0)
                     {   
                         for(int j=1;j<=NO_MOBS;j++)
                                 if( (tower[i]->GetDistance(mob[j]))>=0 && (tower[i]->GetDistance(mob[j]))<=tower[i]->GetRange() )
                                 {   
                                     if(mob[j]->IsAlive()==true)
                                         tower[i]->SetTarget(j);        
                                 }
                     }
                     else
                         continue;                                                          
     }                
     
     for(int i=1;i<=towerCount;i++)
             if(tower[i]->GetTarget()!=0)
                 if(tower[i]->GetDistance(mob[tower[i]->GetTarget()])>=tower[i]->GetRange() || mob[tower[i]->GetTarget()]->IsAlive()==false)
                 {   
                       tower[i]->SetTarget(0); 
                       bullet[i]->SetDead(true);
                 }
                                      
     for(int i=1;i<=towerCount;i++)
     {        if(tower[i]->GetTarget()!=0)
              {
                   tower[i]->SetRot(atan2( tower[i]->GetY()-mob[tower[i]->GetTarget()]->GetY(),tower[i]->GetX()-mob[tower[i]->GetTarget()]->GetX()));   //-10 la ambele
                   
                   if(bullet[i]->IsDead()==true)
                   {                            
                                                if(counter[i]>=1.0)
                                                {
                                                                   bullet[i]->SetDead(false);
                                                                   bullet[i]->GetInitialCoordinates(tower[i]);
                                                                   bullet[i]->SetRot( atan2( bullet[i]->GetY()-mob[tower[i]->GetTarget()]->GetY(),bullet[i]->GetX()-mob[tower[i]->GetTarget()]->GetX()) );
                                                                   bullet[i]->Start(1); 
                                                                   counter[i]=0.0;
                                                }                                                        
                   }
                   
                   if(bullet[i]->IsMoving())
                                            bullet[i]->Move(dt,550.0,GlobalSpeed);
                                            
                   //if((bullet[i]->Outside(1280,640)) || ( (bullet[i]->GetX()+32 >= mob[tower[i]->GetTarget()]->GetX()) && ( bullet[i]->GetX()+32 <= mob[tower[i]->GetTarget()]->GetX()+64 ) && ( bullet[i]->GetY()+32 >= mob[tower[i]->GetTarget()]->GetY() ) && ( bullet[i]->GetY()+32 <= mob[tower[i]->GetTarget()]->GetY()+64) ))
                                                  // bullet[i]->SetDead(true);
                                                  
                   if(bullet[i]->Outside(1280,640))
                                                   bullet[i]->SetDead(true);
                                                   
                   if( (bullet[i]->GetX()+32 >= mob[tower[i]->GetTarget()]->GetX()) && ( bullet[i]->GetX()+32 <= mob[tower[i]->GetTarget()]->GetX()+64 ) && ( bullet[i]->GetY()+32 >= mob[tower[i]->GetTarget()]->GetY() ) && ( bullet[i]->GetY()+32 <= mob[tower[i]->GetTarget()]->GetY()+64))
                   {                                                  
                                                                      if(bullet[i]->IsDead()==false)
                                                                      {                             
                                                                                                    int mb=tower[i]->GetTarget();
                                                                                                    
                                                                                                    if(mob[mb]->GetHealth()>0)
                                                                                                                              mob[mb]->SetHealth(mob[mb]->GetHealth()-tower[i]->GetDamage());
                                                                                                                              
                                                                                                    bullet[i]->SetDead(true);
                                                                      }              
                                                                                                                       
                                                                                                                       
                   }
              }                                                          
     }                                 
     
       
                                                                                          
    
     
     for(int i=1;i<=NO_MOBS;i++)
     {
             if(hge->Input_GetKeyState(HGEK_LBUTTON)) //daca se da click pe un mob,se afiseaza healthbar-ul aferent mobului
                                                     if(mx>mob[i]->GetX() && mx<mob[i]->GetX()+46 && my>mob[i]->GetY() && my<mob[i]->GetY()+46)
                                                                          mob[i]->ShowHealth(true);
                                                     else
                                                                          mob[i]->ShowHealth(false);
                                                                          
             if(gameStarted==0)
                               if(mob[i]->IsAlive())
                                                    mob[i]->Move(point,noPoints,dt,GlobalSpeed);
                               else
                               {
                                   if(mob[i]->IsChecked()==false)
                                   {                             
                                                                 Points+=mob[i]->GetPoints();
                                                                 Money+=mob[i]->GetMoney();
                                                                 
                                                                  mob[i]->Check(true);
                                   }
                               }
             
             if(mob[i]->GetX()==960)                  
             {          
                       
                                                              Life-=10;
                        //mob[i]->SetHealth(0.0);
             }             
     }     
     
     if(Life<0)
               return true;
     
     if(mob_counter_start>=2.0)
     {
                               gameStarted=0; 
                               txt[0]->Show(true);
                               txt[1]->Show(true);  
     }    
     
     if(mob_counter_start>=-1.0 && gameStarted==0)
     {                          
                                if(GlobalSpeed==1)
                                                  mob_counter_start-=dt;
                                                                                                                                                                        
     }
     else
     {
                                                   txt[0]->Show(false);
                                                   txt[1]->Show(false);    
     }
                                                          
     return false;
}

bool RenderFunc()
{
     hge->Gfx_BeginScene();
     
     background->RenderEx(0.0,0.0,0.0,GlobalScale,GlobalScale);
    
     
     for(int i=0;i<HTILES;i++)
     {
             for(int j=0;j<WTILES;j++)
                     if(t[i][j]->IsVisible())
                                             if(buildMode)
                                             {
                                                          spr->RenderEx(t[i][j]->GetX(),t[i][j]->GetY(),0.0,GlobalScale,GlobalScale);
                                                                       
                                                          if(t[i][j]->IsFree())
                                                                               test->RenderEx(t[i][j]->GetX(),t[i][j]->GetY(),0.0,GlobalScale,GlobalScale);
                                             }
                                             else
                                             {                                                                               
                                                                               spr->RenderEx(t[i][j]->GetX(),t[i][j]->GetY(),0.0,GlobalScale,GlobalScale);
                                             }
     }
     
     
     for(int i=1;i<=towerCount;i++)
     {       
            if(tower[i]->GetType()==1)
            {                         
                                      towerspr_base->RenderEx(tower[i]->GetX(),tower[i]->GetY(),0.0,GlobalScale,GlobalScale);
                                      towerspr->RenderEx(tower[i]->GetX()+32,tower[i]->GetY()+32,tower[i]->GetRot(),GlobalScale,GlobalScale);                                      
            }                                 
            else
            {
                                       towerspr2_base->RenderEx(tower[i]->GetX(),tower[i]->GetY(),0.0,GlobalScale,GlobalScale);
                                       towerspr2->RenderEx(tower[i]->GetX()+32,tower[i]->GetY()+32,tower[i]->GetRot(),GlobalScale,GlobalScale);
            }                                                                          
     }
     
     for(int i=1;i<=towerCount;i++)
             if(bullet[i]->IsDead()==false)
             {
                                        bulletSpr->RenderEx(bullet[i]->GetX()+32,bullet[i]->GetY()+32,0.0);                                       
                                                                       
                                        //hge->Gfx_RenderLine(bullet[i]->GetX()+32,bullet[i]->GetY()+32,bullet[i]->GetX()+39,bullet[i]->GetY()+32); //+10,+10,+56,+10
                                        //hge->Gfx_RenderLine(bullet[i]->GetX()+39,bullet[i]->GetY()+32,bullet[i]->GetX()+39,bullet[i]->GetY()+39); //+56,+10,+56,+56
                                        //hge->Gfx_RenderLine(bullet[i]->GetX()+39,bullet[i]->GetY()+39,bullet[i]->GetX()+32,bullet[i]->GetY()+39); //+56,+56,+10,+56
                                        //hge->Gfx_RenderLine(bullet[i]->GetX()+32,bullet[i]->GetY()+39,bullet[i]->GetX()+32,bullet[i]->GetY()+32);
            }
     
     for(int i=1;i<=NO_MOBS;i++)
     {        
              if(mob[i]->IsAlive())
                                   mobspr->RenderEx(mob[i]->GetX()+32,mob[i]->GetY()+32,mob[i]->GetRot(),GlobalScale,GlobalScale);
              
              //hge->Gfx_RenderLine(mob[i]->GetX(),mob[i]->GetY(),mob[i]->GetX()+64,mob[i]->GetY()); //+10,+10,+56,+10
              //hge->Gfx_RenderLine(mob[i]->GetX()+64,mob[i]->GetY(),mob[i]->GetX()+64,mob[i]->GetY()+64); //+56,+10,+56,+56
              //hge->Gfx_RenderLine(mob[i]->GetX()+64,mob[i]->GetY()+64,mob[i]->GetX(),mob[i]->GetY()+64); //+56,+56,+10,+56
              //hge->Gfx_RenderLine(mob[i]->GetX(),mob[i]->GetY()+64,mob[i]->GetX(),mob[i]->GetY()); //+10,+56,+10,+10
              
              if(mob[i]->IsHealthVisible())
              {
                                           health_scale=mob[i]->GetHealth()/100;
                                           
                                           if(mob[i]->IsAlive())
                                                                health_bar_spr->RenderEx(mob[i]->GetX()+10,mob[i]->GetY()+5,0.0,health_scale,1.0);
              }
     }
     
     
      
     
     
                          
      buildmenu->Show(windowbuildspr,turretspr,turret2spr);
      
     //if(towerCount>0)
                     //for(int i=1;i<=towerCount;i++)
                             //if(tower[i]->GetTarget()!=0)
                                                 //hge->Gfx_RenderLine(tower[i]->GetX()+32,tower[i]->GetY()+32,mob[tower[i]->GetTarget()]->GetX()+32,mob[tower[i]->GetTarget()]->GetY()+32); 
     
     fnt->printf(980,368,HGETEXT_LEFT,"Points: %d",Points);
     fnt->printf(980,410,HGETEXT_LEFT,"Money: %d",Money);
     fnt->printf(980,452,HGETEXT_LEFT,"Life: %d",Life);
     smallfnt->printf(1064,22,HGETEXT_LEFT,"Cost: %d",turret_button[1].cost);
     smallfnt->printf(1064,34,HGETEXT_LEFT,"Damage: %d",turret_button[1].damage);
     smallfnt->printf(1064,46,HGETEXT_LEFT,"Range: %d",turret_button[1].radius);
     smallfnt->printf(1064,92,HGETEXT_LEFT,"Cost: %d",turret_button[2].cost);
     smallfnt->printf(1064,104,HGETEXT_LEFT,"Damage: %d",turret_button[2].damage);
     smallfnt->printf(1064,116,HGETEXT_LEFT,"Range: %d",turret_button[2].radius);
     
     if( txt[0]->IsVisible() && txt[1]->IsVisible() )
     {   
         if(txt[0]->GetX()!=320)
                                if(GlobalSpeed==1)
                                                  txt[0]->MoveX(1.0);
         if(txt[1]->GetX()!=320)
                                if(GlobalSpeed==1)
                                                  txt[1]->MoveX(-1.0);
                                                      
                                    
                                    textSpr->RenderEx(txt[0]->GetX(),txt[0]->GetY(),0.0);
                                    textSpr->RenderEx(txt[1]->GetX(),txt[1]->GetY(),0.0);
         
     }
     
     if(menu->IsVisible())
                          menu->Show(window,play,quit);
         
     mouse->Render(mx,my);
     
     
     
     /*for(int i=1;i<=towerCount;i++)
             for(int j=1;j<=NO_MOBS;j++)
                     if(bullet[1]->Collision(mob[j]))
                     {
                                                     GlobalSpeed=0;                               
                                                     fnt->printf(0,0,HGETEXT_LEFT,"Coliziune!");
                                                     fnt->printf(0,32,HGETEXT_LEFT,"%d",j);
                                                      
                                                     
                                                     float x1=bullet[1]->GetX();
                                                     float y1=bullet[1]->GetY();
                                                     hge->Gfx_RenderLine(x1,y1,x1+32,y1);
                     }
     */
     
     
     
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
	hge->System_SetState(HGE_TITLE, "Nemesis");
	hge->System_SetState(HGE_USESOUND, true);
	hge->System_SetState(HGE_WINDOWED, true);
	hge->System_SetState(HGE_SCREENWIDTH, 1280);
	hge->System_SetState(HGE_SCREENHEIGHT, 640);
	hge->System_SetState(HGE_SCREENBPP, 32);
    
    
    if(hge->System_Initiate())
    {                         
                              myRes = new hgeResourceManager("sounds/audio.res");
                              
                              tex=hge->Texture_Load("graphics/1280x640/tile.png");
                              spr=new hgeSprite(tex,0,0,64,64);
                              
                              mouseTex=hge->Texture_Load("graphics/1280x640/cursor.png");
                              mouse=new hgeSprite(mouseTex,0,0,32,32);
                              
                              backgroundTex=hge->Texture_Load("graphics/1280x640/background.png");
                              background=new hgeSprite(backgroundTex,0,0,960,640);
                              
                              windowTex=hge->Texture_Load("graphics/1280x640/window.png");
                              window=new hgeSprite(windowTex,0,0,300,400);
                              
                              playTex=hge->Texture_Load("graphics/1280x640/play_button.png");
                              play=new hgeSprite(playTex,0,0,243,92);
                              
                              quitTex=hge->Texture_Load("graphics/1280x640/Quit_button.png");
                              quit=new hgeSprite(quitTex,0,0,243,92);
                              
                              windowbTex=hge->Texture_Load("graphics/1280x640/build_menu.png");
                              windowbuildspr=new hgeSprite(windowbTex,0,0,320,640);
                              
                              turretTex=hge->Texture_Load("graphics/1280x640/turret.png");
                              turretspr=new hgeSprite(turretTex,0,0,64,64);
                              
                              turret2Tex=hge->Texture_Load("graphics/1280x640/turret2.png");
                              turret2spr=new hgeSprite(turret2Tex,0,0,64,64);
                              
                              fnt=new hgeFont("font3.fnt");
                              smallfnt=new hgeFont("font4.fnt");
                              
                              testTex=hge->Texture_Load("graphics/1280x640/free.png");
                              test=new hgeSprite(testTex,0,0,64,64);
                              
                              towerTex=hge->Texture_Load("graphics/1280x640/tower.png");
                              towerspr=new hgeSprite(towerTex,0,0,64,64);
                              towerspr->SetHotSpot(32,32);
                              towerTex_base=hge->Texture_Load("graphics/1280x640/tower_base.png");
                              towerspr_base=new hgeSprite(towerTex_base,0,0,64,64);
                              
                              towerTex2=hge->Texture_Load("graphics/1280x640/tower2.png");
                              towerspr2=new hgeSprite(towerTex2,0,0,64,64);
                              towerspr2->SetHotSpot(32,32);
                              towerTex2_base=hge->Texture_Load("graphics/1280x640/tower2_base.png");
                              towerspr2_base=new hgeSprite(towerTex2_base,0,0,64,64);
                              
                              mobTex=hge->Texture_Load("graphics/1280x640/3.png");
                              mobspr=new hgeSprite(mobTex,0,0,46,46);
                              mobspr->SetHotSpot(23,23);
                              
                              health_bar_tex=hge->Texture_Load("graphics/1280x640/health_bar.png");
                              health_bar_spr=new hgeSprite(health_bar_tex,0,0,46,5);
                              
                              bulletTex=hge->Texture_Load("graphics/1280x640/bullet.png");
                              bulletSpr=new hgeSprite(bulletTex,0,0,7,7);
                              
                              textTex=hge->Texture_Load("graphics/1280x640/mobs_coming.png");
                              textSpr=new hgeSprite(textTex,0,0,641,264);
                              
                              myMusic = myRes->GetStream("theme");
                              chan = hge->Stream_Play(myMusic, true);
                                        
                              hge->System_Start();
                              
    }
    
    hge->System_Shutdown();
    hge->Release();
    
    return 0;
}
