package com.geog.dao;
// import sql libraries
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//import mongodb libraries
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.codecs.BsonArrayCodec;
import org.bson.codecs.BsonValueCodecProvider;
import org.bson.codecs.DocumentCodecProvider;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.json.JsonReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.geog.model.*;

public class GeographyDaoImpl implements GeographyDao {
	private Connection conn;
	private MongoCollection<Document> headsOfState;
	private List<Country> countryList;
	private List<Region> regionList;
	private List<City> cityList;
	private List<Result> resultList;
	private List<State> stateList;
	
	public GeographyDaoImpl() {
		/*try {
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
		}*/
		// initialise the mySql connection
		initSqlConnection();
		initMongoConnection();
	}
	
	public void initSqlConnection() {
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
	
	public void initMongoConnection() {
		// establish a connection
		MongoClient mongoClient = new MongoClient();		
		MongoDatabase database = mongoClient.getDatabase("headsOfStateDB");
		headsOfState =  database.getCollection("headsOfState");
		
		for (String name : database.listCollectionNames()) {
		    System.out.println("List name: " + name);
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
				} while (rs.next());
			}
			else {
				System.out.println("Result set is null");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return countryList;
	}

	@Override
	public String addCountry(Country country) {
		PreparedStatement pstmt = null;
		try {
			int i = 0;
			String query = "insert into country values(?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++i, country.getCo_code());
			pstmt.setString(++i, country.getCo_name());
			pstmt.setString(++i, country.getCo_details());
			
			int j = pstmt.executeUpdate();  
			return j + " records added";  
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {			
				pstmt.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public String updateCountry(Country country, String srcCo_code) {
		PreparedStatement pstmt = null;
		try {
			int i = 0;
			String query = "update country set co_code = ?, co_name = ?, co_details = ? where co_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++i, country.getCo_code());
			pstmt.setString(++i, country.getCo_name());
			pstmt.setString(++i, country.getCo_details());
			pstmt.setString(++i, srcCo_code);	
			int j = pstmt.executeUpdate();  
			return j + " records updated";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {				
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public String deleteCountry(Country country) {
		PreparedStatement pstmt = null;
		try {
			int i = 0;
			String query = "delete from country where co_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++i, country.getCo_code());
			
			int j = pstmt.executeUpdate();  
			return j + " records deleted";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
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
				} while (rs.next());
			}
			else {
				System.out.println("Result set is null");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return regionList;
	}

	@Override
	public String addRegion(Region region) {
		PreparedStatement pstmt = null;
		try {
			int i = 0;
			String query = "insert into region values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++i, region.getCo_code());
			pstmt.setString(++i, region.getReg_code());
			pstmt.setString(++i, region.getReg_name());
			pstmt.setString(++i, region.getReg_details());
			
			int j = pstmt.executeUpdate();  
			return j + " records added";  
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String updateRegion(Region region) {
		PreparedStatement pstmt = null;
		try {
			int i = 0;
			String query = "update region set reg_name = ?, reg_desc = ? where reg_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++i, region.getReg_name());
			pstmt.setString(++i, region.getReg_details());
			pstmt.setString(++i, region.getReg_code());
			
			int j = pstmt.executeUpdate();  
			return j + " records updated";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public String deleteRegion(Region region) {
		PreparedStatement pstmt = null;
		try {
			int i = 0;
			String query = "delete from region where reg_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++i, region.getReg_code());
			
			int j = pstmt.executeUpdate();  
			return j + " records deleted";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
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
				} while (rs.next());
			}
			else {
				System.out.println("Result set is null");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cityList;
	}
	
	public List<Result> searchCities(City city, String condition) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		resultList = new ArrayList<Result>();
		try {
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
				} while (rs.next());
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}
	
	// targetValue is which user wants to get from the source. ex) country name from the country table
	public String getValueFromMultiTables(City cty, String targetValue, String commonValue, String source) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String output = "";
		try {
			int i = 0;
			String query = "select "+targetValue+" from "+source+" where "+commonValue+" = "
					+ "(select "+commonValue+" from city where cty_code = ?)";
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(++i, cty.getCty_code());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {					
					output = rs.getString(1);
				} while (rs.next());
			}
			else {
				System.out.println("Result set is null");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return output;
	}

	@Override
	public String addCity(City city) {
		PreparedStatement pstmt = null;
		try {
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
			return j + " records added";  
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public String updateCity(City city) {
		PreparedStatement pstmt = null;
		try {
			int i = 0;
			String query = "update city set cty_name = ?, population = ?, isCostal = ?, areaKM = ? where cty_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++i, city.getCty_name());
			pstmt.setInt(++i, city.getPopulation());
			pstmt.setString(++i, String.valueOf(city.getIsCoastal()));
			pstmt.setDouble(++i, city.getAreaKm());
			pstmt.setString(++i, city.getCty_code());
			
			int j = pstmt.executeUpdate();  
			return j + " records updated";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public String deleteCity(City city) {
		PreparedStatement pstmt = null;
		try {
			int i = 0;
			String query = "delete from city where cty_code = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++i, city.getCty_code());
			
			int j = pstmt.executeUpdate();  
			return j + " records deleted";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<State> getAllStates() {		
		stateList = new ArrayList<State>();
		// Getting the iterable object
		FindIterable<Document> iter = headsOfState.find();
				
		for (Document doc : iter) {	
			System.out.println(doc.getString("_id")+"\n"+doc.getString("headOfState"));
			// iterate the document object to add values into the state array list			
			State state = new State();
			String _id = doc.getString("_id");
			String headOfState = doc.getString("headOfState");
			state.set_id(_id);
			state.setHeadOfState(headOfState);			
			stateList.add(state);
		}
		return stateList;
	}
	public String addState(State state) {
		try {
			if (state != null) {
				Document doc = new Document()
					.append("_id", state.get_id())
					.append("headOfState", state.getHeadOfState());	            
				headsOfState.insertOne(doc);
				return "value added";
		    }
			else {
				return "state is null";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}		
	}
	public String deleteState(State state) {
		headsOfState.deleteOne(new Document("_id", state.get_id()));
		try {
			if (state != null) {
				Document doc = new Document("_id", state.get_id());          
				headsOfState.deleteOne(doc);
				return "value removed";
		    }
			else {
				return "state is null";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}
	
}
