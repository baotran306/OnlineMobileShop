package com.example.phonestore.AdminController;

import com.example.phonestore.object.Phone;
import com.example.phonestore.service.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class controller {
    private PhoneService phoneService;

    public controller(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @RequestMapping("/phone")
    public String test(ModelMap modelMap){
        modelMap.addAttribute("message","measdas");
        List<Phone> phones = phoneService.getListPhone();
        modelMap.addAttribute("phones",phones);
        return "product/phone";
    }
    @RequestMapping("/uploadPhone")
    public String showFormForAdd(){

        return "product/form-upload-phone";
    }

    @RequestMapping("/brand")
    public String showBrand(){
        return "product/brand";
    }
    @RequestMapping("/color")
    public String showColor(){
        return "product/color";
    }

}
