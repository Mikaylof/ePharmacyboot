package az.mushfigm.epharmacyboot.repository;

import az.mushfigm.epharmacyboot.entity.Customer;
import az.mushfigm.epharmacyboot.entity.Purchaser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaserRepository extends JpaRepository<Purchaser,Long> {
    Purchaser findCustomerByIdAndActive(Long id, Integer active);

    List<Purchaser> findAllByActive(Integer active);

    Purchaser findByTokenAndActive(String token, Integer active);

}
