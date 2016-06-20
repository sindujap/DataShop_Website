import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;

class WormScanner extends JPanel 
	{
		JProgressBar progressBar = new JProgressBar();
		static int NUMLOOPS = 100;
		JLabel statusField = new JLabel("Click Start to begin", JLabel.CENTER);
		SwingWorker worker;
		public JLabel jLabel1;
		public static JLabel jLabel2;
		//public JComboBox jComboBox1;
		public static JTextArea jTextArea1;
		public JScrollPane jScrollPane1;
		public static JTextArea jTextArea2;
		public JScrollPane jScrollPane2;
		public static JTextArea jTextArea3;
		public JScrollPane jScrollPane3;
		public JProgressBar jProgressBar1;
		public JButton startButton;
		public JPanel contentPane;
		public ImageIcon img;
		public static int count=0;
		public static int count2=0;
		public static int count3=0;
		public String addr[]={"C:/Program Files/"};
		public static String filename;
		public static long time,st,et;
		public static double ti;
		public static String wname="";
		
		

		JButton getStartButton() 
		{
			return startButton;
		}

    /**
     * When the worker needs to update the GUI we do so by queuing
     * a Runnable for the event dispatching thread with 
     * SwingUtilities.invokeLater().  In this case we're just
     * changing the progress bars value.
     */
    void updateStatus(final int i) 
		{
			Runnable doSetProgressBarValue = new Runnable() 
			{
				public void run() 
				{
					progressBar.setValue(i);
				}
			};
        SwingUtilities.invokeLater(doSetProgressBarValue);
		}

    
    /**
     * This method represents the application code that we'd like to 
     * run on a separate thread.  It simulates slowly computing 
     * a value, in this case just a string 'All Done'.  It updates the 
     * progress bar every half second to remind the user that
     * we're still busy.
     */
    Object doWork() 
		{
			try 
				{
					for(int i = 0; i < 1; i++) 
					{
						updateStatus(i);
						{
							
							System.out.println("\nScanning Started.");							
							
							
							try
							{
								for (int h=0;h<addr.length ;h++ )
								{
									WormScanner.listfile(addr[h]);
								}
								

							}
							catch (Exception dr)
							{
								dr.printStackTrace();
							}
							

						}

						if (Thread.interrupted()) 
						{
							throw new InterruptedException();
						}
						Thread.sleep(500);
					}
				}
			catch (InterruptedException e) 
				{
					updateStatus(0);
					return "Interrupted";  // SwingWorker.get() returns this
				}
				return "All Done";         // or this
		}


    /**
     * This action listener, called by the "Start" button, effectively 
     * forks the thread that does the work.
     */
    ActionListener startListener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				startButton.setEnabled(false);				
				

            /* Invoking start() on the SwingWorker causes a new Thread
             * to be created that will call construct(), and then
             * finished().  Note that finished() is called even if
             * the worker is interrupted because we catch the
             * InterruptedException in doWork().
             */
				worker = new SwingWorker() 
				{
					public Object construct() 
						{
							return doWork();
						}
					public void finished() 
					{
						startButton.setEnabled(true);						
						statusField.setText(get().toString());
					}
				};
            worker.start();
        }
    };

	


	public static void listfile(String drive) throws IOException
	{
		//File f=new File(path);
		//path.list
		//
		//System.out.println(count);
		st=System.currentTimeMillis();
		System.out.println("Scanning Started...");
		File path=new File(drive);
		File files[]=path.listFiles();
		if(files!=null)
		{
		
		
			for(int i=0;i<files.length;i++)
			{
				
				count2++;
				//jTextArea3.setText("Scanned Files : "+count2);
				if(files.length==0)
				{
					System.out.println("");
				}
				else
				{
//					System.out.println("compiling");
					try
					{
					
						filename=files[i].toString();
						//System.out.println(filename);
						//Thread.sleep(1000);
						System.out.println("\nScanning File : "+filename);
						     
						jTextArea1.setText("Status : Scanning File\nLast Object :\n  "
																+files[i].toString());
						Thread.sleep(1);
						if(filename.endsWith(wname))
						{
							
							count++;
							jTextArea2.append("\nDetected Worm :"+files[i].toString());
							files[i].delete();
							
							//Thread.sleep(1000);
							
							
						}
						if(files[i].isDirectory())
						{
							count3++;									
							listfile(files[i].toString());
				
						}
						
						et=System.currentTimeMillis();
						time=(et-st)/1000;
						ti+=(double)time;
						
						jTextArea3.setText("\nScanned Files   "+count2+"\n\nScanned Folders  "
						+count3+"\n\nDetections      "+count+"\n\nTime Elapsed  "+ti);		
					}
					catch (Exception ee)
					{
						ee.printStackTrace();
					}
				}
			}
		}
		else
		{
			jTextArea1.setText("Status : Scanning Completed\nLast Object :\n  "+filename);
			
		}
	}
	/*ActionListener sd = new ActionListener() 
	{

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("\njComboBox1_actionPerformed(ActionEvent e) called.");
		
		Object o = jComboBox1.getSelectedItem();
		System.out.println(">>" + ((o==null)? "null" : o.toString()) + " is selected.");
		// TODO: Add any handling code here for the particular object being selected
		if(o.toString()=="Worm32.dll")
		{
			System.out.println("Worm32.dll  df");
			wname="Worm32.dll";
			
		}
		else if(o.toString()=="Trojan.xff")
		{
			System.out.println("Trojan.xff  df");
			wname="Trojan.xff";
			
		}
		else //(o.toString()=="KeelWrm.jax");
		{
			System.out.println("KeelWrm.jax  df");
			wname="KeelWrm.jax";
			
		}
		
	 }
	};*/

    
    WormScanner() 
		{	 
		wname="Worm32.dll";

		this.setLayout(null);
		
		
		img=new ImageIcon("Logo1.gif");
		
		jLabel1 = new JLabel(img);
		jLabel1.setBounds(1,0,765,190);

		jLabel2 = new JLabel();
		jLabel2.setFont(new Font("Lucida Calligraphy",Font.BOLD,14));
		jLabel2.setForeground(new Color(0, 0, 204));
		jLabel2.setText("  Worm Name : ");
		jLabel2.setBounds(65,238,158,34);
		
		//String[] label1={"Worm32.dll","Trojan.xff","Keel/Wrm.jax"};
		//jComboBox1 = new JComboBox(label1);
		//jComboBox1.setForeground(new Color(0, 0, 204));
		//jComboBox1.setBounds(249,241,164,31);
		
		//jComboBox1.addActionListener(sd);
			

		jTextArea1 = new JTextArea();
		jTextArea1.setFont(new Font("Times New Roman",Font.BOLD,14));
		jScrollPane1 = new JScrollPane(jTextArea1);
		jScrollPane1.setBounds(30,306,700,100);

		jTextArea2 = new JTextArea();
		jTextArea2.setFont(new Font("Times New Roman",Font.BOLD,14));
		jScrollPane2 = new JScrollPane(jTextArea2);
		jScrollPane2.setBounds(89,468,251,179);


		jTextArea3 = new JTextArea();
		jTextArea3.setFont(new Font("Times New Roman",Font.BOLD,14));
		jScrollPane3 = new JScrollPane(jTextArea3);
		jScrollPane3.setBounds(421,464,247,185);


		jProgressBar1 = new JProgressBar();
		jProgressBar1.setBounds(64,414,630,22);

		
		startButton = new JButton("Start Scan");		
		startButton.addActionListener(startListener);
		startButton.setFont(new Font("Lucida Calligraphy",Font.BOLD,14));
		startButton.setForeground(new Color(0, 0, 204));
		startButton.setBounds(456,236,202,40);




		this.add(startButton);
		this.add(jLabel1);
		this.add(jLabel2);
		//this.add(jComboBox1);
		this.add(jScrollPane1);
		this.add(jScrollPane2);
		this.add(jScrollPane3);
		this.add(startButton);
		this.add(jProgressBar1);
		

		this.setVisible(true);
		this.setBackground(new Color(72, 82, 91));		
	
    }

  
}

