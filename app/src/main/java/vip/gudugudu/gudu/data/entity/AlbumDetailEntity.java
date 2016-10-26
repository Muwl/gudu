package vip.gudugudu.gudu.data.entity;

import java.util.List;

import vip.gudugudu.gudu.base.BaseEntity;

/**
 * Created by Administrator on 2016/10/26.
 */

public class AlbumDetailEntity  extends BaseEntity.BaseBean {
    public String ClassName;
    public String AlbumTitle;
    public List<PicturesEntity> Pictures;
    public List<AlbumsEntity> RecommentsAlbums;
}
