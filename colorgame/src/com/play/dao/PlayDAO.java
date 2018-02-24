package com.play.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.play.entity.Comments;
import com.play.entity.Players;
import com.play.helper.DBConnector;

public class PlayDAO {
	static String sql = null;
	static Connection connection = null;
	static PreparedStatement statement = null;
	static ResultSet resultSet = null;
	public static List<Players> getPlayerList(){
		List<Players> playerList = new ArrayList<Players>();
		sql = "select username,max(score) as score,date from players where username in (select distinct username from players) group by username order by max(score) desc Limit 20";
		//sql = "select * from players order by score desc LIMIT 20";
		try {
			connection = DBConnector.createConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			Players players = null;
			while(resultSet.next()){
				players = new Players();
				players.setUsername(resultSet.getString(1));
				players.setScore(resultSet.getInt(2));
				players.setDate(resultSet.getString(3));
				
				playerList.add(players);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playerList;
	}
	
	public static boolean insertUserInDatabase(Players player){
		sql = "insert into players(username,score,date) values(?,?,?)";
		int rowsUpdated = 0;
		try {
			connection = DBConnector.createConnection();
			if(connection == null)
				System.out.println("connection is null");
			statement = connection.prepareStatement(sql);
			statement.setString(1, player.getUsername());
			statement.setInt(2, player.getScore());
			statement.setString(3, player.getDate());
			
			rowsUpdated = statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rowsUpdated != 0)
			return true;
		return false;
		
	}

	public static int[] getIndexOfOptions(int lengthOfColorArray) {
		Random random = new Random();
		int[] indexOfOptions = new int[4];
		for(int i=0;i<4;i++)
			indexOfOptions[i] = random.nextInt(lengthOfColorArray); 
		return indexOfOptions;
	}

	public static int matchingColor(int[] colorsUsedInOptions) {
		Random random = new Random();
		return colorsUsedInOptions[random.nextInt(4)];
	}

	public static int getPositionOfMatchingColor() {
		Random random = new Random();
		return random.nextInt(4);
	}

	public static boolean userAlreadyExists(String username) {
		sql = "select * from players where username = ?";
		try {
			connection = DBConnector.createConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			resultSet.next();
			if(resultSet != null)
				return true;
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static int addComment(String username, String comment) {
		// TODO Auto-generated method stub
		int rows = 0;
		sql = "insert into comments values('"+username+"','"+comment+"')";
		try {
			connection = DBConnector.createConnection();
			statement = connection.prepareStatement(sql);
			rows = statement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rows;
	}

	public static List<Comments> getComments() {
		List<Comments> commentList = new ArrayList<Comments>();
		
		sql = "select * from comments";
		try {
			connection = DBConnector.createConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			Comments c = null;
			while(resultSet.next()){
				c = new Comments();
				c.setUsername(resultSet.getString(1));
				c.setComments(resultSet.getString(2));
				commentList.add(c);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return commentList;
	}
}
