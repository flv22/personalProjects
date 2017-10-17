#include "Menu.h"

Menu::Menu(int _x,int _y)
{
               x=_x;
               y=_y;
               visible=false;
}

void Menu::AddWindow(Window* _wind)
{
     window=_wind;
}

void Menu::AddButtons(Button* _btn,Button* _btn2)
{
     btn1=_btn;
     btn2=_btn2;
}

void Menu::Show(hgeSprite* _window,hgeSprite* _button,hgeSprite* _button2)
{
     _window->Render(window->GetX(),window->GetY());
     _button->Render(btn1->GetX(),btn1->GetY());
     _button2->Render(btn2->GetX(),btn2->GetY());
}

void Menu::SetVisible(bool _visible)
{
     visible=_visible;
}

bool Menu::IsVisible()
{
     return visible;
}
