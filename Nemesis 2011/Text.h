#pragma once

class Text{
      
      float x,y;
      bool visible;
      
      public:
             Text(float _x,float _y);
             ~Text();
             
             void MoveX(float _x);
             void MoveY(float _y);
             
             float GetX();
             float GetY();
             
             void Show(bool _visible);
             bool IsVisible(){return visible;}
};
