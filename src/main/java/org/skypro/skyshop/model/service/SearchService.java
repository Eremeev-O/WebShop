package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String string) {
        if (storageService.generalCollection() == null || storageService.generalCollection().isEmpty()){
            return List.of();
        } else {
            return storageService.generalCollection().stream()
                    .filter(object -> object.toString().toLowerCase().contains(string.toLowerCase()))
                    .filter(Objects::nonNull)
                    .map(SearchResult::fromSearchable)
                    .collect(Collectors.toList());
        }


    }


}
