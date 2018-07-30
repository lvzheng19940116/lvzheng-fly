package flylvzheng.utils.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description : Adapter—>XML
 * @Author : liu.fly 2018/5/8
 */
public class JaxbAdapter {
    /**
     * 日期
     */
    public static class DateAdapter extends XmlAdapter<String, Date> {
        @Override
        public Date unmarshal(String v) throws Exception {
            if (v == null) {
                return null;
            }
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            return format.parse(v);
        }

        @Override
        public String marshal(Date v) throws Exception {
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            return format.format(v);
        }
    }

    /**
     * 时间
     */
    public static class TimeAdapter extends XmlAdapter<String, Date> {
        @Override
        public Date unmarshal(String v) throws Exception {
            if (v == null) {
                return null;
            }
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            return format.parse(v);
        }

        @Override
        public String marshal(Date v) throws Exception {
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            return format.format(v);
        }
    }
}
