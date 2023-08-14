/**
 * CustomerNumberingMapperUpdateCustomerCodeTest.java
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package tsysSpring.sales.mapper;

import static com.github.springtestdbunit.annotation.DatabaseOperation.*;
import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.*;
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
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import tsysSpring.sales.test.util.ExecuteQueryForTestService;


/**
 * クラスカテゴリテスト
 * テストID                  ：PT002_02
 * インターフェース名  ：MemberNumberingMapper
 * メソッド名               ：updateMemberCode
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
@DisplayName("PT002_02:MemberNumberingMapper.updateMemberCode()メソッドのテスト")
public class MemberNumberingMapperUpdateMemberCodeTest {

	@Autowired
	MemberNumberingMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@ExpectedDatabase(value = "classpath:MemberNumberingMapper/expectedMemberNumberingUpdate.xml",
			assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT002_02_001:更新に成功する場合")
	void test001() {

		// assert
		int count = sut.updateMemberCode();

		assertThat(count).isEqualTo(1);
	}

	@Test
	@DatabaseSetup(type = DELETE_ALL)
	@DisplayName("PT002_02_002：更新が失敗する場合(レコード0件)")
	void test002() {

		// assert
		int count = sut.updateMemberCode();

		assertThat(count).isEqualTo(0);

	}

	@Nested
	@SpringBootTest
	@DisplayName("PT002_02_003:DataAccessExceptionが発生する場合")
	class CustomerTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("membernumbering", "membernumbering2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("membernumbering2", "membernumbering");
		}

		@Test
		@DisplayName("メンバー番号の変更に失敗する")
		void test003() throws Exception {

		// assert
					assertThatThrownBy(() -> sut.updateMemberCode())
				.isInstanceOf(DataAccessException.class);
		}
	}
}
