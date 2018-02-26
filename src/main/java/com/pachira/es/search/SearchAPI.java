package com.pachira.es.search;

import com.pachira.es.ElasticsearchClientBase;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

/**
 * 搜索查询，返回查询匹配的结果，搜索一个index / type 或者多个index / type
 * 官方文档 @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search.html'></a>
 * 中文文档 @see <a href='https://es.quanke.name/search-api.html'></a>
 * Created by http://quanke.name on 2017/11/15.
 */
public class SearchAPI extends ElasticsearchClientBase {

    @Test
    public void testPrepareSearch() throws Exception {
        SearchResponse response = client.prepareSearch("answers")//可以是多个index
                .setTypes("list")//可以是多个类型
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("content", "当车辆转弯"))                 // Query 查询条件
//                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter 过滤
                .setFrom(0).setSize(60).setExplain(true)
                .get();

        println(response);
    }
}
