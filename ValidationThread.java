package core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValidationThread implements Runnable {

	private ArrayList<List<String>> input=new ArrayList<>();
	Connection conn;
	int verifiedrecords;
	int rejectedrecords;
	File f;
	public ValidationThread(ArrayList<List<String>> a,Connection con,File x) {
		// TODO Auto-generated constructor stub
		this.input=a;
		this.conn=con;
		this.verifiedrecords=0;
		this.rejectedrecords=0;
		this.f=x;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(List<String> m:input)
		{
			if((m.get(0).compareTo(""))!=0&&m.size()==5)
			{
				verifiedrecords++;
				try {
					PreparedStatement ps=conn.prepareStatement("insert into testtable values(?,?,?,?,?)");
					for(int i=0;i<5;i++)
					{
						ps.setString(i+1, m.get(i));
					}
					ps.executeUpdate();
					ps.close();
					//conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else
			{
				rejectedrecords++;
				try {
					if((m.get(0).compareTo(""))==0)
					error(m,f,1);
					else
						error(m,f,2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void error(List<String> a,File filename,int errorcode) throws Exception
	{
		//String[] strar;
		String filenamepath=filename.getAbsolutePath();
		//System.out.println(filenamepath);
		//System.out.println(filenamepath.lastIndexOf("\\"));
		filenamepath=filenamepath.substring(0,filenamepath.lastIndexOf("\\"));
		StringBuilder errordata1= new StringBuilder(filenamepath);
		errordata1.append("/errordata.txt");
		String errordatafilename=errordata1.toString();
		File errordata=new File(errordatafilename);
		//File errordata=new File("C:/Users/Hi/Desktop/Errordata.txt");
		FileWriter fr=new FileWriter(errordata,true);
		
		BufferedWriter br=new BufferedWriter(fr);
		br.write("File name:"+filename.getName()+" Record name:");
		for(int x=0;x<a.size();x++)
			br.write(a.get(x)+",");
		if(errorcode==1)
		br.write(" Reason:Mandatory field is missing");
		else
			br.write(" Reason:Inappropriate length");
		br.newLine();
		br.flush();
		br.close();
	}
	public int getverifiedrecords()
	{
		return verifiedrecords;
	}
	public int getrejectedrecords()
	{
		return rejectedrecords;
	}

}
