package com.syntrontech.test.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImp implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchUsers(List<SearchCriteria> params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<User> query = builder.createQuery(User.class);
		
		Root root = query.from(User.class);
		
		Predicate predicate = builder.conjunction();
		
		for(SearchCriteria param : params){
			if(">".equalsIgnoreCase(param.getOperation())){
				Predicate additionalPredicate = builder.greaterThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()); 
				predicate = builder.and(predicate, additionalPredicate);
			}else if("<".equalsIgnoreCase(param.getOperation())){
				Predicate additionalPredicate = builder.lessThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()); 
				predicate = builder.and(predicate, additionalPredicate);
			}else if(":".equalsIgnoreCase(param.getOperation())){
				if(String.class == root.get(param.getKey()).getJavaType()){
					Predicate additionalPredicate = builder.like(root.get(param.getKey()), "%"+param.getValue()+"%"); 
					predicate = builder.and(predicate, additionalPredicate);
				}else{
					Predicate additionalPredicate = builder.equal(root.get(param.getKey()), param.getValue()); 
					predicate = builder.and(predicate, additionalPredicate);
				}
			}
		}
		query.where(predicate);
		
		List<User> result = entityManager.createQuery(query).getResultList();
		
		return result;
	}

	@Override
	public void save(User entity) {
		entityManager.persist(entity);
	}

	@Override
	public List searchUserDistinctBy(List<SearchCriteria> params, String distinctColumn) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery query = builder.createQuery(User.class);
		
		Root root = query.from(User.class);
		
		Predicate predicate = builder.conjunction();
		
		for(SearchCriteria param : params){
			if(">".equalsIgnoreCase(param.getOperation())){
				Predicate additionalPredicate = builder.greaterThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()); 
				predicate = builder.and(predicate, additionalPredicate);
			}else if("<".equalsIgnoreCase(param.getOperation())){
				Predicate additionalPredicate = builder.lessThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()); 
				predicate = builder.and(predicate, additionalPredicate);
			}else if(":".equalsIgnoreCase(param.getOperation())){
				if(String.class == root.get(param.getKey()).getJavaType()){
					Predicate additionalPredicate = builder.like(root.get(param.getKey()), "%"+param.getValue()+"%"); 
					predicate = builder.and(predicate, additionalPredicate);
				}else{
					Predicate additionalPredicate = builder.equal(root.get(param.getKey()), param.getValue()); 
					predicate = builder.and(predicate, additionalPredicate);
				}
			}
		}
		query.where(predicate);
		query.select(root.get(distinctColumn));
		query.distinct(true);
		
		List<String> result = entityManager.createQuery(query).getResultList();
		
		return result;
	}

}
