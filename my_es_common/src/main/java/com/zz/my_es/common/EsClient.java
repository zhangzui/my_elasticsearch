package com.zz.my_es.common;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Level;
import org.joda.time.ReadablePeriod;
import com.fasterxml.jackson.core.JsonFactory;
//import org.apache.lucene.search.CoveringQuery;
//import org.elasticsearch.script.ScriptEngineService;

/**
 * Created by xiaotian on 2017/12/1.
 */
@Component
public class EsClient {

    /**
     * elasticsearch的地址
     */
    private static final String host = "127.0.0.1";

    /**
     * elasticsearch的端口
     */
    private static final Integer port = 9300;
    private Client client;

    public EsClient() {
        TransportClient client = null;
        try {
            Settings settings = Settings.builder().put("cluster.name", "elastictest").build();
            this.client = new PreBuiltTransportClient(settings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Client getClient() {
        return client;
    }
}