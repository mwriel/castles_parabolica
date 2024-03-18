/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package worms;
import java.awt.*;
import java.lang.Math;
/**
 *
 * @author Elohim Becerra
 */
public class Castle {
    public double vx1;
    public double vy1;
    public int x0;
    public int y0;
    public int x1;
    public int y1;
    public double theta;
    public double v0;
    public boolean shoot;
    public double t;
    public double ti;
    public Castle enemy;
    public Castle(int x0, int y0, double theta, double v0) {
        t=0;
        ti=0;
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x0;
        this.y1 = y0;
        this.theta = theta;
        this.v0 = v0;
        vx1=(v0*Math.cos(Math.toRadians(theta)));
        vy1=(v0*Math.sin(Math.toRadians(theta)));
        shoot=false;
    }
    public Castle() {
        t=0;
        this.x0 = 0;
        this.y0 = 0;
        this.x1 = 0;
        this.y1 = 0;
        this.theta = 45;
        this.v0 = 50;
        vx1=(v0*Math.cos(Math.toRadians(theta)));
        vy1=(v0*Math.sin(Math.toRadians(theta)));
        shoot=false;
    }
    public void vxy(){
        vx1=(v0*Math.cos(Math.toRadians(theta)));
        vy1=(v0*Math.sin(Math.toRadians(theta)));
        
    }
    public void shoot(Graphics2D g2d, Color color){
        g2d.setPaint(color);
        
        
        g2d.fillOval(x1, 295-y1, 10, 10);
    }
    public void draw(Graphics2D g2d, Color color){
        g2d.setPaint(color);
        
        g2d.fillOval(x0,295-y0, 5,5);
        
    }
    
    public void calculate(double t0){
        
        if(y1<=0){
            System.out.println("se detubo el coso");
            shoot=false;
            y1=y0;
            x1=x0;
            
            return;
        }
        if(x1<=enemy.x0+10 &&x1>=enemy.x0 && y1 <=enemy.y0+10&& y1 >=enemy.y0-10){
            System.out.println("el enemigo se murio "+x1+","+y1);
            shoot=false;
            y1=y0;
            x1=x0;
            
            return;
        }
        t=t0-ti;
        
        x1=(int)(x0+vx1*t);
        y1=(int)(y0+(vy1*t)-9.81*(Math.pow(t, 2))/2 );
    }
}
