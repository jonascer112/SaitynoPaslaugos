import Util.HibernateUtil;
import Util.JaxbUtil;
import org.example.Computer;
import org.example.Customer;
import org.example.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Receive XML file from client
            InputStream inputStream = socket.getInputStream();
            File receivedFile = new File("received.xml");
            FileOutputStream fileOutputStream = new FileOutputStream(receivedFile);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
            fileOutputStream.close();
            inputStream.close();

            // Transform XML to POJO
            Order order = JaxbUtil.convertToJava(receivedFile, Order.class);
            if (order != null) {
                System.out.println("Received Order: " + order);
            }

            // Perform database operations
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.save(order);
                transaction.commit();
                System.out.println("Order saved to the database.");
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }

            // Send response to client
            OutputStream outputStream = socket.getOutputStream();
            String response = "Order received and saved to the database.";
            outputStream.write(response.getBytes());
            outputStream.close();

            // Close the socket and server
            socket.close();
            System.out.println("Server closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
