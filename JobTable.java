import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//this class controls what is put into the task list

public class JobTable extends JPanel implements ActionListener, FocusListener{
//declare class variables
private static JTextField t;
private static JButton d, o;

private static Room[] room;
private static ArrayList<Job> job = new ArrayList<Job>();
private static ArrayList<Job> loadJ;
private static int rNum;
private static int x = 0;
private static int dirtyRoom = 0;
private static DefaultListModel<String> l;
private static JList<String> toDo;
private static Random ran;
private static String selected;

private GridBagConstraints gbc = new GridBagConstraints();

//jobTable constructor
public JobTable()
{
	setLayout(new GridBagLayout());
	l = new DefaultListModel<String>();
	toDo = new JList<String>(l);
	toDo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	toDo.setVisibleRowCount(5);
	JScrollPane s = new JScrollPane(toDo);
	t = new JTextField("Create new task");
	t.setForeground(Color.LIGHT_GRAY);
	d = new JButton("D");
	o = new JButton("O");
	s.setPreferredSize(new Dimension(200, 80));
	gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.gridwidth = 5;
	gbc.gridheight = 5;
	add(s, gbc);
	gbc.gridx = 0;
	gbc.gridy = 6;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	t.setPreferredSize(new Dimension(150, 25));
	add(t, gbc);
	
	gbc.gridx = 3;
	gbc.gridy = 6;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	d.setToolTipText("Daily");
	d.setPreferredSize(new Dimension(25, 25));
	add(d, gbc);
	gbc.gridx = 4;
	gbc.gridy = 6;
	gbc.gridwidth = 1;
	gbc.gridheight = 1;
	o.setToolTipText("One-Time");
	o.setPreferredSize(new Dimension(25, 25));
	add(o, gbc);
	
	if(Load.getJob().isEmpty() == false)
	{
		populateJobs();
	}
	else
	{
	Job tl = new Job("Task List");
	job.add(tl);
	l.add(0, "Task List");
	toDo.setEnabled(false);
	toDo.setForeground(Color.LIGHT_GRAY);
	}
	setBackground(Color.WHITE);
	setVisible(true);
	t.addFocusListener(this);
	o.setActionCommand("one-time");
	o.addActionListener(this);
	d.setActionCommand("daily");
	d.addActionListener(this);
}
//createList method
public static void createList()
{
	if(toDo.isEnabled() == false)
	{
		toDo.setEnabled(true);
		removeElement("Task List");
		job.remove("Task List");
		toDo.setForeground(Color.BLACK);
	}
	//getRm returns array which gets assigned to room
	room = CreateRooms.getRm();
	//enhanced for loop that randomly sets each room to either clean or dirty
	for(Room i : room)
	{
		ran = new Random();
		rNum = ran.nextInt(2) + 1;
		if(rNum == 1)
		{
			i.setClean(false);
			dirtyRoom++;
		}
		if(rNum == 2)
		{
			i.setClean(true);
		}
	}
	//enhanced for loop to iterate through each room determine whether room is dirty and if it is dirty create a job
	for(Room z : room)
	{
		if(z.isClean() == false)
		{
			job.add(new Job(z.getName()));
		}
	}
	
	for(int i = l.size(); i < job.size(); i++)
	{
		//if l.size() is zero n gets assigned zero else it is assigned i-1
		int n = (l.size() == 0)?0:i-1;
		//if the job doesn't have someone assigned to it it will equal ""
		if(job.get(n).getAssigned().equals(""))
		{
		//assign return string to gname
		String gname = job.get(i).getName();
		//add gname to the DefaultListModel
		l.add(n, gname);
		}
	}
	

}

public static void addJob(Job j)
{
	if(toDo.isEnabled() == false)
	{
		toDo.setEnabled(true);
		removeElement("Task List");
		job.remove("Task List");
		toDo.setForeground(Color.BLACK);
	}
	//add job to array
	job.add(j);
	//assign the size of DefaultListModel to num
	int num = l.size();
	//add the name string to DefaultListModel
	l.add(num, j.getName());
}

public static String getSelected()
{
	//this method returns the selectedvalue in the list
	return toDo.getSelectedValue();
}

public static void removeElement(String n)
{
	//enhanced for loop the iterates through all job objects in job ArrayList
	for(Job jb : job)
	{
		//the job name is a unique value so if it equals n remove it from the list
		if(jb.getName().equals(n))
		{
			job.remove(n);
			l.removeElement(n);
			
		}
		
	}
}
//populates the daily jobs
public static void populateJobs()
{
	//getJob method returns an arraylist and gets assigned to loadJ
	loadJ = Load.getJob();
	//enhanced for loop that iterates through all job objects in loadJ
	for(Job x : loadJ)
	{
		//num gets assigned the value of the size of the DefaultListModel
		int num = l.size();
		//sets all of the jobs to unnassigned so that they show up in the task list
		x.setAssigned("");
		//add job to the arraylist job
		job.add(x);
		//add name of job to the DefaultListModel
		l.add(num, x.getName());
	}
}
//method that returns the job arraylist
public static ArrayList<Job> getJ()
{
	return job;
}
//method that autoselects a value to make for quicker assigning of tasks
public static void setSelection(int s)
{
	if(l.isEmpty() == false)
	{
		if(l.get(s).equals("Task List") == false)
		{
		toDo.setSelectedIndex(s);
		}
	}
	
}
//method that returns loadJ arraylist
public static ArrayList<Job> getJob()
{
	return loadJ;
}

@Override
public void actionPerformed(ActionEvent e) {
	//if the one-time button gets pressed
	if(e.getActionCommand() == "one-time" && t.getText().equals("Create new task") == false && t.getText().equals("") == false)
	{
		Job nj = new Job(t.getText());
		addJob(nj);
	}
	//if the daily button gets pressed
	if(e.getActionCommand() == "daily" && t.getText().equals("Create new task") == false && t.getText().equals("") == false)
	{
		//assign a current list of jobs to loadJ
		loadJ = Load.getJob();
		//create a new job with the name of what ever is in the JTextField
		Job nj = new Job(t.getText());
		//invoke addJob method to add the job to the DefaultListModel
		addJob(nj);
		//add the job to the arraylist loadJ
		loadJ.add(nj);
		//invoke the save method
		Save.saveJob();
		
	}
	//reset JTextBox back to blank
	t.setForeground(Color.LIGHT_GRAY);
	t.setText("Create new task");
	
	
}
@Override
public void focusGained(FocusEvent e) {
	
    t.setText("");
    t.setForeground(Color.BLACK);
        
}
@Override
public void focusLost(FocusEvent e) {
	if(t.getText() == "")
	{
	t.setText("Create new task");
    t.setForeground(Color.LIGHT_GRAY);
	}
}
}
