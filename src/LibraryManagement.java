//import java.util.Date;
//import java.util.List;
//import java.util.HashMap;
//
//public class LibraryManagement {
//}
//
//enum BookFormat{
//    HARDCOVER,
//    PAPERBACK,
//    AUDIO_BOOK,
//    EBOOK,
//    NEWSPAPER,
//    MAGAZINE,
//    JOURNAL
//}
//
//enum BookStatus{
//    AVAILABLE,
//    RESERVED,
//    LOANED,
//    LOST
//}
//
//enum ReservationStatus{
//    WAITING,
//    PENDING,
//    COMPLETED,
//    CANCELED,
//    NONE
//}
//
//enum AccountStatus{
//    ACTIVE,
//    CLOSED,
//    CANCELED,
//    BLACKLISTED,
//    NONE
//}
//
//class Address {
//    private String streetAddress;
//    private String city;
//    private String state;
//    private String zipCode;
//    private String country;
//}
//
//class Person {
//    private String name;
//    private String email;
//    private Address address;
//    private String phone;
//}
//
//class Constants{
//    public static final int MAX_BOOKS_ISSUED_TO_A_USER = 5;
//    public static final int MAX_LENDING_DAYS = 10;
//}
//
//abstract class Account {
//    private String id;
//    private String password;
//    private AccountStatus status;
//    private Person person;
//
//    public boolean resetPassword(){
//        return false;
//    };
//}
//
////class Librarian extends Account {
////    public boolean addBookItem(BookItem bookItem){return false;};
////
////    public boolean blockMember(Member member){return false;};
////
////    public boolean unBlockMember(Member memeber){return false;};
////}
//
//class Member extends Account {
//    private Date dateofMembership;
//    private int totalBooksCheckedout;
//
//    public int getTotalBooksCheckedout(){return - 1;};
//
//    public boolean reserveBookItem(BookItem bookItem){return false;};
//
//    private void incrementTotalBooksCheckedout(){};
//
//    public boolean checkoutBookItem(BookItem bookItem) {
//        if (this.getTotalBooksCheckedout() >= Constants.MAX_BOOKS_ISSUED_TO_A_USER) {
//            System.out.println("The user has already checked-out maximum number of books");
//            return false;
//        }
////        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
////        if (bookReservation != null && bookReservation.getMemberId() != this.getId()) {
////            // book item has a pending reservation from another user
////            System.out.println("This book is reserved by another member");
////            return false;
////        } else if (bookReservation != null) {
////            // book item has a pending reservation from the give member, update it
////            bookReservation.updateStatus(ReservationStatus.COMPLETED);
////        }
////
////        if (!bookItem.checkout(this.getId())) {
////            return false;
////        }
////
////        this.incrementTotalBooksCheckedout();
//        return true;
//    }
//
////    private void checkForFind(String bookItemBarcode) {
////        BookLending bookLending = BookLending.fetchLendingDetails(bookItemBarcode);
////        Date dueDate = bookLending.getDueDate();
////        Date today = new Date();
////        // check if the book has been returned within the due date
////        if (today.compareTo(dueDate) > 0) {
////            long diff = todayDate.getTime() - dueDate.getTime();
////            long diffDays = diff / (24 * 60 * 60 * 1000);
////            Fine.collectFine(memberId, diffDays);
////        }
////    }
////
////    public void returnBookItem(BookItem bookItem) {
////        this.checkForFine(bookItem.getBarcode());
////        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
////        if (bookReservation != null) {
////            // book item has a pending reservation
////            bookItem.updateBookItemStatus(BookStatus.RESERVED);
////            bookReservation.sendBookAvailableNotification();
////        }
////        bookItem.updateBookItemStatus(BookStatus.AVAILABLE);
////    }
////
////    public boolean renewBookItem(BookItem bookItem) {
////        this.checkForFine(bookItem.getBarcode());
////        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
////        // check if this book item has a pending reservation from another member
////        if (bookReservation != null && bookReservation.getMemberId() != this.getMemberId()) {
////            System.out.println("This book is reserved by another member");
////            member.decrementTotalBooksCheckedout();
////            bookItem.updateBookItemState(BookStatus.RESERVED);
////            bookReservation.sendBookAvailableNotification();
////            return false;
////        } else if (bookReservation != null) {
////            // book item has a pending reservation from this member
////            bookReservation.updateStatus(ReservationStatus.COMPLETED);
////        }
////        BookLending.lendBook(bookItem.getBarCode(), this.getMemberId());
////        bookItem.updateDueDate(LocalDate.now().plusDays(Constants.MAX_LENDING_DAYS));
////        return true;
////    }
////}
//
//class BookReservation {
//    private Date creationDate;
//    private ReservationStatus status;
//    private String bookItemBarcode;
//    private String memberId;
//
//    public static BookReservation fetchReservationDetails(String barcode) {return null;};
//
//    public Date getCreationDate() {
//        return creationDate;
//    }
//
//    public ReservationStatus getStatus() {
//        return status;
//    }
//
//    public String getBookItemBarcode() {
//        return bookItemBarcode;
//    }
//
//    public String getMemberId() {
//        return memberId;
//    }
//}
//
//class BookLending {
//    private Date creationDate;
//    private Date dueDate;
//    private Date returnDate;
//    private String bookItemBarcode;
//    private String memberId;
//
//    public static boolean lendBook(String barcode, String memberId) {
//        return false;
//    };
//    public static BookLending fetchLendingDetails(String barcode) {return null;};
//}
//
//class Fine {
//    private Date creationDate;
//    private double bookItemBarcode;
//    private String memberId;
//
//    public static void collectFine(String memberId, long days) {}
//}
//
//abstract class Book {
//    private String ISBN;
//    private String title;
//    private String subject;
//    private String publisher;
//    private String language;
//    private int numberOfPages;
//    private List<String> authors;
//}
//
//class BookItem extends Book {
//    private String barcode;
//    private boolean isReferenceOnly;
//    private Date borrowed;
//    private Date dueDate;
//    private double price;
//    private BookFormat format;
//    private BookStatus status;
//    private Date dateOfPurchase;
//    private Date publicationDate;
//    private Rack placedAt;
//    private BookItem bookItem;
//
//    private boolean getIsReferenceOnly() {return false;};
//    private String getBarCode() {return "";};
//    private void updateBookItemStatus(BookStatus status) {};
//
//    public boolean checkout(String memberId) {
//        if(bookItem.getIsReferenceOnly()) {
//            System.out.println("This book is Reference only and can't be issued");
//            return false;
//        }
//        if(!BookLending.lendBook(this.getBarCode(), memberId)){
//            return false;
//        }
//        this.updateBookItemStatus(BookStatus.LOANED);
//        return true;
//    }
//}
//
//class Rack {
//    private int number;
//    private String locationIdentifier;
//}
//
//
//interface Search {
//    public List<Book> searchByTitle(String title);
//    public List<Book> searchByAuthor(String author);
//    public List<Book> searchBySubject(String subject);
//    public List<Book> searchByPubDate(Date publishDate);
//}
//
//class Catalog implements Search {
//    private HashMap<String, List<Book>> bookTitles;
//    private HashMap<String, List<Book>> bookAuthors;
//    private HashMap<String, List<Book>> bookSubjects;
//    private HashMap<String, List<Book>> bookPublicationDates;
//
//    @Override
//    public List<Book> searchByTitle(String query) {
//        // return all books containing the string query in their title.
//        return bookTitles.get(query);
//    }
//
//    @Override
//    public List<Book> searchByAuthor(String query) {
//        // return all books containing the string query in their author's name.
//        return bookAuthors.get(query);
//    }
//
//    @Override
//    public List<Book> searchBySubject(String subject) {
//        return null;
//    }
//
//    @Override
//    public List<Book> searchByPubDate(Date publishDate) {
//        return null;
//    }
//}
