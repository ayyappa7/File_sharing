/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prefinal;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ayyappa
 */
class Getip extends JFrame{
   public  JButton button ;
   public JTextArea textarea;
   public JTextField textfield;
   public Container cont;
   public FlowLayout layout;
    public String ip=null;
   public Getip(){
       super("  SET IP  ");
       //declaration
      // ip="127.0.0.1";
   button = new JButton("set ip");
   textarea = new JTextArea(30,30);
   textfield =  new JTextField(30);
   layout = new FlowLayout();
   cont = getContentPane();
  
  
   setLayout(layout);
   //adding to jframe
   
   add(textfield);
   add(button);
   add(textarea);
   layout.setAlignment(FlowLayout.LEFT);
   layout.layoutContainer(cont);
   //textfield.setText("127.0.0.1");
      
  button.addActionListener(
             new ActionListener(){
             public void actionPerformed(ActionEvent event){
                 
                
                     ip=textfield.getText();
                     returnip();
                     dispose();
                    // Clientrec cl = new Clientrec(ip);
                     
                     //cl.recieve();
                    //cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                     
                 
                 
                     
                 
             }});
   setSize(360,200);
   setVisible(true);
   showMessage("Enter the ip of system u want to connect");
   }
   public String returnip(){
      
     System.out.println("returned ip is"+ip);
       return ip;
       
       
       
   }
     private void showMessage(final String text){

            SwingUtilities.invokeLater(
            new Runnable(){
            public void run(){
             textarea.append(text);
            }
            }
            );
            }   
    
}
