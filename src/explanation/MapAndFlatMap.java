package explanation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapAndFlatMap {
	public static void main(String[] args) {
		//Diferenca entre map e flatMap
		
		//Para explicar essa diferenca vou comecar com um exemplo
		
		System.out.println();
		List<String> words = Arrays.asList("Homeless", "Cartman");
		System.out.println(words);
		//Quero pegar cada letra desse list, como eu faria isso sem stream? Possivelmente assim...
		
		List<String[]> charWords = new ArrayList<>();
		//Como podemos ver aqui, tive que criar uma list de array de String pois o metodo split do String retorna um array
		for(String word : words) {
			String[] characters = word.split("");
			charWords.add(characters);
		}
		
		List<String> string = new ArrayList<>();
		for(String[] array : charWords) {
			string.add(Arrays.toString(array).replaceAll("[\\[\\]]", ""));
			//TIve que usar o replaceAll pois o toString do Arrays retorna uma String com barras no comeco e no final ._.
		}
		System.out.println(string);
		
		//Agora vamos usar o Stream
		
		List<String> wordsStream = words.stream()
				.map((String s) -> s.split(""))//Aqui ele esta retornando uma Stream assim: Stream<String[]>, mas como tiramos
				//essa parte do Array, tipo transformar tudo em uma String? Com o flatMap, pois ele realiza o achatamento(flat)
				//Do conteudo da steam, tipo, se eu tiver uma Stream<List<String>> e usar o flatMap ele vai retornar o 
				//Stream<String>
				.flatMap(Arrays::stream)//Estamos fazendo que o flatMap retorne apenas a Stream<String>
				.collect(Collectors.toList());//E depois coletamos
		
		System.out.println(wordsStream);
		
		
	}
}
