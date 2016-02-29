import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
//this class serves as the main window
public class Main implements ActionListener{
//class variables
private static JFrame f;
private JPanel north, east, south, west, center;
private static Dimension w = Toolkit.getDefaultToolkit().getScreenSize();
private Color[] c = {Color.BLUE, Color.WHITE, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY};
private Room[] tr = new Room[Room.getTotalRooms()];
private ArrayList<Employee> emp;

private ProfilePanel pro;
private RoomPanel rp;
private OptionPanel o;
private JobTable jt;
private JMenuBar j;
private JMenu o1, o2;
private JMenuItem i1, i2, i3;

public static void main(String[] args)
{
	//invoke methods from the load class
	Load.loadEmployee();
	Load.loadJob();
	Load.buildArray();
	
	Main m = new Main();
	f.setSize(new Dimension(w.width/3, w.height/2));
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setVisible(true);
}

public Main()
{
	//initialize components
	f = new JFrame();
	f.setLayout(new BorderLayout());
	
	
	north = new JPanel();
	east = new JPanel();
	south = new JPanel();
	west = new JPanel();
	center = new JPanel();
	
	//set layouts for each region
	north.setLayout(new BorderLayout());
	east.setLayout(new BorderLayout());
	south.setLayout(new BorderLayout());
	west.setLayout(new BorderLayout());
	center.setLayout(new BorderLayout());
	
	//initialize components
	pro = new ProfilePanel();
	rp = new RoomPanel();
	o = new OptionPanel();
	jt = new JobTable();
	j = new JMenuBar();
	o1 = new JMenu("File");
	o2 = new JMenu("Help");
	i1 = new JMenuItem("New Employee");
	i2 = new JMenuItem("How to use");
	i3 = new JMenuItem("Demo");
	
	//add items to JMenuBar
	o1.add(i1);
	o2.add(i2);
	o1.add(i3);
	j.add(o1);
	j.add(o2);
	f.setJMenuBar(j);
	
	//define colors of these regions
	south.setBackground(c[1]);
	west.setBackground(c[1]);
	center.setBackground(c[1]);
	
	//define borders for these regions
	west.setBorder(BorderFactory.createRaisedSoftBevelBorder());
	south.setBorder(BorderFactory.createRaisedSoftBevelBorder());
	
	//add JPanels to their regions
	f.add(north, BorderLayout.NORTH);
	f.add(east, BorderLayout.EAST);
	f.add(south, BorderLayout.SOUTH);
	f.add(west, BorderLayout.WEST);
	f.add(center, BorderLayout.CENTER);
	
	//add components to JPanels
	west.add(o, BorderLayout.NORTH);
	west.add(jt, BorderLayout.SOUTH);
	center.add(pro, BorderLayout.NORTH);
	center.add(rp, BorderLayout.SOUTH);
	
	//define actioncommand and addactionlistener to items in JMenu
	i1.setActionCommand("emp");
	i1.addActionListener(this);
	i2.setActionCommand("help");
	i2.addActionListener(this);
	i3.setActionCommand("demo");
	i3.addActionListener(this);
}

@Override
public void actionPerformed(ActionEvent e) {
	
	//if action command is emp invoke CreateEmp constructor
	if(e.getActionCommand() == "emp")
	{
		CreateEmp ne = new CreateEmp();
	}
	//if action command is job invoke CreateJob constructor
	if(e.getActionCommand() == "job")
	{
		CreateJob nj = new CreateJob();
	}
	//if action command is demo invoke CreateRooms constructor
	if(e.getActionCommand() == "demo")
	{
		CreateRooms cr = new CreateRooms();
		JobTable.createList();
	}
	
	if(e.getActionCommand() == "help")
	{
		Help h = new Help();
	}
	
}
}
