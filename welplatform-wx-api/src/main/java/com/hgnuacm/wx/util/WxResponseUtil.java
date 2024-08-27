package com.hgnuacm.wx.util;

import com.hgnuacm.common.core.domain.R;

/**
 * 微信接口枚举信息的响应
 * 
 * @author CHENBO
 * @since 1.0.0
 * @QQ 623659388
 */
public class WxResponseUtil extends R {

	/**
	 * 按枚举返回错误响应结果
	 * 
	 * @param responseCode
	 * @return
	 */
	public static Object fail(WxResponseCode responseCode) {
		return fail(responseCode.code(), responseCode.desc());
	}
}
