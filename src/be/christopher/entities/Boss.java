package be.christopher.entities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import be.christopher.utils.Utils;

public class Boss {
	private double x;
	private double y;
	private double velX;
	private double velY;
	private int health;
	private BufferedImage skin;
	
	public Boss(int x, int y) {
		this.x = x;
		this.y = y;
		this.velX = Utils.randomVelocity(-1, 1);
		this.velY = Utils.randomVelocity(-1, 1);
		try {
			this.health = Utils.randomInt(6, 12);
			skin = ImageIO.read(getClass().getResourceAsStream("/boss.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		if(x + velX >= 0 && x + velX <= 684)
			x += velX;
		else
			velX = Utils.randomVelocity(-1, 1);
		if(y + velY >= 0 && y + velY <= 353)
			y += velY;
		else
			velY = Utils.randomVelocity(-1, 1);
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public BufferedImage getSkin() {
		return skin;
	}

	public void setSkin(BufferedImage skin) {
		this.skin = skin;
	}
	
}
