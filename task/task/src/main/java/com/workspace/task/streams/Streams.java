package com.workspace.task.streams;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.workspace.task.payload.Employee;

public class Streams {

    public static void main(String[] args) {
        System.out.println("Test Line ");

        //Program using streams to print even numbers
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> collect = nums.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println(collect);

        //Program using streams to convert string to uppercase
        List<String> names = Arrays.asList("Rama", "Sita", "Lakshmana");
        List<String> collect1 = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect1);

        //Program using streams to sort numbers descending order
        List<Integer> nums2 = Arrays.asList(5, 1, 8, 3, 2);
        List<Integer> collect2 = nums2.stream().sorted(Comparator.comparing(Integer::intValue).reversed()).collect(Collectors.toList());
        System.out.println(collect2);

        //Program using streams to return distinct elements
        List<Integer> nums3 = Arrays.asList(5, 1, 8, 3, 2, 2, 7, 9, 9);
        List<Integer> collect3 = nums3.stream().distinct().collect(Collectors.toList());
        System.out.println(collect3);

        //Program using streams to return count of the elements
        List<String> names2 = Arrays.asList("Ram", "Ravi", "Sita", "Ramesh");
        Long count = names2.stream().filter(m -> m.startsWith("R")).count();
        System.out.println(count);

        //Program using streams to return first element greater than 30
        List<Integer> nums4 = Arrays.asList(11, 22, 33, 44, 55);
        Optional<Integer> res = nums4.stream().filter(m -> m > 30).findFirst();
        System.out.println(res.get());

        //Program using streams to return any matching value divisible by 2
        List<Integer> nums5 = Arrays.asList(11, 22, 33, 44, 55);
        Boolean value = nums5.stream().anyMatch(n -> n % 2 == 0);
        System.out.println(value);

        List<Integer> nums6 = Arrays.asList(11, 22, 33, 44, 55);
        Boolean value1 = nums5.stream().allMatch(n -> n % 2 == 0);
        System.out.println(value1);

        List<Integer> nums7 = Arrays.asList(11, 22, 33, 44, 55);
        Boolean value2 = nums5.stream().noneMatch(n -> n < 0);
        System.out.println(value2);

        List<Integer> nums8 = Arrays.asList(1, 2, 3, 4, 5);
        Integer result = nums8.stream().reduce(0, Integer::sum);
        System.out.println(result);

        List<String> strList = Arrays.asList("Akbar", "king", "of Hastinapura");
        String modifiedString = strList.stream().reduce("", (a, b) -> a + " " + b).substring(1);
        System.out.println(modifiedString);

        List<Integer> nums9 = Arrays.asList(12, 45, 67, 2, 89, 34);
        Integer max = nums9.stream().max(Integer::compareTo).get();
        System.out.println(max);

        List<List<String>> stringslist = Arrays.asList(Arrays.asList("A", "B"), Arrays.asList("C", "D"));
        List<String> sList = stringslist.stream().flatMap(List::stream).toList();
        System.out.println(sList);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1L, "Srihari", "srihari@gmail.com", 9876543210L, new BigDecimal("98000.00"), "HR", "Manager"));
        employeeList.add(new Employee(2L, "Achuth", "achyuth@gmail.com", 9876543211L, new BigDecimal("97000.00"), "Admin", "Assistant Manager"));
        employeeList.add(new Employee(3L, "Keshav", "keshav@gmail.com", 9876543212L, new BigDecimal("99000.00"), "Technical", "Developer"));
        employeeList.add(new Employee(4L, "Rishi", "rishi@gmail.com", 9876543213L, new BigDecimal("96000.00"), "HR", "Manager"));
        employeeList.add(new Employee(5L, "Shanmukh", "shanmukh@gmail.com", 9876543214L, new BigDecimal("93000.00"), "Admin", "Assistant Manager"));
        employeeList.add(new Employee(6L, "Pramukh", "pramukh@gmail.com", 9876543215L, new BigDecimal("92000.00"), "Technical", "Developer"));
        employeeList.add(new Employee(7L, "Rishi", "rishi@gmail.com", 9876543213L, new BigDecimal("96000.00"), "HR", "Manager"));
        employeeList.add(new Employee(8L, "Shanmukh", "shanmukh@gmail.com", 9876543214L, new BigDecimal("93000.00"), "Admin", "Assistant Manager"));
        employeeList.add(new Employee(9L, "Pramukh", "pramukh@gmail.com", 9876543215L, new BigDecimal("92000.00"), "Technical", "Developer"));

        Optional<Employee> salary = employeeList.stream().max(Comparator.comparingDouble(m -> m.getSalary().doubleValue()));
        System.out.println(salary.get().getSalary());

        Double collect4 = employeeList.stream().collect(Collectors.averagingDouble(m -> m.getSalary().doubleValue()));
        System.out.println(collect4);

        List<String> emps = employeeList.stream().filter(m -> m.getSalary().doubleValue() > 98000).map(Employee::getEmpName).toList();
        System.out.println(emps);

        Map<String, List<Employee>> data = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(data);

        Map<String, Long> data2 = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(data2);

        Map<String, Double> data3 = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(m -> m.getSalary().doubleValue())));
        System.out.println(data3);

        Optional<Employee> empsal = employeeList
                .stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::getSalary)
                .distinct().skip(1)
                .findFirst()
                .flatMap(sal-> employeeList.stream().filter(m->m.getSalary().compareTo(sal)==0).findAny());
        System.out.println("empsal: "+empsal);


        Map<Boolean,List<Employee>> empP = employeeList.stream()
                .collect(Collectors.partitioningBy(m->m.getSalary().doubleValue()>97000));
        System.out.println(empP);



        List<String> empNames = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getEmpName,Collectors.counting()))
                .entrySet().stream()
                .filter(m-> m.getValue()>1)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(empNames);


        String empData2 = employeeList.stream().map(Employee::getEmpName).collect(Collectors.joining(","));
        System.out.println(empData2);


        String sentence = "I love java streams streans java project project";

        Map<String,Long> dat = Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(m-> m,Collectors.counting()));

        System.out.println(dat);


        String emp = employeeList.stream()
                .filter(m->m.getDepartment().equals("HR"))
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(5).map(Employee::getEmpName)
                .collect(Collectors.joining(","));


        Map<Character,List<String>> d = employeeList.stream().collect(Collectors.groupingBy(e->e.getEmpName().charAt(0),Collectors.mapping(Employee::getEmpName,Collectors.toList())));
        System.out.println(d);

    }

}
