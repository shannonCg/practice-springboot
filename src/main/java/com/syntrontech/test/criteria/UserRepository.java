package com.syntrontech.test.criteria;

import java.util.List;

/*
 * use JPA 2 criteria
 */
public interface UserRepository {

	List<User> searchUsers(List<SearchCriteria> params);
	
	void save(User entity);
	
	List searchUserDistinctBy(List<SearchCriteria> params, String distinctColumn);
}
