package person.attornatus.api.dto.response;

import java.util.ArrayList;
import java.util.List;

public class PersonWithAddressResponse extends PersonResponseDTO {

    private List<AddressResponse> addressess = new ArrayList<>();

    public List<AddressResponse> getAddressess() {
        return addressess;
    }

    public void setAddressess(List<AddressResponse> addressess) {
        this.addressess = addressess;
    }
}
