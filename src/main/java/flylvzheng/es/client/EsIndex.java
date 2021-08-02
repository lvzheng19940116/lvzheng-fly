//package flylvzheng.es.client;
//
//import com.alibaba.fastjson.JSON;
//import org.elasticsearch.action.DocWriteResponse;
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.text.Text;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.MultiMatchQueryBuilder;
//import org.elasticsearch.index.query.Operator;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.TermQueryBuilder;
//import org.elasticsearch.rest.RestStatus;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
//import org.elasticsearch.search.sort.FieldSortBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author LvZheng
// * 创建时间：2021/3/10 上午11:32
// */
//public class EsIndex {
//
//    private RestHighLevelClient client;
//
//    /**
//     *  elasticsearch-rest-high-level-client
//     *  创建索引 和 映射
//     *  删除索引
//     *  插入文档
//     *  更新文档
//     *  删除文档
//     *  查询文档  根据ID查询
//     *  搜索管理  查询所有文档
//     *  搜索管理  分页查询
//     *  搜索管理  Term Query精确查找 ，在搜索时会整体匹配关键字，不再将关键字分词
//     *  搜索管理 根据ID查询
//     *  搜索管理 match query 先分词后查找
//     *  搜索管理 match query 先分词后查找 minimum_should_match  （如果实现三个词至少有两个词匹配如何实现）
//     *  搜索管理 同时搜索多个Field
//     *  搜索管理 布尔查询
//     *  搜索管理 过滤器
//     *  排序
//     *  高亮显示
//     *
//     *
//     * */
//
//
//    /**
//     * 创建索引 和 映射
//     *
//     * @throws IOException
//     */
//    public void addIndex() throws IOException {
//
//        //创建名称为blog2的索
//        CreateIndexRequest request = new CreateIndexRequest("blog1");
//
//        //设置映射 doc type名称
//        request.mapping("doc", " {\n" +
//                " \t\"properties\": {\n" +
//                "           \"name\": {\n" +
//                "              \"type\": \"text\",\n" +
//                "              \"analyzer\":\"ik_max_word\",\n" +
//                "              \"search_analyzer\":\"ik_smart\"\n" +
//                "           },\n" +
//                "           \"description\": {\n" +
//                "              \"type\": \"text\",\n" +
//                "              \"analyzer\":\"ik_max_word\",\n" +
//                "              \"search_analyzer\":\"ik_smart\"\n" +
//                "           },\n" +
//                "           \"studymodel\": {\n" +
//                "              \"type\": \"keyword\"\n" +
//                "           },\n" +
//                "           \"price\": {\n" +
//                "              \"type\": \"float\"\n" +
//                "           }\n" +
//                "        }\n" +
//                "}", XContentType.JSON);
//
//
//        CreateIndexResponse createIndexResponse = client.indices().create(request);
//        System.out.println(JSON.toJSONString(createIndexResponse));
//
//        //释放资源
//        client.close();
//    }
//
//    /**
//     * 删除索引
//     */
//    //删除索引库
//    public void testDeleteIndex() throws IOException {
//        //删除索引请求对象
//        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("blog2");
//        //删除索引
//        DeleteIndexResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest);
//        //删除索引响应结果
//        boolean acknowledged = deleteIndexResponse.isAcknowledged();
//        System.out.println(acknowledged);
//    }
//
//    /**
//     * 插入文档
//     */
//
//    public void testAddDocument() throws IOException {
//        //准备json数据
//        Map<String, Object> jsonMap = new HashMap<>();
//        jsonMap.put("name", "11spring cloud实战");
//        jsonMap.put("description", "11本课程主要从四个章节进行讲解： 1.微服务架构入门 2.spring cloud基础入门 3.实战Spring Boot 4.注册中心eureka。");
//        jsonMap.put("studymodel", "201001");
//        jsonMap.put("price", 5.6f);
//
//        //索引请求对象
//        IndexRequest indexRequest = new IndexRequest("blog1", "doc").id("11");
//        //指定索引文档内容
//        indexRequest.source(jsonMap);
//
//        //索引响应对象
//        IndexResponse index = client.index(indexRequest);
//
//        //获取响应结果
//        DocWriteResponse.Result result = index.getResult();
//        System.out.println(result);
//
//    }
//
//    /**
//     * 查询文档  根据ID查询
//     */
//
//    public void getDocumentById() throws IOException {
//        GetRequest getRequest = new GetRequest("blog1", "doc", "11");
//
//        GetResponse response = client.get(getRequest);
//
//        boolean exists = response.isExists();
//
//        Map<String, Object> sourceAsMap = response.getSourceAsMap();
//        System.out.println(sourceAsMap);
//
//    }
//
//    /**
//     * 更新文档
//     */
//
//    public void updateDocument() throws IOException {
//        UpdateRequest updateRequest = new UpdateRequest("blog1", "doc", "11");
//
//        Map<String, String> map = new HashMap<>();
//        map.put("name", "spring cloud实战222");
//        updateRequest.doc(map);
//
//        UpdateResponse update = client.update(updateRequest);
//
//        RestStatus status = update.status();
//
//        System.out.println(status);
//    }
//
//    /**
//     * 删除文档
//     */
//
//    public void deleteDocument() throws IOException {
//        String id = "AXIDNJoC19Z06cvw7_Gv";
//        DeleteRequest deleteRequest = new DeleteRequest("blog2", "doc", id);
//
//        DeleteResponse delete = client.delete(deleteRequest);
//
//        System.out.println(delete.status());
//
//    }
//
//    /**
//     * 搜索管理  查询所有文档
//     */
//
//    public void testSearchAll() throws IOException {
//
//        SearchRequest searchRequest = new SearchRequest("blog1");
//        searchRequest.types("doc");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        //source源字段过虑
//        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel", "description"}, new String[]{});
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse searchResponse = client.search(searchRequest);
//        SearchHits hits = searchResponse.getHits();
//        long totalHits = hits.getTotalHits();
//
//        System.out.println("total=" + totalHits);
//
//        SearchHit[] searchHits = hits.getHits();
//        for (SearchHit searchHit : searchHits) {
//            String index = searchHit.getIndex();
//            System.out.println("index=" + index);
//            String id = searchHit.getId();
//            System.out.println("id=" + id);
//            String sourceAsString = searchHit.getSourceAsString();
//            System.out.println(sourceAsString);
//            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
//            //String articleId = String.valueOf( sourceAsMap.get( "id" ) );
//            String title = (String) sourceAsMap.get("name");
//            String content = (String) sourceAsMap.get("description");
//            //System.out.println("articleId="+articleId);
//            System.out.println("title=" + title);
//            System.out.println("content=" + content);
//        }
//
//    }
//
//    /**
//     * 搜索管理  分页查询
//     */
//
//    public void testSearchByPage() throws IOException {
//        //设置要查询的索引 和 type
//        SearchRequest searchRequest = new SearchRequest("blog1");
//        searchRequest.types("doc");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        //分页查询，设置起始下标，从0开始
//        searchSourceBuilder.from(0);
//        //每页显示个数
//        searchSourceBuilder.size(2);
//        //source源字段过虑
//        //searchSourceBuilder.fetchSource(new String[]{"title","id","content"},new String[]{}  );
//        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel", "description"}, new String[]{});
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse search = client.search(searchRequest);
//        SearchHits hits = search.getHits();
//        long totalHits = hits.getTotalHits();
//
//        System.out.println(totalHits);
//        for (SearchHit searchHit : hits.getHits()) {
//            String sourceAsString = searchHit.getSourceAsString();
//            System.out.println(sourceAsString);
//        }
//    }
//
//
//    /**
//     * 搜索管理  Term Query精确查找 ，在搜索时会整体匹配关键字，不再将关键字分词 ，
//     * 下面的语句：查询title 包含 spring 的文档
//     */
//
//    public void testSearchTerm() throws IOException {
//        //创建查询，设置索引
//        SearchRequest searchRequest = new SearchRequest("blog1");
//        //设置type
//        searchRequest.types("doc");
//        //设置查询条件
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.termQuery("name", "spring"));
//        //source源字段过虑
//        //searchSourceBuilder.fetchSource(new String[]{"title","id","content"},new String[]{});
//        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel", "description"}, new String[]{});
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse search = client.search(searchRequest);
//        SearchHits hits = search.getHits();
//        long totalHits = hits.getTotalHits();
//        System.out.println("总条数：" + totalHits);
//
//        for (SearchHit searchHit : hits.getHits()) {
//            String sourceAsString = searchHit.getSourceAsString();
//            System.out.println(sourceAsString);
//        }
//    }
//
//    /**
//     * 搜索管理 根据ID查询
//     */
//
//    public void testSearchByID() throws IOException {
//        //创建查询，设置索引
//        SearchRequest searchRequest = new SearchRequest("blog1");
//        //设置type
//        searchRequest.types("doc");
//        //设置查询条件
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        String[] ids = {"11", "12"};
//        List<String> stringList = Arrays.asList(ids);
//        searchSourceBuilder.query(QueryBuilders.termsQuery("_id", stringList));
//        //source源字段过虑
//        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel", "description"}, new String[]{});
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse search = client.search(searchRequest);
//        SearchHits hits = search.getHits();
//        long totalHits = hits.getTotalHits();
//        System.out.println("总条数：" + totalHits);
//
//        for (SearchHit searchHit : hits.getHits()) {
//            String sourceAsString = searchHit.getSourceAsString();
//            System.out.println(sourceAsString);
//        }
//    }
//
//    /**
//     * 搜索管理 match query 先分词后查找
//     */
//
//    public void testSearchMatch() throws IOException {
//        //创建查询，设置索引
//        SearchRequest searchRequest = new SearchRequest("blog1");
//        //设置type
//        searchRequest.types("doc");
//        //设置查询条件
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        //匹配关键字
//        searchSourceBuilder.query(QueryBuilders.matchQuery("name", "spring开发").operator(Operator.OR));
//        //source源字段过虑
//        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel", "description"}, new String[]{});
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse search = client.search(searchRequest);
//        SearchHits hits = search.getHits();
//        long totalHits = hits.getTotalHits();
//        System.out.println("总条数：" + totalHits);
//
//        for (SearchHit searchHit : hits.getHits()) {
//            String sourceAsString = searchHit.getSourceAsString();
//            System.out.println(sourceAsString);
//        }
//    }
//
//
//    /**
//     * 搜索管理 match query 先分词后查找 minimum_should_match  （如果实现三个词至少有两个词匹配如何实现）
//     */
//
//    public void testSearchMatchMinimnum() throws IOException {
//        //创建查询，设置索引
//        SearchRequest searchRequest = new SearchRequest("blog1");
//        //设置type
//        searchRequest.types("doc");
//        //设置查询条件
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        //匹配关键字
//        searchSourceBuilder.query(QueryBuilders.matchQuery("name", "spring开发").minimumShouldMatch("60%"));
//        //source源字段过虑
//        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel", "description"}, new String[]{});
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse search = client.search(searchRequest);
//        SearchHits hits = search.getHits();
//        long totalHits = hits.getTotalHits();
//        System.out.println("总条数：" + totalHits);
//
//        for (SearchHit searchHit : hits.getHits()) {
//            String sourceAsString = searchHit.getSourceAsString();
//            System.out.println(sourceAsString);
//        }
//    }
//
//
//    /**
//     * 搜索管理 同时搜索多个Field
//     */
//
//    public void testSearchMultiMatch() throws IOException {
//        //创建查询，设置索引
//        SearchRequest searchRequest = new SearchRequest("blog1");
//        //设置type
//        searchRequest.types("doc");
//        //设置查询条件
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        //匹配关键字
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("Boot 开发", "name", "description")
//                .minimumShouldMatch("50%");
//        multiMatchQueryBuilder.field("name", 10);//提升boost 表示权重提升10倍
//
//        searchSourceBuilder.query(multiMatchQueryBuilder);
//        //source源字段过虑
//        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel", "description"}, new String[]{});
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse search = client.search(searchRequest);
//        SearchHits hits = search.getHits();
//        long totalHits = hits.getTotalHits();
//        System.out.println("总条数：" + totalHits);
//
//        for (SearchHit searchHit : hits.getHits()) {
//            String sourceAsString = searchHit.getSourceAsString();
//            System.out.println(sourceAsString);
//        }
//    }
//
//    /**
//     * 搜索管理 布尔查询
//     */
//
//    public void testSearchBoolean() throws IOException {
//        //创建搜索请求对象
//        SearchRequest searchRequest = new SearchRequest("blog1");
//        //设置type
//        searchRequest.types("doc");
//        // 创建搜索源配置对象
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        //source源字段过虑
//        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel", "description"}, new String[]{});
//        //multiQuery
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("Boot 开发", "name", "description")
//                .minimumShouldMatch("50%");
//        multiMatchQueryBuilder.field("name", 10);//提升boost 表示权重提升10倍
//        //TermQuery
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("studymodel", "201001");
//        // 布尔查询
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(multiMatchQueryBuilder);
//        boolQueryBuilder.must(termQueryBuilder);
//        //设置布尔查询对象
//        searchSourceBuilder.query(boolQueryBuilder);
//        //设置搜索源配置
//        searchRequest.source(searchSourceBuilder);
//        //搜索
//        SearchResponse search = client.search(searchRequest);
//        SearchHits hits = search.getHits();
//        long totalHits = hits.getTotalHits();
//        System.out.println("总条数：" + totalHits);
//
//        for (SearchHit searchHit : hits.getHits()) {
//            String sourceAsString = searchHit.getSourceAsString();
//            System.out.println(sourceAsString);
//        }
//    }
//
//
//    /**
//     * 搜索管理 过滤器
//     */
//
//    public void testSearchFilter() throws IOException {
//        //创建搜索请求对象
//        SearchRequest searchRequest = new SearchRequest("blog1");
//        //设置type
//        searchRequest.types("doc");
//        //创建搜索源配置对象
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        //source源字段过虑
//        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel", "description"}, new String[]{});
//        //multiQuery 多field查询
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("Boot 开发", "name", "description")
//                .minimumShouldMatch("50%");
//        multiMatchQueryBuilder.field("name", 10);//提升boost 表示权重提升10倍
//        //多Field查询，添加到搜索源配置对象中
//        searchSourceBuilder.query(multiMatchQueryBuilder);
//
//        //TermQuery
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("studymodel", "201001");
//        //Term查询，添加到搜索源配置对象中
//        searchSourceBuilder.query(termQueryBuilder);
//
//        // 布尔查询
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(searchSourceBuilder.query());
//
//        //过虑
//        boolQueryBuilder.filter(QueryBuilders.termQuery("studymodel", "201001"));
//        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(5).lte(100));
//
//        //设置布尔查询对象
//        searchSourceBuilder.query(boolQueryBuilder);
//        //设置搜索源配置
//        searchRequest.source(searchSourceBuilder);
//        //搜索
//        SearchResponse search = client.search(searchRequest);
//        SearchHits hits = search.getHits();
//        long totalHits = hits.getTotalHits();
//        System.out.println("总条数：" + totalHits);
//
//        for (SearchHit searchHit : hits.getHits()) {
//            String sourceAsString = searchHit.getSourceAsString();
//            System.out.println(sourceAsString);
//        }
//    }
//
//    /**
//     * 排序
//     *
//     * @throws IOException
//     */
//
//    public void testSort() throws IOException {
//        SearchRequest searchRequest = new SearchRequest("blog1");
//        searchRequest.types("doc");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        //source源字段过虑
//        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel", "price", "description"}, new String[]{});
//        searchRequest.source(searchSourceBuilder);
//        //布尔查询
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        //过虑
//        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(0).lte(100));
//        // 排序
//        searchSourceBuilder.sort(new FieldSortBuilder("studymodel").order(SortOrder.DESC));
//        searchSourceBuilder.sort(new FieldSortBuilder("price").order(SortOrder.ASC));
//        //搜索
//        SearchResponse searchResponse = client.search(searchRequest);
//        SearchHits hits = searchResponse.getHits();
//        SearchHit[] searchHits = hits.getHits();
//        for (SearchHit hit : searchHits) {
//            String sourceAsString = hit.getSourceAsString();
//            System.out.println(sourceAsString);
//        }
//
//    }
//
//    /**
//     * 高亮显示
//     *
//     * @throws IOException
//     */
//
//    public void testHighlight() throws IOException {
//        SearchRequest searchRequest = new SearchRequest("blog1");
//        searchRequest.types("doc");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        //source源字段过虑
//        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel", "price", "description"}, new String[]{});
//        searchRequest.source(searchSourceBuilder);
//        //匹配关键字
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("实战", "name", "description");
//        searchSourceBuilder.query(multiMatchQueryBuilder);
//        //布尔查询
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(searchSourceBuilder.query());
//        //过虑
//        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(0).lte(100));
//        //排序
//        searchSourceBuilder.sort(new FieldSortBuilder("studymodel").order(SortOrder.DESC));
//        searchSourceBuilder.sort(new FieldSortBuilder("price").order(SortOrder.ASC));
//        //高亮设置
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.preTags("<tag>");//设置前缀
//        highlightBuilder.postTags("</tag>");//设置后缀
//        // 设置高亮字段
//        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
//        //highlightBuilder.fields().add(new HighlightBuilder.Field("description"));
//        searchSourceBuilder.highlighter(highlightBuilder);
//
//        SearchResponse searchResponse = client.search(searchRequest);
//        SearchHits hits = searchResponse.getHits();
//        SearchHit[] searchHits = hits.getHits();
//        for (SearchHit hit : searchHits) {
//            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
//            //名称
//            String name = (String) sourceAsMap.get("name");
//            //取出高亮字段内容
//            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
//            if (highlightFields != null) {
//                HighlightField nameField = highlightFields.get("name");
//                if (nameField != null) {
//                    Text[] fragments = nameField.getFragments();
//                    StringBuffer stringBuffer = new StringBuffer();
//                    for (Text str : fragments) {
//                        stringBuffer.append(str.string());
//                    }
//                    name = stringBuffer.toString();
//                }
//            }
//            System.out.println("高亮==" + name);
//
//            String sourceAsString = hit.getSourceAsString();
//            System.out.println(sourceAsString);
//        }
//    }
//
//}
