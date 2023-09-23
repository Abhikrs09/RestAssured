package payloads;

public class pojo_booking_create_data {

	private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private pojo_booking_dates_data bookingdates;
    private String additionalneeds;

    // Getters and Setters

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int string) {
        this.totalprice = string;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public pojo_booking_dates_data getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(pojo_booking_dates_data bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
}


