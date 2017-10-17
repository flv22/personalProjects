#include "Text.h"

Text::Text(float _x,float _y)
{
                 x=_x;
                 y=_y;
}

void Text::MoveX(float _x)
{
     x+=_x;
}

void Text::MoveY(float _y)
{
     y+=_y;
}

float Text::GetX()
{
      return x;
}

float Text::GetY()
{
      return y;
}

void Text::Show(bool _visible)
{
     visible=_visible;
}
