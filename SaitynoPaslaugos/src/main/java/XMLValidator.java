import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XMLValidator {
    public static void main(String[] args) {
        String xmlFilePath = "C:\\Users\\jonas\\Desktop\\SaitynoPaslaugos\\order.xml"; // Replace with the path to your XML file
        String xsdFilePath = "C:\\Users\\jonas\\Desktop\\SaitynoPaslaugos\\order.xsd"; // Replace with the path to your XSD file

        boolean isValid = validateXMLSchema(xmlFilePath, xsdFilePath);
        if (isValid) {
            System.out.println("The XML is valid.");
        } else {
            System.out.println("The XML is not valid.");
        }
    }

    public static boolean validateXMLSchema(String xmlFilePath, String xsdFilePath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdFilePath));
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(xmlFilePath));
            validator.validate(source);
            return true;
        } catch (Exception e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        }
    }
}