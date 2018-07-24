package br.com.caelum.casadocodigo.models;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Product {

	private String summaryPath;

	public String getSummaryPath() {
		return summaryPath;
	}

	public void setSummaryPath(String summaryPath) {
		this.summaryPath = summaryPath;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	private String title;

	@Lob
	@NotEmpty
	private String description;

	@NotEmpty
	private String author;

	@Min(1)
	@Max(500)
	private Integer numberOfPages;

	@ElementCollection
	private List<Price> prices;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Calendar releaseDate;

	@Override
	public String toString() {
		return "Product{" + "titulo='" + title + '\'' + ", description='" + description + '\'' + ", author='" + author
				+ '\'' + ", numberOfPages=" + numberOfPages + "prices = " + prices.toString() + "data = " + releaseDate
				+ '}';
	}

	public Calendar getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public Long getId() {
		return id;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	// public BigDecimal priceFor(BookType bookType) {
	// return prices
	// .stream()
	// .filter(price -> price.getBookType().equals(bookType))
	// .findFirst().get().getValue();
	// }

	public BigDecimal priceFor(BookType bookType) {
		BigDecimal valor = null;
		for (Price price : prices) {
			if (price.getBookType().equals(bookType)) {
				valor = price.getValue();
			}
		}
		return valor;
	}

}
