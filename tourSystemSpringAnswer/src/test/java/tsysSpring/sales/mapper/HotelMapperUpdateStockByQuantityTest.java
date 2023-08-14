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

import tsysSpring.sales.entity.Hotel;
import tsysSpring.sales.entity.Item;
import tsysSpring.sales.test.util.ExecuteQueryForTestService;


/**
 * クラスカテゴリテスト
 * テストID                  ：PT003_03
 * インターフェース名  ：HotelMapper
 * メソッド名               ：updateStockByQuantity
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
@DisplayName("PT003_03:HotelMapper.updateStockByQuantity()メソッドのテスト")
public class HotelMapperUpdateStockByQuantityTest {

	@Autowired
	HotelMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@ExpectedDatabase(value = "classpath:HotelMapper/exoectedHotleUpdateStock.xml",
			assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT003_03_001:ホテル情報の在庫更新が成功する場合")
	void test001() {

		// setup
		Item testArg_item = new Hotel("HTL000110","福岡プリンセスホテル",15000,5,15,"FKO001","2022/04/20","04","福岡","5",15000);

		// assert
		int count = sut.updateStockByQuantity(testArg_item);

		assertThat(count).isEqualTo(1);
	}

	@Test
	@ExpectedDatabase(value = "classpath:HotelMapper/exoectedHotleInitial.xml",
			assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT003_03_002:ホテル情報の在庫更新が失敗する場合（存在しない商品コード）")
	void test002() {

		// setup
		Item testArg_item = new Hotel("HTL999110","福岡プリンセスホテル",15000,5,15,"FKO001","2022/04/20","04","福岡","5",15000);

		// assert
		int count = sut.updateStockByQuantity(testArg_item);

		assertThat(count).isEqualTo(0);
	}

	@Test
	@DatabaseSetup(type = DELETE_ALL)
	@DisplayName("PT003_03_003：ホテル情報の在庫更新が失敗する場合（レコード0件）")
	void test003() {

		// setup
		Item testArg_item = new Hotel("HTL000110","福岡プリンセスホテル",15000,5,15,"FKO001","2022/04/20","04","福岡","5",15000);

		// assert
		int count = sut.updateStockByQuantity(testArg_item);

		assertThat(count).isEqualTo(0);

	}

	@Nested
	@SpringBootTest
	@DisplayName("PT003_03_004:DataAccessExceptionが発生する場合")
	class CustomerTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("hotel", "hotel2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("hotel2", "hotel");
		}

		@Test
		@DisplayName("メンバー番号の変更に失敗する")
		void test004() throws Exception {

			// setup
			Item testArg_item = new Hotel("HTL000110","福岡プリンセスホテル",15000,5,15,"FKO001","2022/04/20","04","福岡","5",15000);

		// assert
					assertThatThrownBy(() -> sut.updateStockByQuantity(testArg_item))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
