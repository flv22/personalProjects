#include "Window.h"

Window::Window(int _x,int _y)
{
                   x=_x;
                   y=_y;
}

void Window::SetX(int _x)
{
     x=_x;
}

void Window::SetY(int _y)
{
     y=_y;
}

int Window::GetX()
{
    return x;
}

int Window::GetY()
{
    return y;
}
