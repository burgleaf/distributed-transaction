package me.binf.distributed.baselocalmsg.controller;


import me.binf.distributed.baselocalmsg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/handleorder")
    @ResponseBody
    public String orderSet(int id){
        try {
            int flag = orderService.handleOrder(id);
            if (flag == 0) {
                return "success";
            } else {
                return "fail";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return "fail";
        }
    }
}
