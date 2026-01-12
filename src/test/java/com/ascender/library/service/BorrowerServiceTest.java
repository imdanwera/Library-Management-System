package com.ascender.library.service;

import com.ascender.library.dto.BorrowerDto;
import com.ascender.library.entity.Borrower;
import com.ascender.library.repository.BorrowerRepository;
import com.ascender.library.service.impl.BorrowerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BorrowerServiceTest {

    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private BorrowerServiceImpl borrowerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterBorrower() {
        BorrowerDto dto = BorrowerDto.builder().name("John").email("john@example.com").build();
        Borrower borrower = Borrower.builder().id(1L).name("John").email("john@example.com").build();
        when(borrowerRepository.save(any(Borrower.class))).thenReturn(borrower);

        BorrowerDto result = borrowerService.registerBorrower(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }
}
