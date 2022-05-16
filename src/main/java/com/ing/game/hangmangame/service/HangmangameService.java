package com.ing.game.hangmangame.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.ing.game.hangmangame.constant.Alphabets;
import com.ing.game.hangmangame.constant.GameStatus;
import com.ing.game.hangmangame.dto.HangmangameDTO;

import lombok.Getter;

@Service
@Getter
public class HangmangameService {

	String randomWord;
	
	List<String> listOfLettersEntered;

	public HangmangameDTO getHangmanBorad() throws IOException {
		Random random = new Random();
		List<String> dictionary = Files.readAllLines(Paths.get(".\\src\\main\\resources\\templates\\dictionary.txt"));
		randomWord = dictionary.get(random.nextInt(dictionary.size()));
		HangmangameDTO hangmangameDTO = new HangmangameDTO();
		hangmangameDTO.setAlphabets(Stream.of(Alphabets.values()).map(Enum::name).collect(Collectors.toList()));
		hangmangameDTO.setWordToGuess(randomWord);
		hangmangameDTO.setSecrectWord(randomWord.replaceAll(".", "__ "));
		hangmangameDTO.setGameStatus(String.valueOf(GameStatus.ACTIVE));
		hangmangameDTO.setGuessesLeft(7);
		listOfLettersEntered = new ArrayList<String>();
		return hangmangameDTO;
	}

	public HangmangameDTO updateHangmanBorad(String letter, HangmangameDTO hangmangameDTO) {
		String wordToGuess = hangmangameDTO.getWordToGuess();
		int guessLeft= hangmangameDTO.getGuessesLeft();
		if(wordToGuess.contains(letter)) {
		listOfLettersEntered.add(letter);
		String letterToExclude=listOfLettersEntered.stream().collect(Collectors.joining());
		hangmangameDTO.setSecrectWord(randomWord.replaceAll("[^" + letterToExclude + "]", "__ "));
		}else if(guessLeft!=0){
			hangmangameDTO.setGuessesLeft(guessLeft-1);
		}
		if(hangmangameDTO.getGuessesLeft() == 0) {
			hangmangameDTO.setGameStatus(String.valueOf(GameStatus.LOST));	
		}
		
		if(hangmangameDTO.getSecrectWord().equals(hangmangameDTO.getWordToGuess())) {
			hangmangameDTO.setGameStatus(String.valueOf(GameStatus.WON));	
		}
		hangmangameDTO.getAlphabets().remove(letter);
		return hangmangameDTO;
	}

}
