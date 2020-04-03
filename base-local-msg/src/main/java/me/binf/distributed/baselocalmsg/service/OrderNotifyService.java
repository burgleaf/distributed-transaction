package me.binf.distributed.baselocalmsg.service;

import me.binf.distributed.baselocalmsg.entity.PayMsg;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderNotifyService {


    @Autowired
    @Qualifier("db20JdbcTemplate")
    private JdbcTemplate db20JdbcTemplate;

    @Scheduled(cron = "0/5 * * * * ?")
    public void orderNotify() throws Exception{
        List<PayMsg> payMsgList =new ArrayList<>();
        //查询未处理成功的消息
        List<Map<String,Object>> mapList = db20JdbcTemplate.queryForList("select id,order_id,status,fail_count from pay_msg where status = 0");
        if(mapList.isEmpty()){
            return;
        }
        for(Map<String,Object> map : mapList){
            PayMsg payMsg = new PayMsg();
            BeanUtils.populate(payMsg,map);
            payMsgList.add(payMsg);
        }
        for (PayMsg payMsg: payMsgList) {
            int order_id = payMsg.getOrder_id();
            //调用订单接口来更改消息状态，这里会有重试，最多重试五次
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet("http://localhost:8080/handleorder?id="+order_id);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("************调用结果:"+response);
            if("success".equals(response)){
                payMsg.setStatus(1);
            }else{
                int count = payMsg.getFail_count();
                payMsg.setFail_count(count+1);
                if(count+1>5){
                    payMsg.setStatus(2);
                }
            }
            db20JdbcTemplate.update("update pay_msg set status=?,fail_count=? where id=?",payMsg.getStatus(),payMsg.getFail_count(),payMsg.getId());
        }
    }
}
