package by.nikita.services;

import by.nikita.dao.api.IUserDao;
import by.nikita.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userByUsername = userDao.getByUsername(username);

        if (userByUsername == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.
                User(userByUsername.getUsername(),
                userByUsername.getPassword(),
                userByUsername.isActive(), userByUsername.isActive(), userByUsername.isActive(), userByUsername.isActive(),
                getAuthorities(userByUsername));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        ArrayList<GrantedAuthority> arrayList = new ArrayList<>();
        arrayList.add(new SimpleGrantedAuthority(user.getRoles().toString()));
        return arrayList;
    }
}
