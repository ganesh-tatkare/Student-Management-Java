import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class ViewFrame extends JFrame
{
	Container c;
	TextArea taData;
	JButton btnViewBack;


	ViewFrame()
	{
		c=getContentPane();
		c.setLayout(new FlowLayout());

		taData= new TextArea(10,30);
		btnViewBack= new JButton("Back");

		c.add(taData);
		c.add(btnViewBack);


		ActionListener a1= (ae)->
		{
			MainFrame a= new MainFrame();
			dispose();
		};
		btnViewBack.addActionListener(a1);

		DbOperation db= new DbOperation();
		String data=db.viewStudent();
		taData.setText(data);

		setTitle("View Student");
		setSize(600,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}