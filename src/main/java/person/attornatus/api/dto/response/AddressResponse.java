package person.attornatus.api.dto.response;

public class AddressResponse {
    private String extenalUUID;

    private int number;

    private String city;

    private String publicPlace;

    public String getExtenalUUID() {
        return extenalUUID;
    }

    public void setExtenalUUID(String extenalUUID) {
        this.extenalUUID = extenalUUID;
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

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }
}
