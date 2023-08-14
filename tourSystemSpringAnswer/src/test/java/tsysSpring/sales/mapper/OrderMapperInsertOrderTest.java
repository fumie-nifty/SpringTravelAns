/**
 * CustomerNumberingMapperUpdateCustomerCodeTest.java
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package tsysSpring.sales.mapper;

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

import tsysSpring.sales.entity.Order;
import tsysSpring.sales.test.util.ExecuteQueryForTestService;


/**
 * クラスカテゴリテスト
 * テストID                  ：PT006_01
 * インターフェース名  ：OrderMapper
 * メソッド名               ：insertOrder
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
@DisplayName("PT006_01:OrderMapper.insertOrder()メソッドのテスト")
public class OrderMapperInsertOrderTest {

	@Autowired
	OrderMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@ExpectedDatabase(value = "classpath:OrderMapper/expectedOrderInsert.xml",
			assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT006_01_001:注文情報の登録が成功する場合")
	void test001() {

		// setup
		Order testArg_order = new Order();
		testArg_order.setOrderDate("2022/06/25");
		testArg_order.setOrderTotal(30000);
		testArg_order.setMemberCode("CM0000001");
		testArg_order.setPayment("1");

		// assert
		sut.insertOrder(testArg_order);

	}

	@Nested
	@SpringBootTest
	@DisplayName("PT006_01_002:DataAccessExceptionが発生する場合")
	class CustomerTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("OrderMaster", "OrderMaster2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("OrderMaster2", "OrderMaster");
		}

		@Test
		@DisplayName("注文情報の登録に失敗する")
		void test004() throws Exception {

			// setup
			Order testArg_order = new Order();
			testArg_order.setOrderDate("2022/06/25");
			testArg_order.setOrderTotal(30000);
			testArg_order.setMemberCode("CM0000001");
			testArg_order.setPayment("1");

			// assert
					assertThatThrownBy(() -> sut.insertOrder(testArg_order))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
