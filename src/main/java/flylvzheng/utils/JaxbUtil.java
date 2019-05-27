package flylvzheng.utils;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * 首先熟悉一下JAXB实现对象与xml互转时常用的一些注解使用：
 * <p>
 * 1.@XmlRootElement，用于类级别的注解，对应xml的跟元素。通过name属性定义这个根节点的名称。
 * <p>
 * 2.@XmlAccessorType，定义映射这个类中的何种类型都需要映射到xml。(如果不存在@XmlAccessorType,默认使用XmlAccessType.PUBLIC_MEMBER注解)
 * <p>
 * 　　参数：XmlAccessType.FIELD: java对象中的所有成员变量。
 * <p>
 * 　　XmlAccessType.PROPERTY：java对象中所有通过getter/setter方式访问的成员变量。
 * <p>
 * 　　XmlAccessType.PUBLIC_MEMBER：java对象中所有的public访问权限的成员变量和通过getter/setter方式访问的成员变量。
 * <p>
 * 　　XmlAccessType.NONE: java对象的所有属性都不映射为xml的元素。
 * <p>
 * 3.@XmlAttribute，用于把java对象的属性映射为xml的属性,并可通过name属性为生成的xml属性指定别名。
 * <p>
 * 4.@XmlElement，指定一个字段或get/set方法映射到xml的节点。通过name属性定义这个根节点的名称。
 * <p>
 * 5.@XmlElementWrapper，为数组或集合定义一个父节点。通过name属性定义这个父节点的名称。
 * <p>
 * <p>
 * <p>
 * <p>
 * XmlRootElement: 将类或枚举类型映射到 XML 元素。JAXB中的注解，用来根据java类生成xml内容。
 * <p>
 * 当使用 @XmlRootElement 注释对顶层类或枚举类型进行注释时，类型值被表示为 XML 文档中的 XML 元素。
 * <p>
 * JAXB Annotation
 *
 * @XmlRootElement // xml 文件的根元素
 * @XmlElement
 * @XmlAccessorType // 控制默认情况下是否对字段或 Javabean 属性进行系列化。
 * @XmlTransient
 * @XmlJavaTypeAdaptor:参考Using JAXB 2.0's XmlJavaTypeAdapter
 * <p>
 * <p>
 * <p>
 * <p>
 * XmlAccessorType
 * 默认规则：
 * 默认情况下，如果包中不存在 @XmlAccessorType，那么假定使用以下包级别注释。
 * @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER) 默认情况下，如果类中不存在 @XmlAccessorType，并且没有任何超类是使用 @XmlAccessorType 注释的，则假定在类中使用以下默认注释：
 * @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER) 可能值：
 * FIELD:    JAXB 绑定类中的每个非静态、非瞬态字段将会自动绑定到 XML，除非由 XmlTransient 注释。
 * NONE:     所有字段或属性都不能绑定到 XML，除非使用一些 JAXB 注释专门对它们进行注释。
 * PROPERTY: JAXB 绑定类中的每个获取方法/设置方法对将会自动绑定到 XML，除非由 XmlTransient 注释。
 * PUBLIC_MEMBER:每个公共获取方法/设置方法对和每个公共字段将会自动绑定到 XML，除非由 XmlTransient 注释。
 * <p>
 * 应用参考：http://blog.sina.com.cn/s/blog_4051f5dc0100ju0a.html
 */

@SuppressWarnings("all")
@Slf4j
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
            throw new RuntimeException();
        }
    }

    /**
     * xml转换成JavaBean
     * T
     *
     * @param xml
     * @param c
     * @return
     */
    public static <T> T convertToJavaBean(String xml, Class<T> c) {
        T t;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(new String(xml.getBytes("GBK"), "GBK")));
        } catch (JAXBException | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return t;
    }
}
