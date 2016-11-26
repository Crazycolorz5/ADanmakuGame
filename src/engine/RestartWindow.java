package engine;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class RestartWindow extends JFrame{

	DrawingPanel ancestor;
	JPanel p;
	JLabel timeSurvived;
	JLabel time;
	JButton restart;
	
	public RestartWindow(int x, int y, DrawingPanel previousGame) {
		super("Game Over!");
		super.setSize(x, y);
		ancestor = previousGame;
		
		String defaultFontName = UIManager.getDefaults().getFont("Label.font").getName();
		timeSurvived = new JLabel("Ticks survived: ");
		timeSurvived.setFont(new Font(defaultFontName, Font.PLAIN, 16));
		Integer timeInt = ancestor.getEngine().getTicks();
		time = new JLabel(timeInt.toString());
		time.setFont(new Font(defaultFontName, Font.PLAIN, 16));
		restart = new JButton("Play Again!");
		restart.setPreferredSize(new Dimension(160,40));
		restart.addActionListener(new restartListener());
		
		p = new JPanel();
		p.add(timeSurvived);
		p.add(time);
		p.add(restart);
		
		this.add(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	private class restartListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			restart();
		}
	}
	public void restart()
	{
		this.dispose();
		ancestor.dispose();
		new StartWindow(240, 190);
	}
}
