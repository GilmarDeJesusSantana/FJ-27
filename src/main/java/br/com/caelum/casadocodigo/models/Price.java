package br.com.caelum.casadocodigo.models;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.math.BigDecimal;

@Embeddable
public class Price {

//	@Enumerated(EnumType.STRING)
    private BookType bookType;
	
    private BigDecimal price;

    @Override
    public String toString() {
        return "Price{" +
                "bookType=" + bookType +
                ", price=" + price +
                '}';
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	public BigDecimal getValue() {
		return price;
	}
}
