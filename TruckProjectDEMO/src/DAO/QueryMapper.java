package DAO;

public interface QueryMapper {
	public static final String getTruck="select * from truckdetails";
	public static final String gettruckid="select truckid from truckdetails where truckid=?";
	public static final String gettruckquan="select AVAILABLENOS from truckdetails where truckid=?";
	public static final String insertQuery = "insert into BookingDetails values(booking_id_seq.nextval,?,?,?,?,?)";
	public static final String updateQuery="update truckdetails set AVAILABLENOS=AVAILABLENOS-? where truckid=?";
	public static final String getbookingId="select booking_id_seq.currval from dual ";
}
