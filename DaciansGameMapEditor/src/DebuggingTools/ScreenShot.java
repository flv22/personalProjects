package DebuggingTools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.imageout.ImageOut;

public class ScreenShot {
	
	private static Image target;
	
	public static void takeSnapshot(){
		GL11.glReadBuffer(GL11.GL_FRONT);
		int width = Display.getDisplayMode().getWidth();
		int height= Display.getDisplayMode().getHeight();
		int bpp = 4; // Assuming a 32-bit display with a byte each for red, green, blue, and alpha.
		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
		GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer );
		
		File file = new File("C:\\Users\\tyger\\workspaces\\DaciansGame\\snapshot.png"); // The file to save to.
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Eroare la screenshot");
			e1.printStackTrace();
		}
		String format = "PNG"; // Example: "PNG" or "JPG"
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		   
		for(int x = 0; x < width; x++)
		{
		    for(int y = 0; y < height; y++)
		    {
		        int i = (x + (width * y)) * bpp;
		        int r = buffer.get(i) & 0xFF;
		        int g = buffer.get(i + 1) & 0xFF;
		        int b = buffer.get(i + 2) & 0xFF;
		        image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
		    }
		}
		   
		try {
		    ImageIO.write(image, format, file);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	 public static void takeScreenShot(GameContainer container, String fileExt) {

	      try {
	         File FileSSDir = new File("/screenshots");
	         if (!FileSSDir.exists()) {
	            FileSSDir.mkdirs();
	            System.out.println(FileSSDir.getAbsolutePath());
	         }else
	        	 System.out.println(FileSSDir.getAbsolutePath());

	         int number = 0;
	         String screenShotFileName = "/screenshots/" + "screenshot_" + number + fileExt;
	         File screenShot = new File(screenShotFileName);

	         while (screenShot.exists()) {
	            number++;
	            screenShotFileName = "/screenshots/" + "screenshot_" + number + fileExt;
	            screenShot = new File(screenShotFileName);
	         }
	         System.out.println("Screenshot outputting to: " + screenShotFileName);

	         if (target == null) {
	            target = new Image(container.getWidth(), container.getHeight());
	         }
	         Graphics g = container.getGraphics();
	         g.copyArea(target, 0, 0);
	         ImageOut.write(target, screenShotFileName) ;

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	
}
