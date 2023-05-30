import Util.HibernateUtil;
import Util.JaxbUtil;
import org.example.Computer;
import org.example.Customer;
import org.example.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateApp {
    public static void main(String[] args){

        Computer computer1 = new Computer("Ateities I5", "I7-7700", 16, "GTX 1060", 250, 900);
        Customer customer1 = new Customer("David", "Guodis");
        Order order1 = new Order("2023-05-24", List.of(customer1), List.of(computer1));

        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(order1);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            List<Order> orders = session.createQuery("from Order", Order.class).list();
            orders.forEach(ordr -> System.out.println());

            System.out.println("---------------------------------");
            orders.forEach(ordr -> JaxbUtil.convertToXML(ordr, "order.xml"));

            //server = Server.createTcpServer().start();
            System.in.read();
        }catch (Exception e){
            if(transaction != null){
                System.out.println(e.getMessage());
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            //server.shutdown();
        }
    }
}