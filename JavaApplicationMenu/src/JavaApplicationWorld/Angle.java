package JavaApplicationWorld;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Angle extends JPanel
{

    boolean lookForChange=true;
    DocumentListener dl;
    JTextField[] tf=new JTextField[4];
    JLabel[] labels=new JLabel[4];

    public Angle() 
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
        String[] str={"Degrees","radian","gon (grad or gradian)","sign"};
        setLayout(new GridBagLayout());
        GridBagConstraints gc=new GridBagConstraints();
        
        gc.fill=GridBagConstraints.HORIZONTAL;
        gc.insets=new Insets(2, 5, 2, 5);
        gc.anchor=GridBagConstraints.NORTH;
        
        for(int i=0;i<4;i++)
        {
            labels[i]=new JLabel(str[i]);
            labels[i].setPreferredSize(new Dimension(185,20));
            
            tf[i]=new JTextField(20);
            tf[i].getDocument().putProperty("owner",tf[i]);
            tf[i].setPreferredSize(new Dimension(135,20));
            tf[i].setBackground(Color.MAGENTA);
            
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
        double torad=0;
        try
        {
            JTextField t=(JTextField)e.getDocument().getProperty("owner");
            if(t.getText().equals(""))
                return;
            double input=Double.parseDouble(t.getText());
            int i;
            for(i=0;i<4;i++)
                if(t.equals(tf[i]))
                    break;
            switch(i)
            {
                case 0:torad=input*Math.PI/180;break;
                case 1:torad=input;break;
                case 2:torad=input*Math.PI/200;break;
                case 4:torad=input*Math.PI/6;break;
            }
            double[] vals=new double[4];
            lookForChange=false;
            
            vals[0]=torad*180/Math.PI;
            vals[1]=torad;
            vals[2]=torad*200/Math.PI;
            vals[3]=torad*6/Math.PI;

            for(int j=0;j<4;j++)
            if(j!=i)
                tf[j].setText(Double.toString(vals[j]));
            lookForChange=true;
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
