import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class AddFrame extends JFrame
{
	Container c;
	JLabel lblAddRno, lblAddName;
	JTextField txtAddRno, txtAddName;
	JButton btnAddSave, btnAddBack;


	AddFrame()
	{
		c=getContentPane();
		c.setLayout(new FlowLayout());
		lblAddRno= new JLabel("enter rno ");
		txtAddRno= new JTextField(10);
		lblAddName= new JLabel("enter name");
		txtAddName= new JTextField(20);
		btnAddSave= new JButton("Save");
		btnAddBack= new JButton("Back");


		c.add(lblAddRno);
		c.add(txtAddRno);
		c.add(lblAddName);
		c.add(txtAddName);
		c.add(btnAddSave);
		c.add(btnAddBack);


		ActionListener a1=(ae)->
		{
			String rno= txtAddRno.getText();
			String name= txtAddName.getText();
			int unum=0;
			boolean numeric = true;
			boolean isname= true;
			numeric = name.matches("-?\\d+(\\.\\d+)?");
			isname  = rno.matches("-?\\d+(\\.\\d+)?");
	
			if (rno.equals("")){ JOptionPane.showMessageDialog(null,"Roll number cannot be empty"); }
	 		else if (name.equals("")) { JOptionPane.showMessageDialog(null,"Name cannot be empty");}
             else if(!isname){JOptionPane.showMessageDialog(null,"Number cannot be alphabet");  txtAddRno.setText("");}
             else if(numeric){JOptionPane.showMessageDialog(null,"Name cannot be numeric");     txtAddName.setText("");}
            else if(Integer.parseInt(rno)<0){JOptionPane.showMessageDialog(null,"number cannot be negative"); txtAddRno.setText("");}
			else if (name!= null && rno!=null){ 
             	
				Student s= new Student(Integer.parseInt(rno),name,unum);
				DbOperation db= new DbOperation();
				db.addStudent(s);

			 //clear
			 txtAddRno.setText("");
			 txtAddName.setText("");
			 }

			
		};
		btnAddSave.addActionListener(a1);


		ActionListener a2= (ae)->
		{
			MainFrame a= new MainFrame();
			dispose();
		};
		btnAddBack.addActionListener(a2);


		setTitle("Add Student");
		setSize(600,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);


	}
}