package payloads;

public class pojo_class_auth_data {

	private String token;
	private int bookingid;
	private pojo_booking_create_data booking;
	private String lastname;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public pojo_booking_create_data getBooking() {
        return booking;
    }

    public void setBooking(pojo_booking_create_data booking) {
        this.booking = booking;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
	
}
