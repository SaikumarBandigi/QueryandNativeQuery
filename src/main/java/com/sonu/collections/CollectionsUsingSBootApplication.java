package com.sonu.collections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sonu.collections.model.Book;
import com.sonu.collections.model.CustomType;
import com.sonu.collections.model.Employee;
import com.sonu.collections.model.Person;
import com.sonu.collections.model.Publisher;
import com.sonu.collections.service.PersonService;

@SpringBootApplication
public class CollectionsUsingSBootApplication implements CommandLineRunner {

	@Autowired
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(CollectionsUsingSBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// createPersons();
		// getPersonByIds();
		
		//findByLastNameOrFirstName();
		//findByLastNameAndFirstName();
		
		//findByLastNameOrderByCreatedDateDesc();
		//findByAgeLessThanEqual();
		//findByFirstNameLike();
		//findByLastNameAndAgeLessThanEqual();
		//findByCreatedDateBetween();
		//giveDataByLastName();
		
		//saveBookPublishers();
		//findByBookName();
	//	retireveByBookName();
		//createEmployees();
		//getMaxSalaryByDept();
		//giveFewcolumns();
		findPersonInfobyFirstNameorEmail();
		findPersonINfobyFirstname();
	}
	
	
	private void createEmployees() {
		List<Employee> empList = Arrays.asList(
				//Admin Dept
				Employee.create("Ram", "Admin", 20000),
				Employee.create("Gopi", "Admin", 35000),

				//Sales Dept
				Employee.create("Sita", "Sale", 10000),
				Employee.create("Ganesh", "Sale", 30000),

				//IT Dept
				Employee.create("Laxman", "IT", 40000),
				Employee.create("Seenu", "IT", 25000),

				//HR Dept
				Employee.create("Swathi", "HR", 80000),
				Employee.create("Sneha", "HR", 65000)

		);

		Iterable<Employee> list = personService.saveAllEmployees(empList);
		for (Employee emp : list) {
			System.out.println("Employee Object" + emp.toString());

		}
	}

	
	private void getMaxSalaryByDept() {

		List<Object[]> list = personService.getMaxSalaryByDept(
				Arrays.asList("Admin", "IT", "HR"));
		list.forEach(arr -> {
			System.out.println(Arrays.toString(arr));
		}
		);
	}
	
	
	
	private void saveBookPublishers() {

		Publisher publisherA = new Publisher("AbdulKalam");
		Publisher publisherB = new Publisher("Stephen Kovey");
		Publisher publisherC = new Publisher("ChetanBagath");
		Publisher publisherD = new Publisher("Author2");
		Publisher publisherE = new Publisher("Author3");
		Publisher publisherF = new Publisher("Nazir");
		
		//One to One from Book to Publisher
		Book bookA = new Book("WingsofFire", new HashSet<>(Arrays.asList(publisherA)));
		Book bookB = new Book("SevenHabits", new HashSet<>(Arrays.asList(publisherB)));
		Book bookC = new Book("TwoStates", new HashSet<>(Arrays.asList(publisherC)));
		
		
		// One to Many from Book to Publisher
		Book bookD = new Book("Book2", new HashSet<>(Arrays.asList(publisherD, publisherE)));

		// One to Many from Publisher to Book
		Book bookE = new Book("Book5", new HashSet<>(Arrays.asList(publisherF)));
		Book bookF = new Book("Book6", new HashSet<>(Arrays.asList(publisherF)));

		personService.saveBooks(Arrays.asList(bookA, bookB, bookC, bookD, bookE, bookF));

		// fetch all publishers
		for (Book book : personService.findBooks()) {
			System.out.println(book.toString());
		}

	}

	
private void retireveByBookName() {
		
		Iterable<Book> bookObj=personService.retireveByBookName("Book2");

		// fetch all publishers
		for (Book book : bookObj) {
			System.out.println(book.toString());
		}


	}

	
private void findByBookName() {
		
		Iterable<Book> bookObj=personService.findByBookName("Book2");

		// fetch all publishers
		for (Book book : bookObj) {
			System.out.println(book.toString());
		}


	}

