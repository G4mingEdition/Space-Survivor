package be.christopher.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import be.christopher.entities.Boss;
import be.christopher.entities.Bullet;
import be.christopher.entities.Clock;
import be.christopher.entities.Ennemy;
import be.christopher.entities.RepairKey;
import be.christopher.utils.Utils;

@SuppressWarnings("serial")
public class GameView extends JPanel implements ChangeListener, MouseMotionListener, MouseListener {

	private Model model;
	private Timer timer;
	private int ticks;
	private int oldTicks;
	private boolean isShooting = false;
	private BufferedImage background;
	
	
	public GameView(Model model) {
		this.model = model;
		model.addView(this);
		ticks = 0;
		oldTicks = 0;
		
		setBounds(0, 68, 720,382);
		setLayout(null);
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/background.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		addMouseMotionListener(this);
		addMouseListener(this);
		timer = new Timer(1, e -> {
			if(model.getIsPlaying() && !model.getIsPause()) {
				model.update();
				if(ticks%200 == 0 && isShooting) {			    
				    if(model.getPlayer().getBullets().size() < 2) {
						model.playShotFx();
						
				        double velX = 1.75 * Math.cos(Math.toRadians(model.getPlayer().getAngle() - 90));
				        double velY = 1.75 * Math.sin(Math.toRadians(model.getPlayer().getAngle() - 90));
					    model.getPlayer().getBullets().add(new Bullet(model.getPlayer().getX() + 10, model.getPlayer().getY() + 10, velX, velY, Color.ORANGE));
				    }
				}
				if(ticks%300 == 0 && model.getEnnemies().size() < model.getEnnemiesMax()) {
					if(model.getClock() == null || (model.getClock() != null && !model.getClock().getIsEnabled())) {
						boolean isSpawned = false;
						while(!isSpawned) {
							int x = Utils.randomInt(15, 690);
							int y = Utils.randomInt(15, 350);
							if(!(x >= model.getPlayer().getX() - 15 && x <= model.getPlayer().getX() + 15)) {
								if(!(y >= model.getPlayer().getY() - 25 && y <= model.getPlayer().getY() + 25)) {
									isSpawned = true;
									model.getEnnemies().add(new Ennemy(x, y));	
								}
							}
						}
					}
				}
				if(ticks%1000 == 0) {
					if(model.getTime() + 1 < model.getTimeMax()) {
						model.setIsPlaying(true);
						model.setTime(model.getTime() + 1);
						if(model.getClock() == null || (model.getClock() != null && !model.getClock().getIsEnabled())) {
							if(model.getTimeMax() - (ticks/1000) == 10) {		
								boolean isSpawned = false;
								while(!isSpawned) {
									int x = Utils.randomInt(15, 690);
									int y = Utils.randomInt(15, 350);
									if(!(x >= model.getPlayer().getX() - 15 && x <= model.getPlayer().getX() + 15)) {
										if(!(y >= model.getPlayer().getY() - 25 && y <= model.getPlayer().getY() + 25)) {
											isSpawned = true;
											model.setBoss(new Boss(x, y));	
										}
									}
								}
							}
						}
					}
					else {
						model.setIsPlaying(false);
						timer.stop();
					}
				}
				if(ticks%4000 == 0) {
					int random = Utils.randomInt(1, 5);

					if(random == 2 && model.getRepairKey() == null) {
						boolean isSpawned = false;
						while(!isSpawned) {
							int x = Utils.randomInt(15, 690);
							int y = Utils.randomInt(15, 350);
							if(!(x >= model.getPlayer().getX() - 15 && x <= model.getPlayer().getX() + 15)) {
								if(!(y >= model.getPlayer().getY() - 25 && y <= model.getPlayer().getY() + 25)) {
									isSpawned = true;
									model.setRepairKey(new RepairKey(x, y));
								}
							}
						}
					}
					if(random == 4 && model.getClock() == null) {
						boolean isSpawned = false;
						while(!isSpawned) {
							int x = Utils.randomInt(15, 690);
							int y = Utils.randomInt(15, 350);
							if(!(x >= model.getPlayer().getX() - 15 && x <= model.getPlayer().getX() + 15)) {
								if(!(y >= model.getPlayer().getY() - 25 && y <= model.getPlayer().getY() + 25)) {
									isSpawned = true;
									model.setClock(new Clock(x, y));
								}
							}
						}
					}
				}
				ticks++;
			}
			else {
				timer.stop();
			}
		});
	}
	
