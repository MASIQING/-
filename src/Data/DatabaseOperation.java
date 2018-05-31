package Data;

import java.sql.*;

/**
 * 在java语言上的数据库操作
 * 
 * @author 李晶晶
 *
 */
public class DatabaseOperation {
	private static final String JDBC_DRIVER = "org.postgresql.Driver";// The driver is
																// for mysql
	private static final String DB_URL = "jdbc:postgresql://10.20.23.25/cs307?useUnicode"
			                    + "=true&characterEncoding=utf-8&useSSL=false";//My data base sql
																															
	private static final String USER = "u11612023";// my username
	private static final String PASS = "11612023";// my password

	/**
	 * 打印film表
	 * <p>
	 * 获取数据库的元素并打印出来
	 * </p>
	 * 
	 * @exception SQLException
	 */
	public void printTable() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// 启动链接数据库
			stmt = conn.createStatement();// 实例化Statement
			String sql = "select * from films";// 打印films表
			ResultSet rs = stmt.executeQuery(sql);// 执行sql语句
			while (rs.next()) {
				int id = rs.getInt("movieid");
				String name = rs.getString("movie_name");
				String purl = rs.getString("photo_url");
				String murl = rs.getString("movie_url");
				String nation = rs.getString("country");
				int yearReleased = rs.getInt("year_released");
				int runtime = rs.getInt("runtime");
				// 打印表格元素
				System.out.print("ID: " + id);
				System.out.print(", Title: " + name);
				System.out.print(", PhotoURL: " + purl);
				System.out.print(", MovieURL: " + murl);
				System.out.print(", Country: " + nation);
				System.out.print(", YearReleased: " + yearReleased);
				System.out.print(", RunTime: " + runtime);
				System.out.println();
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se1) {

			}
			try {
				if (conn != null) {
					stmt.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	/**
	 * 查询操作
	 * <p>
	 * 用来执行具体的sql查询语句
	 * </p>
	 * 
	 * @param query
	 *            查询语句
	 * @exception SQLException
	 */
	public void query(String query) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// 启动链接数据库
			stmt = conn.createStatement();// 实例化Statement
			String sql = query;
			boolean rs = stmt.execute(sql);// 执行sql语句
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se1) {

			}
			try {
				if (conn != null) {
					stmt.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	/**
	 * 打印不同类型的电影表
	 * <p>
	 * query为不同类型的查询电影语句，最后将其打印出来
	 * </p>
	 * 
	 * @param query
	 *            查询语句
	 * @exception SQLException
	 */
	public void showtype(String query) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// 启动链接数据库
			stmt = conn.createStatement();// 实例化Statement
			String sql = query;
			boolean rrs = stmt.execute(sql);// 执行sql语句
			ResultSet rs = stmt.executeQuery(sql);// 执行sql语句
			while (rs.next()) {
				int id = rs.getInt("movieid");
				String name = rs.getString("movie_name");
				String nation = rs.getString("country");
				int yearReleased = rs.getInt("year_released");
				int runtime = rs.getInt("runtime");
				// 打印表格元素
				System.out.print("ID: " + id);
				System.out.print(", Title: " + name);
				System.out.print(", Nation: " + nation);
				System.out.print(", YearReleased: " + yearReleased);
				System.out.print(", RunTime: " + runtime);
				System.out.println();
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se1) {

			}
			try {
				if (conn != null) {
					stmt.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	/**
	 * 获取行数
	 * <p>
	 * 使用conunt(*)来获取film表的行数
	 * </p>
	 * 
	 * @return RowNum film表的行数
	 * @exception SQLException
	 */
	public int getRows() {
		Connection conn = null;
		Statement stmt = null;
		int RowNum = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// 启动链接数据库
			stmt = conn.createStatement();// 实例化Statement
			String sql = "select * from films";// 获取行数
			ResultSet rs = stmt.executeQuery(sql);// 执行sql语句
			while (rs.next()) {
				RowNum++; 
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se1) {

			}
			try {
				if (conn != null) {
					stmt.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return RowNum;
	}

	/**
	 * 获取列数
	 * <p>
	 * 在metedata中获取film表的列数
	 * </p>
	 * 
	 * @return ColNum film表的列数
	 * @exception SQLException
	 */
	public int getCols() {
		Connection conn = null;
		Statement stmt = null;
		int ColNum = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// 启动链接数据库
			stmt = conn.createStatement();// 实例化Statement
			String sql = "select * from films";// 获取列数
			ResultSet rs = stmt.executeQuery(sql);// 执行sql语句
			ResultSetMetaData rsmd = rs.getMetaData();
			ColNum = rsmd.getColumnCount();// 获取列数
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se1) {

			}
			try {
				if (conn != null) {
					stmt.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return ColNum;
	}
	/**
	 * 获取不同类型电影表的行数
	 * <p>
	 * 使用conunt(*)来获取表的行数
	 * </p>
	 * 
	 * @return RowNum film表的行数
	 * @exception SQLException
	 */
	public int getTypeRows(String type) {
		Connection conn = null;
		Statement stmt = null;
		int RowNum = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// 启动链接数据库
			stmt = conn.createStatement();// 实例化Statement
			String sql = "select * from films join type_film tf on movieid = filmid join types t on t.typeid = tf.typeid where t.type = '"
					+ type + "'";// 获取行数
			ResultSet rs = stmt.executeQuery(sql);// 执行sql语句
			while (rs.next()) {
				RowNum++;
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se1) {

			}
			try {
				if (conn != null) {
					stmt.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return RowNum;
	}

	/**
	 * 将film表的数据转成二维表，方便处理
	 * <p>
	 * 获取数据并写到二维表MovieInfo中
	 * </p>
	 * 
	 * @return MovieInfo 电影数据的二维表
	 * @exception SQLException
	 */
	public String[][] dataToArray() {
		Connection conn = null;
		Statement stmt = null;
		String[][] MovieInfo = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// 启动链接数据库
			stmt = conn.createStatement();// 实例化Statement
			String sql = "select * from films";// 打印films表
			ResultSet rs = stmt.executeQuery(sql);// 执行sql语句
			int rows = getRows();
			int cols = getCols();
			MovieInfo = new String[rows][cols];// 初始化MovieInfo
			int i = 0;
			while (rs.next()) {
				int j = 0;
				int id = rs.getInt("movieid");
				MovieInfo[i][j] = String.valueOf(id);
				j++;
				String name = rs.getString("movie_name");
				MovieInfo[i][j] = name;
				j++;
				String purl = rs.getString("photo_url");
				MovieInfo[i][j] = purl;
				j++;
				String murl = rs.getString("movie_url");
				MovieInfo[i][j] = murl;
				j++;
				String nation = rs.getString("country");
				MovieInfo[i][j] = nation;
				j++;
				int yearReleased = rs.getInt("year_released");
				MovieInfo[i][j] = String.valueOf(yearReleased);
				j++;
				int runtime = rs.getInt("runtime");
				MovieInfo[i][j] = String.valueOf(runtime);
				j++;
				i++;
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se1) {

			}
			try {
				if (conn != null) {
					stmt.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return MovieInfo;

	}

	/**
	 * 将表的数据转成二维表，方便处理
	 * <p>
	 * 获取数据并写到二维表MovieInfo中
	 * </p>
	 * 
	 * @return MovieInfo 电影数据的二维表
	 * @exception SQLException
	 */
	public String[][] TypedataToArray(String type) {
		Connection conn = null;
		Statement stmt = null;
		String[][] MovieInfo = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);// 启动链接数据库
			stmt = conn.createStatement();// 实例化Statement
			String sql = "select movieid, movie_name, photo_url, movie_url, country, year_released, runtime from films join type_film tf on movieid = filmid join types t on t.typeid = tf.typeid where t.type = '"
					+ type + "'";// 打印films表
			ResultSet rs = stmt.executeQuery(sql);// 执行sql语句
			int rows = getTypeRows(type);
			int cols = getCols();
			MovieInfo = new String[rows][cols];// 初始化MovieInfo
			int i = 0;
			while (rs.next()) {
				int j = 0;
				int id = rs.getInt("movieid");
				MovieInfo[i][j] = String.valueOf(id);
				j++;
				String name = rs.getString("movie_name");
				MovieInfo[i][j] = name;
				j++;
				String purl = rs.getString("photo_url");
				MovieInfo[i][j] = purl;
				j++;
				String murl = rs.getString("movie_url");
				MovieInfo[i][j] = murl;
				j++;
				String nation = rs.getString("country");
				MovieInfo[i][j] = nation;
				j++;
				int yearReleased = rs.getInt("year_released");
				MovieInfo[i][j] = String.valueOf(yearReleased);
				j++;
				int runtime = rs.getInt("runtime");
				MovieInfo[i][j] = String.valueOf(runtime);
				j++;
				i++;
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			System.out.println("dbop cant find the type");
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("dbop cant find the type");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se1) {

			}
			try {
				if (conn != null) {
					stmt.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return MovieInfo;
	}
}
