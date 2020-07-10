package by.nikita.dao;

import by.nikita.dao.api.IAddressDao;
import by.nikita.models.Address;
import by.nikita.models.Address_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AddressDao extends AGenericDao<Address> implements IAddressDao {

    public AddressDao() {
        super(Address.class);
    }

    List<Address> getAddressByCountry(String country) {
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

}
