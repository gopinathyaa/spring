package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Example;
import com.example.entity.Example2;
import com.example.reppo.UserRepository;
import com.example.reppo.Useritemreppo;
import com.example.service.Pageservice;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.val;

@RestController
@Validated
public class Usercontroller {

	@Autowired
	UserRepository reppoRepository;

	@Autowired
	Useritemreppo Useritemreppo;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	Pageservice Pageservice;

	@PostMapping("/hello")
	public String getdetails(@RequestBody Example exam) {

		exam.setYear(calyear(exam.getAge()));
		Example example = reppoRepository.save(exam);

		for (Example2 example22 : exam.getExample2()) {

			example22.setExample(example);

			Example2 example2 = new Example2();

			example2.setPaidamount(example22.getPaidamount());

			Useritemreppo.save(example2);
		}

		return "hai";
	}

	@PostMapping("/hai")
	public String getpayment(@RequestBody Example exam) {

		Example example = reppoRepository.findById(exam.getId()).get();

		Example2 example1 = Useritemreppo.findById(exam.getId()).get();

		Example2 example22 = new Example2();

		float f = example.getTotalamount();

		float f1 = example1.getPaidamount();

		System.out.println(f1);

		float h = f - f1;

		System.out.println(h);

		example22.setBalanceamount(h);

		Useritemreppo.savebyid(example22.getBalanceamount(), exam.getId());

		return "done";
	}

//  @PostMapping("/done")
//   public String  dataupdate(@RequestBody Example exam) {
//	  
//	  Example example=new Example();
//	  
//	  example.setAge(exam.getAge());
//	  example.setUsername(exam.getUsername());
//	  example.setId(exam.getId());
//	  
//      reppoRepository.savebyidd(example.getAge(),example.getUsername(),example.getId());
//      
//      Example example234= reppoRepository.findById(exam.getId()).get();
//   
//	  
//	  for (Example2  example2 : exam.getExample2()) {
//		  
//		  Example2 example22=new Example2();
//		  
////		  Example example24 = reppoRepository.findById(example2.getExample().getId()).orElse(null);
//		  example22.setExample(example234);
//		  example22.setBalanceamount(example2.getBalanceamount());
//		  example22.setPaidamount(example2.getPaidamount());
//		  
//		  
//		 Example2 example23=  Useritemreppo.save(example22);
//		   
//		   
////		 Useritemreppo.findById(example22.getExample().getId()).get();
//		  
//		  
//		  
////		  Example2 example23456= Useritemreppo.findById(example23.getExample().getId()).get();
////		  
////		  example22.setPaidamount(example2.getPaidamount()+example23456.getPaidamount());
////		  
////		  System.out.println(example22.getPaidamount());
////		  
////		  Useritemreppo.savebyiddd(example22.getPaidamount(),example234.getId());	
//		  
////		  Example2 example2345= Useritemreppo.findById(example23.getPaidid()).get();
//		  
//		 
//	float f= example23.getPaidamount();
//	
//	System.out.println(f);
//		 
//	float f1= example234.getTotalamount();
//	System.out.println(f1);
//	
//	float f2=f1-f;
//	System.out.println(f2);
//	
//	example22.setBalanceamount(f2);
//	
//    Useritemreppo.savebyid(example22.getBalanceamount(),exam.getId());
//		  
//   }	    
//	return "bad bad";	  
//  }

	@PostMapping("/update")
	@Transactional
	public String updatedata(@Valid @RequestBody Example exam) {

		Example exam2 = new Example();

		exam2.setId(exam.getId());

		List<Example2> example3 = Useritemreppo.findByExample(exam2);

		for (Example2 example2 : example3) {

			for (Example2 example5 : exam.getExample2()) {

				float f12 = example5.getPaidamount();
				float f13 = example2.getBalanceamount();

				if (f13 < f12) {

					return "To pay valid balance amount";
				}

			}
		}

		exam2.setAge(exam.getAge());
		exam2.setPassword(exam.getPassword());
		exam2.setUsername(exam.getUsername());
		exam2.setId(exam.getId());
		exam2.setTotalamount(exam.getTotalamount());
		reppoRepository.savebyidd(exam2.getAge(), exam2.getPassword(), exam2.getUsername(), exam2.getTotalamount(),
				exam2.getId());

		Example example44 = reppoRepository.findById(exam.getId()).get();
		List<Example2> example = Useritemreppo.findByExample(example44);

		for (Example2 example2 : example) {
			float f = example2.getPaidamount();

			System.out.println(f);

			for (Example2 example21 : exam.getExample2()) {
				Example2 exam12 = new Example2();

				exam12.setPaidamount(f + example21.getPaidamount());

				exam2.setId(exam.getId());

				Useritemreppo.savebyiddd(exam12.getPaidamount(), exam2);
				entityManager.flush();
				entityManager.clear();

				List<Example2> example1 = Useritemreppo.findByExample(exam2);
				System.out.println(example1.get(0).getPaidamount());
				for (Example2 example22 : example1) {

					float b = example22.getPaidamount();

					System.out.println(b);

					Example example234 = reppoRepository.findById(exam.getId()).get();

					float h = example234.getTotalamount();
					System.out.println(h);

					float y = h - b;

					System.out.println(y);

					exam12.setBalanceamount(y);

					Useritemreppo.savekp(exam12.getBalanceamount(), exam2);
				}
			}
		}

		return "gopi";

	}

	@GetMapping("/sort")
	public List<Example> sortbaseduponsomefields(String amount) {
		return reppoRepository.findAll(Sort.by(Sort.Direction.ASC, amount));
	}

	@GetMapping("/pagination/{offset}/{pagesize}/{fields}")
	public Page<Example> sortproducts(@PathVariable int offset, @PathVariable int pagesize,
			@PathVariable String fields) {
		return Pageservice.paginationbaseduponsomefields(offset, pagesize, fields);
	}

	@PostMapping("/filter")
	public ResponseEntity<List<Example>> filteramount(@RequestParam(value = "amount1") float amount1,@RequestParam(value = "amount2") float amount2) {
		List<Example> examples = reppoRepository.findByTotalamount(amount1, amount2);
		System.out.println(examples.get(0).getUsername());
		return ResponseEntity.ok(examples);
	}
	
	private int calyear(Integer integer) {
		int currentYear = LocalDate.now().getYear();
		int d = currentYear - integer;
		return d;
	}
}
