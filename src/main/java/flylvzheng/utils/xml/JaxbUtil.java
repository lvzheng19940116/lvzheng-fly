package flylvzheng.utils.xml;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


/**
 * @Description : 转换XML工具类
 * @Author : liu.fly 2018/5/8
 */
public class JaxbUtil {

    /**
     * JavaBean转换成xml
     *
     * @param obj
     * @return
     * @throws JAXBException
     * @throws XMLStreamException
     */
    public static String convertToXmlNoStandalone(Object obj) {
        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(byteArrayOutputStream,
                    (String) marshaller.getProperty(Marshaller.JAXB_ENCODING));
            xmlStreamWriter.writeStartDocument((String) marshaller.getProperty(Marshaller.JAXB_ENCODING), "1.0");
            marshaller.marshal(obj, xmlStreamWriter);
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();
            return new String(byteArrayOutputStream.toByteArray(), "GBK").replaceAll("'", "\"");
        } catch (JAXBException | XMLStreamException | UnsupportedEncodingException e) {
        	 e.printStackTrace();
        	// throw new CiticTransactionException(ExceptionLevelEnum.EXCEPTION, ExceptionCategoryEnum.LOGIC, ResultEnum.XML_CONVERT_ERROR);
        }
		return null;
    }

    /**
     * xml转换成JavaBean
     * T
     *
     * @param xml
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(new String(xml.getBytes("ISO-8859-1"), "GBK")));
        } catch (JAXBException | UnsupportedEncodingException e) {
          //  throw new CiticTransactionException(ExceptionLevelEnum.EXCEPTION, ExceptionCategoryEnum.LOGIC, ResultEnum.XML_CONVERT_ERROR);
        	 e.printStackTrace();
        }
        return t;
    }
}
