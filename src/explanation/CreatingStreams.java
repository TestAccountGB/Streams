package explanation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import entities.Person;

public class CreatingStreams {
	public static void main(String[] args) throws IOException {
		
		//Aqui irei mostrar como criar Streams a partir de tal objeto, ou mesmo sem objeto
		
		//Metodo range
		IntStream.rangeClosed(2, 50).filter((int i) -> i  % 2 == 0).forEach((int i) -> System.out.print(i + " "));
		System.out.println();
		IntStream.range(2, 50).filter((int i) -> i  % 2 == 0).forEach((int i) -> System.out.print(i + " "));
		//A unica diferenca entre os dois e que o metodo "range" nao inclui o ultimo numero
		
		//Arrays...
		System.out.println();
		System.out.println();
		int[] array = {1, 2, 3, 4, 5};
		Arrays.stream(array).average().ifPresent(System.out::print);
		
		//Strings/Classes
		
		//Podemos usar o metodo of
		System.out.println();
		System.out.println();
		Stream.of("Hello", " My Friend", ", How are you?").map(String::toLowerCase).forEach(System.out::print);
		 
		System.out.println();
		Person person = new Person("Eu", 20, 2500);
		Stream.of(person).filter((Person p) -> p.getSalary() > 2000).forEach(System.out::print);;
		
		//Stream de um arquivo...
		Path file = Paths.get(System.getProperty("user.home"), "Desktop", "Stream.txt");
		
		if(Files.notExists(file))
			Files.createFile(file);
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file.toString()))){
			
			writer.write("Ingredientes:");
			writer.newLine();
			writer.write("1 - 1/2 xícara (chá) de óleo");
			writer.newLine();
			writer.write("2 - 3 cenouras médias raladas");
			writer.newLine();
			writer.write("3 - 4 ovos");
			writer.newLine();
			writer.write("4 - 2 xícaras (chá) de açúcar");
			writer.newLine();
			writer.write("5 - 2 e 1/2 xícaras (chá) de farinha de trigo");
			writer.newLine();
			writer.write("6 - 1 colher (sopa) de fermento em pó");
			writer.newLine();
			
		}
		
		try(Stream<String> txt = Files.lines(file, Charset.defaultCharset());
				//Este metodo da classe "Files" do pega o texto INTEIRO e coloca dentro de uma Stream
				BufferedReader reader = new BufferedReader(new FileReader(file.toString()))){
			
			System.out.println();
			System.out.println();
			txt.filter((String s) -> s.charAt(0) == '3')
			.map((String s) -> s.substring(4, s.length()))
			.forEach((String s) -> System.out.print("Terceiro Ingrediente -> " + s));
			
			//Maneira alternativa de fazer...
			System.out.println();
			String string;
			
			while((string = reader.readLine()) != null) {
				Stream.of(string).filter((String s) -> s.charAt(0) == '3')
				.map((String s) -> s.substring(4, s.length()))
				.forEach((String s) -> System.out.print("Terceiro Ingrediente -> " + s));
			}
		}
		
		//Iterate
		System.out.println();
		System.out.println();
		//O metedo iterate realiza uma funcao que lhe foi passada PARA SEMPRE, por isso que sempre usamos ele, temos
		//Que colocar o metodo "limit" para parar ele.
		
		//Ele pede dois parametros, o primeiro sendo o "seed" que o valor que ele vai comocar e o segundo parametro
		//E uma interface funcional "UnaryOperator" (Ou seja, podemos passar lambda), e o diferencial dessa interface
		//E que ela tem que retornar o tipo que foi passado no seed, para assim continuar um loop. Ou seja, se passamos
		//Uma String no seed, temo que retornar uma String na lambda
		Stream.iterate("String", n -> n.substring(0, n.length() - 1)).limit(6).forEach(System.out::println);
		//Vamos diminuir uma letra de String 6 vezes
		
		//Logica da Sequencida de Fibonacci... (Caso nao conheca. Pesquisa seu vagabundo!)
		Stream.iterate(new int[] {0, 1}, (int[] i) -> new int[] {i[1], i[0] + i[1]})
		//Vamos comecar pelos valores 0 e 1, e depois vamos somar. Vamos guardar o resultado no index 0 e fazer a
		//Operacao no index 1.
		.limit(10)
		.forEach((int[] i) -> System.out.println(Arrays.toString(i)));
		
		//Generate
		System.out.println();
		//Generate e basicamente o iterate so que ele nao tem nenhum comportamento que podemos passar, seu uso mais
		//Comum e com numeros aleatorios, como...
		
		Random random = new Random();
		Stream.generate(() -> random.nextInt(100)).limit(5).forEach(System.out::println);
		//Seu metodo aceita apenas um Supplier, e como voce sabe, o metodo do supplier nao tem nenhum parametro,
		//Temos que apenas passar um valor
		
	}
}
