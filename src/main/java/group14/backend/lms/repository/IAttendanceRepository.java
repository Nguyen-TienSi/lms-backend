package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAttendanceRepository extends CrudRepository<Attendance, Long> {
    List<Attendance> findAttendanceByRoomId(long roomId);
}
