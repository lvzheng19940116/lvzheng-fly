package flylvzheng.es;

import flylvzheng.bean.Company;
import flylvzheng.repository.CompanyRepository;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 以动手实践为荣,以只看不练为耻.
 * 以打印日志为荣,以出错不报为耻.
 * 以局部变量为荣,以全局变量为耻.
 * 以单元测试为荣,以手工测试为耻.
 * 以代码重用为荣,以复制粘贴为耻.
 * 以多态应用为荣,以分支判断为耻.
 * 以定义常量为荣,以魔法数字为耻.
 * 以总结思考为荣,以不求甚解为耻.
 *
 * @author LvZheng
 * 创建时间：2018/10/31 下午2:34
 */
@RestController
public class EsController {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyEsRepository esRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //增加
    @PostMapping("/esadd")
    public String add() {
        // elasticsearchTemplate.putMapping(CompanyEs.class);
        List<Company> all = companyRepository.findAll();
        List<CompanyEs> list = new ArrayList<CompanyEs>();
        for (Company company : all) {
            CompanyEs companyEs = new CompanyEs();
            companyEs.setCompany(company.getCompany());
            esRepository.save(companyEs);
        }
        return "success";
    }


    /**
     * 调用 ES 获取 IK 分词后结果
     *
     * @param searchContent
     * @return
     */
    @PostMapping("/es")
    public List<String> getIkAnalyzeSearchTerms(@RequestParam("searchContent") String searchContent) {

        //调用 IK 分词分词
        AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(elasticsearchTemplate.getClient(),
                AnalyzeAction.INSTANCE, "companyname", searchContent);
        ikRequest.setTokenizer("ik_max_word");//ik_smart  ik_max_word
        List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();
        // 循环赋值
        List<String> searchTermList = new ArrayList<>();
        ikTokenList.forEach(ikToken -> {
            searchTermList.add(ikToken.getTerm());
        });
        return searchTermList;
    }




    @GetMapping("/esget")
    public Object searchCompany(@RequestParam("company") String company) {
        //创建builder
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //builder下有must、should以及mustNot 相当于sql中的and、or以及not
        //设置模糊搜索,真实姓名中包含金的用户
        // builder.must(QueryBuilders.fuzzyQuery("company", company));
        /**
         * 使用QueryBuilder
         * termQuery("key", obj) 完全匹配
         * termsQuery("key", obj1, obj2..)   一次匹配多个值
         * matchQuery("key", Obj) 单个匹配, field不支持通配符, 前缀具高级特性
         * multiMatchQuery("text", "field1", "field2"..);  匹配多个字段, field有通配符忒行
         * matchAllQuery();         匹配所有文件
         *
         * must(QueryBuilders) :   AND
         * mustNot(QueryBuilders): NOT
         * should:                  : OR
         *
         *    QueryBuilders.idsQuery(String...type).ids(Collection<String> ids)
         *
         *    fuzzyQuery   * 模糊查询* 不能用通配符, 不知道干啥用 fuzzyQuery
         *
         *
         *     * moreLikeThisQuery: 实现基于内容推荐, 支持实现一句话相似文章查询
         *      * {
         *         "more_like_this" : {
         *         "fields" : ["title", "content"],   // 要匹配的字段, 不填默认_all
         *         "like_text" : "text like this one",   // 匹配的文本
         *         }
         *     }
         *
         *     percent_terms_to_match：匹配项（term）的百分比，默认是0.3
         *
         *     min_term_freq：一篇文档中一个词语至少出现次数，小于这个值的词将被忽略，默认是2
         *
         *     max_query_terms：一条查询语句中允许最多查询词语的个数，默认是25
         *
         *     stop_words：设置停止词，匹配时会忽略停止词
         *
         *     min_doc_freq：一个词语最少在多少篇文档中出现，小于这个值的词会将被忽略，默认是无限制
         *
         *     max_doc_freq：一个词语最多在多少篇文档中出现，大于这个值的词会将被忽略，默认是无限制
         *
         *     min_word_len：最小的词语长度，默认是0
         *
         *     max_word_len：最多的词语长度，默认无限制
         *
         *     boost_terms：设置词语权重，默认是1
         *
         *     boost：设置查询权重，默认是1
         *
         *     analyzer：设置使用的分词器，默认是使用该字段指定的分词器
         *     QueryBuilders.moreLikeThisQuery("user")
         *                             .like("kimchy");
         *                             .minTermFreq(1)         //最少出现的次数
         *                             .maxQueryTerms(12);
         **/

        builder.must(QueryBuilders.queryStringQuery(company));

        //完全匹配
        // builder.must(QueryBuilders.termQuery("company", company));

        //范围匹配
        //builder.must(QueryBuilders.rangeQuery("stockCount").lt(99999999).gt(0));

        //设置用户名为king
        // builder.must(new QueryStringQueryBuilder("king").field("username"));

        //排序
        // FieldSortBuilder sort = SortBuilders.fieldSort("age").order(SortOrder.DESC);

        //设置分页
        //====注意!es的分页和Hibernate一样api是从第0页开始的=========
        // PageRequest page = new PageRequest(0, 2);
        PageRequest page = PageRequest.of(0, 100);

        //构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(page);
        //将排序设置到构建中
        //  nativeSearchQueryBuilder.withSort(sort);

        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //执行,返回包装结果的分页
        Page<CompanyEs> search = esRepository.search(query);

        return search;

    }


}
