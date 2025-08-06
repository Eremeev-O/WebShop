package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService extends StorageService {
    private final StorageService searchService;

    public SearchService(StorageService searchService) {
        this.searchService = searchService;
    }

    public List<SearchResult> search(String string) {
        return generalCollection().stream()
                .filter(object -> object.toString().toLowerCase().contains(string.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }

}
