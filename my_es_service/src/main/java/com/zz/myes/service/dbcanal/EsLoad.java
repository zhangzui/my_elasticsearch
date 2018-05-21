package com.zz.myes.service.dbcanal;

import com.zz.myes.service.IndexService;
import com.zz.myes.service.domain.req.EsReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangzuizui
 * @date 2018/5/21 11:00
 */
@Component
public class EsLoad {

    @Autowired
    private IndexService indexService;

    /**
     * 插入文档
     */
    public void inesrt(EsReq esReq) {
        indexService.insertData(esReq.getIndexName(),esReq.getIndexType(),esReq.getDocumentId(),esReq.getMap());
    }

    /**
     * 删除文档
     */
    public void delete(EsReq esReq) {
        indexService.deleteIndex(esReq.getIndexName(),esReq.getIndexType(),esReq.getDocumentId());
    }

    /**
     * updateData
     * 暂时只实现父子关系
     */
    public void update(EsReq esReq) {
        String parentValue = "xxx";
        String routingValue = "xxxx";
        indexService.updateData(esReq.getIndexName(),esReq.getIndexType(),esReq.getDocumentId(),esReq.getMap(),parentValue,routingValue);
    }
}
