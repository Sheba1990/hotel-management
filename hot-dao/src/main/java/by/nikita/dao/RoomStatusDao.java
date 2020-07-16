package by.nikita.dao;

import by.nikita.dao.api.IRoomStatusDao;
import by.nikita.models.Room;
import by.nikita.models.RoomStatus;
import by.nikita.models.RoomStatus_;
import by.nikita.models.Room_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@Repository
public class RoomStatusDao extends AbstractGenericDao<RoomStatus> implements IRoomStatusDao {

    public RoomStatusDao() {
        super(RoomStatus.class);
    }

    @Override
    public RoomStatus getRoomStatusByRoomNumber(Integer roomNumber) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<RoomStatus> query = criteriaBuilder.createQuery(RoomStatus.class);
            Root<RoomStatus> root = query.from(RoomStatus.class);
            Join<RoomStatus, Room> room = root.join(RoomStatus_.ROOMS);
            query.select(root).where(criteriaBuilder.equal(room.get(Room_.ROOM_NUMBER), roomNumber));
            TypedQuery<RoomStatus> result = entityManager.createQuery(query);
            return result.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
