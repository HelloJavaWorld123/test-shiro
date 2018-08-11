package test.shiro.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
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
@RestController
@RequestMapping("/api/user")
public class LogInController {

	@RequestMapping("/login")
	public ResponseEntity logIn(String username,String password){
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return ResponseEntity.badRequest().body("缺少参数");
		}
		return ResponseEntity.ok().build();
	}



}
