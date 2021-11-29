import java.io.*;
import java.net.*;

public class GreetServer {
    public static void main(String[] args) {
            int port = GreetClient.getPort();
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server is listening on port " + port);
                    Socket socket = serverSocket.accept();
                    System.out.println("New client connected");

                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);


                    String text;

                    do {
                        text = reader.readLine();
                        writer.println("Server: " + text);
                        System.out.println(text);
                    } while (!text.equals("bye"));

                    socket.close();

            } catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
}