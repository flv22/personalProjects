#include "Tower.h"
#include <math.h>

class Bullet{
      
      float x,y;
      float rot;
      bool dead;
      int moving;
      
      public:
             Bullet(float _x,float _y);
             
             void SetX(float _x){x=_x;}
             void SetY(float _y){y=_y;}
             void SetRot(float _r){rot=_r;}
             
             float GetX(){return x;}
             float GetY(){return y;}
             float GetRot(){return rot;}
             
             void SetDead(bool _d){dead=_d;}
             bool IsDead(){return dead;}
             
             void GetInitialCoordinates(Tower* t);
             int Collision(Mob* mob){if(x>=mob->GetX()+10 && x<=mob->GetX()+56 && y>=mob->GetY()+10 && y<=mob->GetY()+56) return 1; return 0;}
                                     
             int Outside(int width,int height){if(x<0 || x>width || y<0 || y>height)return 1; return 0;}
             
             void Start(int _s){moving=_s;}
             int IsMoving(){return moving;}
             void Move(float _dt,float _speed,int GlobalSpeed){ 
                                                x=x-(cos(rot)*_dt*_speed)*GlobalSpeed;
                                                y=y-(sin(rot)*_dt*_speed)*GlobalSpeed;
                                                //bullet->SetX(bullet->GetX()-cos(bullet->GetRot())*dt*450);//bullet->Move();
                                                //bullet->SetY(bullet->GetY()-sin(bullet->GetRot())*dt*450);//
                                              }
};
