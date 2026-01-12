package com.ascender.library.controller;

import com.ascender.library.dto.ApiResponse;
import com.ascender.library.dto.BorrowerDto;
import com.ascender.library.service.BorrowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrowers")
@RequiredArgsConstructor
public class BorrowerController {

    private final BorrowerService borrowerService;

    @Operation(summary = "Register a Borrower ")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Broorwer registered successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Borrower not found")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<BorrowerDto>> addBorrower(@Valid @RequestBody BorrowerDto request) {
        BorrowerDto saved = borrowerService.registerBorrower(request);
        return ResponseEntity.ok(ApiResponse.success("Borrower registered successfully", saved));
    }
}
