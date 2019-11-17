import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.net.*;
@SuppressWarnings("unused")
public class ReceiveUnsortedSendSorted 
{ 
    //initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       = null; 
    private DataOutputStream out     = null;
    // constructor with port 
    public ReceiveUnsortedSendSorted(int port) 
    { 
        // starts server and waits for a connection 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("waiting for client...."); 
            socket = server.accept(); 
            System.out.println("connected."); 
            // takes input from the client socket 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
  
            out    = new DataOutputStream(socket.getOutputStream()); 
            
            String line = "";
            try
            { 
                line = in.readUTF(); 
                line = sortFunction(line);                
                out.writeUTF(line); 
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
            
            // close connection 
            socket.close(); 
            in.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static String sortFunction(String returnAns){
	      String[] stringNumbers = returnAns.split(" ");
	      int[] numbers = new int[stringNumbers.length];
	      for(int i = 0; i < numbers.length; i++)
	      	numbers[i] = Integer.parseInt(stringNumbers[i]);
	      Arrays.sort(numbers);
	      String ans = "";
	      for( int i = 0; i < numbers.length; i++ )
	        ans += numbers[i] + ", ";
	      return ans;
	  }
    	
    public static void main(String args[]) 
    { 
    	new ReceiveUnsortedSendSorted(5000); 
    } 
} 




