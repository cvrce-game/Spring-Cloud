package com.example.learning.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.learning.model.Expense;
import com.example.learning.service.ExpenseService;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

	private final ExpenseService expenseService;

	public ExpenseController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@PostMapping("/addExpense")
	public ResponseEntity<String> addExpense(@RequestBody Expense expense) {
		expenseService.addExpense(expense);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/updateExpense")
	public ResponseEntity<Boolean> updateExpense(@RequestBody Expense expense) {
		expenseService.updateExpense(expense);

		return ResponseEntity.status(HttpStatus.OK).body(true);
	}

	@GetMapping("/getAllExpense")
	public ResponseEntity<List<Expense>> getAllExpense() {
		System.out.println("Entered ...");
		return ResponseEntity.ok(expenseService.getAllExpense());
	}

	@GetMapping("/getExpenseByName/{name}")
	public ResponseEntity<Expense> getByName(@PathVariable("name") String name) {
	    Expense expense = expenseService.getExpenseByName(name);
	    return ResponseEntity.ok(expense);
	}

	@DeleteMapping("/deleteExpense/{id}")
	public ResponseEntity<Boolean> deleteExpense(@PathVariable("id") String id) {
		expenseService.deleteExpense(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
	}
}
