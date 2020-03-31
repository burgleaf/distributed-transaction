package me.binf.distributed.xaatomikos;

import me.binf.distributed.xaatomikos.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class XaAtomikosApplicationTests {

	@Autowired
	private OrderService orderService;

	@Test
	void contextLoads(@Qualifier("db20") DataSource dataSource20,
					  @Qualifier("db21")DataSource dataSource21) {
		orderService.insertTest(dataSource20,dataSource21);

	}

}
