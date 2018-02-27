package com.pachira.es;

import com.pachira.es.util.Utils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.After;
import org.junit.Before;

/**
 * Created by luhui.liu on 2018/2/27.
 */
public class ElasticsearchRestClient {
    protected RestHighLevelClient client;
    @Before
    public void setUp() throws Exception {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        System.out.println("ElasticsearchRestClient 启动成功");
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
