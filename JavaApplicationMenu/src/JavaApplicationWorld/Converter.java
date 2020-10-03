package JavaApplicationWorld;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class Converter extends JFrame  
{

    JPanel conversion_panel;
    JLabel ob1 =new JLabel("Convert");
    JComboBox x;
     
    public Converter() 
    {
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0)
        {
             
            JButton button_4 = new JButton("");
            button_4.setVisible(true);
            dispose();
        }
        });
        btnBack.setFont(new Font("Thoma",Font.PLAIN,16));
        btnBack.setBounds(2,5,80,30);
        btnBack.setBackground(Color.CYAN);
        btnBack.setForeground(Color.RED);
        add(btnBack);
        
        setSize(450,560);
        setTitle("Unit Converter");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.cyan);

        conversion_panel=new JPanel();
        conversion_panel.setBorder(new EtchedBorder());
        conversion_panel.setBackground(Color.ORANGE);

        ob1.setFont(new java.awt.Font("Tahoma", 1, 12));
        ob1.setBackground(Color.PINK);


        x = new JComboBox(new String[]{ "Distance","Time","Temperature","Acceleration","Power","Angle","Volume","Speed"});
        x.setSelectedIndex(0);
        x.setBackground(Color.cyan);
        x.setForeground(Color.red);
        setLayout(new GridBagLayout());

        GridBagConstraints ob = new GridBagConstraints();


        ob.gridx=0;
        ob.gridy=0;
        ob.insets=new Insets(5,0,5,0);
        ob.anchor=GridBagConstraints.ABOVE_BASELINE_TRAILING;
        ob.fill=GridBagConstraints.NONE;
        add(ob1,ob);
        ob.gridx=1;
        add(x,ob);
        ob.gridx=0;
        ob.gridy=1;
        ob.gridwidth=2;
        ob.gridheight=9;
        ob.fill=GridBagConstraints.BOTH;

        add(conversion_panel,ob);

        conversion_panel.setLayout(new GridLayout(1,1));
        conversion_panel.add(new Distance());
        conversion_panel.setPreferredSize(new Dimension(408,446));
        x.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e)
        {
            conversion_panel.removeAll();
            switch(x.getSelectedIndex())
            {
                case 0:conversion_panel.add(new Distance());break;
                case 1:conversion_panel.add(new Time());break;
                case 2:conversion_panel.add(new Temperature());break;
                case 3:conversion_panel.add(new Acceleration());break;
                case 4:conversion_panel.add(new Power());break;
                case 5:conversion_panel.add(new Angle());break;
                case 6:conversion_panel.add(new Volume());break;
                case 7:conversion_panel.add(new Speed());break;
            }
            conversion_panel.revalidate();
            conversion_panel.repaint();
            }
        });
    }
    
     

    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {}

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Converter().setVisible(true);
            }
        });
    }
}