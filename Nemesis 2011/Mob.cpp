#include "Mob.h"

#define UP 1
#define DOWN 2
#define LEFT 3
#define RIGHT 4

Mob::Mob(float _x,float _y,float _speed)
{
             x=_x;
             y=_y;
             speed=_speed;
             index=1;
             rot=0.0;
             health=100.0;
             visible_health=false;
             checked=false;
             points=10;
             money=30;
}

float Mob::GetRot()
{
      return rot;
}

float Mob::GetX()
{
    return x;
}

float Mob::GetY()
{
    return y;
}

void Mob::SetX(float _x)
{
     x=_x;
}

void Mob::SetY(float _y)
{
     y=_y;
}

void Mob::MoveX(float _x)
{
     x+=_x;
}

void Mob::MoveY(float _y)
{
     y+=_y;
}

void Mob::Move(Point p[],int n,float _dt,int global)
{
     if( ((x==p[index].x) && (y==p[index].y)) )
     {
            if(index<n)
                        index++;
            
            else
            {
                        
            }                        
     }
     else
     {
         if(p[index].dir==UP)
         { y=y-speed*global;//*_dt+0.3;
           rot=-1.57*2.0;
         }
         else
         if(p[index].dir==DOWN)
         { y=y+speed*global;//*_dt+0.3;
           rot=0.0;
         }
         else
         if(p[index].dir==LEFT)
         {
           x=x-speed*global;
           rot=1.57;
         }
         else
         if(p[index].dir==RIGHT)
         { x=x+speed*global;
           rot=-1.57;
         }      
     }  
      
}

void Mob::SetSpeed(float _speed)
{
     speed=_speed;
}

void Mob::SetHealth(float _health)
{
     health=_health;
}

float Mob::GetHealth()
{
      return health;
}

void Mob::ShowHealth(bool _s)
{
     visible_health=_s;
}

bool Mob::IsHealthVisible()
{
     return visible_health;
}

bool Mob::IsAlive()
{
     if(health>0)
                 return true;
     
     return false;
}

void Mob::Check(bool _check)
{
     checked=_check;     
}

bool Mob::IsChecked()
{
     return checked;
}

int Mob::GetPoints()
{
    return points;
}

int Mob::GetMoney()
{
    return money;
}
