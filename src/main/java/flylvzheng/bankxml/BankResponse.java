package flylvzheng.bankxml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 以动手实践为荣,以只看不练为耻.
 * 以打印日志为荣,以出错不报为耻.
 * 以局部变量为荣,以全局变量为耻.
 * 以单元测试为荣,以手工测试为耻.
 * 以代码重用为荣,以复制粘贴为耻.
 * 以多态应用为荣,以分支判断为耻.
 * 以定义常量为荣,以魔法数字为耻.
 * 以总结思考为荣,以不求甚解为耻.
 *
 * @author LvZheng
 * 创建时间：2019/5/27 下午2:16
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CMBSDKPGK")
@Data
public class BankResponse {

    @XmlElement(name = "INFO")
    private ResponseInfo responseInfo;
    @XmlElement(name = "NTQTSINFZ")
    private ResponseNtqtsinfz responseNtqtsinfz;


    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ResponseInfo{
        private String  DATTYP;
        private String  ERRMSG;
        private String  FUNNAM;
        @XmlElement(name = "LGNNAM")
        private String  lgnnam;
        @XmlElement(name = "RETCOD")
        private String  retcod;

    }
    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class  ResponseNtqtsinfz{
        private String  AMTCDR;
        private String  APDFLG;
        private String  ATHFLG;
        private String  BBKNBR;
        private String  BUSNAM;
        private String  C_ATHFLG;
        private String  C_BBKNBR;
        private String  C_ETYDAT;
        private String  C_GSBBBK;
        private String  C_RPYBBK;
        private String  C_TRSAMT;
        private String  C_TRSAMTC;
        private String  C_TRSBLV;
        private String  C_VLTDAT;
        private String  ETYDAT;
        private String  ETYTIM;
        private String  GSBBBK;
        private String  NAREXT;
        private String  NARYUR;
        private String  REFSUB;
        private String  REQNBR;
        private String  RPYACC;
        private String  RPYADR;
        private String  RPYBBK;
        private String  RPYBNK;
        private String  RPYNAM;
        private String  RSV30Z;
        private String  RSV31Z;
        private String  RSV50Z;
        private String  TRSAMT;
        private String  TRSAMTC;
        private String  TRSANL;
        private String  TRSBLV;
        private String  TRSCOD;
        private String  VLTDAT;
        private String  YURREF;
    }
}
