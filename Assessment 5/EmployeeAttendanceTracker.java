import java.time.*;
import java.util.*;
import java.util.stream.Collector;

class Employee {
    private String name;
    private List<Attendance> attendanceRecords;

    public Employee(String name) {
        this.name = name;
        this.attendanceRecords = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Attendance> getAttendanceRecords() {
        return attendanceRecords;
    }

    
    public void addAttendance(Attendance attendance) {
        attendanceRecords.add(attendance);
    }
}

class Attendance {
    private LocalDate date;
    private LocalTime clockInTime;
    private LocalTime clockOutTime;

    public Attendance(LocalDate date, LocalTime clockInTime, LocalTime clockOutTime) {
        this.date = date;
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getClockInTime() {
        return clockInTime;
    }

    public LocalTime getClockOutTime() {
        return clockOutTime;
    }

    public Duration getWorkedHours() {
        return Duration.between(clockInTime, clockOutTime);
    }
}

public class EmployeeAttendanceTracker {
    public static void main(String[] args) {
       
        Employee emp1 = new Employee("Alice");
        Employee emp2 = new Employee("Bob");

        
        emp1.addAttendance(new Attendance(LocalDate.of(2025, 4, 1), LocalTime.of(9, 0), LocalTime.of(17, 30)));
        emp1.addAttendance(new Attendance(LocalDate.of(2025, 4, 2), LocalTime.of(9, 15), LocalTime.of(17, 45)));
        emp2.addAttendance(new Attendance(LocalDate.of(2025, 4, 1), LocalTime.of(8, 30), LocalTime.of(17, 0)));
        emp2.addAttendance(new Attendance(LocalDate.of(2025, 4, 2), LocalTime.of(9, 0), LocalTime.of(18, 0)));

        
        List<Employee> employees = Arrays.asList(emp1, emp2);

        
        employees.forEach(employee -> {
            System.out.println("Attendance record for: " + employee.getName());

            
            Optional<Duration> totalWorkedHours = employee.getAttendanceRecords().stream()
                    .map(Attendance::getWorkedHours)
                    .reduce(Duration::plus);

           
            totalWorkedHours.ifPresentOrElse(
                    duration -> System.out.println("Total worked hours: " + duration.toHours() + " hours"),
                    () -> System.out.println("No attendance records available.")
            );

            
            System.out.println("Attendance details:");
            employee.getAttendanceRecords().forEach(attendance -> {
                System.out.println("Date: " + attendance.getDate() + ", Clock In: " + attendance.getClockInTime() +
                        ", Clock Out: " + attendance.getClockOutTime() +
                        ", Worked Hours: " + attendance.getWorkedHours().toHours() + " hours");
            });
            System.out.println("-------------------------");
        });

      
        double averageWorkedHours = employees.stream()
                .flatMap(employee -> employee.getAttendanceRecords().stream())
                .map(Attendance::getWorkedHours)
                .mapToLong(Duration::toHours)
                .average()
                .orElse(0.0);

        System.out.println("Average worked hours across all employees: " + averageWorkedHours + " hours");
    }
}
