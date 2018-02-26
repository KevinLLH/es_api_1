package com.pachira.es;

import com.pachira.es.util.Utils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

/**
 * Elasticsearch XPack Client
 * Created by luhui.liu on 2018/2/26.
 */
public class ElasticsearchXPackClient {

    protected TransportClient client;
    @Before
    public void setUp() throws Exception {
        /**
         * 如果es集群安装了x-pack插件则以此种方式连接集群
         * 1. java客户端的方式是以tcp协议在9300端口上进行通信
         * 2. http客户端的方式是以http协议在9200端口上进行通信
         */
        Settings settings = Settings.builder()
                .put("xpack.security.user", "elastic:2$6l7k3T*y47^KkRO04S")
                .put("cluster.name", "elasticsearch")
                .build();
        client = new PreBuiltXPackTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials("elastic", "utan100"));

        System.out.println("ElasticsearchXPackClient 启动成功");
    }

    @Test
    public void testClientConnection() throws Exception {

        System.out.println("--------------------------");
    }

    @After
    public void tearDown() throws Exception {
        if (client != null) {
            client.close();
        }

    }

    protected void println(SearchResponse searchResponse) {
        Utils.println(searchResponse);
    }
}
