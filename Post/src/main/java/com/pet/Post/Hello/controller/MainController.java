package com.pet.Post.Hello.controller;

import com.pet.Post.Hello.domain.Busket;
import com.pet.Post.Hello.domain.Staff;
import com.pet.Post.Hello.domain.User;
import com.pet.Post.Hello.repos.BusketRepo;
import com.pet.Post.Hello.repos.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@org.springframework.stereotype.Controller
public class MainController {
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private BusketRepo busketRepo;
    @Value("${upload.path}")
    private String uploadPath;
    @GetMapping("/")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Staff> staff = staffRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            staff = staffRepo.findByTag(filter);
        } else {
            staff = staffRepo.findAll();
        }

        model.addAttribute("allStaff", staff);
        model.addAttribute("filter", filter);

        return "main";
    }
    @PostMapping("/")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Staff staff,
                      BindingResult bindingResult,
                      Model model,
            @RequestParam("file") MultipartFile file)
    {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("staff", staff);
        }
        else{
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                try {
                    file.transferTo(new File(uploadPath + "/" + resultFilename));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                staff.setFilename(resultFilename);
            }
            model.addAttribute("staff", null);
            staffRepo.save(staff);
        }
        Iterable<Staff> allStaff = staffRepo.findAll();
        model.addAttribute("allStaff", allStaff);
        model.addAttribute("filter", "");
        return "main";
    }
    @GetMapping("/product/{id}")
    public String pruductId(@PathVariable("id") Long id, Model model){

        Staff staff = staffRepo.findById(id);
        model.addAttribute("staff", staff);
        return "product";
    }
    @Transactional
    @PostMapping("/product/{id}/delete")
    public String delete(@PathVariable("id") Long id,
                         @AuthenticationPrincipal User user){
        staffRepo.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/product/{id}/edit")
    public String editProduct(@PathVariable("id") Long id, Model model){
        Staff staff = staffRepo.findById(id);
        model.addAttribute("staff", staff);
        return "productEdit";
    }
    @PostMapping("/product/{id}/edit")
    public String updateProduct(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal User user,
            @Valid Staff staffUpdate,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file)
    {
        Staff staff = staffRepo.findById(id);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("staff", staffUpdate);
        }
        else{
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                try {
                    file.transferTo(new File(uploadPath + "/" + resultFilename));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                staff.setFilename(resultFilename);
            }
            staff.setTag(staffUpdate.getTag());
            staff.setText(staffUpdate.getText());
            model.addAttribute("staff", null);
            staffRepo.save(staff);
        }
        Iterable<Staff> allStaff = staffRepo.findAll();
        model.addAttribute("allStaff", allStaff);
        model.addAttribute("filter", "");
        return "main";
    }
    @GetMapping("/basket")
    public String basket(Model model, @AuthenticationPrincipal User user){
        ArrayList<Busket> allBusket = busketRepo.findAllByUserid(user.getId());
        ArrayList<Staff> allStaff = new ArrayList<>();

        for (Busket busket : allBusket)
            allStaff.add(staffRepo.findById(busket.getStaffid()));
        int temp = allBusket.size();
        for (int i = 0; i < temp; i++) {
            for (int j = i+1; j < temp; j++) {
                if(allStaff.get(i).getId().equals(allStaff.get(j).getId())) {
                    allStaff.remove(j);
                    temp--;
                }
            }
        }

            model.addAttribute("allStaff", allStaff);
        return "basket";
    }
    @PostMapping("/basket/{id}")
    public String basketAdd(@PathVariable("id") Long id,Model model, @AuthenticationPrincipal User user){
        Busket busket = new Busket();
        busket.setUserId(user.getId());
        busket.setStaffid(id);
        busketRepo.save(busket);
        return "redirect:/";
    }
    @Transactional
    @PostMapping("/basket/{id}/delete")
    public String basketDelete(@PathVariable("id") Long id,Model model, @AuthenticationPrincipal User user){
        //Busket busket = busketRepo.findByStaffidAndUserid(id, user.getId());
        ArrayList<Busket> busket = busketRepo.findAllByStaffidAndUserid(id, user.getId());
        for (Busket value : busket) {
            busketRepo.deleteById(value.getId());
        }
        return basket(model, user);
//        ArrayList<Busket> allBusket = busketRepo.findAllByUserid(user.getId());
//        ArrayList<Staff> allStaff = new ArrayList<>();
//        for (int i = 0; i < allBusket.size(); i++) {
//            allStaff.add(staffRepo.findById(allBusket.get(i).getStaffid()));
//        }
//
//        model.addAttribute("allStaff", allStaff);
//        return "basket";
    }
}
