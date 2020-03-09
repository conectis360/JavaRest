package com.labegen.promebo.resources;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import com.google.gson.Gson;
import com.labegen.promebo.config.Conexao;
import com.labegen.promebo.model.Message;
import com.labegen.promebo.service.MessageService;
import com.labegen.promebo.util.Utilitario;

import io.swagger.annotations.ApiParam;

@Path("/messages")
public class MessageResource {
	Connection conn = null;
	Map<String, Object> saida = new HashMap<String, Object>();

	MessageService messageService = new MessageService();

	@GET
	@Produces("application/json")
	public List<Message> getMessage() throws ClassNotFoundException, SQLException {
		conn = Conexao.getConexao();
		return messageService.getAllMessages(conn);
	}

	@POST
	@Path("/inserir")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes("application/json")
	public Response setNewMessage(@ApiParam(value = "Message", required = true) Message message, @Context HttpHeaders headers) 
			throws ClassNotFoundException, SQLException 
	{
		conn = Conexao.getConexao();
		try {
			MessageService.setNewMessage(conn, message);
			conn.commit();
			conn.commit();
			saida.put("status", "success");
			saida.put("mensagem", "Inserido com sucesso.");
		} catch (Exception e) {
			conn.rollback();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			saida.put("status", "error");
			saida.put("errorMessage", e.getMessage());
		}
		conn.close();
		String json = new Gson().toJson(saida);
		return Utilitario.retorna_resposta_json(json);

	}
}
