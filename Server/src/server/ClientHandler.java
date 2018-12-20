/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Olku
 */
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable
{
    private Socket clientSocket;
    String R_msg, t_info;
 
    
    private String userN;
    private String pass;
 
    
 
    private static String S_msg;

    ClientHandler(String userN, String pass, Socket socket) {
        this.userN=userN;
        this.pass=pass;
        this.clientSocket = socket;  
    }
    
    public void run(){
         DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(clientSocket.getOutputStream());
            String[] arr2 = R_msg.split(":");
            if(arr2[0].equalsIgnoreCase("msg")){
                for(int i=3;i<arr2.length;i++){
                    dos.writeBytes(arr2[i] +"->"+ arr2[1]+'\n');
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          
           
        }
        
    
        public String getUserName() {
        return userN;
    }

    public void setUserName(String userN) {
        this.userN = userN;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String pass) {
        this.pass = pass;
    }
    
} 

