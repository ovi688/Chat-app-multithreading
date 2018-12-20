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
import java.util.*;

public class Server {

 
   private static  String[] userInfo;
     
   public static ArrayList<ClientHandler> cList = new ArrayList<ClientHandler>();
   public static ArrayList<ClientHandler> onlineUserList = new ArrayList<ClientHandler>();
 
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
         try{
             ServerSocket  serverSocket = new ServerSocket(7777);
            
            Socket socket = serverSocket.accept();
            
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                
            String recMsg;
            InputStream istream = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(istream));
            
            String sendMsg,msg,userN,pass,cmd2;
                
            while(true){
                
                String[] userInfo = (br.readLine()).split(":");
                
                String teststr = userInfo[0];
               int flag=0;
               ClientHandler tempClient=null;
                if(teststr.equals("sign_in") || teststr.equals("sign_up")){
                userN =userInfo[1];
                pass =userInfo[2];
                
                for(int i=0;i<cList.size();i++){
                    
                    if(cList.get(i).getUserName().equals(userN)
                            && cList.get(i).getPassword().equals(pass)){
                        tempClient=cList.get(i);
                        flag=1;
                        break;
                    }
                }
                
                if(flag==1){
                    msg = "UserName and Password Matched. Logged In.";
                    Thread th = new Thread(tempClient);
                    th.start();
                    onlineUserList.add(tempClient);
                    dos.writeBytes(msg+'\n');                   
                }
                else{                            
                   ClientHandler client = new ClientHandler(userN,pass,socket);                 
                    cList.add(client);                   
                    msg="Registration Complete New Client: "+userN;
                    dos.writeBytes(msg+'\n');  
                    onlineUserList.add(tempClient);
                } 
            }
                
                 if(teststr.equals("cmd")){
                    cmd2=userInfo[1];
                    String userListStr="";
                    int count=1;
                    if(cmd2.equalsIgnoreCase("show_online_user_list")){
                        for(int i=0;i<onlineUserList.size();i++){
                            userListStr= userListStr+onlineUserList.get(i).getUserName()+"  "+(i+2)+".";
                            dos.writeBytes(i+"."+userListStr.substring(0,userListStr.length()-2)+'\n');
                        }
                        
                        
                        
                    }
                }
        }
         }
         
        catch(Exception ex){
            System.out.println(ex);
        }
         }
}
    
           
      
    
    

