import com.fasterxml.jackson.databind.ObjectMapper;
import com.sagpro.domains.user.application.dto.UserRequest;
import com.sagpro.domains.user.application.dto.UserResponse;
import com.sagpro.domains.user.domain.UserEntity;
import com.sagpro.domains.user.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void Posts_등록된다() throws Exception {
        //given
        String userId = "title";
        String userPw = "content";
        String snsId = "aeae";
        UserRequest requestDto = UserRequest.builder()
                .userId(userId)
                .userPw(userPw)
                .snsId(snsId)
                .build();

        String url = "http://localhost:" + port + "/user/add";

        //when
        ResponseEntity<Long> response = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
       assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
       assertThat(response.getBody()).isGreaterThan(0L);

       List<UserEntity> all = userRepository.findAll();
       assertThat(all.get(0).getUserId()).isEqualTo(userId);
       assertThat(all.get(0).getUserPw()).isEqualTo(userPw);
       assertThat(all.get(0).getSnsId()).isEqualTo(snsId);
    }

}
