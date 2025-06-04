package com.ascender.library.service.impl;

import com.ascender.library.dto.BorrowerDto;
import com.ascender.library.entity.Borrower;
import com.ascender.library.exception.DuplicateResourceException;
import com.ascender.library.repository.BorrowerRepository;
import com.ascender.library.service.BorrowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowerServiceImpl implements BorrowerService {

    private final BorrowerRepository borrowerRepository;

    @Override
    public BorrowerDto registerBorrower(BorrowerDto dto) {

        Optional<Borrower> borrower = borrowerRepository.findByEmail(dto.getEmail());

        if (borrower.isPresent()) {
            throw new DuplicateResourceException("A borrower with this email already exists.");
        }

        Borrower newBorrower = Borrower.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
        newBorrower = borrowerRepository.save(newBorrower) ;
        dto.setId(newBorrower.getId());
        return dto;
    }
}
