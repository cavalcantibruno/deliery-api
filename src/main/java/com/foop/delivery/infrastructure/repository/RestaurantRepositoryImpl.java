package com.foop.delivery.infrastructure.repository;

import com.foop.delivery.domain.model.Restaurant;
import com.foop.delivery.domain.repository.RestaurantRepository;
import com.foop.delivery.domain.repository.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.foop.delivery.infrastructure.repository.spec.RestaurantSpecs.withFreeShipping;
import static com.foop.delivery.infrastructure.repository.spec.RestaurantSpecs.withSimilarName;


@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {
    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestaurantRepository restaurantRepository;

    
    public List<Restaurant> list() {
        return manager.createQuery("from Restaurant", Restaurant.class).getResultList();
    }

    
    public Restaurant byId(Long id) {
        return manager.find(Restaurant.class, id);
    }

    
    public Restaurant save(Restaurant restaurant) {
        return manager.merge(restaurant);
    }

    
    public void remove(Restaurant restaurant) {
        manager.remove(byId(restaurant.getId()));
    }

    @Override
    public List<Restaurant> find(String name, BigDecimal shippingFeeStart, BigDecimal shippingFeeEnd) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        var predicate = new ArrayList<Predicate>();

        if(StringUtils.hasText(name)) {
            predicate.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        if(shippingFeeStart != null) {
            predicate.add(builder.greaterThanOrEqualTo(root.get("shippingFee"), shippingFeeStart));
        }

        if(shippingFeeEnd != null) {
            predicate.add(builder.lessThanOrEqualTo(root.get("shippingFee"), shippingFeeEnd));
        }

        criteria.where(predicate.toArray(new Predicate[0]));

       TypedQuery<Restaurant> query = manager.createQuery(criteria);
       return query.getResultList();
    }

    @Override
    public List<Restaurant> findWithFreeShipping(String name) {
        return restaurantRepository.findAll(withFreeShipping().and(withSimilarName(name)));
    }

//    @Override
//    public List<Restaurant> find(String name, BigDecimal shippingFeeStart, BigDecimal shippingFeeEnd) {
//        var jpql = new StringBuilder();
//        jpql.append("from Restaurant where 1 = 1 ");
//
//        var param = new HashMap<String, Object>();
//
//        if(StringUtils.hasLength(name)) {
//            jpql.append("and name like :name ");
//            param.put("name", "%" + name + "%");
//        }
//
//        if(shippingFeeStart != null) {
//            jpql.append("and shippingFee >= :shippingStart ");
//            param.put("shippingStart", shippingFeeStart);
//        }
//
//        if(shippingFeeStart != null) {
//            jpql.append("and shippingFee :shippingEnd ");
//            param.put( "shippingEnd", shippingFeeStart);
//        }
//
//        TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurants.class);
//        param.forEach((key, value) -> { query.setParameter(key, value); });
//
//        return query.getResultList();
//    }
}
