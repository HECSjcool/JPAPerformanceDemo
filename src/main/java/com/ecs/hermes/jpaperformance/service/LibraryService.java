package com.ecs.hermes.jpaperformance.service;

import com.ecs.hermes.jpaperformance.persistence.dao.IGenericDAO;
import com.ecs.hermes.jpaperformance.persistence.domain.Book;
import com.ecs.hermes.jpaperformance.persistence.domain.Library;
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
public class LibraryService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    @Qualifier("libraryDAO")
    private IGenericDAO genericDAO;


    @Transactional
    public List<Library> saveACollectionOfLibraries(List<Library> libraryList) {
        return genericDAO.saveACollection(libraryList);

    }


    @Transactional
    public void retrieveAllBooksAndUpdateLastReadDate(Date date) {

        List<Library> libraryList = genericDAO.findAll();

        for (Library oneLib : libraryList) {

            Set<Book> booksToKeep = new HashSet<Book>();

            for (Book oneBook : oneLib.getBookSet()) {

                updateBook(date, oneBook);
                booksToKeep.add(oneBook);
            }

            for (Book b : booksToKeep) {
                oneLib.getBookSet().add(b);
            }
        }
    }

    private void updateBook(Date date, Book oneBook) {

        oneBook.setLastReadDate(date);
    }


    @Transactional
    public void deleteAllLibraries() {
        List<Library> libraryList = genericDAO.findAll();

        for (Library lib : libraryList) {

            genericDAO.delete(lib);
        }
    }

    @Transactional
    public void deleteAllBookForOneLibrary() {
        List<Library> libraryList = genericDAO.findAll();

        for (Library lib : libraryList) {


            lib.getBookSet().clear();
            break;

        }
    }
}
