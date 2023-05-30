package Util;

import org.example.Order;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbUtil {

    public static void convertToXML(Order order, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Order.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(order, new File(filePath));
            marshaller.marshal(order, System.out);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static void convertToPojo() {
        String fileName = "order.xml";
        File xmlFile = new File(fileName);

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Order.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Order employee = (Order) jaxbUnmarshaller.unmarshal(xmlFile);

            System.out.println(employee);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static <T> T convertToJava(File file, Class<T> valueType) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(valueType);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return valueType.cast(jaxbUnmarshaller.unmarshal(file));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
