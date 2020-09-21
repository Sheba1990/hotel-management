package by.nikita.dao.api;

import by.nikita.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserDaoJpaImpl extends JpaRepository<User, Long> {

    @Query(value =
            "SELECT ut.*, udt.*, at.*" +
                    " FROM User_table ut" +
                    " INNER JOIN User_details_table udt ON ut.user_details_id = udt.id" +
                    " INNER JOIN Contact_data_table cdt ON udt.contact_data_id = cdt.id" +
                    " INNER JOIN Address_table at ON cdt.address_id = at.id" +
                    " WHERE CONCAT(ut.username || ' ' || udt.first_name || ' ' || udt.last_name || ' ' || udt.middle_name) LIKE ('%' || ? || '%')",
            nativeQuery = true)
    public List<User> search(String keyword);


}
