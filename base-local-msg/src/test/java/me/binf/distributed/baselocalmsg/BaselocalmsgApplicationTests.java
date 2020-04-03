package me.binf.distributed.baselocalmsg;

import me.binf.distributed.baselocalmsg.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaselocalmsgApplicationTests {


	@Autowired
	private PaymentService paymentService;


	@Test
	void contextLoads() {
		paymentService.payment(2,1001,10);

	}

}
