class Tile{
      int x,y;
      bool free,visible;
      
      public:
             Tile(int _x,int _y,bool _visible);
             
             int GetX();
             int GetY();
             
             void SetVisible(bool _visible);
             void SetFree(bool _free);
             
             bool IsVisible();
             bool IsFree();
};
