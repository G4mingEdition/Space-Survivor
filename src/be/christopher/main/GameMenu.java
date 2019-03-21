package be.christopher.main;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GameMenu extends JPanel {

	private InfosView infosView;
	private GameView gameView;
	
	public GameMenu(Model model) {
		setBackground(Color.DARK_GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		infosView = new InfosView(model);
		add(infosView);
		gameView = new GameView(model);
		add(gameView);
		infosView.setTimer(gameView.getTimer());
	}
	
	public void start() {
		this.gameView.getTimer().start();
	}

	public InfosView getInfosView() {
		return infosView;
	}

	public GameView getGameView() {
		return gameView;
	}
	
}
