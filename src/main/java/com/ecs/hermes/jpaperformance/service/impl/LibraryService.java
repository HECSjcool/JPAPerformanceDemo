package com.ecs.hermes.jpaperformance.service.impl;

import com.ecs.hermes.jpaperformance.persistence.dao.IGenericDAO;
import com.ecs.hermes.jpaperformance.persistence.domain.Book;
import com.ecs.hermes.jpaperformance.persistence.domain.Library;
import com.ecs.hermes.jpaperformance.service.ILibraryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 7/01/13
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "libraryService")
public class LibraryService implements ILibraryService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    @Qualifier("libraryDAO")
    private IGenericDAO genericDAO;

    @Override
    @Transactional
    public List<Library> saveACollectionOfLibraries(List<Library> libraryList) {
        return genericDAO.saveACollection(libraryList);

    }

    @Override
    @Transactional
    public void retrieveAllBooksAndUpdateLastReadDate(Date date) {

        List<Library> libraryList = genericDAO.findAll();
        logger.info("Libraries retrieved");
        for (Library oneLib : libraryList) {

            Set<Book> booksToKeep = new HashSet<Book>(oneLib.getBookSet().size());

            for (Book oneBook : oneLib.getBookSet()) {

                updateBook(date, oneBook);
                booksToKeep.add(oneBook);
            }

            for (Book b : booksToKeep) {
                oneLib.getBookSet().add(b);
            }
        }
    }

    private static void updateBook(Date date, Book oneBook) {

        oneBook.setLastReadDate(date);
    }

    @Override
    @Transactional
    public void deleteAllLibraries() {
        List<Library> libraryList = genericDAO.findAll();

        for (Library lib : libraryList) {

            genericDAO.delete(lib);
        }
    }

    @Override
    @Transactional
    public void deleteAllBookForOneLibrary() {
        List<Library> libraryList = genericDAO.findAll();

        //Better to clear all list than one by one
        libraryList.get(0).getBookSet().clear();

    }

    @Override
    public List<Library> getAllLibraries() {
        return genericDAO.findAll();  //To change body of implemented methods use File | Settings | File Templates.
    }
}
