package com.zz.my_es.common;

public interface SearchService {
    /**
     * 普通查询
     */
    void  search();

    /**
     * 条件查询
     * @throws Exception
     */
    void  searchByCondition() throws Exception;

    /**
     *
     */
    void  multiSearch();

    /**
     *
     */
    void aggsearch();

    /**
     *
     */
    void metricsAgg();
}