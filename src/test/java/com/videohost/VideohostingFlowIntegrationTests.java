package com.videohost;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers(disabledWithoutDocker = true)
class VideohostingFlowIntegrationTests {

    @Container
    private static final MongoDBContainer MONGO_DB_CONTAINER =
        new MongoDBContainer(DockerImageName.parse("mongo:7.0"));

    @Autowired
    private ViewInfoRepository viewInfoRepository;

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void configureMongo(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
    }

    @AfterEach
    void cleanDatabase() {
        viewInfoRepository.deleteAll();
    }

    @Test
    void addEndpointPersistsViewInfoToMongo() throws Exception {
        mockMvc.perform(post("/add")
                .param("viewer", "Коваленко Олег Іванович")
                .param("producer", "Петренко Ірина Василівна")
                .param("watchedDate", "2024-09-14")
                .param("watchedTime", "20:30:00")
                .param("videoTitle", "Весняний ранок")
                .param("videoDuration", "00:15:45")
                .param("genre", "Драма")
                .param("producerCountry", "Україна")
                .param("videoRating", "8.7")
                .param("platform", "VideoHub"))
            .andExpect(status().is3xxRedirection());

        assertThat(viewInfoRepository.findAll())
            .hasSize(1)
            .extracting(ViewInfo::getVideoTitle)
            .containsExactly("Весняний ранок");
    }

    @Test
    void savedViewInfoAreRenderedOnTheMainPage() throws Exception {
        viewInfoRepository.save(new ViewInfo(
            "Коваленко Олег Іванович",
            "Петренко Ірина Василівна",
            "2024-09-14",
            "20:30:00",
            "Весняний ранок",
            "00:15:45",
            "Драма",
            "Україна",
            8.7,
            "VideoHub"
        ));

        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("videohost"))
            .andExpect(content().string(org.hamcrest.Matchers.containsString("Коваленко Олег Іванович")))
            .andExpect(content().string(org.hamcrest.Matchers.containsString("Весняний ранок")));
    }

    @Test
    void addPageRendersFormWithEmptyViewInfoModel() throws Exception {
        mockMvc.perform(get("/add"))
            .andExpect(status().isOk())
            .andExpect(view().name("add"))
            .andExpect(model().attributeExists("viewInfo"));
    }

    @Test
    void deleteEndpointRemovesPersistedViewInfoFromMongo() throws Exception {
        ViewInfo saved = viewInfoRepository.save(new ViewInfo(
            "Коваленко Олег Іванович",
            "Петренко Ірина Василівна",
            "2024-09-14",
            "20:30:00",
            "Весняний ранок",
            "00:15:45",
            "Драма",
            "Україна",
            8.7,
            "VideoHub"
        ));

        mockMvc.perform(post("/delete/{id}", saved.getId()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));

        assertThat(viewInfoRepository.findById(saved.getId())).isEmpty();
    }
}