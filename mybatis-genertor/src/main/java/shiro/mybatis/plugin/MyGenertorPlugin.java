package shiro.mybatis.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/22  11:01
 * Version: V1.0
 * Description:
 * ======================
 */
public class MyGenertorPlugin
		extends PluginAdapter {

	@Override
	public boolean validate(List<String> list) {
		return true;
	}


	/**
	 * 自定义生成的dao层的接口
	 */
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable){
		interfaze.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
		interfaze.addAnnotation("@Repository");
		return true;
	}



}
