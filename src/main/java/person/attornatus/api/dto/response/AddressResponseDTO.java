package person.attornatus.api.dto.response;

public class AddressResponseDTO {
    private String externalUUID;

    private int number;

    private String city;

    private String publicPlace;

    public String getExternalUUID() {
       return externalUUID;
    }

    public void setExternalUUID(String externalUUID) {
        this.externalUUID = externalUUID;
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
