#include "Bullet.h"

Bullet::Bullet(float _x,float _y)
{
                     x=_x;
                     y=_y;
                     rot=0.0;
                     dead=true;
                     moving=0;
}

void Bullet::GetInitialCoordinates(Tower* t)
{
     x=t->GetX();
     y=t->GetY();
     rot=t->GetRot();
}
