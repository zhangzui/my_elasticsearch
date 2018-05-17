package com.zz.my_es.common;

public interface SearchService {
    void  search();
    void  searchByCondition() throws Exception;
    void  multiSearch();
    void aggsearch();
    void metricsAgg();
}