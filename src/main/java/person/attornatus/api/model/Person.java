package person.attornatus.api.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String externalUUID;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = true)
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Address> addresses;

    public Person(String name, LocalDate birthDate, List<Address> addresses) {
        this.name = name;
        this.birthDate = birthDate;
        this.addresses = addresses;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getExternalUUID() {
        return externalUUID;
    }

    public void setExternalUUID(String externalUUID) {
        this.externalUUID = externalUUID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", addresses=" + addresses +
                '}';
    }
}
