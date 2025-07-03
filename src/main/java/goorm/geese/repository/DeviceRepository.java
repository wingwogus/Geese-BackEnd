package goorm.geese.repository;

import goorm.geese.domain.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
