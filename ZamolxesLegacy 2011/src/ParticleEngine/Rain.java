package ParticleEngine;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Rain {
	ArrayList<RainDrop> rainDrops;
	int seconds;
	
	public Rain(){
		rainDrops=new ArrayList<RainDrop>();
		this.initializeRain(50);
		this.seconds=0;
	}
	
	public void doRain() {
		if (rainDrops.size() < 700) {
			seconds++;
			if (seconds > 110) {
				this.initializeRain(50);
				seconds = 0;
			}
		}

		for (RainDrop item : rainDrops) {
			item.setY(item.getY() + 7.0f);
			item.setX(item.getX() - 1.0f);

			if (item.getY() > 720) {
				item.setX((float) ((Math.random() * 2000) + 30));
				item.setY((float) ((Math.random() * 5)));
			}
		}

	}
	
	public void initializeRain(int numberOfRainDrops){
		for(int i=0;i<numberOfRainDrops;i++){
			RainDrop item=new RainDrop((int) (Math.random() * 10 + 4));
			item.setX((float)((Math.random()*2000)+30));
			item.setY((float)((Math.random()*750)));
			
			rainDrops.add(item);
		}
	}
	
	public void render(Graphics g){
		g.setColor(new Color(188.0f,227.0f,229.0f,0.3f));
		for(RainDrop item: rainDrops)
			g.draw(item);
	}
}
