package be.christopher.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class Main extends JFrame implements KeyListener {
	
	private BufferedImage icon;
	private Model model;
	private GameMenu jpGame;
	private HomeMenu jpHome;
	private SettingsMenu jpSettings;
	private EndMenu jpEnd;
	private boolean isUpPressed = false;
	private boolean isDownPressed = false;
	private boolean isLeftPressed = false;
	private boolean isRightPressed = false;
	private Timer timer;
	
	public Main() {
		setTitle("Space Survivor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 100, 720, 480);
		try {
			icon = ImageIO.read(getClass().getResourceAsStream("/player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setIconImage(icon);
		
		model = new Model();
		jpGame = new GameMenu(model);
		jpHome = new HomeMenu();
		jpSettings = new SettingsMenu(model);
		jpEnd = new EndMenu(model);
		
		displayHome();	
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		model.playTheme();
		
		
		addKeyListener(this);
		setFocusable(true);
		jpHome.getJbStart().addActionListener(e -> {
			model.playButtonPressedFx();
			displayGame();
		});
		
		jpHome.getJbSettings().addActionListener(e -> {
			model.playButtonPressedFx();
			displaySettings();
		});
		
		jpGame.getInfosView().getJbPause().addActionListener(e -> {
			model.playButtonPressedFx();
		});
		
		jpGame.getInfosView().getJbResume().addActionListener(e -> {
			model.playButtonPressedFx();
		});

		jpSettings.getJbBack().addActionListener(e -> {
			model.playButtonPressedFx();
			displayHome();
		});
		timer = new Timer(1, e -> {
			if(!model.getIsPlaying()) {
				timer.stop();
				displayEnd();
				if(model.getPlayer().getHealth() < 1)
					model.playGameOverFx();
			}
		});
		timer.start();
	}

	
	public static void main(String[] args) {	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Main();
	}

	
	private void displayGame() {
		setContentPane(jpGame);
		jpGame.start();
		revalidate();
	}
	
	private void displayHome() {
		setContentPane(jpHome);
		revalidate();
	}
	
	private void displaySettings() {
		setContentPane(jpSettings);
		revalidate();
	}
	
	private void displayEnd() {
		setContentPane(jpEnd);
		revalidate();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!model.getIsPause() && model.getIsPlaying() ) {
			int key = e.getKeyCode();
			
			if(key == model.getUp())
				isUpPressed = true;
			else if(key == model.getLeft())
				isLeftPressed = true;
			else if(key == model.getDown())
				isDownPressed = true;
			else if(key == model.getRight())
				isRightPressed = true;
			
			if(isUpPressed && !isDownPressed)
				model.getPlayer().setVelY(-0.75);
			else if(isDownPressed && !isUpPressed)
				model.getPlayer().setVelY(0.75);
			else
				model.getPlayer().setVelY(0);
			
			if(isLeftPressed && !isRightPressed)
				model.getPlayer().setVelX(-0.75);
			else if(isRightPressed && !isLeftPressed)
				model.getPlayer().setVelX(0.5);
			else
				model.getPlayer().setVelX(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!model.getIsPause() && model.getIsPlaying()) {
			int key = e.getKeyCode();
			
			if(key == model.getUp()) {
				isUpPressed = false;
				if(isDownPressed)
					model.getPlayer().setVelY(0.75);
				else
					model.getPlayer().setVelY(0);
			}
			if(key == model.getLeft()) {
				isLeftPressed = false;
				if(isRightPressed)
					model.getPlayer().setVelX(0.75);
				else
					model.getPlayer().setVelX(0);
			}
			if(key == model.getDown()) {
				isDownPressed = false;
				if(isUpPressed)
					model.getPlayer().setVelY(-0.75);
				else
					model.getPlayer().setVelY(0);
			}
			if(key == model.getRight()) {
				isRightPressed = false;
				if(isLeftPressed)
					model.getPlayer().setVelX(-0.75);
				else
					model.getPlayer().setVelX(0);
			}
		}
		else {
			isUpPressed = false;
			isDownPressed = false;
			isLeftPressed = false;
			isRightPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
}
