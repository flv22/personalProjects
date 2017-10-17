#include "Button.h"

Button::Button(int _x,int _y)
{
                   x=_x;
                   y=_y;
}

void Button::SetX(int _x)
{
     x=_x;
}

void Button::SetY(int _y)
{
     y=_y;
}

int Button::GetX()
{
    return x;
}

int Button::GetY()
{
    return y;
}

