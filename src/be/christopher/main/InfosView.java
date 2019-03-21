package be.christopher.main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class InfosView extends JPanel implements ChangeListener {

	private Model model;
	private Timer timer;
	private JProgressBar jpbHealth;
	private JTextField jtScore;
	private JProgressBar jpbTime;
	private Button jbResume;
	private Button jbPause;
	
	public InfosView(Model model) {
		this.model = model;
		model.addView(this);
		
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 720, 68);
		setLayout(null);
		
		JLabel jlHealth = new JLabel("Health");
		jlHealth.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jlHealth.setBounds(10, 11, 146, 14);
		add(jlHealth);
		
		jpbHealth = new JProgressBar();
		jpbHealth.setForeground(Color.GREEN);
		jpbHealth.setValue(model.getPlayer().getHealth());
		jpbHealth.setMaximum(model.getPlayer().getHealthMax());
		jpbHealth.setBounds(10, 36, 146, 21);
		add(jpbHealth);
		
		JLabel jlScore = new JLabel("Score");
		jlScore.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jlScore.setBounds(166, 13, 146, 14);
		add(jlScore);
		
		jtScore = new JTextField();
		jtScore.setText(String.valueOf(model.getPlayer().getScore()));
		jtScore.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jtScore.setEnabled(false);
		jtScore.setEditable(false);
		jtScore.setBounds(166, 36, 146, 21);
		add(jtScore);
		jtScore.setColumns(10);
		
		JLabel jlTime = new JLabel("Time");
		jlTime.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jlTime.setBounds(322, 11, 146, 14);
		add(jlTime);
		
		jpbTime = new JProgressBar();
		jpbTime.setValue(model.getTime());
		jpbTime.setMaximum(model.getTimeMax());
		jpbTime.setBounds(322, 36, 146, 21);
		jpbTime.setForeground(Color.ORANGE);
		add(jpbTime);
		
		jbResume = new Button("Resume");
		jbResume.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jbResume.setBounds(485, 11, 104, 47);
		jbResume.setFocusable(false);
		add(jbResume);
		
		jbPause = new Button("Pause");
		jbPause.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jbPause.setBounds(600, 11, 104, 47);
		jbPause.setFocusable(false);
		add(jbPause);
		
		repaint();
		
		
		jbResume.addActionListener(e -> {
			if(!timer.isRunning()) {
				timer.start();
				model.setIsPause(false);
			}
			
		});
		
		jbPause.addActionListener(e -> {
			if(timer.isRunning()) {
				timer.stop();
				model.setIsPause(true);
				model.getPlayer().setVelX(0);
				model.getPlayer().setVelY(0);
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
		
		jpbHealth.setValue(model.getPlayer().getHealth());
		jpbHealth.setMaximum(model.getPlayer().getHealthMax());;
		jtScore.setText(String.valueOf(model.getPlayer().getScore()));
		jpbTime.setValue(model.getTime());
	}
	
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Button getJbResume() {
		return jbResume;
	}

	public Button getJbPause() {
		return jbPause;
	}
	
}
