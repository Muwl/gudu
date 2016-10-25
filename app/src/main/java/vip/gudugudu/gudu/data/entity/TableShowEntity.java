package vip.gudugudu.gudu.data.entity;

import java.io.Serializable;

/**
 * 作者：Administrator on 2016/10/18 11:41
 * 描述：
 */

public class TableShowEntity implements Serializable{
    public String ClassName;
    public String PinYin;

    public TableShowEntity() {
        super();
    }

    public TableShowEntity(String className, String pinYin) {
        ClassName = className;
        PinYin = pinYin;
    }
}
