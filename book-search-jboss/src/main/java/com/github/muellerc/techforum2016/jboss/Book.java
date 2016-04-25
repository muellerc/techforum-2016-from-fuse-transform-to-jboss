package com.github.muellerc.techforum2016.jboss;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement
@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ISBN", nullable = false, length = 10)
    private String isbn;
    @Column(name = "TITLE", nullable = false, length = 40)
    private String title;
    @Column(name = "AUTHOR", nullable = false, length = 40)
    private String author;
    // TODO ignore this field in JPA
    private BigDecimal price;
    private String image;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
