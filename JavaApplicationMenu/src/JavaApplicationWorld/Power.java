package JavaApplicationWorld;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Power extends JPanel
{

    boolean lookForChange=true;
    DocumentListener dl;
    JTextField[] tf=new JTextField[5];
    JLabel[] labels=new JLabel[5];

    public Power() 
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
        String[] str={"watt","kilowatt","megawatt","gigawatt","metric horsepower(hp)"};
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
            tf[i].setBackground(Color.ORANGE);
            
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
        double tow=0;
        try
        {
            JTextField t=(JTextField)e.getDocument().getProperty("owner");
            if(t.getText().equals(""))
                return;
            double input=Double.parseDouble(t.getText());
            int i;
            for(i=0;i<5;i++)
                if(t.equals(tf[i]))
                    break;
            switch(i)
            {
                case 0:tow=input;break;
                case 1:tow=input*1000;break;
                case 2:tow=input*1000000;break;
                case 3:tow=input*1000000000;break;
                case 4:tow=input*735.39875;break;
                
            }
            double[] vals=new double[5];
            lookForChange=false;
            vals[0]=tow;
            vals[1]=tow/1000;
            vals[2]=tow/1000000;
            vals[3]=tow/1000000000;
            vals[4]=tow/735.39875;

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
