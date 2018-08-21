package test.shiro.druid;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/20  16:02
 * Version: V1.0
 * Description:
 * ======================
 */
@ConfigurationProperties(prefix = DuridConfigProperties.PREFIX_CONFIG)
@Data
public class DuridConfigProperties {
	static final String PREFIX_CONFIG = "spring.datasource";

	private String username;

	private String password;

	private String url;

	private String driverClassName;

	private int minIdle;

	private int maxIdle;

	private int maxActive;

	private int maxWait;

	private int initialSize;

	private String validationQuery;

	private Boolean testOnBorrow;

	private Boolean testOnReturn;

	private Boolean testWhileIdle;

	private Boolean poolPreparedStatements;

	private String filters;

	private int timeBetweenEvictionRunsMillis;

	private int minEvictableIdleTimeMillis;

	private int maxOpenPreparedStatements;

	private String type;
}
