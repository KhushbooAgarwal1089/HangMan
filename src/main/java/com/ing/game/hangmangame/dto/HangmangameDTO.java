package com.ing.game.hangmangame.dto;

import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
@Scope("session")
public class HangmangameDTO {

	
	private List<String> alphabets;
	
	@Value("7")
	private int guessesLeft;
	
	private String secrectWord;
	
	private String gameStatus;
	
	private String wordToGuess;
	
	@Size(max=1)
	private String enteredLetter;
	
}
