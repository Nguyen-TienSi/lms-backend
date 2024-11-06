package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRoomRepository extends CrudRepository<Room, Long> {
    List<Room> findRoomsByCourseId(long courseId);
}
