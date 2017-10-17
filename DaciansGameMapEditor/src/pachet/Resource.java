package pachet;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Resource {
	Image img;
	float x,y;
	String type;
	int i,j;
	
	public Resource(Image image,String _type,Tile t,int ip,int jp){
		img=image;
		type=_type;
		x=t.x;
		y=t.y;
		i=ip;
		j=jp;
	}
	
	public void render(Graphics g){
		g.drawImage(img, x, y);
	}
	
	public void moveX(float speed){
		x+=speed;
	}
	
	public void moveY(float speed){
		y+=speed;
	}
}
