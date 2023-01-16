package person.attornatus.api.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String externalUUID;

    @Column(nullable = false)
    private String publicPlace;

    @Column(nullable = false)

    private String zipCode;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private String city;

    @Column(nullable = true)
    private Boolean isMain;

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExternalUUID() {
        return externalUUID;
    }

    public void setExternalUUID(String externalUUID) {
        this.externalUUID = externalUUID;
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }

}
