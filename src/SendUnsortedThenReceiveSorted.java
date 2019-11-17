import java.io.*;
import java.util.Scanner;
import java.net.*;

public class SendUnsortedThenReceiveSorted{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataInputStream in       = null; 
    private DataOutputStream out     = null;
	private Scanner sc; 
  
    // constructor to put ip address and port 
    public SendUnsortedThenReceiveSorted(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
  
            // takes input from terminal 
            input  = new DataInputStream(System.in); 
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String line = ""; 
  
        sc = new Scanner(System.in);
        
	    // takes input from terminal 
	    System.out.print("How many integers: ");
	    int n = sc.nextInt();
	
	    System.out.println("Enter "+ n + " integers: ");
        
        int[] numInt = new int[n];
	    for( int i = 0; i < n; i++ )
	        numInt[i] = sc.nextInt();
	    
	    for( int i = 0; i < n; i++ )
	        line += numInt[i] + " ";
	    
        try{ 
            out.writeUTF(line); 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
            line = in.readUTF(); 
        }catch(IOException i) { 
            System.out.println(i); 
        } 
        		
        System.out.println(line);
  
        // close the connection 
        try
        { 
            input.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
    	new SendUnsortedThenReceiveSorted("127.0.0.1", 5000); 
    } 
}


