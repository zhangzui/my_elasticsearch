package com.zz.my_es.common;


public interface IndexService {
    /**
     * 创建索引
     * @param id
     */
    void  index(String id);

    /**
     * 查询
     */
    void  get();

    /**
     * 删除索引
     * @param id
     */
    void  del(String id);

    /**
     * 更新
     * @param id
     * @throws Exception
     */
    void  update(String id) throws  Exception;

    /**
     * 批量查询
     * @param ids
     * @throws Exception
     */
    void  multiGet(String ... ids) throws  Exception;

    /**
     * bulk相当于数据库里的批量操作
     * bulk一次最大处理多少数据量？
     　bulk会把将要处理的数据载入内存中，所以数据量是有限制的，最佳的数据量不是一个确定的数值，它取决于你的硬件，你的文档大小以及复杂性，你的索引以及搜索的负载。
     　一般建议是1000-5000个文档，如果你的文档很大，可以适当减少队列，大小建议是5-15MB，默认不能超过100M，可以在es的配置文件（即$ES_HOME下的config下的elasticsearch.yml）中。
     * @param ids
     * @throws Exception
     */
    void  bulk(String ... ids) throws  Exception;

    /**
     *
     * @param index
     * @param type
     * @param ids
     * @throws Exception
     */
    void  bulkProcesstor(String index,String type,String... ids) throws  Exception;
}