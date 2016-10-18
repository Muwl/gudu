package vip.gudugudu.gudu.ui.main;

import java.io.Serializable;

/**
 * 作者：Administrator on 2016/10/18 11:41
 * 描述：
 */

public class TableShowEntity implements Serializable{
    public String tip;
    public String type;

    public TableShowEntity() {
        super();
    }

    public TableShowEntity(String tip,String type) {
        super();
        this.tip=tip;
        this.type=type;
    }

}
