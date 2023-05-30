import Util.JaxbUtil;
import org.example.Computer;
import org.example.Customer;
import org.example.Order;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            // Create the Order object
            Computer computer1 = new Computer("Ateities I5", "I7-7700", 16, "GTX 1060", 250, 900);
            Customer customer1 = new Customer("David", "Guodis");
            Order order1 = new Order("2023-05-24", List.of(customer1), List.of(computer1));

            // Convert Order to XML
            String filePath = "C:\\Users\\jonas\\Desktop\\SaitynoPaslaugos\\order.xml";
            JaxbUtil.convertToXML(order1, filePath);

            // Send XML file to server
            OutputStream outputStream = socket.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(filePath);  // Use filePath instead of xmlFile
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            fileInputStream.close();

            // Receive response from server
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String response = reader.readLine();
            System.out.println("Server Response: " + response);

            // Transform XML to POJO
            Order receivedOrder = JaxbUtil.convertToJava(new File(filePath), Order.class);
            if (receivedOrder != null) {
                System.out.println("Received Order: " + receivedOrder);
            }

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
