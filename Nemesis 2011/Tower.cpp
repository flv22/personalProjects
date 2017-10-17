#include "Tower.h"
#include <math.h>

Tower::Tower(int _x,int _y,int _type,int _range)
{
                 x=_x;
                 y=_y;
                 type=_type;
                 if(type==1)
                 {
                            cost=100;
                            damage=10;
                 }
                 else
                 if(type==2)
                 {
                            damage=20;
                            cost=200;
                 }
                            
                 shot=0;
                 target=0;
                 range=_range;
}

int Tower::GetX()
{
    return x;
}

int Tower::GetY()
{
    return y;
}

int Tower::GetType()
{
    return type;
}

float Tower::GetDistance(Mob *m) //rad din x2-x1 la patrat + y2-y1 la patrat
{   
    return sqrt(pow((x-m->GetX()),2)+pow((y-m->GetY()),2));
}

void Tower::SetRot(float _rotation)
{
      rotation=_rotation;
}

float Tower::GetRot()
{
      return rotation;
}

int Tower::GetCost()
{
    return cost;
}
