package test.shiro.dto;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/11  16:22
 * Version: V1.0
 * Description:
 * 授权 验证 封装的用户信息
 * ======================
 */
public class LogInUser {
	private String id;

	private String userName;

	private String nickName;

	private String mobile;

	//角色 以及  权限 从数据库获取到的


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
