import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class OptionPanel extends JPanel implements ListSelectionListener, ActionListener{
//class variables
private JTextField t;
private ArrayList<Employee> e;
private ArrayList<Job> j;
private static DefaultListModel<String> lm = new DefaultListModel<String>();
private static JList<String> list;
private JScrollPane l;
private int listSize;
private JButton assign;
	//OptionPanel constructor
	public OptionPanel()
	{
		//assign return value to e
		e = Load.getEmp();
		
		setLayout(new BorderLayout());
		assign = new JButton("Assign");
				
		t = new JTextField();
		
		this.setBackground(Color.WHITE);
		
		for(int i = 0; i < e.size(); i++)
		{
			String temp = e.get(i).getName();
			lm.add(i, temp);
		}
		
		listSize = 5;
		
		list = new JList<String>(lm);
		
		if(lm.isEmpty())
		{
			lm.add(0, "Employee List");
			list.setForeground(Color.LIGHT_GRAY);
			list.setEnabled(false);
		}
		else
		{
			list.setForeground(Color.BLACK);
			list.setEnabled(true);
		}
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(listSize);
		l = new JScrollPane(list);
		Dimension d = l.getPreferredSize();
		d.width = 200;
		l.setPreferredSize(d);
		add(l, BorderLayout.NORTH);
		add(assign, BorderLayout.SOUTH);
		
		
		list.addListSelectionListener(this);
		
		assign.setActionCommand("assign");
		assign.addActionListener(this);
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		//invoke setProfile from ProfilePanel class
		ProfilePanel.setProfile(list.getSelectedValue());
		//invoke displayAssignment from RoomPanel class
		RoomPanel.displayAssignment();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(JobTable.getSelected() != null && list.getSelectedValue() != null)
		{
		//if actioncommand is assign
		if(e.getActionCommand() == "assign")
		{
			//assign return value to j
			j = JobTable.getJ();
			
			//enhanced for loop that iterates through job objects in j
			for(Job t : j)
			{
				//if the name matches the job name
				if(t.getName().equals(JobTable.getSelected()))
				{
					//assign selected value in employee list to assigned for job object
					t.setAssigned(list.getSelectedValue());
					//invoke displayAssignment
					RoomPanel.displayAssignment();
					//remove selected job in job table
					JobTable.removeElement(JobTable.getSelected());
				}
				
			}
			if(JobTable.getJ().isEmpty() == false)
			{
			//select the job existing at index 0
			JobTable.setSelection(0);
			}
		}
		}
	}
	//method that adds string n to the DefaultListModel
	public static void addtoList(String n)
	{
		if(list.isEnabled() == false)
		{
			lm.removeElement("Employee List");
			list.setEnabled(true);
			list.setForeground(Color.BLACK);
			
		}
		int num = lm.size();
		lm.add(num, n);
		list.revalidate();
	}
	//method that returns selected value in list
	public static String getSelected()
	{
		return list.getSelectedValue();
	}
}
