package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dto.CategoryDto;
import kg.attractor.jobsearchjava27.model.Category;
import kg.attractor.jobsearchjava27.repository.CategoryRepository;
import kg.attractor.jobsearchjava27.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private CategoryDto toDto(Category c) {
        return CategoryDto.builder()
                .id(c.getId())
                .name(c.getName())
                .parentId(c.getParent() != null ? c.getParent().getId() : null)
                .build();
    }
}
