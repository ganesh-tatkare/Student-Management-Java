import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class UpdateFrame extends JFrame
{
	Container c;
	JLabel lblnum,lblUpName, lblUpRno;
	JTextField txtnum, txtUpName,txtUpRno;
	JButton btnUpSave, btnUpBack;


	UpdateFrame()
	{
		c=getContentPane();
		c.setLayout(new FlowLayout());
		lblnum= new JLabel("Enter number of student to be updated ");
		txtnum= new JTextField(10);
		lblUpName= new JLabel("enter name");
		txtUpName= new JTextField(10);
		lblUpRno= new JLabel("Enter roll number");
		txtUpRno= new JTextField(10);
		btnUpSave= new JButton("Save");
		btnUpBack= new JButton("Back");


		c.add(lblnum);
		c.add(txtnum);
		c.add(lblUpName);
		c.add(txtUpName);
		c.add(lblUpRno);
		c.add(txtUpRno);
		c.add(btnUpSave);
		c.add(btnUpBack);


		ActionListener a1=(ae)->
		{
			String rno= txtUpRno.getText();
			String name= txtUpName.getText();
			String unum= txtnum.getText();
			boolean numeric = true;
			boolean isname= true;
			numeric = name.matches("-?\\d+(\\.\\d+)?");
			isname  = rno.matches("-?\\d+(\\.\\d+)?");

			 if (unum.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter number to be updated ");}

			 else if (rno.equals("")){
                JOptionPane.showMessageDialog(null,"Roll number cannot be empty ");}

            else if (name.equals("")){
                JOptionPane.showMessageDialog(null,"Name cannot be empty");}

            else if(!isname){JOptionPane.showMessageDialog(null,"Number cannot be alphabet");  txtUpRno.setText("");}
            else if(numeric){JOptionPane.showMessageDialog(null,"Name cannot be numeric");     txtUpName.setText("");}
            else if(Integer.parseInt(rno)<0){JOptionPane.showMessageDialog(null,"number cannot be negative");txtUpRno.setText("");}
            else if (Integer.parseInt(unum)<0) {JOptionPane.showMessageDialog(null,"number cannot be negative");}

             else
             {

				Student s= new Student(Integer.parseInt(rno),name,Integer.parseInt(unum));
				DbOperation db= new DbOperation();
				db.updateStudent(s);
			 txtnum.setText("");
			 txtUpName.setText("");
			 txtUpRno.setText("");
			 }	


		};
		btnUpSave.addActionListener(a1);


		ActionListener a2= (ae)->
		{
			MainFrame a= new MainFrame();
			dispose();
		};
		btnUpBack.addActionListener(a2);


		setTitle("Update Student");
		setSize(600,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);


	}
}