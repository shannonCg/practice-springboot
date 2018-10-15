package com.syntrontech.test.criteria;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.syntrontech.test.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class JPACriteriaQueryTest {
	
	@Autowired
	private UserRepositoryImp repository;
	
	private User userA;
	
	private User userB;
	
	private User userC;
	
	private User userSameUserAName;
	
	private User userSameUserBAge;
	
	@Before
	public void init(){
		userA = new User();
		userA.setName("ABC");
		userA.setAge(10);
		repository.save(userA);
		
		userB = new User();
		userB.setName("CDE");
		userB.setAge(20);
		repository.save(userB);
		
		userC = new User();
		userC.setName("ACF");
		userC.setAge(15);
		repository.save(userC);
		
		userSameUserAName = new User();
		userSameUserAName.setName("ABC");
		userSameUserAName.setAge(11);
		repository.save(userSameUserAName);
		
		userSameUserBAge = new User();
		userSameUserBAge.setName("GHI");
		userSameUserBAge.setAge(20);
		repository.save(userSameUserBAge);
	}

	@Test
	public void givenInvalidOperation(){
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
		params.add(new SearchCriteria("name", "?", "C"));
		
		List<User> results = repository.searchUsers(params);
		
		assertThat(results.size(), equalTo(5));
		assertThat(results, hasItem(userA));
		assertThat(results, hasItem(userB));
		assertThat(results, hasItem(userC));
		assertThat(results, hasItem(userSameUserAName));
		assertThat(results, hasItem(userSameUserBAge));
	}
	
	@Test
	public void givenNameLike(){
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
		params.add(new SearchCriteria("name", ":", "A"));
		
		List<User> results = repository.searchUsers(params);
		
		assertThat(results.size(), equalTo(3));
		assertThat(results, hasItem(userA));
		assertThat(results, not(hasItem(userB)));
		assertThat(results, hasItem(userC));
		assertThat(results, hasItem(userSameUserAName));
		assertThat(results, not(hasItem(userSameUserBAge)));
	}
	
	@Test
	public void givenAgeIs(){
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
		params.add(new SearchCriteria("age", ":", 10));
		
		List<User> results = repository.searchUsers(params);
		
		assertThat(results.size(), equalTo(1));
		assertThat(results, hasItem(userA));
		assertThat(results, not(hasItem(userB)));
		assertThat(results, not(hasItem(userC)));
		assertThat(results, not(hasItem(userSameUserAName)));
		assertThat(results, not(hasItem(userSameUserBAge)));
	}
	
	@Test
	public void givenAgeLessThanEqual(){
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
		params.add(new SearchCriteria("age", "<", 15));
		
		List<User> results = repository.searchUsers(params);
		
		assertThat(results.size(), equalTo(3));
		assertThat(results, hasItem(userA));
		assertThat(results, not(hasItem(userB)));
		assertThat(results, hasItem(userC));
		assertThat(results, hasItem(userSameUserAName));
		assertThat(results, not(hasItem(userSameUserBAge)));
	}
	
	@Test
	public void givenAgeGreaterThanEqual(){
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
		params.add(new SearchCriteria("age", ">", 15));
		
		List<User> results = repository.searchUsers(params);
		
		assertThat(results.size(), equalTo(3));
		assertThat(results, not(hasItem(userA)));
		assertThat(results, hasItem(userB));
		assertThat(results, hasItem(userC));
		assertThat(results, not(hasItem(userSameUserAName)));
		assertThat(results, hasItem(userSameUserBAge));
	}
	
	@Test
	public void givenNameLikeAndAgeLessThanEqual(){
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
		params.add(new SearchCriteria("name", ":", "C"));
		params.add(new SearchCriteria("age", "<", 14));
		
		List<User> results = repository.searchUsers(params);
		
		assertThat(results.size(), equalTo(2));
		assertThat(results, hasItem(userA));
		assertThat(results, not(hasItem(userB)));
		assertThat(results, not(hasItem(userC)));
		assertThat(results, hasItem(userSameUserAName));
		assertThat(results, not(hasItem(userSameUserBAge)));
	}
	
	@Test
	public void givenSameName(){
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
		params.add(new SearchCriteria("name", ":", "C"));
//		params.add(new SearchCriteria("age", ">", 15));
		
		List<String> results = repository.searchUserDistinctBy(params, "name");
		System.out.println("size:"+results.size());
		results.forEach(r -> {
			System.out.println("name:"+r);
			System.out.println("---------------");
		});
		
		assertThat(results.size(), equalTo(1));
		assertThat(results, hasItem("ABC"));
	}
	
	@Test
	public void givenSameAge(){
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
		params.add(new SearchCriteria("age", ":", 20));
		
		List<Integer> results = repository.searchUserDistinctBy(params, "age");
		System.out.println("size:"+results.size());
		results.forEach(r -> {
			System.out.println("age:"+r);
			System.out.println("---------------");
		});
		
		assertThat(results.size(), equalTo(1));
		assertThat(results, hasItem(20));
	}
	
}
