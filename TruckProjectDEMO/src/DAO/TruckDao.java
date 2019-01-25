package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import EXCEPTION.TTBException;
import MODEL.BookingBean;
import MODEL.TruckBean;
import UTILITY.JdbcUtility;

public class TruckDao implements ITruckDao {
	PreparedStatement statement = null;
	Connection connection = null;
	boolean checkFlag=false;
	@Override
	public List<TruckBean> getAllTruckDetails() throws TTBException {
		List<TruckBean> list = new ArrayList<>();
		ResultSet resultSet = null;
		connection = JdbcUtility.getConnection();
		try {
			statement =connection.prepareStatement(QueryMapper.getTruck);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				int truckId=resultSet.getInt(1);
				String truckType=resultSet.getString(2);
				String origin=resultSet.getString(3);
				String destination=resultSet.getString(4);
				float charges = resultSet.getFloat(5);
				int availableNos = resultSet.getInt(6);
				TruckBean bean=new TruckBean();
				bean.setTruckID(truckId);
				bean.setTruckType(truckType);
				bean.setOrigin(origin);
				bean.setDestination(destination);
				bean.setCharges(charges);
				bean.setAvailableNos(availableNos);
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public int findtruck(int truckId) throws TTBException {
		connection = JdbcUtility.getConnection();
		ResultSet resultSet = null;
		int truckid=0;
		try {
			statement = connection.prepareStatement(QueryMapper.gettruckid);
			statement.setInt(1, truckId);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				truckid=resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return truckid;
	}
	@Override
	public int findtruckquan(int truckId) throws TTBException {
		connection = JdbcUtility.getConnection();
		ResultSet resultSet = null;
		int availableno=0;
		try {
			statement = connection.prepareStatement(QueryMapper.gettruckid);
			statement.setInt(1, truckId);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				availableno=resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return availableno;
	}
	@Override
	public int insert(BookingBean bean) throws TTBException {
		connection = JdbcUtility.getConnection();
		int bookingId=0;
		ResultSet resultSet = null;
		Date dateofTrans=Date.valueOf(bean.getDateOfTransport());
		try {
			statement=connection.prepareStatement(QueryMapper.insertQuery);
			statement.setString(1, bean.getCustId());
			statement.setLong(2, bean.getCustMobile());
			statement.setInt(3, bean.getTruckId());
			statement.setInt(4, bean.getNoOfTrucks());
			statement.setDate(5, dateofTrans);
			statement.executeUpdate();
			statement=connection.prepareStatement(QueryMapper.updateQuery);
			statement.setInt(1, bean.getNoOfTrucks());
			statement.setInt(2, bean.getTruckId());
			statement.executeUpdate();
			statement=connection.prepareStatement(QueryMapper.getbookingId);
			resultSet=statement.executeQuery();
			
			while(resultSet.next())
			{
				 bookingId=resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return bookingId;
	}

}
