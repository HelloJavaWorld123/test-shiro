package test.shiro.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/11  17:26
 * Version: V1.0
 * Description: swagger2 的相关的配置
 * ======================
 */
@Configuration
@EnableConfigurationProperties(value = {ServerProperties.class})
public class Swagger2Config {

	@Value(value = "${swagger2.enable}")
	private Boolean enable;


	@Autowired
	private ServerProperties serverProperties;

	/**
	 * 构建 工程中
	 * 关于接口的划分
	 * 接口的请求方式
	 * 接口的响应方式
	 * 接口的请求链接
	 * 接口中请求头部是否需要加入统一的参数
	 * @return ： 构建成功的对象
	 */
	@Bean
	public Docket docket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(this.enable)
				.groupName("shiro")
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.host("127.0.0.1:"+serverProperties.getPort())
				.select()
				.apis(RequestHandlerSelectors.basePackage("test.shiro.controller"))
				.paths(Predicates.or(PathSelectors.ant("/api/**")))
				.build()
				.apiInfo(apiInfo());
	}

	/**
	 * 构建关于 接口文档的基本信息：
	 * 接口文档的版本
	 * 接口文档的开发者信息（姓名 链接方式）
	 * 接口文档的主要访问主页等信息
	 * @return ：文档信息
	 */
	public ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("Shiro API")
				.version("V1.0.0")
				.description("权限相关以及用户信息相关的接口")
				.contact(new Contact("REN","https://github.com/HelloJavaWorld123/test-shiro" , "13560184921@139.com"))
				.license("APACHE 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.build();

	}


	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
