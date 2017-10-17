#pragma once

typedef struct
{
        int x,y;
        int dir;
}Point;

class Mob
{
      float x,y,speed,rot;
      int index;
      float health;
      bool visible_health;
      bool checked;
      int points,money;
      
      public:
             Mob(float _x,float _y,float _speed);
             
             int GetPoints();
             int GetMoney();
             float GetX();
             float GetY();
             float GetRot();
             float GetHealth();
             bool IsHealthVisible();
             bool IsAlive();
             bool IsChecked();
             
             void MoveX(float _x);
             void MoveY(float _y);
             void Check(bool _check);
             
             void SetX(float _x);
             void SetY(float _y);
             void SetSpeed(float _speed);
             void SetHealth(float _health);
             
             void ShowHealth(bool _s);
             
             void Move(Point p[],int n,float _dt,int global);
};
