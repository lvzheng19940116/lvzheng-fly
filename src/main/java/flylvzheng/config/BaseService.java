package flylvzheng.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by huqiwen on 2018-5-18.
 */
public abstract class BaseService<T,ID extends Serializable> {

    private static Logger logger = LoggerFactory.getLogger(BaseService.class);

    public static final String PAGE_INDEX = "pageIndex";
    public static final String PAGE_SIZE = "pageSize";
    public static final String SORT_FIELD = "sortField";
    public static final String SORT_ORDER = "sortOrder";
    public static final String STOPED = "stoped";
    public static final String STATUS = "status";

    public static final String MODIFY_DATE = "modifyDate";

    public abstract BaseDao<T, ID> getDAO();

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;

    public T findOne(ID id){
        return getDAO().findById(id).orElse(null);
    }

    public List<T> findAll() {
        return (List<T>) getDAO().findAll();
    }


    public void deleteById(ID id){
        getDAO().deleteById(id);
    }
    public void delete(List<ID> ids){
        for (ID id:ids){
            deleteById(id);
        }
    }
    public void delete(ID[] ids){
        for (ID id:ids){
            deleteById(id);
        }
    }


    public T update(T t){
        return getDAO().save(t);
    }

    public void updateAll(Iterable<T> var1){
        getDAO().saveAll(var1);
    }


