package com.example.phonestore.service;

import com.example.phonestore.DAO.PhoneDAO;
import com.example.phonestore.object.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PhoneServiceImp implements PhoneService {
    @Autowired
    PhoneDAO phoneDAO;
    @Override
    public List<Phone> getListPhone() {
        return phoneDAO.getListPhone();
    }

    @Override
    public List<Color> getListColor() {
        return phoneDAO.getListColor();
    }

    @Override
    public List<Brand> getListBrand() {
        return phoneDAO.getListBrand();
    }

    @Override
    public void savePhone(PhonePost phone) {
        phoneDAO.savePhone(phone);
    }

    @Override
    public void updatePhone(PhonePut phone) {
        phoneDAO.updatePhone(phone);
    }

    @Override
    public Page<Phone> findPaginated(Pageable pageable) {
        List<Phone> phones = phoneDAO.getListPhone();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Phone> list;

        if (phones.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, phones.size());
            list = phones.subList(startItem, toIndex);
        }

        Page<Phone> phonePage
                = new PageImpl<Phone>(list, PageRequest.of(currentPage, pageSize), phones.size());

        return phonePage;
    }

    @Override
    public Page<Brand> findPaginatedBrands(Pageable pageable) {
        List<Brand> brands = phoneDAO.getListBrand();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Brand> list;

        if (brands.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, brands.size());
            list = brands.subList(startItem, toIndex);
        }

        Page<Brand> brandPage
                = new PageImpl<Brand>(list, PageRequest.of(currentPage, pageSize), brands.size());

        return brandPage;
    }

    @Override
    public Page<Color> findPaginatedColors(Pageable pageable) {
        List<Color> colors = phoneDAO.getListColor();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Color> list;

        if (colors.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, colors.size());
            list = colors.subList(startItem, toIndex);
        }

        Page<Color> colorPage
                = new PageImpl<Color>(list, PageRequest.of(currentPage, pageSize), colors.size());

        return colorPage;
    }
}
