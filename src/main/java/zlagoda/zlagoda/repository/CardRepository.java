package zlagoda.zlagoda.repository;

import zlagoda.zlagoda.entity.CardEntity;

import java.util.List;

public interface CardRepository extends BaseRepository<CardEntity, String> {
    List<CardEntity> searchCardsByPercent(int percent);
    List<CardEntity> searchByCustomerSurname(String surname);
}
