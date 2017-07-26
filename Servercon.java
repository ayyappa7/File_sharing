/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prefinal;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ayyappa
 */
public class Servercon extends JFrame{
     public JTextArea sta;
    private final Container sc2;
    public JButton sb1;
     public JButton sb2;
   // public JTextField st;
    public static int soc;
    public static boolean flag;
    
    
     ServerSocket server = null;
    public Socket connection = null;
                            public Servercon() throws IOException{
                                super("serverConnection");
                              soc=7777;
                              
                                System.out.println("in constructor - servercon ");
                                server = new ServerSocket();
                              connection = new Socket();
                              
                                 sc2=getContentPane();
                                // st = new JTextField();
                                sta= new JTextArea();
                                 sb1=new JButton("send");
                                 sb2=new JButton("close");
                                 setLayout(new BoxLayout(sc2, BoxLayout.Y_AXIS));
                               
                               // add(st);
                                //st.setEditable(true);
        
      // st.setToolTipText("enter socketnumber here");
       
                                 add(new JScrollPane(sta));
                                 // add(sb1);
                                  add(sb2);
                                 System.out.println("added layouts ");
                               
                               System.out.println("Do not press send until connection establishes");
                               
                              sb2.addActionListener(
                                     new ActionListener(){
                                     public void actionPerformed(ActionEvent event){
                                     flag=false;
                                     if(connection!=null)try {
                                         connection.close();
                                     } catch (IOException ex) {
                                         Logger.getLogger(Servercon.class.getName()).log(Level.SEVERE, null, ex);
                                     }
                                     if(server!=null)try {
                                         server.close();
                                     } catch (IOException ex) {
                                         Logger.getLogger(Servercon.class.getName()).log(Level.SEVERE, null, ex);
                                     }System.exit(0);
                                     }});
                           System.out.println("ëntered servercon");
                               
                              setSize(300,200);
                              setVisible(true);
                                      }
    
     public void conn() throws IOException, InterruptedException{
     showMessage("connection starting in servercon");
    try {
      server = new ServerSocket(soc);
     
      flag=true;
      //while (connection!=null) {
         // while(flag){
        showMessage("\nWaiting...");
        try {
             //showMessage("\ntry statement started in connect");
         connection = server.accept();
         
          System.out.println("\nAccepted connection : " + connection);
          showMessage("\nAccepted connection : " + connection.getInetAddress().getCanonicalHostName());
             Filetosend fts = new Filetosend(connection);
             fts.filenamesend();
          //sendd the array of strings to the client
          //call file to send class and parellelly run the recieving filename
          //call another function if name recieved
         
        }
        catch(EOFException ioException){
            showMessage("\ntry statement started not worked in connect");
                showMessage("\nserver ended");
      }
        
          
    finally {
        // if(connection!=null)connection.close();
         System.out.println("need to close connections");
        }
      
    
         // }
    }finally{
        System.out.println("need to close server");
    
    }
   
}
    
            private void showMessage(final String text){

            SwingUtilities.invokeLater(
            new Runnable(){
            public void run(){
             sta.append(text);
            }
            }
            );
            }
}
