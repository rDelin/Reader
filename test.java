package core;
//import java.util.*;
import java.io.*;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class test {
	File f1;
	test(String a)
	{
		f1=new File(a);
	}
	static boolean isConnected(File a,List<String> b) throws Exception
	{
		try
		{
			FileReader fr=new FileReader(a);
			fr.close();
		}catch(Exception e)
		{
		
			String path=a.getAbsolutePath();
			path=path.substring(0, path.lastIndexOf("\\"));
			path=path+"/AbortData.txt";
			File abortdata=new File(path);
			FileWriter fw=new FileWriter(abortdata);
			BufferedWriter br=new BufferedWriter(fw);
			for(int i=0;i<b.size();i++)
				br.write(b.get(i));
			br.flush();
			br.close();
			return false;
		}
		return true;
	}
	static boolean fileVerify(File a)
	{
		try
		{
		String filename=a.getName().substring(0,a.getName().lastIndexOf("."));
		String[] filenamear=filename.split("_");
		if(filenamear.length!=2)
		{
			return false;
		}
		if(filenamear[0].compareTo("input")!=0&&(filenamear[1].matches("[0-9]+"))!=true)
		{
			return false;
		}
		}catch(Exception e)
		{
			return false;
		}

		return true;
	}

	static boolean isdone(File f,File[] list)
	{
		String compname=f.getName().substring(0,f.getName().lastIndexOf("."));
		for(File x:list)
		{
			if(!(x.getName().endsWith(".txt")))
			{
				String name=x.getName().substring(0, x.getName().lastIndexOf("."));
				if(name.compareTo(compname)==0)
					return true;			
			}
		}
		return false;
	}
	void indexerror(List<String> a,String filename) throws Exception
	{
		String pathname=f1.getAbsolutePath();
		StringBuilder name=new StringBuilder(pathname);
		name.append("/Rejected files");
		File rejectedfiles=new File(name.toString());
		rejectedfiles.mkdir();
		File indexerrorfile=new File(rejectedfiles.getAbsolutePath());
		indexerrorfile.createNewFile();
		
	}
	void read() throws Exception
	{
		ArrayList<DashboardData> dashstuff=new ArrayList<>();
		File[] list=f1.listFiles();
		ArrayList<File> donefiles=new ArrayList<>();
		
		for(File x:list)
		{
			if(x.isFile()&&x.getName().endsWith(".txt"))
			{
				if(isdone(x,list)&&fileVerify(x))
				{
					donefiles.add(x);
				}
			}
		}
		Collections.sort(donefiles,new LastModifiedComparator());
		//System.out.println(donefiles);
		for(File x:donefiles)
		{
			
			int threadno=0;
			ArrayList<List<String>> inputlist1=new ArrayList<>();
			ArrayList<List<String>> inputlist2=new ArrayList<>();
			ArrayList<List<String>> inputlist3=new ArrayList<>();
			ArrayList<List<String>> inputlist4=new ArrayList<>();
			ArrayList<List<String>> inputlist5=new ArrayList<>();
			String line;
			FileReader fr=new FileReader(x);
			BufferedReader br=new BufferedReader(fr);
			while((line=br.readLine())!=null)
			{
				if(threadno==5)
					threadno=0;
				List<String> curline=CSVUtils.parseLine(line);
				
				if(threadno==0)
					inputlist1.add(curline);
				else if(threadno==1)
					inputlist2.add(curline);
				else if(threadno==2)
					inputlist3.add(curline);
				else if(threadno==3)
					inputlist4.add(curline);
				else
					inputlist5.add(curline);
				threadno++;
				
			}
			br.close();
			ValidationThread one=new ValidationThread(inputlist1,Connect.doconnection(),x);
			ValidationThread two=new ValidationThread(inputlist2,Connect.doconnection(),x);
			ValidationThread three=new ValidationThread(inputlist3,Connect.doconnection(),x);
			ValidationThread four=new ValidationThread(inputlist4, Connect.doconnection(), x);
			ValidationThread five=new ValidationThread(inputlist5, Connect.doconnection(), x);
			Thread t1=new Thread(one);
			Thread t2=new Thread(two);
			Thread t3=new Thread(three);
			Thread t4=new Thread(four);
			Thread t5=new Thread(five);
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
			DashboardData d=new DashboardData();
			
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			d.f=x;
			d.rejectedrecords=one.getrejectedrecords()+two.getrejectedrecords()+three.getrejectedrecords()+four.getrejectedrecords()+five.getrejectedrecords();
			d.verifiedrecords=one.getverifiedrecords()+two.getverifiedrecords()+three.getverifiedrecords()+four.getverifiedrecords()+five.getverifiedrecords();
			dashstuff.add(d);
			
		}
		new Dashboard(dashstuff);
	}


}
