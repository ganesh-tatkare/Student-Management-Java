import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteFrame extends JFrame
{
	Container c;
	JLabel lblDeleteRno;
	JTextField txtDeleteRno;
	JButton btnDelete, btnDeleteBack;

	DeleteFrame()
	{
		c=getContentPane();
		c.setLayout(new FlowLayout());
		lblDeleteRno= new JLabel("Enter Roll No");
		txtDeleteRno= new JTextField(20);
		
		btnDelete= new JButton("Delete");
		btnDeleteBack= new JButton("Back");

		c.add(lblDeleteRno);
		c.add(txtDeleteRno);
		
		c.add(btnDelete);
		c.add(btnDeleteBack);



		ActionListener a0= (ae)->
		{
			MainFrame a= new MainFrame();
			dispose();
		};
		btnDeleteBack.addActionListener(a0);


		ActionListener a1= (ae)->
		{
		    String rno= txtDeleteRno.getText();
		    String name= "";
		    int unum=0;		
		    boolean isname= true;
			isname  = rno.matches("-?\\d+(\\.\\d+)?");			


		    if (rno.equals("")){
                JOptionPane.showMessageDialog(null,"Roll number cannot be empty");  }

			else if(!isname){JOptionPane.showMessageDialog(null,"Number cannot be alphabet"); txtDeleteRno.setText("");}

			else if(Integer.parseInt(rno)<0) {JOptionPane.showMessageDialog(null,"number cannot be negative"); txtDeleteRno.setText("");}

            else
            {
			    Student s= new Student(Integer.parseInt(rno),name,unum);
				DbOperation db= new DbOperation();
				db.deleteStudent(s);
				
			txtDeleteRno.setText("");
			}	

		};
		btnDelete.addActionListener(a1);


		setTitle("Delete Student");
		setSize(600,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}}