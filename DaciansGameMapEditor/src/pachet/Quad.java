package pachet;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Quad {
	int size=1;
	Image img;
	
	public Quad(){
		try {
			img=new Image("quad.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(String what,float x,float y){
		switch (what){
		
		case "goldMine": 
			for(int i=0;i<2;i++)
				for(int j=0;j<2;j++)
					img.draw(x+j*64, y+i*64);
		break;
		
		case "tree":
			for(int i=0;i<8;i++)
				for(int j=0;j<8;j++)
					img.draw(x+j*64, y+i*64);
		
		case "single": 
					img.draw(x, y);
		break;
		
		case "farm":
			for(int i=0;i<2;i++)
				for(int j=0;j<2;j++)
					img.draw(x+j*64, y+i*64);
			break;
		
		default: break;
		}
		
	}
}
