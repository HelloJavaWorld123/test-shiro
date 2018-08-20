package test.shiro.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/20  16:02
 * Version: V1.0
 * Description: 自定义 数据库连接池
 * ======================
 */
@Configuration
@EnableConfigurationProperties(value = DuridConfigProperties.class)
public class CoustomerDruidConfig {

	@Bean
	public DataSource dataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		//TODO

		return dataSource;
	}


}
