package person.attornatus.api.model;

import jakarta.persistence.*;


@Entity
@Table(name = "tb_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String publicPlace;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private String city;



}
