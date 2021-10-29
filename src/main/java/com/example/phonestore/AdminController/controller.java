package com.example.phonestore.AdminController;

import com.example.phonestore.object.*;
import com.example.phonestore.service.PhoneService;
import com.example.phonestore.service.StaffService;
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
    private StaffService staffService;

    public controller(PhoneService phoneService,StaffService staffService) {
        this.staffService = staffService;
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
        PhonePut phone = new PhonePut();
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


    private void uploadFileImage(String fileName,MultipartFile multipartFile) throws IOException {
        String uploadDir = "I:\\BACKUP\\backup\\mobile-shop-APIWebService\\Image";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        InputStream inputStream = multipartFile.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    @PostMapping(value = "/phone/save")
    public String savePhone(@ModelAttribute("phone") PhonePut phone,
                            RedirectAttributes redirectAttributes,
                            @RequestParam("fileImage") MultipartFile multipartFile,
                            BindingResult result) {

        try {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            if (phone.getId() == 0) {
                PhonePost phonePost = new PhonePost();
                phonePost.setImage(fileName);
                phonePost.setBrand(phone.getBrand());
                phonePost.setColor(phone.getColor());
                phonePost.setDescription(phone.getDescription());
                phonePost.setPrice(phone.getPrice());
                phonePost.setQuantity(phone.getQuantity());
                phonePost.setPhoneName(phone.getPhoneName());
                phone.setImage(fileName);
                uploadFileImage(fileName,multipartFile);
                phoneService.savePhone(phonePost);
            } else {
                if (!fileName.equals("")) {
                    phone.setImage(fileName);
                    uploadFileImage(fileName,multipartFile);
                }

                phoneService.updatePhone(phone);
            }
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
    @GetMapping("/staff")
    public String showStaff(ModelMap theModelMap){
        List<GetStaff> staffs = staffService.getStaffList();
        theModelMap.addAttribute("staffs",staffs);
        return "staff/staff";
    }

    @GetMapping(value = "/staff/upload-staff")
    public String showFormForUpLoad(){
        return "staff/upload-staff";
    }
}
