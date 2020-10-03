package JavaApplicationWorld;

import java.awt.*;  
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.*;

public class MyCalculator extends JFrame 
{  
  
    
    public boolean setClear = true;  
    double n1,n2;  
    char op;  
    final int FW = 350, FH = 350, H = 30, W = 30, HS = 10, VS = 10, TOPX = 30, TOPY = 50;

    String digitButtonText[] = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "+/-", "." };  
    String operatorButtonText[] = {"/", "sqrt", "*", "%", "-", "1/X", "+", "=" };   
    String specialButtonText[] = {"BKSPACE", "C", "pi" };  

    MyDigitButton digitButton[] = new MyDigitButton[digitButtonText.length];  
    MyOperatorButton operatorButton[] = new MyOperatorButton[operatorButtonText.length];   
    MySpecialButton specialButton[] = new MySpecialButton[specialButtonText.length];  

    Label jtxtdisplay = new Label("0",Label.RIGHT);  
    Label btndisplay = new Label(" ",Label.RIGHT); 
    
    
    
  		
    MyCalculator(String cal) 
    {  
         
        super(cal);
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0)
        {
             
            JButton button_1 = new JButton("");
            button_1.setVisible(true);
            dispose();
        }
        });
        btnBack.setFont(new Font("Thoma",Font.PLAIN,16));
        btnBack.setBounds(2,5,80,30);
        btnBack.setBackground(Color.CYAN);
        btnBack.setForeground(Color.RED);
        add(btnBack);

        int X = TOPX, y = TOPY;  
        jtxtdisplay.setBounds(X,y,240,H);  
        jtxtdisplay.setBackground(Color.black);  
        jtxtdisplay.setForeground(Color.YELLOW);  
        add(jtxtdisplay);  

        btndisplay.setBounds(TOPX, TOPY+H+VS, W, H);  
        add(btndisplay);  


        X  = TOPX+1*(W+HS);
        y = TOPY+1*(H+VS);  
        for(int i=0;i<specialButton.length;i++)  
        {  
            specialButton[i] = new MySpecialButton(X,y,W*2,H,specialButtonText[i], this);  
            specialButton[i].setForeground(Color.MAGENTA);  
            X=X+2*W+HS;  
        }  


        int digitX = TOPX+W+HS;  
        int digitY = TOPY+2*(H+VS);  
        X = digitX;  y = digitY;  
        for(int i=0; i<digitButton.length; i++)  
        {  
            digitButton[i]=new MyDigitButton(X,y,W,H,digitButtonText[i], this);  
            digitButton[i].setForeground(Color.RED);  
            X+=W+HS;  
            if((i+1)%3==0)
            { 
                X=digitX;
                y+=H+VS;
            }  
        }  

        int opsX=digitX+2*(W+HS)+HS;  
        int opsY=digitY;  
        X = opsX; 
        y = opsY;  		
        for(int i=0;i<operatorButton.length;i++)  
        {  
            X +=  W+HS;  
            operatorButton[i] = new MyOperatorButton(X,y,W,H,operatorButtonText[i], this);  
            operatorButton[i].setForeground(Color.GREEN);  
            if((i+1)%2==0)
            {
                X = opsX; 
                y += H+VS;
            }  
        }  
        
        

        addWindowListener(new WindowAdapter()  
        {  
            public void windowClosing(WindowEvent ev)  
            {
                System.exit(0);
            }  
        }); 
        

        setLayout(null);  
        setSize(FW,FH);  
        setVisible(true); 
        
    }  
    
    static String getFormattedText(double temp)  
    {  
        String txt=""+temp;  
        if(txt.lastIndexOf(".0")>0)  
            txt = txt.substring(0,txt.length()-2);  
        return txt;  
    }  
        
    
    public static void main(String []args)  
    {  
        
        new MyCalculator("My Calculator");  
    }  

    
}  
	  
    class MyDigitButton extends Button implements ActionListener  
    {  
        MyCalculator cl;  

        MyDigitButton(int x, int y, int width, int height, String s, MyCalculator cal)  
        {  
            super(s);  
            setBounds(x,y,width,height);  
            this.cl=cal;  
            this.cl.add(this);  
            addActionListener(this);  
        }  
        static boolean isInString(String s, char ch)  
        {  
            for(int i=0; i<s.length();i++) 
                if(s.charAt(i)==ch)
                    return true;  
            return false;  
        }   
        public void actionPerformed(ActionEvent ev)  
        {  
            String tmptxt=((MyDigitButton)ev.getSource()).getLabel(); 
            if(tmptxt.equals("+/-"))  
            {
                double ops = Double.parseDouble(String.valueOf(cl.jtxtdisplay.getText()));
                ops = ops*(-1);
                cl.jtxtdisplay.setText(String.valueOf(ops));
            }
            if(tmptxt.equals("."))  
            {  
                if(cl.setClear)   
                {
                    cl.jtxtdisplay.setText("0.");
                    cl.setClear=false;
                }  
                else if(!isInString(cl.jtxtdisplay.getText(),'.'))  
                   cl.jtxtdisplay.setText(cl.jtxtdisplay.getText()+".");  
                return;  
            } 

            int index;
            try
            {  
                index = Integer.parseInt(tmptxt);  
            }catch(NumberFormatException e)
            {
                return;
            }  

            if (index==0 && cl.jtxtdisplay.getText().equals("0")) 
                return;  

            if(cl.setClear)  
            { 
                cl.jtxtdisplay.setText(""+index);
                cl.setClear=false;
            }  
            else  
                cl.jtxtdisplay.setText(cl.jtxtdisplay.getText()+index);  
        } 
    } 
  
    class MyOperatorButton extends Button implements ActionListener  
    {  
        MyCalculator cl;  

        MyOperatorButton(int x,int y, int width,int height,String s, MyCalculator cal)  
        {  
            super(s);  
            setBounds(x,y,width,height);  
            this.cl = cal;  
            this.cl.add(this);  
            addActionListener(this);  
        }   
        public void actionPerformed(ActionEvent ev)  
        {  
            String optxt=((MyOperatorButton)ev.getSource()).getLabel();  

            cl.setClear = true;  
            double temp = Double.parseDouble(cl.jtxtdisplay.getText());  

            if(optxt.equals("1/x"))  
            {  
                try  
                {
                    double tempd = 1/(double)temp;  
                    cl.jtxtdisplay.setText(MyCalculator.getFormattedText(tempd));
                }catch(ArithmeticException excp)  
                {
                    cl.jtxtdisplay.setText("Divide by 0.");
                }  
                return;  
            }  
            if(optxt.equals("sqrt"))  
            {  
                try  
                {
                    double tempd=Math.sqrt(temp);  
                    cl.jtxtdisplay.setText(MyCalculator.getFormattedText(tempd));
                }catch(ArithmeticException excp)  
                 {
                    cl.jtxtdisplay.setText("Divide by 0.");
                 }  
                return;  
            }  
            if(!optxt.equals("="))  
            {  
                cl.n1=temp;  
                cl.op=optxt.charAt(0);  
                return;  
            }    
            switch(cl.op)  
            {  
            case '+':  
                temp+=cl.n1;
                break;  
            case '-':  
                temp=cl.n1-temp;
                break;  
            case '*':  
                temp*=cl.n1;
                break;  
            
            case '/':  
                try
                {
                    temp=cl.n1/temp;
                }  
                catch(ArithmeticException excp)  
                {
                    cl.jtxtdisplay.setText("Divide by 0."); 
                    return;
                }  
                break; 
            case '%':  
                try
                {
                    temp=cl.n1%temp;
                }  
                catch(ArithmeticException excp)  
                {
                    cl.jtxtdisplay.setText("Divide by 0.");
                    return;
                }  
                break;  
            }  

            cl.jtxtdisplay.setText(MyCalculator.getFormattedText(temp));  

        }  
    } 
  
    class   MySpecialButton extends Button implements ActionListener  
    {  
        MyCalculator cl;  
        MySpecialButton(int x,int y, int width,int height,String s, MyCalculator cal)  
        {  
            super(s);  
            setBounds(x,y,width,height);  
            this.cl=cal;  
            this.cl.add(this);  
            addActionListener(this);  
        }   
        static String backSpace(String s)  
        {  
            String Result="";  
            for(int i=0; i<s.length()-1; i++) 
                    Result+=s.charAt(i);  
            return Result;  
        }  

        public void actionPerformed(ActionEvent ev)  
        {  
            String opText=((MySpecialButton)ev.getSource()).getLabel();   
            if(opText.equals("BKSPACE"))  
            {  
                String tempText = backSpace(cl.jtxtdisplay.getText());  
                if(tempText.equals(""))   
                    cl.jtxtdisplay.setText("0");  
                else   
                    cl.jtxtdisplay.setText(tempText);  
                return;  
            }  

            if(opText.equals("C"))   
            {  
                cl.n1=0.0;
                cl.op=' ';
                cl.n2=0.0;  
                cl.btndisplay.setText(" "); 
                cl.jtxtdisplay.setText("");
            }
            if(opText.equals("pi"))   
            {
                double ops ;
                ops = 3.141592;
                cl.jtxtdisplay.setText(String.valueOf(ops));
            }  

            cl.setClear = true;  
        }
    } 
