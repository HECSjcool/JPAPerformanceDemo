package com.ecs.hermes.jpaperformance.service;

import com.ecs.hermes.jpaperformance.persistence.domain.Book;
import com.ecs.hermes.jpaperformance.persistence.domain.Library;
import com.ecs.hermes.jpaperformance.utils.SpringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 7/01/13
 * Time: 16:12
 * To change this template use File | Settings | File Templates.
 */
public class TestLibraryService {

    static ILibraryService ILibraryService;
    static ApplicationContext context = SpringUtils.init();

    @BeforeClass
    public static void setUp() {

        ILibraryService = (ILibraryService) context.getBean("libraryService");
    }

    @Test
    public void testCreateCollectionOfLibraries() {

        List<Library> libraryList = createDummyLibrariesAndBooks(2, 5);
        ILibraryService.saveACollectionOfLibraries(libraryList);

    }

    private static List<Library> createDummyLibrariesAndBooks(int numbOfLibToBeCreated, int numbOfBookPerLib) {
        List<Library> libraryList = new ArrayList<Library>(numbOfLibToBeCreated);

        for (Integer i = 0; i < numbOfLibToBeCreated; i++) {

            Library l = new Library();
            l.setName("name" + System.currentTimeMillis());

            Set<Book> bookSet = new HashSet<Book>(numbOfBookPerLib);
            for (Integer j = 0; j < numbOfBookPerLib; j++) {

                Book b = new Book();
                b.setIsdn(i.toString());
                b.setTitle(j.toString());
                bookSet.add(b);

            }
            l.setBookSet(bookSet);
            libraryList.add(l);
        }
        return libraryList;
    }

    /**
     * <p/>
     * Hibernate: update Book set LIB_ID=? where id=?*
     */
    @Test
    public void testUpdate() {
        List<Library> libraryList = createDummyLibrariesAndBooks(2, 5);
        ILibraryService.saveACollectionOfLibraries(libraryList);
        ILibraryService.retrieveAllBooksAndUpdateLastReadDate(new Date());

    }

    @Test
    public void testDelete() {
        List<Library> libraryList = createDummyLibrariesAndBooks(2, 5);
        ILibraryService.saveACollectionOfLibraries(libraryList);
        ILibraryService.deleteAllBookForOneLibrary();
    }

}
