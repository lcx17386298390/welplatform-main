package com.hgnuacm.wx.service.impl;

public class ImageRecongizeService {

    public static com.aliyun.imagerecog20190930.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // Endpoint 请参考 https://api.aliyun.com/product/imagerecog
        config.endpoint = "imagerecog.cn-shanghai.aliyuncs.com";
        return new com.aliyun.imagerecog20190930.Client(config);
    }
}
