package zlagoda.zlagoda.repository;

import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.entity.enums.UserRole;

import java.util.List;

public interface UserRepository extends BaseRepository<UserEntity, String> {
    List<UserEntity> searchUsersByRole(UserRole role);
    List<UserEntity> searchUsersBySurname(String surname);
}
