/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Olku
 */

import java.io.*;
import java.net.*;

public class Client {

   public static void main(String[] args) throws IOException {
       
        try{
     Socket socket = new Socket("localhost", 7777);                            
     BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
     System.out.println("sign_in/up:UserName:Password ");
     
   
     
     DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
     
     InputStream istream = socket.getInputStream();
     BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

     String receiveMessage, sendMessage;               
     
     while(true)
     {
        sendMessage = keyRead.readLine();   
         
        dos.writeBytes(sendMessage+'\n');
                   
        if((receiveMessage = receiveRead.readLine()) != null)
        {
           System.out.println(receiveMessage);  
        }
      
      }               
    }
        catch(Exception ex){
            System.out.println(ex);
        }
} 
    
}
