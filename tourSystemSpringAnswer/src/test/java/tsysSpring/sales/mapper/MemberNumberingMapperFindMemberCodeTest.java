/**
 * CustomerNumberingMapperFindCustomerCodeTest.java
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

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

import tsysSpring.sales.test.util.ExecuteQueryForTestService;

/**
 * クラスカテゴリテスト
 * テストID                  ：PT002_01
 * インターフェース名  ：MemberNumberingMapper
 * メソッド名               ：findMemberCode
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
@DisplayName("PT002_01:MemberNumberingMapper.findMemberCode()メソッドのテスト")
public class MemberNumberingMapperFindMemberCodeTest {

	@Autowired
	MemberNumberingMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@DisplayName("PT002_01_001：検索が成功する場合")
	void test001() {
		// setup
		int expected = 11;

		// assert
		assertThat(sut.findMemberCode()).isEqualTo(expected);

	}

	@Test
	@DatabaseSetup(type = DELETE_ALL)
	@DisplayName("PT002_01_002：検索が失敗する場合(レコード0件)")
	void test002() {

		// assert
		assertThat(sut.findMemberCode()).isNull();

	}

	@Nested
	@SpringBootTest
	@DisplayName("PT002_01_003：DataAccessExceptionが発生する場合")
	class CustomernumberingTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("membernumbering", "membernumbering2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("membernumbering2", "membernumbering");
		}

		@Test
		@DisplayName("メンバー番号の取得に失敗する")
		void test003() throws Exception {
			// assert
			assertThatThrownBy(() -> sut.findMemberCode())
				.isInstanceOf(DataAccessException.class);
		}
	}
}
