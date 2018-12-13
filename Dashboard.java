package core;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dashboard {
public Dashboard(ArrayList<DashboardData> list) {
	// TODO Auto-generated constructor stub
	JFrame jf=new JFrame("Result");
	jf.setLayout(null);
	jf.setSize(600,400);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	int xcord=300;
	int ycord=20;
	int xsize=300;
	int ysize=20;
	JLabel main=new JLabel("Number of files read:"+list.size());
	main.setBounds(xcord,ycord,xsize,ysize);
	xcord=100;
	ycord=ycord+30;
	int rejectedfilesycord=70;
	int totalverified=0;
	int totalrejected=0;
	for(DashboardData x:list)
	{
		JLabel filename=new JLabel("File name:"+x.f.getAbsolutePath());
		filename.setBounds(xcord,ycord,700,ysize);
		jf.add(filename);
		JLabel rejrec=new JLabel("Rejected records:"+x.rejectedrecords);
		rejrec.setBounds(xcord, rejectedfilesycord,200, ysize);
		jf.add(rejrec);
		JLabel verifiedrec=new JLabel("Accepted records:"+x.verifiedrecords);
		verifiedrec.setBounds(xcord+250, rejectedfilesycord, 200, ysize);
		jf.add(verifiedrec);
		rejectedfilesycord+=70;
		ycord+=70;
		totalrejected=totalrejected+x.rejectedrecords;
		totalverified=totalverified+x.verifiedrecords;
	}
	JLabel summary=new JLabel("Total");
	summary.setBounds(300, ycord, 100, 20);
	jf.add(summary);
	JLabel totalrej=new JLabel("Rejected records:"+totalrejected);
	totalrej.setBounds(100, ycord+20, 200, ysize);
	jf.add(totalrej);
	JLabel totalver=new JLabel("Verified records:"+totalverified);
	totalver.setBounds(350, ycord+20, 200, ysize);
	jf.add(totalver);
	jf.add(main);
	jf.setVisible(true);
}
}
