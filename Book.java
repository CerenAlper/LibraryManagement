package domain.classes;

public class Book {
	private int id;
	private String title;
	private String author;
	private String publisher;
	private String edition;
	private String genre;
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getEdition() {
		return edition;
	}
	public String getGenre() {
		return genre;
	}
	
	public Book(int id, String title, String author, String publisher, String edition, String genre, int quantity) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.edition = edition;
		this.genre = genre;
		this.quantity = quantity;
	}
}
