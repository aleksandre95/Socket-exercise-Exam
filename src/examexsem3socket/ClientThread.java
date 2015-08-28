/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examexsem3socket;

import java.net.Socket;

/**
 *
 * @author user
 */
public class ClientThread implements Runnable {
    ExamExSem3Socket serv;

    public ClientThread(ExamExSem3Socket serv) {
        this.serv = serv;
    }
    
    
    public void run(){
        for (int i = 0; i < 100; i++) {
            
            
        }
 
    }
    
    public void setNewServSocket(Socket s){
        
    }
    
}
