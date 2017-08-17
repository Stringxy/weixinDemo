package com.xy.service.menu.impl;

import com.xy.service.menu.MenuService;
import com.xy.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by xy on 2017/8/16.
 */
@Service
public class MenuServiceImpl implements MenuService {
    private static Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    // 菜单创建（POST） 限1000（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    // 菜单查询（POST） 限10000（次/天）
    public static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    // 菜单删除（POST） 限1000（次/天）
    public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";


    @Override
    public JSONObject getMenu(String accessToken) {
        // 拼装创建菜单的url
        String url = menu_get_url.replace("ACCESS_TOKEN", accessToken);
        // 调用接口查询菜单
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);

        return jsonObject;
    }

    @Override
    public int createMenu(Map<String, Object> menu, String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
                log.error("****"+jsonMenu+"****");
            }
        }
        return result;
    }

    @Override
    public int deleteMenu(String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);

        // 调用接口创建菜单
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", null);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
}
