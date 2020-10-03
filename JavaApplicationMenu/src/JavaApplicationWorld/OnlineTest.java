package JavaApplicationWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnlineTest extends JFrame implements ActionListener
{
    JLabel lb;
    JRadioButton rb[]=new JRadioButton[5];
    ButtonGroup btn;
    JButton b1,b2;
    int cnt=0,current=0,h=1,v=1,p=0;
    int m[]=new int[10];	
    OnlineTest(String s)
    {
        
        super(s);
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0)
        {
             
            JButton button_3 = new JButton("");
            button_3.setVisible(true);
            dispose();
        }
        });
        btnBack.setFont(new Font("Thoma",Font.PLAIN,16));
        btnBack.setBounds(2,5,80,30);
        btnBack.setBackground(Color.CYAN);
        btnBack.setForeground(Color.RED);
        add(btnBack);
        
        
        lb=new JLabel();
        add(lb);
        btn=new ButtonGroup();
        for(int i=0;i<5;i++)
        {
            rb[i]=new JRadioButton();	
            add(rb[i]);
            btn.add(rb[i]);
        }
        b1=new JButton("Next");
        b2=new JButton("BOOKMARK");
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(b1);
        add(b2);
        set();
        lb.setBounds(30,40,450,20);
        lb.setBackground(Color.orange);
        lb.setForeground(Color.magenta);

        rb[0].setBounds(50,80,100,20);
        rb[1].setBounds(50,110,100,20);
        rb[2].setBounds(50,140,100,20);
        rb[3].setBounds(50,170,100,20);

        b1.setBounds(40,240,100,30);
        b1.setBackground(Color.GREEN);
        b1.setForeground(Color.BLACK);
        b2.setBounds(240,240,100,30);
        b2.setBackground(Color.RED);
        b2.setForeground(Color.black);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(3,3,500,500);
        setVisible(true);
        
       
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            if(check())
                cnt=cnt+1;
            current++;
            set();	
            if(current==9)
            {
                b1.setEnabled(false);
                b2.setText("Result");
            }
        }
        if(e.getActionCommand().equals("BOOKMARK"))
        {
            JButton bk=new JButton("BOOKMARK"+h);
            bk.setBounds(380,15+30*h,100,20);
            add(bk);
            bk.addActionListener(this);
            m[h]=current;
            h++;
            current++;
            set();	
            if(current==9)
                    b2.setText("Result");
            setVisible(false);
            setVisible(true);
        }
        for(int i=0,v=1;i<h;i++,v++)
        {
            if(e.getActionCommand().equals("BOOKMARK"+v))
            {
                if(check())
                    cnt=cnt+1;
                p=current;
                current=m[v];
                set();
                ((JButton)e.getSource()).setEnabled(false);
                current=p;
            }
        }

        if(e.getActionCommand().equals("Result"))
        {
            if(check())
                cnt=cnt+1;
            current++;
            JOptionPane.showMessageDialog(this,"Correct Answers: "+cnt);
            System.exit(0);
        }
    }
    void set()
    {
        rb[4].setSelected(true);
        if(current==0)
        {
            lb.setText("Que1:__types of Package.");
            rb[0].setText("three");rb[1].setText("two");rb[2].setText("one");rb[3].setText("four");	
        }
        if(current==1)
        {
            lb.setText("Que2:Private access modifier is specified for ?");
            rb[0].setText("Same Package");rb[1].setText("Same package & sub class");rb[2].setText("Same class");rb[3].setText("Different package");
        }
        if(current==2)
        {
            lb.setText("Que3: Which is features of java?");
            rb[0].setText("platform independent");rb[1].setText("platform dependent");rb[2].setText("not object oriented");rb[3].setText("doesn't support web based application");
        }
        if(current==3)
        {
            lb.setText("Que4: String class is defined in ___ package.");
            rb[0].setText("awt");rb[1].setText("swing");rb[2].setText("Applet");rb[3].setText("lang");
        }
        if(current==4)
        {
            lb.setText("Que5:One byte equal to ?");
            rb[0].setText("4 bit");rb[1].setText("6 bit");rb[2].setText("8 bit");rb[3].setText("16 bit");
        }
        if(current==5)
        {
            lb.setText("Que6:Java is __ programming language ");
            rb[0].setText("low level ");rb[1].setText("high level");rb[2].setText("mid level");rb[3].setText("low and mid level");
        }
        if(current==6)
        {
            lb.setText("Que7: Which one among these is not a class?");
            rb[0].setText("Applet");rb[1].setText("Actionperformed");rb[2].setText("ActionEvent");rb[3].setText("Button");
        }
        if(current==7)
        {
            lb.setText("Que8: Which among these is a int?");
            rb[0].setText("78");rb[1].setText("java");rb[2].setText("99.98765");rb[3].setText("34.01");		
        }
        if(current==8)
        {
            lb.setText("Que9: JDK means?");
            rb[0].setText("java development kit");rb[1].setText("jsp development kit");rb[2].setText("jsp non-development kit");rb[3].setText("only java");
        }
        if(current==9)
        {
            lb.setText("Que10: Which one among these is not a keyword?");
            rb[0].setText("double");rb[1].setText("if");rb[2].setText("get");rb[3].setText("class");
        }
        lb.setBounds(30,40,450,20);
        for(int i=0,j=0;i<=90;i+=30,j++)
            rb[j].setBounds(50,80+i,200,20);
    }
    boolean check()
    {
        if(current==0)
                return(rb[1].isSelected());
        if(current==1)
                return(rb[2].isSelected());
        if(current==2)
                return(rb[0].isSelected());
        if(current==3)
                return(rb[3].isSelected());
        if(current==4)
                return(rb[2].isSelected());
        if(current==5)
                return(rb[1].isSelected());
        if(current==6)
                return(rb[1].isSelected());
        if(current==7)
                return(rb[0].isSelected());
        if(current==8)
                return(rb[0].isSelected());
        if(current==9)
                return(rb[2].isSelected());
        return false;
    }
    
    public static void main(String s[])
    {
        new OnlineTest("Online Test ");
    }
}

