/*
 * Copyright 2019 Guangdong Etone Technology Co.,Ltd.
 * All rights reserved.
 */
package com.jxust.sell.util;

import com.jxust.sell.vo.ResultVO;

/**
 * 封装结果视图工具类
 *
 * @author <a href="mailto:maxid@qq.com">LiuTao</a>
 * @since $$Id$$
 */
public class ResultVOUtil {

    /**
     * 有返回值的
     *
     * @time 下午5:46:28
     * @version V1.0
     * @param object
     * @return 结果视图对象
     */
    @SuppressWarnings("rawtypes")
    public static ResultVO success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<Object>();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    /**
     *
     * 没有返回值的
     *
     * @time 下午5:46:11
     * @version V1.0
     * @return 结果视图对象
     */
    @SuppressWarnings("unchecked")
    public static ResultVO<Object> success() {
        return success(null);
    }

    /**
     * 错误时返回
     *
     * @time 下午5:46:58
     * @version V1.0
     * @param code
     * @param msg
     * @return 结果视图对象
     */
    public static ResultVO<?> error(Integer code, String msg) {
        ResultVO<?> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
