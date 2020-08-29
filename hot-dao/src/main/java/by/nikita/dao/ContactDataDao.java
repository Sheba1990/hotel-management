package by.nikita.dao;

import by.nikita.dao.api.IContactDataDao;
import by.nikita.models.ContactData;
import by.nikita.models.ContactData_;
import by.nikita.models.UserInDetails;
import by.nikita.models.UserInDetails_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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
            Join<ContactData, UserInDetails> userDetails = root.join(ContactData_.USER_IN_DETAILS);
            query.select(root).where(criteriaBuilder.equal(userDetails.get(UserInDetails_.FIRST_NAME), userFirstName));
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
            Join<ContactData, UserInDetails> userDetails = root.join(ContactData_.USER_IN_DETAILS);
            query.select(root).where(criteriaBuilder.equal(userDetails.get(UserInDetails_.LAST_NAME), userLastName));
            TypedQuery<ContactData> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ContactData> getContactDataByUserFullName(String userFirstName, String userLastName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ContactData> query = criteriaBuilder.createQuery(ContactData.class);
            Root<ContactData> root = query.from(ContactData.class);
            Join<ContactData, UserInDetails> userDetails = root.join(ContactData_.USER_IN_DETAILS);
            Predicate predicateForFirstName = criteriaBuilder.equal(userDetails.get(UserInDetails_.FIRST_NAME), userFirstName);
            Predicate predicateForLastName = criteriaBuilder.equal(userDetails.get(UserInDetails_.LAST_NAME), userLastName);
            Predicate predicateForFullName = criteriaBuilder.and(predicateForFirstName, predicateForLastName);
            query.select(root).where(predicateForFullName);
            TypedQuery<ContactData> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
