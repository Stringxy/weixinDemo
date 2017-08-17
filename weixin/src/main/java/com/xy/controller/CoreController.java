package com.xy.controller;

import com.xy.service.core.CoreService;
import com.xy.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xy on 2017/8/15.
 */
@RestController
@RequestMapping("")
public class CoreController {
    @Autowired
    private CoreService coreService;
    //增加日志
    private static Logger log = LoggerFactory.getLogger(CoreController.class);
    //验证是否来自微信服务器的消息
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String checkSignature(@RequestParam(name = "signature" ,required = false) String signature  ,
                                 @RequestParam(name = "nonce",required = false) String  nonce ,
                                 @RequestParam(name = "timestamp",required = false) String  timestamp ,
                                 @RequestParam(name = "echostr",required = false) String  echostr){
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            log.info("接入成功");
            return echostr;
        }
        log.error("接入失败");
        return "";
    }

    // 调用核心业务类接收消息、处理消息跟推送消息
    @RequestMapping(value = "",method = RequestMethod.POST)
    public  String post(HttpServletRequest req){
        String respMessage = coreService.processRequest(req);
        return respMessage;
    }
}
