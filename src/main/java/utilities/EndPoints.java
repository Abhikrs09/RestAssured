package utilities;

public class EndPoints {
	private static final String auth = "/auth";
	private static final String getById = "/booking/";
	private static final String createBooking = "/booking";
	private static final String update_booking = "/booking/";
	private static final String partial_update = "/booking/";
	private static final String delete = "/booking/";

	// Getter methods to access the private endpoints
	public static String getAuth() {
		return auth;
	}

	public static String getGetById() {
		return getById;
	}

	public static String getCreateBooking() {
		return createBooking;
	}

	public static String getUpdateBooking() {
		return update_booking;
	}

	public static String getPartialUpdate() {
		return partial_update;
	}

	public static String getDelete() {
		return delete;
	}
}
