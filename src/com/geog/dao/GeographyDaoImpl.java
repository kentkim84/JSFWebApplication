package com.geog.dao;
// sql libraries
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
// utilities and model package 
import java.util.ArrayList;
import java.util.List;
// model package
import com.geog.model.*;

public class GeographyDaoImpl implements GeographyDao {	
	private Connection conn;	
	private List<Country> countryList;
	private List<Region> regionList;
	private List<City> cityList;
	private List<Result> resultList;
	
	public GeographyDaoImpl() throws Exception {	
		initSqlConnection();
	}
	
	public void initSqlConnection() throws Exception {
		// establish the connection
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/geography");
		conn = ds.getConnection();
		// connection error will throw the exception
		if (conn != null) {
			System.out.println("Open the Sql Connection");
		}
	}
	
	public void closeSqlConnection() throws Exception {
		// close the connection
		conn.close();
		if (conn != null) {
			System.out.println("Close the Sql Connection");
		}		
	}
	
	// Get List:
	// 1. create the statement
	// 2. execute the query
	// 3. get data from the table (Country, Region, City)
	// 4. iterate the result set and put each result into the array list
	// 5. close the result set, statement and connection
	@Override
	public List<Country> getAllCountries() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		countryList = new ArrayList<Country>();
		String query = "select * from country";
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = stmt.executeQuery(query);
		if (rs.next()) {
			do {
				Country country = new Country();
				String co_code = rs.getString("co_code");
				String co_name = rs.getString("co_name");
				String co_details = rs.getString("co_details");
				country.setCo_code(co_code);
				country.setCo_name(co_name);
				country.setCo_details(co_details);
				countryList.add(country);
				System.out.println(co_code+"\n"+co_name+"\n"+co_details);
			} while (rs.next());
		}
		rs.close();
		stmt.close();
		closeSqlConnection();
		return countryList;
	}
	
	// Add values:
	// 1. prepare the statement with the parameter
	// 2. execute/update the query
	// 3. close the prepared statement and connection
	@Override
	public void addCountry(Country country) throws Exception {
		PreparedStatement pstmt = null;
		int i = 0;
		String query = "insert into country values(?, ?, ?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(++i, country.getCo_code());
		pstmt.setString(++i, country.getCo_name());
		pstmt.setString(++i, country.getCo_details());			
		int j = pstmt.executeUpdate();
		System.out.println(j + " Country records added");
		pstmt.close();
		closeSqlConnection();
	}

	// Update values:
	// 1. prepare the statement with the parameters, 
	// srcCo_code (source country code) indicates where values updated
	// 2. execute/update the query
	// 3. close the prepared statement and connection
	@Override
	public void updateCountry(Country country, String srcCo_code) throws Exception {
		PreparedStatement pstmt = null;
		int i = 0;
		String query = "update country set co_code = ?, co_name = ?, co_details = ? where co_code = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(++i, country.getCo_code());
		pstmt.setString(++i, country.getCo_name());
		pstmt.setString(++i, country.getCo_details());
		pstmt.setString(++i, srcCo_code);
		int j = pstmt.executeUpdate();  
		System.out.println(j + " Country records updated");
		pstmt.close();
		closeSqlConnection();
	}

	// Delete values:
	// 1. prepare the statement with the parameter
	// 2. execute/update the query
	// 3. close the prepared statement and connection
	@Override
	public void deleteCountry(Country country) throws Exception {
		PreparedStatement pstmt = null;		
		int i = 0;
		String query = "delete from country where co_code = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(++i, country.getCo_code());			
		int j = pstmt.executeUpdate();  
		System.out.println(j + " Country records deleted");
		pstmt.close();
		closeSqlConnection();
	}

	@Override
	public List<Region> getAllRegions() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		regionList = new ArrayList<Region>();
		String query = "select * from region";
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = stmt.executeQuery(query);
		if (rs.next()) {
			do {
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
				System.out.println(co_code+"\n"+reg_code+"\n"+reg_name+"\n"+reg_details);
			} while (rs.next());
		}
		rs.close();
		stmt.close();
		closeSqlConnection();
		return regionList;
	}

	@Override
	public void addRegion(Region region) throws Exception {
		PreparedStatement pstmt = null;
		int i = 0;
		String query = "insert into region values(?, ?, ?, ?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(++i, region.getCo_code());
		pstmt.setString(++i, region.getReg_code());
		pstmt.setString(++i, region.getReg_name());
		pstmt.setString(++i, region.getReg_details());			
		int j = pstmt.executeUpdate();  
		System.out.println(j + " Region records added");
		pstmt.close();
		closeSqlConnection();
	}

	@Override
	public void updateRegion(Region region, String srcReg_code) throws Exception {
		PreparedStatement pstmt = null;
		int i = 0;
		String query = "update region set reg_code = ?, reg_name = ?, reg_desc = ? where reg_code = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(++i, region.getReg_code());
		pstmt.setString(++i, region.getReg_name());
		pstmt.setString(++i, region.getReg_details());
		pstmt.setString(++i, srcReg_code);
		int j = pstmt.executeUpdate();  
		System.out.println(j + " Region records updated");
		pstmt.close();
		closeSqlConnection();
	}

	@Override
	public void deleteRegion(Region region) throws Exception {
		PreparedStatement pstmt = null;
		int i = 0;
		String query = "delete from region where reg_code = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(++i, region.getReg_code());			
		int j = pstmt.executeUpdate();  
		System.out.println(j + " Region records deleted");
		pstmt.close();
		closeSqlConnection();
	}
	
	@Override
	public List<City> getAllCities() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		cityList = new ArrayList<City>();
		String query = "select * from city";
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = stmt.executeQuery(query);
		if (rs.next()) {
			do {
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
				System.out.println(cty_code+"\n"+co_code+"\n"+reg_code+"\n"+cty_name+"\n"+population+"\n"+isCoastal+"\n"+areaKM);
			} while (rs.next());
		}
		rs.close();
		stmt.close();
		closeSqlConnection();
		return cityList;
	}
	
	// Search values:
	// 1. prepare the statement with the parameters, 
	// 'condition'is the symbol that used to provide the comparison 
	// between the population and the value which user specifically looking for
	// 2. execute/update the query
	// 3. close the prepared statement and connection
	public List<Result> searchCities(City city, String condition) throws Exception {		
		PreparedStatement pstmt = null;		
		ResultSet rs = null;		
		resultList = new ArrayList<Result>();		
		String option = "";		
		int i = 0;	
		if (!city.getCo_code().isEmpty()) {
			option = " and city.co_code = ?";
		}
		String query = "select city.cty_code, city.cty_name, country.co_name, region.reg_name,"
				+ " city.population, city.iscoastal, city.areakm"
				+ " from city"
				+ " inner join country on city.co_code = country.co_code"
				+ " inner join region on city.reg_code = region.reg_code"
				+ " where city.population " + condition + " ?"
				+ option
				+ " and city.iscoastal = ?";
		pstmt = conn.prepareStatement(query);		
		pstmt.setInt(++i, city.getPopulation());
		if (!city.getCo_code().isEmpty()) {
			pstmt.setString(++i, city.getCo_code());				
		}
		pstmt.setString(++i, String.valueOf(city.getIsCoastal()));			
		rs = pstmt.executeQuery();		
		if(rs.next()) {
			do {
				Result result = new Result();
				String cty_code = rs.getString("cty_code");
				String cty_name = rs.getString("cty_name");
				String co_name = rs.getString("co_name");
				String reg_name = rs.getString("reg_name");				
				int population = rs.getInt("population");
				boolean isCoastal = rs.getBoolean("isCoastal");				
				double areaKM = rs.getDouble("areaKM");
				result.setCty_code(cty_code);
				result.setCty_name(cty_name);
				result.setCo_name(co_name);
				result.setReg_name(reg_name);					
				result.setPopulation(population);
				result.setIsCoastal(isCoastal);
				result.setAreaKm(areaKM);
				resultList.add(result);
				System.out.println(cty_code+"\n"+cty_name+"\n"+co_name+"\n"+reg_name+"\n"+population+"\n"+isCoastal+"\n"+areaKM);
			} while (rs.next());
		}
		rs.close();
		pstmt.close();
		closeSqlConnection();
		return resultList;
	}
	
	// 1. specialised method that returns essentially, the 'target value' 
	// which used to get either the name of country or region
	// 2. 'source' indicates the table where the 'target value' is located
	// 3. 'common value' is used for joining purpose in sql
	public String getValueFromMultiTables(City cty, String targetValue, String commonValue, String source) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String output = "";
		int i = 0;
		String query = "select "+targetValue+" from "+source+" where "+commonValue+" = "
				+ "(select "+commonValue+" from city where cty_code = ?)";
		pstmt = conn.prepareStatement(query);			
		pstmt.setString(++i, cty.getCty_code());
		rs = pstmt.executeQuery();
		if (rs.next()) {
			do {					
				output = rs.getString(1);
				System.out.println(output+"\n");
			} while (rs.next());
		}
		closeSqlConnection();
		return output;
	}

	@Override
	public void addCity(City city) throws Exception {
		PreparedStatement pstmt = null;
		int i = 0;
		String query = "insert into city values(?, ?, ?, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(++i, city.getCty_code());
		pstmt.setString(++i, city.getCo_code());
		pstmt.setString(++i, city.getReg_code());
		pstmt.setString(++i, city.getCty_name());
		pstmt.setInt(++i, city.getPopulation());
		pstmt.setString(++i, String.valueOf(city.getIsCoastal()));
		pstmt.setDouble(++i, city.getAreaKm());			
		int j = pstmt.executeUpdate();  
		System.out.println(j + " City records added");
		pstmt.close();
		closeSqlConnection();
	}

	@Override
	public void updateCity(City city, String srcCty_code) throws Exception {
		PreparedStatement pstmt = null;
		int i = 0;
		String query = "update city set cty_code = ?, cty_name = ?, population = ?, isCostal = ?, areaKM = ? where cty_code = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(++i, city.getCty_code());
		pstmt.setString(++i, city.getCty_name());
		pstmt.setInt(++i, city.getPopulation());
		pstmt.setString(++i, String.valueOf(city.getIsCoastal()));
		pstmt.setDouble(++i, city.getAreaKm());
		pstmt.setString(++i, srcCty_code);
		int j = pstmt.executeUpdate();  
		System.out.println(j + " City records updated");
		pstmt.close();
		closeSqlConnection();
	}

	@Override
	public void deleteCity(City city) throws Exception {
		PreparedStatement pstmt = null;
		int i = 0;
		String query = "delete from city where cty_code = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(++i, city.getCty_code());			
		int j = pstmt.executeUpdate();  
		System.out.println(j + " City records deleted");
		pstmt.close();
		closeSqlConnection();
	}	
}
