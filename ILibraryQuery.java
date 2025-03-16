package domain.services.querymanager;

public interface ILibraryQuery {
	public String findMostIssuedBookName();
	public String findMostIssuedMember();
	public String findHighestPenaltyForLateReturn();
	public String findBookWithMostCopies();
	public String findBookWithLeastCopiesAndIssuedBefore();
	public String findLeastIssuedMember(int libraryIndex);
}
