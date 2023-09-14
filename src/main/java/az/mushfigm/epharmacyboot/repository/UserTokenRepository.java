package az.mushfigm.epharmacyboot.repository;

import az.mushfigm.epharmacyboot.entity.User;
import az.mushfigm.epharmacyboot.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken,Long> {
    UserToken findUserTokenByUserAndTokenAndActive(User user, String token, Integer active);
}
