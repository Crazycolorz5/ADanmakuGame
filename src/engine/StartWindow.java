package engine;

import hardcodedLevels.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class StartWindow extends JFrame {
	
	//constructors
	JPanel p;
	//JTextField numOfNPC;
	JComboBox<String> level;
	JButton startButton;
	JCheckBox slowDown;
	JCheckBox speedUp;
	JCheckBox catchUp;
	JLabel shotType;
	JRadioButton shotType1;
	JRadioButton shotType2;
	JRadioButton shotType3;
	
	public StartWindow(int x, int y) {
		super();
		super.setSize(x, y);
		//initialize components
		startButton = new JButton("Start");
		startButton.addActionListener(new startListener());
		/*numOfNPC = new JTextField(3);
		numOfNPC.setText("2");*/
		String[] levels = {"Endless", "Level 1"};
		level = new JComboBox<String>(levels);
		slowDown = new JCheckBox("slow down game(you wimp)");
		speedUp = new JCheckBox("speed up game(adventurous!)");
		catchUp = new JCheckBox("enable \"catchup\" after slowdown");
		shotType = new JLabel("Choose Shot Type:");		
		shotType1 = new JRadioButton("Speed", true);
		shotType2 = new JRadioButton("Penetrating");
		shotType3 = new JRadioButton("Spread");
		ButtonGroup shots = new ButtonGroup();
		shots.add(shotType1);
		shots.add(shotType2);
		shots.add(shotType3);
		
		p = new JPanel();
		
		//p.add(numOfNPC,BorderLayout.SOUTH);
		p.add(level);
		p.add(startButton, BorderLayout.LINE_START);
		p.add(slowDown);
		p.add(speedUp);
		p.add(catchUp);
		p.add(shotType);
		p.add(shotType1);
		p.add(shotType2);
		p.add(shotType3);
		p.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add components
		this.add(p);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	private class startListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			startGame();
		}		
	}
	
	
	public void startGame()
	{
		JFrame f = new JFrame("Game");
		f.setSize(DrawingPanel.pWidth, DrawingPanel.pHeight);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setResizable(false);
		DrawingPanel p = new DrawingPanel();
		
		if(slowDown.isSelected())
			p.setPeriod(20000000L);
		else if(speedUp.isSelected())
			p.setPeriod(11111111L);
		if(catchUp.isSelected())
			p.getEngine().setCatchUp();
		int levelNum = level.getSelectedIndex();
		
		int shot;
		if(shotType1.isSelected())
			shot = 1;
		else if(shotType2.isSelected())
			shot = 2;
		else
			shot = 3;
		if(levelNum==0)
		{
			//Endless
			p.getEngine().setLevel(new Endless(p,shot));
		}
		else if(levelNum==1)
		{
			//Level 1
			p.getEngine().setLevel(new LevelOne(p,shot));
		}
		//Inital Enemies in endless mode
		//int numOfEnem = Integer.parseInt(numOfNPC.getText());
		//for(int c=0; c<numOfEnem;c++)
		//	p.getEngine().getEhandler().addEvent(new Event(0,(byte)1,0,0,0,0x0001));
		
		f.setVisible(true);
		f.getContentPane().add(p);
		f.setVisible(true);
		p.startEngine();
		p.setFrame(f);
		this.dispose();
	}
}
