package zlagoda.zlagoda.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, K> extends AutoCloseable {
    List<T> getAll();

    Optional<T> getById(final K id);

    void create(final T e);

    void update(final T e);

    void delete(final K id);

    @Override
    void close();
}
