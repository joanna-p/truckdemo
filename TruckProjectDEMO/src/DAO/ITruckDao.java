package DAO;

import java.util.List;

import EXCEPTION.TTBException;
import MODEL.BookingBean;
import MODEL.TruckBean;

public interface ITruckDao {

	List<TruckBean> getAllTruckDetails() throws TTBException;

	int findtruck(int truckId) throws TTBException;

	int findtruckquan(int truckId) throws TTBException;

	int insert(BookingBean bean) throws TTBException;

}
