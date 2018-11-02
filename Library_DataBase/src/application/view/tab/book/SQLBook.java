package application.view.tab.book;

import application.Main;
import application.view.tab.person.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLBook {

    private Connection DBCon;
    private Statement st;
    private ResultSet rs;

    public SQLBook() {
        try {
            this.DBCon = Main.db.getDBCon();
            this.st = DBCon.createStatement();

        } catch (SQLException e) {
        }
    }

//    public ArrayList<String> getLanguage() {
//        ArrayList<String> result = new ArrayList<>();
//
//        String query = "SELECT lang from tbl_language inner join tbl_book on language_id = tbl_language_fk;";
//
//        try {
//            rs = st.executeQuery(query);
//
//            while (rs.next()) {
//                result.add(String.format("%-10s %s",
//                        rs.getString("storeName"), rs.getString("cityName")));
//            }
//        } catch (SQLException e) {
//        }
//        return result;
//    }

    /**
     * Load All Books matching the Search Criteria
     *
     * @param userSearch Criteria
     * @return ArrayList List of All Books machting the Criteria
     */
    public ArrayList<Book> loadBookSearchData(String userSearch) {

        ArrayList<Book> searchResult = new ArrayList<>();

        String query = "SELECT "
                + "* "
                + "FROM "
                + "m153.tbl_book "
                + "WHERE "
                + "title LIKE '" + userSearch + "%'"
                + "ORDER BY "
                + "title;";

        int i = 0;

        try {
            rs = st.executeQuery(query);

            while (rs.next()) {
                if (i > 15) break;

                searchResult.add(new Book(rs.getInt("book_id"), rs.getString("title")));

                i++;
            }

        } catch (SQLException e) {
        }

        return searchResult;
    }

    /**
     * Load All Data From Book specified
     *
     * @param b Book Object
     */
    public void loadBookData(Book b) {

        String basic = "SELECT "
                + "b.book_id, b.title, b.description, b.releaseDate, b.price_day, b.price_replace, b.rating,"
                + "c.cat AS category, "
                + "l.lang AS lang, "
                + "pu.company AS publisher, "
                + "a.firstName, a.lastName "

                + "FROM tbl_book AS b "

                + "LEFT JOIN tbl_category AS c "
                + "ON b.tbl_category_fk = c.category_id "

                + "LEFT JOIN tbl_language AS l "
                + "ON b.tbl_language_fk = l.language_id "

                + "LEFT JOIN tbl_publisher AS pu "
                + "ON b.tbl_publisher_fk = pu.publisher_id "

                + "LEFT JOIN tbl_author AS a "
                + "ON b.tbl_author_fk = a.author_id "

                + "WHERE book_id = '" + b.getID() + "';";


        try {

            rs = st.executeQuery(basic);
            rs.next();

            b.setTitle(rs.getString("title"));
            b.setDescription(rs.getString("description"));
            b.setReleaseDate(rs.getDate("releaseDate"));
            b.setPrice_day(rs.getString("price_day"));
            b.setPrice_replace(rs.getString("price_replace"));
            b.setRating(rs.getInt("rating"));
            b.setCompany(rs.getString("publisher"));
            b.setFirstName(rs.getString("firstName"));
            b.setLastName(rs.getString("lastName"));
            b.setLang(rs.getString("lang"));
            b.setCat(rs.getString("category"));

            
        } catch (SQLException e) {
        	System.out.println(e);
        }

    }

    /**
     * Insert New Category
     * @param categoryName New Category Name
     * @return Return Index of Category
     */
    public int newCategory(String categoryName) {

        String search =       "SELECT "
                + "category_id FROM tbl_category "
                + "WHERE cat = '" + categoryName + "';";

        String newCategory =   "INSERT INTO "
                + "tbl_category(cat) "
                + "VALUE ('" + categoryName + "');";

        try {
            rs = st.executeQuery(search);

            if (rs.next()) {
                return rs.getInt("category_id");
            }
            else {
                insert(newCategory);
                return newCategory(categoryName);
            }

        } catch (SQLException e) {}

        return 0;
    }

    /**
     * Insert New Language
     * @param languageName New Language Name
     * @return Return Index of Language
     */
    public int newLanguage(String languageName) {

        String search =       "SELECT "
                + "language_id FROM tbl_language "
                + "WHERE lang = '" + languageName + "';";

        String newLanguage =   "INSERT INTO "
                + "tbl_language(lang) "
                + "VALUE ('" + languageName + "');";

        try {
            rs = st.executeQuery(search);

            if (rs.next()) {
                return rs.getInt("language_id");
            }
            else {
                insert(newLanguage);
                return newLanguage(languageName);
            }

        } catch (SQLException e) {}

        return 0;
    }

    /**
     * Insert New Publisher
     * @param publisherName New Publisher Name
     * @return Return Index of Publisher
     */
    public int newPublisher(String publisherName) {

        String search =       "SELECT "
                + "publisher_id FROM tbl_publisher "
                + "WHERE company = '" + publisherName + "';";

        String newPublisher =   "INSERT INTO "
                + "tbl_publisher(company) "
                + "VALUE ('" + publisherName + "');";

        try {
            rs = st.executeQuery(search);

            if (rs.next()) {
                return rs.getInt("publisher_id");
            }
            else {
                insert(newPublisher);
                return newPublisher(publisherName);
            }

        } catch (SQLException e) {}

        return 0;
    }

    /**
     * Insert New Author
     * @param firstName New Author firstName
     * @param lastName  New Author lastName
     * @return Return Index of Author
     */

    public int newAuthor(String firstName, String lastName) {

        String search =       "SELECT "
                + "author_id FROM tbl_author "
                + "WHERE firstName = '" + firstName + "' AND lastName = '" + lastName + "';";

        String newAuthor =   "INSERT INTO "
                + "tbl_author(firstName, lastName) "
                + "VALUE ('" + firstName + "'" + "'" + lastName + "'" + ");";

        try {
            rs = st.executeQuery(search);

            if (rs.next()) {
                return rs.getInt("author_id");
            }
            else {
                insert(newAuthor);
                return newAuthor(firstName, lastName);
            }

        } catch (SQLException e) {}

        return 0;
    }

    /**
     * Insert Basic Book Information
     * @param b Book Object
     * @return int ID of Book
     */
    public int insertBasicBook(Book b) {

        String query = "INSERT "
                + "INTO tbl_book "
                + "(title, description, releaseDate, price_day, price_replace, rating, tbl_publisher_fk, tbl_author_fk, tbl_language_fk, tbl_category_fk) "
                + "VALUES "
                + "('" + b.getTitle() + "', '" + b.getDescription() + "', '" + b.getReleaseDate() + "', '" + b.getPrice_day() + "', '" + b.getPrice_replace() + "', '" + b.getRating() + "', '" + b.getPublisher_id() + "', '" + b.getAuthor_id() + "', '" + b.getLanguage_id() + "', '" + b.getCategory_id() + "');";


        String getID = "SELECT "
                + "book_id FROM tbl_book "
                + "WHERE "
                + "title = '" + b.getTitle()   + "' AND "
                + "description  = '" + b.getDescription()    + "' AND "
                + "releaseDate  = '" + b.getReleaseDate() + "' AND "
                + "price_day       = '" + b.getPrice_day() + "' AND "
                + "price_replace = '" + b.getPrice_replace() + "' AND "
                + "rating = '" + b.getRating() + "' AND "
                + "tbl_publisher_fk = '" + b.getPublisher_id() + "' AND "
                + "tbl_author_fk = '" + b.getAuthor_id() + "' AND "
                + "tbl_language_fk = '" + b.getLanguage_id() + "' AND "
                + "tbl_category_fk = '" + b.getCategory_id() + "' AND "
                + ";";

        insert(query);

        try {
            rs = st.executeQuery(getID);

            if (rs.next()) return rs.getInt("book_id");

        } catch (SQLException e) {}

        return 0;
    }


    /**
     * Update Basic Book information
     * @param b Book Object
     * @return boolean Did Insert Work
     */
    public boolean updateBasicBook(Book b) {

        String query =    "UPDATE "
                + "tbl_book "
                + "SET "
                + "title = '"      + b.getTitle()   + "', "
                + "description = '"       + b.getDescription()    + "', "
                + "releaseDate = '"          + b.getReleaseDate()       + "', "
                + "price_day = '"            + b.getPrice_day()         + "', "
                + "price_replace = '" + b.getPrice_replace()  + "', "
                + "rating = '"       + b.getRating() + "' "
                + "tbl_publisher_fk = '"       + b.getPublisher_id() + "' "
                + "tbl_author_fk = '"       + b.getAuthor_id() + "' "
                + "tbl_language_fk = '"       + b.getLanguage_id() + "' "
                + "tbl_category_fk = '"       + b.getCategory_id() + "' "
                + "WHERE "
                + "(book_id = '" + b.getID() + "');";


        return (insert(query) == 1) ? true : false;
    }

    /**
     * INSERT UPDATE IN DATABASE
     * @param query InsertQuery
     * @return boolean Did UPDATE Work
     */
    private int insert(String query) {
        try {
            return st.executeUpdate(query);

        } catch (SQLException e) {}
        return 0;
    }
}
