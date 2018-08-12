package test.shiro.config;

import org.apache.shiro.realm.Realm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/11  17:10
 * Version: V1.0
 * Description:
 * ======================
 */
@Configuration
public class ShiroConfig {

	/**
	 *配置自定义的 授权的桥梁
	 */
	@Bean
	public Realm myRealm(){
		return new MyRealmConfig();
	}

}
