package flylvzheng.bankxml;


import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 请求头信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CMBSDKPGK")
@Data
public class GetTransInfoBean {
    @XmlElement(name = "INFO")
    private Info info;
    @XmlElement(name = "SDKTSINFX")
    private Sdk sdk;


    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class  Info{
        @XmlElement(name = "FUNNAM")
        private  String funnam;

        @XmlElement(name = "DATTYP")
        private  String dattyp;

        @XmlElement(name = "LGNNAM")
        private  String lgnnam;

    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class  Sdk{
        @XmlElement(name = "BBKNBR")
        private  String bbknbr;

        @XmlElement(name = "C_BBKNBR")
        private  String cBbknbr;

        @XmlElement(name = "ACCNBR")
        private  String accnbr;

        @XmlElement(name = "BGNDAT")
        private  String bgndat;

        @XmlElement(name = "ENDDAT")
        private  String enddat;

        @XmlElement(name = "LOWAMT")
        private  String lowamt;

        @XmlElement(name = "HGHAMT")
        private  String hghamt;

        @XmlElement(name = "AMTCDR")
        private  String amtcdr;

    }
}
