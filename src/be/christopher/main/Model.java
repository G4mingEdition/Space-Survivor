package be.christopher.main;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import be.christopher.entities.Boss;
import be.christopher.entities.Clock;
import be.christopher.entities.Ennemy;
import be.christopher.entities.Player;
import be.christopher.entities.RepairKey;

public class Model {

	private ArrayList<ChangeListener> views;
	private Player player;
	private ArrayList<Ennemy> ennemies;
	private int ennemiesMax;
	private Boss boss;
	private RepairKey repairKey;
	private Clock clock;
	private Point spawnPoint;
	private int time;
	private int timeMax;
	private boolean isPlaying;
	private boolean isPause;
	private FloatControl musicGainControl;
	private int musicVolume;
	private int sfxVolume;
	private int up;
	private int down;
	private int left;
	private int right;
	private int difficulty;
	
	public Model() {
		views = new ArrayList<ChangeListener>();
		spawnPoint = new Point(345, 185);
		player = new Player((int) spawnPoint.getX(), (int) spawnPoint.getY(), 4, 4);
		ennemies = new ArrayList<Ennemy>();
		ennemiesMax = 5;
		time = 0;
		isPlaying = true;
		isPause = false;
		timeMax = 50;
		musicVolume = 0;
		sfxVolume = 0;
		up = KeyEvent.VK_Z;
		down = KeyEvent.VK_S;
		left = KeyEvent.VK_Q;
		right = KeyEvent.VK_D;
		difficulty = 2;
	}
	
	public void addView(ChangeListener view) {
		if(!views.contains(view))
			views.add(view);
	}
	
	public void removeView(ChangeListener view) {
		if(views.contains(view))
			views.remove(view);
	}
	
