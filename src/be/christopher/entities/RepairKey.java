package be.christopher.entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RepairKey {
	
	private double x;
	private double y;
	private BufferedImage skin;
	
	public RepairKey(int x, int y) {
		this.x = x;
		this.y = y;
		
		try {
			skin = ImageIO.read(getClass().getResourceAsStream("/repairkey.png"));
		} catch (IOException e) {
			e.printStackTrace();
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

	public BufferedImage getSkin() {
		return skin;
	}

	public void setSkin(BufferedImage skin) {
		this.skin = skin;
	}
	
}
