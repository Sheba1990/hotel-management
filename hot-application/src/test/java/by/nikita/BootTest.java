package by.nikita;

import by.nikita.dao.api.IUserDetailsDao;
import by.nikita.models.UserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BootTest {

    @Autowired
    IUserDetailsDao userDetailsDao;

    @Test
    public void testUserDetailsAccess() throws InterruptedException {
        UserDetails userDetails = new UserDetails();

        userDetails.setFirstName("Nikita");
        userDetails.setLastName("Shebin");
        UserDetails newUserDetails = userDetailsDao.create(userDetails);

        UserDetails getUserById = userDetailsDao.get(newUserDetails.getId());

        assertEquals(getUserById.getFirstName(), "Nikita");
        assertEquals(getUserById.getLastName(), "Shebin");
    }
}
