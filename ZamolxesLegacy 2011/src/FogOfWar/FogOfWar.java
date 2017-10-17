package FogOfWar;

import org.newdawn.slick.Graphics;

public class FogOfWar {
	int sizeW,sizeH;
	TileFog fog[][];
	
	public FogOfWar(int sizeW,int sizeH){
		this.sizeW=sizeW;
		this.sizeH=sizeH;
		
		fog=new TileFog[sizeH][sizeW];
		
		for(int i=0;i<sizeH;i++)
			for(int j=0;j<sizeW;j++){
				fog[i][j]=new TileFog();
				fog[i][j].setBits(1, 1, 1, 1);
				fog[i][j].setX(j*64);
				fog[i][j].setY(i*64);
			}
		
		this.interpretBits();
	}
	
	public void interpretBits(){
		for(int i=0;i<sizeH;i++)
			for(int j=0;j<sizeW;j++)
				fog[i][j].interpretBits();
	}
	
	public TileFog getTile(int i,int j){
		return fog[i][j];
	}
	
	public void moveX(float speed){
		for(int i=0;i<sizeH;i++)
			for(int j=0;j<sizeW;j++)
				fog[i][j].setX(fog[i][j].getX()+speed);
	}
	
	public void moveY(float speed){
		for(int i=0;i<sizeH;i++)
			for(int j=0;j<sizeW;j++)
				fog[i][j].setY(fog[i][j].getY()+speed);
	}
	
	public void update(){
		
	}
	
	public void render(Graphics g){
		for(int i=0;i<sizeH;i++){
			for(int j=0;j<sizeW;j++)
				if(fog[i][j].getSprite()!=null)
					g.drawImage(fog[i][j].getSprite(), fog[i][j].getX(), fog[i][j].getY());
		}
	}

	public TileFog[][] getFog() {
		return fog;
	}

	public void setFog(TileFog[][] fog) {
		this.fog = fog;
	}
	
}
