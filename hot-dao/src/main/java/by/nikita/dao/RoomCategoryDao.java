package by.nikita.dao;

import by.nikita.dao.api.IRoomCategoryDao;
import by.nikita.models.Room;
import by.nikita.models.RoomCategory;
import by.nikita.models.RoomCategory_;
import by.nikita.models.Room_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@Repository
public class RoomCategoryDao extends AbstractGenericDao<RoomCategory> implements IRoomCategoryDao {

    public RoomCategoryDao() {
        super(RoomCategory.class);
    }

    @Override
    public RoomCategory getRoomCategoryByRoomNumber(Integer roomNumber) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<RoomCategory> query = criteriaBuilder.createQuery(RoomCategory.class);
            Root<RoomCategory> root = query.from(RoomCategory.class);
            Join<RoomCategory, Room> room = root.join(RoomCategory_.ROOMS);
            query.select(root).where(criteriaBuilder.equal(room.get(Room_.ROOM_NUMBER), roomNumber));
            TypedQuery<RoomCategory> result = entityManager.createQuery(query);
            return result.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
