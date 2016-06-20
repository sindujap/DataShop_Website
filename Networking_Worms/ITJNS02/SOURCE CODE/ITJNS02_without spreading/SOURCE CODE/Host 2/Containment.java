import java.awt.*;
import java.awt.event.*;
import java.io.File;
//import com.sun.java.swing.*;  //old package name
import javax.swing.*;           //new package name

public class Containment extends WormScanner
	{
		public static WormScanner ws;

	 public Containment()
	{
		 JFrame f = new JFrame("Containment");	

        Container contentPane = f.getContentPane();
        ws = new WormScanner();
        contentPane.setLayout(new GridLayout(1, 0));
        contentPane.add(ws);
		f.setPreferredSize(new Dimension(774, 754));      
        f.pack();
        f.show();
	 }
		public static void main(String[] args) 
			{
				
			

        SwingUtilities.invokeLater(new Runnable() 
			{
				public void run() 
				{
					ws.getStartButton().requestFocus(); 
				}
			});
		}	
	}

