package com.sonu.collections.atm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/*
why @OneToOne mapping?

one table's primary key id becomes other table's foreign key so.....

@OneToOne mapping can be done by unidirectional(one way) or bidirectional(two ways)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pin;

    // @OneToOne(targetEntity = Account.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    // @Column means if we want customize column's name in table
    // @JoinColumn is jus like @Column but since the another table's column is joining and if we want custom name then we use @JoinColumn(name),
    //referencedColumnName is mapping table's primary key's name

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    // Constructors, getters, and setters
}
