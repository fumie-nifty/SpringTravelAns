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

import tsysSpring.sales.entity.Hotel;
import tsysSpring.sales.test.util.ExecuteQueryForTestService;

/**
 * クラスカテゴリテスト
 * テストID                  ：PT003_01
 * インターフェース名  ：HotelMapper
 * メソッド名               ：findHotel
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
@DisplayName("PT003_01:HotelMapper.findHotel()メソッドのテスト")
public class HotelMapperFindHotelTest {

	@Autowired
	HotelMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@DisplayName("PT003_01_001：ホテル情報の検索が成功する場合")
	void testFindOne_test1() {
		// setup
		String testArg_itemrCode = "HTL000190";

		Hotel expected = new Hotel();
		expected.setItemCode("HTL000190");
		expected.setItemName("福岡プリンセスホテル");
		expected.setPrice(15000);
		expected.setStock(15);
		expected.setHotelCode("FKO001");
		expected.setDate("2022/07/09");
		expected.setCityCode("04");
		expected.setCityName("福岡");
		expected.setGrade("5");
		expected.setBasicPrice(15000);

		// assert
		assertThat(sut.findHotel(testArg_itemrCode)).isEqualTo(expected);

	}

	@Test
	@DisplayName("PT003_01_002：ホテル情報の検索が失敗する場合（存在しない商品コード）")
	void testFindOne_test2() {
		// setup
		String testArg_itemrCode = "HTL999190";

		// assert
		assertThat(sut.findHotel(testArg_itemrCode)).isNull();

	}

	@Test
	@DatabaseSetup(type = DELETE_ALL)
	@DisplayName("PT003_01_003：ホテル情報の検索が失敗する場合（レコード0件）")
	void testFindOne_test3() {
		// setup
		String testArg_itemrCode = "HTL000190";

		// assert
		assertThat(sut.findHotel(testArg_itemrCode)).isNull();
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT003_01_004：DataAccessExceptionが発生する場合")
	class MemberTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("hotel", "hotel2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("hotel2", "hotel");
		}

		@Test
		@DisplayName("PT003_01_004：DataAccessExceptionが発生する場合")
		void testFindOne_test4() throws Exception {
			// setup
			String testArg_itemrCode = "HTL000190";

			// assert
			assertThatThrownBy(() -> sut.findHotel(testArg_itemrCode))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
