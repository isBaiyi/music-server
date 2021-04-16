package com.baiyi.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 前台返回值工具类
 *
 * @author liaozc
 * @date 2021/4/15 15:44
 **/
public class ResponseUtil {

    /**
     * 封装成功返回信息
     *
     * @param successMsg 成功信息
     * @return jsonObject
     */
    public static Object successRsp(String successMsg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MSG, successMsg);
        return jsonObject;
    }

    /**
     * 封装成功返回信息
     *
     * @param successMsg 成功信息
     * @param extMsg 额外信息
     * @return jsonObject
     */
    public static Object successRsp(String successMsg, String extMsg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.CODE, 1);
        jsonObject.put(Consts.MSG, successMsg);
        return jsonObject;
    }

    /**
     * 封装失败返回信息
     *
     * @param failMsg 失败信息
     * @return jsonObject
     */
    public static Object failRsp(String failMsg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, failMsg);
        return jsonObject;
    }

    /**
     * 封装失败返回信息
     *
     * @param failMsg 失败信息
     * @param extMsg 额外信息
     * @return jsonObject
     */
    public static Object failRsp(String failMsg, String extMsg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, failMsg);
        return jsonObject;
    }

    /**
     * 封装查询列表或单条数据的信息
     *
     * @param object 结果对象
     * @return 结果对象
     */
    public static Object objectRsp(Object object) {
        return object;
    }
}
