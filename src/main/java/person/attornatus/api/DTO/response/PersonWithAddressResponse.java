package person.attornatus.api.DTO.response;

import java.util.ArrayList;
import java.util.List;

public class PersonWithAddressResponse extends PersonResponse {

    private List<AddressResponse> addressess = new ArrayList<>();

    public List<AddressResponse> getAddressess() {
        return addressess;
    }

    public void setAddressess(List<AddressResponse> addressess) {
        this.addressess = addressess;
    }
}
