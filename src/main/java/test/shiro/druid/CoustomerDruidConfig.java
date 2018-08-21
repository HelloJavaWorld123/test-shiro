package test.shiro.druid;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
@Slf4j
public class CoustomerDruidConfig {

	@Autowired
	DuridConfigProperties properties;


	@Bean
	public DataSource dataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(properties.getUrl());
		dataSource.setPassword(properties.getPassword());
		dataSource.setUsername(properties.getUsername());
		dataSource.setMaxActive(properties.getMaxActive());
		dataSource.setMaxIdle(properties.getMaxIdle());
		dataSource.setMinIdle(properties.getMinIdle());
		dataSource.setMaxWait(properties.getMaxWait());
		dataSource.setDbType(properties.getType());
		dataSource.setInitialSize(properties.getInitialSize());
		dataSource.setTestOnBorrow(properties.getTestOnBorrow());
		dataSource.setTestOnReturn(properties.getTestOnReturn());
		dataSource.setTestWhileIdle(properties.getTestWhileIdle());
		dataSource.setDriverClassName(properties.getDriverClassName());
		dataSource.setValidationQuery(properties.getValidationQuery());
		dataSource.setPoolPreparedStatements(properties.getPoolPreparedStatements());
		dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxOpenPreparedStatements());

		try {
			dataSource.setFilters(properties.getFilters());

			List<Filter> filters = new ArrayList<>();
			filters.add(wallFilter());
			dataSource.setProxyFilters(filters);

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("数据库配置过滤器时出现异常：{}",e.getMessage());
		}
		return dataSource;
	}



	@Bean
	public WallFilter wallFilter(){
		WallFilter wallFilter = new WallFilter();
		wallFilter.setConfig(wallConfig());
		//Log.error 的输出 当有攻击型的语句时
		wallFilter.setLogViolation(true);
		//当认为是攻击型的语句时 不不抛出异常
		wallFilter.setThrowException(false);
		return wallFilter;
	}

	//配置数据库链接池的 wallConfig
	//通过配置该对象 可以设置 那些条件的SQL可以执行 比如：要禁止 select * 这种操作或者禁止删除操作 都可以通过该对象设置
	@Bean
	public WallConfig wallConfig(){
		WallConfig wallConfig = new WallConfig();
		//是否允许一次性执行多条语句
		wallConfig.setMultiStatementAllow(true);
		//是否允许 执行DDL 语句
		wallConfig.setNoneBaseStatementAllow(true);

		//是否允许 select * 语句执行 默认为true
		wallConfig.setSelectAllColumnAllow(false);
		//是否 检查 删除语句的where语句是否成立
		wallConfig.setDeleteWhereAlwayTrueCheck(true);
		return wallConfig;
	}

	//结合 spring boot 配置 druid 的web 页面的配置
	@Bean
	public ServletRegistrationBean registrationBean(){
		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		//view this page 黑名单 deny 优先于 allow
//		bean.addInitParameter("deny","192.138.1.101" );
		bean.addInitParameter("loginUsername","admin" );
		bean.addInitParameter("loginPassword","123456" );
		//是否允许重置
		bean.addInitParameter("resetEnable","false" );
		return bean;
	}


	//配置druid 的过滤器
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());

		bean.setName("druidWebStatFilter");
		bean.addUrlPatterns("/*");
		bean.addInitParameter("exclusions","*.jsp,*.gif,*.jpg,*.html,*.png,*.ico,/druid/*" );
		return bean;
	}


	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource());
	}


}
