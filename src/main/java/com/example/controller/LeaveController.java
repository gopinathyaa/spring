package com.example.controller;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Employee;
import com.example.entity.EmployeeSalary;
import com.example.entity.LeaveRequest;
import com.example.reppo.Addemployee;
import com.example.reppo.Empsalary;
import com.example.reppo.LeaveReq;

@RestController
public class LeaveController {

	@Autowired
	Addemployee addemployee;

	@Autowired
	LeaveReq req;
	
	@Autowired
	Empsalary salary1;
	
	

	@PostMapping("/addemp")
	public String addemployee(@RequestBody Employee employee) {
		Employee b = null;
	 
		LeaveRequest request = new LeaveRequest();
		Employee employee2 = new Employee();
		employee2.setEmpname(employee.getEmpname());
		employee2.setLeaveBalance(employee.getLeaveBalance());
		Employee employee12 = addemployee.save(employee2);

		for (LeaveRequest leave : employee.getLeaveRequests()) {
			request.setEmployee(employee12);
			request.setFromDate(leave.getFromDate());
			request.setToDate(leave.getToDate());
			request.setReason(leave.getReason());
			request.setLeaveDays(dateCalculate(leave.getFromDate(), leave.getToDate()));
			LeaveRequest leaveRequest = req.save(request);
			b = leaveRequest.getEmployee();
		}
		
		Employee employee3 = addemployee.findById(employee12.getEmployeeid()).get();
		Long g = employee3.getLeaveBalance();
		System.out.println(g);

		List<LeaveRequest> leaveRequests = req.findByEmployee(employee12);
		for (LeaveRequest leaveRequest : leaveRequests) {
			Employee employee34 = new Employee();
			Long o = leaveRequest.getLeaveDays();
			System.out.println(o);
			long h = g - o;
			employee34.setLeaveBalance(h);	
			System.out.println(b);
			System.out.println(employee34.getLeaveBalance() +"yes");		 
			addemployee.savekt(employee34.getLeaveBalance(),b.getEmployeeid());	
		}	
		return "done";
	}
	
	
	
	@PostMapping("/updateemp")
	public String updateemployee(@RequestBody LeaveRequest request1) {
		LeaveRequest request = new LeaveRequest();
		request.setReason(request1.getReason());
		request.setFromDate(request1.getFromDate());
		request.setToDate(request1.getToDate());
		request.setLeaveDays(dateCalculate(request1.getFromDate(), request1.getToDate()));
		request.setEmployee(request1.getEmployee());
		LeaveRequest leaveRequest = req.save(request);
		
        Long	k = leaveRequest.getEmployee().getEmployeeid();
		
		Employee employee3 = addemployee.findById(leaveRequest.getEmployee().getEmployeeid()).get() ;
		Long g = employee3.getLeaveBalance();
		System.out.println(g);

		List<LeaveRequest> leaveRequests = req.findByEmployee(employee3);
		for (LeaveRequest leaveRequest1 : leaveRequests) {
			Employee employee34 = new Employee();
			Long o = leaveRequest1.getLeaveDays();
			System.out.println(o);
			long h = g - o;
			employee34.setLeaveBalance(h);	
			System.out.println(k);
			System.out.println(employee34.getLeaveBalance() +"yes");		 
			addemployee.savekt(employee34.getLeaveBalance(),k);	
		}	
		return "success";	
	}
	
	
@PostMapping("/addsal")	
public String addsalary(@RequestBody EmployeeSalary employeeSalary) {	
	EmployeeSalary employeeSalary2=new EmployeeSalary();
	
	employeeSalary2.setIncrementsalary(employeeSalary.getIncrementsalary());
	employeeSalary2.setSalaryamo(employeeSalary.getSalaryamo()+employeeSalary.getIncrementsalary());
	employeeSalary2.setIncrementsalaryDate( employeeSalary.getIncrementsalaryDate());
	employeeSalary2.setEmployee(employeeSalary.getEmployee());
	salary1.save(employeeSalary2);
	return "good";
}

@GetMapping("/test")
public String addsalary( ) {	 
	return "good";
}

@GetMapping("/getleavelistafterincrement")
 public  ResponseEntity<List<EmployeeSalary>> getlist(@RequestBody EmployeeSalary salary) {
	Employee employeeSalary2=addemployee.findById(salary.getEmployee().getEmployeeid()).get();
	System.out.println(salary);
    List<EmployeeSalary> salary2= salary1.findByEmployee(employeeSalary2);
    List<LeaveRequest>   salary3= req.findByEmployee(employeeSalary2);
      for (EmployeeSalary employeeSalary : salary2) {  	
    	System.out.println(employeeSalary.getIncrementsalaryDate());	 	
    	 for (LeaveRequest leaveRequest : salary3) {
			System.out.println(leaveRequest.getFromDate());
			
			Date date = leaveRequest.getFromDate();  
			LocalDate inputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			 
			System.out.println(inputDate);
			Date date1 = employeeSalary.getIncrementsalaryDate();  
						LocalDate inputDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			System.out.println(inputDate1);
			
			
			if (inputDate.isAfter(inputDate1)) {			
				 List<EmployeeSalary> salary4= salary1.findByEmployee(employeeSalary2);
				 return ResponseEntity.status(HttpStatus.OK).body(salary4);
			}
		}
	}
	return null;  	 	
}


 public long dateCalculate(Date date, Date date2 ) {	  
	        LocalDate startDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate endDate = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);     
	        int weekendDays = countWeekendDays(startDate, endDate);
	        System.out.println(weekendDays);
	        long id= daysBetween-weekendDays;       
	        return id;	                
}
 
	 public static int countWeekendDays(LocalDate startDate, LocalDate endDate) {
	        int weekendCount = 0;
	        while(!startDate.isAfter(endDate)) {
	            DayOfWeek dayOfWeek = startDate.getDayOfWeek();
	            System.out.println(dayOfWeek);
	            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
	                weekendCount++;
	            }           
	            startDate = startDate.plusDays(1);
	        }
	        return weekendCount;
	    }  
}
