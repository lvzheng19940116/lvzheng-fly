//package flylvzheng.config;
//
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef="entityManagerFactorySecondary",
//        transactionManagerRef="transactionManagerSecondary",
//        basePackages= { "flylvzheng.repository.worldRepository" }) //设置Repository所在位置
//public class SecondaryConfig {  
//	@Resource  
//    @Qualifier("secondaryDataSource")  
//    private DataSource secondaryDataSource;  
//  
//    @Bean(name = "entityManagerSecondary")  
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {  
//        return entityManagerFactorySecondary(builder).getObject().createEntityManager();  
//    }  
//  
//    @Resource  
//    private JpaProperties jpaProperties;  
//  
//    private Map<String, Object> getVendorProperties() {  
//        return jpaProperties.getHibernateProperties(new HibernateSettings());  
//    }  
//  
//    @Bean(name = "entityManagerFactorySecondary")  
//    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {  
//        return builder  
//                .dataSource(secondaryDataSource)  
//                .packages("flylvzheng.bean.world")  
//                .persistenceUnit("secondaryPersistenceUnit")  
//                .properties(getVendorProperties())  
//                .build();  
//    }  
//  
//    @Bean(name = "transactionManagerSecondary")  
//    PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {  
//        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());  
//    }  
//    
//}