package com.zz.my_es.common;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

import java.io.IOException;
/**
 * @author zhangzuizui
 * @date 2018/5/17 10:11
 */
public class TestEs {

    private static Client client = new EsClient().getClient();

    @Test
    public void createIndex() throws IOException {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        IndexResponse response = client.prepareIndex("twitter", "tweet")
                .setSource(json, XContentType.JSON)
                .get();
    }

    @Test
    public void searchTest001() {
        SearchResponse searchResponse = client.prepareSearch("sina", "bog").get();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        System.out.println(JSON.toJSONString(searchHists));
    }

    @Test
    public void searchTest002() {
        SearchResponse searchResponse = client.prepareSearch("sina", "bog")
                .setTypes("type1", "type2")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                // Query
                .setQuery(QueryBuilders.termQuery("multi", "test"))
                // Filter
                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))
                .setFrom(0).setSize(60).setExplain(true)
                .get();

        //.setScroll(new TimeValue(10000 ))
        QueryBuilder query = QueryBuilders.matchAllQuery();
        SearchResponse searchResponse001 = client.prepareSearch("sasa").setTypes("").setQuery(query)
                .setFrom(0).setSize(10000).execute().actionGet();

        SearchHits hits = searchResponse.getHits();
        SearchHits hits2 = searchResponse001.getHits();
        SearchHit[] searchHists = hits.getHits();
        SearchHit[] searchHists2 = hits2.getHits();
        System.out.println("查询到记录数=" + hits.getTotalHits() + "=====" + searchHists.length);
        System.out.println("查询到记录数=" + hits2.getTotalHits() + "=====" + searchHists2.length);
    }

    @Test
    public void test001(){
//        Map<String,Object> map = Collections.emptyMap();
//        //提前定义好查询销量是否大于1000的脚本，类似SQL里面的having
//        Script script = new Script(ScriptType.INLINE, "painless","params._value0 > 0",map);
//
//        long beginTime = System.currentTimeMillis();
//        SearchResponse sr = client.prepareSearch("dm_di").setTypes("sale")
//                .setQuery(QueryBuilders.boolQuery()
//                        //挨个设置查询条件，没有就不加，如果是字符串类型的，要加keyword后缀
//                        .must(QueryBuilders.termQuery("store_name.keyword", "xxx旗舰店"))
//                        .must(QueryBuilders.termQuery("department_name.keyword", "玎"))
//                        .must(QueryBuilders.termQuery("category_name.keyword", "T恤"))
//                        .must(QueryBuilders.rangeQuery("pay_date").gt("2017-03-07").lt("2017-07-09"))
//                ).addAggregation(
//                        //按货号分组，最多查500个货号.SKU直接改字段名字就可以
//                        AggregationBuilders.terms("by_product_code").field("product_code.keyword").size(500)
//                                //按店铺分组，不显示店铺可以过滤掉这一行，下边相应减少一个循环
//                                .subAggregation(AggregationBuilders.terms("by_store_name").field("store_name.keyword").size(50)
//                                        //分组计算销量汇总
//                                        .subAggregation(AggregationBuilders.sum("total_sales").field("quantity"))
//                                        //分组计算实付款汇总，需要加其他汇总的在这里依次加
//                                        .subAggregation(AggregationBuilders.sum("total_sales_amount").field("amount_actual"))
//                                        //查询是否大于指定值
//                                        .subAggregation(PipelineAggregatorBuilders.bucketSelector("sales_bucket_filter",script,"total_sales")))
//                                //分组排序
//                                .order(Terms.getBucketByKey().compound(Terms.Order.aggregation("total_calculate_sale_amount",false))))
//
//                .execute().actionGet();
//        //查询遍历第一个根据货号分组的aggregation
//        Terms terms = sr.getAggregations().get("by_product_code");
//
//        System.out.println(terms.getBuckets().size());
//        for (Terms.Bucket entry : terms.getBuckets()) {
//            System.out.println("------------------");
//            System.out.println("【 " + entry.getKey() + " 】订单数 : " + entry.getDocCount() );
//            //查询遍历第二个根据店铺分组的aggregation
//            Terms subTerms = entry.getAggregations().get("by_store_name");
//            for (Terms.Bucket subEntry : subTerms.getBuckets()) {
//                //取得销量的汇总
//                Sum sum1 = subEntry.getAggregations().get("total_sales");
//                double total_sales = sum1.getValue();
//                //店铺和订单数量和销量
//                System.out.println(subEntry.getKey() + " 订单数:  " + subEntry.getDocCount() + "  销量: " + total_sales);
//            }
//        }
//
//        long endTime = System.currentTimeMillis();
//        System.out.println("查询耗时" + ( endTime - beginTime ) + "毫秒");
    }
}
