#include "hge.h"
#include "hgesprite.h"
#include "Button.h"
#include "Window.h"


class Menu
{
      int x,y;
      
      Button* btn1;
      Button* btn2;
      Window* window;
      
      bool visible;
      
      public:
             Menu(int _x,int _y);
             
             void AddWindow(Window* _wind);
             void AddButtons(Button* _btn,Button* _btn2);
             
             void SetVisible(bool _visible);
             bool IsVisible();
             
             void Show(hgeSprite* _window,hgeSprite* _button,hgeSprite* _button2);
};
