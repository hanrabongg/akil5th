package com.ak.il5th.service.code;

import com.ak.il5th.domain.code.ProductKindRepository;
import com.ak.il5th.web.dto.ProductKindSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ProductCodeService {
    private final ProductKindRepository productKindRepository;

    @Transactional
    public Long save(ProductKindSaveRequestDto requestDto) {
        return productKindRepository.save(requestDto.toEntity()).getId();
    }

}
