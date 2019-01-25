package SERVICE;

import java.util.List;

import DAO.ITruckDao;
import DAO.TruckDao;
import EXCEPTION.TTBException;
import MODEL.BookingBean;
import MODEL.TruckBean;

public class TruckService implements ITruckService {
	ITruckDao truckDao=new TruckDao();
	@Override
	public List<TruckBean> getAllTruckDetails() throws TTBException {
		// TODO Auto-generated method stub
		return truckDao.getAllTruckDetails();
	}
	@Override
	public int findtruck(int truckId) throws TTBException {
		// TODO Auto-generated method stub
		return truckDao.findtruck(truckId);
	}
	@Override
	public int findtruckquan(int truckId) throws TTBException {
		// TODO Auto-generated method stub
		return truckDao.findtruckquan(truckId);
	}
	@Override
	public int insert(BookingBean bean) throws TTBException {
		// TODO Auto-generated method stub
		return truckDao.insert(bean);
	}

}
