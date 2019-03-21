package be.christopher.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class EndMenu extends JPanel implements ChangeListener {
	
	private Model model;
	private JLabel jlTitle;
	private JLabel jlScore;
	
	public EndMenu(Model model) {
		this.model = model;
		model.addView(this);
		
		setBackground(Color.DARK_GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		jlTitle = new JLabel("");
		jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jlTitle.setForeground(Color.LIGHT_GRAY);
		jlTitle.setFont(new Font("Ebrima", Font.BOLD, 30));
		jlTitle.setBounds(-1, 69, 714, 73);
		add(jlTitle);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(251, 140, 215, 2);
		add(separator);	
		
		jlScore = new JLabel("");
		jlScore.setHorizontalAlignment(SwingConstants.CENTER);
		jlScore.setForeground(Color.LIGHT_GRAY);
		jlScore.setFont(new Font("Ebrima", Font.BOLD, 26));
		jlScore.setBounds(-1, 150, 714, 154);
		add(jlScore);
		
		repaint();
	}
	
	public void setModel(Model model) {
		this.model = model;
		repaint();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		repaint();		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(model.getPlayer().getHealth() > 0) {
			jlTitle.setText("YOU SURVIVED!");
		}
		else {
			jlTitle.setText("GAME OVER!");
		}
		jlScore.setText("Score : " + model.getPlayer().getScore());
	}
}
