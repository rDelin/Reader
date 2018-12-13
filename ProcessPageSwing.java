package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class ProcessPageSwing {
	public static void main(String[] args) {
		JFrame jf=new JFrame();
		jf.setLayout(null);
		jf.setSize(600,400);
		jf.setTitle("File Reader Application");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel desc=new JLabel();
		desc.setText("Select the directory containing both the .txt and .done files.");
		desc.setBounds(20, 10, 500, 20);
		jf.add(desc);
		JLabel desc2=new JLabel("Only files with .txt extension and matching .done file will be read.soeghwihrjrwojrwogjrwoigjweropgjweijgweio4gj");
		JLabel desc3=new JLabel("Ensure files are properly formatted");
		desc2.setBounds(20, 30, 500, 20);
		desc3.setBounds(20, 50, 500, 20);
		jf.add(desc2);
		jf.add(desc3);
		final JButton jb=new JButton("...");
		jb.setBounds(340, 100, 100, 20);
		final JTextField tf=new JTextField();
		tf.setBounds(120, 100, 200, 20);
		JLabel jl=new JLabel("Choose file:");
		jl.setBounds(20, 100, 100, 20);
		final JButton jb1=new JButton("Process");
		jb1.setBounds(175, 150, 100, 20);
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc=new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
				fc.setCurrentDirectory(new java.io.File("C:/Users/Hi/Desktop"));
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);
				if (fc.showOpenDialog(jb) == JFileChooser.APPROVE_OPTION) { 
					tf.setText(fc.getSelectedFile().getAbsolutePath());
				}
				else {
					tf.setText(null);
				}
			}
		});
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				test t=new test(tf.getText());
				try {
					t.read();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		jf.add(tf);
		jf.add(jb);
		jf.add(jb1);
		jf.add(jl);
		jf.setVisible(true);
	}
}
