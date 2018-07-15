package codesquad.service;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.exception.UserLoginFailException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyVararg;
import static org.mockito.Mockito.*;


public class UserServiceTest {

    UserService userService;
    List<User> users;
    UserRepository mockUserRepository;
    @Before
    public void setUp() {
        userService = new UserService();
        users = Arrays.asList(
                new User((long) 1, "junsu", "1234", "junsu", "aa@aa"),
                new User((long) 2, "intae", "1234", "intae", "bb@bb")
        );
        mockUserRepository = Mockito.mock(UserRepository.class);
        when(mockUserRepository.findById((long)1)).thenReturn(Optional.ofNullable(users.get(0)));
        when(mockUserRepository.findByUserId("junsu")).thenReturn(Optional.ofNullable(users.get(0)));
        userService.setUserRepository(mockUserRepository);

    }

    @Test
    public void findById() {
        User expectedUser = new User((long) 1, "junsu", "1234", "junsu", "aa@aa");
        assertThat(userService.findById((long) 1)).isEqualTo(expectedUser); //상태기반 테스트
        verify(mockUserRepository).findById((long)1);   //행동기반 테스트
    }

    @Test
    public void login(){
        User loginUser = userService.login("junsu","1234");
        when(mockUserRepository.findByUserId("junsu")).thenReturn(Optional.ofNullable(users.get(0)));
        assertThat(loginUser).isNotEqualTo(null);
        assertThat(loginUser).isEqualTo(users.get(0));
        verify(mockUserRepository,times(1)).findByUserId("junsu");
    }

    @Test(expected = UserLoginFailException.class)
    public void loginExceptionTest(){
        String wrongPassword = "4567";
        userService.login("junsu",wrongPassword);
    }


    @Test
    public void mockStudy(){
        List mockList = Mockito.mock(List.class);
        when(mockList.get(0)).thenReturn(1);
        assertThat(mockList.get(0)).isEqualTo(1);

        mockList.clear();
        when(mockList.isEmpty()).thenReturn(true);
    }

}