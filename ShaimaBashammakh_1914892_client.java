
// Student Name : Shaima Abdullah Bashammakh
// ID : 1914892

// Import io : For I/O Exception, DataInputStream and DataOutputStream
import java.io.*;
// Import net : For Create Socket 
import java.net.*;
//Import util.Scanner To Read Input From User 
import java.util.Scanner;

public class ShaimaBashammakh_1914892_client {

    //---------------------------------------------MAIN METHOD---------------------------------------------------------
    
    public static void main(String[] args) throws IOException {
        
        // Create a client socket with the port number = 3500 and local host name
        // The port number must Not be from 0 to 1024, they reserved port numbers.
        // Start a handshaking
        Socket client_socket = new Socket("localhost", 3500);

        // Create a datainputstream to recieve a response from a server
        DataInputStream responce_from_Server = new DataInputStream(client_socket.getInputStream());

        // Create a dataoutputstream to send a request to a server
        DataOutputStream request_to_Server = new DataOutputStream(client_socket.getOutputStream());

        // Create a scanner object to take input from user
        Scanner input = new Scanner(System.in);

        // Print to the user
        System.out.println("----------------------------------------------------");
        System.out.println("       Welcome to MAC Address Checker Program");
        System.out.println("----------------------------------------------------\n");

        // Loop to make a TCP connection is persistent 
        while (true) {
            
            // Read input from user
            System.out.print("Enter the MAC address : ");
            String User_input = input.next();
            // Send the input to server
            request_to_Server.writeUTF(User_input);
            
            // Take the response from the server and print it
            System.out.println(responce_from_Server.readUTF());
            
            // Read input from user
            // Ask user if he wants to use the program again
            System.out.print("Do you want to use the program again? (yes or no): ");
            String User_input2 = input.next();
            System.out.println("");
            
            // If the user enter "no", the TCP connection will close
            if (User_input2.equalsIgnoreCase("no")) {
                // Close TCP connection
                request_to_Server.writeUTF("close");
                System.out.println("...Thank you for using MAC address checker program...");
                client_socket.close();
                break;
            }//End if

        }//End loop

    }//End main

}//End class
