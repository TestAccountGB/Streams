package explanation.collectors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import entities.AgeClassification;
import entities.Gender;
import entities.Person;

public class Grouping {
	public static void main(String[] args) {
		
		//O Collectors tem alguns metodos muito uteis que reduzem muito o codigo, como por exemplo, quero fazer um
		//Map agrupando todos por genero, mas primeiramente, vamos fazer no jeito tradicional e depois vamos fazer
		//Usando o Collectors
		
		//Tradicional...
		Map<Gender, List<Person>> traditionalWay = new HashMap<>();
		List<Person> male = new ArrayList<>();
		List<Person> female = new ArrayList<>();
		
		for(Person person : Person.database()) {
			if(person.getGender() == Gender.MALE)
				male.add(person);
			else female.add(person);
		}
		
		traditionalWay.put(Gender.MALE, male);
		traditionalWay.put(Gender.FEMALE, female);
		
		System.out.println(traditionalWay);
		
		//Usando Collectors
		Map<Gender, List<Person>> newWay = Person.database().stream()
				.collect(Collectors.groupingBy(Person::getGender));
		System.out.println();
		System.out.println(newWay);
		
		//O metodo groupingBy pode receber dois parametros, o primeiro sendo o tipo da key do Map que ele vai retornar,
		//E no segundo parametro podemos passar o que ele vai retornar nos valores desse Map, e ele vai se basear no
		//Primeiro parametro para a organizacao. No metodo acima pedimos que a key fosse o genero da pessoa, e nao
		//Precisamos passar o segundo parametro pois o java ja consegue perceber que passamos uma lista de Pessoas
		//Na stream (Person.database()), mas no metodo abaixo usamos os dois parametros...
		//Obs.: Todos os dois parametros sao passados por meio de lambda
		
		Map<Gender, Long> countingByGender = Person.database().stream()
				.collect(Collectors.groupingBy(Person::getGender, Collectors.counting()));
		//A key vai ser o genero, e ele vai retornar a contagem do mesmo
		
		System.out.println();
		System.out.println(countingByGender);
		
		Map<Gender, Double> averageSalayByGender = Person.database().stream()
				.collect(Collectors.groupingBy(Person::getGender, Collectors.averagingDouble(Person::getSalary)));
		
		System.out.println();
		System.out.println(averageSalayByGender);
		
		//Podemos ate organizar por dados que nao estao na propria classe, porque o parametro aceita uma lambda, entao
		//Podemos passar comportamentos, como:
		
		Map<AgeClassification, List<Person>> personByAgeClassification = Person.database().stream()
				.collect(Collectors.groupingBy((Person p) -> {
					if(p.getAge() < 18) return AgeClassification.NOT_ADULT;
					else return AgeClassification.ADULT;
				}));
		
		System.out.println();
		System.out.println(personByAgeClassification);
		
		//Caso voce nao saiba, o java deixa a gente fazer um Map de um Map, ou seja, podemos colocar um groupingBy
		//Dentro de um groupingBy e assim por diante, claro, isso nao e recomendado, mas para alguns casos nao e "errado"
		//Usar isso, por exemplo:
		
		Map<Gender, Map<AgeClassification, List<Person>>> personByGenderAndAgeClassification =
				Person.database().stream()
				.collect(Collectors.groupingBy(Person::getGender, Collectors.groupingBy((Person p) -> {
					if(p.getAge() < 18) return AgeClassification.NOT_ADULT;
					else return AgeClassification.ADULT;
				})));
		//Estou pedindo que retorne uma key de genero, e como valor retorne um Map baseado na idade
		
		System.out.println();
		System.out.println(personByGenderAndAgeClassification);
		
		//Podemos fazer o inverso disso...
		Map<AgeClassification, Map<Gender, List<Person>>> personByAgeClassificationAndGender =
				Person.database().stream()
				.collect(Collectors.groupingBy((Person p) -> {
					if(p.getAge() < 18) return AgeClassification.NOT_ADULT;
					else return AgeClassification.ADULT;
				}, Collectors.groupingBy(Person::getGender)));
		
		System.out.println();
		System.out.println(personByAgeClassificationAndGender);

		//Jeitos interessantes de usar o groupingBy:
		//Metodo collectingAndThen...
		//Esse metodo ele vai dentro do metodo Collect, mas a diferenca dele e que ele vai coletar e realizar tal acao, por
		//Isso o seu nome...
		
		Map<Gender, Person> collect = Person.database().stream()
				.collect(Collectors.groupingBy(Person::getGender, Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparing(Person::getSalary)), Optional::get)));
		
		//Estamos usando o collectingAndThen aqui para tirar o valor do Optional, pois o mesmo retorna um Optional, mas
		//Ele aqui na lista e basicamente inutil, pois aqui nao vai retornar null, caso nao tenha nada no valor do Map
		//Ele nem cria o mesmo
		System.out.println();
		System.out.println(collect);
		
		Map<Gender, DoubleSummaryStatistics> collect2 = Person.database().stream()
				.collect(Collectors.groupingBy(Person::getGender, Collectors.summarizingDouble(Person::getSalary)));
		
		System.out.println();
		System.out.println(collect2);
		
		//Conversao
		
		@SuppressWarnings("unused")
		Map<AgeClassification, Set<Person>> test2 = Person.database().stream()
						.collect(Collectors.groupingBy((Person p) -> {
							if(p.getAge() < 18) return AgeClassification.NOT_ADULT;
							else return AgeClassification.ADULT;
						}, Collectors.toSet()));
		//Temos que colocar como segundo parametro do groupingBy pois o segundo parametro que e o valor do Map
		
		//Podemos usar o metodo toCollection caso a gente queria uma collection mais especifica
		@SuppressWarnings("unused")
		Set<Person> test = Person.database().stream()
				.filter((Person p) -> p.getName().length() > 3)
				.collect(Collectors.toCollection(LinkedHashSet::new)); //() -> new LinkedHashSet<>() Isso e um supplier, por isso
		//Nao tem nenhum parametro
		
	}
}
