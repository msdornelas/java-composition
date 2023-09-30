package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Worker;
import entities.enums.WorkerLevel;
import entities.Department;
import entities.HourContract;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.print("Enter department's name: ");
		String departmentName = sc.nextLine();

		System.out.println("Enter worker's data:");

		System.out.print("Name: ");
		String workerName = sc.nextLine();

		System.out.print("Level: ");
		String workerLevel = sc.nextLine();

		System.out.print("Base salary: ");
		double workerBaseSalary = sc.nextDouble();

		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), workerBaseSalary, new Department(departmentName));

		System.out.print("How many contracts to this worker? ");
		int numberOfContracts = sc.nextInt();

		for (int i = 1; i <= numberOfContracts; i++) {
			System.out.println("Enter contract #" + i + " data: ");

			System.out.print("Date (DD/MM/YYYY): ");
			Date contractDate = sdf.parse(sc.next());

			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();

			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();

			HourContract contract = new HourContract(contractDate, valuePerHour, hours);

			worker.addContract(contract);
		}

		System.out.println();

		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		String[] monthAndYear = sc.next().split("/");

		int incomeMonth = Integer.parseInt(monthAndYear[0]);
		int incomeYear = Integer.parseInt(monthAndYear[1]);

		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " + incomeMonth + "/" + incomeYear + ": " + String.format("%.2f", worker.income(incomeYear, incomeMonth)));

		sc.close();
	}

}
