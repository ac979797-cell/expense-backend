package com.example.backend;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin // 프론트에서 호출 가능하게 CORS 열어둠
public class ExpenseController {

    private final ExpenseRepository repo;

    public ExpenseController(ExpenseRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Expense> all() {
        return repo.findAll();
    }

    @PostMapping
    public Expense create(@RequestBody Expense e) {
        return repo.save(e);
    }
}
