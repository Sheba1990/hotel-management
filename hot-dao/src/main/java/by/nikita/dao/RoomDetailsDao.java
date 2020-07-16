package by.nikita.dao;

import by.nikita.dao.api.IRoomDetailsDao;
import by.nikita.models.Room;
import by.nikita.models.RoomDetails;
import by.nikita.models.RoomDetails_;
import by.nikita.models.Room_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@Repository
public class RoomDetailsDao extends AbstractGenericDao<RoomDetails> implements IRoomDetailsDao {

    public RoomDetailsDao() {
        super(RoomDetails.class);
    }

    @Override
    public RoomDetails getRoomDetailsByRoomNumber(Integer roomNumber) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<RoomDetails> query = criteriaBuilder.createQuery(RoomDetails.class);
            Root<RoomDetails> root = query.from(RoomDetails.class);
            Join<RoomDetails, Room> room = root.join(RoomDetails_.ROOMS);
            query.select(root).where(criteriaBuilder.equal(room.get(Room_.ROOM_NUMBER), roomNumber));
            TypedQuery<RoomDetails> result = entityManager.createQuery(query);
            return result.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
