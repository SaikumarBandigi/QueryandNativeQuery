package com.sonu.collections.dao;

import java.util.Date;
import java.util.List;

import com.sonu.collections.model.CustomType;
import com.sonu.collections.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends CrudRepository<Person, Integer> {
	
	/*
	 * 
	 * We are going to write abstract methods in this layer and implementation to be given by framework
	 */

	
	public Iterable<Person> findByLastNameOrFirstName(String lastName,String firstName);

	//select * from tbl_person where last_name=lastName or first_name=firstName

	public Person findByLastNameAndFirstName(String lastName,String firstName);
	
	//select * from tbl_person where last_name=lastName and  first_name=firstName
	
	
	public List<Person> findByLastNameOrderByCreatedDateDesc(String lastName);
	
	//select * from tbl_person where last_name=lastName order by created_date desc
	
	
	public List<Person> findByAgeLessThanEqual(Integer age);
	
	//select * from tbl_person where age<=age;
	
	
	public List<Person> findByFirstNameLike(String firstName);
	
	//select * from tbl_person where first_name like '%firstName%'
	
	public List<Person> findByLastNameAndAgeLessThanEqual(String lastName,int age);
	
	//select * from tbl_person where last_name=lastName and age<=age
	
	
	public List<Person> findByCreatedDateBetween(Date startdate,Date endDate);
	
	//select * from tbl_person where created_date>=startdate and created_date<=endDate
	
	
	/*
	 * Named Query Section
	 * Abstract Method in the interface layer ==> Any name , no framework rules
	 * Implementation at Model/Domain/Entity Layer
	 */
	
	public List<Person> giveDataByLastName(String lastName); //Abstract Method
	
	public List<CustomType> giveFewcolumns(String lastName); //Abstract Method

	
	/*
	 * Query and Native Query Section
	 * 
	 * Query ==> Abstract Method and Implementation both at interface layer itself
	 * 
	 * Query ==> Only Java Class Names and Property Names
	 * 
	 * Native Query ==> We will write database tables and database columns
	 * 
	 */
	
	//Query -> We are going to write JPQL -> Writing Java Classes and Java proprties
	
	@Query(value="SELECT p FROM Person p where p.firstName=?1 OR p.email=?2") //Implementation
	List<Person> findPersonInfobyFirstNameorEmail(String firstName,String email); //Abtract Method

	//NativeQuery -> We are going to write SQL -> Writing database tables and database columns
	@Query(value="SELECT * FROM tbl_person p where p.first_name=?1",nativeQuery = true)
	List<Person> findPersonINfobyFirstname(String firstName);



	
}

