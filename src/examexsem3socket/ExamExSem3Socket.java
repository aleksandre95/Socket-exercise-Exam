/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examexsem3socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */



public class ExamExSem3Socket {

    public static final int PORT = 8080;
    public static final String IP = "localhost";
    public ServerSocket serverSocket;
    Counter count = new Counter();

    private void handleClient(Socket s) throws IOException {
        try {
            Scanner scanner = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            String msg = scanner.nextLine();
            Client client = new Client();
            Client1 turnstile2 = new Client1();
            
            
            

            boolean check = true;
            //CMD commands - turnstile(n)-turnstile-(m) n-which turnstile m-number of visitors
// monitor(n)-turnstile-yes - takes the monitor
            // The turnstiles are clients because I was confuse at the beggining.
            //DiD not make it to start a Thread with a lot of clients at the same time
            //DESCRIPTION****
            
            while (check) {
                String[] clientid = msg.split("-");
                String part1 = clientid[0];
                String part2 = clientid[1];
                String part3 = clientid[2];
                switch (part1) {
                    case "turnstile1":
                        client.connect(IP,PORT);
                        CounterUser t1 = new CounterUser(Integer.parseInt(part3), count);
                        t1.start();
                        break;

                    case "turnstile2":
                        turnstile2.connect(IP, PORT);
                        CounterUser t2 = new CounterUser(Integer.parseInt(part3), count);
                        t2.start();
                        break;
                   case "monitor1":
                        
                        client.send("monitor1-turnstile-yes");
                        
                        break;
                        case "monitor2":
                        
                        turnstile2.send("monitor2-turnstile-yes");
                        
                        break;
                    case "monitor1-turnstile-yes":
                        
                        pw.println(count.getValue());
                        break;
                        case "monitor2-turnstile-yes":
                        
                        pw.println(count.getValue());
                        break;
                }
                pw.println(count.getValue());//using it so that i can see the letters in the cmd NOT NEEDED
                if(part1.equals("stop")){
                 s.close();   
                }
                msg = scanner.nextLine();
            }

            

        } catch (IOException ex) {
            Logger.getLogger(ExamExSem3Socket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void startServer() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(IP, PORT));
        System.out.println("Server started");
        while (true) {
            Socket socket = serverSocket.accept(); // Important : Blocking call until a connection comes in
            handleClient(socket);
            System.out.println("a New client connected");
        }

    }
    private void startNewThreads(Socket s){
        ClientThread tNew = new ClientThread(this);
        tNew.setNewServSocket(s);
        tNew.run();
        
    }

    public static void main(String[] args) {
        try {
            new ExamExSem3Socket().startServer();
        } catch (IOException ex) {
            Logger.getLogger(ExamExSem3Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
