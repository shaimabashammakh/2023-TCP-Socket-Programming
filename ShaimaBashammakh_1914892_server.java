
// Student Name : Shaima Abdullah Bashammakh
// ID : 1914892

// Import io : For I/O Exception, DataInputStream and DataOutputStream
import java.io.*;
// Import net : For Create Server Socket, Socket 
import java.net.*;
// Import for MAC Address Checking
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ShaimaBashammakh_1914892_server {
    
    //---------------------------------------------MAIN METHOD---------------------------------------------------------

    public static void main(String[] args) throws IOException {
      
        // Create a server socket with the port number = 3500
        // The port number must not be from 0 to 1024, they reserved port numbers.
        ServerSocket server_socket = new ServerSocket(3500);


        // Loop to make a server always ON to reaceive any TCP connection
        // If we want to close the server after one client, we don't need this loop
        while (true) {
            
           // Create a TCP Connection
           // Create a Socket Object -socket- to accept the TCP connection from the client
           // The handshaking step
           Socket socket = server_socket.accept(); 

            // Print message to notify the client who is connect to server
            System.out.println("-------------------------------------------------------");
            System.out.println("TCP Connection is Accepted, Client Connected to the Server");
            System.out.println("-------------------------------------------------------\n");

            // Create a datainputstream to recieve a data/message from a client
           DataInputStream Request_from_client = new DataInputStream(socket.getInputStream());
            // Create a dataoutputstream to send a data/message to a client
           DataOutputStream response_to_Client = new DataOutputStream(socket.getOutputStream());

          
           // Variable to count the number of rquests
           int request_number=1;
           
           // Loop to make a TCP connection is persistent 
            while (true) {

                // Take a data from a client
                String recieved_data = Request_from_client.readUTF();

                // If the client sent "Close", the server will close the TCP connection
                if (recieved_data.equalsIgnoreCase("close")) {
                    //close TCP connection.
                    socket.close();
                    break; 
                } //End if

                // Print message to notify the client with request number and the data that send to client
                System.out.println("Request number :" + request_number++);
                System.out.println("Client's request messagae that is recieved : " + recieved_data);
                System.out.println("\n");

                // Sent the recieved data to the is_Valid_MACAddress method to check if the address is valid or invalid
                // Send the Method's return value to the client
                response_to_Client.writeUTF(is_Valid_MACAddress(recieved_data));

            }//End loop 
        }//End loop

    }//End main
    
    //---------------------------------------------IS VALID MAC ADDRESS METHOD---------------------------------------------------------
    
    public static String is_Valid_MACAddress(String MacAddress) {
        
        // The regular expresion of MAC address
        // MAC address consists From 5 Groups of numbers/letters seperated by {-} , each contains 2 numbers/letters.
        // The available letters : A-F and a-f
        // The available numbers : 0-9
        String RegulerExpresion_MACaddress = "^([A-Fa-f0-9]{2}-){5}([A-Fa-f0-9]{2})$";

        // Create a pattern object to combile the RegulerExpresion_MACaddress
        Pattern pattern = Pattern.compile(RegulerExpresion_MACaddress);

        // Create a matcher object to compare between the RegulerExpresion_MACaddress and the input macAddress
        Matcher matcher = pattern.matcher(MacAddress);

        // Return true if RegulerExpresion_MACaddress and the input macAddress are matching
        Boolean result = matcher.matches();
        
        // Determine the return value based on the result
        if(result)
            return "The entered string is a valid MAC address";
        else 
            return "The entered string is an invalid MAC address";
        
    }//End method
    
    /* 
    REFERENCE:
    IS VALID MAC ADDRESS Method, I have benefited from:
    https://stackoverflow.com/questions/4260467/what-is-a-regular-expression-for-a-mac-address
    */
    
}//End class
