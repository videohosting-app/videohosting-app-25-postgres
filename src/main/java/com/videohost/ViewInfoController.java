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

    // РЕДАГУВАННЯ 

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        ViewInfo viewInfo = viewInfoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Невірний id: " + id));
        model.addAttribute("viewInfo", viewInfo);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateViewInfo(@PathVariable String id, @ModelAttribute ViewInfo viewInfo) {
        ViewInfo existing = viewInfoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Невірний id: " + id));
        existing.setViewer(viewInfo.getViewer());
        existing.setProducer(viewInfo.getProducer());
        existing.setWatchedDate(viewInfo.getWatchedDate());
        existing.setWatchedTime(viewInfo.getWatchedTime());
        existing.setVideoTitle(viewInfo.getVideoTitle());
        existing.setVideoDuration(viewInfo.getVideoDuration());
        existing.setGenre(viewInfo.getGenre());
        existing.setProducerCountry(viewInfo.getProducerCountry());
        existing.setVideoRating(viewInfo.getVideoRating());
        existing.setPlatform(viewInfo.getPlatform());
        viewInfoRepository.save(existing);
        return "redirect:/";
    }

    //  ВИДАЛЕННЯ 

    @PostMapping("/delete/{id}")
    public String deleteViewInfo(@PathVariable String id) {
        viewInfoRepository.deleteById(id);
        return "redirect:/";
    }
}