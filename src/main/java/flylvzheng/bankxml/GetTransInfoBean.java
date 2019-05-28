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
        /**
         * 函数名
         */
        @XmlElement(name = "FUNNAM")
        private  String funnam;

        /**
         * 数据格式
         */
        @XmlElement(name = "DATTYP")
        private  String dattyp;
        /**
         *
         */
        @XmlElement(name = "LGNNAM")
        private  String lgnnam;

    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class  Sdk{
        /**
         * 分行号
         */
        @XmlElement(name = "BBKNBR")
        private  String bbknbr;
        /**
         *  分行名称
         */
        @XmlElement(name = "C_BBKNBR")
        private  String cBbknbr;
        /**
         *  账号
         */
        @XmlElement(name = "ACCNBR")
        private  String accnbr;
        /**
         * 起始日期
         */
        @XmlElement(name = "BGNDAT")
        private  String bgndat;
        /**
         * 结束日期
         */
        @XmlElement(name = "ENDDAT")
        private  String enddat;
        /**
         *  最小金额
         */
        @XmlElement(name = "LOWAMT")
        private  String lowamt;
        /**
         * 最大金额
         */
        @XmlElement(name = "HGHAMT")
        private  String hghamt;
        /**
         *  借贷码
         */
        @XmlElement(name = "AMTCDR")
        private  String amtcdr;

    }
}
