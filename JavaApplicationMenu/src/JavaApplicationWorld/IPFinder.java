package JavaApplicationWorld;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;  
import java.net.*;  


public class IPFinder extends JFrame implements ActionListener
{  
    
    JLabel x;  
    JTextField y;  
    JButton z;  
    IPFinder()
    {  
        super("IP FINDER");  
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0)
        {
             
            JButton button_2 = new JButton("");
            button_2.setVisible(true);
            dispose();
        }
        });
        btnBack.setFont(new Font("Thoma",Font.PLAIN,16));
        btnBack.setBounds(2,5,80,30);
        btnBack.setBackground(Color.CYAN);
        btnBack.setForeground(Color.RED);
        add(btnBack);

        x = new JLabel("Enter Url:");  
        x.setBounds(50,70,150,20); 
        x.setBackground(Color.cyan);
        x.setForeground(Color.MAGENTA);  
			
        y = new JTextField();  
        y.setBounds(50,100,200,20);
        y.setBackground(Color.GREEN);  
        y.setForeground(Color.black);    

        z = new JButton("Find IP");  
        z.setBounds(50,150,80,30); 
        z.setBackground(Color.MAGENTA);  
        z.setForeground(Color.black);

        z.addActionListener(this);  
        add(x);  
        add(y);  
        add(z);  
        setSize(350,350);  
        setLayout(null);  
        setVisible(true);  
    }  
    public void actionPerformed(ActionEvent e)
    {  
        String url = y.getText();  
        try 
        {  
		        
            InetAddress EnterAddress = InetAddress.getByName(url); 	 	        	
            String ip = EnterAddress.getHostAddress();  	
            JOptionPane.showMessageDialog(this,ip); 
		        	 
	}catch(UnknownHostException e1) 
	{  
	    JOptionPane.showMessageDialog(this,e1.toString());  
        }  
    } 
       
    public static void main(String[] args) 
    {  
	new IPFinder();  
    } 
}  