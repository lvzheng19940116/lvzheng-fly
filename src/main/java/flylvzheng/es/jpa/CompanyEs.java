//package flylvzheng.es.jpa;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Mapping;
//import org.springframework.data.elasticsearch.annotations.Setting;
//
//import java.io.Serializable;
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
// * 创建时间：2018/10/31 下午2:27
// */
//@Data
//@Document(indexName = "companyname", type = "name", shards = 1, replicas = 1)
//public class CompanyEs implements Serializable {
//    @Id
//   // @Field( searchAnalyzer = "ik", analyzer = "ik")
//    private String id;
//    private String company;
//}
