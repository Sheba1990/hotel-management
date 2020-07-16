package by.nikita.dao;

import by.nikita.dao.api.IContactDataDao;
import by.nikita.models.ContactData;
import by.nikita.models.ContactData_;
import by.nikita.models.UserDetails;
import by.nikita.models.UserDetails_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ContactDataDao extends AbstractGenericDao<ContactData> implements IContactDataDao {

    public ContactDataDao() {
        super(ContactData.class);
    }

    @Override
    public List<ContactData> getContactDataByUserFirstName(String userFirstName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ContactData> query = criteriaBuilder.createQuery(ContactData.class);
            Root<ContactData> root = query.from(ContactData.class);
            Join<ContactData, UserDetails> userDetails = root.join(ContactData_.USER_DETAILS);
            query.select(root).where(criteriaBuilder.equal(userDetails.get(UserDetails_.FIRST_NAME), userFirstName));
            TypedQuery<ContactData> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ContactData> getContactDataByUserLastName(String userLastName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ContactData> query = criteriaBuilder.createQuery(ContactData.class);
            Root<ContactData> root = query.from(ContactData.class);
            Join<ContactData, UserDetails> userDetails = root.join(ContactData_.USER_DETAILS);
            query.select(root).where(criteriaBuilder.equal(userDetails.get(UserDetails_.LAST_NAME), userLastName));
            TypedQuery<ContactData> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
