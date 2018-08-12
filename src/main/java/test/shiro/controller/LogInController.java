package test.shiro.controller;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/11  10:43
 * Version: V1.0
 * Description:
 * 关于用户登录 注销  记住 忘记密码 等的操作
 * ======================
 */
@Api(value = "/api/user",description = "用户登录 登出 忘记密码等接口",position = 1)
@RestController
@RequestMapping("/api/user")
public class LogInController {

	/**
	 * 用户登录接口
	 * @param username ：当前用户的用户名或者手机号 （唯一代表当前用户的标识）
	 * @param password ： 当前用户的密码（经过处理以后的密码）
	 * @return ：登录是否或者对应的异常或者当前用户的授权信息
	 */
	@ApiOperation(value = "用户登录接口",produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username",value = "用户姓名",dataType = "String",required = true),
			@ApiImplicitParam(name = "password",value = "用户密码",dataType = "String",required = true)
	})
	@ApiResponses(value = {
			@ApiResponse(code = 200,message = "登录成功"),
			@ApiResponse(code = 400,message = "密码错误"),
			@ApiResponse(code = 403,message = "找好错误"),
			@ApiResponse(code = 423,message = "账号过期"),
			@ApiResponse(code = 510,message = "密码过期")
	})
	@PostMapping(value = "/login")
	public ResponseEntity logIn(String username, String password) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return ResponseEntity.badRequest()
					.body("缺少参数");
		}

		//构建当前用户的令牌
		UsernamePasswordToken token = new UsernamePasswordToken(username.trim(), password);

		//获取当前主体
		Subject subject = SecurityUtils.getSubject();

		//通过主体 提交需要认证的令牌
		subject.login(token);

		return ResponseEntity.ok().body("登录成功");
	}
}
