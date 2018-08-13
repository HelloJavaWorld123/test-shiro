package test.shiro.dto;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/8/12  14:42
 * Version: V1.0
 * Description:
 * ======================
 */
public class LogInDTO {
	private String ip;

	private String methodName;

	private String uri;

	private long complateTime;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public long getComplateTime() {
		return complateTime;
	}

	public void setComplateTime(long complateTime) {
		this.complateTime = complateTime;
	}
}
