package com.example.learning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.learning.model.Expense;
import com.example.learning.repository.ExpenseRepository;

@Service
public class ExpenseService {

	private final ExpenseRepository expenseRepository;

	public ExpenseService(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	public void addExpense(Expense expense) {
		expenseRepository.insert(expense);
	}

	public void updateExpense(Expense expense) {
		Expense findExpense = expenseRepository.findById(expense.getId()).orElseThrow(
				() -> new RuntimeException(String.format("Can not find Expense By Id: %s", expense.getId())));
		findExpense.setExpenseName(expense.getExpenseName());
		findExpense.setExpenseCategory(expense.getExpenseCategory());
		findExpense.setExpenseAmount(expense.getExpenseAmount());

		expenseRepository.save(findExpense);
	}

	public List<Expense> getAllExpense() {
		return expenseRepository.findAll();
	}

	public Expense getExpenseByName(String name) {
		return expenseRepository.findByName(name)
				.orElseThrow(() -> new RuntimeException(String.format("Can not find Expense By Name: %s", name)));
	}

	public void deleteExpense(String id) {
		expenseRepository.deleteById(id);
	}
}
