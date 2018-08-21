package test.shiro.exception;

import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/11  16:02
 * Version: V1.0
 * Description:
 * 全局异常分析
 * 捕捉未处理的异常以及自定义的或者指定的异常
 * ======================
 */
@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(value = {IncorrectCredentialsException.class,UnknownAccountException.class,LockedAccountException.class,ExpiredCredentialsException.class})
	public ResponseEntity cacheException(Throwable e){
		if(e instanceof IncorrectCredentialsException){
			return ResponseEntity.badRequest()
					.body("密码错误");
		}else if( e instanceof UnknownAccountException) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("账号错误");
		}else if( e instanceof LockedAccountException){
			return ResponseEntity.status(HttpStatus.LOCKED)
					.body("账号异常");
		}else if( e instanceof ExpiredCredentialsException){
			return ResponseEntity.status(HttpStatus.NOT_EXTENDED)
					.body("密码过期");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("服务器异常");
		}
	}
}
