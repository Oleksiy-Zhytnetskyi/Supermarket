package zlagoda.zlagoda.service;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.CategoryEntity;
import zlagoda.zlagoda.repository.BaseRepositoryFactory;
import zlagoda.zlagoda.repository.CategoryRepository;
import zlagoda.zlagoda.view.CategoryView;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CategoryService {

    private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);

    private static final String GET_ALL_CATEGORIES = "Get all categories";
    private static final String GET_CATEGORY_BY_ID = "Get category by id: %d";
    private static final String CREATE_CATEGORY = "Create category: %s";
    private static final String UPDATE_CATEGORY = "Update category: %s";
    private static final String DELETE_CATEGORY = "Delete category: %s";

    private final BaseRepositoryFactory repositoryFactory;

    private static class Holder {
        static final CategoryService INSTANCE = new CategoryService(BaseRepositoryFactory.getRepositoryFactory());
    }

    public static CategoryService getInstance() { return Holder.INSTANCE; }

    public List<CategoryEntity> getAllCategories() {
        LOGGER.info(GET_ALL_CATEGORIES);
        try (CategoryRepository repository = repositoryFactory.createCategoryRepository()) {
            return repository.getAll();
        }
    }

    public Optional<CategoryEntity> getCategoryById(Integer id) {
        LOGGER.info(String.format(GET_CATEGORY_BY_ID, id));
        try (CategoryRepository repository = repositoryFactory.createCategoryRepository()) {
            return repository.getById(id);
        }
    }

    public void createCategory(CategoryView categoryView) {
        LOGGER.info(String.format(CREATE_CATEGORY, categoryView.getName()));
        CategoryEntity category = buildCategoryFromView(categoryView);
        try (CategoryRepository repository = repositoryFactory.createCategoryRepository()) {
            repository.create(category);
        }
    }

    public void updateCategory(CategoryView categoryView) {
        LOGGER.info(String.format(UPDATE_CATEGORY, categoryView.getId()));
        CategoryEntity category = buildCategoryFromView(categoryView);
        try (CategoryRepository repository = repositoryFactory.createCategoryRepository()) {
            repository.update(category);
        }
    }

    public void setDeleteCategory(Integer id) {
        LOGGER.info(String.format(DELETE_CATEGORY, id));
        try (CategoryRepository repository = repositoryFactory.createCategoryRepository()) {
            repository.delete(id);
        }
    }

    private static CategoryEntity buildCategoryFromView(CategoryView view) {
        return CategoryEntity.builder()
                .id(view.getId())
                .name(view.getName())
                .build();
    }
}
