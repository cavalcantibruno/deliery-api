package com.foop.delivery.infrastructure.repository;

import com.foop.delivery.domain.model.Restaurants;
import com.foop.delivery.domain.repository.RestaurantsRepository;
import com.foop.delivery.domain.repository.RestaurantsRepositoryQueries;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
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

import static com.foop.delivery.infrastructure.repository.spec.RestaurantsSpecs.withFreeShipping;
import static com.foop.delivery.infrastructure.repository.spec.RestaurantsSpecs.withSimilarName;


@Repository
public class RestaurantsRepositoryImpl implements RestaurantsRepositoryQueries {
    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestaurantsRepository restaurantsRepository;

    
    public List<Restaurants> list() {
        return manager.createQuery("from Restaurants", Restaurants.class).getResultList();
    }

    
    public Restaurants byId(Long id) {
        return manager.find(Restaurants.class, id);
    }

    
    public Restaurants save(Restaurants restaurants) {
        return manager.merge(restaurants);
    }

    
    public void remove(Restaurants restaurants) {
        manager.remove(byId(restaurants.getId()));
    }

    @Override
    public List<Restaurants> find(String name, BigDecimal shippingFeeStart, BigDecimal shippingFeeEnd) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurants> criteria = builder.createQuery(Restaurants.class);
        Root<Restaurants> root = criteria.from(Restaurants.class);

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

       TypedQuery<Restaurants> query = manager.createQuery(criteria);
       return query.getResultList();
    }

    @Override
    public List<Restaurants> findWithFreeShipping(String name) {
        return restaurantsRepository.findAll(withFreeShipping().and(withSimilarName(name)));
    }

//    @Override
//    public List<Restaurants> find(String name, BigDecimal shippingFeeStart, BigDecimal shippingFeeEnd) {
//        var jpql = new StringBuilder();
//        jpql.append("from Restaurants where 1 = 1 ");
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
//        TypedQuery<Restaurants> query = manager.createQuery(jpql.toString(), Restaurants.class);
//        param.forEach((key, value) -> { query.setParameter(key, value); });
//
//        return query.getResultList();
//    }
}
