package be.christopher.main;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Button;

@SuppressWarnings("serial")
public class HomeMenu extends JPanel {
	
	private Button jbStart;
	private Button jbSettings;
	private Button jbExit;
	private BufferedImage background;
	
	public HomeMenu() {
		setBackground(Color.DARK_GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/background-2.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel jlTitle = new JLabel("Space Survivor");
		jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jlTitle.setForeground(Color.LIGHT_GRAY);
		jlTitle.setFont(new Font("Ebrima", Font.BOLD, 30));
		jlTitle.setBounds(-1, 69, 714, 73);
		add(jlTitle);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(251, 140, 215, 2);
		add(separator);
		
		jbStart = new Button("Start Game");
		jbStart.setFont(new Font("Ebrima", Font.PLAIN, 20));
		jbStart.setBackground(new Color(192, 192, 192));
		jbStart.setBounds(251, 195, 214, 37);
		add(jbStart);
		
		jbSettings = new Button("Settings");
		jbSettings.setFont(new Font("Ebrima", Font.PLAIN, 20));
		jbSettings.setBackground(Color.LIGHT_GRAY);
		jbSettings.setActionCommand("");
		jbSettings.setBounds(251, 255, 214, 37);
		add(jbSettings);
		
		jbExit = new Button("Exit");
		jbExit.setFont(new Font("Ebrima", Font.PLAIN, 20));
		jbExit.setBackground(Color.LIGHT_GRAY);
		jbExit.setBounds(250, 315, 215, 37);
		add(jbExit);
		
		
		jbExit.addActionListener(e -> {
			System.exit(0);
		});
	}
	
	public Button getJbStart() {
		return this.jbStart;
	}
	
	public Button getJbSettings() {
		return this.jbSettings;
	}

	public Button getJbExit() {
		return jbExit;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, null);
	}
	
}
