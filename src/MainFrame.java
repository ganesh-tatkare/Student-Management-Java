//// javac -cp ojdbc14.jar *.java///
//// java -cp ojdbc14.jar .; MainFrame///

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class MainFrame extends JFrame
{
	Container c;
	JButton btnAdd, btnView, btnDelete,btnUpdate;


	MainFrame()
	{
		c=getContentPane();
		c.setLayout(new FlowLayout());

		btnAdd= new JButton("Add");
		btnView= new JButton("View");
		btnDelete= new JButton("Delete");
		btnUpdate= new JButton("Update");

		c.add(btnAdd);
		c.add(btnView);
		c.add(btnDelete);
		c.add(btnUpdate);

		//Add action
		ActionListener a1= (ae) ->
		{
			AddFrame a = new AddFrame();
			dispose();
		};
		btnAdd.addActionListener(a1);

		ActionListener a4= (ae) ->
		{
			UpdateFrame a = new UpdateFrame();
			dispose();
		};
		btnUpdate.addActionListener(a4);

 		ActionListener a3= (ae) ->
		{
			DeleteFrame a = new DeleteFrame();
			dispose();
		};
		btnDelete.addActionListener(a3);
 


		//view action
		ActionListener a2= (ae) ->
		{
			ViewFrame a = new ViewFrame();
			dispose();
		};
		btnView.addActionListener(a2);




		setTitle("Student System");
		setSize(700,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	} 


	public static void main(String args[])
	{
		MainFrame m= new MainFrame();
	}
}



class Student
{
	private int rno;
	private String name;
	private int unum;

	public Student(){}

	public Student(int rno, String name, int unum)
	{
		this.rno= rno;
		this.name= name;
		this.unum=unum;
	}

	public int getRno(){ return rno;}
	public String getName(){return name;}
	public int getunum(){return unum;}

	public void setName(int rno){this.rno = rno;}
	public void setName(String name){this.name = name;}
	public void setunum(int unum){this.unum=unum;}

}


class DbOperation
{
	public void addStudent(Student s)
	{

		StringBuffer sb= new StringBuffer();
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

			String s1="insert into student values(?,?)";
			PreparedStatement pst= con.prepareStatement(s1);

			int rno=s.getRno();
			String name=s.getName();
			pst.setInt(1,rno);
			pst.setString(2,name);
			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(),r+" records inserted");

			pst.close();
			con.close();
		}

		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(),"insert issue"+ e);
		}
	}


public void updateStudent(Student s)
	{
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			String s5="update student set rno=(?) , name=(?) where rno=(?) ";
			PreparedStatement pst= con.prepareStatement(s5);
			
			int rno=s.getRno();
			String name=s.getName();
			int unum=s.getunum();
			pst.setInt(1,rno);
			pst.setString(2,name);
			pst.setInt(3,unum);
			int r = pst.executeUpdate();
			if (r==0) {JOptionPane.showMessageDialog(new JDialog()," no such record");}
			else {JOptionPane.showMessageDialog(new JDialog(),r+" record updated");}

			pst.close();
			con.close();
		}

		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(),"update issue"+ e);
		}
	}



	public void deleteStudent(Student s)
	{
				StringBuffer sb= new StringBuffer();
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

			String s1="select * from student";
			Statement s6= con.createStatement();
			ResultSet rs= s6.executeQuery(s1);
			 if (rs.next() == false){ JOptionPane.showMessageDialog(null,"Table empty");}
			 
			 else
			 {
			 	String s2;                
             	int drno;  
             	 s2="DELETE FROM student where rno= ?";
              	PreparedStatement pst= con.prepareStatement(s2);			
				int rno=s.getRno();
				//String name=s.getName();
				pst.setInt(1,rno);
				//pst.setString(2,name);
				int r = pst.executeUpdate();	
				if (r==0) {JOptionPane.showMessageDialog(new JDialog()," no such record");}
				else {JOptionPane.showMessageDialog(new JDialog(),r +" record deleted");}

				pst.close();
				con.close();
			 }	
                       
		}

		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(),"record not found"+ e);
		}
	}





	public String viewStudent()
	{
		StringBuffer sb= new StringBuffer();
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

			String s1="select * from student";
			Statement s2= con.createStatement();

			ResultSet rs= s2.executeQuery(s1);
			sb.append("  Rno    Name \n");
			while(rs.next())
			{
				int rno= rs.getInt(1);
				String name=rs.getString(2);
				sb.append(rno+"     "+name+"\n");

			}	

			rs.close();
			s2.close();
			con.close();

		}

		catch(SQLException e)
		{
			System.out.println(e);
		}

		return sb.toString();
	}	
}