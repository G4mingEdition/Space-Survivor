package be.christopher.entities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Clock {
	
	private double x;
	private double y;
	private int ticks;
	private boolean isEnabled;
	private BufferedImage skin;
	
	public Clock(int x, int y) {
		this.x = x;
		this.y = y;
		this.ticks = 1250;
		this.isEnabled = false;
		
		try {
			skin = ImageIO.read(getClass().getResourceAsStream("/clock.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ticks() {
		if(ticks - 1 >= 0) {
			ticks--;
		}
		if(ticks < 1) {
			this.setIsEnabled(false);
		}
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public int getTicks() {
		return ticks;
	}

	public void setTicks(int ticks) {
		this.ticks = ticks;
	}
	

	public boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public BufferedImage getSkin() {
		return skin;
	}

	public void setSkin(BufferedImage skin) {
		this.skin = skin;
	}
	
}
