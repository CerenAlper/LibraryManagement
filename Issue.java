package domain.classes;

import java.time.Duration;
import java.util.Date;

public class Issue {
	private int id;
	private int memberId;
	private int bookId;
	private Date issueDate;
	private Date returningDate;
	private Book book;
	private Member member;

	public int getId() {
		return id;
	}

	public int getMemberId() {
		return memberId;
	}

	public int getBookId() {
		return bookId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public Date getReturningDate() {
		return returningDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Issue(int id, int memberId, int bookId, Date issueDate, Date returningDate) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.bookId = bookId;
		this.issueDate = issueDate;
		this.returningDate = returningDate;
	}
	
	public double calculateDelayCost() {
		var delayInMillis = returningDate.getTime() - issueDate.getTime() - Duration.ofDays(14).toMillis();
		
		var delayDuration = Duration.ofMillis(delayInMillis);
				
		var cost =  delayDuration.toDays() * 0.5;
		
		return cost > 0 ? cost : 0;
	}

}
