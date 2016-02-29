import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfilePanel extends JPanel{
//class variables
private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
private GridBagConstraints gbc = new GridBagConstraints();
private static ArrayList<Employee> e;
private static int[] avail;

private static JLabel name;
private String dotw;
private JLabel s, m, t, w, th, f, sa;
private static JCheckBox[] av;

public ProfilePanel()
{
	this.setBackground(Color.WHITE);
	setPreferredSize(new Dimension(d.width/10, d.height/10));
	//assign return value to e
	e = Load.getEmp();
	//initalize components
	name = new JLabel("");
	s = new JLabel("Sun");
	m = new JLabel("Mon");
	t = new JLabel("Tue");
	w = new JLabel("Wed");
	th = new JLabel("Thu");
	f = new JLabel("Fri");
	sa = new JLabel("Sat");
	av = new JCheckBox[7];
	//interate through JCheckBoxes and intialize and set to disabled
	for(int i = 0; i < av.length; i++)
	{
		av[i] = new JCheckBox();
		av[i].setEnabled(false);
	}
	//position components
	setLayout(new GridBagLayout());
	
	gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.insets = new Insets(0, 0, 10, 0);
	gbc.gridwidth = 7;
	add(name, gbc);
	
	gbc.gridx = 0;
	gbc.gridy = 1;
	gbc.gridwidth = 1;
	add(av[0], gbc);
	
	gbc.gridx = 1;
	gbc.gridy = 1;
	add(av[1], gbc);
	
	gbc.gridx = 2;
	gbc.gridy = 1;
	add(av[2], gbc);
	
	gbc.gridx = 3;
	gbc.gridy = 1;
	add(av[3], gbc);
	
	gbc.gridx = 4;
	gbc.gridy = 1;
	add(av[4], gbc);
	
	gbc.gridx = 5;
	gbc.gridy = 1;
	add(av[5], gbc);
	
	gbc.gridx = 6;
	gbc.gridy = 1;
	add(av[6], gbc);
	
	gbc.gridx = 0;
	gbc.gridy = 2;
	add(s, gbc);
	
	gbc.gridx = 1;
	gbc.gridy = 2;
	add(m, gbc);
	
	gbc.gridx = 2;
	gbc.gridy = 2;
	add(t, gbc);
	
	gbc.gridx = 3;
	gbc.gridy = 2;
	add(w, gbc);
	
	gbc.gridx = 4;
	gbc.gridy = 2;
	add(th, gbc);
	
	gbc.gridx = 5;
	gbc.gridy = 2;
	add(f, gbc);
	
	gbc.gridx = 6;
	gbc.gridy = 2;
	add(sa, gbc);
	
	//format the date to only show day of the week
	SimpleDateFormat fo = new SimpleDateFormat("E");
	//assign string to dotw
	dotw = fo.format(new Date());
	
	//switch that adds green border to the current day of the week
	switch(dotw)
	{
	case "Sun":
		s.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GREEN));
		break;
		
	case "Mon":
		m.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GREEN));
		break;
		
	case "Tue":
		t.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GREEN));
		break;
		
	case "Wed":
		w.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GREEN));
		break;
		
	case "Thu":
		th.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GREEN));
		break;
		
	case "Fri":
		f.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GREEN));
		break;
		
	case "Sat":
		sa.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GREEN));
		break;
	}
	
}
//method that changes what the appearance of the employee profile
public static void setProfile(String n)
{
	name.setText(n);
	
	for(Employee emp : e)
	{
		if(emp.getName().equals(n))
		{
			avail = emp.getAvailability();
		}
	}
	
	for(int i = 0; i < avail.length; i++)
	{
		if(avail[i] == 1)
		{
			av[i].setSelected(true);
		}
		else
		{
			av[i].setSelected(false);
		}
	}
}

}
