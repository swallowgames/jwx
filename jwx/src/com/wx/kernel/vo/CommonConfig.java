package com.wx.kernel.vo;

import lombok.Data;

/**
 * <公共文件相关信息>
 * 
 * @author yuanzh
 */
@Data
public class CommonConfig {
    /**公众号配置服务器url*/
    private String serverUrl;
    /**服务器地址url*/
    private String imageUrl;
    /**图片上传路径*/
    private String imagePath;
    /**小i智能机器人配置key*/
    private String robotKey;
    /**小i智能机器人配置value*/
    private String robotValue;
    /**小i智能机器人默认问题*/
    private String question;
}
