package be.christopher.entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player {

	private double x;
	private double y;
	private double velX;
	private double velY;
	private double angle;
	private int health;
	private int healthMax;
	private int score;
	private ArrayList<Bullet> bullets;
	private BufferedImage skin;
	
	public Player(double x, double y, int health, int healthMax) {
		this.x = x;
		this.y = y;
		this.velX = 0;
		this.velY = 0;
		this.angle = 0;
		this.health = health;
		this.healthMax = healthMax;
		this.score = 0;
		bullets = new ArrayList<Bullet>();
		try {
			skin = ImageIO.read(getClass().getResourceAsStream("/player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		if(x + velX >= 0 && x + velX <= 684)
			x += velX;
		if(y + velY >= 0 && y + velY <= 353)
			y += velY;
	}

	public int getX() {
		return (int) x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return (int) y;
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

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealthMax() {
		return healthMax;
	}

	public void setHealthMax(int healthMax) {
		this.healthMax = healthMax;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public BufferedImage getSkin() {
		return skin;
	}

	public void setSkin(BufferedImage skin) {
		this.skin = skin;
	}
	
}
