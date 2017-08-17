package com.xy.message.req;

/**
 * 文本消息
 * Created by xy on 2017/8/15.
 */
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
