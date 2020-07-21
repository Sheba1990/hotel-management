package by.nikita.dao;

import by.nikita.dao.api.IPassportDataDao;
import by.nikita.models.PassportData;
import by.nikita.models.PassportData_;
import by.nikita.models.UserDetails;
import by.nikita.models.UserDetails_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class PassportDataDao extends AbstractGenericDao<PassportData> implements IPassportDataDao {

    public PassportDataDao() {
        super(PassportData.class);
    }

    @Override
    public List<PassportData> getPassportDataByUserFirstName(String userFirstName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<PassportData> query = criteriaBuilder.createQuery(PassportData.class);
            Root<PassportData> root = query.from(PassportData.class);
            Join<PassportData, UserDetails> userDetails = root.join(PassportData_.USER_DETAILS);
            query.select(root).where(criteriaBuilder.equal(userDetails.get(UserDetails_.FIRST_NAME), userFirstName));
            TypedQuery<PassportData> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<PassportData> getPassportDataByUserLastName(String userLastName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<PassportData> query = criteriaBuilder.createQuery(PassportData.class);
            Root<PassportData> root = query.from(PassportData.class);
            Join<PassportData, UserDetails> userDetails = root.join(PassportData_.USER_DETAILS);
            query.select(root).where(criteriaBuilder.equal(userDetails.get(UserDetails_.LAST_NAME), userLastName));
            TypedQuery<PassportData> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<PassportData> getPassportDataByUserFullName(String userFirstName, String userLastName) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<PassportData> query = criteriaBuilder.createQuery(PassportData.class);
            Root<PassportData> root = query.from(PassportData.class);
            Join<PassportData, UserDetails> userDetails = root.join(PassportData_.USER_DETAILS);
            Predicate predicateForFirstName = criteriaBuilder.equal(userDetails.get(UserDetails_.FIRST_NAME), userFirstName);
            Predicate predicateForLastName = criteriaBuilder.equal(userDetails.get(UserDetails_.LAST_NAME), userLastName);
            Predicate predicateForFullName = criteriaBuilder.and(predicateForFirstName, predicateForLastName);
            query.select(root).where(predicateForFullName);
            TypedQuery<PassportData> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
