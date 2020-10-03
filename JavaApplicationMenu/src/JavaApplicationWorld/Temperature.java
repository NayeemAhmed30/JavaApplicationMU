package JavaApplicationWorld;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;


public class Temperature extends JPanel
{

    boolean lookForChange=true;
    DocumentListener dl;
    JTextField[] tf=new JTextField[5];
    JLabel[] labels=new JLabel[5];

    public Temperature() 
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
        String[] str={"Celsius","Kelvin","Fahrenheit","Rankine","Romer"};
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
        double toc=0;
        try
        {
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
                case 0:toc=input;break;
                case 1:toc=input-273.15;break;
                case 2:toc=(input-32)*5d/9d;break;
                case 3:toc=(input-491.67)*5d/9d;break;
                case 4:toc=(input-7.5)*40d/21d;break;
            }
            double[] vals=new double[5];
            lookForChange=false;
            
            vals[0]=toc;
            vals[1]=toc+273.15;
            vals[2]=toc*9d/5d+32;
            vals[3]=toc*9d/5d+491.67;
            vals[4]=toc*21d/40d+7.5;

            for(int j=0;j<5;j++)
            {
                if(j!=i)
                    tf[j].setText(Double.toString(vals[j]));
            }
            lookForChange=true;
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
