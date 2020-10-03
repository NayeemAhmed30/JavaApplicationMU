package JavaApplicationWorld;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Distance extends javax.swing.JPanel 
{

    boolean lookForChange = true;
    DocumentListener dl;
    JTextField[] tf = new JTextField[6];
    JLabel[] labels = new JLabel[6];
    
    public Distance()
    {
    
        setSize(500,560);
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
        String[] str={"Meter","Kilometer","Centimeter","Millimeter","Micrometer","Nanometer"};
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc=new GridBagConstraints();
        gc.fill=GridBagConstraints.HORIZONTAL;
        gc.insets=new Insets(2, 5, 2, 5);
        
        for(int i=0;i<6;i++)
        {
            labels[i]=new JLabel(str[i]);
            labels[i].setPreferredSize(new Dimension(185,20));
            
            tf[i] = new JTextField(20);
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

        double tom=0;
        try
        {
            JTextField t=(JTextField)e.getDocument().getProperty("owner");
            if(t.getText().equals(""))
                throw new Exception();
            double input=Double.parseDouble(t.getText());
            int i;
            for(i=0;i<6;i++)
                if(t.equals(tf[i]))
                    break;
            switch(i)
            {
                case 0:tom=input;break;
                case 1:tom=input*1000;break;
                case 2:tom=input/100;break;
                case 3:tom=input/1000;break;
                case 4:tom=input/1000000;break;
                case 5:tom=input*Math.pow(10,-9);break;
                
            }
            double[] vals=new double[6];
            lookForChange=false;
            
            vals[0]=tom;
            vals[1]=tom/1000;
            vals[2]=tom*100;
            vals[3]=tom*1000;
            vals[4]=tom*1000000;
            vals[5]=tom*Math.pow(10,9);
            
            
            for(int j=0;j<6;j++)
                if(j!=i)
                    tf[j].setText(Double.toString(vals[j]));
            lookForChange=true;
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
