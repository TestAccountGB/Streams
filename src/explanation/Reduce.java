package explanation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import entities.Person;

public class Reduce {
	public static void main(String[] args) {
		
		//Reduce
		System.out.println();
		//Reduce e um metodo mais complicadinho pois primeiramente temos que entender sua logica. Como o seu proprio
		//Nome ja diz, o seu objetivo e reduzir, mas reduzir como? Vamos imaginar que temos uma lista de Integer, como:
		List<Integer> listReduce = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		
		//O meu objetivo com essa lista e somar todos o seus elementos, transforma-los em um so, ou seja, quero
		//Reduzi-los, :0
		
		Optional<Integer> soma = listReduce.stream()
		.reduce((Integer x, Integer y) -> x + y);
		
		//Ou seja, estamos somando todos os elementos da lista, podemos substituir por Integer::sum
		//Vale lembrar que o reduce recebe uma operacao matematica, entao ele e util para somar, subtrair etc.
		System.out.println("Reduce soma: " + soma.get());
		
		Optional<Integer> mult = listReduce.stream()
		.reduce((Integer x, Integer y) -> x * y);
		//Em java nao temos Integer::mult ou algo parecido, por isso precisamos fazer assim
		System.out.println("Reduce multiplicacao: " + mult.get());
		
		//Reduce com classes...
		Optional<Double> peopleSalarySum = Person.database().stream()
		.map(Person::getSalary)//Vamos apenas retornar o salario
		.reduce(Double::sum);//E depois fazer a soma
		
		System.out.println(peopleSalarySum.get());
		
		//Mas quando a gente usa o map apenas pra retornar apenas um valor primitivo podemos fazer isso...
		double/*pode ser uma classe wrapper aqui*/ peopleSalarySum2 = Person.database().stream()
		.mapToDouble(Person::getSalary)//Isso nos permite usar diversos metodos matematicos, como o "sum", "min"
		//"max" e etc.
		.sum();
		
		System.out.println(peopleSalarySum2);
		
	}
}
