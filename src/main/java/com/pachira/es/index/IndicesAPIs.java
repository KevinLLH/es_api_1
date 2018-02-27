package com.pachira.es.index;

import com.pachira.es.ElasticsearchRestClient;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;

/**
 * 索引相关操作
 * Created by luhui.liu on 2018/2/27.
 */
public class IndicesAPIs extends ElasticsearchRestClient{

    /**
     * 创建index
     */
    @Test
    public void CreateIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("twitter");
        CreateIndexRequest settings = request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );
        request.mapping("tweet",
                "  {\n" +
                        "    \"tweet\": {\n" +
                        "      \"properties\": {\n" +
                        "        \"message\": {\n" +
                        "          \"type\": \"text\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }",
                XContentType.JSON);
        //client.indices().createAsync(request, listener);

        CreateIndexResponse createIndexResponse = client.indices().create(request);

    }


    /**
     * 删除索引
     */
    @Test
    public void DeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("twitter");
        DeleteIndexResponse deleteIndexResponse = client.indices().delete(request);
    }


}
