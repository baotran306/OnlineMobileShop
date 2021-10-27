package com.example.phonestore.AdminController;

import com.example.phonestore.object.Brand;
import com.example.phonestore.object.Color;
import com.example.phonestore.object.Phone;
import com.example.phonestore.object.PhonePost;
import com.example.phonestore.service.PhoneService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class controller {
    private PhoneService phoneService;

    public controller(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @RequestMapping("/phone")
    public String test(ModelMap modelMap) {
        modelMap.addAttribute("message", "measdas");
        List<Phone> phones = phoneService.getListPhone();
        modelMap.addAttribute("phones", phones);
        return "product/phone";
    }

    @GetMapping("/updatePhone")
    public String showFormForUpdate(ModelMap modelMap, @RequestParam("idPhone") int theId) {
        List<Phone> phones = phoneService.getListPhone();
        List<Color> colors = phoneService.getListColor();
        List<Brand> brands = phoneService.getListBrand();
        PhonePost phone = new PhonePost();
        for (int i = 0; i < phones.size(); i++) {
            if (phones.get(i).getId() == theId) {
                phone.setImage(phones.get(i).getImage());
                phone.setBrand(phones.get(i).getIdBrand());
                phone.setColor(phones.get(i).getIdColor());
                phone.setDescription(phones.get(i).getDescription());
                phone.setId(phones.get(i).getId());
                phone.setPrice(phones.get(i).getPrice());
                phone.setQuantity(phones.get(i).getQuantity());
                phone.setPhoneName(phones.get(i).getPhoneName());
            }
        }
        modelMap.addAttribute("phone", phone);
        modelMap.addAttribute("colors", colors);
        modelMap.addAttribute("brands", brands);
        return "product/form-upload-phone";
    }

    @GetMapping("/uploadPhone")
    public String showFormForAdd(ModelMap modelMap) {
        Phone phone = new Phone();
        List<Color> colors = phoneService.getListColor();
        List<Brand> brands = phoneService.getListBrand();
        modelMap.addAttribute("phone", phone);
        modelMap.addAttribute("colors", colors);
        modelMap.addAttribute("brands", brands);
        return "product/form-upload-phone";
    }

    @PostMapping(value = "/phone/save")
    public String savePhone(@ModelAttribute("phone") PhonePost phone,
                            RedirectAttributes redirectAttributes,
                            @RequestParam("fileImage") MultipartFile multipartFile,
                            BindingResult result) {
        try {

            System.out.println(phone.getImage());

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            phone.setImage(fileName);
            if (result.hasErrors()) {
                return "product/form-upload-phone";
            }
            String uploadDir = "I:\\BACKUP\\backup\\mobile-shop-APIWebService\\Image";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//                phoneService.savePhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/phone";
    }

    @RequestMapping("/brand")
    public String showBrand() {
        return "product/brand";
    }

    @RequestMapping("/color")
    public String showColor() {
        return "product/color";
    }

}
