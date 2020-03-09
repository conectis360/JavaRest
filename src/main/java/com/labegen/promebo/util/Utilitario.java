package com.labegen.promebo.util;

import javax.ws.rs.core.Response;

public class Utilitario {
	public static Response retorna_resposta_json(String json){
		return Response.ok(json)
        .header("Access-Control-Allow-Origin", "*")
        .header("Access-Control-Allow-Credentials", "true")
        .header("Access-Control-Allow-Headers",
          "origin, content-type, accept, authorization,sessionid")
        .header("Access-Control-Allow-Methods", 
          "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        .build();
	}
	
}
