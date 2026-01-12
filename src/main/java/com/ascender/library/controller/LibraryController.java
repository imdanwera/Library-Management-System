package com.ascender.library.controller;

import com.ascender.library.dto.ApiResponse;
import com.ascender.library.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @Operation(summary = "Borrow a Book ")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Book borrowed successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Book or Borrower not found")
    })
    @PostMapping("/borrow")
    public ResponseEntity<ApiResponse<Void>> borrowBook(@RequestParam Long borrowerId, @RequestParam Long bookId) {
        log.info("Book Borrowing - starting");
        libraryService.borrowBook(borrowerId, bookId);
        return ResponseEntity.ok(ApiResponse.success("Book borrowed successfully",null));
    }

    @Operation(summary = "Return a Book ")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Book returned successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Book or Borrower not found")
    })
    @PostMapping("/return")
    public ResponseEntity<ApiResponse<Void>> returnBook(@RequestParam Long borrowerId, @RequestParam Long bookId) {
        libraryService.returnBook(borrowerId, bookId);
        return ResponseEntity.ok(ApiResponse.success("Book returned successfully",null));
    }
}
