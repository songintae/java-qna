package codesquad.service;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserServiceTest {

    UserService userService;
    List<User> users;

    @Before
    public void setUp() {
        userService = new UserService();
        users = Arrays.asList(
                new User((long) 1, "junsu", "1234", "junsu", "aa@aa"),
                new User((long) 2, "intae", "1234", "intae", "bb@bb")
        );

    }

    @Test
    public void findById() {
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        when(mockUserRepository.findById((long)1)).thenReturn(Optional.ofNullable(users.get(0)));
        userService.setUserRepository(mockUserRepository);
        User expectedUser = new User((long) 1, "junsu", "1234", "junsu", "aa@aa");
        assertThat(userService.findById((long) 1)).isEqualTo(expectedUser);
        verify(mockUserRepository).findById((long)1);
    }

}