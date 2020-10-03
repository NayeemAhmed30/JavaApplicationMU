package JavaApplicationWorld;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Speed extends JPanel
{

    boolean lookForChange=true;
    DocumentListener dl;
    JTextField[] tf=new JTextField[3];
    JLabel[] labels=new JLabel[3];
    
    public Speed() 
    {   
        setSize(450,500);
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
        String[] str={"Meters per second","Kilometers per hour","miles per hour"};
        setLayout(new GridBagLayout());
        GridBagConstraints gc=new GridBagConstraints();
        
        gc.fill=GridBagConstraints.HORIZONTAL;
        gc.insets=new Insets(2, 5, 2, 5);
        gc.anchor=GridBagConstraints.NORTH;
        
        for(int i=0;i<3;i++)
        {
            labels[i]=new JLabel(str[i]);
            labels[i].setPreferredSize(new Dimension(185,20));
            
            tf[i]=new JTextField(20);
            tf[i].getDocument().putProperty("owner",tf[i]);
            tf[i].setPreferredSize(new Dimension(135,20));
            tf[i].setBackground(Color.PINK);
            
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
        double mps=0;
        try
        {
            JTextField t=(JTextField)e.getDocument().getProperty("owner");
            if(t.getText().equals(""))
            throw new Exception();
            double input=Double.parseDouble(t.getText());
            int i;
            for(i=0;i<3;i++)
            if(t.equals(tf[i]))
                break;
            switch(i)
            {
                case 0:mps=input;break;
                case 1:mps=input*5d/18d;break;
                case 2:mps=input*0.44704;break;
    
            }
            double[] vals=new double[3];
            lookForChange=false;
            
            vals[0]=mps;
            vals[1]=mps*18d/5d;
            vals[2]=mps/0.44704;


            for(int j=0;j<3;j++)
                if(j!=i)
                    tf[j].setText(Double.toString(vals[j]));
            lookForChange=true;
        }catch(Exception ex){ex.printStackTrace();}
    }
}
