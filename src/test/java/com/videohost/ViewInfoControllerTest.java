package com.videohost;

import static com.videohost.support.ViewInfoTestDataBuilder.aViewInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

class ViewInfoControllerTest {

    @Test
    void viewVideohostReturnsVideohostViewName() {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        when(repository.findAll()).thenReturn(Collections.emptyList());
        ViewInfoController controller = new ViewInfoController(repository);
        Model model = new ExtendedModelMap();

        String viewName = controller.viewVideohost(model);

        assertEquals("videohost", viewName);
    }

    @Test
    void viewVideohostAddsAllViewInfoToModel() {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        ViewInfo viewInfo = aViewInfo().build();
        when(repository.findAll()).thenReturn(List.of(viewInfo));
        ViewInfoController controller = new ViewInfoController(repository);
        Model model = new ExtendedModelMap();

        controller.viewVideohost(model);

        verify(repository, times(1)).findAll();
        assertNotNull(model.getAttribute("viewInfoList"));
        assertEquals(1, ((List<?>) model.getAttribute("viewInfoList")).size());
    }

    @Test
    void showAddFormReturnsAddViewName() {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        ViewInfoController controller = new ViewInfoController(repository);
        Model model = new ExtendedModelMap();

        String viewName = controller.showAddForm(model);

        assertEquals("add", viewName);
    }

    @Test
    void showAddFormAddsEmptyViewInfoToModel() {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        ViewInfoController controller = new ViewInfoController(repository);
        Model model = new ExtendedModelMap();

        controller.showAddForm(model);

        assertNotNull(model.getAttribute("viewInfo"));
    }

    @Test
    void addViewInfoSavesToRepositoryAndRedirects() {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        ViewInfoController controller = new ViewInfoController(repository);
        ViewInfo viewInfo = aViewInfo().build();

        String viewName = controller.addViewInfo(viewInfo);

        verify(repository, times(1)).save(viewInfo);
        assertEquals("redirect:/", viewName);
    }

    @Test
    void deleteViewInfoRemovesFromRepositoryAndRedirects() {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        ViewInfoController controller = new ViewInfoController(repository);

        String viewName = controller.deleteViewInfo("id-123");

        verify(repository, times(1)).deleteById("id-123");
        assertEquals("redirect:/", viewName);
    }
}