	public void processEvent(ChangeEvent e) {
		for(ChangeListener view : views) {
			view.stateChanged(e);
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Ennemy> getEnnemies() {
		return ennemies;
	}

	public void setEnnemies(ArrayList<Ennemy> ennemies) {
		this.ennemies = ennemies;
	}
	
	public int getEnnemiesMax() {
		return ennemiesMax;
	}

	public void setEnnemiesMax(int ennemiesMax) {
		this.ennemiesMax = ennemiesMax;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	public RepairKey getRepairKey() {
		return repairKey;
	}

	public void setRepairKey(RepairKey repairKey) {
		this.repairKey = repairKey;
	}
	
	public Clock getClock() {
		return clock;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	public Point getSpawnPoint() {
		return spawnPoint;
	}

	public void setSpawnPoint(Point spawnPoint) {
		this.spawnPoint = spawnPoint;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public void update() {
		processEvent(new ChangeEvent(this));
	}

	public boolean getIsPlaying() {
		return isPlaying;
	}

	public void setIsPlaying(boolean playing) {
		this.isPlaying = playing;
	}

	public boolean getIsPause() {
		return isPause;
	}

	public void setIsPause(boolean isPause) {
		this.isPause = isPause;
	}
	
	public int getTimeMax() {
		return timeMax;
	}

	public void setTimeMax(int gameTimeMax) {
		this.timeMax = gameTimeMax;
	}
	
	public int getMusicVolume() {
		return musicVolume;
	}

	public void setMusicVolume(int musicVolume) {
		this.musicVolume = musicVolume;
	}

	public int getSfxVolume() {
		return sfxVolume;
	}

	public void setSfxVolume(int sfxVolume) {
		this.sfxVolume = sfxVolume;
	}

	public ArrayList<ChangeListener> getViews() {
		return views;
	}

	public void setViews(ArrayList<ChangeListener> views) {
		this.views = views;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}
	
	public int getDifficuly() {
		return this.difficulty;
	}
	
	public void setDifficulty(int difficulty) {
		if(difficulty >= 1 && difficulty <= 3) {
			this.difficulty = difficulty;
			
			if(difficulty == 1) {
				this.ennemiesMax = 4;
				this.getPlayer().setHealth(6);
				this.getPlayer().setHealthMax(6);
			}
			else if(difficulty == 2) {
				this.ennemiesMax = 5;
				this.getPlayer().setHealth(4);
				this.getPlayer().setHealthMax(4);
			}
			else if(difficulty == 3) {
				this.ennemiesMax = 6;
				this.getPlayer().setHealth(2);
				this.getPlayer().setHealthMax(2);
			}
		}
	}

	public FloatControl getMusicGainControl() {
		return musicGainControl;
	}

	public void setMusicGainControl(FloatControl musicGainControl) {
		this.musicGainControl = musicGainControl;
	}

	public void playTheme() {
		try {
			// Open an audio input stream.
	        URL url = this.getClass().getClassLoader().getResource("theme.wav");
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	        // Get a sound clip resource.
	        Clip theme = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        theme.open(audioIn);
	        musicGainControl = (FloatControl) theme.getControl(FloatControl.Type.MASTER_GAIN);
	        musicGainControl.setValue(-20.0f);
	        theme.start();
	    } catch (UnsupportedAudioFileException exception) {
	    	exception.printStackTrace();
	    } catch (IOException exception) {
	    	exception.printStackTrace();
	    } catch (LineUnavailableException exception) {
	    	exception.printStackTrace();
	    }
	}
	
	public void playButtonPressedFx() {
		try {
			// Open an audio input stream.
	        URL url = this.getClass().getClassLoader().getResource("button.wav");
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	        // Get a sound clip resource.
	        Clip button = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        button.open(audioIn);
	        FloatControl gainControl = (FloatControl) button.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(-20.0f + this.sfxVolume);
	        button.start();
	    } catch (UnsupportedAudioFileException exception) {
	    	exception.printStackTrace();
	    } catch (IOException exception) {
	    	exception.printStackTrace();
	    } catch (LineUnavailableException exception) {
	    	exception.printStackTrace();
	    }
	}
	
	public void playShotFx() {
		try {
			// Open an audio input stream.
	        URL url = this.getClass().getClassLoader().getResource("shot.wav");
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	        // Get a sound clip resource.
	        Clip shot = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        shot.open(audioIn);
	        FloatControl gainControl = (FloatControl) shot.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(-20.0f + this.sfxVolume);
	        shot.start();
	    } catch (UnsupportedAudioFileException exception) {
	    	exception.printStackTrace();
	    } catch (IOException exception) {
	    	exception.printStackTrace();
	    } catch (LineUnavailableException exception) {
	    	exception.printStackTrace();
	    }
	}
	
	public void playDamageFx() {
		try {
			// Open an audio input stream.
	        URL url = this.getClass().getClassLoader().getResource("damage.wav");
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	        // Get a sound clip resource.
	        Clip damage = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        damage.open(audioIn);
	        FloatControl gainControl = (FloatControl) damage.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(0.0f + this.sfxVolume);
	        damage.start();
	    } catch (UnsupportedAudioFileException exception) {
	    	exception.printStackTrace();
	    } catch (IOException exception) {
	    	exception.printStackTrace();
	    } catch (LineUnavailableException exception) {
	    	exception.printStackTrace();
	    }
	}
	
	public void playExplosionFx() {
		try {
			// Open an audio input stream.
	        URL url = this.getClass().getClassLoader().getResource("explosion.wav");
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	        // Get a sound clip resource.
	        Clip explosion = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        explosion.open(audioIn);
	        FloatControl gainControl = (FloatControl) explosion.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(-10.0f + this.sfxVolume);
	        explosion.start();
	    } catch (UnsupportedAudioFileException exception) {
	    	exception.printStackTrace();
	    } catch (IOException exception) {
	    	exception.printStackTrace();
	    } catch (LineUnavailableException exception) {
	    	exception.printStackTrace();
	    }
	}
	
	public void playGameOverFx() {
		try {
			// Open an audio input stream.
	        URL url = this.getClass().getClassLoader().getResource("gameOver.wav");
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	        // Get a sound clip resource.
	        Clip gameOver = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        gameOver.open(audioIn);
	        FloatControl gainControl = (FloatControl) gameOver.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(-20.0f - this.sfxVolume);
	        gameOver.start();
	    } catch (UnsupportedAudioFileException exception) {
	    	exception.printStackTrace();
	    } catch (IOException exception) {
	    	exception.printStackTrace();
	    } catch (LineUnavailableException exception) {
	    	exception.printStackTrace();
	    }
	}
	
	public void playRepairsFx() {
		try {
			// Open an audio input stream.
	        URL url = this.getClass().getClassLoader().getResource("repairs.wav");
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	        // Get a sound clip resource.
	        Clip repairs = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        repairs.open(audioIn);
	        FloatControl gainControl = (FloatControl) repairs.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(-5.0f - this.sfxVolume);
	        repairs.start();
	    } catch (UnsupportedAudioFileException exception) {
	    	exception.printStackTrace();
	    } catch (IOException exception) {
	    	exception.printStackTrace();
	    } catch (LineUnavailableException exception) {
	    	exception.printStackTrace();
	    }
	}
	
	public void playStartingTimeFx() {
		try {
			// Open an audio input stream.
	        URL url = this.getClass().getClassLoader().getResource("startingTime.wav");
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	        // Get a sound clip resource.
	        Clip startingTime = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        startingTime.open(audioIn);
	        FloatControl gainControl = (FloatControl) startingTime.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(-20.0f - this.sfxVolume);
	        startingTime.start();
	    } catch (UnsupportedAudioFileException exception) {
	    	exception.printStackTrace();
	    } catch (IOException exception) {
	    	exception.printStackTrace();
	    } catch (LineUnavailableException exception) {
	    	exception.printStackTrace();
	    }
	}
	
	public void playStoppingTimeFx() {
		try {
			// Open an audio input stream.
	        URL url = this.getClass().getClassLoader().getResource("stoppingTime.wav");
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	        // Get a sound clip resource.
	        Clip stoppingTime = AudioSystem.getClip();
	        // Open audio clip and load samples from the audio input stream.
	        stoppingTime.open(audioIn);
	        FloatControl gainControl = (FloatControl) stoppingTime.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(-20.0f - this.sfxVolume);
	        stoppingTime.start();
	    } catch (UnsupportedAudioFileException exception) {
	    	exception.printStackTrace();
	    } catch (IOException exception) {
	    	exception.printStackTrace();
	    } catch (LineUnavailableException exception) {
	    	exception.printStackTrace();
	    }
	}
	
}
