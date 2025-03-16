package main;

import domain.classes.*;
import domain.services.querymanager.*;
import infra.issueloader.*;
import util.*;

public class LibraryManagementApp {
	// define resource folder path
	public static final String FOLDER_PATH = "./src/resources/";

	public static void main(String[] args) {

		// define file paths
		var booksPaths = new String[] { "L1_Books.csv", "L2_Books.csv", "L3_Books.csv" };
		var issuesPaths = new String[] { "L1_Issues.csv", "L2_Issues.csv", "L3_Issues.csv" };
		var memberPath = "Members.csv";

		Library[] libraries;
		LibraryManagement libraryManager;
		MemberManagement memberManager;

		// handle errors. print log, then exit.
		try {
			libraries = Helper.importLibraryBooksCsvs(FOLDER_PATH, booksPaths);
			libraryManager = Helper.importLibraryIssuesCsvs(FOLDER_PATH, issuesPaths);
			memberManager = Helper.importMemberCsv(FOLDER_PATH, memberPath);
		} catch (Exception e) {
			System.out.println(e);
			return;
		}

		
		// we use issue loader to load related books and members data in issues
		IIssueLoader issueLoader = new IssueLoader(libraryManager, libraries, memberManager);

		issueLoader.loadIssues();

		ILibraryQuery queryManager = new LibraryQuery(libraryManager, libraries, memberManager);
		
		System.out.println(queryManager.findMostIssuedBookName());
		System.out.println(queryManager.findMostIssuedMember());
		System.out.println(queryManager.findHighestPenaltyForLateReturn());
		System.out.println(queryManager.findBookWithMostCopies());
		System.out.println(queryManager.findBookWithLeastCopiesAndIssuedBefore());
		System.out.println(queryManager.findLeastIssuedMember(2));
		 
	}
}