	private void createPersons() {

		/*
		 * List<Person> personList=new ArrayList<Person>();
		 *
		 * personList.add(new Person("Kiran", "kumar", "kiran@gmail.com", 20));
		 *
		 * personList.add(new Person("Kiran1", "kumar", "kiran@gmail.com", 20));
		 *
		 * personList.add(new Person("Kiran2", "kumar", "kiran@gmail.com", 20));
		 * personList.add(new Person("Kiran3", "kumar", "kiran@gmail.com", 20));
		 * personList.add(new Person("Kiran4", "kumar", "kiran@gmail.com", 20));
		 * personList.add(new Person("Kiran5", "kumar", "kiran@gmail.com", 20));
		 * personList.add(new Person("Kiran7", "kumar", "kiran@gmail.com", 20));
		 * personList.add(new Person("Kiran8", "kumar", "kiran@gmail.com", 20));
		 *
		 * personService.saveAllpersons(personList);
		 *
		 * personList=null;
		 */

		// List<Person> personList=new ArrayList<Person>();
		// Person per=new Person();

		List<Person> personList = Arrays.asList(new Person("Kiran", "kumar", "kiran@gmail.com", 20),
				new Person("Ram", "kumar", "ram@gmail.com", 22),
				new Person("RamKiran", "LaxmiKiran", "sita@gmail.com", 30),
				new Person("Lakshamn", "Seth", "seth@gmail.com", 50),
				new Person("Sita", "Kumar", "lakshman@gmail.com", 50),
				new Person("Ganesh", "Kumar", "ganesh@gmail.com", 50),
				new Person("KiranKiran", "kumar", "kiran@yahoo.com", 20),
				new Person("RamRam", "kumar", "ram@yahoo.com", 22),
				new Person("RamKiranRamKiran", "LaxmiKiran", "sita@yahoo.com", 30),
				new Person("RamKiranRamKiran", "Seth", "seth@yahoo.com", 50),
				new Person("SitaSita", "Kumar", "lakshman@yahoo.com", 50),
				new Person("GaneshSita", "Kumar", "ganesh@yahoo.com", 50));

		Iterable<Person> list = personService.saveAllPersons(personList);
		for (Person person : list) {
			System.out.println("Person Object" + person.toString());

		}
	}
	
	
	
	
	private void findPersonInfobyFirstNameorEmail() {

		Iterable<Person> personsList = personService.findPersonInfobyFirstNameorEmail("Kiran", "kiran@gmail.com");
		for (Person person : personsList) {
			System.out.println("Person Object" + person.toString());

		}
	}

	private void findPersonINfobyFirstname() {

		Iterable<Person> personsList = personService.findPersonINfobyFirstname("Kiran");
		for (Person person : personsList) {
			System.out.println("Person Object" + person.toString());

		}
	}

	
	private void getPersonByIds() {
		List<Integer> personList = new ArrayList<Integer>();
		personList.add(1);
		personList.add(2);
		personList.add(12);
		personList.add(5);
		personList.add(6);
		personList.add(20);
		personList.add(40);
		personList.add(11);
		personList.add(15);
		personList.add(3);
		personList.add(4);

		// findPerson ==> select * from tbl_person where person_id in
		// (1,2,12,5,6......);
		Iterable<Person> personsList = personService.getMultiplePersons(personList);
		for (Person person : personsList) {
			System.out.println("Person Object" + person.toString());

		}
	}

	private void findByLastNameOrFirstName() {

		Iterable<Person> personsList = personService.findByLastNameOrFirstName("kumar", "Ram");
		for (Person person : personsList) {
			System.out.println("Person Object" + person.toString());

		}
	}

	private void findByLastNameAndFirstName() {

		Person personObj = personService.findByLastNameAndFirstName("kumar", "Ram");
		System.out.println("Person Object" + personObj.toString());

	}
	
	private void findByLastNameOrderByCreatedDateDesc() {

		Iterable<Person> personsList = personService.findByLastNameOrderByCreatedDateDesc("kumar");
		for (Person person : personsList) {
			System.out.println("Person Object" + person.toString());

		}
	}
	
	private void findByAgeLessThanEqual() {

		//select * from tbl_person where age<=40
		Iterable<Person> personsList = personService.findByAgeLessThanEqual(40);
		for (Person person : personsList) {
			System.out.println("Person Object" + person.toString());

		}
	}
	
	private void findByFirstNameLike() {

		//select * from tbl_person where first_name like '%kiran%';
		Iterable<Person> personsList = personService.findByFirstNameLike("%kiran%");
		for (Person person : personsList) {
			System.out.println("Person Object" + person.toString());

		}
	}
	
	private void findByLastNameAndAgeLessThanEqual() {

		//select * from tbl_person where last_name='kumar' and age<=40;
		Iterable<Person> personsList = personService.findByLastNameAndAgeLessThanEqual("kumar",40);
		for (Person person : personsList) {
			System.out.println("Person Object" + person.toString());

		}
	}
	
	
	private void findByCreatedDateBetween() {

		//select * from tbl_person where last_name='kumar' and age<=40;
		Iterable<Person> personsList = personService.findByCreatedDateBetween(getDatewithTime("2023-07-12 20:23:38"),
																			  getDatewithTime("2023-07-12 20:23:44"));
		for (Person person : personsList) {
			System.out.println("Person Object" + person.toString());

		}
	}
	
	
	
	
	private Date getDatewithTime(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			return format.parse(dateString);
		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
	}
	
	
	private void giveDataByLastName() {

		Iterable<Person> personsList = personService.giveDataByLastName("kumar");
		for (Person person : personsList) {
			System.out.println("Person Object" + person.toString());

		}
	}
	
	private void giveFewcolumns() {

		Iterable<CustomType> personsList = personService.giveFewcolumns("kumar");
		for (CustomType person : personsList) {
			System.out.println("Person Object" + person.toString());

		}
	}

	
	

	

}
