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
    
    public double t;
    public Castle castle[]=new Castle[2];
    public JPanel panel;
    public JButton shoot1;
    public JButton shoot2;
    public Thread timer;

    public Form() {
        
        t=0;
        shoot1=new JButton("shoot");
        shoot2=new JButton("shoot");
        shoot1.setBounds(0,10,100,50);
        shoot2.setBounds(400,10,100,50);
        
        //Castle(int x0, int y0, int x1, int y1, double theta, double v0)
            castle[0]=new Castle(200,100,0,0,99,70);
            castle[1]=new Castle(700,100,0,0,135,70);
        ActionListener alshoot1=new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(castle[0].shoot==true){
                    return;
                }
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
                    castle[i].draw(g2d, Color.yellow);
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
