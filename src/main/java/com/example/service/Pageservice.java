package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entity.Example;
import com.example.reppo.UserRepository;


@Service
public class Pageservice {
	
	
	@Autowired
	UserRepository repository;
	
	  public Page<Example> paginationbaseduponsomefields(int offset,int pagesize,String fields){			 
			return   repository.findAll(PageRequest.of(offset, pagesize).withSort(Sort.by(Sort.Direction.ASC,fields)));  
		  }  
}
