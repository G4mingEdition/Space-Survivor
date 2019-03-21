package be.christopher.entities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet {

	private double x;
	private double y;
	private double velX;
	private double velY;
	private Color color;
	private BufferedImage skin;
	
	public Bullet(double x, double y, double velX, double velY, Color color) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.color = color;
		try {
			skin = ImageIO.read(getClass().getResourceAsStream("/bullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean tick() {
		if(x + velX >= -6 && x + velX <= 710)
			x += velX;
		else
			return false;
		if(y + velY >= -6 && y + velY <= 380)
			y += velY;
		else
			return false;
		return true;
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

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public BufferedImage getSkin() {
		return skin;
	}

	public void setSkin(BufferedImage skin) {
		this.skin = skin;
	}
	
}
