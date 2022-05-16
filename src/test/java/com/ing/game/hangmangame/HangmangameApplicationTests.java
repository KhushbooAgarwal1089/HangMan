package com.ing.game.hangmangame;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.game.hangmangame.dto.HangmangameDTO;
import com.ing.game.hangmangame.service.HangmangameService;

@SpringBootTest
@AutoConfigureMockMvc
class HangmangameApplicationTests {

	@Autowired
    private MockMvc mvc;
	
	@Autowired
	HangmangameService service;
	
	
	@Test
	public void getHangmanBoradTest()
	  throws Exception {

 
		 mvc.perform(post("/getHangmanBorad")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("gameStatus", is("ACTIVE")))
	      .andExpect(jsonPath("guessesLeft", is(7)));
		
	}
	
	@Test
	public void updateHangmanBoradTest()
	  throws Exception {
		
		HangmangameDTO dto=  service.getHangmanBorad();
		
		ObjectMapper objectMapper = new ObjectMapper();
	    String json =  objectMapper.writeValueAsString(dto);

		 mvc.perform(post("/updateHangmanBorad/@")
	      .contentType(MediaType.APPLICATION_JSON).content(json))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("gameStatus", is("ACTIVE")))
	      .andExpect(jsonPath("guessesLeft", is(6)));
		
	}

}
