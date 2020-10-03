package JavaApplicationWorld;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JavaApplicationWorld extends JFrame 
{

	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() 
                        {
                            try 
                            {
                                JavaApplicationWorld frame = new JavaApplicationWorld();
                                frame.setVisible(true);
                            } catch (Exception e) 
                            {
                                e.printStackTrace();
                            }
			}
		});
	}
        public JavaApplicationWorld()
        {
            
            setTitle("PROJECT WORK I");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(330, 15, 680, 680);
            contentPane = new JPanel();
            contentPane.setBackground(new Color(204, 255, 204));
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
		
		
            JLabel lblJavaApplicationWorld = new JLabel("CSE-200");
            lblJavaApplicationWorld.setFont(new Font("Tahoma", Font.BOLD, 15));
            lblJavaApplicationWorld.setForeground(new Color(200, 20, 20));
		
            JButton button_1 = new JButton("");
            button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
		MyCalculator.main(new String[]{});
		}
            });
            button_1.setIcon(new ImageIcon(JavaApplicationWorld.class.getResource("MyCalculator.PNG")));
		
		
            JButton button_2 = new JButton("");
            button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IPFinder.main(new String[]{});
                }
            });
            button_2.setIcon(new ImageIcon(JavaApplicationWorld.class.getResource("IP.PNG")));		
		
		
            JButton button_3 = new JButton("");
            button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
		OnlineTest.main(new String[]{});
		}
            });
            button_3.setIcon(new ImageIcon(JavaApplicationWorld.class.getResource("OnlineTest.PNG")));		
		
				
            JButton button_4 = new JButton("");
            button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
		Converter.main(new String[]{});
		}
            });
            button_4.setIcon(new ImageIcon(JavaApplicationWorld.class.getResource("Converter.PNG")));
	
            
            GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
                    gl_contentPane.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPane.createSequentialGroup()
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPane.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(button_1, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(button_2, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(150)
                            .addComponent(lblJavaApplicationWorld))
                        .addGroup(gl_contentPane.createSequentialGroup()
			    .addContainerGap()
			    .addComponent(button_3, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
			    .addPreferredGap(ComponentPlacement.UNRELATED)
			    .addComponent(button_4, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)))
			    .addPreferredGap(ComponentPlacement.UNRELATED)
			    .addContainerGap(8, Short.MAX_VALUE))
		);
                
                gl_contentPane.setVerticalGroup(
		    gl_contentPane.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(12)
                            .addComponent(lblJavaApplicationWorld)
                            .addGap(10)
			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                            .addComponent(button_1, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_2, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                            .addComponent(button_3, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_4, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(8, Short.MAX_VALUE))
						
		);
		contentPane.setLayout(gl_contentPane);
        }	
}
