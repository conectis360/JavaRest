package com.labegen.promebo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.labegen.promebo.model.Message;

public class MessageService {
	
	static PreparedStatement ps = null;
	
	public List<Message> getAllMessages(Connection conn) throws SQLException {
		 ps = conn.prepareStatement( "SELECT * from messages;" );
         ResultSet rs = ps.executeQuery();
 		 List<Message> list = new ArrayList<Message>();
         while ( rs.next() ) {
        	Message m1 = new Message();
        	m1.setId(rs.getInt("id"));
        	m1.setAuthor(rs.getString("usuario"));
        	m1.setMessage(rs.getString("mensagem"));
        	list.add(m1);
         }

		return list;
	}
	 

	public static void setNewMessage(Connection conn, Message message) throws SQLException, ClassNotFoundException {
		ps = conn.prepareStatement( "insert into messages (usuario, mensagem) values (?,?)");
        ps.setString(1, message.getAuthor());
        ps.setString(2, message.getMessage());
        ps.executeUpdate();
       
        ps.close();
	}
	
	
	
}
