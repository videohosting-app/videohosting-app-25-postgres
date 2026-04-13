package com.videohost;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Веб-контролер для перегляду таблиці відеохостингу, додавання нових записів та видалення існуючих.
 */
@Controller
public class ViewInfoController {

    private final ViewInfoRepository viewInfoRepository;

    public ViewInfoController(ViewInfoRepository viewInfoRepository) {
        this.viewInfoRepository = viewInfoRepository;
    }

    @GetMapping("/")
    public String viewVideohost(Model model) {
        List<ViewInfo> viewInfoList = viewInfoRepository.findAll();
        model.addAttribute("viewInfoList", viewInfoList);
        return "videohost";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("viewInfo", new ViewInfo());
        return "add";
    }

    @PostMapping("/add")
    public String addViewInfo(@ModelAttribute ViewInfo viewInfo) {
        viewInfoRepository.save(viewInfo);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteViewInfo(@PathVariable String id) {
        viewInfoRepository.deleteById(id);
        return "redirect:/";
    }
}