package com.zz.myes.service;

import org.elasticsearch.action.search.SearchResponse;

/**
 * @author zhangzuizui
 * @date 2018/5/18 15:48
 */
public interface SearchService {

    /**
     * 全文索引
     */
    SearchResponse allSearch(String indexName);

    /**
     * 条件查询
     */
    void conditionSearch(String indexName,String type);

    /**
     * 批量查询
     */
    void breachSearch();

    /**
     * 统计查询
     */
    void groupSearch();

}
