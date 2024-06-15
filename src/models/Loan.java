package models;

public class Loan {
    private int id;
    private int bookId;
    private int memberId;
    private String loanDate;
    private String returnDate;

    public Loan(int id, int bookId, int memberId, String loanDate, String returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }


    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
