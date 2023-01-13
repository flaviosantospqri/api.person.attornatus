package person.attornatus.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import person.attornatus.api.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
