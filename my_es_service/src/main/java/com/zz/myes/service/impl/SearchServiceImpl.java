package com.zz.myes.service.impl;

import com.zz.my_es.common.EsClient;
import com.zz.myes.service.SearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author zhangzuizui
 * @date 2018/5/18 15:48
 */
public class SearchServiceImpl implements SearchService{
    /**
     * ES客户端
     */
    private Client client = new EsClient().getClient();

    /**
     * 全文索引
     */
    @Override
    public SearchResponse allSearch(String indexName) {
        SearchResponse searchResponse = client.prepareSearch(indexName).get();
        return searchResponse;
    }

    /**
     * 条件查询
     */
    @Override
    public void conditionSearch(String indexName,String type) {
        SearchResponse searchResponse = client.prepareSearch(indexName)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                // Query
                .setQuery(QueryBuilders.termQuery("multi", "test"))
                // Filter
                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))
                .setFrom(0).setSize(60).setExplain(true)
                .get();
    }

    /**
     * 批量查询
     */
    @Override
    public void breachSearch() {

    }

    /**
     * 统计查询
     */
    @Override
    public void groupSearch() {

    }
}
