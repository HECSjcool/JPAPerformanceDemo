package com.ecs.hermes.jpaperformance.service;

import com.ecs.hermes.jpaperformance.persistence.domain.Library;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 1/24/13
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ILibraryService {
    @Transactional
    List<Library> saveACollectionOfLibraries(List<Library> libraryList);

    @Transactional
    void retrieveAllBooksAndUpdateLastReadDate(Date date);

    @Transactional
    void deleteAllLibraries();

    @Transactional
    void deleteAllBookForOneLibrary();
}
