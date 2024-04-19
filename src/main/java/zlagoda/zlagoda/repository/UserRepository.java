package zlagoda.zlagoda.repository;

import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.entity.enums.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<UserEntity, String> {
    Optional<UserEntity> getUserByCredentials(String email, String password);
    Optional<UserEntity> getUserByEmail(String email);
    List<UserEntity> searchUsersByRole(UserRole role);
    List<UserEntity> searchUsersBySurname(String surname);
}
