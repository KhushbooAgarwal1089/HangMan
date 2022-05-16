package com.ing.game.hangmangame.controller;

import java.io.IOException;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ing.game.hangmangame.dto.HangmangameDTO;
import com.ing.game.hangmangame.service.HangmangameService;

@RestController	
@Validated
public class HangmangameController {
	
	@Autowired
	HangmangameService service;
	
	@PostMapping("/getHangmanBorad")
	public ResponseEntity<HangmangameDTO> getHangmanBorad() throws IOException {
		
		return new ResponseEntity<>(service.getHangmanBorad(),HttpStatus.OK);
		
	}
	
	@PostMapping("/updateHangmanBorad/{letter}")
	public ResponseEntity<HangmangameDTO> updateHangmanBorad(@PathVariable("letter") @Size(max = 1) String letter, @RequestBody HangmangameDTO hangmangameDTO) throws IOException{
		
		
		if(hangmangameDTO!=null && hangmangameDTO.getGameStatus().equals("ACTIVE")) {
		return new ResponseEntity<>(service.updateHangmanBorad(letter.toUpperCase(),hangmangameDTO),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(hangmangameDTO,HttpStatus.OK);
	}

}
