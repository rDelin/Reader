package core;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class LastModifiedComparator implements Comparator<File> {

	@Override
	public int compare(File a, File b) {
		// TODO Auto-generated method stub
		SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy HH-mm-ss-SS");
		df.setLenient(false);
		try
		{
		String da=df.format(a.lastModified());
		Date dadate=df.parse(da);
		String db=df.format(a.lastModified());
		Date dbdate=df.parse(db);
		if(dadate.compareTo(dbdate)>0)
			return 1;
		else
			return 0;
		
		}catch(ParseException e)
		{
			e.printStackTrace();
		}
		
			
		return 0;
	}

}
