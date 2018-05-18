package com.zz.myes.service;

import org.elasticsearch.action.index.IndexResponse;

/**
 * 索引操作接口
 * @author zhangzuizui
 * @date 2018/5/18 15:33
 */
public interface IndexService {

    /**
     * 创建索引
     * indexName 索引名
     * type 类型
     * id id
     * @param indexName
     */
    IndexResponse createIndex(String indexName, String type);

    /**
     * 删除索引
     * @param indexName
     */
    void deleteIndex(String indexName,String type,String id,String json);

    /**
     * 更新
     * @param indexName
     */
    void updateIndex(String indexName,String type,String id,String json);

    /**
     * 查询索引
     * @param indexName
     */
    void queryIndex(String indexName,String type,String id,String json);
}
