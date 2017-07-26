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
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import static prefinal.Server2.k;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static oracle.jrockit.jfr.events.Bits.byteValue;
/**
 *
 * @author Ayyappa
 */
public final class Clientrec extends JFrame{
    public final static int SOCKET_PORT = 7777;      // you may change this
   // public final static String SERVER = "192.168.137.46";
    public static String SERVER  ;   
    public  static String FILE_TO_RECEIVED ;
    public final static int FILE_SIZE = 65536; 
    int bytesRead;
    int tim,current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    //ObjectOutputStream oosc = null;
     //ObjectInputStream oisc = null;
    Socket clientconnection = null;
     public JTextArea cta;
    private final Container cc1; 
    public JButton cb1;
   public JButton cb2;
   public FlowLayout layout1;
   public JTextField ctf;
    public JTextField namef;
    public JTextField locf;
    public static boolean flag,flag2;
    public InputStream is =null;
    public OutputStream os =null;
    private String message="";
    public static boolean visflag;
 public Clientrec() throws IOException, ClassNotFoundException, FileNotFoundException, InterruptedException { 
    super("CLIENT LIST CLASS");
    layout1 =new FlowLayout();
         cta= new JTextArea(30,30);
        cc1=getContentPane();
        ctf= new JTextField(30);
       namef= new JTextField(30);
       locf= new JTextField(30);
        cb1=new JButton("close");
         cb2=new JButton("send name");
         visflag=true;
        setLayout(layout1);
        add(ctf);
        add(namef);
        add(locf);
        add(cb2);
        add(new JScrollPane(cta));
        add(cb1);
       
        setLayout(layout1);
         layout1.setAlignment(FlowLayout.LEFT);
   layout1.layoutContainer(cc1);
        Random ran = new Random();
      showMessage("\nclientlist constructor started");
      System.out.println("\nclientlist constructor started");
       // showMessage("\nrecieving loop started");
     
       namef.setToolTipText("enter save as name");
      // namef.setText("");
        locf.setToolTipText("enter location");
       //FILE_TO_RECEIVED = "D:\\Client_files\\";
     locf.setText("E:\\programs\\");
       System.out.println("\ncalling getip");
         
      cb1.addActionListener(
                                     new ActionListener(){
                                     public void actionPerformed(ActionEvent event){
                                    // dispose();
                                          if (fos != null) try {
                                              fos.close();
                                          } catch (IOException ex) {
                                              Logger.getLogger(Clientrec.class.getName()).log(Level.SEVERE, null, ex);
                                          }
                                          if (bos != null) try {
                                              bos.close();
                                          } catch (IOException ex) {
                                              Logger.getLogger(Clientrec.class.getName()).log(Level.SEVERE, null, ex);
                                          }
                                          System.exit(0);
                                     }});
       cb2.addActionListener(
                                     new ActionListener(){
                                     public void actionPerformed(ActionEvent event){
                                    // dispose();
                                         String nameoffile,storename;
                                         nameoffile=ctf.getText();
                                         //StandardCharsets.UTF_8
                                        
                                              ByteArrayOutputStream baos=new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
                                         try {
                                             //for (String element : listFile) {
                                             out.writeUTF(nameoffile);   } catch (IOException ex) {
                                             Logger.getLogger(Clientrec.class.getName()).log(Level.SEVERE, null, ex);
                                         }
            //}
        byte[] namebytes = baos.toByteArray();
                                         try {
                                             // os.flush();ytes
                                             os.write(namebytes);
                                         } catch (IOException ex) {
                                             Logger.getLogger(Clientrec.class.getName()).log(Level.SEVERE, null, ex);
                                         }
                                         try {
                                             os.flush();
                                         } catch (IOException ex) {
                                             Logger.getLogger(Clientrec.class.getName()).log(Level.SEVERE, null, ex);
                                         }
                                         String file = null,names=null,locs=null;
                                         names=namef.getText();
                                         locs=locf.getText();
                                         file=locs+names;
                                         
                                       
                                         try {
                                            if(names!=null && locs!=null)
                                             recieve(file);
                                         } catch (IOException ex) {
                                             Logger.getLogger(Clientrec.class.getName()).log(Level.SEVERE, null, ex);
                                         } catch (InterruptedException ex) {
                                             Logger.getLogger(Clientrec.class.getName()).log(Level.SEVERE, null, ex);
                                         }
                                       
                                            }});
       
       
       
      locf.setVisible(visflag);
      namef.setVisible(visflag);
       setSize(200,400);
         this.pack();
      setVisible(true);
      settingip();
      
      // TimeUnit.MILLISECONDS.sleep(1000);
         }
                                     
 
 public String filename(){
 return FILE_TO_RECEIVED;
 } 
 public void namerecieve() throws IOException, FileNotFoundException, InterruptedException{
     System.out.println("entered namerecieve");
     String filestring=null;
 is = clientconnection.getInputStream();
 os=clientconnection.getOutputStream();
 byte[] bytes  = new byte [FILE_SIZE];
 System.out.println("created byte array");
 try{
     System.out.println("going to read");
     if(is.available()==0){
         TimeUnit.MILLISECONDS.sleep(2000);}
     {
is.read(bytes,0,FILE_SIZE);
     ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        DataInputStream in = new DataInputStream(bais);
        filestring = in.readUTF();
      //filestring= String( bytes, Charset charset);
     }
    filestring=filestring.replace('$','\n');
     showMessage("we read"+filestring);
System.out.println("yooo we read it"+filestring);
 } catch(IOException ioException){
    cta.append( "\nERRORcant get stringg of file names");
    }
  System.out.println("neeed to recieve string");
 
        System.out.println("clientlist 180");
      
  //recieve();
 }
        public void recieve(String file) throws FileNotFoundException, IOException, InterruptedException
        {flag=true;
        //  filerec=filerec.replace('D', 'E');
    final int markint;      
      String file2=file;
        try{
               System.out.println("\nrecieve started");
            showMessage("\nfile recieve loop going to start");
            byte [] mybytearray  ;// new byte [65535];
      
     
      showMessage("\ninputstream contains "+is.available()+"bits");
     
      int countfiles = 0;
    //  do{
       //showMessage("\nin file recieve loop");
      if(is.available()==0){
                TimeUnit.MILLISECONDS.sleep(10);
      
      if(is.available()==0){
                TimeUnit.MILLISECONDS.sleep(20);
       if(is.available()==0){
                TimeUnit.MILLISECONDS.sleep(100);
        if(is.available()==0){
                TimeUnit.MILLISECONDS.sleep(200);
      
      if(is.available()==0){
                TimeUnit.MILLISECONDS.sleep(400);
       if(is.available()==0){
                TimeUnit.MILLISECONDS.sleep(1000);
        if(is.available()==0){
                TimeUnit.MILLISECONDS.sleep(5000);}
      }
       }
      }
      
      }
       }
      }
      
      
     //   if(is.available()!=0){
            showMessage("\ndata available");
             System.out.println("\ndata available");
             
            
            fos = new FileOutputStream(file2);
      bos = new BufferedOutputStream(fos);
     // System.out.println("length can be recieved"+ mybytearray.length);
     
          
        
    System.out.println("\n"+file2+" recieve starting");
    showMessage("\n"+file2+" recieve starting");
      int k = 0,avl,waittime=0,waitno=1;
      TimeUnit.MILLISECONDS.sleep(5000);
     while(flag){
          mybytearray  = new byte [FILE_SIZE];
                k=  k+bytesRead;
         bytesRead =is.read(mybytearray, 0,FILE_SIZE );
         System.out.println("bytes read = "+bytesRead);
        // countfiles++;
         System.out.println("bytes written = "+bytesRead);
         if(bytesRead>0){
             System.out.println("bytes read>0");
          bos.write(mybytearray, 0,FILE_SIZE);
      bos.flush();}
         
     if(is.available()==0){
            TimeUnit.MILLISECONDS.sleep(5000);
            if(is.available()==0){
             flag=false;}
             }
         
            
         
        }
        
            
        
      
      
     // bos.write(mybytearray, 0 , current);
      //bos.flush();
      showMessage("\nFile " + file + " downloaded \n(" + k + " bytes read)");
      System.out.println("File " + file+ " downloaded (" + k+ " bytes read)");
     
         file2=null;
    //}while(true);
           } finally{
            if (fos != null) fos.close();
      if (bos != null) bos.close();
            }
            
 }
        public void settingip() throws IOException, FileNotFoundException, InterruptedException{
            System.out.println("\ncalling getip");
         Getip gi = new Getip();
    System.out.println("Calling returip");
    while(SERVER==null){
    SERVER = gi.returnip();}
    System.out.println("returned ip is"+SERVER);
            setconnection();
        }
        public void setconnection() throws IOException, FileNotFoundException, InterruptedException{
        clientconnection = new Socket(SERVER, SOCKET_PORT);
      showMessage("\nconnected to "+SERVER);
      System.out.println("\nConnected to server ip:"+SERVER);
     // recieve();
        }
        
        private void showMessage(final String text){

            SwingUtilities.invokeLater(
            new Runnable(){
            public void run(){
             cta.append(text);
            }
            }
            );
            }
       
}


   
    
    

