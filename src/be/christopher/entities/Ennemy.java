package be.christopher.entities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import be.christopher.utils.Utils;

public class Ennemy {

	private double x;
	private double y;
	private double velX;
	private double velY;
	private int health;
	private BufferedImage skin;
	
	public Ennemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.velX = Utils.randomVelocity(-0.4, 0.4);
		this.velY = Utils.randomVelocity(-0.4, 0.4);
		try {
			if(Utils.randomInt(1, 4) == 3) {
				this.health = 3;
				skin = ImageIO.read(getClass().getResourceAsStream("/ennemy-2.png"));
			}
			else {
				this.health = 1;
				skin = ImageIO.read(getClass().getResourceAsStream("/ennemy-1.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		if(x + velX >= 0 && x + velX <= 684)
			x += velX;
		else
			velX = Utils.randomVelocity(-0.4, 0.4);
		if(y + velY >= 0 && y + velY <= 353)
			y += velY;
		else
			velY = Utils.randomVelocity(-0.4, 0.4);
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
