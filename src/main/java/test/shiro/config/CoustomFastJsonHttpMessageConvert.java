package test.shiro.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;

import java.nio.charset.Charset;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/20  11:37
 * Version: V1.0
 * Description:
 *  将 FastJson作为 输出 消息转换器
 * ======================
 */
public class CoustomFastJsonHttpMessageConvert{

	public FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){

		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig  = new FastJsonConfig();

		SerializerFeature[] serializerFeature = new SerializerFeature[]{


				SerializerFeature.WriteMapNullValue,

				SerializerFeature.WriteNullBooleanAsFalse,

				SerializerFeature.WriteDateUseDateFormat,

				//如果数值字段没有值 输出 0
				SerializerFeature.WriteNullNumberAsZero,

				//如果集合为空 输出[] 而非null
				SerializerFeature.WriteNullListAsEmpty,

				//循环引用
				SerializerFeature.DisableCircularReferenceDetect,

		};

		fastJsonConfig.setSerializerFeatures(serializerFeature);
		fastJsonConfig.setCharset(Charset.forName("UTF-8"));
		converter.setFastJsonConfig(fastJsonConfig);
		return converter;
	}
}
