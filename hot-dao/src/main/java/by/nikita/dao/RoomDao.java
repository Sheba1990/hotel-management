package by.nikita.dao;

import by.nikita.dao.api.IRoomDao;
import by.nikita.models.*;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RoomDao extends AbstractGenericDao<Room> implements IRoomDao {

    public RoomDao() {
        super(Room.class);
    }

    @Override
    public List<Room> getRoomByNumber(Integer roomNumber) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            query.select(root).where(criteriaBuilder.equal(root.get(Room_.ROOM_NUMBER), roomNumber));
            TypedQuery<Room> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Room> getRoomsByCategory(String roomCategory) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            Join<Room, RoomCategory> category = root.join(Room_.ROOM_CATEGORY);
            query.select(root).where(criteriaBuilder.equal(category.get(RoomCategory_.CATEGORY_NAME), roomCategory));
            TypedQuery<Room> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Room> getRoomsByStatus(String roomStatus) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            Join<Room, RoomStatus> status = root.join(Room_.ROOM_STATUS);
            query.select(root).where(criteriaBuilder.equal(status.get(RoomStatus_.STATUS_NAME), roomStatus));
            TypedQuery<Room> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Room> getRoomsByCapacity(Integer roomCapacity) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            Join<Room, RoomDetails> roomDetails = root.join(Room_.ROOM_DETAILS);
            query.select(root).where(criteriaBuilder.equal(roomDetails.get(RoomDetails_.CAPACITY), roomCapacity));
            TypedQuery<Room> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Room> getRoomByAmountOfRoom(Integer amountOfRooms) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            Join<Room, RoomDetails> roomDetails = root.join(Room_.ROOM_DETAILS);
            query.select(root).where(criteriaBuilder.equal(roomDetails.get(RoomDetails_.AMOUNT_OF_ROOMS), amountOfRooms));
            TypedQuery<Room> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
