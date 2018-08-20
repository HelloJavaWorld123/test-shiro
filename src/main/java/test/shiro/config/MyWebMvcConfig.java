package test.shiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/13  17:23
 * Version: V1.0
 * Description:
 * ======================
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurationSupport {

	@Autowired
	private List<HttpMessageConverter<?>> messageConverters;

	/**
	 * 将自定义的消息装换器 添加到 spring boot中方式之一（其他两种方式： @Bean 注入 以及 extendMessageConverters）
	 * @param converters : 消息转换器
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new CoustomFastJsonHttpMessageConvert().fastJsonHttpMessageConverter());
	}


	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new MyArguementResolver(messageConverters));
	}



}
