/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prefinal;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;



/**
 *
 * @author Ayyappa
 */
public class Server2  {
    static int k=0;
         public static boolean  flag ;                    

    

    public static void main(String[] args) throws IOException, ClassNotFoundException, FileNotFoundException, InterruptedException  {
            flag = true;
        k=0;
      Options opt = new Options();
      
      // System.out.println("\ngoing to start while loop in main to get k");
       while(flag){
           k=opt.returnk();
           if(k==1 || k==2){
           flag=false;
           }
          //System.out.println("loop");
       
    System.out.print("returned k is "+ k);
       }
        System.out.println("k is "+ k);
       
       // opt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
       /* System.out.println("enter 1 to send,2 to recieve");
   Scanner input = new Scanner(System.in);
   k=input.nextInt();
        */
    if(k == 1){
     Servercon serc =new Servercon();
            serc.conn();
    }else if(k == 2){
        System.out.println("k is in else"+ k);
        try{
        Clientrec cli = new Clientrec();
        try{
        cli.namerecieve();
        }catch(IOException ioE){
        System.out.println("cli.namereiceve not runned");
        }}catch(IOException ioE){
        System.out.println("clientrec not called");
    }}
   
    }
}

   
    
   
    /* public static void  run() throws IOException, ClassNotFoundException{
     if(k==true)
        {
            System.out.println("in 
    run ");
            Servercon serc =new Servercon();
            serc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            serc.conn();
        } 
       else if(l==true) 
        {
           Clientrec cl = new Clientrec();
         cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         cl.recieve();
            
        }
    }*/

    

