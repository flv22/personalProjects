#pragma once

#include "Mob.h"

class Tower{
      int x,y;
      int type,range;
      float rotation;
      int cost;
      int shot;
      int target;
      int damage;
      
      public:
             
             Tower(int _x,int _y,int _type,int _range);
             
             void SetRot(float _rotation);
             
             int GetX();
             int GetY();
             int GetType();
             int GetCost();
             float GetRot();
             
             float GetDistance(Mob *m);
             
             void Shot(int _shot){shot=_shot;}
             int Shoot(){return shot;}
             
             void SetTarget(int _targ){target=_targ;}
             int GetTarget(){return target;}
             
             int GetRange(){return range;}
             int GetDamage(){return damage;}
};
