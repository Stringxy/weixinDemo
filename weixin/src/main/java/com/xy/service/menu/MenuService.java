package com.xy.service.menu;

import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by xy on 2017/8/16.
 */
public interface MenuService {
    public JSONObject getMenu(String accessToken);
    public int createMenu(Map<String, Object> menu, String accessToken);
    public int deleteMenu(String accessToken);
}
