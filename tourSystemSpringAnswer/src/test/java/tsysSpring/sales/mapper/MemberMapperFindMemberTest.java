package tsysSpring.sales.mapper;

import static com.github.springtestdbunit.annotation.DatabaseOperation.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import tsysSpring.sales.entity.Member;
import tsysSpring.sales.test.util.ExecuteQueryForTestService;

/**
 * クラスカテゴリテスト
 * テストID                  ：PT001_02
 * インターフェース名  ：MemberMapper
 * メソッド名               ：findMember
 *
 * @author FLM
 * @version 1.0 yyyy/mm/dd
 */
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:AllTest/setupTourDB.xml")
@DatabaseTearDown("classpath:AllTest/setupTourDB.xml")
@DisplayName("PT001_01:EmployeeMapper.findOne()メソッドのテスト")
public class MemberMapperFindMemberTest {

	@Autowired
	MemberMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@DisplayName("PT001_02_001：メンバーが検索が成功する場合")
	void testFindOne_test1() {
		// setup
		String testArg_memberCode = "CM0000001";

		Member expected = new Member("CM0000001","Customer", "佐藤ハナコ", "pass",   "108-6112", "東京都", "港区港南", "03-3333-1234","hanako@aaa.com");

		// assert
		assertThat(sut.findMember(testArg_memberCode)).isEqualTo(expected);

	}

	@Test
	@DisplayName("PT001_02_002：メンバーが検索が失敗する場合（存在しないメンバーコード）")
	void testFindOne_test2() {
		// setup
		String testArg_memberCode = "CM0000099";

		// assert
		assertThat(sut.findMember(testArg_memberCode)).isNull();

	}

	@Test
	@DatabaseSetup(type = DELETE_ALL)
	@DisplayName("PT001_02_003：メンバーが検索が失敗する場合（レコード0件）")
	void testFindOne_test3() {
		// setup
		String testArg_memberCode = "CM0000001";

		// assert
		assertThat(sut.findMember(testArg_memberCode)).isNull();
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT001_02_004：DataAccessExceptionが発生する場合")
	class MemberTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("member", "member2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("member2", "member");
		}

		@Test
		@DisplayName("PT001_02_004：DataAccessExceptionが発生する場合")
		void testFindOne_test4() throws Exception {
			// setup
			String testArg_memberCode = "CM0000001";

			// assert
			assertThatThrownBy(() -> sut.findMember(testArg_memberCode))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
