import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FindAverageSalaryApp {

	public void findAverageSalary(List<Employee> employees) {

		List<String> uniqueLocs = findLocations(employees);

		for (String loc : uniqueLocs) {
			List<Employee> locEmps = employees.stream().filter(predicate -> predicate.getOfficeLocation().equals(loc))
					.collect(Collectors.toList());
			Map<String, Double> avgSalaryOfDepartments = locEmps.stream().collect(
					Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
			Set<Map.Entry<String, Double>> entrySet = avgSalaryOfDepartments.entrySet();
			for (Map.Entry<String, Double> entry : entrySet) {
				System.out.println(loc + " --> " + entry.getKey() + " --> " + entry.getValue());
			}

		}
	}

	private List findLocations(List<Employee> employees) {
		List locs = new ArrayList<String>();
		for (Employee emp : employees) {
			if (!locs.contains(emp.getOfficeLocation())) {
				locs.add(emp.getOfficeLocation());
			}
		}
		return locs;
	}

}
