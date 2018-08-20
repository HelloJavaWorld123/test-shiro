package test.shiro.config;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/20  15:27
 * Version: V1.0
 * Description:参数解析器
 * ======================
 */
public class MyArguementResolver extends AbstractMessageConverterMethodArgumentResolver {
	public MyArguementResolver(List<HttpMessageConverter<?>> converters) {
		super(converters);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
			throws Exception {
		ServletServerHttpRequest inputMessage = createInputMessage(webRequest);

		HttpHeaders headers = inputMessage.getHeaders();

		MediaType contentType = headers.getContentType();

		if(contentType.includes(MediaType.APPLICATION_FORM_URLENCODED)){
			Map<String, String[]> parameterMap = webRequest.getParameterMap();
			return JSON.toJSONString(parameterMap);
		}
		return super.readWithMessageConverters(webRequest, parameter, parameter.getGenericParameterType());
	}
}
