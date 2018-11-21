//package flylvzheng.es;
//
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.SearchResultMapper;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.web.bind.annotation.RestController;
//
//
///**
// * 以动手实践为荣,以只看不练为耻.
// * 以打印日志为荣,以出错不报为耻.
// * 以局部变量为荣,以全局变量为耻.
// * 以单元测试为荣,以手工测试为耻.
// * 以代码重用为荣,以复制粘贴为耻.
// * 以多态应用为荣,以分支判断为耻.
// * 以定义常量为荣,以魔法数字为耻.
// * 以总结思考为荣,以不求甚解为耻.
// *
// * @author LvZheng
// * 创建时间：2018/11/1 下午5:36
// */
//@RestController
//public class HighController {
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    public List<CompanyEs> highLigthQuery(String field, String searchMessage) {
//        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.termQuery(field, searchMessage))
//                .withHighlightFields(new HighlightBuilder.Field(field)).build();
//        Page<CompanyEs> page = elasticsearchTemplate.queryForPage(searchQuery, CompanyEs.class, new SearchResultMapper() {
//
//            @Override
//            public <T> Page<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
//                ArrayList<CompanyEs> CompanyEss = new ArrayList<CompanyEs>();
//                SearchHits hits = response.getHits();
//                for (SearchHit searchHit : hits) {
//                    if (hits.getHits().length <= 0) {
//                        return null;
//                    }
//                    CompanyEs CompanyEs = new CompanyEs();
//                    String highLightMessage = searchHit.getHighlightFields().get(field).fragments()[0].toString();
//                    CompanyEs.setId(searchHit.getId());
//                    CompanyEs.setCompany(String.valueOf(searchHit.getSource().get("company")));
//                    // 反射调用set方法将高亮内容设置进去
//                    try {
//                        String setMethodName = parSetName(field);
//                        Class<? extends CompanyEs> CompanyEsClazz = CompanyEs.getClass();
//                        Method setMethod = CompanyEsClazz.getMethod(setMethodName, String.class);
//                        setMethod.invoke(CompanyEs, highLightMessage);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    CompanyEss.add(CompanyEs);
//                }
//                if (CompanyEss.size() > 0) {
//                    return new PageImpl<T>((List<T>) CompanyEss);
//                }
//                return null;
//            }
//        });
//        List<CompanyEs> CompanyEss = page.getContent();
//        return CompanyEss;
//    }
//
//    /**
//     * 拼接在某属性的 set方法
//     *
//     * @param fieldName
//     * @return String
//     */
//    private static String parSetName(String fieldName) {
//        if (null == fieldName || "".equals(fieldName)) {
//            return null;
//        }
//        int startIndex = 0;
//        if (fieldName.charAt(0) == '_')
//            startIndex = 1;
//        return "set" + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
//                + fieldName.substring(startIndex + 1);
//    }
//
//}
