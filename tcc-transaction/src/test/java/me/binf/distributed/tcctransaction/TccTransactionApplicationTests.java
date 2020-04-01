package me.binf.distributed.tcctransaction;

import me.binf.distributed.tcctransaction.service.TransferAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TccTransactionApplicationTests {

	@Autowired
	private TransferAccountService transferAccountService;

	@Test
	void contextLoads() {
		transferAccountService.transfer();
	}

}
