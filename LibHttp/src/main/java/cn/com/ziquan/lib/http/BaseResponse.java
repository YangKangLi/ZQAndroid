package cn.com.ziquan.lib.http;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/11/16.
 */

public class BaseResponse<T> {

    /**
     * 返回码
     */
    @SerializedName(value = "retCode", alternate = {"code, retCd"})
    private int retCode;

    /**
     * 返回信息
     */
    @SerializedName(value = "retMsg", alternate = {"msg", "message"})
    private String retMsg;

    /**
     * 返回数据
     */
    @SerializedName(value = "retData", alternate = "data")
    private T retData;


    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getRetData() {
        return retData;
    }

    public void setRetData(T retData) {
        this.retData = retData;
    }
}
