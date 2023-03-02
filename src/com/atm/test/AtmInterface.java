package com.atm.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class AtmInterface {




	public static void main(String[] args) throws NumberFormatException, IOException, SQLException, ParseException {
		boolean login=true;
		String status=null;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("============================== WELCOME TO XYZ BANK ATM ============================================================================\n");
         do {
		System.out.println("1 ---> Sign Up");
          System.out.println("2 ---> Sign In");
          System.out.println("3 ---> Exit");
			System.out.println("--------------------------------------------------------------------------------------------------------------------\n");

          System.out.print("Enter Your Choice "+" ");
          
          int choice =Integer.parseInt(br.readLine());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");

		switch(choice) {
		
		case 1:
			System.out.print("Enter Your First Name :"+" ");
			String firstname=br.readLine();
			
			System.out.print("Enter Your Last Name :"+" ");
			String lastname=br.readLine();
			
			System.out.print("Enter Your Moble Number :"+" ");
		  long mobile=Long.parseLong(br.readLine());
			
			System.out.print("Enter Your Email Id :"+" ");
			String email=br.readLine();
               
			System.out.print("Enter Your Password  :"+" ");
			String password=br.readLine();
			
			
			
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
			Connection conn=MySqlConnection.getConnection();
			PreparedStatement ps=conn.prepareStatement("insert into signup_table(first_name, last_name, mobile, email , password)"+"values(? ,?,? ,? , ?)");
			ps.setString(1,firstname);
			ps.setString(2,lastname);
			ps.setLong(3,mobile);
			ps.setString(4,email);
			ps.setString(5,password);
			int result=ps.executeUpdate();
            if(result>0) {
            	System.out.println("...Your Records Inserted.........");
            }
            System.out.print("Do you want to continue??(Y/N)"+" ");
			 status=br.readLine();
			
			if(status.equals("n") || status.equals("N"))
			{
				login=false;
			}
            break;
            
		case 2:
			try {
				System.out.print("Enter Your Registered Email Id :"+" ");
				email=br.readLine();
				
				System.out.print("Enter Your Password :"+" ");
				password =br.readLine();
				conn=MySqlConnection.getConnection();

				 ps=conn.prepareStatement("select * from signup_table where password=?");
					ps.setString(1, password);
					ResultSet result1=ps.executeQuery();
					
					while(result1.next())
					{
						String userpassword=result1.getString("password");
						
						if(userpassword.equals(password)) {
							System.out.println("==============================Welcome================================================================");
							System.out.println();
					
							do
							{	            System.out.println("1 --->    Account");
											System.out.println("2  --->   Deposit");
											System.out.println("3  --->   Withdraw");
											System.out.println("4  --->   Fund Transfer");
											System.out.println("5  --->   Balance Check");
											System.out.println("6  --->   Change Secretpin");
											System.out.println("7  --->   Transaction History");
											System.out.println("8  --->   Exit / Logout");
											System.out.println("----------------------------------------------------------------------------------------------------------------------");
											System.out.print(" Enter your choice:"+" "); 
											int operationNumber=Integer.parseInt(br.readLine());
											
											
											switch(operationNumber)
											{
												case 1: 
													System.out.println("---------------------------------Enter Your Details---------------------------------------------------------------------------------\n");
													System.out.print("Enter Your Full Name:"+" ");
													String fullname=br.readLine();
													
													System.out.print("Enter Your parrents Name:"+" ");
													String parentsname=br.readLine();
													
													System.out.print("Enter Your Mobile Number:"+" ");
												    mobile=Long.parseLong(br.readLine());
												    
												    System.out.print("Enter Your Email Id:"+" ");
													email=br.readLine();
													
													System.out.print("Enter Your Address:"+" ");
													String address=br.readLine();
													
													System.out.print("Enter Your State:"+" ");
													String state=br.readLine();
													
													System.out.print("Enter Your Country:"+" ");
													String country=br.readLine();
													
													System.out.print("Enter Your Account Id:"+" ");
													int accountid=Integer.parseInt(br.readLine());
													
													System.out.print("Enter Your Account Type:"+" ");
													String accounttype=br.readLine();
													
													System.out.print("Enter Your First Deposite Amount:"+" ");
													double depositbalence=Double.parseDouble(br.readLine());
													
													System.out.print("Enter Your Account Pin:"+" ");
													int pin=Integer.parseInt(br.readLine());
													
													System.out.print("Enter Your IFSC Code:"+" ");
													String ifsc=br.readLine();
													
													System.out.println("--------------------------------------------------------------------------------------------------------------------");
												    conn=MySqlConnection.getConnection();
												    ps=conn.prepareStatement("insert into customber_table(full_name, parrents_name, mobile, email , address,state, country,accountId,accountType,balance,pin ,IFSC)"
												    +"values(? ,?,? ,? , ?,?,?,?,?,?,?,?)");
													ps.setString(1,fullname);
													ps.setString(2,parentsname);
													ps.setLong(3,mobile);
													ps.setString(4,email);
													ps.setString(5,address);
													ps.setString(6,state);
													ps.setString(7,country);
													ps.setInt(8,accountid);
													ps.setString(9,accounttype);
													ps.setDouble(10,depositbalence);
													ps.setInt(11,pin);
													ps.setString(12,ifsc);
													result=ps.executeUpdate();
										            if(result>0) {
										            	System.out.println("....... Your Records Inserted .......\n");
										            	System.out.println("      Your Account Open Successfully  ");
										            }
										            System.out.print("Do you want to continue??(Y/N)"+" ");
													 status=br.readLine();
													
													if(status.equals("n") || status.equals("N"))
													{
														login=false;
													}
													
													break;
													
							case 2:	
							                       System.out.print("Enter Deposite Amount"+" ");
								                    double depositAmount=Double.parseDouble(br.readLine());
								                    System.out.print("Enter Your Secret Pin"+" ");
								                   pin=Integer.parseInt(br.readLine());
														
														if(depositAmount>0)
														{
															conn=MySqlConnection.getConnection();
															ps=conn.prepareStatement("select * from customber_table where pin =?");
															ps.setInt(1, pin);
															result1=ps.executeQuery();
															
															double balance=0.0;
														     accountid=0;
															while(result1.next())
															{
																balance=result1.getDouble("balance");
																accountid=result1.getInt("accountId");
															}
															
															
															balance=balance+depositAmount;
															
															ps=conn.prepareStatement("update customber_table set balance=? where accountId=?");
															ps.setDouble(1, balance);
															ps.setInt(2, accountid);
															
															if(ps.executeUpdate()>0)
															{
																ps=conn.prepareStatement("insert into transactions values(?,?,?,?,?,?)");
																Timestamp timestamp = new Timestamp(System.currentTimeMillis());
																String transactionId="TN"+timestamp.getTime(); 
																ps.setString(1, transactionId);
																ps.setDouble(2, depositAmount);
																ps.setDate(3, new Date(System.currentTimeMillis()));
																ps.setString(4, "deposit");
																ps.setLong(5,accountid);
																ps.setLong(6,accountid);
																
																ps.executeUpdate();
										
																
																System.out.println("......Balance Updated.....\n");
																System.out.println("  New Balance : "+balance);
															}
															else
															{
																System.out.println("..... Something went wrong ......");
															}
															
														}
														
														System.out.print("Do you want to continue??(Y/N)"+" ");
														 status=br.readLine();
														
														if(status.equals("n") || status.equals("N"))
														{
															login=false;
														}
														
														break;
														
							case 3:	
								
								System.out.print("Enter Withdrawal amount:"+" ");
							  double withdrawalAmount=Double.parseDouble(br.readLine());
							  
							  System.out.print("Enter Your Secret Pin Number :"+" ");
			                   pin=Integer.parseInt(br.readLine());
							 if(withdrawalAmount>0)
							 {
							    conn=MySqlConnection.getConnection();
								ps=conn.prepareStatement("select * from customber_table where pin=?");
								ps.setInt(1, pin);
								result1=ps.executeQuery();
								accountid=0;
								double balance=0.0;
								while(result1.next())
								{
									balance=result1.getDouble("balance");
									accountid=result1.getInt("accountId");
								}
								
								
								if(balance>withdrawalAmount)
								{
									balance=balance-withdrawalAmount;
									ps=conn.prepareStatement("update customber_table set balance=? where accountId=?");
									ps.setDouble(1, balance);
									ps.setInt(2,accountid);
									
									if(ps.executeUpdate()>0)
									{
										ps=conn.prepareStatement("insert into transactions values(?,?,?,?,?,?)");
										Timestamp timestamp = new Timestamp(System.currentTimeMillis());
										String transactionId="TN"+timestamp.getTime(); //TN3243432432423
										ps.setString(1, transactionId);
										ps.setDouble(2, withdrawalAmount);
										ps.setDate(3, new Date(System.currentTimeMillis()));
										ps.setString(4, "withdraw");
										ps.setInt(5,accountid);
										ps.setInt(6,accountid);
										
										
										ps.executeUpdate();
										
										System.out.println("......Balance Updated ........\n");
										System.out.println("  New Balance : "+balance);
									}
									else
									{
										System.out.println(".... Something went wrong .....");
									}
								}
								else
								{
									System.out.println("Insufficient Balance .....");
								}

							 }
							 System.out.print("Do you want to continue??(Y/N)"+" ");
							 status=br.readLine();
								
								if(status.equals("n") || status.equals("N"))
								{
									login=false;
								}
							 	
							 	break;
						
							case 4: 
								 System.out.print("Enter Your Account Id"+" ");
								 int senderId=Integer.parseInt(br.readLine());
								
								System.out.print("Please enter the receiver account Id:"+" ");
					    	int rcveId=Integer.parseInt(br.readLine());
					    		
					    		System.out.print("Enter the amount:"+" ");
					    		double amount=Double.parseDouble(br.readLine());
					    		
					    		System.out.print("Enter Your Secret Pin Number :"+" ");
					    		pin=Integer.parseInt(br.readLine());
					    		
					    		conn=MySqlConnection.getConnection();
					    		
					    		long receiverId=0;
					    		
								ps=conn.prepareStatement("select * from customber_table where accountId=?" );
						
								ps.setInt(1, rcveId);
								result1=ps.executeQuery();
								
								while(result1.next())
								{
									receiverId=result1.getInt("accountId");
								}
								
								double Balance=0.0;
								senderId=0;
								ps=conn.prepareStatement("select balance,accountId from customber_table where pin=?");
							    
								ps.setInt(1, pin);
								result1=ps.executeQuery();
								
								while(result1.next())
								{
									Balance=result1.getDouble("balance");
									senderId=result1.getInt("accountId");
								}
								
								if(receiverId==0)
								{
									System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");				
									System.out.println("Wrong receiver id ......");
									System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");				

								}
								else if(Balance==0 || Balance<amount)
								{
									System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");				
									System.out.println("Insufficient account balance ........");
									System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");				

								}
								else
								{
								   Balance=Balance-amount;
									ps=conn.prepareStatement("update customber_table set balance=? where accountId=?");
									ps.setDouble(1, Balance);
									ps.setInt(2, senderId);
									
									if(ps.executeUpdate()>0)
									{
										ps=conn.prepareStatement("select Balance from customber_table where accountId=?");
										ps.setLong(1, rcveId);
										double rcvBalance=0.0;
										result1=ps.executeQuery();
										while(result1.next())
										{
											rcvBalance=result1.getDouble("balance");
										}
										
										rcvBalance=rcvBalance + amount;
										
										ps=conn.prepareStatement("update customber_table set balance=? where accountId=?");
										ps.setDouble(1, rcvBalance);
										ps.setLong(2, receiverId);
										
										if(ps.executeUpdate()>0)
										{
											 ps=conn.prepareStatement("insert into transactions values(?,?,?,?,?,?)");
				                                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				                                String transactionId="TN"+timestamp.getTime(); //TN3243432432423
				                                ps.setString(1, transactionId);
				                                ps.setDouble(2, amount);
				                                ps.setDate(3, new Date(System.currentTimeMillis()));
				                                ps.setString(4, "fund transfer");
				                                ps.setLong(5,senderId);
				                                ps.setLong(6,rcveId);
				                                
				                                ps.executeUpdate();
											System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");				
											System.out.println(".....Transaction Completed ......");
											System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");				

										}
										else
										{
											System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");				
											System.out.println("Transaction Failed ........");
											System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");				

										}
										
									}
									
									
								}
								System.out.print("Do you want to continue??(Y/N)"+" ");
								 status=br.readLine();
								
								if(status.equals("n") || status.equals("N"))
								{
									login=false;
								}
								
								break;
								
							case 5:	
								 System.out.print("Enter Your Account Id"+" ");
				                   accountid=Integer.parseInt(br.readLine());
				                   
				                   System.out.print("Enter Your Secret Pin :"+" ");
				                   pin=Integer.parseInt(br.readLine());
								
								conn=MySqlConnection.getConnection();
							ps=conn.prepareStatement("select balance from customber_table where accountId =?");
							//ps.setInt(1,pin);
						   ps.setInt(1,accountid);
							result1=ps.executeQuery();
							
							double balance=0.0;
							while(result1.next())
							{
								balance=result1.getDouble("balance");
							}
							System.out.println("---------------------------------------------------------------------------------------------------------------------");				
							System.out.println("Current Available Balance:"+balance);
							System.out.println("---------------------------------------------------------------------------------------------------------------");				

							 System.out.print("Do you want to continue??(Y/N)"+" ");
							 status=br.readLine();
								
								if(status.equals("n") || status.equals("N"))
								{
									login=false;
								}
							 	
							 	break;

							case 6:
								 System.out.print("Enter Your Account Id"+" ");
				                   accountid=Integer.parseInt(br.readLine());
								
								System.out.print("Please enter the existing secret pin: "+" ");
									
							String existingsecretpin =br.readLine();
									
									System.out.println("Set new secret pin:");
							String newsecretpin1=br.readLine();
									
									System.out.println("Retype new secret pin:");
							String retypenewsecretpin=br.readLine();
									
									
									ps=conn.prepareStatement("select pin from customber_table where accountId=?");
									ps.setInt(1, accountid);
									
									result1=ps.executeQuery();
								
									while(result1.next())
									{
										String newsecretpin=result1.getString("pin");
									
									
									if(existingsecretpin.equals(newsecretpin))
									{
										if(newsecretpin1.equals(retypenewsecretpin))
										{
											ps=conn.prepareStatement("update customber_table set pin=? where accountId=?");
											ps.setString(1, newsecretpin1);
											ps.setInt(2, accountid);
											
											if(ps.executeUpdate()>0)
											{
												System.out.println("---------------------------------------------------------------------------------------");				
												System.out.println("....... Secretpin Changed ........");
												System.out.println("--------------------------------------------------------------------------------------------------------------------");				
												
											}
											
											else
											{
												System.out.println("----------------------------------------------------------------------------------------");				
												System.out.println("Error in Secretpin change .......");
												System.out.println("---------------------------------------------------------------------------------------------------------");				
												
											}
										}
										else
										{
											System.out.println("----------------------------------------------------------------------------------------------------------------");				
											System.out.println("Set new Secretpin and retype Secretpin must be same ....");
											System.out.println("-------------------------------------------------------------------------------------------------------------------");				
											
										}
									}
									else
									{
										System.out.println("---------------------------------------------------------------------------------------------------------");				
										System.out.println("Please enter correct existing Secretpion ........");
										System.out.println("----------------------------------------------------------------------------------------------------------------------");				
										
							
									}
									
									System.out.print("Do you want to continue??(Y/N)"+" ");
									status=br.readLine();
									System.out.println("--------------------------------------------------------------------------------------------------------------------");				

									if(status.equals("n") || status.equals("N"))
									{
										login=false;
									}}
									break;
									
							case 7: 
								
								 System.out.print("Enter Your Account Id"+" ");
				                   accountid=Integer.parseInt(br.readLine());
				                   
								ps=conn.prepareStatement("select * from customber_table where accountId=?");
							ps.setInt(1, accountid);
							
							result1=ps.executeQuery();
							long cusId=0;
							while(result1.next())
							{
								cusId=result1.getLong("accountId");
							}
							if(cusId!=0)
							{
								ps=conn.prepareStatement("select * from transactions");
								
								
								result1=ps.executeQuery();
								System.out.println("-------------------------------------------------------------------------------------------------");	
								System.out.println("TransactionId \t Amount \t Date \t Type ");
								System.out.println("----------------------------------------------------------------------------------------------------------------");	
								while(result1.next())
								{
									System.out.println(result1.getString("transactionId")+"\t"+result1.getDouble("depositAmount")+"\t"+result1.getDate("Date")+"\t"+result1
											.getString("deposit")+"\t"+result1.getInt("senderId")+"\t"+result1.getInt("receiverId"));
								}
								System.out.println("---------------------------------------------------------------------------------------------------------------");	
							}
							System.out.print("Do you want to continue??(Y/N)"+" ");
							status=br.readLine();
							System.out.println("------------------------------------------------------------------------------------------------------------------------------");				

							if(status.equals("n") || status.equals("N"))
							{
								login=false;
							}
							break;
							
							
							case 8:  login=false;
							 break;
					
						
							
					default:
						System.out.println("....Wrong Input!!..........");

								
						}

					}
							while(login);
							System.out.println("--------------------------------------------------------------------------------------------------------");				
							System.out.println("  Bye...Bye..");
							System.out.println("  Have a nice day .....");
							System.out.println("-----------------------------------------------------------------------------------------------------------------");				

						}
						else
						{
							System.out.println("-------------------------------------------------------------------------------------------------------------------------------");				
							System.out.println("  Wrong username/secretpin .........");
							System.out.println("--------------------------------------------------------------------------------------------------------------");				

						}
					}
				
				
			} catch(Exception e) {
				System.out.println(e);
			}
			
		case 3:  
			login=false;
		 break;
		 
		default:
			System.out.println("....Wrong Input!!..........");

		
		}
		
			
         }
         while(login);
			System.out.println("--------------------------------------------------------------------------------------------------------");				
			System.out.println("  Bye...Bye..");
			System.out.println("  Have a nice day .....");
			System.out.println("-----------------------------------------------------------------------------------------------------------------");				

		
							
							
						
						}
						
		}
	

