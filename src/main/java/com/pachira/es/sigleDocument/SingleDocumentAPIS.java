package com.pachira.es.sigleDocument;

import com.pachira.es.ElasticsearchRestClient;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Document相关操作
 * Created by luhui.liu on 2018/2/27.
 */
public class SingleDocumentAPIS extends ElasticsearchRestClient {

    /**
     * 向index中插入数据
     *
     * @throws IOException
     */
    @Test
    public void IndexRequest() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "kimchy");
            builder.field("postDate", new Date());
            builder.field("message", "trying out Elasticsearch");
        }
        builder.endObject();
        IndexRequest indexRequest = new IndexRequest("twitter", "doc", "1")
                .source(builder);


        IndexResponse indexResponse = client.index(indexRequest);

        //Asynchronous
        //client.indexAsync(request, listener);


        //Response
        String index = indexResponse.getIndex();
        String type = indexResponse.getType();
        String id = indexResponse.getId();
        long version = indexResponse.getVersion();
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            System.out.println("[com.pachira.es.sigleDocument.SingleDocumentAPIS]CREATE SUCCESS！");
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            System.out.println("[com.pachira.es.sigleDocument.SingleDocumentAPIS]UPDATE SUCCESS！");
        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {

        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();
                System.out.println("[com.pachira.es.sigleDocument.SingleDocumentAPIS]CREATE FAILURE！" + reason);
            }
        }
    }


    /**
     * 删除文档
     */
    @Test
    public void DeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest(
                "posts",
                "doc",
                "1");
        DeleteResponse deleteResponse = client.delete(request);

        //response
        String index = deleteResponse.getIndex();
        String type = deleteResponse.getType();
        String id = deleteResponse.getId();
        long version = deleteResponse.getVersion();
        ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
        if(shardInfo.getTotal()!=shardInfo.getSuccessful())
        {

        }
        if(shardInfo.getFailed()>0)

        {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();
            }
        }
    }

    //更新Document
    @Test
    public void UpdateDocument() throws IOException {

        UpdateRequest request = new UpdateRequest("posts", "doc", "1")
                .doc("updated", new Date(),
                        "reason", "daily update");

        UpdateResponse updateResponse = client.update(request);

        String index = updateResponse.getIndex();
        String type = updateResponse.getType();
        String id = updateResponse.getId();
        long version = updateResponse.getVersion();
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {

        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {

        } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {

        } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {

        }
    }




}
