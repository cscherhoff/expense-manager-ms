package com.exxeta.expensemanagerms.controller.expenseController;

import com.exxeta.expensemanagerms.service.ExpenseKafkaService;
import com.exxeta.expenseservice.dtos.ExpenseFromFrontend;
import com.exxeta.expenseservice.dtos.TransferDto;
import com.exxeta.expenseservice.services.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController()
@RequestMapping(path = "/expense/user/{userId}")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseKafkaService expenseKafkaService;
    private final ObjectMapper mapper = new ObjectMapper();

    public ExpenseController(ExpenseService expenseService, ExpenseKafkaService expenseKafkaService) {
        this.expenseService = expenseService;
        this.expenseKafkaService = expenseKafkaService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/accountName/{accountName}")
    public String postNewExpenses(@PathVariable String userId, @PathVariable String accountName,
                                 @RequestBody List<ExpenseFromFrontend> expensesFromFrontend) throws IOException {

        expensesFromFrontend.forEach(expense -> expense.setUserId(userId));
        saveAllNewExpenses(expensesFromFrontend);
        for (ExpenseFromFrontend expense: expensesFromFrontend) {
            final TransferDto transferDto = new TransferDto(userId, accountName, BigDecimal.valueOf(expense.price));
//            expenseKafkaService.sendKafkaMessage(transferDto);
        }
        return mapper.writeValueAsString(expensesFromFrontend);
    }

    private void saveAllNewExpenses(List<ExpenseFromFrontend> expenseFromFrontend) {
        try {
            expenseService.saveAllNewExpenses(expenseFromFrontend);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping
    public String getAllExpenses(@PathVariable String userId) {
        try {
            return mapper.writeValueAsString(expenseService.getAllExpenses(userId));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
