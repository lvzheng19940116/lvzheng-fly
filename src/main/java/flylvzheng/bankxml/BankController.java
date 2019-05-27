package flylvzheng.bankxml;

import flylvzheng.utils.JaxbUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

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
 * 创建时间：2019/5/22 下午2:35
 */
@SuppressWarnings("all")
@RestController
public class BankController {

    public void main() {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
        sb.append("\r\n");
        sb.append("<CMBSDKPGK>");
        sb.append("\r\n");
        sb.append("<INFO>");
        sb.append("\r\n");
        sb.append("<FUNNAM>[FUNNAM]</FUNNAM>");//GetTransInfo
        sb.append("\r\n");
        sb.append("<DATTYP>[DATTYP]</DATTYP>");//2
        sb.append("\r\n");
        sb.append("<LGNNAM>[LGNNAM]</LGNNAM>");//直连查询-林阿娟
        //  RETCOD   ERRMSG  不存在
        sb.append("\r\n");
        sb.append("</INFO>");
        sb.append("\r\n");
        sb.append("<SDKTSINFX>");
        sb.append("\r\n");
        sb.append("<BBKNBR>[BBKNBR]</BBKNBR>"); // BankAccount 表中
        sb.append("\r\n");
        sb.append("<C_BBKNBR>[C_BBKNBR]</C_BBKNBR>"); //银行名称  C_BBKNBR
        sb.append("\r\n");
        sb.append("<ACCNBR>[ACCNBR]</ACCNBR>");//银行小帐号  TOTALACCNBR
        sb.append("\r\n");
        //  FRMCOD   企业识别码

        sb.append("<BGNDAT>[BGNDAT:yyyyMMdd]</BGNDAT>");//  当前时间-1
        sb.append("\r\n");
        sb.append("<ENDDAT>[ENDDAT:yyyyMMdd]</ENDDAT>");//当前时间
        sb.append("\r\n");
        sb.append("<LOWAMT>[LOWAMT]</LOWAMT>");
        sb.append("\r\n");
        sb.append("<HGHAMT>[HGHAMT]</HGHAMT>");
        sb.append("\r\n");
        sb.append("<AMTCDR>[AMTCDR]</AMTCDR>");
        sb.append("\r\n");
        sb.append("</SDKTSINFX>");
        sb.append("\r\n");
        sb.append("</CMBSDKPGK>");
        System.out.println(sb);
    }

    @PostMapping("/post")
    @ApiOperation("xml")
    public BankResponse aaa(@RequestBody GetTransInfoBean getTransInfoBean) {
        String s = JaxbUtil.convertToXmlNoStandalone(getTransInfoBean);
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        template.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("GBK")));
        String s1 = template.postForObject("http://10.112.7.3:8080",s,String.class);//https://10.99.101.187:443
        System.out.println(s1);
        BankResponse bankResponse = JaxbUtil.convertToJavaBean(s1, BankResponse.class);
        return bankResponse;
    }

    @PostMapping("/postg")
    public  String  add(){
        GetTransInfoBean getTransInfoBean=new GetTransInfoBean();
        getTransInfoBean.setInfo(new GetTransInfoBean.Info());
        getTransInfoBean.setSdk(new GetTransInfoBean.Sdk());



        GetTransInfoBean.Info info=new GetTransInfoBean.Info();
        info.setFunnam("");
        info.setDattyp("");
        info.setLgnnam("");


        GetTransInfoBean.Sdk sdk=new GetTransInfoBean.Sdk();
        sdk.setBbknbr("");
        sdk.setCBbknbr("");
        sdk.setAccnbr("");
        sdk.setBgndat("");
        sdk.setEnddat("");
        sdk.setLowamt("");
        sdk.setHghamt("");
        sdk.setAmtcdr("");


        getTransInfoBean.setInfo(info);
        getTransInfoBean.setSdk(sdk);

        String s1 = JaxbUtil.convertToXmlNoStandalone(getTransInfoBean);
        return s1;
    }
}
