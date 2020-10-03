package JavaApplicationWorld;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Acceleration extends JPanel
{

    boolean lookForChange=true;
    DocumentListener dl;
    JTextField[] tf =new JTextField[5];
    JLabel[] labels =new JLabel[5];

    public Acceleration() 
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
        String[] str={"meters/second","Kilometers/hour","miles/hour","Gal (cm/s)","foot/s"};
        setLayout(new GridBagLayout());
        GridBagConstraints gc=new GridBagConstraints();
        
        gc.fill=GridBagConstraints.HORIZONTAL;
        gc.insets=new Insets(2, 5, 2, 5);
        gc.anchor=GridBagConstraints.NORTH;
        
        for(int i=0;i<5;i++)
        {
            labels[i]=new JLabel(str[i]);
            labels[i].setPreferredSize(new Dimension(185,20));
            
            tf[i]=new JTextField(20);
            tf[i].getDocument().putProperty("owner",tf[i]);
            tf[i].setPreferredSize(new Dimension(135,20));
            tf[i].setBackground(Color.CYAN);
            
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
        try{
        JTextField t=(JTextField)e.getDocument().getProperty("owner");
        if(t.getText().equals(""))
            throw new Exception();
        double input=Double.parseDouble(t.getText());
        int i;
        for(i=0;i<5;i++)
        if(t.equals(tf[i]))
            break;
        switch(i)
        {
            case 0:mps=input;
                break;
            case 1:mps=input*7.716049382716049*Math.pow(10,-5);
                break;
            case 2:mps=input*0.00012417777777778;
                break;
            case 3:mps=input/100;
                break;
            case 4:mps=input*0.3048;
                break;
        }
        double[] vals=new double[5];
        lookForChange=false;
        
        vals[0]=mps;
        vals[1]=mps/(7.716049382716049*Math.pow(10,-5));
        vals[2]=mps/0.00012417777777778;
        vals[3]=mps*100;
        vals[4]=mps/0.3048;

        for(int j=0;j<5;j++)
            if(j!=i)
                tf[j].setText(Double.toString(vals[j]));
            lookForChange=true;
        }catch(Exception ex)
        {   
            ex.printStackTrace();
        }
    }
}
