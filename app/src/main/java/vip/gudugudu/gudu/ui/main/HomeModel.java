package vip.gudugudu.gudu.ui.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/16.
 */

public class HomeModel implements HomeContract.Model{

    @Override
    public List<TableShowEntity> getTabs() {
        TableShowEntity tableShowEntity=new TableShowEntity("二次元","1");
        TableShowEntity tableShowEntity1=new TableShowEntity("二次元1","2");
        TableShowEntity tableShowEntity2=new TableShowEntity("二次元2","3");
        TableShowEntity tableShowEntity3=new TableShowEntity("二次元3","4");
        List<TableShowEntity> tableShowEntities=new ArrayList<>();
        tableShowEntities.add(tableShowEntity);
        tableShowEntities.add(tableShowEntity1);
        tableShowEntities.add(tableShowEntity2);
        tableShowEntities.add(tableShowEntity3);
        return tableShowEntities;
    }

}
