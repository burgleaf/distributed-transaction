package me.binf.distributed.xaatomikos.config;


import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

@Configuration
public class DBConfig {



    @Bean("db20")
    public DataSource db20(){
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUser("root");
        mysqlXADataSource.setPassword("123456");
        mysqlXADataSource.setUrl("jdbc:mysql://192.168.3.20:3306/shard_order?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        return atomikosDataSourceBean;
    }

    @Bean("db21")
    public DataSource db21(){
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUser("root");
        mysqlXADataSource.setPassword("123456");
        mysqlXADataSource.setUrl("jdbc:mysql://192.168.3.21:3306/shard_order?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        return atomikosDataSourceBean;
    }


    @Bean("xaTransaction")
    public JtaTransactionManager jtaTransactionManager(){
        UserTransaction userTransaction = new UserTransactionImp();
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        return new JtaTransactionManager(userTransaction,userTransactionManager);
    }
}
