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

import tsysSpring.sales.entity.OrderDetail;
import tsysSpring.sales.test.util.ExecuteQueryForTestService;


/**
 * クラスカテゴリテスト
 * テストID                  ：PT006_02
 * インターフェース名  ：OrderMapper
 * メソッド名               ：insertOrderDetal
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
@DisplayName("PT006_01:OrderMapper.insertOrderDetal()メソッドのテスト")
public class OrderMapperInsertOrderDetailTest {

	@Autowired
	OrderMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@ExpectedDatabase(value = "classpath:OrderMapper/expectedOrderDetailInsert.xml",
			assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT006_01_001:注文情報の登録が成功する場合")
	void test001() {

		// setup
		OrderDetail testArg_orderDetail = new OrderDetail();
		testArg_orderDetail.setOrderNo(16);
		testArg_orderDetail.setItemCode("HTL000110");
		testArg_orderDetail.setItemName("福岡プリンセスホテル");
		testArg_orderDetail.setPrice(15000);
		testArg_orderDetail.setQuantity(2);

		// assert
		sut.insertOrderDetail(testArg_orderDetail);

	}

	@Nested
	@SpringBootTest
	@DisplayName("PT006_01_002:DataAccessExceptionが発生する場合")
	class CustomerTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("orderdetail", "orderdetail2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("orderdetail2", "orderdetail");
		}

		@Test
		@DisplayName("注文情報の登録に失敗する")
		void test004() throws Exception {

			// setup
			OrderDetail testArg_orderDetail = new OrderDetail();
			testArg_orderDetail.setOrderNo(16);
			testArg_orderDetail.setItemCode("HTL000110");
			testArg_orderDetail.setItemName("福岡プリンセスホテル");
			testArg_orderDetail.setPrice(15000);
			testArg_orderDetail.setQuantity(2);

			// assert
					assertThatThrownBy(() -> sut.insertOrderDetail(testArg_orderDetail))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
