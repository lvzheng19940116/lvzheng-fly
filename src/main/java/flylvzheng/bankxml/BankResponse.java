package flylvzheng.bankxml;

import io.swagger.annotations.ApiModelProperty;
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
    public static class ResponseInfo {
        /**
         * 数据格式
         */
        @ApiModelProperty(value = "数据格式")
        private String DATTYP;
        /**
         * 错误信息
         */
        private String ERRMSG;
        /**
         * 函数名
         */
        private String FUNNAM;

        /**
         * 登陆用户名
         */
        @XmlElement(name = "LGNNAM")
        private String lgnnam;
        /**
         * 返回代码
         */
        @XmlElement(name = "RETCOD")
        private String retcod;

    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ResponseNtqtsinfz {

        //   NUSAGE	 用途
        //  BUSNAR	业务摘要
        //  OTRNAR	其它摘要
        // RPYBBN	收/付方开户行行号
        // GSBACC	母/子公司帐号
        // GSBNAM	母/子公司名称
        //  INFFLG	信息标志
        // CHKNBR	票据号
        // RSVFLG	冲帐标志
        // FRMCOD	企业识别码

        /**
         * 借贷标记
         */
        private String AMTCDR;
        /**
         *
         */
        private String APDFLG;
        /**
         * 有否附件信息标志  Y/N
         */
        private String ATHFLG;
        /**
         * 分行号
         */
        private String BBKNBR;
        /**
         * 业务名称
         */
        private String BUSNAM;
        /**
         *
         */
        private String C_ATHFLG;
        /**
         * 分行名称
         */
        private String C_BBKNBR;
        /**
         * 日期  2018年11月08日
         */
        private String C_ETYDAT;
        /**
         * 母/子公司所在地区
         */
        private String C_GSBBBK;
        /**
         * 收/付方开户地区
         */
        private String C_RPYBBK;
        /**
         *
         */
        private String C_TRSAMT;
        /**
         *
         */
        private String C_TRSAMTC;
        /**
         *
         */
        private String C_TRSBLV;
        /**
         *
         */
        private String C_VLTDAT;
        /**
         * 交易日
         */
        private String ETYDAT;
        /**
         * 交易时间
         */
        private String ETYTIM;
        /**
         *
         */
        private String GSBBBK;
        /**
         * 扩展摘要
         */
        private String NAREXT;
        /**
         * 摘要
         */
        private String NARYUR;
        /**
         * 流水号
         */
        private String REFNBR;
        /**
         * 商务支付订单号
         */
        private String REFSUB;
        /**
         * 流程实例号
         */
        private String REQNBR;
        /**
         * 收/付方帐号
         */
        private String RPYACC;
        /**
         * 收/付方开户行地址
         */
        private String RPYADR;
        /**
         *
         */
        private String RPYBBK;
        /**
         * 收/付方开户行名
         */
        private String RPYBNK;
        /**
         * 收/付方名称
         */
        private String RPYNAM;
        /**
         *
         */
        private String RSV30Z;
        /**
         *
         */
        private String RSV31Z;
        /**
         *
         */
        private String RSV50Z;
        /**
         * 借方金额
         */
        private String TRSAMT;
        /**
         * 贷方金额
         */
        private String TRSAMTC;
        /**
         * 交易分析码
         */
        private String TRSANL;
        /**
         * 余额
         */
        private String TRSBLV;
        /**
         * 交易类型
         */
        private String TRSCOD;
        /**
         * 起息日
         */
        private String VLTDAT;
        /**
         * 业务参考号
         */
        private String YURREF;
    }
}
