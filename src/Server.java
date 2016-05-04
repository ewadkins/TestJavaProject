import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static void main(String[] args) {
		int port = 8080;
		if (args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		serve(port);
    }

	public static void serve(final int port) {
		// Open server as resource
		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("Server opened on port " + port);
	        while (true) {
	            // block until a client connects, then repeat
	            final Socket socket = server.accept();
	            System.out.println(socket.getRemoteSocketAddress() + " connected");
	            
	            // Handle the connection
	            Thread handler = new Thread(new Runnable() {
					public void run() {
						try {
							handleConnection(socket);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
	            });
	            handler.start();
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static void handleConnection(Socket socket) throws IOException {
		// Create input reader and output writer
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        // Read input
        String line;
        while (!socket.isClosed() && (line = in.readLine()) != null) {
        	System.err.println(socket.getRemoteSocketAddress() + ": " + line);
        	
        	// Handle request
        	List<String> response = handleRequest(line, socket);
        	
        	// Check connection status
        	if (socket.isClosed()) {
        		break;
        	}
        	
        	// Write response
        	for (String output : response) {
        		if (output != null) {
        			out.println(output);
        		}
        		else {
        			socket.close();
        			System.out.println(socket.getRemoteSocketAddress() + " connection terminated");
        			return;
        		}
        	}
        }
        
        System.out.println(socket.getRemoteSocketAddress() + " connection closed by client");
        
	}
	
	public static List<String> handleRequest(String request, Socket socket) {
		List<String> response = new ArrayList<>();
		
		response.add("Message received");
		response.add(null); // Close connection
		
		return response;
	}
	
}
