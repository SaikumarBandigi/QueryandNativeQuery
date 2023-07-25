package com.sonu.collections.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book")

//Implementation of retireveByBookName

@NamedQueries(value= {
		
		
		//NamedQuery1
		@NamedQuery(name = "Book.retireveByBookName",
				query = "select distinct b from Book b join b.publishers p where b.bookName=?1")
		
		
		//NamedQuery2
		
		/*
		 * select b from book inner join book_publisher bp on b.book_id=bp.book_id
		 *                     inner join publisher p on bp.publisher_id=p.publisher_id
		 */
		
})

public class Book {
	
	
		@Id
		@Column(name = "book_id")
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer bookId;
		
		@Column(name = "book_name")
		private String bookName;
		
		//CascadeType.ALL ==> ANY DML (SELECT,INSERT,UPDATE AND DELETE) OPERATION IT WILL CASCADE TO PUBLISHER ALSO
		//fetch = FetchType.EAGER ==> Both Parent and Child
		//fetch = FetchType.LAZY ==> Only Parent , on Demand Child
		@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
		@JoinTable(name = "book_publisher",
					joinColumns = @JoinColumn(name="bookId",referencedColumnName = "book_id"),
					inverseJoinColumns = @JoinColumn(name="publisherId",referencedColumnName = "publisher_id")
		)
		private Set<Publisher> publishers;
		
		

		public Integer getBookId() {
			return bookId;
		}

		public void setBookId(Integer bookId) {
			this.bookId = bookId;
		}

		public String getBookName() {
			return bookName;
		}

		public void setBookName(String bookName) {
			this.bookName = bookName;
		}

		public Set<Publisher> getPublishers() {
			return publishers;
		}

		public void setPublishers(Set<Publisher> publishers) {
			this.publishers = publishers;
		}

		public Book( String bookName, Set<Publisher> publishers) {
			super();
			
			this.bookName = bookName;
			this.publishers = publishers;
		}
		
		public Book() {}
		
		
		//Customized To String Method
		@Override
		public String toString() {

			String result = String.format("Book Data  [id=%d,name='%s']%n", bookId, bookName);
			// Book Data[id=1,name='Book A']

			if (publishers != null)
				for (Publisher publisher : publishers) {

					result += String.format("Publisher [id=%d,name='%s']%n", publisher.getPublisherId(),
							publisher.getPublisherName());
				}

			return result;

		}
		
		
		
		

}
