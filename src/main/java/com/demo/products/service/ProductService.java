package com.demo.products.service;

import com.demo.products.converter.DozerConverter;
import com.demo.products.data.ProductDTO;
import com.demo.products.exception.ResourceNotFoundException;
import com.demo.products.model.Product;
import com.demo.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return DozerConverter.parseListObjects(productRepository.findAll(), ProductDTO.class);
    }

    public void delete(final Long id) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado"));
        productRepository.delete(entity);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(final Long id) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado"));
        return DozerConverter.parseObject(entity, ProductDTO.class);
    }

    public ProductDTO update(final Long id, final ProductDTO dto) {
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado"));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());

        return DozerConverter.parseObject(productRepository.save(entity), ProductDTO.class);
    }

    public ProductDTO create(ProductDTO dto) {
        Product entity = DozerConverter.parseObject(dto, Product.class);
        return DozerConverter.parseObject(productRepository.save(entity), ProductDTO.class);
    }

}
