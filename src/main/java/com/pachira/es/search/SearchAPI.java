package com.pachira.es.search;

import com.pachira.es.ElasticsearchClientBase;
import com.pachira.es.ElasticsearchRestClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 搜索查询，返回查询匹配的结果，搜索一个index / type 或者多个index / type
 * 官方文档 @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search.html'></a>
 * 中文文档 @see <a href='https://es.quanke.name/search-api.html'></a>
 * Created by http://quanke.name on 2017/11/15.
 */
public class SearchAPI extends ElasticsearchRestClient {

    private String index;

    private String type;

    @Before
    public void prepare() {
        index = "twitter";
        type = "doc";
    }

    @Test
    public void searchTest(){
        SearchSourceBuilder sourceBuilder;
        sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));//延迟
        sourceBuilder.fetchSource(new String[]{"content","type"}, new String[]{});//过滤内容
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("content", "空调");
        //TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("content", "开锁");
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(matchQueryBuilder);
        //boolBuilder.must(termQueryBuilder);
        sourceBuilder.query(boolBuilder);
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response = client.search(searchRequest);
            System.out.println(response);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
