package testutils;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static testutils.JsonFileReader.readFile;

public class RequestGenerator {
    public static void testValidPostRequest(MockMvc mockMvc, String controllerURL, String jsonDirectory) throws Exception {
        mockMvc.perform(
                post(controllerURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(readFile(jsonDirectory + "/create.json")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(readFile(jsonDirectory + "/state.json")));
    }

    public static void testValidPostRequestOk(MockMvc mockMvc, String controllerURL, String jsonRequest) throws Exception {
        mockMvc.perform(
                post(controllerURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(readFile(jsonRequest)))
                .andExpect(status().isOk());
    }

    public static void testInvalidPostRequest(MockMvc mockMvc, String controllerURL, String jsonRequest, HttpStatus expectedStatus) throws Exception {
        mockMvc.perform(
                post(controllerURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readFile(jsonRequest)))
                .andExpect(status().is(expectedStatus.value()));
    }

    public static void testValidGetRequest(MockMvc mockMvc, String controllerURL, String jsonResponse) throws Exception {
        mockMvc.perform(get(controllerURL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(readFile(jsonResponse)));
    }

    public static void testInvalidGetRequest(MockMvc mockMvc, String controllerURL, HttpStatus expectedStatus) throws Exception {
        mockMvc.perform(get(controllerURL))
                .andExpect(status().is(expectedStatus.value()));
    }
}