	public void setModel(Model model) {
		this.model = model;
		repaint();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {	
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(background, 0, 0, null);
		model.getPlayer().tick();
		for(int i = 0; i < model.getPlayer().getBullets().size(); i++) {
			Bullet bullet = model.getPlayer().getBullets().get(i);
			if(bullet.tick()) {
				g.drawImage(bullet.getSkin(), (int) bullet.getX(), (int) bullet.getY(), null);
				boolean impact = false;
				if(model.getBoss() != null) {
					if(bullet.getX() >= model.getBoss().getX() - 15 && bullet.getX() <= model.getBoss().getX() + 15) {
						if(bullet.getY() >= model.getBoss().getY() - 25 && bullet.getY() <= model.getBoss().getY() + 25) {
							impact = true;
							model.getPlayer().getBullets().remove(bullet);
							
							model.getBoss().setHealth(model.getBoss().getHealth() - 1);
							System.out.println(model.getBoss().getHealth());
							if(model.getBoss().getHealth() < 1) {
								model.playExplosionFx();
								model.setBoss(null);
								model.getPlayer().setScore(model.getPlayer().getScore() + 10);
							}
						}
					}
				}
				int j = 0;
				while(j < model.getEnnemies().size() && !impact) {
					Ennemy ennemy = model.getEnnemies().get(j);
					if(bullet.getX() >= ennemy.getX() - 15 && bullet.getX() <= ennemy.getX() + 15) {
						if(bullet.getY() >= ennemy.getY() - 25 && bullet.getY() <= ennemy.getY() + 25) {
							model.getPlayer().getBullets().remove(i);
							impact = true;
							
							ennemy.setHealth(ennemy.getHealth() - 1);
							if(ennemy.getHealth() < 1) {
								model.playExplosionFx();
								model.getEnnemies().remove(j);
								model.getPlayer().setScore(model.getPlayer().getScore() + 1);
							}
						}
					}
					j++;
				}
			}
			else
				model.getPlayer().getBullets().remove(bullet);
		}
		
		for(int i = 0; i < model.getEnnemies().size(); i++) {
			Ennemy ennemy = model.getEnnemies().get(i);
			g.drawImage(ennemy.getSkin(), (int) ennemy.getX(), (int) ennemy.getY(), null);
		}
		
		if(model.getBoss() != null) {
			if(model.getClock() == null || (model.getClock() != null && !model.getClock().getIsEnabled())) {
				model.getBoss().tick();
			}
			g.drawImage(model.getBoss().getSkin(), (int) model.getBoss().getX(), (int) model.getBoss().getY(), null);
		}
		
		AffineTransform at = AffineTransform.getTranslateInstance(model.getPlayer().getX(), model.getPlayer().getY());
		at.rotate(Math.toRadians(model.getPlayer().getAngle()), 15, 15);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(model.getPlayer().getSkin(), at, null);
		
		g.setColor(Color.WHITE);
		g.drawString("x:" + model.getPlayer().getX() + " y:" + model.getPlayer().getY(), 10, 20);
		
		for(int i = 0; i < model.getEnnemies().size(); i++) {
			Ennemy ennemy = model.getEnnemies().get(i);
			if(model.getClock() == null || (model.getClock() != null && !model.getClock().getIsEnabled())) {
				ennemy.tick();
			}
			if(model.getPlayer().getX() >= ennemy.getX() - 15 && model.getPlayer().getX() <= ennemy.getX() + 15) {
				if(model.getPlayer().getY() >= ennemy.getY() - 25 && model.getPlayer().getY() <= ennemy.getY() + 25) {
					if(ticks - oldTicks >= 500 || model.getPlayer().getHealth() == model.getPlayer().getHealthMax()) {
						model.playDamageFx();
						model.getPlayer().setHealth(model.getPlayer().getHealth() - 1);
						oldTicks = ticks;
					}
					if(model.getPlayer().getHealth() < 1) {
						model.setIsPlaying(false);
					}
				}
			}
		}
		
		if(model.getRepairKey() != null) {
			if(model.getRepairKey().getX() >= model.getPlayer().getX() - 15 && model.getRepairKey().getX() <= model.getPlayer().getX() + 15) {
				if(model.getRepairKey().getY() >= model.getPlayer().getY() - 25 && model.getRepairKey().getY() <= model.getPlayer().getY() + 25) {
					model.playRepairsFx();
					model.setRepairKey(null);
					if(model.getPlayer().getHealth() < model.getPlayer().getHealthMax()) {
						model.getPlayer().setHealth(model.getPlayer().getHealth() + 1);
					}
				}
			}			
			if(model.getRepairKey() != null) {
				g.drawImage(model.getRepairKey().getSkin(), (int) model.getRepairKey().getX(), (int) model.getRepairKey().getY(), null);
			}
		}
		
		if(model.getClock() != null) {
			if(!model.getClock().getIsEnabled()) {
				if(model.getClock().getTicks() > 1) {
					if(model.getClock().getX() >= model.getPlayer().getX() - 15 && model.getClock().getX() <= model.getPlayer().getX() + 15) {
						if(model.getClock().getY() >= model.getPlayer().getY() - 25 && model.getClock().getY() <= model.getPlayer().getY() + 25) {
							model.playStoppingTimeFx();
							model.getClock().setIsEnabled(true);
						}
					}
				}
				else {
					model.playStartingTimeFx();
					model.setClock(null);
				}
			}
			
			if(model.getClock() != null) {
				if(model.getClock().getIsEnabled()) {
					if(model.getClock().getTicks() >= 1) {
						model.getClock().ticks();
					}
				}
				if(!model.getClock().getIsEnabled()) {
					g.drawImage(model.getClock().getSkin(), (int) model.getClock().getX(), (int) model.getClock().getY(), null);
				}
			}
		}
	}
	
	public Timer getTimer() {
		return this.timer;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(!model.getIsPause() && model.getIsPlaying()) {
			double mouseX = e.getX();
		    double mouseY = e.getY();
		    double angle = Math.toDegrees(Math.atan2(model.getPlayer().getY() - mouseY, model.getPlayer().getX() - mouseX)) + 180;
		    double angleFinal = Math.round(angle + Math.ceil(- angle / 360 ) * 360);
		    model.getPlayer().setAngle(angleFinal + 90);
		}
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		if(!model.getIsPause() && model.getIsPlaying()) {
			double mouseX = e.getX();
		    double mouseY = e.getY();
		    double angle = Math.toDegrees(Math.atan2(model.getPlayer().getY() - mouseY, model.getPlayer().getX() - mouseX)) + 180;
		    double angleFinal = Math.round(angle + Math.ceil(- angle / 360 ) * 360);
		    model.getPlayer().setAngle(angleFinal + 90);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isShooting = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isShooting = false;
	}

}
