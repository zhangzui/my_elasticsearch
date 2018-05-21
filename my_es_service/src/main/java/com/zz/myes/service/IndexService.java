package com.zz.myes.service;

import org.elasticsearch.action.index.IndexResponse;

import java.util.Map;

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
     * 插入数据
     * @param indexName
     * @param type
     * @param id
     * @param map
     * @return
     */
    IndexResponse insertData(String indexName, String type, String id, Map map);

    /**
     * 删除索引
     * @param indexName
     */
    void deleteIndex(String indexName,String type,String id);

    /**
     * 更新
     * @param indexName
     */
    void updateIndex(String indexName,String type,String id,String json);

    /**
     * 查询索引信息
     * @param indexName
     */
    void queryIndex(String indexName);

    /**
     * 父子关系更新
     * @param indexName
     * @param indexType
     * @param documentId
     * @param map
     * @param parentValue
     * @param routingValue
     */
    void updateData(String indexName, String indexType, String documentId, Map map, String parentValue, String routingValue);
}
