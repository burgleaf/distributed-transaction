package me.binf.distributed.baselocalmsg.controller;


import me.binf.distributed.baselocalmsg.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment")
    @ResponseBody
    public String payment(int uid,int orderid,int amount){
        int status = paymentService.payment(uid,orderid,amount);
        return "支付成功: "+status;
    }
}
