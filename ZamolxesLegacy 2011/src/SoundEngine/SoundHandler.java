package SoundEngine;

import org.newdawn.slick.Sound;

public class SoundHandler {
	
	Sound menuSound;
	Sound inGameSound;
	
	public SoundHandler(){
		
	}
	
	public void setMenuSound(Sound sound){
		this.menuSound=sound;
	}
	
	public void setInGameSound(Sound inGameSound){
		this.inGameSound=inGameSound;
	}
	
	public void playMenuSound(){
		menuSound.loop();
	}
	
	public void playInGameSound(){
		inGameSound.loop();
	}
	
	public void stopMenuSound(){
		menuSound.stop();
	}
}
