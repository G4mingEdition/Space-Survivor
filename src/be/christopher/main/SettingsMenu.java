package be.christopher.main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class SettingsMenu extends JPanel implements ChangeListener {
	
	private Model model;
	private Button jbBack;
	private JTextField jtfUp;
	private JTextField jtfDown;
	private JTextField jtfLeft;
	private JTextField jtfRight;
	
	public SettingsMenu(Model model) {
		this.model = model;
		model.addView(this);
		setBackground(Color.DARK_GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel jlTitle = new JLabel("Settings");
		jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jlTitle.setForeground(Color.LIGHT_GRAY);
		jlTitle.setFont(new Font("Ebrima", Font.BOLD, 30));
		jlTitle.setBounds(-1, 69, 714, 73);
		add(jlTitle);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(251, 140, 215, 2);
		add(separator);
		
		JLabel jlSfxVolume = new JLabel("SFX Volume");
		jlSfxVolume.setForeground(Color.LIGHT_GRAY);
		jlSfxVolume.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jlSfxVolume.setBounds(72, 268, 84, 20);
		add(jlSfxVolume);
		
		JSlider jsSfxVolume = new JSlider(-50, 0, model.getSfxVolume());
		jsSfxVolume.setBackground(Color.DARK_GRAY);
		jsSfxVolume.setForeground(Color.DARK_GRAY);
		jsSfxVolume.setBounds(67, 291, 154, 26);
		add(jsSfxVolume);
		
		JSlider jsMusicVolume = new JSlider(-50, 0, model.getMusicVolume());
		jsMusicVolume.setForeground(Color.DARK_GRAY);
		jsMusicVolume.setBackground(Color.DARK_GRAY);
		jsMusicVolume.setBounds(67, 227, 154, 26);
		add(jsMusicVolume);
		
		JLabel jlMusicVolume = new JLabel("Music Volume");
		jlMusicVolume.setForeground(Color.LIGHT_GRAY);
		jlMusicVolume.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jlMusicVolume.setBounds(72, 204, 104, 20);
		add(jlMusicVolume);
		
		JSpinner jsDifficulty = new JSpinner();
		jsDifficulty.setModel(new SpinnerListModel(new String[] {"Easy", "Normal", "Hard"}));
		jsDifficulty.setValue("Normal");
		jsDifficulty.setBounds(297, 230, 149, 20);
		add(jsDifficulty);
		
		JLabel jbDifficulty = new JLabel("Difficulty");
		jbDifficulty.setForeground(Color.LIGHT_GRAY);
		jbDifficulty.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jbDifficulty.setBounds(297, 204, 84, 20);
		add(jbDifficulty);
		
		JLabel jlSound = new JLabel("Sound");
		jlSound.setForeground(Color.LIGHT_GRAY);
		jlSound.setFont(new Font("Ebrima", Font.BOLD, 18));
		jlSound.setBounds(57, 173, 104, 20);
		add(jlSound);
		
		JLabel jlGameplay = new JLabel("Gameplay");
		jlGameplay.setForeground(Color.LIGHT_GRAY);
		jlGameplay.setFont(new Font("Ebrima", Font.BOLD, 18));
		jlGameplay.setBounds(280, 173, 104, 20);
		add(jlGameplay);
		
		JLabel jlKeyboard = new JLabel("Keyboard");
		jlKeyboard.setForeground(Color.LIGHT_GRAY);
		jlKeyboard.setFont(new Font("Ebrima", Font.BOLD, 18));
		jlKeyboard.setBounds(488, 173, 104, 20);
		add(jlKeyboard);
		
		JLabel jlUp = new JLabel("Up");
		jlUp.setForeground(Color.LIGHT_GRAY);
		jlUp.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jlUp.setBounds(503, 204, 52, 20);
		add(jlUp);
		
		jtfUp = new JTextField(String.valueOf((char) model.getUp()));
		jtfUp.setEditable(false);
		jtfUp.setBounds(503, 233, 58, 20);
		add(jtfUp);
		jtfUp.setColumns(10);
		
		JLabel jlDown = new JLabel("Down");
		jlDown.setForeground(Color.LIGHT_GRAY);
		jlDown.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jlDown.setBounds(574, 204, 65, 20);
		add(jlDown);
		
		jtfDown = new JTextField(String.valueOf((char) model.getDown()));
		jtfDown.setEditable(false);
		jtfDown.setColumns(10);
		jtfDown.setBounds(574, 233, 58, 20);
		add(jtfDown);
		
		JLabel jlLeft = new JLabel("Left");
		jlLeft.setForeground(Color.LIGHT_GRAY);
		jlLeft.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jlLeft.setBounds(503, 268, 52, 20);
		add(jlLeft);
		
		JLabel jlRight = new JLabel("Right");
		jlRight.setForeground(Color.LIGHT_GRAY);
		jlRight.setFont(new Font("Ebrima", Font.PLAIN, 14));
		jlRight.setBounds(574, 268, 52, 20);
		add(jlRight);
		
		jtfRight = new JTextField(String.valueOf((char) model.getRight()));
		jtfRight.setEditable(false);
		jtfRight.setColumns(10);
		jtfRight.setBounds(574, 297, 58, 20);
		add(jtfRight);
		
		jtfLeft = new JTextField(String.valueOf((char) model.getLeft()));
		jtfLeft.setEditable(false);
		jtfLeft.setColumns(10);
		jtfLeft.setBounds(503, 297, 58, 20);
		add(jtfLeft);
		
		jbBack = new Button("Back");
		jbBack.setFont(new Font("Ebrima", Font.PLAIN, 20));
		jbBack.setBackground(Color.LIGHT_GRAY);
		jbBack.setBounds(595, 394, 99, 37);
		add(jbBack);
		
		repaint();
		
		
		jsDifficulty.addChangeListener(e -> {
			int difficulty = model.getDifficuly();
			if(jsDifficulty.getValue() == "Easy")
				difficulty = 1;
			else if(jsDifficulty.getValue() == "Normal")
				difficulty = 2;
			else if(jsDifficulty.getValue() == "Hard")
				difficulty = 3;
			model.setDifficulty(difficulty);
		});
		
		jtfUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				jtfUp.setFocusable(true);
			}
		});
		jtfUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				
				model.setUp(e.getKeyCode());
				model.update();
				jtfUp.setFocusable(false);
			}
		});
		
		jtfLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				jtfLeft.setFocusable(true);
			}
		});
		jtfLeft.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				
				model.setLeft(e.getKeyCode());
				model.update();
				jtfLeft.setFocusable(false);
			}
		});
		
		jtfDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				jtfDown.setFocusable(true);
			}
		});
		jtfDown.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);

				model.setDown(e.getKeyCode());
				model.update();
				jtfDown.setFocusable(false);
			}
		});
		
		jtfRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				jtfRight.setFocusable(true);
			}
		});
		jtfRight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				
				model.setRight(e.getKeyCode());
				model.update();
				jtfRight.setFocusable(false);
			}
		});
		
		jsMusicVolume.addChangeListener(e -> {
			model.getMusicGainControl().setValue(-20.0f + jsMusicVolume.getValue());
		});
		jsSfxVolume.addChangeListener(e -> {
			model.setSfxVolume(jsSfxVolume.getValue());
		});
	}
	
	public Button getJbBack() {
		return this.jbBack;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		jtfUp.setText(String.valueOf((char) model.getUp()));
		jtfDown.setText(String.valueOf((char) model.getDown()));
		jtfLeft.setText(String.valueOf((char) model.getLeft()));
		jtfRight.setText(String.valueOf((char) model.getRight()));
	}
	
}
