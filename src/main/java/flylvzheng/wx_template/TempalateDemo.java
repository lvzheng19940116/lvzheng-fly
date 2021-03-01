package flylvzheng.wx_template;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author LvZheng
 * 创建时间：2021/2/26 下午3:23
 */
@Slf4j
public class TempalateDemo {

    @Autowired
    private WxMpService wxMpService;

    public void orderStatus() {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId("模板ID");
        templateMessage.setToUser("要发送的人的Openid");
        //点餐模板
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "亲，请记得收货。"),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", "18868812345"),
                new WxMpTemplateData("keyword3", ""),
                new WxMpTemplateData("keyword4", ""),
                new WxMpTemplateData("keyword5", "￥"),
                new WxMpTemplateData("remark", "欢迎再次光临！")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模版消息】发送失败, {}", e);
        }
    }

}
