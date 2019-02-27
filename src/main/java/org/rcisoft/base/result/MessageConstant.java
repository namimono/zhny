package org.rcisoft.base.result;

/**
 * Created by lcy on 17/2/11.
 */
public enum MessageConstant {

    MESSAGE_ALERT_SUCCESS("操作成功"),
    MESSAGE_ALERT_ERROR("操作失败");

    public String msg;

    MessageConstant(String msg) {
        this.msg = msg;
    }
}
