package com.zz.my_es.common;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

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
            //client.transport.sniff true 添加es集群名，自动嗅探，无需添加ip和端口
            Settings settings = Settings.builder().put("cluster.name", "elastictest").put("client.transport.sniff",true).build();
            this.client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Client getClient() {
        return client;
    }
}