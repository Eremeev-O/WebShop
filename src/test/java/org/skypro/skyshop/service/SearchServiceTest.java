package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    StorageService storageServiceMock;

    @InjectMocks
    SearchService searchService;

    @Test
    //Тестируем поиск в случае отсутствия объектов в StorageService
    public void testSearchWithNoObjects() {
        Mockito.when(storageServiceMock.generalCollection()).thenReturn(Collections.emptySet());

        List<SearchResult> results = searchService.search("Молоко");

        assertTrue(results.isEmpty());
    }

//    Тестируем поиск в случае, если объекты в StorageService есть, но нет подходящего
    @Test
    public void weTestTheCaseOfTheAbsenceOfTheDesiredObjectInTheList() {
        Set<Searchable> storageData = new HashSet<>();
        storageData.add(new SimpleProduct("Мороженое", 100));
        storageData.add(new FixPriceProduct("Твороженое"));

        Mockito.when(storageServiceMock.generalCollection()).thenReturn(storageData);

        List<SearchResult> results = searchService.search("Молоко");

        assertNotNull(results);
        assertTrue(results.isEmpty());

        Mockito.verify(storageServiceMock, Mockito.times(3)).generalCollection();
    }

    @Test
    //Тестируем поиск в случае, если объекты в StorageService есть, и есть подходящий
    public void thereAreObjectsAndThereIsASuitable() {
        Set<Searchable> storageData = new HashSet<>();
        storageData.add(new SimpleProduct("Мороженое", 100));
        storageData.add(new FixPriceProduct("Твороженое"));

        Mockito.when(storageServiceMock.generalCollection()).thenReturn(storageData);

        List<SearchResult> results = searchService.search("Мороженое");

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals("Мороженое", results.get(0).getName());

        Mockito.verify(storageServiceMock, Mockito.times(3)).generalCollection();
    }

    @Test
    // Тестируем поиск нескольких подходящих объектов
    public void testSearchMultipleMatches() {
        Set<Searchable> storageData = new HashSet<>();
        storageData.add(new SimpleProduct("Мороженое ванильное", 100));
        storageData.add(new SimpleProduct("Мороженое шоколадное", 120));
        storageData.add(new FixPriceProduct("Твороженое"));

        Mockito.when(storageServiceMock.generalCollection()).thenReturn(storageData);

        List<SearchResult> results = searchService.search("Мороженое");

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());

        assertTrue(results.stream().anyMatch(sr -> sr.getName().equals("Мороженое ванильное")));
        assertTrue(results.stream().anyMatch(sr -> sr.getName().equals("Мороженое шоколадное")));
    }

    @Test
    // Тестируем, что происходит при поиске пустой строки
    public void testSearchEmptyString() {
        Set<Searchable> storageData = new HashSet<>();
        storageData.add(new SimpleProduct("Мороженое", 100));
        storageData.add(new FixPriceProduct("Твороженое"));

        Mockito.when(storageServiceMock.generalCollection()).thenReturn(storageData);

        List<SearchResult> results = searchService.search("");

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(storageData.size(), results.size());
    }
}
