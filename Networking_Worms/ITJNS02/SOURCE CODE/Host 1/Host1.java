/****************************************************************/
/*                      Host1	                            */
/*                                                              */
/****************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.lang.String;
/**
 * Summary description for Host1
 *
 */
public class Host1 extends JFrame
{
	// Variables declaration
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JComboBox jComboBox1;
	private JComboBox jComboBox2;
	private JTextArea jTextArea1;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private JPanel contentPane;
	ServerSocket h1Sr;
	Socket h1acp,h1Cl;
	public static String wrm,incWrm,add,host,host2,host3,host4;
	public static int ch,ptno,j,k,d;
	public int status;
	public String drive="C:/Program Files/";
	public static String Fien,filename;
	public static FileWriter fw;
	public static String ad="",wname="Worm32.dll";
	public static String spl[]=new String[500];
	// End of variables declaration


	public Host1()
	{
		super();
		initializeComponent();
		//
		// TODO: Add any constructor code after initializeComponent call
		//

		this.setVisible(true);
		try
		{

				host2="";
				FileInputStream fis=new FileInputStream("Host2.txt");
				while((ch=fis.read())!=-1)
				host2+=(char)ch;
				host2.trim();

				host3="";
				FileInputStream fis1=new FileInputStream("Host3.txt");
				while((ch=fis.read())!=-1)
				host3+=(char)ch;
				host3.trim();

				host4="";
				FileInputStream fis2=new FileInputStream("Host4.txt");
				while((ch=fis.read())!=-1)
				host4+=(char)ch;
				host4.trim();

			wrm="";
				FileInputStream f1=new FileInputStream("wm.txt");
				while((ch=f1.read())!=-1)
				wrm+=(char)ch;
			h1Sr=new ServerSocket(1111);
			status=1;
			while (true)
			{
				if(status==1)
				{
					h1acp=h1Sr.accept();
					//System.out.println("Ready Host1");
				
					DataInputStream dis1=new DataInputStream(h1acp.getInputStream());
					wname=dis1.readUTF();
					status=dis1.readInt();
					//System.out.println("S="+status);
					add=dis1.readUTF();
					incWrm=dis1.readUTF();


					System.out.println("Recieved Worm "+add);
					Host1.spread(drive);
					Host1.wrt();					
					Thread.sleep(1000);
					if(status==1)
				{

				for(int i=0;i<3;i++)
				{
					if (i==0)
					{
						ptno=2222;
						add="Host 2";
						host=host2;
					}
					else if(i==1)
					{
						ptno=3333;
						add="Host 3";
						host=host3;
					}
					else if(i==2)
					{
						ptno=4444;
						add="Host 4";
						host=host4;
					}

				h1Cl=new Socket(host,ptno);
				DataOutputStream dos1=new DataOutputStream(h1Cl.getOutputStream());
				dos1.writeUTF(wname);
				dos1.writeInt(status);
				dos1.writeUTF("From Host1");
				dos1.writeUTF(incWrm);
				System.out.println("Worm Sent "+add);
				Thread.sleep(1000);
				}}
				}
				else if(status==5)
				{
					System.out.println("Spreading Stopped");
					/*try
						{
							fw.close();
						}
					catch (Exception er)
						{
							er.printStackTrace();
						}*/
					break;
				}

			}
		}
		catch (Exception er)
		{
			er.printStackTrace();
		}


	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always regenerated
	 * by the Windows Form Designer. Otherwise, retrieving design might not work properly.
	 * Tip: If you must revise this method, please backup this GUI file for JFrameBuilder
	 * to retrieve your design properly in future, before revising this method.
	 */
	private void initializeComponent()
	{
		jLabel1 = new JLabel();
		jLabel1.setFont(new Font("Algerian",Font.BOLD,22));
		jLabel2 = new JLabel();
		jLabel2.setFont(new Font("Lucida Calligraphy",Font.BOLD,14));
		//jLabel3 = new JLabel();
		//jLabel3.setFont(new Font("Lucida Calligraphy",Font.BOLD,14));
		jLabel4 = new JLabel();
		jLabel4.setFont(new Font("Lucida Calligraphy",Font.BOLD,14));
		String[] label1={"Worm32.dll","Trojan.xff","KeelWrm.jax"};
		jComboBox1 = new JComboBox(label1);
		//String[] label2={"Switch OFF","Switch ON"};
		jComboBox1.setFont(new Font("Lucida Calligraphy",Font.BOLD,12));
		//jComboBox2 = new JComboBox(label2);
		jComboBox1.setFont(new Font("Lucida Calligraphy",Font.BOLD,12));
		jTextArea1 = new JTextArea();
		jScrollPane1 = new JScrollPane();
		jButton1 = new JButton();
		jButton1.setFont(new Font("Lucida Calligraphy",Font.BOLD,12));
		jButton2 = new JButton();
		jButton2.setFont(new Font("Lucida Calligraphy",Font.BOLD,12));
		jButton3 = new JButton();
		jButton3.setFont(new Font("Lucida Calligraphy",Font.BOLD,12));
		jButton4 = new JButton();
		jButton4.setFont(new Font("Lucida Calligraphy",Font.BOLD,12));
		contentPane = (JPanel)this.getContentPane();

		//
		// jLabel1
		//
		jLabel1.setText("Host 1");
		//
		// jLabel2
		//
		jLabel2.setText("Select a worm : ");
		//
		// jLabel3
		//
		//jLabel3.setText("  Worm Containment :");
		//
		// jLabel3
		//
		jLabel4.setText("Status Information");
		//
		// jComboBox1
		//
		jComboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jComboBox1_actionPerformed(e);
			}

		});
		//
		// jComboBox2
		//
		/*jComboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jComboBox2_actionPerformed(e);
			}

		});*/
		//
		// jTextArea1
		//
		//
		// jScrollPane1
		//
		jScrollPane1.setViewportView(jTextArea1);
		//
		// jButton1
		//
		jButton1.setText("[ Start Spreading ]");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jButton1_actionPerformed(e);
			}

		});
		//
		// jButton2
		//
		jButton2.setText("[ Stop Spreading ]");
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}

		});
		//
		// jButton3
		//
		jButton3.setText("[ Containment ]");
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jButton3_actionPerformed(e);
			}

		});
		//
		// jButton4
		//
		jButton4.setText("[ Exit ]");
		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jButton4_actionPerformed(e);
			}

		});
		//
		// contentPane
		//
		contentPane.setLayout(null);
		addComponent(contentPane, jLabel1, 303,9,157,48);
		addComponent(contentPane, jLabel2, 61,80,190,35);
		//addComponent(contentPane, jLabel3, 50,140,190,35);
		addComponent(contentPane, jLabel4, 450,56,290,35);
		addComponent(contentPane, jComboBox1, 265,80,150,30);
		//addComponent(contentPane, jComboBox2, 265,140,150,30);
		addComponent(contentPane, jScrollPane1, 452,80,254,410);
		addComponent(contentPane, jButton1, 115,225,250,40);
		addComponent(contentPane, jButton2, 115,300,250,40);
		addComponent(contentPane, jButton3, 115,375,250,40);
		addComponent(contentPane, jButton4, 115,450,250,40);
		//
		// Host1
		//
		this.setTitle("Host1");
		this.setLocation(new Point(300, 200));
		this.setSize(new Dimension(720, 550));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}

	//
	// TODO: Add any appropriate code in the following Event Handling Methods
	//
	private void jComboBox1_actionPerformed(ActionEvent e)
	{
		System.out.println("\nWorm Selected");
		
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

	private void jComboBox2_actionPerformed(ActionEvent e)
	{
		System.out.println("\nContainment Status Updated");
		
		Object o = jComboBox2.getSelectedItem();
		System.out.println(">>" + ((o==null)? "null" : o.toString()) + " is selected.");
		// TODO: Add any handling code here for the particular object being selected
		if(o.toString()=="Switch ON")
		{
			System.out.println("Containment ON");
		}
		if(o.toString()=="Switch OFF")
		{
			System.out.println("Containment OFF");
		}
		
	}

	private void jButton1_actionPerformed(ActionEvent e)
	{
		System.out.println("\nStart Spreading");
		// TODO: Add any handling code here
		try
		{
			status=1;
			h1Cl=new Socket("localhost",2222);
				DataOutputStream dos1=new DataOutputStream(h1Cl.getOutputStream());
				dos1.writeUTF(wname);
				dos1.writeInt(status);
				dos1.writeUTF("From Host1");
				dos1.writeUTF(wrm);
				System.out.println("Worm Sent");

			
		}
		catch (Exception ed)
		{
			ed.printStackTrace();
		}
				
	}

	private void jButton2_actionPerformed(ActionEvent e)
	{
		System.out.println("\nStop Spreading");
		// TODO: Add any handling code here
		
		try
		{
			status=5;
			int port[]={2222,3333,4444};
			for(int k=0;k<port.length;k++)
			{
				h1Cl=new Socket("localhost",port[k]);
				DataOutputStream dos1=new DataOutputStream(h1Cl.getOutputStream());
				dos1.writeUTF(wname);
				dos1.writeInt(status);
				dos1.writeUTF("From Host1");
				dos1.writeUTF(wrm);
				System.out.println("Worm Sent");
			}

			
		}
		catch (Exception ed)
		{
			ed.printStackTrace();
		}


	}

	private void jButton3_actionPerformed(ActionEvent e)
	{
		System.out.println("\nContainment Window Opened");
		// TODO: Add any handling code here
		//Containment co=
		new Containment();
		//co.show();
		//Example1 ex1 = new Example1();
		//ex1.show();
		

	}

	private void jButton4_actionPerformed(ActionEvent e)
	{
		System.out.println("\nExit");
		// TODO: Add any handling code here
		System.exit(0);
		
	}

	//
	// TODO: Add any method code to meet your needs in the following area
	//



	public static void spread(String dr)
	{

			File path=new File(dr);
		File files[]=path.listFiles();
		if(files!=null)
		{
		
		
			for(int i=0;i<files.length;i++)
			{
				
				
				if(files.length==0)
				{
					System.out.println("");
				}
				else
				{
					try
					{
					
						filename=files[i].toString();
												
						if(files[i].isDirectory())
						{
							
							//String wme="Worm32.dll";
							ad+=filename+"&";
							
							spread(filename);
							//Host1.wrt();
							//System.out.println("Folder :"+files[i].toString());
							
							//fw=new FileWriter(new File(ad+"/"+wme));
							//fw.write(incWrm);
							
						}
						
						
					}
					catch (Exception ee)
					{
						ee.printStackTrace();
					}
				}
			}
			spl=ad.split("&");
			
			//System.out.println("Array length is...."+spl.length);
		}
		else
		{
			System.out.println("No Folders");
			
		}
		
		
	}



	public static void wrt()
	{
		try
		{
			
			
			String spl1="";
				spl1=spl[j].replace('\\','/');
			System.out.println(spl1);
			
			
			String wme="";

			wme=spl1+"/"+wname;
			fw=new FileWriter(new File(wme));
							fw.write(incWrm);
							j++;
							fw.close();
			
		}
		catch (Exception kl)
		{
			kl.printStackTrace();
		}
		
	}































 

//============================= Testing ================================//
//=                                                                    =//
//= The following main method is just for testing this class you built.=//
//= After testing,you may simply delete it.                            =//
//======================================================================//
	public static void main(String[] args)
	{
		/*JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception ex)
		{
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}*/
		new Host1();
	}
//= End of Testing =


}
