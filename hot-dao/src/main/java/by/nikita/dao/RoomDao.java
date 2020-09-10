package by.nikita.dao;

import by.nikita.dao.api.IRoomDao;
import by.nikita.models.*;
import by.nikita.models.enums.RoomStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class RoomDao extends AbstractGenericDao<Room> implements IRoomDao {

    public RoomDao() {
        super(Room.class);
    }

    @Override
    public Room getRoomByNumber(Integer roomNumber) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            query.select(root).where(criteriaBuilder.equal(root.get(Room_.ROOM_NUMBER), roomNumber));
            TypedQuery<Room> result = entityManager.createQuery(query);
            return result.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Room> getRoomByCategoryAndCapacity(String roomCategory, Integer capacity) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            Join<Room, RoomDetails> roomDetails = root.join(Room_.ROOM_DETAILS);
            Join<Room, RoomCategory> category = root.join(Room_.ROOM_CATEGORY);
            Predicate predicateForRoomCapacity = criteriaBuilder.equal(roomDetails.get(RoomDetails_.CAPACITY), capacity);
            Predicate predicateForRoomCategory = criteriaBuilder.equal(category.get(RoomCategory_.CATEGORY_NAME), roomCategory);
            Predicate predicateForCategoryAndCapacity = criteriaBuilder.and(predicateForRoomCapacity, predicateForRoomCategory);
            query.select(root).where(predicateForCategoryAndCapacity);
            TypedQuery<Room> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Room> getRoomsByDeluxeCategory() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            Join<Room, RoomCategory> category = root.join(Room_.ROOM_CATEGORY);
            query.select(root).where(criteriaBuilder.equal(category.get(RoomCategory_.CATEGORY_NAME), "DELUXE"));
            query.orderBy(criteriaBuilder.asc(category.get(RoomCategory_.CATEGORY_NAME)));
            TypedQuery<Room> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Room> getRoomsByBusinessCategory() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            Join<Room, RoomCategory> category = root.join(Room_.ROOM_CATEGORY);
            query.select(root).where(criteriaBuilder.equal(category.get(RoomCategory_.CATEGORY_NAME), "BUSINESS"));
            TypedQuery<Room> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Room> getRoomsByStandardCategory() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            Join<Room, RoomCategory> category = root.join(Room_.ROOM_CATEGORY);
            query.select(root).where(criteriaBuilder.equal(category.get(RoomCategory_.CATEGORY_NAME), "STANDARD"));
            TypedQuery<Room> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Room> getRoomsByEconomCategory() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            Join<Room, RoomCategory> category = root.join(Room_.ROOM_CATEGORY);
            query.select(root).where(criteriaBuilder.equal(category.get(RoomCategory_.CATEGORY_NAME), "ECONOM"));
            TypedQuery<Room> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Room> getRoomsWhereStatusIsVacant() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            query.select(root).where(criteriaBuilder.equal(root.get(Room_.roomStatus), RoomStatus.VACANT));
            TypedQuery<Room> result = entityManager.createQuery(query);
            return result.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Room> getRoomsWhereStatusIsOccupied() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
            Root<Room> root = query.from(Room.class);
            query.select(root).where(criteriaBuilder.equal(root.get(Room_.roomStatus), RoomStatus.OCCUPIED));
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
    public List<Room> getRoomByAmountOfRooms(Integer amountOfRooms) {
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
