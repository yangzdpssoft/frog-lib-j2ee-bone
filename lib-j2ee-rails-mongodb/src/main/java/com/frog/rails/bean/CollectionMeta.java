package com.frog.rails.bean;

import com.cyou.fz.common.base.db.mongo.annotation.DBCollection;
import com.cyou.fz.common.crud.property.DataBean;


@DBCollection("CollectionMeta")
public class CollectionMeta extends DataBean{

    private ListPanelMeta listPanelMeta;

    private TreePanelMeta treePanelMeta;

    private ThumbPanelMeta thumbPanelMeta;

    private FormPanelMeta formPanelMeta;

}
