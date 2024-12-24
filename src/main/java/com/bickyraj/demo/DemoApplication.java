package com.bickyraj.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages = "com.bickyraj.demo")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		StringBuilder sb = new StringBuilder();
//		sb.append("hello world");
//		sb.append(" test");
//		System.out.println(sb.capacity());
//		System.out.println(sb);

//		Employee employee = new Employee("bicky", 500d);
//		Employee employee1 = new Employee("ram", 400d);
//		Employee employee2 = new Employee("test", 100d);
//		Employee employee3 = new Employee("sam", 700d);
//		Employee employee4 = new Employee("jon", 400d);
//		Employee employee5 = new Employee("joe", 900d);
//		Employee employee6 = new Employee("jane", 600d);
//
//		List<Employee> employees = List.of(employee, employee1, employee2, employee3, employee4, employee5, employee6);
//		double average = employees.
//                stream()
//                .map(Employee::getSalary)
//				.collect(Collectors.averagingDouble(Double::doubleValue));
//		System.out.println(average);
//
//		List<Integer> numbers = List.of(1, 3, 4, 5, 34, 5, 7, 7,22, 23, 3421, 44, 66, 345, 54647, 4356);
//
//		List<Integer> test = numbers
//				.stream()
//				.filter(a -> a % 2 == 0)
//				.toList();
//		System.out.println(test);
//
//		List<String> words = List.of("hello", "world", "ram", "hambo");
//		List<String> test1 = words
//				.stream()
//				.map(w ->  w.substring(0, w.length() -1).toLowerCase() +  w.substring(w.length() - 1).toUpperCase())
//				.map(w -> new StringBuilder(w).reverse().toString())
//				.toList();
//		System.out.println(test1);
//
//		List<String> test2 = words
//				.stream()
//				.filter(w -> w.substring(0, 1).equalsIgnoreCase("h"))
//				.toList();
//		System.out.println(test2);
//
//		Integer sum = numbers
//				.stream()
//				.reduce(0, Integer::sum);
//		System.out.println(sum);
//
//		int max = numbers
//				.stream()
//				.reduce(0, Integer::max);
//		int min = numbers
//				.stream()
//						.min(Integer::compareTo)
//								.get();
//		System.out.println("the max number is " + max);
//		System.out.println("the min number is " + min);
//
//		List<Integer> distinct = numbers
//				.stream()
//				.distinct()
//				.toList();
//		System.out.println(distinct);
//
//		List<Integer> sortnumbers = numbers
//				.stream()
//				.distinct()
//				.sorted()
//				.toList();
//		System.out.println(sortnumbers);
//
//		List<String> sort = words
//				.stream()
//				.sorted(Comparator.comparing(i -> i.substring(0, 1)))
//				.toList();
//
//		System.out.println(sort);
//
//		List<String> testLength = words
//				.stream()
//				.filter(w -> w.length() > 3)
//				.toList();
//		System.out.println(testLength);
//
//		Map<String, Double> mapPerson = employees
//				.stream()
//				.collect(Collectors.toMap(Employee::getName, Employee::getSalary));
//		System.out.println(mapPerson);
//
//		Predicate<Integer> predicateEven = i -> i % 2 == 0;
//
//		if (!predicateEven.test(5)) {
//			System.out.println("yes that's not correct");
//		} else {
//			System.out.println("yes that's correct");
//		}
//
//		Function<Integer, Integer> getMidValue = (Integer a) -> a/2;
//
//
//		System.out.println(getMidValue.apply(6));
	}
}
