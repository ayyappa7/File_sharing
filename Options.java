/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prefinal;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Ayyappa
 */
public class Options extends JFrame{
    public JButton send;
   public  JButton rec;
    public  Container c;
    public static int k;
    FlowLayout layout2;
     public Options(){
     super("select");
     layout2=new FlowLayout();
    send = new JButton("send");
     rec = new JButton("recieve");
    c=getContentPane();
    setLayout(layout2);
    
    add(send);
    add(rec);
    
   
    setSize(300,200);
    setVisible(true);
    send.addActionListener(
                                     new ActionListener(){
                                     public void actionPerformed(ActionEvent event){
                                     k=1;
                                     System.out.println("k is 1");
                                    dispose();
                                     
                                         }});
    rec.addActionListener(
                                     new ActionListener(){
                                     public void actionPerformed(ActionEvent event){
                                     k=2;
                                     System.out.println("k is 2");
                                     dispose();
                                    }});
    }

     public int returnk(){
         
        return k;
     
     }
    
}
