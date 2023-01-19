package person.attornatus.api.exceptions;

public class AddressNotFoundException extends Exception {
    public AddressNotFoundException(String addressUUID) {
        super("Address not found, verify this uuid" + addressUUID );
    }
}
