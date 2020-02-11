package com.emprendesoftcr.web.jsonDeserializer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.ConteoManualCajaBo;
import com.emprendesoftcr.modelo.ConteoManualCaja;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ConteoManualCajaDeserializer extends StdDeserializer<Object> {

	

	private static final long serialVersionUID = 5725745564521327390L;
	
	@Autowired
	ConteoManualCajaBo conteoManualCajaBo;

	public ConteoManualCajaDeserializer() {
		super(ConteoManualCaja.class);
	}

	@Override
	public ConteoManualCaja deserialize(JsonParser json, DeserializationContext context) throws IOException, JsonProcessingException {
		try {
			if (StringUtils.isEmpty(json.getText())) {
				return null;
			}
			return conteoManualCajaBo .buscar(Long.parseLong(json.getText()));
		} catch (ParseException e) {
			return null;
		}
	}
}
