package MAIN;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import MODEL.BookingBean;
import MODEL.TruckBean;
import SERVICE.ITruckService;
import SERVICE.TruckService;

public class UiInterface {

	public static void main(String[] args) {
		boolean validateFlag = false;
		DateTimeFormatter formatter = null;
		LocalDate dateOfTransport = null;

		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Transpot Truck Booking Online");
			System.out.println("1. Book Trucks");
			System.out.println("2. Exit");
			System.out.println("enter ur choices");
			int choice = 0;
			try {
				choice = scanner.nextInt();
				if (choice > 0 && choice < 3) {
					validateFlag = true;
					switch (choice) {
					case 1:
						boolean doflag = false;
						String custId;
						ITruckService service = new TruckService();

						do {
							scanner = new Scanner(System.in);
							System.out.println("enter ur id");
							custId = scanner.nextLine();
							String rexname = "[A-Z]{1}[0-9]{6}";
							boolean pattern = Pattern.matches(rexname, custId);
							if (pattern == true) {
								doflag = true;
							} else {
								System.err.println("onealpha6dig");
							}

						} while (!doflag);
						doflag = false;
						try {
							List<TruckBean> list = service.getAllTruckDetails();
							if (!list.isEmpty()) {
								System.out.println("TRUCKID" + "        " + "TRUCKTYPE" + "           " + "ORIGIN"
										+ "        " + "DESTINATION" + "        " + "CHARGER" + "        "
										+ "AVAILABLE NOS");

								for (TruckBean truckBean : list) {
									System.out.println(truckBean.getTruckID() + "		" + truckBean.getTruckType()
											+ "		" + truckBean.getOrigin() + "		" + truckBean.getDestination()
											+ "		" + truckBean.getCharges() + "		"
											+ truckBean.getAvailableNos());
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
						doflag = false;
						int truckId = 0;
						int truck = 0;
						do {
							scanner = new Scanner(System.in);
							System.out.println("enter truckId");
							try {
								truckId = scanner.nextInt();

								try {
									truck = service.findtruck(truckId);
								} catch (Exception e) {
									System.err.println("not truck available in this id");
								}
								if (truckId == truck) {
									doflag = true;
								} else {
									System.err.println("truck not found");
								}
							} catch (InputMismatchException e) {
								System.err.println("truckid must be integer");
							}
						} while (!doflag);
						doflag = false;
						int no = 0;
						int avano;
						do {
							scanner = new Scanner(System.in);
							System.out.println("enter no of truck");
							try {
								no = scanner.nextInt();
								try {
									avano = service.findtruckquan(truckId);
									if (no <= avano) {
										doflag = true;
									} else {
										System.err.println("enter quantity is more than we have");
									}
								} catch (Exception e) {

									System.err.println("no truck avaliable");
								}
							} catch (InputMismatchException e) {
								System.err.println("must be integer");

							}
						} while (!doflag);
						doflag = false;
						long phonenumber = 0;
						do {
							scanner = new Scanner(System.in);
							System.out.println("enter the phoneNumber");
							try {
								phonenumber = scanner.nextLong();
								String number = String.valueOf(phonenumber);
								String regnum = "[6|7|8|9]{1}[0-9]{9}";
								boolean pat = Pattern.matches(regnum, number);
								if (pat == true) {
									doflag = true;
								} else {
									System.err.println("should be 10 digit");
								}
							} catch (InputMismatchException e) {
								System.err.println("only integer");
							}
						} while (!doflag);
						doflag=false;
						String date;
						do {scanner=new Scanner(System.in);
							System.out.println("date of booking (yyyy-MM-dd)");
							date = scanner.nextLine();
							formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							try {
							dateOfTransport=LocalDate.parse(date, formatter);
							LocalDate now=LocalDate.now();
							Period period=now.until(dateOfTransport);
							int days=period.getDays();
							if(days>=1) {
								doflag=true;
							}
							else if(days<0)
							{
								System.err.println("you r trying to book for past day");
							}
							else
							{
								System.err.println("you should book 24 prior");
							}}catch (Exception e) {
						  System.err.println("format wrong");
							}
						}while(!doflag);
						BookingBean bean=new BookingBean();
						bean.setCustId(custId);
						bean.setTruckId(truckId);
						bean.setNoOfTrucks(no);
						bean.setDateOfTransport(dateOfTransport);
						bean.setCustMobile(phonenumber);
						try {
						int bookingid=service.insert(bean);
						System.err.println(bookingid);
						}
						catch (Exception e) {
							
						}
						break;
					case 2:
						System.exit(0);
						break;
					default:
						System.out.println("Invalid Selection Try Again");
						System.out.println();
						validateFlag = false;
						break;
					}

				} else {
					System.err.println("1to2");
				}

			} catch (InputMismatchException e) {
				System.err.println("interger only");
			}
		} while (!validateFlag);
	}

}
