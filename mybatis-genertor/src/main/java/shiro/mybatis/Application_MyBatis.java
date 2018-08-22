package shiro.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/22  10:10
 * Version: V1.0
 * Description:
 * ======================
 */
public class Application_MyBatis {

	public static void main(String [] args){

		try {
			List<String> warnings = new ArrayList<>();
			boolean overwrite = true;
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration configuration = cp.parseConfiguration(Application_MyBatis.class.getResourceAsStream("/generatorConfig.xml"));

			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);

			myBatisGenerator.generate(null);
		} catch (IOException | InvalidConfigurationException | XMLParserException | SQLException | InterruptedException e) {
			e.printStackTrace();
		}


	}

}
