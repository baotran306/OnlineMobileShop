package com.example.phonestore.AdminController;

import com.example.phonestore.object.*;
import com.example.phonestore.object.PostColor;
import com.example.phonestore.object.user.ChangePassword;
import com.example.phonestore.object.user.PostStaff;
import com.example.phonestore.object.user.ResponseLoginMessage;
import com.example.phonestore.object.user.User;
import com.example.phonestore.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin")
public class controller {
    private PhoneService phoneService;
    private StaffService staffService;
    private CustomerService customerService;
    private OrderService orderService;
    private LoginService loginService;
    private String message ="";

    public controller(PhoneService phoneService,StaffService staffService,
                      CustomerService customerService,OrderService orderService,LoginService loginService) {
        this.staffService = staffService;
        this.phoneService = phoneService;
        this.customerService = customerService;
        this.orderService = orderService;
        this.loginService = loginService;
    }


    @GetMapping("/phone")
    public String findPaginatedPhone(ModelMap theModelMap,@RequestParam("page") Optional<Integer> page){
        Optional<Integer> size = Optional.of(5);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Phone> pagePhone = phoneService.findPaginated(PageRequest.of(currentPage-1,pageSize));
        theModelMap.addAttribute("phonePage",pagePhone);
        int totalPages = pagePhone.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            theModelMap.addAttribute("pageNumbers", pageNumbers);
        }
        List<Phone> phones = phoneService.getListPhone();
        theModelMap.addAttribute("phones", phones);
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
        String uploadDir = "I:\\BACKUP\\backup\\OnlineMobileShop-APIWebService\\Image";
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
    public String showBrand(ModelMap theModelMap,@RequestParam("page") Optional<Integer> page) {
        Optional<Integer> size = Optional.of(10);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Brand> brandPage = phoneService.findPaginatedBrands(PageRequest.of(currentPage-1,pageSize));
        theModelMap.addAttribute("brandPage",brandPage);
        int totalPages = brandPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            theModelMap.addAttribute("pageNumbers", pageNumbers);
        }
        theModelMap.addAttribute("brands",phoneService.getListBrand());
        theModelMap.addAttribute("postBrand",new PostBrand());

