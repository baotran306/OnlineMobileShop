package com.example.phonestore.service;

import com.example.phonestore.object.*;
import com.example.phonestore.object.PostColor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhoneService {
    public List<Phone> getListPhone();
    public List<Color> getListColor();
    public List<Brand> getListBrand();
    public void savePhone(PhonePost phone);
    public void updatePhone(PhonePut phone);
    public Page<Phone> findPaginated(Pageable pageable);
    public Page<Brand> findPaginatedBrands(Pageable pageable);
    public Page<Color> findPaginatedColors(Pageable pageable);
    public void postColor(PostColor color);
    public void postBrand(PostBrand brand);
}
