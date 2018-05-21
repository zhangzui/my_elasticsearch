package com.zz.myes.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zz.my_es.common.EsClient;
import com.zz.myes.service.IndexService;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;

import java.util.Map;

/**
 * @author zhangzuizui
 * @date 2018/5/18 15:35
 */
public class IndexServiceImpl implements IndexService{
    /**
     * ES客户端
     */
    private Client client = new EsClient().getClient();

    /**
     * 创建索引
     *
     * @param indexName
     */
    @Override
    public IndexResponse createIndex(String indexName,String type) {
        IndexResponse response = client.prepareIndex(indexName, type).get();
        return response;
    }

    @Override
    public IndexResponse insertData(String indexName, String type, String id, Map map) {
        IndexResponse response = client.prepareIndex(indexName, type, id)
                .setSource(new JSONObject(map).toJSONString()
                )
                .execute()
                .actionGet();
        return response;
    }

    /**
     * 删除
     *
     * @param indexName
     */
    @Override
    public void deleteIndex(String indexName,String type,String id) {
        DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("_id",id))
                .source(indexName)
                .execute(new ActionListener<BulkByScrollResponse>() {
                    @Override
                    public void onResponse(BulkByScrollResponse response) {
                        long deleted = response.getDeleted();
                        System.out.println("delete==========="+deleted);
                    }
                    @Override
                    public void onFailure(Exception e) {
                    }
                });
    }

    /**
     * 更新
     *
     * @param indexName
     */
    @Override
    public void updateIndex(String indexName,String type,String id,String json) {
        client.prepareUpdate(indexName,type,id).setDoc();
    }

    /**
     * 查询索引
     *
     * @param indexName
     */
    @Override
    public void queryIndex(String indexName) {

    }

    @Override
    public void updateData(String indexName, String indexType, String documentId, Map map, String parentValue, String routingValue) {
        client.prepareIndex(indexName, indexType, documentId)
                .setParent(parentValue)
                .setRouting(routingValue)
                .setSource(new JSONObject(map).toJSONString()
                )
                .execute()
                .actionGet();
    }
}
