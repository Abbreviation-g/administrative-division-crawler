package com.my.crawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.my.crawler.model.County;
import com.my.crawler.model.Prefecture;
import com.my.crawler.model.Province;
import com.mysql.jdbc.Driver;

public class ResolveFromFile {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		File file = new File(
				"C:\\work\\eclipse-rcp-oxygen-3a-win32-x86_64\\eclipse\\workspace_douban-album\\administrative.division.crawler\\target\\classes\\provinces.txt");
		List<Province> provinces = resolveFromFile(file);

		System.out.println(provinces);

		saveProvinceInDB(provinces);
	}

	private static void saveProvinceInDB(List<Province> provinces) {
		List<Prefecture> allPrefectures = new ArrayList<>();
		List<County> allCounties = new ArrayList<>();

		String dbUrl = "jdbc:mysql://127.0.0.1:3306/province_city_county?useSSL=true";
		String user = "joshvm_developer";
		String password = "josh2017";
		String insertProvinceSql = "insert ignore into `t_province` (`province_name`, `division_code`, `abbreviation`) values(?, ?, ?);";

		try {
			Class.forName(Driver.class.getCanonicalName());
			Connection connection = DriverManager.getConnection(dbUrl, user, password);
			PreparedStatement preparedStatement = connection.prepareStatement(insertProvinceSql,
					Statement.RETURN_GENERATED_KEYS);
			for (Province province : provinces) {
				preparedStatement.setString(1, province.getProvinceName());
				preparedStatement.setString(2, province.getDivisionCode());
				preparedStatement.setString(3, province.getAbbreviation());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			Iterator<Province> provinceIter = provinces.iterator();
			while (resultSet != null && resultSet.next()) {
				provinceIter.next().setId(resultSet.getLong(1));
			}

			provinces.forEach((province) -> {
				List<Prefecture> prefectures = province.getPrefectures();
				prefectures.forEach((prefecture) -> prefecture.setPid(province.getId()));
				allPrefectures.addAll(prefectures);
			});

			String insertPrefectureSql = "insert  into `t_prefecture`(`pid`,`prefecture_name`,`division_code`,`phone_code`) values (?,?,?,?);";
			preparedStatement = connection.prepareStatement(insertPrefectureSql, Statement.RETURN_GENERATED_KEYS);
			for (Prefecture prefecture : allPrefectures) {
				preparedStatement.setLong(1, prefecture.getPid());
				preparedStatement.setString(2, prefecture.getPrefectureName());
				preparedStatement.setString(3, prefecture.getDivisionCode());
				preparedStatement.setString(4, prefecture.getPhoneCode());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			resultSet = preparedStatement.getGeneratedKeys();

			Iterator<Prefecture> prefectureIter = allPrefectures.iterator();
			while (resultSet != null && resultSet.next()) {
				prefectureIter.next().setId(resultSet.getLong(1));
			}

			allPrefectures.forEach((prefecture) -> {
				List<County> counties = prefecture.getCounties();
				counties.forEach((county) -> county.setPid(prefecture.getId()));
				allCounties.addAll(counties);
			});

			String insertCountySql = "insert ignore  into `t_county`(`pid`,`county_name`,`division_code`,`phone_code`) values (?,?,?,?);";
			preparedStatement = connection.prepareStatement(insertCountySql, Statement.RETURN_GENERATED_KEYS);
			for (County county : allCounties) {
				preparedStatement.setLong(1, county.getPid());
				preparedStatement.setString(2, county.getCountyName());
				preparedStatement.setString(3, county.getDivisionCode());
				preparedStatement.setString(4, county.getPhoneCode());
				preparedStatement.addBatch();
			}
			preparedStatement.executeLargeBatch();
			resultSet = preparedStatement.getGeneratedKeys();
			Iterator<County> countyIter = allCounties.iterator();
			while (resultSet != null && resultSet.next()) {
				countyIter.next().setId(resultSet.getLong(1));
			}

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Province> resolveFromFile(File file) {
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
			Object object = inputStream.readObject();
			inputStream.close();
			@SuppressWarnings("unchecked")
			List<Province> provinces = (List<Province>) object;
			return provinces;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
