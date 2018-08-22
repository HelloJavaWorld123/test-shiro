package test.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/11  9:14
 * Version: V1.0
 * Description:
 * ======================
 */
@EnableSwagger2
@MapperScan(basePackages = {"test.shiro.dao"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = {"test.shiro"})
public class TestShiroApplication {
	public static void main(String [] args){
		SpringApplication.run(TestShiroApplication.class, args);
	}
}
