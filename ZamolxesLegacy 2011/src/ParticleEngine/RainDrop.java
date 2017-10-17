package ParticleEngine;

import org.newdawn.slick.geom.Polygon;

public class RainDrop extends Polygon{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6078064955716869721L;

	public RainDrop(int size){
		super();
		this.addPoint(0, 0);
		this.addPoint(0, size);
	}

}
