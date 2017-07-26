/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prefinal;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.crypto.CipherInputStream;
import javax.crypto.*;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
/**
 *
 * @author Ayyappa
 */
public class Filetosend extends JFrame {
    public static Socket socketname; 
     OutputStream os = null;
    // ObjectOutputStream oos = null;
     InputStream is = null;
     //ObjectInputStream ois = null;
      private JTextArea fta=null;
       FileInputStream fis = null;
    BufferedInputStream bis = null;
    FlowLayout layout=null;
    
     public byte[] bytes =null;
     private final Container cc2;
   public static String message = null;
   
    public Filetosend(Socket socket) throws IOException{
    super("Client requests");
   
    fta = new JTextArea(20,30);
     layout = new FlowLayout();
        cc2=getContentPane();
        setLayout(layout);
        layout.setAlignment(FlowLayout.LEFT);
        layout.layoutContainer(cc2);
    socketname=socket;
   os=socketname.getOutputStream();
     
   //os.flush();
   is=socketname.getInputStream();
   add(fta);
    setSize(600,600);
        setVisible(true);
    }
    public void filenamesend() throws IOException, InterruptedException{
         int j;
       
         
        
    String tempo="";
        
        File folder= new File("D:\\Projects\\project CN\\files");// Enter your folder location you want to share
        File[] list=folder.listFiles();
        for(int i=0;i<list.length;i++)
        {
            if(list[i].isFile()){
               // System.out.println(list[i]+"");
                tempo=tempo+list[i]+"$"+list[i].length()+"$";
        
            }
        } 
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        //for (String element : listFile) {
             out.writeUTF(tempo);
            //}
        byte[] bytes = baos.toByteArray();//Everything is converted to bytes here and you need to send this
        
        
os.write(bytes);
        os.flush();
        listenname();
    }
        //send array of strings
    public void listenname() throws InterruptedException, IOException
    {//boolean f=true;
        byte[] bytes3=new byte[65536];
        showMessage("listening for filename");
        System.out.println("listening for filename");
   String filename=null;
    while(true){
        
        
        if(is.available()!=0){
           showMessage(" filename available"); 
            System.out.println(" filename available");
            try {
                                             is.read(bytes3, 0, 65536);
                                         } catch (IOException ex) {}
            
           {
    ByteArrayInputStream bais2 = new ByteArrayInputStream(bytes3);
        DataInputStream in2 = new DataInputStream(bais2);
         filename = in2.readUTF();
        showMessage(" filename read"+filename); 
            System.out.println(" filename read");
        
       
    
      System.out.println("requested "+filename);
     showMessage("requested "+filename);
   filetosend(filename);
        }
            }
    }
    }
    
   
    public void filetosend(String filename) throws InterruptedException, IOException
    {os=socketname.getOutputStream();
     try {
                     showMessage(filename);
                     showMessage("\ntransfering");
                     System.out.println("sending started");
                     File myFile = new File (filename);
                     byte [] mybytearray  = new byte [(int)myFile.length()];
                      fis = new FileInputStream(myFile);
                     bis = new BufferedInputStream(fis);
                         bis.read(mybytearray,0,mybytearray.length);
           showMessage("\nSending " + filename + "(" + mybytearray.length + " bytes)");
          System.out.println("\nSending " + filename + "(" + mybytearray.length + " bytes)");
          os.write(mybytearray,0,mybytearray.length); 
          showMessage("\nDONE");
          os.flush();
            System.out.println("\nDone.");
         showMessage("\nDONE");
        }        catch (FileNotFoundException ex) {
            showMessage("\nNO CONNECTION with client");
                   //  Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (IOException ex) {
                    // Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
     private void showMessage(final String text){

            SwingUtilities.invokeLater(
            new Runnable(){
            public void run(){
             fta.append(text);
            }
            }
            );
            }
}
