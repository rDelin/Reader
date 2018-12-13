package core;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<File>
{

	@Override
	public int compare(File arg0, File arg1) {
		// TODO Auto-generated method stub
		{
			String firstname=arg0.getName().substring(0,arg0.getName().lastIndexOf("."));
			String secondname=arg1.getName().substring(0,arg1.getName().lastIndexOf("."));
			String[] firstnamear=firstname.split("%");
			String[] secondnamear=secondname.split("%");
			SimpleDateFormat df1=new SimpleDateFormat("dd-MM-yyyy");
			df1.setLenient(false);
			SimpleDateFormat tf1=new SimpleDateFormat("HH-mm-ss");
			tf1.setLenient(false);
			try {
				Date d1=df1.parse(firstnamear[1]);
				Date d2=df1.parse(secondnamear[1]);
				Date t1=tf1.parse(firstnamear[2]);
				Date t2=tf1.parse(secondnamear[2]);
				if(d1.compareTo(d2)>0)
					return 1;
				else if(d1.compareTo(d2)<0)
					return 0;
				else
				{
					if(t1.compareTo(t2)>0)
						return 1;
					else
						return 0;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return 0;
	}

}
