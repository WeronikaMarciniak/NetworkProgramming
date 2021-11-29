import java.net.*;
import java.io.*;
import java.util.Scanner;
/**/
public class GreetClient {
    public static int getPort() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Listening to port 7 by default, do you want to change it?: (y/n) ");
        String answer = scan.nextLine();
        int port = 7;
        if (answer.equals("y") || answer.equals("Y")) {
            System.out.println("Insert port number: ");
            port = scan.nextInt();
        }
        return port;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String hostname;
        System.out.println("Provide host name or IP address");
        hostname= scan.nextLine();
        int port=7;
        port= getPort();
        try (Socket socket = new Socket(hostname, port)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            Console console = System.console();
            String text;

            do {
                text = console.readLine("Enter text: ");
                writer.println(text);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String message = reader.readLine();
                System.out.println(message);

            } while (!text.equals("bye"));

            socket.close();

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}