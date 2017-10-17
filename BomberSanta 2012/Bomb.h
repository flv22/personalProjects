class Bomb{
      
      float x,y;
      float _dt;
      bool start;
      bool blocked;
      bool exploded;
      
      public:
             Bomb(){_dt=0.0;start=false;blocked=false;exploded=false;}
             
             void StartClock(){start=true;}
             void PlantBomb(float _x,float _y){x=_x;y=_y;blocked=true;}
             int Update(float _dt_){_dt+=_dt_; if(_dt>=1.5){_dt=0.0; return 1;} return 0;}
             float GetDT(){return _dt;}
             int Boom(){blocked=false;x=900;y=800;exploded=true;return 1;}
             void SetExploded(bool _exploded){exploded=_exploded;}
                  
             void SetBlocked(bool _bl){blocked=_bl;}
             bool IsBlocked(){return blocked;}
             bool Exploded(){return exploded;}
                  
             void SetX(float _x){x=_x;}
             void SetY(float _y){y=_y;}
             float GetX(){return x;}
             float GetY(){return y;}
             
      };
