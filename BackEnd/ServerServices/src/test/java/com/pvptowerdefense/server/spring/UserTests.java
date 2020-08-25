package com.pvptowerdefense.server.spring;

import com.pvptowerdefense.server.spring.users.User;
import com.pvptowerdefense.server.spring.users.UsersDao;
import com.pvptowerdefense.server.spring.users.UsersService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTests {
	@LocalServerPort
	private static int port;
	private static String url;

	@Autowired
	private UsersService usersService;

	private UsersService usersServiceMock;

	private User testUser;
	private List<User> testUserList;

	@BeforeAll
	static void setUrl() {
		url = "http://localhost:" + port + "/users";
	}

	@BeforeEach
	void setup() {
		testUser = new User("test1", "TestUser1", "testemail", "TestFN", "TestLN"
				, "User");
		testUserList = new ArrayList<>();
		testUserList.add(testUser);

		UsersDao usersDaoMock = Mockito.mock(UsersDao.class);
		usersServiceMock = new UsersService(usersDaoMock);

		Mockito.when(usersDaoMock.findAll())
				.thenReturn(testUserList);
		Mockito.when(usersDaoMock.existsById(testUser.getPhoneId()))
				.thenReturn(true);
		Mockito.when(usersDaoMock.findUserByPhoneId(testUser.getPhoneId()))
                .thenReturn(testUser);

		try {
			usersService.addUserToDb(testUser);
		} catch (Exception ignored) {
		}
	}

	@AfterEach
	void removeCard() {
		try {
			usersService.deleteUserById(testUser.getPhoneId());
		} catch (Exception ignored) {
		}
	}

	@Test
	void deleteUserTest() {
		User testUserGet = usersService.findUserById((testUser.getPhoneId()));

		Assertions.assertNotNull(testUserGet);
		Assertions.assertAll(
				() -> Assertions.assertEquals(testUser.getPhoneId(), testUserGet.getPhoneId()),
				() -> Assertions.assertEquals(testUser.getFirstName(), testUserGet.getFirstName()),
				() -> Assertions.assertEquals(testUser.getLastName(), testUserGet.getLastName()),
				() -> Assertions.assertEquals(testUser.getUserName(), testUserGet.getUserName()),
				() -> Assertions.assertEquals(testUser.getUserType(), testUserGet.getUserType()),
				() -> Assertions.assertEquals(testUser.getEmail(), testUserGet.getEmail())
		);

		usersService.deleteUserById(testUser.getPhoneId());
		User deletedUser = usersService.findUserById(testUser.getPhoneId());

		Assertions.assertNull(deletedUser);

	}

	@Test
	void getAllUsersTestMock() {
		List<User> allUsers = usersServiceMock.getAllUsers();

		Assertions.assertIterableEquals(testUserList, allUsers);
	}

	@Test
	void getUserTestMock() {
		User testUserGet = usersService.findUserById(testUser.getPhoneId());

		Assertions.assertNotNull(testUserGet);
		Assertions.assertAll(
				() -> Assertions.assertEquals(testUser.getPhoneId(), testUserGet.getPhoneId()),
				() -> Assertions.assertEquals(testUser.getFirstName(), testUserGet.getFirstName()),
				() -> Assertions.assertEquals(testUser.getLastName(), testUserGet.getLastName()),
				() -> Assertions.assertEquals(testUser.getUserName(), testUserGet.getUserName()),
				() -> Assertions.assertEquals(testUser.getUserType(), testUserGet.getUserType()),
				() -> Assertions.assertEquals(testUser.getEmail(), testUserGet.getEmail())
		);
	}

	@Test
	void getUserTypeTest() {
		User testUserGet = usersService.findUserById(testUser.getPhoneId());

		Assertions.assertEquals(testUserGet.getUserType(), testUser.getUserType());
	}

//	@Test
//	void getUserCardsTest() {
//		User testUserGet = usersService.findUserById(testUser.getPhoneId());
//
//		Assertions.assertIterableEquals(testUserGet.getOwnedCards(), testUser.getOwnedCards());
//	}

	@Test
	void getUserTrophiesTest() {
		User testUserGet = usersService.findUserById(testUser.getPhoneId());

		Assertions.assertEquals(testUserGet.getTrophies(), testUser.getTrophies());
	}


}