    public void saveAll(T[] objs){
        for (T t:objs){
            update(t);
        }
    }
    /**
     * 获取当前的实体对象
     */
    private Class<T> getEntityClass() {
        ParameterizedType type = (ParameterizedType) getClass()
                .getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }
    public long count(){
        return getDAO().count();
    }

//    /**
//     * 此方法封装前端的查询方法，方便做常规查询处理；
//     * @param params
//     * @return
//     * @Author huqiwen 2018年6月7日
//     */
//    public Page<T> searchByParams(Map<String,Object> params){
//        Pageable pageable = buildPageRequest(params);
//
//        return getDAO().findAll((root,query,builder)->{
//            List<Predicate> predicates = buildPredicates(params,root,builder);
//            if (!predicates.isEmpty()) {
//                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
//            }
//            return builder.conjunction();
//        },pageable);
//
//    }
//
//    public List<T> searchAllByParams(Map<String,Object> params){
//        return getDAO().findAll((root,query,builder)->{
//            List<Predicate> predicates = buildPredicates(params,root,builder);
//            if (!predicates.isEmpty()) {
//                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
//            }
//            return builder.conjunction();
//        });
//    }

//    /**
//     * 传入MAP构建Predicate的查询串，目前此方法只支持equal类型的查询构建；
//     * 主要用在自定义的prdicates里面方便快速构建；请自行确定传入的key值在entity里面存在；
//     * @param searchMap
//     * @param root
//     * @param builder
//     * @return
//     * @Author huqiwen 2018年6月7日
//     */
//    public List<Predicate> buildPredicates(Map<String,Object> searchMap, Root<T> root, CriteriaBuilder builder){
//        List<Predicate> predicates = new ArrayList<>();
//        Map<String,Field> fieldMap = BeanUtil.getEntityFiledMap(getEntityClass());
//        Date beginDate = null;
//        Date endDate = null;
//        String dateFiled = StringPool.BLANK;
//        //添加默认搜索条件，默认查询的是非停用状态的数据
//        if (null == searchMap.get(STOPED) && null == searchMap.get(STATUS) && null !=fieldMap.get(STOPED)){
//            predicates.add(builder.equal(root.get(STOPED),Boolean.FALSE));
//        }
//        for (Map.Entry entry : searchMap.entrySet()) {
//            String k = (String)entry.getKey();
//            Object v = entry.getValue();
//            if (Validator.isNull(v)){
//                continue;
//            }
//            /**
//             * 有些前端传过来的字段是需要特殊处理的，仅限于本项目特殊情况
//             */
//            if (STATUS.equals(k)) {
//                if (GetterUtil.getInteger(v) == 1) {
//                    predicates.add(builder.equal(root.get(STOPED), Boolean.FALSE));
//                }
//                if (GetterUtil.getInteger(v) == 2){
//                    predicates.add(builder.equal(root.get(STOPED), Boolean.TRUE));
//                }
//            } else {
//                //是否有以begin开头的日期类型字段，如果有跳出当前循环
//                if (k.startsWith(StringPool.S_BEGIN)){
//                    String tempK = k.substring(StringPool.S_BEGIN.length());
//                    Field field = fieldMap.get(tempK);
//                    if (null !=field){
//                        beginDate = GetterUtil.getDate(v);
//                        dateFiled = tempK;
//                        continue;
//                    }
//                //是否有以end开头的日期类型字段，如果有跳出当前循环
//                }else if (k.startsWith(StringPool.S_END)){
//                    String tempK = k.substring(StringPool.S_END.length());
//                    Field field = fieldMap.get(tempK);
//                    if (null !=field){
//                        endDate = GetterUtil.getDate(v);
//                        dateFiled = tempK;
//                        continue;
//                    }
//                }else{
//                    Field field = fieldMap.get(k);
//                    if (field!=null){
//                        //如果是String类型，则进行like查询
//                        String fieldTypeName = field.getType().getName();
//                        if (String.class.getName().equals(fieldTypeName)){
//                            predicates.add(builder.like(root.get(k), StringPool.PERCENT + v + StringPool.PERCENT));
//                            //boolean类型
//                        }else if (Boolean.class.getName().equals(fieldTypeName)) {
//                            predicates.add(builder.equal(root.get(k), GetterUtil.getBoolean(v)));
//                            //日期类型
//                        }else if (Date.class.getName().equals(fieldTypeName)){
//                            predicates.add(builder.equal(root.get(k), GetterUtil.getDate(v)));
//                         //后续根据项目情况调整，目前in查询只支持Long类型，也就是项目中的主键 by huqiwen 2018-7-12 9:57:13
//                        }else  if (Long.class.getName().equals(fieldTypeName) && v instanceof Collection){
//                            Collection<Long> ids = (Collection<Long>)v;
//                            if (!ids.isEmpty()){
//                                predicates.add(root.get(k).in(ids));
//                            }
//                        }else{
//                            predicates.add(builder.equal(root.get(k), v));
//                        }
//                    }
//                }
//            }
//        }
//
//        //日期的字段的处理；
//        if (null!=beginDate && Validator.isNotNull(dateFiled)) {
//            predicates.add(builder.greaterThanOrEqualTo(root.get(dateFiled),beginDate));
//        }
//        if (null!=endDate && Validator.isNotNull(dateFiled)){
//            predicates.add(builder.lessThanOrEqualTo(root.get(dateFiled),endDate));
//        }
//
//        return predicates;
//    }
//
//    public PageRequest buildPageRequest(Map<String,Object> params){
//        int page = GetterUtil.getInteger(params.get(PAGE_INDEX));
//        int pageSize = GetterUtil.getInteger(params.get(PAGE_SIZE));
//        String sortField =  (String)params.get(SORT_FIELD);
//        List<Order> orders = new ArrayList<>();
//        if (Validator.isNotNull(sortField)){
//            orders.add(new Order(Direction.ASC,sortField));
//        }
//        if (!StringPool.DEFAULT_SORT_FIELD.equals(sortField)){
//            orders.add(new Order(Direction.ASC, StringPool.DEFAULT_SORT_FIELD));
//        }
//
//        /**
//         *  添加时间排序，倒序，这里有可能碰到没有修改时间字段？貌似本系统中不存在此情况，后续如果发现，
//         *  使用    getEntityFiledMap，先做一次判断是否有此字段；
//         */
//        orders.add(new Order(Direction.DESC,MODIFY_DATE));
//
//        Sort sort = Sort.by(orders);
//
//        return PageRequest.of(page, pageSize, sort);
//    }

//    public PageRequest buildPageRequest(int pageNumber, int pagzSize) {
//        return buildPageRequest(pageNumber, pagzSize, StringPool.BLANK);
//    }
//    /**
//     * 创建分页请求.
//     */
//    public PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortFiled) {
//        Direction direction = Direction.DESC;
//        if (StringUtils.isEmpty(sortFiled)){
//            sortFiled = MODIFY_DATE;
//        }
//
//        if (StringPool.DEFAULT_SORT_FIELD.equals(sortFiled)){
//            direction = Direction.ASC;
//        }
//        Sort sort = new Sort(direction,sortFiled);
//
//        return PageRequest.of(pageNumber, pagzSize, sort);
//    }





}
