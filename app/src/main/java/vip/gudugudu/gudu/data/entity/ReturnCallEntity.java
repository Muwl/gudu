package vip.gudugudu.gudu.data.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/1.
 */

public class ReturnCallEntity implements Serializable {

    public String state;
    public String result;

    public ReturnCallEntity(String state, String result) {
        this.state = state;
        this.result = result;
    }
}
