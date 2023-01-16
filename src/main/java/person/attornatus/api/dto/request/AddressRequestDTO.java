package person.attornatus.api.dto.request;

import javax.validation.constraints.Pattern;

public class AddressRequestDTO{
        private String extenalUUID;

        private Boolean isMain;

        @Pattern(regexp = ("[0-9d]{5}-[0-9d]{3}"), message = "value error")
        private String zipCode;

        private int number;

        private String city;

        private String publicPlace;

        public String getExtenalUUID() {
            return extenalUUID;
        }

        public void setExtenalUUID(String extenalUUID) {
            this.extenalUUID = extenalUUID;
        }

        public Boolean getMain() {
            return isMain;
        }

        public void setMain(Boolean main) {
            isMain = main;
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

        public String getPublicPlace() {
            return publicPlace;
        }

        public void setPublicPlace(String publicPlace) {
            this.publicPlace = publicPlace;
        }
    }

