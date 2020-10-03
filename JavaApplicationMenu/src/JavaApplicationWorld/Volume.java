package JavaApplicationWorld;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Volume extends JPanel
{

    boolean lookForChange=true;
    DocumentListener dl;
    JTextField[] tf=new JTextField[5];
    JLabel[] labels=new JLabel[5];

    public Volume() 
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
    String[] str={"Cubic meter","Liter","Milliliter","Cubic foot","Cubic inch"};
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
        double tol=0;
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
                
                case 0:tol=input*1000;break;
                case 1:tol=input;break;
                case 2:tol=input*0.001;break;
                case 3:tol=input*28.3168;break;
                case 4:tol=input*0.0163871;break;        
            }
            
            double[] vals=new double[5];
            lookForChange=false;
            
            vals[0]=tol/1000;
            vals[1]=tol;
            vals[2]=tol/0.001;
            vals[3]=tol/28.3168;
            vals[4]=tol/0.0163871;

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
