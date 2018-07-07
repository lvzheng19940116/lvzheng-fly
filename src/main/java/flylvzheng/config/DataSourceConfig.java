//package flylvzheng.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
///**
// * 
//*以动手实践为荣,以只看不练为耻.
//*以打印日志为荣,以出错不报为耻.
//*以局部变量为荣,以全局变量为耻.
//*以单元测试为荣,以手工测试为耻.
//*以代码重用为荣,以复制粘贴为耻.
//*以多态应用为荣,以分支判断为耻.
//*以定义常量为荣,以魔法数字为耻.
//*以总结思考为荣,以不求甚解为耻.
//*
//*@author： LvZheng
//* 创建时间：2018年7月4日 下午3:47:36
// */
//@Configuration
//public class DataSourceConfig {
//
//    @Bean(name = "primaryDataSource")
//    @Qualifier("primaryDataSource")
//    @Primary
//    @ConfigurationProperties(prefix="spring.datasource.primary")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
////	@Bean
////    @Primary
////    @Qualifier("primaryDataSource")
////    @ConfigurationProperties(prefix = "spring.datasource.primary")
////    public DataSourceProperties primaryDataSourceProperties(){
////        return new DataSourceProperties();
////    }
//    @Bean(name = "secondaryDataSource")
//    @Qualifier("secondaryDataSource")
//    @ConfigurationProperties(prefix="spring.datasource.secondary")
//    public DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }   
////	@Bean
////    @Primary
////    @Qualifier("secondaryDataSource")
////    @ConfigurationProperties(prefix = "spring.datasource.secondary")
////    public DataSourceProperties secondaryDataSourceProperties(){
////        return new DataSourceProperties();
////    }
//
//// 
////    @Bean
////    @Primary
////    @Qualifier("primaryDataSource")
////    @ConfigurationProperties(prefix = "spring.datasource.primary")
////    public DataSource primaryDataSource(){
////        return primaryDataSourceProperties().initializeDataSourceBuilder().build();
////    }
//// 
////    @Autowired
////    @Qualifier("primaryDataSource")
////    private DataSource primaryDataSource;
//
//
//}
