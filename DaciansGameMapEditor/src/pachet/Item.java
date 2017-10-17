package pachet;
import org.newdawn.slick.Image;


public class Item {
	float x,y;
	Image img;
	int imageIndex;
	
	public Item(Image img,float x,float y){
		this.x=x;
		this.y=y;
		this.img=img;
		//System.out.println(img.getName());
	}
}
