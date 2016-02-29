import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateEmp extends JFrame implements ActionListener, FocusListener{
//declare variables
private static ArrayList<Employee> e;
private JTextField n;
private JComboBox<String> t;
private JButton c;
private JPanel p;
private JLabel status;
private JLabel su,m,tu,w,th,f,sa;
private JCheckBox sunday, monday, tuesday, wednesday, thursday, friday, saturday;
private GridBagConstraints gbc = new GridBagConstraints();

public CreateEmp()
{	
	//assign return arraylist from Load class to e arraylist
	e = Load.getEmp();
	
	setLayout(new BorderLayout());
	p = new JPanel();
	p.setLayout(new GridBagLayout());
	
	c = new JButton("Create");
	n = new JTextField("Employee Name");
	n.setForeground(Color.LIGHT_GRAY);
	t = new JComboBox<String>();
	status = new JLabel();
	c.setPreferredSize(new Dimension(100, 25));
	n.setPreferredSize(new Dimension(150, 25));
	
	//initialize JCheckboxes
	sunday = new JCheckBox();
	monday = new JCheckBox();
	tuesday = new JCheckBox();
	wednesday = new JCheckBox();
	thursday = new JCheckBox();
	friday = new JCheckBox();
	saturday = new JCheckBox();
	//initialize JLabels
	su = new JLabel("Sunday");
	m = new JLabel("Monday");
	tu = new JLabel("Tuesday");
	w = new JLabel("Wednesday");
	th = new JLabel("Thursday");
	f = new JLabel("Friday");
	sa = new JLabel("Saturday");
	
	//enhanced for loop that iterates through all strings in the array returned by getJobTypes method
	for(String i : Employee.getJobTypes())
	{
		t.addItem(i);
	}
	
	//position and add the components to the JFrame
	gbc.insets = new Insets(10, 0, 10, 0);
	gbc.gridwidth = 1;
	gbc.gridx = 0;
	gbc.gridy = 0;
	p.add(n, gbc);
	gbc.gridx = 0;
	gbc.gridy = 1;
	p.add(t, gbc);
	
	gbc.gridx = 0;
	gbc.gridy = 2;
	p.add(su, gbc);
	gbc.gridx = 0;
	gbc.gridy = 3;
	p.add(m, gbc);
	gbc.gridx = 0;
	gbc.gridy = 4;
	p.add(tu, gbc);
	gbc.gridx = 0;
	gbc.gridy = 5;
	p.add(w, gbc);
	gbc.gridx = 0;
	gbc.gridy = 6;
	p.add(th, gbc);
	gbc.gridx = 0;
	gbc.gridy = 7;
	p.add(f, gbc);
	gbc.gridx = 0;
	gbc.gridy = 8;
	p.add(sa, gbc);
	
	gbc.gridx = 1;
	gbc.gridy = 2;
	p.add(sunday, gbc);
	gbc.gridx = 1;
	gbc.gridy = 3;
	p.add(monday, gbc);
	gbc.gridx = 1;
	gbc.gridy = 4;
	p.add(tuesday, gbc);
	gbc.gridx = 1;
	gbc.gridy = 5;
	p.add(wednesday, gbc);
	gbc.gridx = 1;
	gbc.gridy = 6;
	p.add(thursday, gbc);
	gbc.gridx = 1;
	gbc.gridy = 7;
	p.add(friday, gbc);
	gbc.gridx = 1;
	gbc.gridy = 8;
	p.add(saturday, gbc);
	gbc.gridx = 0;
	gbc.gridy = 9;
	p.add(c, gbc);
	gbc.gridx = 0;
	gbc.gridy = 10;
	p.add(status, gbc);
	
	add(p, BorderLayout.NORTH);
	setSize(200, 500);
	setVisible(true);
	
	n.addFocusListener(this);
	
	//define actioncommand and add actionlistener to the create button
	c.setActionCommand("create");
	c.addActionListener(this);
}
//method that returns an arraylist of Employee objects
public static ArrayList<Employee> getE()
{
	return e;
}

@Override
public void actionPerformed(ActionEvent a) {
	//if the actioncommand is create and the textfield is not blank
	if(a.getActionCommand() == "create" && n.getText().equals("") == false && n.getText().equals("Employee Name") == false)
	{
		//assign value in JTextField to name
		String name = n.getText();
		//assign value in JComboBox to string type
		String type = (String) t.getSelectedItem();
		
		//determine which checkboxes are checked and assign 1 for checked or 0 for unchecked
		int sun = (sunday.isSelected())?1:0;
		int mon = (monday.isSelected())?1:0;
		int tue = (tuesday.isSelected())?1:0;
		int wed = (wednesday.isSelected())?1:0;
		int thur = (thursday.isSelected())?1:0;
		int fri = (friday.isSelected())?1:0;
		int sat = (saturday.isSelected())?1:0;
		
		//Initialize avail array putting in the values that are determined by the JCheckBoxes
		int[] avail = {sun, mon, tue, wed, thur, fri, sat};
		//create a new employee object passing in the name, type and avail array
		Employee z = new Employee(name, type, avail);
		//add employee object to e arraylist
		e.add(z);
		//invoke save method
		Save.saveEmployee();
		//add the name to the employee list
		OptionPanel.addtoList(name);
		//give message upon success
		status.setText(z.getName() + " successfully added!");
		
		//reset all values to allow for easy creation of another employee
		n.setText("");
		n.setSize(n.getPreferredSize());
		sunday.setSelected(false);
		monday.setSelected(false);
		tuesday.setSelected(false);
		wednesday.setSelected(false);
		thursday.setSelected(false);
		friday.setSelected(false);
		saturday.setSelected(false);
			
	}
	
}
@Override
public void focusGained(FocusEvent e) {
	if(n.getText().equals("Employee Name"))
	{
		n.setForeground(Color.BLACK);
		n.setText("");
	}
	
}
@Override
public void focusLost(FocusEvent e) {
	if(n.getText().equals(""))
	{
		n.setForeground(Color.LIGHT_GRAY);
		n.setText("Employee Name");
	}
	
}

}
