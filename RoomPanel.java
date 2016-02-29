import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
//this class is a JPanel
public class RoomPanel extends JPanel{
//class variables
private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
private static ArrayList<Job> j;

private static Dimension box = new Dimension(10, 10);
private static ArrayList<String> jobName;
private static JPanel mainPanel;
private static JPanel[] panels;
private static JLabel[] labels;
private static DefaultListModel<String> lm = new DefaultListModel<String>();
private static JList<String> task;
private static int ct;

//constructor
public RoomPanel()
{
	setBackground(Color.WHITE);
	setPreferredSize(new Dimension(d.width/3, d.height/3));
	setLayout(new BorderLayout());
	task = new JList<String>(lm);
	jobName = new ArrayList<String>();
	mainPanel = new JPanel();
	mainPanel.setBackground(Color.WHITE);
	mainPanel.setLayout(new GridLayout(0, 1));
	JScrollPane scr = new JScrollPane(task);
	mainPanel.add(scr);
	add(mainPanel);
	lm.add(0, "Task Assignment List");
	task.setForeground(Color.LIGHT_GRAY);
	task.setEnabled(false);
	
}
//displayAssignment method
public static void displayAssignment()
{
	//clear the profile to blank
	lm.clear();
	//local boolean variable
	boolean inArray = false;
	
	if(JobTable.getJ() != null)
	{
	j = JobTable.getJ();
	ct = 0;
	for(Job x : j)
	{
		if(OptionPanel.getSelected() != null)
		{
		//getSelected returns the name of an employee
		String sel = OptionPanel.getSelected();
		//if job is assigned to this employee
		if(x.getAssigned().equals(sel))
		{
			
			//check to see if job already exists in the array
			for(String jn : jobName)
			{
				//if jobname is already in the array set boolean variable to true
				if(jn.equals(x.getName()))
				{
					inArray = true;
				}
			}
			//if it never gets set to true it doesn't exist so add it
			if(inArray == false)
			{
				if(task.getForeground() == Color.LIGHT_GRAY)
				{
				task.setForeground(Color.BLACK);
				}
				lm.add(ct, x.getName());
				ct++;
				
			}
			inArray = false;
		}
		}
	}
	if(lm.isEmpty())
	{
		task.setForeground(Color.LIGHT_GRAY);
		lm.add(0, "Task Assignment List");
	}
	else
	{
	if(lm.get(0).equals("Task Assignment List") && lm.size() > 1)
	{
		lm.removeElement("Task Assignment List");
	}
	}
	//clear elements in jobName array
	jobName.clear();
		
	}
}
}
