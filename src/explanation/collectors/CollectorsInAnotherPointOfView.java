package explanation.collectors;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import entities.Gender;
import entities.Person;

public class CollectorsInAnotherPointOfView {
	public static void main(String[] args) {
		
		//A classe collectors nao serve apenas para pegar a lista, ele tambem consegue fazer operacoes normais como max,
		//Min e etc.
		
		Optional<Person> person = Person.database().stream()
				.max(Comparator.comparing(Person::getSalary));
		System.out.println(person.get());

		
		Optional<Person> personByCollectors = Person.database().stream()
				.collect(Collectors.maxBy(Comparator.comparing(Person::getSalary)));
		System.out.println(personByCollectors.get());
		
		
		Double person2 = Person.database().stream()
				.mapToDouble(Person::getSalary)
				.average().getAsDouble();
		System.out.println(person2);
		
		
		Double personByCollectors2 = Person.database().stream()
				.collect(Collectors.averagingDouble(Person::getSalary));
		System.out.println(personByCollectors2);
		
		//E entre outros metodos que voce possivelmente ja conhece.
		
		//Mas qual e a diferenca? Para o tipo de uso acima, e a mesma coisa usar o collect ou nao, mas o metodo collect da
		//Para juntar diversos metodos da classe Collectors e fazer algo bem legal, como:
		Person personWithoutOptional = Person.database().stream()
				.collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Person::getSalary)),
						Optional::get));
		System.out.println(personWithoutOptional);
		//O metodo collectingAndThen vai coletar e realizar outra acao em seguida :0
		
		//Podemos tambem usar o metodo groupingBy e juntar outros metodos do Collectors
		Map<Gender, Person> list = Person.database().stream()
				.collect(Collectors.groupingBy(Person::getGender, 
						//Caso nao consiga enteder esse metodo, vai para a classe "Grouping"
						Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Person::getSalary)), Optional::get)));
		
		System.out.println(list);
		
		//Mas se der para fazer a mesma coisa com o collect e sem o mesmo, qual eu uso? E meio complicado a escolha,
		//Devemos botar na balanca diversos pontos, como a performance (Que depende de cada caso, recomendo fazer o
		//Teste caso queira o maximo de performance), e tambem a facilidade de leitura e compreensao que depende
		//De cada pessoa.
		
	}
}
