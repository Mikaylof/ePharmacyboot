package az.mushfigm.epharmacyboot.security;

import az.mushfigm.epharmacyboot.entity.Users;
import az.mushfigm.epharmacyboot.enums.EnumAvailableStatus;
import az.mushfigm.epharmacyboot.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findUsersByEmailAndActive(username, EnumAvailableStatus.ACTIVE.value);
        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + username);
        }
        MyUserDetails myUserDetails = new MyUserDetails(user);
        return myUserDetails;
    }


}
