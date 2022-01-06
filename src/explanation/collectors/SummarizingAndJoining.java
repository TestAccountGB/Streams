package explanation.collectors;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import entities.Person;

public class SummarizingAndJoining {
	public static void main(String[] args) {
		//Summarizar e basicamente listar as coisas basicas de uma lista, como o sum, max e etc.
		
		//Isso ajuda muito a reduzir o codigo
		
		//Streams normal:
		List<Person> people = Person.database();
		
		//Count
		long count = people.stream()
				.count();
		
		//Sum
		double sum = people.stream()
				.mapToDouble(Person::getSalary)
				.sum();
		
		//Min
		OptionalDouble min = people.stream()
				.mapToDouble(Person::getSalary)
				.min();
		
		//Average
		OptionalDouble average = people.stream()
				.mapToDouble(Person::getSalary)
				.average();
		
		
		//Max
		OptionalDouble max = people.stream()
				.mapToDouble(Person::getSalary)
				.max();
		
		System.out.println("Statistics: Count = " + count + ", Sum = " + sum + ", Min = " + min.getAsDouble() + 
				", Average = " + average.getAsDouble() + ", Max = " + max.getAsDouble());
		
		//Collector...
		
		DoubleSummaryStatistics summary = people.stream()
				.collect(Collectors.summarizingDouble(Person::getSalary));

		System.out.println();
		System.out.println(summary);
		
		//E ainda podemos pegar valores separados
		
		System.out.println(summary.getAverage());
		
		//Joining...
		System.out.println();
		
		String joining = people.stream()
				.map(Person::getName)
				.collect(Collectors.joining(", "));//Podemos passar um delimitador como eu passei, mas nao e obrigatorio
		
		System.out.println(joining);
	}
}
