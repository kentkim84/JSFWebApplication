package com.geog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.geog.model.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class GeographyDaoImpl implements GeographyDao {
	private Connection conn;
	private List<Country> countryList;
	private List<Region> regionList;
	private List<City> cityList;
	
	public GeographyDaoImpl() {
		try {
			// establish a connection
			MysqlDataSource mysqlDS = new MysqlDataSource();
			String url = "jdbc:mysql://localhost:3306/geography";
			mysqlDS.setURL(url);
			mysqlDS.setUser("root");
			mysqlDS.setPassword("");			
			conn = mysqlDS.getConnection();
			System.out.println(conn);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	public List<Country> getAllCountries() {
		Statement stmt = null;
		ResultSet rs = null;
		countryList = new ArrayList<Country>();
		try {
			String query = "select * from country";
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Country country = new Country();
					String co_code = rs.getString("co_code");
					String co_name = rs.getString("co_name");
					String co_details = rs.getString("co_details");
					country.setCo_code(co_code);
					country.setCo_name(co_name);
					country.setCo_details(co_details);
					countryList.add(country);
				}
			}
			else {
				System.out.println("Result set is null");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return countryList;
	}

	@Override
	public Country getCountry(String code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Country country = new Country();
		try {
			String query = "select * from country where co_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					String co_code = rs.getString("co_code");
					String co_name = rs.getString("co_name");
					String co_details = rs.getString("co_details");
					country.setCo_code(co_code);
					country.setCo_name(co_name);
					country.setCo_details(co_details);
				}
			}
			else {
				System.out.println("Result set is null");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return country;
	}

	@Override
	public void addCountry(Country country) {
		PreparedStatement pstmt = null;
		try {
			String query = "insert into country values(?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, country.getCo_code());
			pstmt.setString(2, country.getCo_name());
			pstmt.setString(3, country.getCo_details());
			
			int i = pstmt.executeUpdate();  
			System.out.println(i+" records added");  
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void updateCountry(Country country) {
		PreparedStatement pstmt = null;
		try {
			String query = "update country set co_name = ?, co_details = ? where co_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, country.getCo_name());
			pstmt.setString(2, country.getCo_details());
			pstmt.setString(3, country.getCo_code());
			
			int i = pstmt.executeUpdate();  
			System.out.println(i+" records updated");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public void deleteCountry(Country country) {
		PreparedStatement pstmt = null;
		try {
			String query = "delete from country where co_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, country.getCo_code());
			
			int i = pstmt.executeUpdate();  
			System.out.println(i+" records deleted");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public List<Region> getAllRegions() {
		Statement stmt = null;
		ResultSet rs = null;
		regionList = new ArrayList<Region>();
		try {
			String query = "select * from region";
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Region region = new Region();
					String co_code = rs.getString("co_code");
					String reg_code = rs.getString("reg_code");
					String reg_name = rs.getString("reg_name");
					String reg_details = rs.getString("reg_desc");
					region.setCo_code(co_code);
					region.setReg_code(reg_code);
					region.setReg_name(reg_name);
					region.setReg_details(reg_details);
					regionList.add(region);
				}
			}
			else {
				System.out.println("Result set is null");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return regionList;
	}

	@Override
	public Region getRegion(String code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Region region = new Region();
		try {
			String query = "select * from region where reg_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					String co_code = rs.getString("co_code");
					String reg_code = rs.getString("reg_code");
					String reg_name = rs.getString("reg_name");
					String reg_details = rs.getString("reg_desc");
					region.setCo_code(co_code);
					region.setReg_code(reg_code);
					region.setReg_name(reg_name);
					region.setReg_details(reg_details);
				}
			}
			else {
				System.out.println("Result set is null");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return region;
	}

	@Override
	public void addRegion(Region region) {
		PreparedStatement pstmt = null;
		try {
			String query = "insert into region values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, region.getCo_code());
			pstmt.setString(2, region.getReg_code());
			pstmt.setString(3, region.getReg_name());
			pstmt.setString(4, region.getReg_details());
			
			int i = pstmt.executeUpdate();  
			System.out.println(i+" records added");  
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateRegion(Region region) {
		PreparedStatement pstmt = null;
		try {
			String query = "update region set reg_name = ?, reg_desc = ? where reg_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, region.getReg_name());
			pstmt.setString(2, region.getReg_details());
			pstmt.setString(3, region.getReg_code());
			
			int i = pstmt.executeUpdate();  
			System.out.println(i+" records updated");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public void deleteRegion(Region region) {
		PreparedStatement pstmt = null;
		try {
			String query = "delete from region where reg_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, region.getReg_code());
			
			int i = pstmt.executeUpdate();  
			System.out.println(i+" records deleted");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	@Override
	public List<City> getAllCities() {
		Statement stmt = null;
		ResultSet rs = null;
		cityList = new ArrayList<City>();
		try {
			String query = "select * from city";
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					City city = new City();
					String cty_code = rs.getString("cty_code");
					String co_code = rs.getString("co_code");
					String reg_code = rs.getString("reg_code");
					String cty_name = rs.getString("cty_name");
					int population = rs.getInt("population");
					boolean isCoastal = rs.getBoolean("isCoastal");
					double areaKM = rs.getDouble("areaKM");
					city.setCty_code(cty_code);
					city.setCo_code(co_code);
					city.setReg_code(reg_code);
					city.setCty_name(cty_name);
					city.setPopulation(population);
					city.setIsCoastal(isCoastal);
					city.setAreaKm(areaKM);
					cityList.add(city);
				}
			}
			else {
				System.out.println("Result set is null");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cityList;
	}
	
	// targetValue is which user wants to get from the source. ex) country name from the country table
	public String getValueFromMultiTables(City cty, String targetValue, String commonValue, String source) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String output = "";
		try {
			String query = "select "+targetValue+" from "+source+" where "+commonValue+" = "
					+ "(select "+commonValue+" from city where cty_code = ?)";
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, cty.getCty_code());
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {					
					output = rs.getString(1);
				}
			}
			else {
				System.out.println("Result set is null");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return output;
	}

	@Override
	public City getCity(String code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		City city = new City();
		try {
			String query = "select * from city where cty_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					String cty_code = rs.getString("cty_code");
					String co_code = rs.getString("co_code");
					String reg_code = rs.getString("reg_code");
					String cty_name = rs.getString("cty_name");
					int population = rs.getInt("population");
					boolean isCoastal = rs.getBoolean("isCoastal");
					double areaKM = rs.getDouble("areaKM");
					city.setCty_code(cty_code);
					city.setCo_code(co_code);
					city.setReg_code(reg_code);
					city.setCty_name(cty_name);
					city.setPopulation(population);
					city.setIsCoastal(isCoastal);
					city.setAreaKm(areaKM);
				}
			}
			else {
				System.out.println("Result set is null");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return city;
	}

	@Override
	public void addCity(City city) {
		PreparedStatement pstmt = null;
		try {
			String query = "insert into city values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, city.getCty_code());
			pstmt.setString(2, city.getCo_code());
			pstmt.setString(3, city.getReg_code());
			pstmt.setString(4, city.getCty_name());
			pstmt.setInt(5, city.getPopulation());
			pstmt.setBoolean(6, city.getIsCoastal());
			pstmt.setDouble(7, city.getAreaKm());
			
			int i = pstmt.executeUpdate();  
			System.out.println(i+" records added");  
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public void updateCity(City city) {
		PreparedStatement pstmt = null;
		try {
			String query = "update city set cty_name = ?, population = ?, isCostal = ?, areaKM = ? where cty_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, city.getCty_name());
			pstmt.setInt(2, city.getPopulation());
			pstmt.setBoolean(3, city.getIsCoastal());
			pstmt.setDouble(4, city.getAreaKm());
			pstmt.setString(5, city.getCty_code());
			
			int i = pstmt.executeUpdate();  
			System.out.println(i+" records updated");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void deleteCity(City city) {
		PreparedStatement pstmt = null;
		try {
			String query = "delete from city where cty_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, city.getCty_code());
			
			int i = pstmt.executeUpdate();  
			System.out.println(i+" records deleted");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
