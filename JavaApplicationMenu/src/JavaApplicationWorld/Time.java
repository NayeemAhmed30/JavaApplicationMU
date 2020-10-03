package JavaApplicationWorld;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Time extends JPanel
{

    boolean lookForChange=true;
    DocumentListener dl;
    JTextField[] tf=new JTextField[12];
    JLabel[] labels=new JLabel[12];

    public Time() 
    {   
        setSize(450,560);
        dl=new DocumentListener() 
        {
            public void insertUpdate(DocumentEvent e) 
            {
                if(lookForChange)convert(e);
            }
            public void removeUpdate(DocumentEvent e) {}
            public void changedUpdate(DocumentEvent e) 
            {
                if(lookForChange)convert(e);
            }
        };
        String[] str={"Nanosecond","Microsecond","Millisecond","Second","Minute","Hour","Day","Week","Month","Year","Decade","Century"};
        setLayout(new GridBagLayout());
        GridBagConstraints gc=new GridBagConstraints();
        
        gc.fill=GridBagConstraints.HORIZONTAL;
        gc.insets=new Insets(2, 5, 2, 5);
        gc.anchor=GridBagConstraints.NORTH;
        
        for(int i=0;i<12;i++)
        {
            labels[i]=new JLabel(str[i]);
            labels[i].setPreferredSize(new Dimension(185,20));
            labels[i].setBackground(Color.CYAN);
            
            tf[i]=new JTextField(20);
            tf[i].getDocument().putProperty("owner",tf[i]);
            tf[i].setPreferredSize(new Dimension(135,20));
            tf[i].setBackground(Color.GREEN);
            
            gc.gridwidth=1;
            gc.gridx=0;
            gc.gridy=i;
            add(labels[i],gc);
            
            gc.gridx=1;
            gc.gridwidth=2;
            add(tf[i],gc);
            
            tf[i].getDocument().addDocumentListener(dl);
        }    
    }
    
    public void convert(DocumentEvent e)
    {
        double tos=0;
        try
        {
            JTextField t=(JTextField)e.getDocument().getProperty("owner");
            if(t.getText().equals(""))
                throw new Exception();
            double input=Double.parseDouble(t.getText());
            int i;
            for(i=0;i<12;i++)
                if(t.equals(tf[i]))
                    break;
            switch(i)
            {
                case 0:tos=input*Math.pow(10,-9);break;
                case 1:tos=input*Math.pow(10,-6);break;
                case 2:tos=input*Math.pow(10,-3);break;
                case 3:tos=input;break;
                case 4:tos=input*60;break;
                case 5:tos=input*3600;break;
                case 6:tos=input*86400;break;
                case 7:tos=input*604800;break;
                case 8:tos=input*2.63*Math.pow(10,6);break;
                case 9:tos=input*3.156*Math.pow(10,7);break;
                case 10:tos=input*3.156*Math.pow(10,8);break;
                case 11:tos=input*3.156*Math.pow(10,9);break;
            }
            double[] vals=new double[12];
            lookForChange=false;
            vals[0]=tos/Math.pow(10,-9);
            vals[1]=tos/Math.pow(10,-6);
            vals[2]=tos/Math.pow(10,-3);
            vals[3]=tos;
            vals[4]=tos/60;
            vals[5]=tos/3600;
            vals[6]=tos/86400;
            vals[7]=tos/604800;
            vals[8]=tos/(2.63*Math.pow(10,6));
            vals[9]=tos/(3.156*Math.pow(10,7));
            vals[10]=tos/(3.156*Math.pow(10,8));
            vals[11]=tos/(3.156*Math.pow(10,9));

            for(int j=0;j<12;j++)
                if(j!=i)
                    tf[j].setText(Double.toString(vals[j]));
            lookForChange=true;
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
