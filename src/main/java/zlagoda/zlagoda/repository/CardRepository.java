package zlagoda.zlagoda.repository;

import zlagoda.zlagoda.entity.CardEntity;

import java.util.List;

public interface CardRepository extends BaseRepository<CardEntity, Integer> {
    List<CardEntity> searchCardsByPercent(int percent);
    List<CardEntity> searchCardsByCustomerSurname(String surname);
}
