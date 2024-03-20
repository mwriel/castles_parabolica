/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package worms;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Elohim Becerra
 */
public class Form extends JFrame{
        //public Image Miku=new ImageIcon(getClass().getResource("./Miku.png")).getImage();

    public double t;
    public Castle castle[]=new Castle[2];
    public JPanel panel;
    public JButton shoot1;
    public JButton shoot2;
    public Thread timer;
    public JTextField TFv1;
    public JTextField TFv2;
    public JTextField TFa1;
    public JTextField TFa2;
    public JLabel label;
    public Form() {
        label=new JLabel("");
        label.setBounds(300,0,200,50);
        label.setVisible(false);
        t=0;
        shoot1=new JButton("shoot");
        shoot2=new JButton("shoot");
        shoot1.setBounds(0,10,100,20);
        shoot2.setBounds(700,10,100,20);
        TFv1=new JTextField();
        TFv2=new JTextField();
        TFa1=new JTextField();
        TFa2=new JTextField();
        TFv1.setBounds(0,30,100,20);
        TFa1.setBounds(0,50,100,20);
        TFv2.setBounds(700,30,100,20);
        TFa2.setBounds(700,50,100,20);
        
        //Castle(int x0, int y0, int x1, int y1, double theta, double v0)
            castle[0]=new Castle("iori",200,100,99,70,"./iori.png",9,label);
            castle[1]=new Castle("miku",700,100,135,70,"./Miku.png",2,label);
            castle[0].enemy=castle[1];
            castle[1].enemy=castle[0];
        ActionListener alshoot1=new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(castle[0].shoot==true){
                    return;
                }
                castle[0].v0=Double.valueOf(TFv1.getText());
                castle[0].theta=Double.valueOf(TFa1.getText());
                castle[0].vxy();
                castle[0].y1=castle[0].y0;
                castle[0].ti=t;
                //castle[]
                castle[0].shoot=true;
                
            }
        };
        ActionListener alshoot2=new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(castle[1].shoot==true){
                    return;
                }
                castle[1].v0=Double.valueOf(TFv2.getText());
                castle[1].theta=Double.valueOf(TFa2.getText());
                castle[1].vxy();
                castle[1].y1=castle[1].y0;
                castle[1].ti=t;
                castle[1].shoot=true;
            }
        };
        shoot1.addActionListener(alshoot1);
        shoot2.addActionListener(alshoot2);
        
        panel=new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2d= (Graphics2D)g;
                for(int i=0;i<castle.length;i++){
                    castle[i].draw(g2d, Color.BLACK);
                    if(castle[i].shoot==true){
                        
                        castle[i].shoot(g2d,Color.BLUE);
                        castle[i].calculate(t);
                    }
                }
                
                
                
            }
        };
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(800,300));
        panel.add(shoot1);
        panel.add(shoot2);
        panel.add(TFv1);
        panel.add(TFv2);
        panel.add(TFa1);
        panel.add(TFa2);
        panel.add(label);
        timer=new Thread(){

            @Override
            public void run() {
                while(true){
                    try {
                        if(t<10000){
                            t+=0.1;
                        }else{
                            t=0;
                        }
                        
                        
                        
                        panel.repaint();
                        //System.out.println("segundo acrual: "+t);
                        
                        sleep(1000/60);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
          
            
        };
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        timer.start();
        
    }
    
    
    
}
