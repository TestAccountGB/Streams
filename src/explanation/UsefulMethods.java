package explanation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.Person;

public class UsefulMethods {
	public static void main(String[] args) {
		
		//Metodos uteis...
		
		//Skip
		System.out.println();
		List<String> nomes = Arrays.asList("Arthur", "Ana", "Julio").stream()
				.filter((String s) -> s.charAt(0) == 'A')
				.skip(1)//Aqui a gente pode skippar o primeiro elemento
				.collect(Collectors.toList());
		System.out.println(nomes);

		//Count
		System.out.println();
		long count = Arrays.asList("Arthur", "Ana", "Julio").stream()
				.filter((String s) -> s.charAt(0) == 'A')
				.count(); //Podemos apenas contar quandos elementos tem
		System.out.println(count);

		//Distinct
		System.out.println();
		nomes = Arrays.asList("Arthur", "Ana", "Ana", "Julio").stream()
				.filter((String s) -> s.charAt(0) == 'A')
				.distinct()//Podemos dintinguir os elementos e retornar apenas os unicos (Isso depende do equals da classe)
				.collect(Collectors.toList());
		System.out.println(nomes);
		
		//Foreach
		System.out.println();
		nomes.stream().forEach(System.out::println);//Foreach
		//System.out::println = (String s) -> System.out.println(s)

		//Min
		System.out.println();
		Optional<String> min = Arrays.asList("Arthur", "Ana", "Julio").stream()
				.min(String::compareTo);//Retorna um Optional. Retorna o valor minimo da Lista (Basicamente o primeiro de
		//Uma lista organizada)
		System.out.println(min);

		//Max
		System.out.println();
		Optional<Person> max = Person.database().stream()
				.max(Comparator.comparing(Person::getSalary));//Retorna um Optional. Retorna o valor maximo da Lista 
		//(Basicamente o ultimo de uma lista organizada)
		System.out.println(max.get().getSalary());
		
		//Of
		System.out.println();
		List<Person> pessoas = new ArrayList<>();
		Person pessoa = new Person("Andre", 30, 2000);
		//addAll porque ele retorna uma list inteira
		pessoas.addAll(Stream.of(pessoa)//Consigo transformar qualquer objeto em Stream :0
				.filter((Person p) -> p.getSalary() > 1000)//Se o salario for maior que 1k ele vai adicionar
				.collect(Collectors.toList()));
		System.out.println(pessoas);
		
		//FindAny
		System.out.println();
		Person.database().stream()
		.filter((Person p) -> p.getAge() > 10)
		.findAny()//Pega basicamente qualquer um
		.ifPresent(System.out::println);
		
		//FindFirst
		System.out.println();
		Person.database().stream()
		.sorted(Comparator.comparing(Person::getAge))
		.findFirst()//Pega o primeiro na lista. Obs.: Organize a lista antes
		.ifPresent(System.out::println);
		
		//Reversed
		System.out.println();
		Person.database().stream()
		.filter((Person p) -> p.getAge() > 10)
		.sorted(Comparator.comparing(Person::getAge).reversed())//Ele inverti a lista
		.findFirst()//E como nao temos um "findLast", podemos usar o metodo reversed e depois usar o findFirst que
		//Vai pegar o ultimo da lista. STONKS
		.ifPresent(System.out::println);
		
		//AllMatch
		System.out.println();
		System.out.println(Person.database().stream()
		.allMatch((Person p) -> p.getAge() > 18));//Ele retorna verdadeiro caso TODOS da lista atender aos parametros
		
		//AnyMatch
		System.out.println();
		System.out.println(Person.database().stream()
		.anyMatch((Person p) -> p.getAge() > 18));//Ele retorna verdadeiro caso ALGUM da lista atenda aos parametros
		
		//NoneMatch
		System.out.println();
		System.out.println(Person.database().stream()
		.noneMatch((Person p) -> p.getAge() < 11));//Ele retorna verdadeiro caso NINGUEM da lista atenda aos parametros
		
	}
}
