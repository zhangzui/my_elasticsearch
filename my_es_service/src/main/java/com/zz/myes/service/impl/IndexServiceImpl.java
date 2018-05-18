package com.zz.myes.service.impl;

import com.zz.my_es.common.EsClient;
import com.zz.myes.service.IndexService;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;

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

    /**
     * 删除
     *
     * @param indexName
     */
    @Override
    public void deleteIndex(String indexName,String type,String id,String json) {
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
    public void queryIndex(String indexName,String type,String id,String json) {
        GetResponse response = client.prepareGet(indexName, type, id).get();
    }
}
