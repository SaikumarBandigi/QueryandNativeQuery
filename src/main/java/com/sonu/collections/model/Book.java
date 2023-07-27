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
/*
In this example, the @JoinTable annotation is used to define the mapping for a many-to-many relationship between two entities: Book and Publisher.
The annotation is applied to one side of the relationship (either Book or Publisher)
and specifies the join table that will be used to manage the association.

Let's break down the parameters:

name: Specifies the name of the join table in the database. In this case, the join table is named "book_publisher."

joinColumns: Specifies the foreign key column(s) in the join table that correspond to the primary key(s) of the owning entity, which is Book in this
 example. It indicates how the Book entity will be linked to the join table.

name: Specifies the name of the column in the join table that will reference the primary key of the Book entity. In this case,
the column is named "bookId."
referencedColumnName: Specifies the name of the primary key column in the Book entity's table that the "bookId" column in the join table references.
In this case, it references the "book_id" column in the Book entity.
inverseJoinColumns: Specifies the foreign key column(s) in the join table that correspond to the primary key(s) of the associated entity,
which is Publisher in this example. It indicates how the Publisher entity will be linked to the join table.

name: Specifies the name of the column in the join table that will reference the primary key of the Publisher entity. In this case,
the column is named "publisherId."
referencedColumnName: Specifies the name of the primary key column in the Publisher entity's table that the "publisherId" column in the join table
 references. In this case, it references the "publisher_id" column in the Publisher entity.
 */