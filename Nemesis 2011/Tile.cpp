#include "Tile.h"

Tile::Tile(int _x,int _y,bool _visible)
{
               x=_x;
               y=_y;
               free=true;
               visible=_visible;
}

int Tile::GetX()
{
    return x;
}

int Tile::GetY()
{
    return y;
}

bool Tile::IsVisible()
{
     return visible;
}

void Tile::SetVisible(bool _visible)
{
     visible=_visible;
}

void Tile::SetFree(bool _free)
{
     free=_free;
}

bool Tile::IsFree()
{
     return free;
}
