package com.zz.myes.service.domain.req;

import java.util.Map;

/**
 * @author zhangzuizui
 * @date 2018/5/21 11:10
 */
public class EsReq {
    private String indexName;
    private String indexType;
    private String documentId;
    private Map map;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
