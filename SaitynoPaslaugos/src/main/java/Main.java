import Util.JaxbUtil;
import jakarta.xml.bind.JAXBException;
import org.example.Computer;
import org.example.Customer;
import org.example.Order;

import java.io.IOException;

import java.util.List;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {

        Computer computer1 = new Computer("I5 RTX","i5-11400F", 16, "GeForce RTX 3050", 1000, 740 );
        Computer computer2 = new Computer("eSports Ryzen 5","AMD Ryzen 5 5500", 16, "GeForce RTX 3050 8GB", 1000, 739 );

        Customer customer1 = new Customer("Antanas", "Antanavicius");
        Customer customer2 = new Customer("Deividas", "Vilnietis");

        Order order1 = new Order("2023-05-27", List.of(customer1, customer2), List.of(computer1, computer2));
        JaxbUtil.convertToXML(order1, "order.xml");

        System.out.println(order1);
    }
}