        return "product/brand";
    }

    @PostMapping("/brand/save")
    public String saveBrand(@ModelAttribute("postBrand")PostBrand postBrand){
        phoneService.postBrand(postBrand);
        return "redirect:/admin/brand";
    }

    @RequestMapping("/color")
    public String showColor(ModelMap theModelMap,@RequestParam("page") Optional<Integer> page) {
        Optional<Integer> size = Optional.of(10);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Color> colorPage = phoneService.findPaginatedColors(PageRequest.of(currentPage-1,pageSize));
        theModelMap.addAttribute("colorPage",colorPage);
        int totalPages = colorPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            theModelMap.addAttribute("pageNumbers", pageNumbers);
        }
        theModelMap.addAttribute("postColor",new PostColor());
        theModelMap.addAttribute("colors",phoneService.getListColor());
        return "product/color";
    }

    @PostMapping("/color/save")
    public String saveColor(@ModelAttribute("postColor")PostColor postColor){
        phoneService.postColor(postColor);
        return "redirect:/admin/color";
    }

    @GetMapping("/staff")
    public String findPaginatedStaff(ModelMap theModelMap,@RequestParam("page") Optional<Integer> page){
        Optional<Integer> size = Optional.of(10);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<GetStaff> pageStaff = staffService.findPaginated(PageRequest.of(currentPage-1,pageSize));
        theModelMap.addAttribute("pageStaff",pageStaff);
        int totalPages = pageStaff.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            theModelMap.addAttribute("pageNumbers", pageNumbers);
        }
        List<GetStaff> staffs = new ArrayList<>();
        for (GetStaff staff:staffService.getStaffList()){
            if(staff.getIsDeleted()==0){
                staffs.add(staff);
            }
        }
        theModelMap.addAttribute("staffs", staffs);
        return "staff/staff";
    }

    @GetMapping(value = "/staff/upload-staff")
    public String showFormForUpLoad(ModelMap theModelMap){
        theModelMap.addAttribute("staff",new StaffUpload());
        return "staff/upload-staff";
    }
    @PostMapping(value = "/staff/upload-staff/save")
    public String saveUploadStaff(@ModelAttribute("staff") StaffUpload staff){
        System.out.println(staff.toString());
        staffService.postStaff(staff);
        return "redirect:/admin/staff";
    }

    @GetMapping(value = "staff/update")
    public String showFormFroUpdate(@RequestParam("idStaff") String theId,ModelMap theModelMap){
        GetStaff staff = staffService.getStaffById(theId);
        theModelMap.addAttribute("staff",staff);
        return "staff/update-staff";
    }
    @PostMapping("/staff/update/save")
    public String saveUpdateStaff(@ModelAttribute("staff") GetStaff staff){
        StaffUpdate staffUpdate = new StaffUpdate();
        staffUpdate.setId(staff.getId());
        staffUpdate.setIdRole(staff.getRoleId());
        staffUpdate.setSalary(staff.getSalary());
        staffService.updateStaff(staffUpdate);
        return "redirect:/admin/staff";
    }

    @GetMapping(value = "staff/delete")
    public String deleteStaff(@RequestParam("idStaff") String theId,ModelMap theModelMap){
        staffService.deleteStaffById(theId);
        return "redirect:/admin/staff";
    }
    @GetMapping(value = "staff/reset")
    public String resetPassStaff(@RequestParam("username") String username){
        staffService.resetPassword(new User(username,"123456"));
        return "redirect:/admin/staff";
    }

    @GetMapping(value = "/customer")
    public String showCustomer(ModelMap modelMap,@RequestParam("page") Optional<Integer> page){

        Optional<Integer> size = Optional.of(10);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<GetCustomer> customerPage = customerService.findPaginatedCustomer(PageRequest.of(currentPage-1,pageSize));
        modelMap.addAttribute("customerPage",customerPage);
        int totalPages = customerPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        List<GetCustomer> customers = customerService.getListCustomer();
        modelMap.addAttribute("customers",customers);
        return "customer/customer";
    }

    @GetMapping(value = "/customer/history")
    public String showHistory(ModelMap theModelMap,@RequestParam("idCustomer") String theId){
        theModelMap.addAttribute("order",customerService.getListHistory(theId).getCustomerOrder());
        return "customer/history-view";
    }
    @GetMapping(value="/order")
    public String showOrder(ModelMap modelMap){
        modelMap.addAttribute("date",new SearchDateOrder());
        modelMap.addAttribute("customerOrders",new CustomerOrder());
        return "order/order";
    }

    @GetMapping("/order/search")
    public String showOrderSearch(ModelMap modelMap,
                                  @RequestParam("from-date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate
            ,@RequestParam("to-date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate){
        List<CustomerOrder> customerOrders = orderService.getListOrder(new SearchDateOrder(fromDate.toString(),toDate.toString()));
        modelMap.addAttribute("date",new SearchDateOrder());
        modelMap.addAttribute("customerOrders",customerOrders);
        return "order/order";
    }
    @GetMapping("/login")
    public String showFormLogin(ModelMap theModelMap,HttpSession session){
        theModelMap.addAttribute("account",new User());
        theModelMap.addAttribute("message",new ResponseLoginMessage());
        session.setAttribute("message","");
        return "login/login";
    }
    @GetMapping("/staff/change-password")
    public String changePassword(ModelMap theModelMap,HttpSession session){
        theModelMap.addAttribute("changePassword",new ChangePassword());
        theModelMap.addAttribute("message",message);
        message="";
        return "staff/change-password";
    }

    @PostMapping("/staff/change-password/save")
    public String savePassword(@ModelAttribute("changePassword")ChangePassword changePassword,HttpSession session){
        ResponseLoginMessage responseLoginMessage = (ResponseLoginMessage) session.getAttribute("user");
        changePassword.setUsername(responseLoginMessage.getStaffInfo().getUsername());
        ResponseMessage responseMessage = staffService.changePassword(changePassword);
        message= responseMessage.getInfo();
        return "redirect:/admin/staff/change-password";
    }


    @GetMapping("/staff/profile")
    public String changeInfo(ModelMap theModelMap,HttpSession session){
        ResponseLoginMessage responseLoginMessage =
                (ResponseLoginMessage) session.getAttribute("user");
        theModelMap.addAttribute("profile",staffService.getProfile(responseLoginMessage.getStaffInfo().getId()));
        theModelMap.addAttribute("message",message);
        message="";
        return "staff/profile-staff";
    }
    @PostMapping("/staff/profile/save")
    public String saveProfile(@ModelAttribute("profile")PostStaff postStaff,ModelMap theModelMap){
        ResponseMessage responseMessage=staffService.updateProfile(postStaff);
        theModelMap.addAttribute("message",responseMessage.getInfo());
        message= responseMessage.getInfo();
        return "redirect:/admin/staff/profile";
    }

    @PostMapping("/login-fail")
    public String login(@ModelAttribute("account")User user ,HttpSession session,ModelMap theModelMap){
        ResponseLoginMessage responseLoginMessage;
        responseLoginMessage=loginService.getUser(user);
        if(responseLoginMessage.getResult()==false) {
            theModelMap.addAttribute("message", responseLoginMessage);
            return "login/login";
        }
        session.setAttribute("user",responseLoginMessage);
       return "redirect:/admin/order";
    }
    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin/login";
    }
}
