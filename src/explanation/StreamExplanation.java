package explanation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import entities.Person;

public class StreamExplanation {
	public static void main(String[] args) {
		
		//O que stream? Stream significa transmissao de dados em tempo real, esse e o significado dela, mas aqui no java
		//Temos uma interface chamada Stream que transforma uma colocao, seja lista, map, set, em dados que ficam muito
		//Mais facil a sua "filtracao". Para demonstrar vo resolver um problema sem usar a Stream e depois usando ela.
		
		//Tenho uma lista de pessoas do banco de dados e quero fazer a seguinte filtragem:
		//Pegar todas as pessoas que tenham 30 ou mais de idade;
		//Pegar as 3 primeiras pessoas, organizando pelo nome.
		
		List<Person> people = Person.database();
		people.sort((Person p1, Person p2) -> p1.getName().compareTo(p2.getName()));
		List<String> result = new ArrayList<>();
		for(Person person : people) {
			if(person.getAge() >= 30) {
				result.add(person.getName());
				if(result.size() == 3) {
					break;
				}
			}
		}
		
		System.out.println(result);
		
		//Usando Stream:
		List<String> peopleStream = Person.database()
				.stream()//Vamos transformar a list em Stream, o interessante do metodo da stream e que eles retornam
				//Ela mesma(Stream), entao da para fazer uma sequencia de metodos sem nenhum problema, isso e chamado
				//"Pipeline Methods"
				.filter((Person p1) -> p1.getAge() >= 30)//O metodo filter pede um predicado, podemos usar lambda ou uma
				//Classe anonima
				.sorted(Comparator.comparing(Person::getName))//Esse metodo e interessante. Podemos apenas passar uma
				//Lambda, como: .sorted((Person p1, Person p2) -> p1.getName().compareTo(p2.getName()));
				//Mas podemoos usar o metodo "comparing" da interface "Comparator" e apenas passar uma lambda como:
				//.sorted(Comparator.comparing((Person p1) -> p1.getName())), que aceita um Function, mas como isso e apenas
				//Uma implementacao padrao, podemos substituir por MethodReference
				.limit(3)//Aqui podemos limitar os resultados
				.map(Person::getName)//E o map faz que retorne tal tipo, aqui estamos apenas retornando os nomes (String)
				//como falei, isso e apenas um Function, podemos escrever lambda mas com MethodReference e mais rapido
				.collect(Collectors.toList());//E depois vamos coletar o Stream e converter para um List, com a classe "Collectors"
				//E podemos converter para diversos tipos de colecoes, como toSet() e etc.
		
		System.out.println(peopleStream);
		
		//Obs.: As Streams so sao executadas quando realizada uma operacao terminal, Aquela que faz a Stream retornar um
		//Valor diferente de uma Stream, as que retornam Stream sao chamadas de operacoes intermediarias
		
		IntStream streamTest = IntStream.iterate(0, (int i) -> i + 2);//A Stream nao executa aqui
		
		System.out.println();
		streamTest.limit(10).forEach((int i) -> System.out.print(i + " "));//Ela executa aqui
		
		//Depois de ter usado uma operacao terminal em uma Stream nao se pode mais usar ela, tem que abir de novo

		//streamTest.limit(10).forEach((int i) -> System.out.print(i + " "));
		
	}
}
