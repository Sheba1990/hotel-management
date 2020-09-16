package by.nikita.dao;

import by.nikita.dao.api.IAddressDao;
import by.nikita.models.*;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressDao extends AbstractGenericDao<Address> implements IAddressDao {

    public AddressDao() {
        super(Address.class);
    }


    //Get mapping methods

    @Override
    public List<Address> getAddressByUserFirstName(String userFirstName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Address> query = criteriaBuilder.createQuery(Address.class);
            Root<Address> root = query.from(Address.class);
            Join<Address, ContactData> contactData = root.join(Address_.CONTACT_DATA);
            Join<ContactData, UserInDetails> userDetails = contactData.join(ContactData_.USER_IN_DETAILS);
            List<Predicate> conditions = new ArrayList<>();
            conditions.add(criteriaBuilder.equal(userDetails.get(UserInDetails_.FIRST_NAME), userFirstName));
            query.select(root).where(conditions.toArray(new Predicate[]{}));
            TypedQuery<Address> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Address> getAddressByUserLastName(String userLastName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Address> query = criteriaBuilder.createQuery(Address.class);
            Root<Address> root = query.from(Address.class);
            Join<Address, ContactData> contactData = root.join(Address_.CONTACT_DATA);
            Join<ContactData, UserInDetails> userDetails = contactData.join(ContactData_.USER_IN_DETAILS);
            List<Predicate> conditions = new ArrayList<>();
            conditions.add(criteriaBuilder.equal(userDetails.get(UserInDetails_.LAST_NAME), userLastName));
            query.select(root).where(conditions.toArray(new Predicate[]{}));
            TypedQuery<Address> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Address> getAddressByCountry(String country) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Address> query = criteriaBuilder.createQuery(Address.class);
            Root<Address> root = query.from(Address.class);
            query.select(root).where(criteriaBuilder.equal(root.get(Address_.COUNTRY), country));
            TypedQuery<Address> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Address> getAddressByPostalCode(String postalCode) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Address> query = criteriaBuilder.createQuery(Address.class);
            Root<Address> root = query.from(Address.class);
            query.select(root).where(criteriaBuilder.equal(root.get(Address_.POSTAL_CODE), postalCode));
            TypedQuery<Address> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Address> getAddressByCity(String city) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Address> query = criteriaBuilder.createQuery(Address.class);
            Root<Address> root = query.from(Address.class);
            query.select(root).where(criteriaBuilder.equal(root.get(Address_.CITY), city));
            TypedQuery<Address> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
