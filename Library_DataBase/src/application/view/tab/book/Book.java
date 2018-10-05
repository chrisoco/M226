package application.view.tab.book;

import java.sql.Date;

public class Book {
    private int ID, rating, publisher_id, author_id, language_id, category_id;
    private String title, description, price_day, price_replace, company, firstName, lastName, lang, cat;
    private Date releaseDate;

    /**Book Loader for List**/
    public Book(int ID, int rating, String title, String description, String price_day, String price_replace, Date releaseDate) {
        this.ID = ID;
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.price_day = price_day;
        this.price_replace = price_replace;
        this.releaseDate = releaseDate;
    }

    /**CREATE NEW BOOK**/
    public Book(int ID, int rating, int publisher_id, int author_id, int language_id, int category_id, String title, String description, String price_day, String price_replace, String company, String firstName, String lastName, String lang, String cat, Date releaseDate) {
        this.ID = ID;
        this.rating = rating;
        this.publisher_id = publisher_id;
        this.author_id = author_id;
        this.language_id = language_id;
        this.category_id = category_id;
        this.title = title;
        this.description = description;
        this.price_day = price_day;
        this.price_replace = price_replace;
        this.company = company;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lang = lang;
        this.cat = cat;
        this.releaseDate = releaseDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice_day() {
        return price_day;
    }

    public void setPrice_day(String price_day) {
        this.price_day = price_day;
    }

    public String getPrice_replace() {
        return price_replace;
    }

    public void setPrice_replace(String price_replace) {
        this.price_replace = price_replace;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
