package com.hgnuacm.wx.util;

public enum WxResponseCode {

	AUTH_INVALID_ACCOUNT(700, "账号密码不对"),
	AUTH_CAPTCHA_UNSUPPORT(701, "小程序后台验证码服务不支持"),
	AUTH_CAPTCHA_FREQUENCY(702, "验证码请求过于频繁"),
	AUTH_CAPTCHA_UNMATCH(703, "验证码错误"),
	AUTH_NAME_REGISTERED(704, "用户名已注册"),
	AUTH_MOBILE_REGISTERED(705, "手机号已注册"),
	AUTH_MOBILE_UNREGISTERED(706, "手机号未注册"),
	AUTH_INVALID_MOBILE(707, "手机号格式不正确"),
	AUTH_OPENID_UNACCESS(708,"获取腾讯官方 openid失败"),
	AUTH_OPENID_BINDED(709, "openid已绑定账号"),
	AUTH_EMAIL_REGISTERED(710, "邮箱已注册"),
	AUTH_INVALID_EMAIL(711, "邮箱格式不正确");
	private final Integer code;
	private final String desc;

	WxResponseCode(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static WxResponseCode getInstance(Integer code) {
		if (code != null) {
			for (WxResponseCode tmp : WxResponseCode.values()) {
				if (tmp.code.intValue() == code.intValue()) {
					return tmp;
				}
			}
		}
		return null;
	}

	public Integer code() {
		return code;
	}

	public String desc() {
		return desc;
	}

}
